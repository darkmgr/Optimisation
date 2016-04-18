package object;

import java.util.Random;
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

		this.EcartNiveau = this.poule1.getNiveau() - this.poule2.getNiveau();
		if (this.EcartNiveau <0)
		{
			this.EcartNiveau = -this.EcartNiveau;
		}

		this.DistanceTotale = this.poule1.getDistanceTotale() + this.poule2.getDistanceTotale();
		this.TempsTotal = this.poule1.getTempsTotal() + this.poule2.getTempsTotal();
	}
	/**
	 * 
	 * On inverse les deux premieres equipes des poules pour faire muter l'individu
	 * 
	 */

	public void mutation(int nombreDeMutationsMax){
		Vector<Equipe> temp1;
		Vector<Equipe> temp2;
		boolean mutationInefficace = true;
		int nombreIterations = 0;




		temp1 = this.poule1.getMesEquipes();
		temp2 = this.poule2.getMesEquipes();

		this.initCalculs();

		double tempsTotalAvantMutation = this.TempsTotal;
		double distanceTotaleAvantMutation = this.DistanceTotale;
		double ecartNiveauAvantMutation = this.EcartNiveau;			

		while (mutationInefficace && nombreIterations < nombreDeMutationsMax)
		{
			Random rand = new Random();

			/* On ajoute une equipe de la poule 1 dans la poule 2 au hasard */
			int nombreAleatoire = rand.nextInt(temp1.size());		
			temp2.add(temp1.get(nombreAleatoire));
			temp1.remove(nombreAleatoire);

			/* On ajoute une equipe de la poule 2 dans la poule 1 au hasard */
			nombreAleatoire = rand.nextInt(temp2.size());		
			temp1.add(temp2.get(nombreAleatoire));
			temp2.remove(nombreAleatoire);



			this.poule1.setMesEquipes(temp1);
			this.poule2.setMesEquipes(temp2);

			this.initCalculs();	
			nombreIterations++;
			/* TODO : a changer par une fonction de comparaison propre*/
			if (this.TempsTotal < tempsTotalAvantMutation && this.DistanceTotale < distanceTotaleAvantMutation && this.EcartNiveau < ecartNiveauAvantMutation)
			{
				mutationInefficace = false;
				System.out.println("====Mutation réussie====\nAprès " + nombreIterations + " mutations aléatoires ! \n");
			}
		}
		
		if (mutationInefficace = true)
		{
			System.out.println("====Mutation echouée====\n l'individu est moins efficace qu'avant\n");
		}


	}

	@Override
	public String toString() {
		this.initCalculs();
		String res = "";
		res = "Individu [";
		res += "Poule 1 : " + poule1.toString() + "\n";
		res += "Poule 2 : " + poule2.toString() + "\n";
		res += "======================\nTemps total individu : " + this.TempsTotal + "\n";
		res += "Distance totale individu : " + this.DistanceTotale +  "\n";
		res += "Ecart de niveau de l'individu : " + this.EcartNiveau + "\n=========================";
		return res;
	}	
}
