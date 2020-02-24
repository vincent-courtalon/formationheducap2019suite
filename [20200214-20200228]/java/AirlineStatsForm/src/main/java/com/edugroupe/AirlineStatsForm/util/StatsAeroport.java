package com.edugroupe.AirlineStatsForm.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class StatsAeroport implements Writable{
	
	public Text aeroportDepart = new Text();
	public LongWritable totalVols = new LongWritable();
	public DoubleWritable pourcentageAnnules = new DoubleWritable();
	public IntWritable retardDepartMoyen = new IntWritable();
	public IntWritable retardArriveeMoyen = new IntWritable();
	
	public StatsAeroport() {}
	public StatsAeroport(String aeroportDepart,
						 long totalVols,
						 double pourcentageAnnules,
						 int retardDepartMoyen,
						 int retardArriveeMoyen) {
		this.aeroportDepart.set(aeroportDepart);
		this.totalVols.set(totalVols);
		this.pourcentageAnnules.set(pourcentageAnnules);
		this.retardDepartMoyen.set(retardDepartMoyen);
		this.retardArriveeMoyen.set(retardArriveeMoyen);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.aeroportDepart.readFields(in);
		this.totalVols.readFields(in);
		this.pourcentageAnnules.readFields(in);
		this.retardDepartMoyen.readFields(in);
		this.retardArriveeMoyen.readFields(in);
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		this.aeroportDepart.write(out);
		this.totalVols.write(out);
		this.pourcentageAnnules.write(out);
		this.retardDepartMoyen.write(out);
		this.retardArriveeMoyen.write(out);
	}
	
}
