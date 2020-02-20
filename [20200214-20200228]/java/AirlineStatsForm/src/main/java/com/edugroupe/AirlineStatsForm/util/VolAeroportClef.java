package com.edugroupe.AirlineStatsForm.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class VolAeroportClef implements WritableComparable<VolAeroportClef> 
{
	
	// les "types" possible de cette clef
	public static final int TYPE_VOL = 1;
	public static final int TYPE_AEROPORT = 0;
		
	// contenu clef
	public IntWritable type_clef = new IntWritable(); 
	public Text code_aeroport = new Text();
	
	
		
	public VolAeroportClef() {}
	public VolAeroportClef(int type_clef, String code_aeroport) {
		this.type_clef.set(type_clef);
		this.code_aeroport.set(code_aeroport);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.type_clef.readFields(in);
		this.code_aeroport.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.type_clef.write(out);
		this.code_aeroport.write(out);
	}

	@Override
	public int compareTo(VolAeroportClef other) {
		int comparaison = this.code_aeroport.compareTo(other.code_aeroport);
		if (comparaison != 0) return comparaison;
		
		return this.type_clef.compareTo(other.type_clef);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code_aeroport == null) ? 0 : code_aeroport.hashCode());
		result = prime * result + ((type_clef == null) ? 0 : type_clef.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VolAeroportClef other = (VolAeroportClef) obj;
		if (code_aeroport == null) {
			if (other.code_aeroport != null)
				return false;
		} else if (!code_aeroport.equals(other.code_aeroport))
			return false;
		if (type_clef == null) {
			if (other.type_clef != null)
				return false;
		} else if (!type_clef.equals(other.type_clef))
			return false;
		return true;
	}

	
}
