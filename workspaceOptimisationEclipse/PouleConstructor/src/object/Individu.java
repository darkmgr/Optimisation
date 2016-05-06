package object;

import java.util.Random;
import java.util.Vector;

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

		if(ConfigReader.getConfig().get("TournoiStandard").equalsIgnoreCase("on")) {
			this.poule1.initPouleCalculWithTournoiStandard();
			this.poule2.initPouleCalculWithTournoiStandard();
	
			this.EcartNiveau = 0.0;
			for (int i=0; i<Integer.parseInt(ConfigReader.getConfig().get("nbEquipeParPoule")); i++)
			{
				this.EcartNiveau += Math.abs(this.poule1.getMesEquipes().get(i).getNiveau() - this.poule2.getMesEquipes().get(i).getNiveau());
	
			}
			
			
			this.DistanceTotale = this.poule1.getDistanceTotale() + this.poule2.getDistanceTotale();
			this.TempsTotal = this.poule1.getTempsTotal() + this.poule2.getTempsTotal();
			
		} else if (ConfigReader.getConfig().get("TournoiComplexe").equalsIgnoreCase("on")) {
			this.initPouleCalculWithTournoiComplexe();
			
			this.EcartNiveau = 0.0;
			for (int i=0; i<Integer.parseInt(ConfigReader.getConfig().get("nbEquipeParPoule")); i++)
			{
				this.EcartNiveau += Math.abs(this.poule1.getMesEquipes().get(i).getNiveau() - this.poule2.getMesEquipes().get(i).getNiveau());
	
			}
			
			this.DistanceTotale = this.poule1.getDistanceTotale() + this.poule2.getDistanceTotale();
			this.TempsTotal = this.poule1.getTempsTotal() + this.poule2.getTempsTotal();
		}
	}
	
	/**
	 * Initialisation du rang � 1 est domine=false
	 */
	public void initRang() {
		this.setRang(1.0);
		this.setDomine(false);
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
		res = "Distance Totale : " + this.DistanceTotale + "km\nTemps total : " + this.TempsTotal + " heures\nEcart de niveau : " + this.EcartNiveau;
		return res;
	}


	public String toString(Boolean AfficherEquipes) {
		this.initCalculs();
		String res = "";

		res = "Distance Totale : " + this.DistanceTotale + "km\nTemps total : " + this.TempsTotal + " heures\nEcart de niveau : " + this.EcartNiveau;

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

	public void initPouleCalculWithTournoiComplexe() {
		
		//On calcul pour la poule 1
		for(int i = 0; i < this.poule1.getMesEquipes().size(); i++) {
			int nbIdParcouru = 0;
			
			for(Integer q : this.poule1.getIdEquipes()) {
				
				// On fait une sous poule fictive, on divise la poule totale en deux
				if(i < (this.poule1.getMesEquipes().size()/2) && nbIdParcouru < (this.poule1.getMesEquipes().size()/2)) {
					this.poule1.setDistanceTotale(this.poule1.getMesEquipes().get(i).getMatriceDistance().get(q-1)*2 + this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(q-1)*2 + this.poule1.getTempsTotal());
				
				} else if(i < (this.poule1.getMesEquipes().size()/2) && nbIdParcouru > (this.poule1.getMesEquipes().size()/2)) {
					this.poule1.setDistanceTotale(this.poule1.getMesEquipes().get(i).getMatriceDistance().get(q-1) + this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(q-1) + this.poule1.getTempsTotal());
					
				} else if(i > (this.poule1.getMesEquipes().size()/2) && nbIdParcouru > (this.poule1.getMesEquipes().size()/2)) {
					this.poule1.setDistanceTotale(this.poule1.getMesEquipes().get(i).getMatriceDistance().get(q-1)*2 + this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(q-1)*2 + this.poule1.getTempsTotal());
					
				} else if(i > (this.poule1.getMesEquipes().size()/2) && nbIdParcouru < (this.poule1.getMesEquipes().size()/2)) {
					this.poule1.setDistanceTotale(this.poule1.getMesEquipes().get(i).getMatriceDistance().get(q-1) + this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(q-1) + this.poule1.getTempsTotal());
					
				}
				
				nbIdParcouru++;
			}
			
			//Il reste a ajouter les trois adversaires aleatoires de la poule2
			if(i < this.poule1.getMesEquipes().size()/2) {
				for(int j = 0; j < this.poule2.getMesEquipes().size()/2; j++) {
					this.poule1.setDistanceTotale(
							//Ici il faut recuperer la valeur de la matrice de distance pour l'equipe i vers l'equipe de la poule 2 random
							this.poule1.getMesEquipes().get(i).getMatriceDistance().get(this.poule2.getIdEquipes().get(j)-1)
							+ this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(this.poule2.getIdEquipes().get(j)-1)
							+ this.poule1.getTempsTotal());
				}
			} else {
				for(int j = (this.poule2.getMesEquipes().size()/2); j < this.poule2.getMesEquipes().size(); j++) {
					//System.out.println("Test j = " + j);
					this.poule1.setDistanceTotale(
							//Ici il faut recuperer la valeur de la matrice de distance pour l'equipe i vers l'equipe de la poule 2 random
							this.poule1.getMesEquipes().get(i).getMatriceDistance().get(this.poule2.getIdEquipes().get(j)-1)
							+ this.poule1.getDistanceTotale());
					this.poule1.setTempsTotal(this.poule1.getMesEquipes().get(i).getMatriceTemps().get(this.poule2.getIdEquipes().get(j)-1)
							+ this.poule1.getTempsTotal());
				}
			}
		}
		
		
		//On calcul pour la poule 2
		
		for(int i = 0; i < this.poule2.getMesEquipes().size(); i++) {
			int nbIdParcouru = 0;
			
			for(Integer q : this.poule2.getIdEquipes()) {
				
				// On fait une sous poule fictive, on divise la poule totale en deux
				if(i < (this.poule2.getMesEquipes().size()/2) && nbIdParcouru < (this.poule2.getMesEquipes().size()/2)) {
					this.poule2.setDistanceTotale(this.poule2.getMesEquipes().get(i).getMatriceDistance().get(q-1)*2 + this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(this.poule2.getMesEquipes().get(i).getMatriceTemps().get(q-1)*2 + this.poule2.getTempsTotal());
					
				} else if(i < (this.poule2.getMesEquipes().size()/2) && nbIdParcouru > (this.poule2.getMesEquipes().size()/2)) {
					this.poule2.setDistanceTotale(this.poule2.getMesEquipes().get(i).getMatriceDistance().get(q-1) + this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(this.poule2.getMesEquipes().get(i).getMatriceTemps().get(q-1) + this.poule2.getTempsTotal());
					
				} else if(i > (this.poule2.getMesEquipes().size()/2) && nbIdParcouru > (this.poule2.getMesEquipes().size()/2)) {
					this.poule2.setDistanceTotale(this.poule2.getMesEquipes().get(i).getMatriceDistance().get(q-1)*2 + this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(this.poule2.getMesEquipes().get(i).getMatriceTemps().get(q-1)*2 + this.poule2.getTempsTotal());
					
				} else if(i > (this.poule2.getMesEquipes().size()/2) && nbIdParcouru < (this.poule2.getMesEquipes().size()/2)) {
					this.poule2.setDistanceTotale(this.poule2.getMesEquipes().get(i).getMatriceDistance().get(q-1) + this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(this.poule2.getMesEquipes().get(i).getMatriceTemps().get(q-1) + this.poule2.getTempsTotal());
					
				}
				
				nbIdParcouru++;
			}
			
			//Il reste a ajouter les trois adversaires aleatoires de la poule1			
			if(i < this.poule2.getMesEquipes().size()/2) {
				for(int j = 0; j < this.poule1.getMesEquipes().size()/2; j++) {
					this.poule2.setDistanceTotale(
							//Ici il faut recuperer la valeur de la matrice de distance pour l'equipe i vers l'equipe de la poule 1 random
							this.poule2.getMesEquipes().get(i).getMatriceDistance().get(this.poule1.getIdEquipes().get(j)-1)
							+ this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(
							this.poule2.getMesEquipes().get(i).getMatriceTemps().get(this.poule1.getIdEquipes().get(j)-1)
							+ this.poule2.getTempsTotal());
				}
			} else {
				for(int j = (this.poule1.getMesEquipes().size()/2); j < this.poule1.getMesEquipes().size(); j++) {
					//System.out.println("Test j = " + j);
					this.poule2.setDistanceTotale(
							//Ici il faut recuperer la valeur de la matrice de distance pour l'equipe i vers l'equipe de la poule 1 random
							this.poule2.getMesEquipes().get(i).getMatriceDistance().get(this.poule1.getIdEquipes().get(j)-1)
							+ this.poule2.getDistanceTotale());
					this.poule2.setTempsTotal(
							this.poule2.getMesEquipes().get(i).getMatriceTemps().get(this.poule1.getIdEquipes().get(j)-1)
							+ this.poule2.getTempsTotal());
				}
			}
		}
		
		//System.out.println("Test poule1 DT : " + this.poule1.getDistanceTotale() + " TT : " + this.poule1.getTempsTotal());
		//System.out.println("Test poule2 DT : " + this.poule2.getDistanceTotale() + " TT : " + this.poule2.getTempsTotal());
	}
	
	
	public void afficherTri()
	{
		System.out.println(this.getRang() + " DT : " + this.DistanceTotale + " TT : " + this.TempsTotal + " EN : " + this.EcartNiveau);
	}




}
