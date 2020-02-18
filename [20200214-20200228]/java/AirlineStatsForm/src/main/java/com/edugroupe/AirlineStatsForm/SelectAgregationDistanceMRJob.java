package com.edugroupe.AirlineStatsForm;

import java.io.IOException;

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


public class SelectAgregationDistanceMRJob extends Configured implements Tool {

	public static final IntWritable DISTANCE_0_100 = new IntWritable(0);
	public static final IntWritable DISTANCE_100_200 = new IntWritable(1);
	public static final IntWritable DISTANCE_200_400 = new IntWritable(2);
	public static final IntWritable DISTANCE_400_800 = new IntWritable(3);
	public static final IntWritable DISTANCE_800_MARS = new IntWritable(4);
	
	
	public static class VolMapper extends Mapper<LongWritable, Text, IntWritable, BooleanWritable> {

		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, IntWritable, BooleanWritable>.Context context)
				throws IOException, InterruptedException {
			if (!AirlineDataUtil.isHeader(value)) {
				String[] values = AirlineDataUtil.getSelectedColumnsB(value);
				int  arrivalDelay = AirlineDataUtil.parseMinutes(values[9], 0);
				boolean isCancelled = AirlineDataUtil.parseBoolean(values[10], false);
				boolean isDiverted = AirlineDataUtil.parseBoolean(values[11], false);
				int distance = AirlineDataUtil.parseMinutes(values[5], 0);
				
				boolean nonPonctuel = arrivalDelay > 15 || isCancelled || isDiverted;
				if (distance < 100)
					context.write(DISTANCE_0_100, new BooleanWritable(nonPonctuel));
				else if (distance < 200)
					context.write(DISTANCE_100_200, new BooleanWritable(nonPonctuel));
				else if (distance < 400)
					context.write(DISTANCE_200_400, new BooleanWritable(nonPonctuel));
				else if (distance < 800)
					context.write(DISTANCE_400_800, new BooleanWritable(nonPonctuel));
				else
					context.write(DISTANCE_800_MARS, new BooleanWritable(nonPonctuel));
			}
		}
	}
	
	public static class VolReducer extends Reducer<IntWritable, BooleanWritable, NullWritable, Text> {

		@Override
		protected void reduce(IntWritable distance_class, Iterable<BooleanWritable> nonPonctuels,
				Reducer<IntWritable, BooleanWritable, NullWritable, Text>.Context context)
				throws IOException, InterruptedException {
			
			int totalVols = 0;
			int volsNonPonctuels = 0;
			int volsPonctuels = 0;
			for (BooleanWritable nonPonctuel : nonPonctuels) {
				totalVols++;
				if (nonPonctuel.get())
					volsNonPonctuels++;
				else
					volsPonctuels++;
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
			
			sb.append("total vols=").append(totalVols)
			  .append(", non ponctuels=").append(volsNonPonctuels)
			  .append(", ponctuels=").append(volsPonctuels);
			
			context.write(NullWritable.get(), new Text(sb.toString()));
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		ToolRunner.run(new SelectAgregationDistanceMRJob(), args);

	}
	

	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectAgregationDistanceMRJob.class);
		// sortie fichier
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		//sortie mapper -> reducteur
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(BooleanWritable.class);
		
		// choix du mapper et reducer
		job.setMapperClass(VolMapper.class);
		job.setReducerClass(VolReducer.class);
		
		// pas de reducteur
		job.setNumReduceTasks(1);
		
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
