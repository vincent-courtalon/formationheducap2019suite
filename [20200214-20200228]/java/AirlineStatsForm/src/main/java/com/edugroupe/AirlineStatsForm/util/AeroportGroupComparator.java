package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AeroportGroupComparator extends WritableComparator {

	public AeroportGroupComparator() {
		super(VolAeroportClef.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		VolAeroportClef clefA = (VolAeroportClef)a;
		VolAeroportClef clefB = (VolAeroportClef)b;
		return clefA.code_aeroport.compareTo(clefB.code_aeroport);
	}
	
	

	
}
