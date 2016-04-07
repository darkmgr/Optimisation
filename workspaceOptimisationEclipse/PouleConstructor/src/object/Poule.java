package object;

import java.util.Iterator;
import java.util.Vector;

public class Poule {

	private static int max_size;
	private Vector<Equipe> mesEquipes;
	private static float distanceTotale;
	private static int niveau;
	
	public Poule() {
		this.setMesEquipes(new Vector<Equipe>());
	}

	/**
	 * @return the mesEquipes
	 */
	public Vector<Equipe> getMesEquipes() {
		return mesEquipes;
	}	

		public void setDistanceTotale(float distance)
		{
			this.distanceTotale = distance;
		}
		public void setNiveau(int niveau)
		{
			this.niveau = niveau;
		}		
		public float getDistanceTotale()
		{
			return this.distanceTotale;
		}
		public int getNiveau()
		{
			return this.niveau;
		}	
		/**
		 * * Calcul du niveau de la poule (Somme des niveaux des équipes)
		 * 
		 */
		public void calculNiveau()
		{
			Iterator i = mesEquipes.iterator();
			while (i.hasNext())
			{
				Object temp = i.next();
				this.niveau += ((Equipe) temp).getNiveau();
			}
		}
	/**
	 * @param mesEquipes the mesEquipes to set
	 */
	public void setMesEquipes(Vector<Equipe> mesEquipes) {
		if(mesEquipes.size() <= max_size) {
			this.mesEquipes = mesEquipes;
		} else {
			System.out.println("Impossible d'ajouter plus d'�quipe que la taille maximum autoris�");
		}
	}
	
	public void addEquipeInPoule(Equipe eTemp) {
		if(this.getMesEquipes().size() <= max_size) {
			this.getMesEquipes().add(eTemp);
		} else {
			System.out.println("Impossible d'ajouter plus d'�quipe que la taille maximum autoris�");
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

	@Override
	public String toString() {
		String res = "";
		res = "[mesEquipes = ";
		for(Equipe e : this.getMesEquipes()) {
			res += e.toString() + "\n";
		}
		res += "]";
		res += "\nNiveau de la poule : " + this.getNiveau();
		res += "\n";
		return res;
	}
}
