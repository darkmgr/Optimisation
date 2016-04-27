package object;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

@SuppressWarnings("serial")
public class SortableVectorEquipe extends Vector<Equipe> implements Comparator<Equipe> {

	public SortableVectorEquipe() {
		
	}
	
	@Override
	public int compare(Equipe i1, Equipe i2) {
		return Double.valueOf(i1.getNiveau()).compareTo(Double.valueOf(i2.getNiveau()));
	}


	public void sortEquipe() {
		Collections.sort(this,this);	
	}

}
