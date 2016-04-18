package main;

import java.util.Vector;

import managers.ObjectManager;
import managers.PopulationConstructor;
import object.Poule;
import tools.ConfigReader;
import tools.ExcelReader;

public class PouleConstructor {

	public static String version = "1.0";
	public static String collaborateur = "PERARDELLE Olivier, MULLER Jean-Francois";
	public static String logiciel = "PouleConstructor";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bienvenue dans le logiciel " + logiciel + " version " + version + " cree par " + collaborateur);
		init();
	}
	
	private static void init() {
		new ConfigReader();
		new ExcelReader();
		new ObjectManager();
		new PopulationConstructor();
		
		ConfigReader.setPathFile("./config.txt");
		ConfigReader.getConfigFromFile();
		//On indique la taille maximale du nombre d'equipe par Poule
		Poule.setMax_size(9);
		
		Vector<String> filesPath = new Vector<String>();
		filesPath.add(ConfigReader.getConfig().get("pathMatrices"));
		
		ExcelReader.setPathFiles(filesPath);
		
		System.out.println("Debut de l'initialisation des Equipes");
		ExcelReader.getEquipeFromExcelFile();
		System.out.println("Fin de l'initialisation des Equipes");
		
		/** DEBUT de l'algorithme genetique **/
		
		//Generation d'un individu aleatoire
		System.out.println("Debut de la generation aleatoire des Individus");
		PopulationConstructor.GenererNIndividuAleatoire(Integer.parseInt(ConfigReader.getConfig().get("nbIndividuAleatoire")));
		System.out.println("Fin de la generation aleatoire des Individus");

		//Mutation des individus
		System.out.println("Debut de la mutation des Individus");
		PopulationConstructor.MutationPopulation(Integer.parseInt(ConfigReader.getConfig().get("nbMaxMutationParIndividu")));
		
		System.out.println("Fin de la mutation des Individus");
		System.out.println("-------------");
		System.out.println("-------------");
		
		//Selection des meilleurs individus
		PopulationConstructor.SelectionNMeilleursIndividus(Integer.parseInt(ConfigReader.getConfig().get("nbSelectionMeilleurIndividu")));
	}

}
