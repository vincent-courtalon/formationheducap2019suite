package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CompagnieCodePartitioner extends Partitioner<VolCompagnieClef, Text> {

	@Override
	public int getPartition(VolCompagnieClef clef, Text valeur, int nb_partitions) {
	
		// hadoop appelera la methode getPartition de notre partioneur pour décider
		// vers quel rédcuteur envoyer la donnée
		return (clef.code_compagnie.hashCode() & Integer.MAX_VALUE) % nb_partitions;
	}
	
	

}
