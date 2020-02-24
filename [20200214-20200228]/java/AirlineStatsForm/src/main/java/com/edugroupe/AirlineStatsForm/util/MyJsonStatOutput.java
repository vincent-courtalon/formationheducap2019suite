package com.edugroupe.AirlineStatsForm.util;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyJsonStatOutput extends FileOutputFormat<NullWritable, StatsAeroport> {

	public static class MyJsonStatRecordWriter extends RecordWriter<NullWritable, StatsAeroport> {

		// le flux de sortie (le fichier dans lequel on va ecrire, dans hdfs)
		private DataOutputStream out;
		// est on sur le premier record a ecrire ?
		private boolean isFirst;
		
		
		public MyJsonStatRecordWriter(DataOutputStream out) throws IOException {
			// a la construction de mon writer
			// je recois le flux ou ecrire et je commence
			// par ouvrir un tableau json
			this.out = out;
			this.isFirst = true;
			// ouverture du tableau json dans le fichier
			this.out.writeBytes("[");
		}
		
		@Override
		public void close(TaskAttemptContext arg0) throws IOException, InterruptedException {
			// je ferme le tableau json
			// je ferme le flux du fichier dans hdfs
			out.writeBytes("]");
			out.close();
		}

		@Override
		public void write(NullWritable clef, StatsAeroport value) throws IOException, InterruptedException {
			if (!isFirst) {
				out.writeBytes(",\n");
			}
			out.writeBytes("{\n");
			out.writeBytes("codeAeroport: \"" + value.aeroportDepart.toString().replaceAll("\"", "") + "\",\n");
			out.writeBytes("totalVols: " + value.totalVols.toString() + ",\n");
			out.writeBytes("pourcentageAnnules: " + value.pourcentageAnnules.toString() + ",\n");
			out.writeBytes("retardDepartMoyen: " + value.retardDepartMoyen.toString() + ",\n");
			out.writeBytes("retardArriveeMoyen: " + value.retardArriveeMoyen.toString() + "\n");
			out.writeBytes("}");
			this.isFirst = false;
		}
		
	}
	
	// cette fonction sera appellée par hadoop pour récupérer chaque exemplaire de writer
	// pour chaque réducteur du job
	@Override
	public RecordWriter<NullWritable, StatsAeroport> getRecordWriter(TaskAttemptContext job)
			throws IOException, InterruptedException {
		// le parametre de type TaskAttemptContext contient toutes les infos
		// sur le job et les méthodes utilitaires associées
		String extension = ".json";
		
		// je veux récupérer le chemin du fichier dans lequel je vais ecrire ma sortie (dans HDFS)
		// ce nom de fichier dépend du job et du reducteur
		// exemple: part-r0001.json
		Path fichier = getDefaultWorkFile(job, extension);
		// ensuite, je récupere un objet FileSystem, qui permet de "manipuler" le systeme de fichier HDFS
		FileSystem fs = fichier.getFileSystem(job.getConfiguration());
		
		// j'ouvre un flux vers ce fichier hdfs
		FSDataOutputStream out = fs.create(fichier, false);

		// je renvoie mon recordWriter correctement configuré avec le flux de sortie vers la bon fichier
		// dans hdfs
		return new MyJsonStatRecordWriter(out);
		
	}
	
	


}
