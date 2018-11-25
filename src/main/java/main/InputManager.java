package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.SortedMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class InputManager {
	
    static LinkedHashMap<Integer, String> readExelData() {
    	LinkedHashMap<Integer, String> latLongMap = new LinkedHashMap<Integer, String>();
    	try {
    		String filename = System.getProperty("user.dir") + "\\data\\tour_sheets\\TourSheet_Complete.xlsx";
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
            
            int counter = count;
            while(count <= end)
            {
            	row = sheet.getRow(count);
                Cell cellLat = row.getCell(3);
                Cell cellLong = row.getCell(4); 
                String latlong = calculateGeoCoordinate(cellLat.getNumericCellValue()) +","+ calculateGeoCoordinate(cellLong.getNumericCellValue());               
                if(!latLongMap.containsValue(latlong)){
                    latLongMap.put(counter, latlong); 
                    counter++;
                } else {
                	counter--;
                }
//                latLongMap.put(row.getRowNum(), latlong);                	
                count++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }    	
		return latLongMap;
    }	
    
    static void deleteRow(FileInputStream file, XSSFWorkbook workbook, XSSFSheet sheet, int rowNo) throws Exception {

/*        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;*/
        try {
            
/*    		String filename = System.getProperty("user.dir") + "\\data\\tour_sheets\\TourSheet_Complete.xlsx";
            FileInputStream file = new FileInputStream(new File(filename));
    	
            // Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            sheet = workbook.getSheetAt(0);*/
 
            int lastRowNum = sheet.getLastRowNum();
            if (rowNo >= 0 && rowNo < lastRowNum) {
                sheet.shiftRows(rowNo + 1, lastRowNum, -1);
            }
            if (rowNo == lastRowNum) {
                XSSFRow removingRow=sheet.getRow(rowNo);
                if(removingRow != null) {
                    sheet.removeRow(removingRow);
                }
            }
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\data\\tour_sheets\\TourSheet_Complete.xlsx"));
            workbook.write(outFile);
            outFile.close();


        } catch(Exception e) {
            throw e;
        } finally {
/*            if(workbook != null)
                workbook.close();*/
        }
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
	
	
	
	static boolean fileAlreadyExist(String srcAddressFormatted) {
		boolean fileAlreadyExist = false;
		//check if the file already exist with the source name
		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
	    		String addressToMatch = srcAddressFormatted + ".xlsx";
		        if(addressToMatch.equals(file.getName())) {
		        	fileAlreadyExist = true;
    	    		//System.out.println("addressToMatch :"+ addressToMatch);
    	    		//System.out.println("listOfFiles :"+ file.getName());
		        	break;
		        }
		    }
		}
		return fileAlreadyExist;
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
