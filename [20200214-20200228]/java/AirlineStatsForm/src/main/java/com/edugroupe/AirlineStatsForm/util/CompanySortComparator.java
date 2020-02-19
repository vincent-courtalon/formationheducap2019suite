package com.edugroupe.AirlineStatsForm.util;

import org.apache.hadoop.io.WritableComparator;

public class CompanySortComparator extends WritableComparator {

	public CompanySortComparator() {
		super(VolCompagnieClef.class, true);
	}

	
}
