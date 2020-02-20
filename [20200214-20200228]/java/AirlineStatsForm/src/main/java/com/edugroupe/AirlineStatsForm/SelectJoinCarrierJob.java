package com.edugroupe.AirlineStatsForm;

import java.io.IOException;

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

import com.edugroupe.AirlineStatsForm.util.AirlineDataUtil;
import com.edugroupe.AirlineStatsForm.util.CompagnieCodePartitioner;
import com.edugroupe.AirlineStatsForm.util.CompanyGroupComparator;
import com.edugroupe.AirlineStatsForm.util.CompanySortComparator;
import com.edugroupe.AirlineStatsForm.util.VolCompagnieClef;

public class SelectJoinCarrierJob  extends Configured implements Tool{

	public static class VolMapper extends Mapper<LongWritable, Text, VolCompagnieClef, Text> {

		@Override
		protected void map(LongWritable key, Text value,
							Context context)
				throws IOException, InterruptedException {
			if (!AirlineDataUtil.isHeader(value)) {
				
				String[] datas = AirlineDataUtil.getSelectedColumnsC(value);
				VolCompagnieClef clef = new VolCompagnieClef(VolCompagnieClef.TYPE_VOL,
															 datas[12]);
				context.write(	clef, 
								new Text(AirlineDataUtil.mergeStringArray(datas, ",").toString()));
			}
		}
	}
	
	public static class CompagnieMapper extends Mapper<LongWritable, Text, VolCompagnieClef, Text> {

		@Override
		protected void map(LongWritable key, Text value,
				Context context)
				throws IOException, InterruptedException {
			String[] infosCompagnie = AirlineDataUtil.parseCompanyDetails(value);
			VolCompagnieClef clef = new VolCompagnieClef(VolCompagnieClef.TYPE_COMPAGNIE,
														 infosCompagnie[0].trim());
			context.write(clef, new Text(infosCompagnie[1]));
		}
	}
	
	
	public static class VolCompagnieReducer extends Reducer<VolCompagnieClef, Text, NullWritable, Text> {
		
		private String compagnieCourante = "inconnue";

		
		@Override
		protected void reduce(VolCompagnieClef clef, Iterable<Text> donnees,
				Context context)
				throws IOException, InterruptedException {
			int totalVols = 0;
			int total_vols_ok = 0;
			double distance_vols = 0.0;
			double retard_departs_vols = 0.0;
			double retard_arrivees_vols = 0.0;
			
			for (Text donnee : donnees) {
				if (clef.type_clef.get() == VolCompagnieClef.TYPE_COMPAGNIE) {
					this.compagnieCourante = donnee.toString();
				}
				else {
					String[] champs = donnee.toString().split(",");
					if (AirlineDataUtil.parseBoolean(champs[10], false) 
						|| AirlineDataUtil.parseBoolean(champs[11], false)) {
						totalVols++;
					}
					else {
						totalVols++;
						total_vols_ok++;
						distance_vols += AirlineDataUtil.parseMinutes(champs[5], 0);
						retard_departs_vols += AirlineDataUtil.parseMinutes(champs[8], 0);
						retard_arrivees_vols += AirlineDataUtil.parseMinutes(champs[9], 0);
					}
					/*context.write(NullWritable.get(),
							new Text(donnee.toString() + ",\"" + this.compagnieCourante + "\""));*/
				}
			}
			
			if (clef.type_clef.get() == VolCompagnieClef.TYPE_VOL) {
				StringBuilder sb = new StringBuilder(compagnieCourante).append(": ");
				sb.append(" total=").append(totalVols)
					.append(", totalok=").append(total_vols_ok)
					.append(", distance moy=").append(distance_vols / total_vols_ok)
					.append(", retard depart moy=").append(retard_departs_vols / total_vols_ok)
					.append(", retard arrivee moy=").append(retard_arrivees_vols / total_vols_ok);
				context.write(NullWritable.get(), new Text(sb.toString()));
			}
		}
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		//Configuration conf = new Configuration();
		ToolRunner.run(new SelectJoinCarrierJob(), args);

	}


	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectJoinCarrierJob.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		// sortie des mapper et entree reducteur
		job.setMapOutputKeyClass(VolCompagnieClef.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setNumReduceTasks(2);
		
		// parsage des arguments en ligne de commande
		String[] arguments = new GenericOptionsParser(getConf(), args).getRemainingArgs();

		
		// mapper des vols
		MultipleInputs.addInputPath(job, new Path(arguments[0]), TextInputFormat.class, VolMapper.class);
		// mapper des compagnies
		MultipleInputs.addInputPath(job, new Path(arguments[1]), TextInputFormat.class, CompagnieMapper.class);

		// sortie du reducer
	    FileOutputFormat.setOutputPath(job, new Path(arguments[2]));
		
	    // tri et repartition pour notre jointure
	    // INDISPENSABLE!
	    job.setPartitionerClass(CompagnieCodePartitioner.class);
	    job.setSortComparatorClass(CompanySortComparator.class);
	    job.setGroupingComparatorClass(CompanyGroupComparator.class);
	    
	    // le reducteur
	    job.setReducerClass(VolCompagnieReducer.class);
	    
	    boolean status = job.waitForCompletion(true);
	     
        if (status) {
        	return 0;
        }
        else {
        	return 1;
        }
	     
	}

}
