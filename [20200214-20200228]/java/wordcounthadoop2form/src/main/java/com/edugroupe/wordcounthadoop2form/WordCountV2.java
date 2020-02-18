package com.edugroupe.wordcounthadoop2form;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Hello world!
 *
 */
public class WordCountV2 
{
	// en api hadoop v2, on hérite directement d'une classe Mapper
	public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		@Override
		protected void map(LongWritable key,
							Text ligne,
							Context context)
				throws IOException, InterruptedException {
			if (ligne != null) {
				String[] mots = ligne.toString().split("[-;?.,!§&$' ()]+|\"+");
				for(String mot : mots) {
					if (mot.trim().length() > 0) {
						// equivalent au output.collect de hadoop v1
						context.write(new Text(mot.trim()), new IntWritable(1));
					}
				}
			}
		}
	}
	
	public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		protected void reduce(Text mot, 
							  Iterable<IntWritable> values,
							  Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			context.write(mot, new IntWritable(sum));
		}
	}
	
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException
    {
        System.out.println( "lancement du word count" );
        // nouveau job, version hadoop v2.x
        Job job = Job.getInstance(new Configuration());
        
        job.setJarByClass(WordCountV2.class);
        
        // configurer les type de sortie
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // pas besoin de définir les type entree, il le déduit du Mapper
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        
        // attention, definition des format de fichier en entree en sortie
        // c'est une classe avec le même nom qu'en v1, mais pas le même package!!!
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // attention, idem avec au dessus
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        
        // démarrage du job
        boolean status = job.waitForCompletion(true);
        if (status) {
        	System.out.println("job complete!!");
        	System.exit(0);
        }
        else {
        	System.out.println("job failure!!");
        	System.exit(1);
        }
        
        
    }
}
