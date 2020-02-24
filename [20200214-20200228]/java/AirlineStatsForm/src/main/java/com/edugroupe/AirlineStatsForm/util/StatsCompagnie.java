package com.edugroupe.AirlineStatsForm.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class StatsCompagnie implements Writable
{
	public Text compagnie = new Text();
	public LongWritable volsTotals = new LongWritable();
	public LongWritable volsOkTotals = new LongWritable();
	public DoubleWritable distanceMoyenne = new DoubleWritable();
	public DoubleWritable retardDepartMoyen = new DoubleWritable();
	public DoubleWritable retardArriveeMoyen = new DoubleWritable();
	
	public StatsCompagnie() {}
	public StatsCompagnie(String compagnie,
						  long volsTotals,
						  long volsOkTotals,
						  double distanceMoyenne,
						  double retardDepartMoyen,
						  double retardArriveeMoyen) {
		this.compagnie.set(compagnie);
		this.volsTotals.set(volsTotals);
		this.volsOkTotals.set(volsOkTotals);
		this.distanceMoyenne.set(distanceMoyenne);
		this.retardDepartMoyen.set(retardDepartMoyen);
		this.retardArriveeMoyen.set(retardArriveeMoyen);
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.compagnie.readFields(in);
		this.volsTotals.readFields(in);
		this.volsOkTotals.readFields(in);
		this.distanceMoyenne.readFields(in);
		this.retardDepartMoyen.readFields(in);
		this.retardArriveeMoyen.readFields(in);
		
	}
	@Override
	public void write(DataOutput out) throws IOException {
		this.compagnie.write(out);
		this.volsTotals.write(out);
		this.volsOkTotals.write(out);
		this.distanceMoyenne.write(out);
		this.retardDepartMoyen.write(out);
		this.retardArriveeMoyen.write(out);
		
		
	}
	
	
}
