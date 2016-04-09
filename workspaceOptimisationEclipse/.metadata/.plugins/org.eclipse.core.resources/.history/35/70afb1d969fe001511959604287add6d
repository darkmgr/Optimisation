package main;

import java.util.Vector;

import managers.ObjectManager;
import managers.PopulationConstructor;
import object.Equipe;
import object.Individu;
import object.Poule;
import tools.ExcelReader;

public class PouleConstructor {

	public static String version = "1.0";
	public static String collaborateur = "PERARDELLE Olivier, MULLER Jean-Fran�ois";
	public static String logiciel = "PouleConstructor";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenue dans le logiciel " + logiciel + " version " + version + " cr�e par " + collaborateur);
		init();
	}
	
	private static void init() {
		new ExcelReader();
		new ObjectManager();
		new PopulationConstructor();
		//On indique la taille maximale du nombre d'�quipe par Poule
		Poule.setMax_size(9);
		
		Vector<String> filesPath = new Vector<String>();
		
		filesPath.add("./Matrice distance.xlsx");
		filesPath.add("./Matrice temps.xlsx");		
		
		ExcelReader.setPathFiles(filesPath);
		ExcelReader.getEquipeFromExcelFile();
		
		System.out.println("Fin de l'initialisation des Equipes");
		for(Equipe e : ObjectManager.getMesEquipes()) {
			System.out.println(e.toString());
		}
		
		System.out.println("-------------");
		System.out.println("-------------");
		
		//G�n�ration d'un individu al�atoire
		PopulationConstructor.GenererIndividuAleatoire();
		System.out.println("Fin de la g�n�ration al�atoire d'un individu");
		for(Individu i : ObjectManager.getMesIndividus()) {
			System.out.println(i.toString());
		}
		
		System.out.println("-------------");
		System.out.println("Mutation des individus\n"); // TODO NOTE : A changer par selection des meilleurs individus
		
		for(Individu i : ObjectManager.getMesIndividus()) {
			i.mutation();
			System.out.println(i.toString());
		}		
		
//		System.out.println("Test equipe ObjectManager");
//		for(Equipe e : ObjectManager.getMesEquipes()) {
//			System.out.println(e.toString());
//		}
	}

}
