package object;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("serial")
public class SortableVectorIndividu extends Vector<Individu> implements Comparator<Individu> {

	public SortableVectorIndividu() {
		
	}
	
	@Override
	public int compare(Individu i1, Individu i2) {
		
		int comparator = 0;
		//Si les deux individus compares ont les memes donnees calculees, ils sont egaux
		if(i1.getDistanceTotale() == i2.getDistanceTotale()
				&& i1.getEcartNiveau() == i2.getEcartNiveau()
				&& i1.getTempsTotal() == i2.getTempsTotal()) {
			comparator = 0;
		}  // Si l'individu 1 est superieur on "l'avance"
			else if (i1.getDistanceTotale() < i2.getDistanceTotale()
				&& i1.getEcartNiveau() < i2.getEcartNiveau()
				&& i1.getTempsTotal() < i2.getTempsTotal()) {
			comparator = -1;
		} // Si l'individu 1 est inferieur on le "recule"
			else {
			comparator = i1.getDistanceTotale().compareTo(i2.getDistanceTotale());
		}
		
		return comparator;
	}
	
	public void sortIndividu() {
		Collections.sort(this,this);
		this.display();
	}

	/*
	 * Simple m�thode d'affichage pour le test
	 */
	public void display(){
		System.out.println("===============================");
		System.out.println("Tri des individus :");
		int i = 0;
		for (Iterator<Individu> iterator = iterator(); iterator.hasNext();) {
			System.out.println("N�" + i + "  " + iterator.next());
			i++;
		}
		System.out.println("===============================");
	}
}