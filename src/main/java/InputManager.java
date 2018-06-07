
import java.io.File;
import java.io.FileInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class InputManager {
	
    static HashMap<Integer, String> readExelData() {
    	HashMap<Integer, String> latLongMap = new HashMap<Integer, String>();
    	try {
    		String filename = System.getProperty("user.dir") + "\\data\\tour_sheets\\TourSheet_Friday.xlsx";
            FileInputStream file = new FileInputStream(new File(filename));
    	
            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0); 
            int start, end, count;
                
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(1);
            start = ((int) cell.getNumericCellValue()) - 1;
            count = start;
            cell = row.getCell(3);
            end = ((int) cell.getNumericCellValue()) - 1;
                
            while(count <= end)
            {
            	row = sheet.getRow(count);
                Cell cellLat = row.getCell(3);
                Cell cellLong = row.getCell(4); 
                String latlong = calculateGeoCoordinate(cellLat.getNumericCellValue()) +","+ calculateGeoCoordinate(cellLong.getNumericCellValue()); 
                latLongMap.put(row.getRowNum(), latlong);
                count++;
            }            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return latLongMap;
    }	
    
	public static double calculateGeoCoordinate(double gpsCoordinate) {
		double geoCoordinate, coordinateRest;
		boolean coordinateNegative = false;
		
		// Check if coordinate is negative
		if(gpsCoordinate < 0) {
			coordinateNegative = true;
			gpsCoordinate = gpsCoordinate * -1;
		}
		
		gpsCoordinate = gpsCoordinate / 100000;
		
		// Calculate geoCoordinate
		geoCoordinate= ((int) gpsCoordinate);
		coordinateRest = gpsCoordinate - geoCoordinate;
		coordinateRest = (coordinateRest * 60) / 100;
		geoCoordinate = geoCoordinate + coordinateRest;
		// Round result
		geoCoordinate = geoCoordinate * 100000;
		geoCoordinate = Math.round(geoCoordinate);
		geoCoordinate = geoCoordinate / 100000;
		
		if(coordinateNegative)
			geoCoordinate = geoCoordinate * -1;
		
		return geoCoordinate;
	}
/*    
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
*/
    
}
