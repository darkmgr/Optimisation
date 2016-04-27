package object;

import java.util.Vector;

public class Poule {

	private static int max_size;
	private Vector<Equipe> mesEquipes;
	/**
	 * Liste des ID des �quipes dans la poule {@link Equipe.numeroEquipe}
	 */
	private Vector<Integer> idEquipes;
	private Double distanceTotale;
	private Double tempsTotal;
	private double niveau;

	public Poule() {
		this.setMesEquipes(new Vector<Equipe>());
		this.setIdEquipes(new Vector<Integer>());
		this.setNiveau(0.0);
		this.setDistanceTotale(0.0);
		this.setTempsTotal(0.0);
	}

	/**
	 * @return the mesEquipes
	 */
	public Vector<Equipe> getMesEquipes() {
		return mesEquipes;
	}	

	private void setDistanceTotale(Double distance) {
		this.distanceTotale = distance;
	}

	private void setNiveau(double niveau) {
		this.niveau = niveau;
	}		
	
	public Double getDistanceTotale()
	{
		return this.distanceTotale;
	}
	
	public double getNiveau()
	{
		return this.niveau;
	}
	
	/**
	 * Initialisation des calculs des Matrices par rapport aux membres des Equipes de la Poule
	 */
	public void initPouleCalcul() {
		this.calculDistanceTotale();
		this.calculTempsTotal();
		this.calculNiveau();
	}
	
	/**
	 * @param mesEquipes the mesEquipes to set
	 */
	public void setMesEquipes(Vector<Equipe> mesEquipes) {
		if(mesEquipes.size() <= max_size) {
			this.mesEquipes = mesEquipes;
		} else {
			System.out.println("Impossible d'ajouter plus d'equipe que la taille maximum autorise");
		}
	}

	public void addEquipeInPoule(Equipe eTemp) {
		if(this.getMesEquipes().size() <= max_size) {
			this.getMesEquipes().add(eTemp);
		} else {
			System.out.println("Impossible d'ajouter plus d'equipe que la taille maximum autorise");
		}
	}

	/**
	 * @return the max_size
	 */
	public static int getMax_size() {
		return max_size;
	}


	/**
	 * @param max_size the max_size to set
	 */
	public static void setMax_size(int max_size) {
		Poule.max_size = max_size;
	}

	public Vector<Integer> getIdEquipes() {
		return idEquipes;
	}

	private void setIdEquipes(Vector<Integer> idEquipes) {
		this.idEquipes = idEquipes;
	}
	
	public Double getTempsTotal() {
		return tempsTotal;
	}

	private void setTempsTotal(Double tempsTotal) {
		this.tempsTotal = tempsTotal;
	}
	
	/**
	 * * Calcul du niveau de la poule (Somme des niveaux des Equipes)
	 * 
	 */
	private void calculNiveau()
	{
		this.setNiveau(0);
		for(Equipe e : this.getMesEquipes()) {
			this.setNiveau(e.getNiveau()+this.getNiveau());
		}
	}
	
	/**
	 * Calcul de la distance totale de la Poule
	 */
	private void calculDistanceTotale() {
		this.setDistanceTotale(0.0);
		for(Equipe e : this.getMesEquipes()) {
			for(Integer i : this.getIdEquipes()) {
				this.setDistanceTotale(e.getMatriceDistance().get(i-1) + this.getDistanceTotale());
			}
		}
	}
	
	/**
	 * Calcul du temps total de la Poule
	 */
	private void calculTempsTotal() {
		this.setTempsTotal(0.0);
		for(Equipe e : this.getMesEquipes()) {
			for(Integer i : this.getIdEquipes()) {
				this.setTempsTotal(e.getMatriceTemps().get(i-1) + this.getTempsTotal());
			}
		}
	}
	
	@Override
	public String toString() {
		String res = "";
		res = "====Equipes====\n";
		for(Equipe e : this.getMesEquipes()) {
			res += e.toString();
		}
		return res;
	}
}
