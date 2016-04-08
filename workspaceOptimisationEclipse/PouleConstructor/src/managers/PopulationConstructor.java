package managers;

import java.util.Random;
import java.util.Vector;

import object.Equipe;
import object.Individu;
import object.Poule;

public class PopulationConstructor {

	public PopulationConstructor() {
		
		/*
		 * for (int i in 1 to config.NombreIndividus)
		 * {
		 * 		GenererIndividuAleatoire();
		 * }
		 * 
		 * 
		 * 
		 * */
		
	}
	
	public static Vector<Individu> SelectionMeilleursIndividus() {
		
		/* ALGO DEGUEULASSE NIVEAU OPTIMISATION A VOIR SI ON PEUT FAIRE MIEUX
		 * 
		 * On regarde sur chaque individu s'il existe un autre individu qui est dominé
		 * c'est à dire si i.EcartDeNiveau < j.EcarteDeNiveau ET i.distanceTotale < j.distanceTotale
		 *   
		 * 		Vector<Individus> Population;
		 *  Population = ObjectManager.getMesIndividus()
		 *     for (individu i in population)
		 *     {
		 *     		for (individu j in population)
		 *     		{
		 *     // TODO : Cas ou on choisi la durée comme critère dans le fichier de config
		 *     			if ((i.EcartDeNiveau < j.EcartDeNiveau) && (i.DistanceTotal < j.distanceTotale))
		 *     			{
		 *     				// TODO: ICI IL FAUT SUPPRIMER J DU VECTEUR POPULATION
		 *     			}
		 *     		}
		 *     }
		 * 		
		 * 		if (is.empty(Population))
		 * 	{
		 * 		System.out.println("Tous les individus sont dominés par un autre, 
		 * soit solution optimale soit critère trop strict !\n");
		 * 	}
		 * 
		 * return Population
		 * 
		 * */
		Vector<Individu> Population = null;
		return Population;
	}
	// On fait muter l'individu, on l'ajoute a la population, et on selectionne les N meilleurs
	public static void mutationPopulation(Vector<Individu> Population)
	{
		for (Individu i : Population)
		{
			//TODO: créer la fonction dans classe Individu
			//i.mutation()
			
		}
		// On ajoute les mutants dans la population
		// PopulationGlobale ?? .ajoutePopulation(Population);
		// PopulationGlobale ?? . triMeilleursIndividus();
		

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
