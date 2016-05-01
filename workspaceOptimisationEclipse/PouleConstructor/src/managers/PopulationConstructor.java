package managers;

import java.util.Collection;
import java.util.Random;
import java.util.Vector;

import object.Equipe;
import object.Individu;
import object.Poule;
import object.SortableVectorEquipe;
import object.SortableVectorIndividu;
import tools.ConfigReader;

public class PopulationConstructor {

	public PopulationConstructor() {		
	}

	public static void initRangs()
	{
		for (Individu i : ObjectManager.getMesIndividus())
		{
			i.setRang(0.0);
			i.setDomine(false);
		}
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
				iTempMute.initCalculs();
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
	
	public static void CroisementPopulation(int nbIndividuCroise) {
		if(!ObjectManager.getMesIndividus().isEmpty()) {
			while(nbIndividuCroise != 0) {
				int nbEquipe = Integer.parseInt(ConfigReader.getConfig().get("nbEquipeParPoule"));
				
				//On prend deux individus aleatoires parmis les individus totaux du Manager
				Random rand = new Random();
				int nombreAleatoire = rand.nextInt(ObjectManager.getMesIndividus().size());
				
				Individu i1 = new Individu();
				i1 = ObjectManager.getMesIndividus().get(nombreAleatoire);
				
				nombreAleatoire = rand.nextInt(ObjectManager.getMesIndividus().size());
				Individu i2 = new Individu();
				i2 = ObjectManager.getMesIndividus().get(nombreAleatoire);
				
				//On prend la premiere moitie de poule de i1 et la deuxieme moitie de poule de i2
				Poule p1 = new Poule();
				Poule p2 = new Poule();
				
				for(int i = 0; i < nbEquipe; i++) {
					if(i < (nbEquipe/2)) {
						p1.addEquipeInPoule(i1.getPoule1().getMesEquipes().get(i));
						p1.getIdEquipes().add(i1.getPoule1().getIdEquipes().get(i));
					} else {
						p1.addEquipeInPoule(i2.getPoule1().getMesEquipes().get(i));
						p1.getIdEquipes().add(i2.getPoule1().getIdEquipes().get(i));
					}
				}
				
				for(int i = 0; i < nbEquipe; i++) {
					if(i < (nbEquipe/2)) {
						p2.addEquipeInPoule(i2.getPoule2().getMesEquipes().get(i));
						p2.getIdEquipes().add(i2.getPoule2().getIdEquipes().get(i));
					} else {
						p2.addEquipeInPoule(i1.getPoule2().getMesEquipes().get(i));
						p2.getIdEquipes().add(i1.getPoule2().getIdEquipes().get(i));
					}
				}
				
				//System.out.println("Test croisementIndividu : \np1 = \n" +p1.toString());
				//System.out.println("Test croisementIndividu : \np2 = \n" + p2.toString());
				
				/**
				 * A ce stade nous avons deux poules qui peuvent contenir les memes equipes, il faut verifier l'uniformite de l'individu
				 * pour le rendre valide, c'est a dire eliminer les doublons et ajouter les equipes manquantes.
				 * En premier lieu nous allons identifier puis eliminer les doublons.
				 **/
				
				
				//Vecteur temporaire pour ajouter les equipes en doublant
				//On ne peut pas supprimer une equipe d'un vecteur dans une boucle !
				Vector<Equipe> vETemp = new Vector<Equipe>();
				
				for(Equipe eTemp : p1.getMesEquipes()) {
					int nbEquipeSimilaire = 0;
					
					for(Equipe eTemp2 : p1.getMesEquipes()) {
						if(eTemp.equals(eTemp2)) {
							nbEquipeSimilaire++;
						}
						if(eTemp.equals(eTemp2) && nbEquipeSimilaire > 1 && !ObjectManager.existEquipeWithName(vETemp, eTemp.getName())) {
							vETemp.addElement(eTemp);
						}
					}
					
					for(Equipe eTemp3 : p2.getMesEquipes()) {
						if(eTemp.equals(eTemp3)) {
							nbEquipeSimilaire++;
						}
						if(eTemp.equals(eTemp3) && nbEquipeSimilaire > 1 && !ObjectManager.existEquipeWithName(vETemp, eTemp.getName())) {
							vETemp.addElement(eTemp);
						}
					}
				}
				
				for(Equipe eTemp : p2.getMesEquipes()) {
					int nbEquipeSimilaire = 0;
					
					for(Equipe eTemp2 : p1.getMesEquipes()) {
						if(eTemp.equals(eTemp2)) {
							nbEquipeSimilaire++;
						}
						if(eTemp.equals(eTemp2) && nbEquipeSimilaire > 1 && !ObjectManager.existEquipeWithName(vETemp, eTemp.getName())) {
							vETemp.addElement(eTemp);
						}
					}
					
					for(Equipe eTemp3 : p2.getMesEquipes()) {
						if(eTemp.equals(eTemp3)) {
							nbEquipeSimilaire++;
						}
						if(eTemp.equals(eTemp3) && nbEquipeSimilaire > 1 && !ObjectManager.existEquipeWithName(vETemp, eTemp.getName())) {
							vETemp.addElement(eTemp);
						}
					}
				}
				
				//System.out.println("Test croisementIndividu equipes en doublons : \n" +vETemp.toString());
				
				/**
				 * Elimination des doublons a l'aide de notre vecteur temporaire.
				 */
				for(Equipe eTemp : vETemp) {
					boolean firstValue = true;
					
					for(int i = 0; i < p1.getMesEquipes().size(); i++) {
						
						if(eTemp.equals(p1.getMesEquipes().get(i)) && !firstValue) {
							p1.getMesEquipes().remove(i);
							p1.getIdEquipes().remove(i);
							i--; // Attention, on supprime un element donc il faut retrancher i pour tester tous les elements

						} else if(eTemp.equals(p1.getMesEquipes().get(i))) {
							firstValue = false;
						}
					}
					
					for(int i = 0; i < p2.getMesEquipes().size(); i++) {
						if(eTemp.equals(p2.getMesEquipes().get(i)) && !firstValue) {
							p2.getMesEquipes().remove(i);
							p2.getIdEquipes().remove(i);
							i--; // Attention, on supprime un element donc il faut retrancher i pour tester tous les elements

						} else if(eTemp.equals(p2.getMesEquipes().get(i))) {
							firstValue = false;
						}
					}
				}
				
				//System.out.println("Test croisementIndividu apres suppression doublons : \np1 = \n" +p1.toString());
				//System.out.println("Test croisementIndividu apres suppression doublons : \np2 = \n" + p2.toString());
				
				/**
				 * A ce stade nous avons supprime les doublons ! Il nous reste a placer les equipes non utilisees dans les poules adequates.
				 * Pour ce faire nous allons utiliser @ObjectManager.getMesEquipes() pour comparer les equipes deja utilisees
				 */
				@SuppressWarnings("unchecked")
				Vector<Equipe> cloneEquipes = (Vector<Equipe>) ObjectManager.getMesEquipes().clone();
				for(int i = 0; i < cloneEquipes.size(); i++) {
					boolean isUsed = false;
					
					for(Equipe eTemp : p1.getMesEquipes()) {
						if(cloneEquipes.get(i).equals(eTemp)) {
							isUsed = true;
						}
					}
					
					if(!isUsed) { //Pas besoin de teste ici si equipe deja utilisee
						for(Equipe eTemp : p2.getMesEquipes()) {
							if(cloneEquipes.get(i).equals(eTemp)) {
								isUsed = true;
							}
						}
					}
					
					//Si equipe testee non utilisee, on l'ajoute a une poule non pleine
					if(!isUsed) {
						if(p1.getMesEquipes().size() < nbEquipe) {
							p1.addEquipeInPoule(cloneEquipes.get(i));
							p1.getIdEquipes().add(cloneEquipes.get(i).getNumeroEquipe());
						} else if (p2.getMesEquipes().size() < nbEquipe) {
							p2.addEquipeInPoule(cloneEquipes.get(i));
							p2.getIdEquipes().add(cloneEquipes.get(i).getNumeroEquipe());
						}
					}
				}
				
				//System.out.println("Test croisementIndividu apres ajout manquant : \np1 = \n" +p1.toString());
				//System.out.println("Test croisementIndividu apres ajout manquant : \np2 = \n" + p2.toString());
				
				/**
				 * Il nous reste a construire le nouvel individu avec les poules crees.
				 */
				
				Individu croisement = new Individu(p1, p2);
				croisement.initCalculs();
				ObjectManager.addIndividu(croisement);
				nbIndividuCroise--;
			}
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

				if(i <= Integer.parseInt(ConfigReader.getConfig().get("nbEquipeParPoule"))) {
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

	/*	@SuppressWarnings("unchecked")
	public static void SelectionNMeilleursIndividus(int nbIndividuSelectionne) {
		if(!ObjectManager.getMesIndividus().isEmpty()) {
			SortableVectorIndividu copyAllIndividu = new SortableVectorIndividu();
			copyAllIndividu.addAll((Collection<? extends Individu>) ObjectManager.getMesIndividus().clone());

			copyAllIndividu.sortIndividu();
			copyAllIndividu.setSize(nbIndividuSelectionne);

			ObjectManager.setMesIndividus(copyAllIndividu);

		}
	}*/


	@SuppressWarnings("unchecked")
	public static void SelectionNMeilleursIndividus(int nbIndividuSelectionne) {
		double rangGeneration = 1.0;
		boolean fin = false;
		initRangs();
		while (!fin)
		{
			fin = true;
			for (Individu i : ObjectManager.getMesIndividus())
			{
				for (Individu j : ObjectManager.getMesIndividus())
				{

					if (j.getRang() == rangGeneration) // S'il n'a pas ete domine dans la generation precedente
					{
						if(ConfigReader.getConfig().get("MethodeSelection").compareToIgnoreCase("distance")==0)
						{
							if (i.getDistanceTotale() > j.getDistanceTotale() && i.getEcartNiveau() > j.getEcartNiveau() && i.getRang() == j.getRang() && j.getDomine()==false)
							{
								i.setDomine(true);
								fin = false;
								break;
							}
						}
						else if (ConfigReader.getConfig().get("MethodeSelection").compareToIgnoreCase("temps")==0)
						{
							if (i.getTempsTotal() > j.getTempsTotal() && i.getEcartNiveau() > j.getEcartNiveau() && i.getRang() == j.getRang() && j.getDomine()==false)
							{
								i.setDomine(true);
								fin = false;
								break;
							}					
						}
						else
						{
							System.out.println("Erreur SortableVector.java GetConfig Inconnu");
							System.exit(1);
						}
					}



				}

				if(i.getDomine() == false)
				{
					i.setRang(rangGeneration+1);					
				}
			}
			rangGeneration++;
		}

		if(!ObjectManager.getMesIndividus().isEmpty()) {
			SortableVectorIndividu copyAllIndividu = new SortableVectorIndividu();
			copyAllIndividu.addAll((Collection<? extends Individu>) ObjectManager.getMesIndividus().clone());

			copyAllIndividu.sortIndividu();
			copyAllIndividu.setSize(nbIndividuSelectionne);

			ObjectManager.setMesIndividus(copyAllIndividu);



			for (Individu i : ObjectManager.getMesIndividus())
			{
				SortableVectorEquipe copyAllEquipe = new SortableVectorEquipe();
				copyAllEquipe.addAll((Collection<? extends Equipe>) i.getPoule1().getMesEquipes().clone());

				copyAllEquipe.sortEquipe();

				i.getPoule1().setMesEquipes(copyAllEquipe);
				
				

				SortableVectorEquipe copyAllEquipe2 = new SortableVectorEquipe();
				copyAllEquipe2.addAll((Collection<? extends Equipe>) i.getPoule2().getMesEquipes().clone());

				copyAllEquipe2.sortEquipe();

				i.getPoule2().setMesEquipes(copyAllEquipe2);
			}

		}		

		/* TODO : Sort du résultat 
		SortableVectorIndividu vectorTemp = new SortableVectorIndividu();
		vectorTemp.setVector(ObjectManager.getMesIndividus());
		vectorTemp.sort();
		vectorTemp.setSize(nbIndividuSelectionne);
		ObjectManager.setMesIndividus(vectorTemp.getVector());*/





	}

}
