package com.edugroupe.wordcountform;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

/**
 * Hello world!
 *
 */
public class WordCountHadoop 
{
	
	public static class WordMapper extends MapReduceBase
								  implements Mapper<LongWritable, Text, Text, IntWritable> {
		// " java  " ----> ("java", 1)
		@Override
		public void map(LongWritable noLigne, Text mot, OutputCollector<Text, IntWritable> output, Reporter reporter)
				throws IOException {
			if (mot != null) {
				String m = mot.toString();
				if (m.trim().length() > 0) {
					output.collect(new Text(m.trim()), new IntWritable(1));
				}
			}
		}
	}
	// (python, 1, 1, 1, 1) ----> (python, 4) 
	public static class WordReducer extends MapReduceBase
									implements Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text mot, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
				Reporter reporter) throws IOException {
			int sum = 0;
			while(values.hasNext()) {
				sum += values.next().get();
			}
			output.collect(mot, new IntWritable(sum));
		}
	}
	
	
	
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Démarrage traitement..." );
        JobConf conf = new JobConf(WordCountHadoop.class);
        conf.setJobName("wordcount");
        
        // configure le format des données en sortie : mot, decompte pour ce mot
        // cela doit correspondre à la sortie du reducteur 
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
        // configurer mapper, combiner, reducteur
        conf.setMapperClass(WordMapper.class);
        conf.setCombinerClass(WordReducer.class);
        conf.setReducerClass(WordReducer.class);
        
        // combien de reducteur ?
        conf.setNumReduceTasks(1);
        
        // définir le format de fichier en entrée et en sortie
        conf.setInputFormat(TextInputFormat.class); // chaque ligne du fichier texte -> noligne, contenu ligne
        conf.setOutputFormat(TextOutputFormat.class); // clef,valeur  ---> clef tab valeur
        
        // chemin avec les fichier a traiter
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        // chemin ou ecrire le résultat
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        
        // lancer le traitement
        JobClient.runJob(conf);
        
    }
}
