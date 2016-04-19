package managers;

import java.util.Collections;
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
	
	// On fait muter l'individu
	@SuppressWarnings("unchecked")
	public static void MutationPopulation(int nbIndividuMute, int nbMutation) {
		if(!ObjectManager.getMesIndividus().isEmpty()) {
			Vector<Individu> copieIndividus = new Vector<Individu>();
			copieIndividus = (Vector<Individu>) ObjectManager.getMesIndividus().clone();
			Random rand = new Random();
			
			for(int i = 0; i < nbIndividuMute; i++) {
				int nombreAleatoire = rand.nextInt(copieIndividus.size());
				Individu iTempMute = new Individu();
				iTempMute = copieIndividus.get(nombreAleatoire);
				iTempMute.mutation(nbMutation);
				//System.out.println("Test mutation : DT " + iTempMute.getDistanceTotale() + " EN " + iTempMute.getEcartNiveau() + " TT " + iTempMute.getTempsTotal());
				ObjectManager.getMesIndividus().add(iTempMute);
			}
			
			// On force le calcul des donn�es pour �viter les nullpointerexceptions
			for(Individu iTemp : ObjectManager.getMesIndividus()) {
				iTemp.initCalculs();
			}
			
			//System.out.println("Test nbIndividu in ObjectManager : " + ObjectManager.getMesIndividus().size());
		}
	}
	
	public static void GenererNIndividuAleatoire(int nbIndividuAleatoire) {
		while(nbIndividuAleatoire!=0) {
			//Creation des deux poules de l'individu
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
					p1.getIdEquipes().add(copieEquipe.get(nombreAleatoire).getNumeroEquipe());
					copieEquipe.removeElementAt(nombreAleatoire);
				} else {
					p2.addEquipeInPoule(copieEquipe.get(nombreAleatoire));
					p2.getIdEquipes().add(copieEquipe.get(nombreAleatoire).getNumeroEquipe());
					copieEquipe.removeElementAt(nombreAleatoire);
				}
				i++;
			}
			
			p1.initPouleCalcul();
			p2.initPouleCalcul();
			
			ObjectManager.addIndividu(new Individu(p1, p2));	
			nbIndividuAleatoire--;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void SelectionNMeilleursIndividus(int nbIndividuSelectionne) {
		if(!ObjectManager.getMesIndividus().isEmpty()) {
			Vector<Individu> copyAllIndividu = new Vector<Individu>();
			copyAllIndividu = (Vector<Individu>) ObjectManager.getMesIndividus().clone();
			
			Collections.sort(copyAllIndividu);
			
			copyAllIndividu.setSize(nbIndividuSelectionne);
			
//			System.out.println("Selection des "+nbIndividuSelectionne+" meilleurs individus : \n");
			
			ObjectManager.setMesIndividus(copyAllIndividu);
			
//			for(Individu i : ObjectManager.getMesIndividus()) {
//				System.out.println(i.toString());
//			}
					
			
//			System.out.println("-------------");
//			System.out.println("-------------");
		}
	}
}
