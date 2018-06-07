import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {
	public static void main(String[] args) {
//        MongoDB.ConnectToDB();
		writeDistancesToXLS();
//        InputManager.writeDistanceToExcellData(distanceMap);
    }
	
	public static boolean writeDistancesToXLS() {
		HashMap<Integer, String> latLongMap = InputManager.readExelData();		
    	for (HashMap.Entry<Integer, String> entrySrc : latLongMap.entrySet()) {
		    String latlongStrSrc = entrySrc.getValue();
			String[] latlongArrSrc = latlongStrSrc.split(",");
			
		     // Create a Workbook
	        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
	        /* CreationHelper helps us create instances of various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	        CreationHelper createHelper = workbook.getCreationHelper();
	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("sheet");
	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBoldweight((short) 14);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        // Create header Row
	        Row headerRow = sheet.createRow(0);
	        // Create cells
	        String[] columns = {"Source", "Destination", "Distancein Km"};
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }

	        int rowNum = 1;
	        String srcName = "";
	        String distName = "";
	        String distanceBwSrcAndDist = "";
			for (HashMap.Entry<Integer, String> entryDist : latLongMap.entrySet()) {
			    String latlongStrDist = entryDist.getValue();
				String[] latlongArrDist = latlongStrDist.split(",");

	    		String[] distanceResult = DistanceFinder.getDistance(latlongArrSrc,latlongArrDist);
		        srcName = distanceResult[0];
		        distName = distanceResult[1];
	    		distanceBwSrcAndDist = distanceResult[2];
	    		System.out.println("Distance :"+ distanceBwSrcAndDist);
	    	    
		        // Create Other rows and cells with employees data
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(srcName);
	            row.createCell(1).setCellValue(distName);
	            row.createCell(2).setCellValue(distanceBwSrcAndDist);
	    	}
			
			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	        
	        // Write the output to a file
	        FileOutputStream fileOut;
			try {
	    		String filename = System.getProperty("user.dir") + "\\data\\gmap_distances\\"+srcName+".xlsx";
				fileOut = new FileOutputStream(new File(filename));
		        workbook.write(fileOut);
		        fileOut.close();
		        // Closing the workbook
		        //workbook.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	
		return true;
	}
	
}
