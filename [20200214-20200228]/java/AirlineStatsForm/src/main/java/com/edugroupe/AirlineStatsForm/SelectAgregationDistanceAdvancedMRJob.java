package com.edugroupe.AirlineStatsForm;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.edugroupe.AirlineStatsForm.util.AirlineDataUtil;


public class SelectAgregationDistanceAdvancedMRJob extends Configured implements Tool {

	public static final IntWritable DISTANCE_0_100 = new IntWritable(0);
	public static final IntWritable DISTANCE_100_200 = new IntWritable(1);
	public static final IntWritable DISTANCE_200_400 = new IntWritable(2);
	public static final IntWritable DISTANCE_400_800 = new IntWritable(3);
	public static final IntWritable DISTANCE_800_MARS = new IntWritable(4);
	
	
	public static final IntWritable VOL = new IntWritable(0);
	public static final IntWritable ANNULE = new IntWritable(1);
	public static final IntWritable DETOURNE = new IntWritable(2);
	public static final IntWritable DEPART_RETARD = new IntWritable(3);
	public static final IntWritable ARRIVEE_RETARD = new IntWritable(4);
	public static final IntWritable DEPART_OK = new IntWritable(5);
	public static final IntWritable ARRIVEE_OK = new IntWritable(6);
	
	
	public static class VolMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

		@Override
		protected void map(LongWritable key, Text value,
				Context context)
				throws IOException, InterruptedException {
			if (!AirlineDataUtil.isHeader(value)) {
				String[] values = AirlineDataUtil.getSelectedColumnsB(value);
				int  arrivalDelay = AirlineDataUtil.parseMinutes(values[9], 0);
				int  departureDelay = AirlineDataUtil.parseMinutes(values[8], 0);
				boolean isCancelled = AirlineDataUtil.parseBoolean(values[10], false);
				boolean isDiverted = AirlineDataUtil.parseBoolean(values[11], false);
				int distance = AirlineDataUtil.parseMinutes(values[5], 0);
				IntWritable classeDistance = null;
				if (distance < 100)
					classeDistance =  DISTANCE_0_100;
				else if (distance < 200)
					classeDistance =  DISTANCE_100_200;
				else if (distance < 400)
					classeDistance =  DISTANCE_200_400;
				else if (distance < 800)
					classeDistance =  DISTANCE_400_800;
				else
					classeDistance =  DISTANCE_800_MARS;
			//-----------------------------------------------	
				context.write(classeDistance, VOL);
				if (isCancelled) {
					context.write(classeDistance, ANNULE);
				}
				else if (isDiverted) {
					context.write(classeDistance, DETOURNE);
				}
				else {
					if (departureDelay > 15)
						context.write(classeDistance, DEPART_RETARD);
					else
						context.write(classeDistance, DEPART_OK);
					if (arrivalDelay > 15)
						context.write(classeDistance, ARRIVEE_RETARD);
					else
						context.write(classeDistance, ARRIVEE_OK);
				}
			}
		}
	}
	
	public static class VolReducer extends Reducer<IntWritable, IntWritable, NullWritable, Text> {

		@Override
		protected void reduce(IntWritable distance_class,
							Iterable<IntWritable> infos,
							Context context)
				throws IOException, InterruptedException {
			
			long totalVols = 0;
			long totalAnnule = 0;
			long totalDetourne = 0;
			long totalRetardDepart = 0;
			long totalRetardArrivee = 0;
			long totalOKDepart = 0;
			long totalOKArrive = 0;

			for (IntWritable etatVol : infos) {
				if (etatVol.equals(VOL))
					totalVols++;
				else if (etatVol.equals(ANNULE))
					totalAnnule++;
				else if (etatVol.equals(DETOURNE))
					totalDetourne++;
				else if (etatVol.equals(ARRIVEE_OK))
					totalOKArrive++;
				else if (etatVol.equals(DEPART_OK))
					totalOKDepart++;
				else if (etatVol.equals(ARRIVEE_RETARD))
					totalRetardArrivee++;
				else if (etatVol.equals(DEPART_RETARD))
					totalRetardDepart++;
			}
			
			StringBuilder sb = new StringBuilder();
			if (distance_class.equals(DISTANCE_0_100))
				sb.append("vols 0-100: ");
			else if (distance_class.equals(DISTANCE_100_200))
				sb.append("vols 100-200: ");
			else if (distance_class.equals(DISTANCE_200_400))
				sb.append("vols 200-400: ");
			else if (distance_class.equals(DISTANCE_400_800))
				sb.append("vols 400-800: ");
			else
				sb.append("vols 800-mars: ");
			
			DecimalFormat df = new DecimalFormat("0.00");
			
			sb.append("total vols = ").append(totalVols)
			  .append(" , annules =").append(df.format(totalAnnule * 100.0 / totalVols))
			  .append(" %,  reroute =").append(df.format(totalDetourne * 100.0 / totalVols))
			  .append(" %, depart en retard =").append(df.format(totalRetardDepart * 100.0 / totalVols))
			  .append(" %, arrivee en retard =").append(df.format(totalRetardArrivee * 100.0 / totalVols))
			  .append(" %, depart ponctuel =").append(df.format(totalOKDepart * 100.0 / totalVols))
			  .append(" %, arrivee ponctuel =").append(df.format(totalOKArrive * 100.0 / totalVols));
			  
			context.write(NullWritable.get(), new Text(sb.toString()));
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		ToolRunner.run(new SelectAgregationDistanceAdvancedMRJob(), args);

	}
	

	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectAgregationDistanceAdvancedMRJob.class);
		// sortie fichier
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		//sortie mapper -> reducteur
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		// choix du mapper et reducer
		job.setMapperClass(VolMapper.class);
		job.setReducerClass(VolReducer.class);
		
		// pas de reducteur
		job.setNumReduceTasks(2);
		
		// parsage des arguments en ligne de commande
		String[] arguments = new GenericOptionsParser(getConf(), args).getRemainingArgs();
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path(arguments[0]));
	    FileOutputFormat.setOutputPath(job, new Path(arguments[1]));
		
	    boolean status = job.waitForCompletion(true);
	     
        if (status) {
        	return 0;
        }
        else {
        	return 1;
        }
	     
	}


}
