package com.edugroupe.AirlineStatsForm;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.edugroupe.AirlineStatsForm.util.AeroportGroupComparator;
import com.edugroupe.AirlineStatsForm.util.AeroportPartitioner;
import com.edugroupe.AirlineStatsForm.util.AeroportSortComparator;
import com.edugroupe.AirlineStatsForm.util.AirlineDataUtil;
import com.edugroupe.AirlineStatsForm.util.CompagnieCodePartitioner;
import com.edugroupe.AirlineStatsForm.util.CompanyGroupComparator;
import com.edugroupe.AirlineStatsForm.util.CompanySortComparator;
import com.edugroupe.AirlineStatsForm.util.VolAeroportClef;
import com.edugroupe.AirlineStatsForm.util.VolCompagnieClef;

public class SelectJoinAirportJob  extends Configured implements Tool{

	public static class VolMapper extends Mapper<LongWritable, Text, VolAeroportClef, Text> {

		@Override
		protected void map(LongWritable key, Text value,
							Context context)
				throws IOException, InterruptedException {
			if (!AirlineDataUtil.isHeader(value)) {
				
				String[] datas = AirlineDataUtil.getSelectedColumnsC(value);
				VolAeroportClef clef = new VolAeroportClef(VolAeroportClef.TYPE_VOL,
															 datas[3]);
				context.write(	clef, 
								new Text(AirlineDataUtil.mergeStringArray(datas, ",").toString()));
			}
		}
	}
	
	public static class AeroportMapper extends Mapper<LongWritable, Text, VolAeroportClef, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Context context)
				throws IOException, InterruptedException {
			String[] infosAeroport = AirlineDataUtil.parseAeroportDetails(value);
			
			VolAeroportClef clef = new VolAeroportClef(VolAeroportClef.TYPE_AEROPORT,
														infosAeroport[0].trim());
			context.write(clef, new Text(infosAeroport[1] + "," + infosAeroport[2]));
		}
	}
	
	
	public static class VolAeroportReducer extends Reducer<VolAeroportClef, Text, NullWritable, Text> {
		
		private String aeroportCourant = "inconnu";

		
		@Override
		protected void reduce(VolAeroportClef clef, Iterable<Text> donnees,
				Context context)
				throws IOException, InterruptedException {
			int totalVols = 0;
			int total_vols_ok = 0;
			int total_vols_annules = 0;
			double distance_vols = 0.0;
			double retard_departs_vols = 0.0;
			long minutes_total = 0;
			
			HashSet<String> code_compagnies = new HashSet<>();
			
			for (Text donnee : donnees) {
				if (clef.type_clef.get() == VolAeroportClef.TYPE_AEROPORT) {
					this.aeroportCourant = donnee.toString();
				}
				else {
					String[] champs = donnee.toString().split(",");
					code_compagnies.add(champs[12]);
					
					totalVols++;
					if (AirlineDataUtil.parseBoolean(champs[10], false)) { 
						total_vols_annules++;
					}
					else if (!AirlineDataUtil.parseBoolean(champs[11], false)) {
						total_vols_ok++;
						distance_vols += AirlineDataUtil.parseMinutes(champs[5], 0);
						retard_departs_vols += AirlineDataUtil.parseMinutes(champs[8], 0);
						int time = AirlineDataUtil.parseMinutes(champs[1], 0);
						minutes_total += ((time / 100) * 60 + (time % 100));
					}
				}
			}
			
			
			if (clef.type_clef.get() == VolAeroportClef.TYPE_VOL) {
				if (totalVols > 0) {
					if (total_vols_ok > 0) {
						minutes_total = minutes_total / total_vols_ok;
						long heure_moyenne = minutes_total / 60;
						long minute_moyenne = minutes_total % 60;
						StringBuilder sb = new StringBuilder(aeroportCourant).append(": ");
						sb.append(" total=").append(totalVols)
							.append(" nb Compagnies=").append(code_compagnies.size())
							.append(", totalok=").append(total_vols_ok)
							.append(", distance moy=").append(distance_vols / total_vols_ok)
							.append(", retard depart moy=").append(retard_departs_vols / total_vols_ok)
							.append(", annulation =").append(total_vols_annules * 100.0 / totalVols)
							.append(", heure moyenne depart=").append(heure_moyenne).append(":").append(minute_moyenne);
						context.write(NullWritable.get(), new Text(sb.toString()));
					}
					else {
						StringBuilder sb = new StringBuilder(aeroportCourant).append(": ");
						sb.append(" total=").append(totalVols)
							.append(", totalok=").append(total_vols_ok)
							.append(", annulation =").append(total_vols_annules * 100.0 / totalVols);
					}
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		//Configuration conf = new Configuration();
		ToolRunner.run(new SelectJoinAirportJob(), args);

	}


	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectJoinAirportJob.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		// sortie des mapper et entree reducteur
		job.setMapOutputKeyClass(VolAeroportClef.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setNumReduceTasks(2);
		
		// parsage des arguments en ligne de commande
		String[] arguments = new GenericOptionsParser(getConf(), args).getRemainingArgs();

		
		// mapper des vols
		MultipleInputs.addInputPath(job, new Path(arguments[0]), TextInputFormat.class, VolMapper.class);
		// mapper des compagnies
		MultipleInputs.addInputPath(job, new Path(arguments[1]), TextInputFormat.class, AeroportMapper.class);

		// sortie du reducer
	    FileOutputFormat.setOutputPath(job, new Path(arguments[2]));
		
	    // tri et repartition pour notre jointure
	    // INDISPENSABLE!
	    job.setPartitionerClass(AeroportPartitioner.class);
	    job.setSortComparatorClass(AeroportSortComparator.class);
	    job.setGroupingComparatorClass(AeroportGroupComparator.class);
	    
	    // le reducteur
	    job.setReducerClass(VolAeroportReducer.class);
	    
	    boolean status = job.waitForCompletion(true);
	     
        if (status) {
        	return 0;
        }
        else {
        	return 1;
        }
	     
	}

}
