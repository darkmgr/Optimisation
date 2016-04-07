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
		public void calculDistance()
		{
			Iterator i = mesEquipes.iterator();
			while (i.hasNext())
			{
				Object temp = i.next();
				
				//TODO : IMPORTANT !
				// Il faut recuperer la ligne correspondant dans la matrice
				
				
				// Faire une nouvelle matrice avec uniquement les equipes qui sont dans la poule
				
				// valeurBasse = 1
				// Pour i allant de 1 à n, 
				//      Pour j allant de valeurBasse à n,
				//           distancetotal += nouvelleMatrice[i][j]
				//           valeurBasse++
				
				// ValeurBasse sert à calculer que la diagonale haute de la matrice (Sinon on calcule 2 fois
				// Genre match A contre B et apres match B contre A
				// A moins qu'on compte des matchs aller-retour dans ce cas faut mettre 1 à n A VOIR
				
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
		res += "\nDistance total à parcourir : " + this.getDistanceTotale();
		res += "\n";
		return res;
	}
}
