package object;

import java.util.Vector;

public class Equipe {

	private String name;
	/**
	 * Default 0
	 */
	private double niveau;
	private Vector<Double> matriceDistance;
	private Vector<Double> matriceTemps;
	private int numeroEquipe;
	/**
	 * Boolean pour savoir si l'�quipe est d�j� utilis� dans une Poule
	 */
	private boolean usedPoule;
	
	
	public Equipe() {
		this.setName("");
		this.setMatriceDistance(new Vector<Double>());
		this.setMatriceTemps(new Vector<Double>());
		this.setNiveau(0);
		this.setUsedPoule(false);
	}
	
	public Equipe(String name, int numeroEquipe) {
		this.setName(name);
		this.setMatriceDistance(new Vector<Double>());
		this.setMatriceTemps(new Vector<Double>());
		this.setNiveau(0);
		this.setNumeroEquipe(numeroEquipe);
		this.setUsedPoule(false);
	}

	public Equipe(String name, Vector<Double> matriceTemps, Vector<Double> matriceDistance, int numeroEquipe) {
		this.setName(name);
		this.setMatriceDistance(matriceDistance);
		this.setMatriceTemps(matriceTemps);
		this.setNiveau(0);
		this.setNumeroEquipe(numeroEquipe);
		this.setUsedPoule(false);
	}
	
	public Equipe(String name, Vector<Double> matriceTemps, Vector<Double> matriceDistance, double niveau, int numeroEquipe) {
		this.setName(name);
		this.setMatriceDistance(matriceDistance);
		this.setMatriceTemps(matriceTemps);
		this.setNiveau(niveau);
		this.setNumeroEquipe(numeroEquipe);
		this.setUsedPoule(false);
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the niveau
	 */
	public double getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(double niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the matriceDistance
	 */
	public Vector<Double> getMatriceDistance() {
		return matriceDistance;
	}

	/**
	 * @param matriceDistance the matriceDistance to set
	 */
	public void setMatriceDistance(Vector<Double> matriceDistance) {
		this.matriceDistance = matriceDistance;
	}

	/**
	 * @return the matriceTemps
	 */
	public Vector<Double> getMatriceTemps() {
		return matriceTemps;
	}

	/**
	 * @param matriceTemps the matriceTemps to set
	 */
	public void setMatriceTemps(Vector<Double> matriceTemps) {
		this.matriceTemps = matriceTemps;
	}
	
	/**
	 * @return the numeroEquipe
	 */
	public int getNumeroEquipe() {
		return numeroEquipe;
	}

	/**
	 * @param numeroEquipe the numeroEquipe to set
	 */
	public void setNumeroEquipe(int numeroEquipe) {
		this.numeroEquipe = numeroEquipe;
	}
	
	@Override
	public String toString() {
		return "Equipe [name=" + name + ", numero="+numeroEquipe+", niveau=" + niveau + ", matriceDistance=" + matriceDistance
				+ ", matriceTemps=" + matriceTemps + "]";
	}

	/**
	 * @return the usedPoule
	 */
	public boolean isUsedPoule() {
		return usedPoule;
	}

	/**
	 * @param usedPoule the usedPoule to set
	 */
	public void setUsedPoule(boolean usedPoule) {
		this.usedPoule = usedPoule;
	}
}
