package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CompanyGroupComparator extends WritableComparator {

	public CompanyGroupComparator() {
		super(VolCompagnieClef.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		VolCompagnieClef clefA = (VolCompagnieClef)a;
		VolCompagnieClef clefB = (VolCompagnieClef)b;
		
		// je ne compare que le code compagnie, et surtout pas le type (vol ou nom)
		// comme ca hadoop regroupera les deux ensemble
		return clefA.code_compagnie.compareTo(clefB.code_compagnie);
	}
	
	
}
