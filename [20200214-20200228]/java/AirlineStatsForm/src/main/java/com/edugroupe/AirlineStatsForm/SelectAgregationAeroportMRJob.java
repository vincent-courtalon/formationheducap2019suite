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


public class SelectAgregationAeroportMRJob extends Configured implements Tool {


	
	public static class VolMapper extends Mapper<LongWritable, Text, Text, BooleanWritable> {

		private int distanceMin = 0;
		
		@Override
		protected void map(LongWritable key,
							Text value,
							Context context)
				throws IOException, InterruptedException {
			if (!AirlineDataUtil.isHeader(value)) {
				String[] values = AirlineDataUtil.getSelectedColumnsB(value);
				int  arrivalDelay = AirlineDataUtil.parseMinutes(values[9], 0);
				boolean isCancelled = AirlineDataUtil.parseBoolean(values[10], false);
				boolean isDiverted = AirlineDataUtil.parseBoolean(values[11], false);
				int distance = AirlineDataUtil.parseMinutes(values[5], 0);
				String origin = values[3];
				boolean nonPonctuel = arrivalDelay > 15 || isCancelled || isDiverted;
				if (distance >= this.distanceMin)
					context.write(new Text(origin), new BooleanWritable(nonPonctuel));
			}
		}

		@Override
		protected void setup(Mapper<LongWritable, Text, Text, BooleanWritable>.Context context)
				throws IOException, InterruptedException {
			this.distanceMin = context.getConfiguration().getInt("map.where.distance", 0);
		}
		
		
	}
	
	public static class VolReducer extends Reducer<Text, BooleanWritable, NullWritable, Text> {

		@Override
		protected void reduce(Text origin, Iterable<BooleanWritable> nonPonctuels,
							Context context)
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
			
			StringBuilder sb = new StringBuilder("aeroport ").append(origin.toString());
			double pourcentagePonctuel = (volsPonctuels * 100.0) / totalVols;
			double pourcentageNonPonctuel = (volsNonPonctuels * 100.0) / totalVols;
			
			sb.append("total vols=").append(totalVols)
			  .append(" , non ponctuels= ").append(pourcentageNonPonctuel).append(" % ")
			  .append(", ponctuels= ").append(pourcentagePonctuel).append(" % ");
			
			context.write(NullWritable.get(), new Text(sb.toString()));
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		ToolRunner.run(new SelectAgregationAeroportMRJob(), args);

	}
	

	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectAgregationAeroportMRJob.class);
		// sortie fichier
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		//sortie mapper -> reducteur
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BooleanWritable.class);
		
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
