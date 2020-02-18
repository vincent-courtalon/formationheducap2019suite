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

public class SelectWhereMRjob  extends Configured implements Tool{

	public static class AirlineMapper extends Mapper<LongWritable, Text, NullWritable, Text > {

		private int delayInMinute = 0;
		private int distanceMin = 0;
		
		/*
		 * 
		 * via la ligne de commande, on va pouvoir renseigner ces attributs
		 * 
		 * hadoop jar fichier.jar classemain -D map.where.delay=45 input output
		 * 
		 * 
		 */
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context)
				throws IOException, InterruptedException {
			
			if (!AirlineDataUtil.isHeader(value)) {
				// je récupere les colonnes qui m'intéresse
				String[] values = AirlineDataUtil.getSelectedColumnsA(value);
				// je veux récuperer uniquement les vols qui on plus de retard que le parametre delayInMinute
				if (AirlineDataUtil.parseMinutes(values[8], 0) > this.delayInMinute) {
					// c'est un vol qui m'intéresse
					// passer toutes les colonnes ensemble à la suite
					StringBuilder sb = AirlineDataUtil.mergeStringArray(values, ",");
					context.write(NullWritable.get(), new Text(sb.toString()));
				}
			}
			
		}

		@Override
		protected void setup(Mapper<LongWritable, Text,  NullWritable, Text>.Context context)
				throws IOException, InterruptedException {
			// a l'execution, grace au generic option parser
			// on peut récuperer le parametre de la ligne de commande
			// récupérer ce parametre, ou la valeur 1 si on le trouve pas
			this.delayInMinute = context.getConfiguration().getInt("map.where.delay", 1);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		ToolRunner.run(new SelectWhereMRjob(), args);

	}


	@Override
	public int run(String[] args) throws Exception {
		// initialisation de notre job 
		Job job = Job.getInstance(getConf());
		
		job.setJarByClass(SelectWhereMRjob.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(AirlineMapper.class);
		
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
