package object;

import java.util.Vector;

public class Poule {

	private static int max_size;
	private Vector<Equipe> mesEquipes;
	
	public Poule() {
		this.setMesEquipes(new Vector<Equipe>());
	}

	/**
	 * @return the mesEquipes
	 */
	public Vector<Equipe> getMesEquipes() {
		return mesEquipes;
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
		return res;
	}
}
