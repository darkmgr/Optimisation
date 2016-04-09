package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigReader {

	private static String pathFile;
	private static HashMap<String, String> config;
	
	public ConfigReader() {
		ConfigReader.setPathFile("");
		ConfigReader.setConfig(new HashMap<String, String>());
	}
	
	public static void getConfigFromFile() {
		if(!ConfigReader.getPathFile().isEmpty()) {
			BufferedReader br = null;

			try {

				String sCurrentLine;

				br = new BufferedReader(new FileReader(ConfigReader.getPathFile()));

				while ((sCurrentLine = br.readLine()) != null) {
					//On ne prend pas les commentaires
					if(!sCurrentLine.matches("#.*")) {
						//On ne prend que les valeurs correctement paramétrées
						if(sCurrentLine.matches(".*=.*")) {
							System.out.println(sCurrentLine);
							config.put(sCurrentLine.split("=")[0], sCurrentLine.split("=")[1]);
						}
					}
				}
			} catch (IOException e) {
				System.out.println("Erreur dans la lecture du fichier de configuration : " + e.toString());
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					System.out.println("Erreur dans la lecture du fichier de configuration : " + ex.toString());
					ex.printStackTrace();
				}
			}
		}
	}

	public static String getPathFile() {
		return pathFile;
	}

	public static void setPathFile(String pathFile) {
		ConfigReader.pathFile = pathFile;
	}

	public static HashMap<String, String> getConfig() {
		return config;
	}

	public static void setConfig(HashMap<String, String> config) {
		ConfigReader.config = config;
	}
}
