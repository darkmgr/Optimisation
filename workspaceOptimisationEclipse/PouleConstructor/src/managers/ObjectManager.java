package managers;

import java.util.Vector;

import object.Equipe;
import object.Individu;

public class ObjectManager {

	private static Vector<Equipe> mesEquipes;
	private static Vector<Individu> mesIndividus;
	
	public ObjectManager() {
		ObjectManager.setMesEquipes(new Vector<Equipe>());
		ObjectManager.setMesIndividus(new Vector<Individu>());
	}
	
	public static void addEquipe(Equipe eTemp) {
		if(eTemp != null) {
			if(!ObjectManager.existEquipeWithName(eTemp.getName())) {
				ObjectManager.getMesEquipes().add(eTemp);
				//System.out.println("Ajout de l'équipe "+eTemp.getName()+" au Manager");
			} else {
				//System.out.println("Impossible d'ajouter l'équipe "+eTemp.getName()+" déjà existante");
			}
		} else {
			System.out.println("Impossible d'ajouter une équipe null");
		}
	}
	
	public static void deleteEquipe(Equipe eTemp) {
		if(eTemp != null) {
			for(Equipe e : ObjectManager.getMesEquipes()) {
				if(e.equals(eTemp)) {
					ObjectManager.getMesEquipes().remove(e);
					System.out.println("Equipe correctement supprimée");
				}
			}
		} else {
			System.out.println("Impossible de supprimer une équipe null");
		}
	}
	
	public static boolean existEquipeWithName(String name) {
		boolean res = false;
		for(Equipe e : ObjectManager.getMesEquipes()) {
			if(e.getName().equals(name)) {
				res = true;
			}
		}
		return res;
	}

	/**
	 * @return the mesEquipes
	 */
	public static Vector<Equipe> getMesEquipes() {
		return mesEquipes;
	}

	/**
	 * @param mesEquipes the mesEquipes to set
	 */
	public static void setMesEquipes(Vector<Equipe> mesEquipes) {
		ObjectManager.mesEquipes = mesEquipes;
	}

	public static void addIndividu(Individu iTemp) {
		if(iTemp != null) {
			ObjectManager.getMesIndividus().add(iTemp);
		} else {
			System.out.println("Impossible d'ajouter un individu null");
		}
	}
	
	/**
	 * @return the mesIndividus
	 */
	public static Vector<Individu> getMesIndividus() {
		return mesIndividus;
	}

	/**
	 * @param mesIndividus the mesIndividus to set
	 */
	public static void setMesIndividus(Vector<Individu> mesIndividus) {
		ObjectManager.mesIndividus = mesIndividus;
	}
}
