package tools;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import managers.ObjectManager;
import object.Equipe;

public class ExcelReader {
	private static Vector<String> pathFiles;
	
	public ExcelReader() {
		ExcelReader.setPathFiles(new Vector<String>());
	}

	public static void getEquipeFromExcelFile() {
		if(!ExcelReader.getPathFiles().isEmpty()) {
			try {
				for(String files : ExcelReader.getPathFiles()) {
					FileInputStream inputStream = new FileInputStream(new File(files));
					Workbook workbook = new XSSFWorkbook(inputStream);
			        Sheet firstSheet = workbook.getSheetAt(0);
			        Iterator<Row> iterator = firstSheet.iterator();
			         
			        while (iterator.hasNext()) {
			        	Row nextRow = iterator.next();
			        	// Premi�re ligne = liste des noms des �quipes
			        	if(nextRow.getRowNum() == 0) {			        		
			        		Iterator<Cell> cellIterator = nextRow.cellIterator();
				             
				            while (cellIterator.hasNext()) {
				                Cell cell = cellIterator.next();
				                
				                switch (cell.getCellType()) {
				                    case Cell.CELL_TYPE_STRING:
				                        ObjectManager.addEquipe(new Equipe(cell.getStringCellValue(), cell.getColumnIndex()));
				                        break;
				                }
				            }
			        	} else {
			        		String name_equipe = "";
			        		Vector<Double> matrice = new Vector<Double>();
				            Iterator<Cell> cellIterator = nextRow.cellIterator();				     
				            
				            while (cellIterator.hasNext()) {
				                Cell cell = cellIterator.next();
				                 
				                switch (cell.getCellType()) {
				                	case Cell.CELL_TYPE_STRING:
				                		name_equipe = cell.getStringCellValue();
				                		break;
				                    case Cell.CELL_TYPE_NUMERIC:
				                    	matrice.add(cell.getNumericCellValue());
				                        break;
				                }
				            }
				            
				            for(Equipe e : ObjectManager.getMesEquipes()) {
				            	if(e.getName().equals(name_equipe)) {
				            		if(files.toLowerCase().contains("distance")) {
				            			e.setMatriceDistance(matrice);
				            		} else if(files.toLowerCase().contains("temps")) {
				            			e.setMatriceTemps(matrice);
				            		}
				            	}
				            }
			        	}
			        }
			         
			        workbook.close();
			        inputStream.close();
				}
			} catch(Exception ex) {
				System.out.println("Erreur dans l'ExcelReader.getEquipeFromExcelFile() : " + ex.toString());
				ex.printStackTrace();
			}
		} else {
			System.out.println("Vous n'avez renseign� aucun fichier Excel");
		}
	}
	
	/**
	 * @return the pathFiles
	 */
	public static Vector<String> getPathFiles() {
		return pathFiles;
	}

	/**
	 * @param pathFiles the pathFiles to set
	 */
	public static void setPathFiles(Vector<String> pathFiles) {
		ExcelReader.pathFiles = pathFiles;
	}
}