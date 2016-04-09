package object;

import java.util.Vector;

public class Individu {
	private Poule poule1;
	private Poule poule2;
	private Double TempsTotal;
	private Double DistanceTotale;
	private Double EcartNiveau;
	
	public Individu(Poule poule1, Poule poule2) {
		this.setPoule1(poule1);
		this.setPoule2(poule2);
	}
	
	/**
	 * @return the poule1
	 */
	public Poule getPoule1() {
		return poule1;
	}
	/**
	 * @param poule1 the poule1 to set
	 */
	public void setPoule1(Poule poule1) {
		this.poule1 = poule1;
	}
	/**
	 * @return the poule2
	 */
	public Poule getPoule2() {
		return poule2;
	}
	/**
	 * @param poule2 the poule2 to set
	 */
	public void setPoule2(Poule poule2) {
		this.poule2 = poule2;
	}
public void initCalculs()
{
	this.poule1.initPouleCalcul();
	this.poule2.initPouleCalcul();

	this.EcartNiveau = this.poule1.getNiveau() - this.poule2.getNiveau(); // TODO : valeur absolue ou test if a>b
	this.DistanceTotale = this.poule1.getDistanceTotale() + this.poule2.getDistanceTotale();
	this.TempsTotal = this.poule1.getTempsTotal() + this.poule2.getTempsTotal();
}
	/**
	 * 
	 * On inverse les deux premieres equipes des poules pour faire muter l'individu
	 * 
	 */
	
	public void mutation(){
		Vector<Equipe> temp1;
		Vector<Equipe> temp2;
		
		temp1 = this.poule1.getMesEquipes();
		temp2 = this.poule2.getMesEquipes();
		
		// On ajoute la premiere equipe a la fin du vecteur de l'autre poule
		temp1.add(temp2.firstElement());
		temp2.add(temp1.firstElement());
		
		//On supprime la premiere equipe
		temp1.remove(0);
		temp2.remove(0);
		
		this.poule1.setMesEquipes(temp1);
		this.poule2.setMesEquipes(temp2);
	}
	
	@Override
	public String toString() {
		String res = "";
		res = "Individu [";
		res += "Poule 1 : " + poule1.toString() + "\n";
		res += "Poule 2 : " + poule2.toString() + "\n";
		this.initCalculs();
		res += "Temps total individu : " + this.TempsTotal + "\n";
		res += "Distance totale individu : " + this.DistanceTotale +  "\n";
		res += "Ecart de niveau de l'individu : " + this.EcartNiveau + "\n";
		return res;
	}	
}
