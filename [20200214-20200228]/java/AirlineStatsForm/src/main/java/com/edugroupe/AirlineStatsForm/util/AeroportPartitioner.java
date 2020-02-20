package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AeroportPartitioner extends Partitioner<VolAeroportClef, Text> {

	@Override
	public int getPartition(VolAeroportClef clef, Text valeur, int nb_partitions) {
		return (clef.code_aeroport.hashCode() & Integer.MAX_VALUE) % nb_partitions;
	}

	
}
