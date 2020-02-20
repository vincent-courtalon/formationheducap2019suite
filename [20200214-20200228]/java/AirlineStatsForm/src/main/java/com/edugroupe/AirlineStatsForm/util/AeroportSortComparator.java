package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.WritableComparator;

public class AeroportSortComparator extends WritableComparator {

	public AeroportSortComparator() {
		super(VolAeroportClef.class, true);
	}

}
