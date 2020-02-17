package com.edugroupe.wordcountexo;

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
public class WordCountExo 
{

	/*public static class WordMapper extends MapReduceBase
								  implements Mapper<LongWritable, Text, Text, IntWritable> {
		// "les temps, comme les oeufs, sont durs " ----> ("les", 1) (temps,1), (comme,1) ....
		@Override
		public void map(LongWritable noLigne, Text ligne, OutputCollector<Text, IntWritable> output, Reporter reporter)
				throws IOException {
			if (ligne != null) {
				String[] mots = ligne.toString().split("[-;?.,!§&$' ()]+|\"+");
				for (String mot : mots ) {
					mot = mot.toLowerCase().trim();
					if (mot.length() > 0)
						output.collect(new Text(mot), new IntWritable(1));
				}
			}
		}
	}*/
	public static class WordMapper extends MapReduceBase
	implements Mapper<LongWritable, Text, IntWritable, IntWritable> {
		// "les temps, comme les oeufs, sont durs " ----> (3, 1) (5,1), (5,1) ....
		@Override
		public void map(LongWritable noLigne, Text ligne, OutputCollector<IntWritable, IntWritable> output, Reporter reporter)
				throws IOException {
			if (ligne != null) {
				String[] mots = ligne.toString().split("[-;?.,!§&$' ()]+|\"+");
				for (String mot : mots ) {
					mot = mot.toLowerCase().trim();
					if (mot.length() > 0)
						output.collect(new IntWritable(mot.length()), new IntWritable(1));
				}
			}
		}
	}

	

	public static class WordReducer extends MapReduceBase
	implements Reducer<IntWritable, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(IntWritable longueurmot, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
				Reporter reporter) throws IOException {
			int sum = 0;
			while(values.hasNext()) {
				sum += values.next().get();
			}
			output.collect(new Text("longueur " + longueurmot.get()+ " :"), new IntWritable(sum));
		}
	}

/*
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
*/


	public static void main( String[] args ) throws IOException
	{
		System.out.println( "Démarrage traitement..." );
		JobConf conf = new JobConf(WordCountExo.class);
		conf.setJobName("wordcount");

		// configure le format des données en sortie : mot, decompte pour ce mot
		// cela doit correspondre à la sortie du reducteur 
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		//----------- pour exemple avec longeur mot uniquement
		conf.setMapOutputKeyClass(IntWritable.class);
		conf.setMapOutputValueClass(IntWritable.class);
		//------------------------------------------------------
		

		// configurer mapper, combiner, reducteur
		conf.setMapperClass(WordMapper.class);
		//conf.setCombinerClass(WordReducer.class);
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
