package object;

import java.util.Random;
import java.util.Vector;

import managers.ObjectManager;
import tools.ConfigReader;


public class Individu  {
	private Poule poule1;
	private Poule poule2;
	private Double TempsTotal;
	private Double DistanceTotale;
	private Double EcartNiveau;
	private Double Rang;
	private boolean estDomine;

	public Individu() {
		this.setPoule1(new Poule());
		this.setPoule2(new Poule());
		this.Rang = 0.0;
	}

	public Individu(Poule poule1, Poule poule2) {
		this.setPoule1(poule1);
		this.setPoule2(poule2);
		this.Rang = 0.0;
	}

	public Individu(Poule poule1, Poule poule2, Double TT, Double DT, Double EN) {
		this.setPoule1(poule1);
		this.setPoule2(poule2);
		this.TempsTotal = TT;
		this.DistanceTotale = DT;
		this.EcartNiveau = EN;
		this.Rang = 0.0;
	}
	/**
	 * @return the poule1
	 */
	public Double getRang() {
		return this.Rang;
	}
	/**
	 * @return the poule1
	 */
	public void setRang(double rang) {
		this.Rang = rang;
	}	
	public void setDomine(boolean bou) {
		this.estDomine = bou;
	}	
	public boolean getDomine()
	{
		return this.estDomine;
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


	public void initCalculs() {

		this.poule1.initPouleCalcul();
		this.poule2.initPouleCalcul();

		/*	@Deprecated
		 * Ancienne méthode de calcul du niveau
		 * 	
		 * 
		 * this.EcartNiveau = Math.abs(this.poule1.getNiveau() - this.poule2.getNiveau());

		 */
		// TODO : Tri des equipe par niveau pour comparer 1er avec 1er etc
		this.EcartNiveau = 0.0;
		for (int i=0; i<Integer.parseInt(ConfigReader.getConfig().get("nbEquipeParPoule")); i++)
		{
			this.EcartNiveau += Math.abs(this.poule1.getMesEquipes().get(i).getNiveau() - this.poule2.getMesEquipes().get(i).getNiveau());

		}

		this.DistanceTotale = this.poule1.getDistanceTotale() + this.poule2.getDistanceTotale();
		this.TempsTotal = this.poule1.getTempsTotal() + this.poule2.getTempsTotal();
	}
	/**
	 * 
	 * On inverse deux equipes aléatoires des poules pour faire muter l'individu
	 * 
	 */

	public void croisement(){

		Vector<Equipe> temp1 = new Vector<Equipe>();
		Vector<Equipe> temp2 = new Vector<Equipe>();
		int i, j;
		temp1 = this.poule1.getMesEquipes();
		temp2 = this.poule2.getMesEquipes();

		this.initCalculs();	

		// TODO : 4 = moitié pour tournoi complexe
		for (i=0; i<4; i++)
		{

			temp2.add(temp1.get(i));
			//TODO : 9 = taille poule
			temp1.add(temp2.get(9-i));
			temp2.remove(9-1-i);
			temp1.remove(i);


		}
		// Retirer les doublons TODO : taille equipe
		for(i = 0;i<9;i++){
			for(j = 0;j<9;j++){
				if(temp1.get(i).equals(temp1.get(j))) {
					temp1.remove(j);
				}
			}
		}

		for(i = 0;i<9;i++){
			for(j = 0;j<9;j++){
				if(temp2.get(i).equals(temp2.get(j))) {
					temp2.remove(j);
				}
			}
		}

		// On parcours les equipes des 2 poules et on ajoute celles qui manquent
		boolean estDedans = false;
		for(i = 0;i<9;i++){
			estDedans = false;
			for (j=0;j<9;j++)
			{
				if (temp1.get(j).equals(ObjectManager.getMesEquipes().get(i) ))
				{
					estDedans = true;
					break;
				}
				if (temp2.get(j).equals(ObjectManager.getMesEquipes().get(i) ))
				{
					estDedans = true;
					break;
				}
			}

			if (!estDedans)
			{
				if (temp1.size() <= 9)
				{
					temp1.add(ObjectManager.getMesEquipes().get(i));
				}
				else if (temp2.size() <= 9)
				{
					temp2.add (ObjectManager.getMesEquipes().get(i));
				}
				else
				{
					System.out.println("Erreur Individu.java croisement echoue !!");
					System.exit(1);
				}
			}			

		}
		// Si l'equipe n'est pas dedans on l'ajoute
	}

	public void mutation(int nombreMaxDeMutations){
		Vector<Equipe> temp1 = new Vector<Equipe>();
		Vector<Equipe> temp2 = new Vector<Equipe>();
		int nombreIterations = 0;

		temp1 = this.poule1.getMesEquipes();
		temp2 = this.poule2.getMesEquipes();

		this.initCalculs();	

		Random rand = new Random();
		int mutationAleatoire = rand.nextInt(nombreMaxDeMutations+1);

		while (nombreIterations < mutationAleatoire) {


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
		}
	}

	@Override
	public String toString() {
		this.initCalculs();
		String res = "";
		res = "Distance Totale : " + this.DistanceTotale + "km\n Temps total : " + this.TempsTotal + " heures\n Ecart de niveau : " + this.EcartNiveau;
		return res;
	}


	public String toString(Boolean AfficherEquipes) {
		this.initCalculs();
		String res = "";
		//res = "Individu [";
		//res += "Poule 1 : " + poule1.toString() + "\n";
		//res += "Poule 2 : " + poule2.toString() + "\n";
		//res += "======================\nTemps total individu : " + this.TempsTotal + "\n";
		//res += "Distance totale individu : " + this.DistanceTotale +  "\n";
		//res += "Ecart de niveau de l'individu : " + this.EcartNiveau + "\n======================";

		res = "Distance Totale : " + this.DistanceTotale + "km\n Temps total : " + this.TempsTotal + " heures\n Ecart de niveau : " + this.EcartNiveau;

		if (AfficherEquipes)
		{
			res += "\n=================Poule 1 :============\n" 

				+ this.poule1.toString() + "\n==========Poule 2 :===========\n" + this.poule2.toString() + "\n\n";
		}
		return res;
	}
	public Double getTempsTotal() {
		return TempsTotal;
	}

	public void setTempsTotal(Double tempsTotal) {
		TempsTotal = tempsTotal;
	}

	public Double getDistanceTotale() {
		return DistanceTotale;
	}

	public void setDistanceTotale(Double distanceTotale) {
		DistanceTotale = distanceTotale;
	}

	public Double getEcartNiveau() {
		return EcartNiveau;
	}

	public void setEcartNiveau(Double ecartNiveau) {
		EcartNiveau = ecartNiveau;
	}


	public void afficherTri()
	{
		System.out.println(this.getRang() + " DT : " + this.DistanceTotale + " TT : " + this.TempsTotal + " EN : " + this.EcartNiveau);
	}




}
