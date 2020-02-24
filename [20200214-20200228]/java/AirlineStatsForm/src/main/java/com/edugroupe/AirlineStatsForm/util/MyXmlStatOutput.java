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


public class MyXmlStatOutput extends FileOutputFormat<NullWritable, StatsCompagnie>
{
	
	public static class MyXmlStatRecordWriter extends RecordWriter<NullWritable, StatsCompagnie> {

		// le flux de sortie (le fichier dans lequel on va ecrire, dans hdfs)
		private DataOutputStream out;
		
		
		public MyXmlStatRecordWriter(DataOutputStream out) throws IOException {
			// a la construction de mon writer
			// je recois le flux ou ecrire et je commence
			// par ouvrir un tableau xml
			this.out = out;
			// ouverture du tableau xml dans le fichier
			this.out.writeBytes("<data>\n");
		}
		
		@Override
		public void close(TaskAttemptContext arg0) throws IOException, InterruptedException {
			// je ferme le tableau xml
			// je ferme le flux du fichier dans hdfs
			out.writeBytes("</data>\n");
			out.close();
		}

		@Override
		public void write(NullWritable clef, StatsCompagnie value) throws IOException, InterruptedException {

			out.writeBytes("\t<stat compagnie=\""+  value.compagnie.toString().replaceAll("\"", "") + "\">\n");
			out.writeBytes("\t\t<volTotals>"+ value.volsTotals.toString() + "</volTotals>\n");
			out.writeBytes("\t\t<volsOkTotals>"+ value.volsOkTotals.toString() + "</volsOkTotals>\n");
			out.writeBytes("\t\t<distanceMoyenne>"+ value.distanceMoyenne.toString() + "</distanceMoyenne>\n");
			out.writeBytes("\t\t<retardDepartMoyen>"+ value.retardDepartMoyen.toString() + "</retardDepartMoyen>\n");
			out.writeBytes("\t\t<retardArriveeMoyen>"+ value.retardArriveeMoyen.toString() + "</retardArriveeMoyen>\n");
			out.writeBytes("\t</stat>");
		}
		
	}

	@Override
	public RecordWriter<NullWritable, StatsCompagnie> getRecordWriter(TaskAttemptContext job)
			throws IOException, InterruptedException {
		// le parametre de type TaskAttemptContext contient toutes les infos
		// sur le job et les méthodes utilitaires associées
		String extension = ".xml";
		
		// je veux récupérer le chemin du fichier dans lequel je vais ecrire ma sortie (dans HDFS)
		// ce nom de fichier dépend du job et du reducteur
		// exemple: part-r0001.xml
		Path fichier = getDefaultWorkFile(job, extension);
		// ensuite, je récupere un objet FileSystem, qui permet de "manipuler" le systeme de fichier HDFS
		FileSystem fs = fichier.getFileSystem(job.getConfiguration());
		
		// j'ouvre un flux vers ce fichier hdfs
		FSDataOutputStream out = fs.create(fichier, false);

		// je renvoie mon recordWriter correctement configuré avec le flux de sortie vers la bon fichier
		// dans hdfs
		return new MyXmlStatRecordWriter(out);
	}

}
