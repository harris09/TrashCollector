package main;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entities.AcoCanFormat;
import entities.GpsData;
import entities.TrashCan;
import enumerations.EDaySpecification;
import enumerations.EFillLevel;
import enumerations.EPublicStatus;


public class TSPGenerator {

	public static void createOutputTspFile() throws FileNotFoundException, UnsupportedEncodingException {
//		String tourNumber = tour.getTourNumber();
		String location = System.getProperty("user.dir") + "\\output\\Tour.tsp";
		List<AcoCanFormat> acoCanList = createAcoCanList();
		
		PrintWriter writer = new PrintWriter(location, "UTF-8");
		writer.println("NAME: " + "Tour");
		writer.println("TYPE: TSP");
		writer.println("COMMENT: Regensburg Trash Collecltion ");
		writer.println("DIMENSION: " + acoCanList.size());
		writer.println("EDGE_WEIGHT_TYPE: GEO");
		writer.println("DISPLAY_DATA_TYPE: COORD_DISPLAY");
		writer.println("NODE_COORD_SECTION");
		for(AcoCanFormat acoCan : acoCanList) {
			writer.println(acoCan.getDescription() + " " + acoCan.getLatitude() + " " + acoCan.getLongitude());
		}
		writer.println("EOF");
		
		writer.close();
	}
	
	public static List<TrashCan> readTourSheetInput() throws IOException {
        String location = System.getProperty("user.dir") + "\\input\\can_list\\TourSheet.xlsx";
        List<TrashCan> canList = new ArrayList<TrashCan>();
        TrashCan can = new TrashCan();
	
        InputStream InputFile = new FileInputStream(location);
        XSSFWorkbook wb = new XSSFWorkbook(InputFile);
        int start, end, count;
            
        XSSFSheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(2);
        Cell cell = row.getCell(1);
        start = ((int) cell.getNumericCellValue()) - 1;
        count = start;
        cell = row.getCell(3);
        end = ((int) cell.getNumericCellValue()) - 1;
            
        while(count <= end)
        {
        	row = sheet.getRow(count);
            can = readTrashCan(row);
            canList.add(can);
            count++;
        }

        return canList;
	}
	
	
	private static TrashCan readTrashCan(Row row)
	{
		TrashCan can = new TrashCan();
		GpsData gpsData = new GpsData();
		EPublicStatus publicStatus = EPublicStatus.PUBLIC;
		EPublicStatus privateStatus = EPublicStatus.PRIVATE;
		
		// Can number
		Cell cell = row.getCell(0);
		can.setCanNumber(Integer.toString((int) cell.getNumericCellValue())); 
		
		// Public status
		cell = row.getCell(1);
		if(cell.getStringCellValue().equals("public"))
			can.setPublicStatus(publicStatus);
		else
			can.setPublicStatus(privateStatus);
		
		// Address
		cell = row.getCell(2);
		can.setAddress(cell.getStringCellValue());
		
		// GpsData
		cell = row.getCell(3);
        gpsData.setLatitude(cell.getNumericCellValue());
        cell = row.getCell(4);
        gpsData.setLongitude(cell.getNumericCellValue());
        can.setGpsData(gpsData);
		
        /*
         * 	// SensorBoolean
			cell = row.getCell(5);
			if(cell.getStringCellValue().equals("true"))
				can.setSensor(true);
			else
				can.setSensor(false);
         * 
         */
        
        /*
         *  // FillLevel
			cell = row.getCell(6);
			if(cell.getStringCellValue().equals("overfull"))
				can.setFillLevel(overFull);
			else if(cell.getStringCellValue().equals("full"))
				can.setFillLevel(full);
			else if(cell.getStringCellValue().equals("half-full"))
				can.setFillLevel(halfFull);
			else if(cell.getStringCellValue().equals("empty"))
				can.setFillLevel(empty);
         * 
         */
        
        cell = row.getCell(6);
        switch(cell.getStringCellValue()) {
        	case "Monday" :
        		can.setDaySpecification(EDaySpecification.MONDAY);
        		break;
        	case "Tuesday" :
        		can.setDaySpecification(EDaySpecification.TUESDAY);
        		break;
        	case "Wednesday" :
        		can.setDaySpecification(EDaySpecification.WEDNESDAY);
        		break;
        	case "Thursday" :
        		can.setDaySpecification(EDaySpecification.THURSDAY);
        		break;
        	case "Friday" :
        		can.setDaySpecification(EDaySpecification.FRIDAY);
        		break;
        }
        
        can.setSensor(false);

		return can;
	}
	
	public static List<AcoCanFormat> createAcoCanList() {
		List<AcoCanFormat> acoCanList = new ArrayList<AcoCanFormat>();
		EPublicStatus publicStatus = EPublicStatus.PUBLIC;
		
		 try {
			for (TrashCan can : readTourSheetInput()) {
				 AcoCanFormat acoCan = new AcoCanFormat();
				 // Create description for acoCan:  CanNr. + publicStatus
				 if(can.getPublicStatus().equals(publicStatus))
					 acoCan.setDescription(can.getCanNumber() + "PB");
				 else
					 acoCan.setDescription(can.getCanNumber() + "PT");
				 
				 // Calculate GEOCoordinates from GPSData
				 acoCan.setLatitude(calculateGeoCoordinate(can.getGpsData().getLatitude()));
				 acoCan.setLongitude(calculateGeoCoordinate(can.getGpsData().getLongitude()));
				 
				 acoCanList.add(acoCan);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return acoCanList;
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
}
