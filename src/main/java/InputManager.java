
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class InputManager {
	
    static HashMap<Integer, String> readExelData() {
    	HashMap<Integer, String> latLongMap = new HashMap<Integer, String>();
    	try {
    		String filename = System.getProperty("user.dir") + "\\input\\can_list\\TourSheet.xlsx";
            FileInputStream file = new FileInputStream(new File(filename));
 
            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cellLat = row.getCell(3);
                Cell cellLong = row.getCell(4);
                if(row.getRowNum() >= 5 && cellLat != null && cellLong != null) {
                    if(cellLat.getCellType() != Cell.CELL_TYPE_BLANK && cellLong.getCellType() != Cell.CELL_TYPE_BLANK) {
                        String latlong = cellLat.getStringCellValue() +","+ cellLong.getStringCellValue(); 
        
/*                        System.out.println("cellLat = " + cellLat.getStringCellValue() + ", cellLong = " + cellLong.getStringCellValue());*/
                        latLongMap.put(row.getRowNum(), latlong);
                    }                	
                }
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return latLongMap;
    }	
    
    static void writeDistanceToExcellData(HashMap<Integer, String> distanceMap) {
		String filename = System.getProperty("user.dir") + "\\input\\can_list\\TourSheet.xlsx";
        
        Workbook wb = null;
	    try {
	    	InputStream inp = new FileInputStream(filename);
	        wb = WorkbookFactory.create(inp);
	        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
	        Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int key = 0;
                String distance = null;
                for (HashMap.Entry<Integer, String> entry : distanceMap.entrySet()) {
        		    key = entry.getKey();
        		    distance = entry.getValue();
                    if(row.getRowNum() >= 5 && key == row.getRowNum()) {
            		    System.out.println("RowNum = " + row.getRowNum() + ", key = " + key + ", distance = " + distance);
                    	Cell cell = row.createCell(7);
            	        cell.setCellValue(distance);
                    }
                }    

            }
            // Now this Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(filename);
	        wb.write(fileOut);                	
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
