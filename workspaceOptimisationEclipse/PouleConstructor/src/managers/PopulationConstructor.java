package managers;

import java.util.Random;
import java.util.Vector;

import object.Equipe;
import object.Individu;
import object.Poule;

public class PopulationConstructor {

	public PopulationConstructor() {
	}
	
	public static void GenererIndividuAleatoire() {
		//Cr�ation des deux poules de l'individu
		Poule p1 = new Poule();
		Poule p2 = new Poule();
		
		@SuppressWarnings("unchecked")
		Vector<Equipe> copieEquipe = (Vector<Equipe>) ObjectManager.getMesEquipes().clone();
		
		
		int i = 1;
		while(!copieEquipe.isEmpty()) {
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(copieEquipe.size());
			
			if(i <= 9) {
				p1.addEquipeInPoule(copieEquipe.get(nombreAleatoire));
				copieEquipe.removeElementAt(nombreAleatoire);
			} else {
				p2.addEquipeInPoule(copieEquipe.get(nombreAleatoire));
				copieEquipe.removeElementAt(nombreAleatoire);
			}
			i++;
		}
		
		p1.calculNiveau();
		p2.calculNiveau();
		

		
		ObjectManager.addIndividu(new Individu(p1, p2));		
	}
}
