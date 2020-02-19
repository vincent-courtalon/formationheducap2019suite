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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.edugroupe.AirlineStatsForm.util.AirlineDataUtil;
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
		
		//job.setMapperClass(AirlineMapper.class);
		
		// pas de reducteur
		job.setNumReduceTasks(0);
		
		// parsage des arguments en ligne de commande
		String[] arguments = new GenericOptionsParser(getConf(), args).getRemainingArgs();
		
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
