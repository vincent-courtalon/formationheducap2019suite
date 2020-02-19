package com.edugroupe.AirlineStatsForm.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class VolCompagnieClef  implements WritableComparable<VolCompagnieClef>{

	// les "types" possible de cette clef
	public static final int TYPE_VOL = 1;
	public static final int TYPE_COMPAGNIE = 0;
	
	// contenu clef
	public IntWritable type_clef = new IntWritable(); // TYPE_VOL ou TYPE_COMPAGNIE
	public Text code_compagnie = new Text();
	
	//
	//( type_clef = 0, code_compagnie="PS")   ------> "nom de la compagnie"
	//( type_clef = 1, code_compagnie="PS")   -------> "vols de la compagnie
	
	public VolCompagnieClef() {}
	
	public VolCompagnieClef(int type_clef, String code_compagnie) {
		this.type_clef.set(type_clef);
		this.code_compagnie.set(code_compagnie);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.type_clef.readFields(in);
		this.code_compagnie.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.type_clef.write(out);
		this.code_compagnie.write(out);
	}

	@Override
	public int compareTo(VolCompagnieClef o) {
		// tri par code compagnie (alphabetique)
		// si jamais deux clef concerne la même compagnie
		// envoyer d'abord le nom et ensuite les vols
		int comparaison = this.code_compagnie.compareTo(o.code_compagnie);
		if (comparaison != 0) return comparaison;
		// les code sont les même, trier par type_clef
		return this.type_clef.compareTo(o.type_clef);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code_compagnie == null) ? 0 : code_compagnie.hashCode());
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
		VolCompagnieClef other = (VolCompagnieClef) obj;
		if (code_compagnie == null) {
			if (other.code_compagnie != null)
				return false;
		} else if (!code_compagnie.equals(other.code_compagnie))
			return false;
		if (type_clef == null) {
			if (other.type_clef != null)
				return false;
		} else if (!type_clef.equals(other.type_clef))
			return false;
		return true;
	}
	
	
	
}
