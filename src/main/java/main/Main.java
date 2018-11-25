package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.maps.GeoApiContext;


public class Main {
	
//	static String API_KEY1 = "AIzaSyA524sVCZwD55K1CRjyqTJGE5vh5KiQdOk";
//	static String API_KEY2 = "AIzaSyCpOGjDglR5_pLm0T9AlPZv_pDlbz8FYN0";
//	static String API_KEY3 = "AIzaSyBOX9qnw0MGzYkZqDPgbpr77LS5UMNI8eY";
//	static String API_KEY4 = "AIzaSyCBR7GyIOoU5BflZSn6d5U1PKcH44PshpA";
	static String API_KEY5 = "AIzaSyAFWxj5CvZMSgwaNtwt7MxY98N9M2KFYsg";
	static String API_KEY6 = "AIzaSyBA7HnQ_X6qTD6ykcq-1Zcgy1CHi3_1jRY";	
	static String API_KEY7 = "AIzaSyAA56vsJT0u9lRjglNigkNn9g1cTNiwT9A";
	static String API_KEY8 = "AIzaSyDvlBgWjbh_WnGt2biiSSRHs5L0BaLhV4Q";
	static String API_KEY9 = "AIzaSyBx32FJmANseLe5BpiJFGUCIMOulX9R2CI";
	static String API_KEY10 = "AIzaSyBo0cb_5E8toXQ5yNS4FSsIcJHrrW5P3rU";
	static String API_KEY11 = "AIzaSyB9aSauFsqV6yeaJ3u-9apSBl4oUdI1BT4";
	static String API_KEY12 = "AIzaSyD870haEcHQ1eCglfL6zXYezjdlK-k_NNM";
	static String API_KEY13 = "AIzaSyDEtWGM1wTUMsE3s6YK17_2KkQZjSoP14E";
	static String API_KEY14 = "AIzaSyAiafasyOFrgL3czjIcvjpfT4biJmKmcAA";
	static String API_KEY15 = "AIzaSyDpDSC7IYliZ5ykiJ9BH00cRnBN4ouXm_8";
	static String API_KEY16 = "AIzaSyBJ1QWfgnlHdwXC8deRG0uwKiPmcJJncls";	
	static String API_KEY17 = "AIzaSyB-bMML_R_TthdQtfTWGklBt3nz6BVqGXo";
	static String API_KEY18 = "AIzaSyB6kUXIGM-sUZXeNDgSUa2eGdghxBTG5N4";
	static String API_KEY19 = "AIzaSyC2qBRXk6gwtUNU2DoBhjsfAWLhjxoFwYE";
	static String API_KEY20 = "AIzaSyBOJQ4PqBvKvrrnYv-C5dg-FuN3Lika2EQ";
	static String API_KEY21 = "AIzaSyB6kUXIGM-sUZXeNDgSUa2eGdghxBTG5N4";
	static String API_KEY22 = "AIzaSyAXybpLCnx-RMxzTrJN8TlJnCNga1dd9QA";
	static String API_KEY23 = "AIzaSyD8QTPdE1pRcm0DmMmRKCjvfibI78Nro2E";
	
	static String API_KEY24 = "AIzaSyD75IFBil9NQ7QDTtqsvvXIWJ-IKvsexxw";
	static String API_KEY25 = "AIzaSyCiSwNuRfEv_eHEyRbulDE5TktNIBL0HRo";
	static String API_KEY26 = "AIzaSyB9Hw_ulLNqon_DaMBYGysz8XlAAKOqFIw";
	static String API_KEY27 = "AIzaSyC5k06NdSrW_ZlLjd0zxicxbBnhReVw464";
	static String API_KEY28 = "AIzaSyAvBT0TzxqaoA58CH75MOyZJVtdzs-FHy0";
	static String API_KEY29 = "AIzaSyCTKBK7K8aQAZxX-7ZLXP904bF9hwrGoUg";
	static String API_KEY30 = "AIzaSyAfVH-waFGEoPvcaficuRwAHdUylOMKHK4";
	static String API_KEY31 = "AIzaSyCdXWbarFu_FW2UqUfFcFzJOELi8O9IhwU";
	static String API_KEY32 = "AIzaSyCN3CCrJT0_RoW54teq92g-R1CZr33a3Sc";
	static String API_KEY33 = "AIzaSyB3LHCsXeWI1FBNC-NhmJK2rwrMxYIhb7I";
	static String API_KEY34 = "AIzaSyBI6ciUSG0CmBnTEK68BEg7qzjWTGdzi5M";
	static String API_KEY35 = "AIzaSyC4Sb3uU9p3mkbagtlu6VI-_HgQ0vwIdzs";
	static String API_KEY36 = "AIzaSyAk8rM0SCxE08JcHjg9XOjFTYx44oWxdbI";
	static String API_KEY37 = "AIzaSyDyFOD7rJXOh0rgkfeYEvl6Ak168XJpLLA";
	static String API_KEY38 = "AIzaSyDwIiEamf9pmu5oWQQWY1c2srtYSSXbjUk";
	static String API_KEY39 = "AIzaSyDGx3eIUNHFcMAoXVb0LZm0ibmYdrDueW8";
	static String API_KEY40 = "AIzaSyA_VOsOnaXP6KKKYfyFarox7zeARuOyh_E";
	static String API_KEY41 = "AIzaSyCrPxMU-9Sl-hBkfh96YCM_1-OqSAVTMuM";
	static String API_KEY42 = "AIzaSyBIfdPUyT2AQ-kHAIYNZNscLbsxSeUKoRQ";
	static String API_KEY43 = "AIzaSyCrp-b_QWrqkvnbyNtS10NrzmL57lU1tZ4";
	static String API_KEY44 = "AIzaSyBSTI_wnmFyskVGhDodhoalpxaVr5-RdB4";
	static String API_KEY45 = "AIzaSyBrKYFKq_qHqqCna7Z4BoHpa4rfSCAcT2w";
	static String API_KEY46 = "AIzaSyAA8bNDXmtyTuOyMuivR3KU1ba9w-9eoZA";
	static String API_KEY47 = "AIzaSyAJBcKhxTNq9QO0nyEduVKSzJtgP0OgYS8";
	static String API_KEY48 = "AIzaSyAXgM0cVphSb8J30EdJHIJlgDXsbuRUDR0";
	static String API_KEY49 = "AIzaSyCN4ezdFs6_N_vefoOvZtmLoeyYRqSwSlA"; 
	static String API_KEY50 = "AIzaSyBr4Muyq8q-MZ9JZsW7pIASEbb3OIfLI_g"; 
	static String API_KEY51 = "AIzaSyCWADNJj6XTRAN2l3Y_eN8FcHOaLcXL0Iw"; 
	static String API_KEY52 = "AIzaSyCCiZkvVxc1pZMRIGlzvuq4fyuM0qUqmwA"; 
	static String API_KEY53 = "AIzaSyC_oLHTShKQCJVRMiNchn38830cSJ3UgTY";
	static String API_KEY54 = "AIzaSyDt1HykV-E55JlDJBiEOc3b5GSXFKwBe4U";
	static String API_KEY55 = "AIzaSyD931rxTyRSys3h6-FIGj5zlKAuLNzuEVM";
	static String API_KEY56 = "AIzaSyCeH6nVz409R92NkbvEsRedetd2RCxNYIg";
	static String API_KEY57 = "AIzaSyDJGhTp8Wk3dk05UtpwS9cO8r8tGOOegRs";
	static String API_KEY58 = "AIzaSyB2LLSR6CftQvaSTf5h4XB_z5j1iGdiLdQ";
	static String API_KEY59 = "AIzaSyAcWRf-Q-9J20tQCEilM-FsQSvGOIKo0ws";
	static String API_KEY60 = "AIzaSyBK-vsZWP_pttSIl08N-hf-E90GIGVszLU";
	static String API_KEY61 = "AIzaSyCUXFxRr_oPoS0aRufaSsbtOjC8S8HsiR8";

	public static void main(String[] args) {
		ArrayList<String> apiKeys = new ArrayList<String>();
//		apiKeys.add(API_KEY1);
//		apiKeys.add(API_KEY2);
//		apiKeys.add(API_KEY3);
//		apiKeys.add(API_KEY4);
		apiKeys.add(API_KEY5);
		apiKeys.add(API_KEY6);
		apiKeys.add(API_KEY7);
		apiKeys.add(API_KEY8);
		apiKeys.add(API_KEY9);
		apiKeys.add(API_KEY10);
		apiKeys.add(API_KEY11);
		apiKeys.add(API_KEY12);
		apiKeys.add(API_KEY13);
		apiKeys.add(API_KEY14);
		apiKeys.add(API_KEY15);
		apiKeys.add(API_KEY16);
		apiKeys.add(API_KEY17);
		apiKeys.add(API_KEY18);
		apiKeys.add(API_KEY19);
		apiKeys.add(API_KEY20);
		apiKeys.add(API_KEY21);
		apiKeys.add(API_KEY22);
		apiKeys.add(API_KEY23);
		apiKeys.add(API_KEY24);
		apiKeys.add(API_KEY25);
		apiKeys.add(API_KEY26);
		apiKeys.add(API_KEY27);
		apiKeys.add(API_KEY28);
		apiKeys.add(API_KEY29);
		apiKeys.add(API_KEY30);
		apiKeys.add(API_KEY31);
		apiKeys.add(API_KEY32);
		apiKeys.add(API_KEY33);
		apiKeys.add(API_KEY34);
		apiKeys.add(API_KEY35);
		apiKeys.add(API_KEY36);
		apiKeys.add(API_KEY37);
		apiKeys.add(API_KEY38);
		apiKeys.add(API_KEY39);
		apiKeys.add(API_KEY40);
		apiKeys.add(API_KEY41);
		apiKeys.add(API_KEY42);
		apiKeys.add(API_KEY43);
		apiKeys.add(API_KEY44);
		apiKeys.add(API_KEY45);
		apiKeys.add(API_KEY46);
		apiKeys.add(API_KEY47);
		apiKeys.add(API_KEY48);
		apiKeys.add(API_KEY49);
		apiKeys.add(API_KEY50);
		apiKeys.add(API_KEY51);
		apiKeys.add(API_KEY52);
		apiKeys.add(API_KEY53);
		apiKeys.add(API_KEY54);
		apiKeys.add(API_KEY55);
		apiKeys.add(API_KEY56);
		apiKeys.add(API_KEY57);
		apiKeys.add(API_KEY58);
		apiKeys.add(API_KEY59);
		apiKeys.add(API_KEY60);
		apiKeys.add(API_KEY61);
		
		//writeDistancesToXLS(apiKeys);
		
		//to be removed
    	//writeDistancesToXLS2(apiKeys);			
        try {			
        	MySQLAccess.writeAddressesToSQLDB2();

    		//to be removed
        	//MySQLAccess.readExelDataAndInsertToDB22();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
 /*       try {
			TSPGenerator.createOutputTspFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	
	public static boolean writeDistancesToXLS(ArrayList<String> apiKeys) {
		LinkedHashMap<Integer, String> latLongMap = InputManager.readExelData();
		
		
		for (int j = 0; j < apiKeys.size(); j++) {
			try {
				String apiKey = apiKeys.get(j);
				System.out.println("ApiKey = " + apiKey);
				GeoApiContext distCalcer = new GeoApiContext.Builder().apiKey(apiKey).build();
//				LinkedHashMap<Integer, String> latLongMap = InputManager.readExelData();
				for (int key : latLongMap.keySet())
				{
						String latlongStrSrc = latLongMap.get(key);
						String[] latlongArr = latlongStrSrc.split(",");
			    		String srcAddressFormatted = DistanceFinder.getFormattedAddress(latlongArr,distCalcer);
			    		
			    		boolean fileAlreadyExist = false;
			    		//check if the file already exist with the source name
			    		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
			    		File[] listOfFiles = folder.listFiles();
			    		for (File file : listOfFiles) {
			    		    if (file.isFile()) {
			    	    		String addressToMatch = srcAddressFormatted + ".xlsx";
			    		        if(addressToMatch.equals(file.getName())) {
			    		        	fileAlreadyExist = true;
				    	    		System.out.println("addressToMatch :"+ addressToMatch);
				    	    		System.out.println("listOfFiles :"+ file.getName());
			    		        	break;
			    		        }
			    		    }
			    		}

			    		if(!fileAlreadyExist) {
						     // Create a Workbook
					        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
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
							for (Entry<Integer, String> entryDist : latLongMap.entrySet()) {
							    String latlongStrDist = entryDist.getValue();
								String[] latlongArrDist = latlongStrDist.split(",");
					    		String distAddressFormatted = DistanceFinder.getFormattedAddress(latlongArrDist,distCalcer);
					    		System.out.println("srcAddressFormatted :"+ srcAddressFormatted);
					    		System.out.println("distAddressFormatted :"+ distAddressFormatted);
								if(latlongStrSrc != latlongStrDist) {
						    		String distanceBwSrcAndDist = DistanceFinder.getDistance(srcAddressFormatted,distAddressFormatted,distCalcer);
						    		System.out.println("Distance :"+ distanceBwSrcAndDist);
						    	    
							        // Create Other rows and cells with employees data
						            Row row = sheet.createRow(rowNum++);
						            row.createCell(0).setCellValue(srcAddressFormatted);
						            row.createCell(1).setCellValue(distAddressFormatted);
						            row.createCell(2).setCellValue(distanceBwSrcAndDist);
								}
					    	}
							
							// Resize all columns to fit the content size
					        for(int i = 0; i < columns.length; i++) {
					            sheet.autoSizeColumn(i);
					        }
					        
					        // Write the output to a file
					        FileOutputStream fileOut;
							try {
					    		String filename = System.getProperty("user.dir") + "\\data\\gmap_distances\\TourSheet_Complete\\"+srcAddressFormatted+".xlsx";
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
			    		} else {
		    		        System.out.println("File already exist with the name: " + srcAddressFormatted);
			    		}
		    	}
			}
	        catch(NullPointerException e)
	        {
	            System.out.print("NullPointerException caught");
	        }
		}
		return true;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean writeDistancesToXLS2(ArrayList<String> apiKeys) {
		LinkedHashMap<Integer, String> latLongMap = InputManager.readExelData();
		
		for (int j = 0; j < apiKeys.size(); j++) {
			try {
				String apiKey = apiKeys.get(j);
				System.out.println("ApiKey = " + apiKey);
				GeoApiContext distCalcer = new GeoApiContext.Builder().apiKey(apiKey).build();
//				LinkedHashMap<Integer, String> latLongMap = InputManager.readExelData();
				
			    //to be removed
				String[] myList = {"Stadlerstraﬂe 5 93053 Regensburg","Karl-Esser-Straﬂe 2 93049 Regensburg","An der Irler Hˆhe 38 93055 Regensburg","Hochweg 46 93049 Regensburg","Margaretenau 24 93049 Regensburg","Kager 7 93059 Regensburg","Riesengebirgstraﬂe 79 93057 Regensburg","Ernst-Reuter-Platz 2, 93047 Regensburg","Gr‰ﬂlstraﬂe 93059 Regensburg","David-Funk-Straﬂe 28 93055 Regensburg","Ziegetsdorfer Str. 24 93051 Regensburg","Auweg 21 93055 Regensburg","Irl 8 93055 Regensburg","Irl 19 93055 Regensburg","Sophie-Scholl-Straﬂe 78 93055 Regensburg"};
				for (String address : myList) {
				    		String srcAddressFormatted = address;
				    		
				    		boolean fileAlreadyExist = false;
				    		//check if the file already exist with the source name
				    		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
				    		File[] listOfFiles = folder.listFiles();
				    		for (File file : listOfFiles) {
				    		    if (file.isFile()) {
				    	    		String addressToMatch = srcAddressFormatted + "3.xlsx";
				    		        if(addressToMatch.equals(file.getName())) {
				    		        	fileAlreadyExist = true;
					    	    		System.out.println("addressToMatch :"+ addressToMatch);
					    	    		System.out.println("listOfFiles :"+ file.getName());
				    		        	break;
				    		        }
				    		    }
				    		}
	
				    		if(!fileAlreadyExist) {
							     // Create a Workbook
						        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
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
									for (String address2 : myList)
									{
						    		String distAddressFormatted = address2;
						    		System.out.println("srcAddressFormatted :"+ srcAddressFormatted);
						    		System.out.println("distAddressFormatted :"+ distAddressFormatted);
									if(address != address2) {
							    		String distanceBwSrcAndDist = DistanceFinder.getDistance(srcAddressFormatted,distAddressFormatted,distCalcer);
							    		System.out.println("Distance :"+ distanceBwSrcAndDist);
							    	    
								        // Create Other rows and cells with employees data
							            Row row = sheet.createRow(rowNum++);
							            row.createCell(0).setCellValue(srcAddressFormatted);
							            row.createCell(1).setCellValue(distAddressFormatted);
							            row.createCell(2).setCellValue(distanceBwSrcAndDist);
									}
						    	}
								
								// Resize all columns to fit the content size
						        for(int i = 0; i < columns.length; i++) {
						            sheet.autoSizeColumn(i);
						        }
						        
						        // Write the output to a file
						        FileOutputStream fileOut;
								try {
						    		String filename = System.getProperty("user.dir") + "\\data\\gmap_distances\\TourSheet_Complete\\"+srcAddressFormatted+"3.xlsx";
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
				    		} else {
			    		        System.out.println("File already exist with the name: " + srcAddressFormatted);
				    		}
			    	
				}
			}
	        catch(NullPointerException e)
	        {
	            System.out.print("NullPointerException caught");
	        }
		}
		return true;	
	}
	

	
}
