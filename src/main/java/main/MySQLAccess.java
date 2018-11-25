package main;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MySQLAccess {
    private static java.sql.Connection connect = null;
    private static java.sql.Statement statement = null;
    private static java.sql.PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    
    
    
    public static void writealocationsToSQLDB2() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/trash_bins?"
                            + "user=root&password=");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
	    	String tableName = "locations";
	    	boolean isTablecreated = createLocationsTable2(tableName,connect);
	    	if(isTablecreated) {
/*		    	String[] myList = {"Stadlerstraﬂe 5 93053 Regensburg","Karl-Esser-Straﬂe 2 93049 Regensburg","An der Irler Hˆhe 38 93055 Regensburg","Hochweg 46 93049 Regensburg","Margaretenau 24 93049 Regensburg","Kager 7 93059 Regensburg","Riesengebirgstraﬂe 79 93057 Regensburg","Ernst-Reuter-Platz 2, 93047 Regensburg","Gr‰ﬂlstraﬂe 93059 Regensburg","David-Funk-Straﬂe 28 93055 Regensburg","Ziegetsdorfer Str. 24 93051 Regensburg","Auweg 21 93055 Regensburg","Irl 8 93055 Regensburg","Irl 19 93055 Regensburg","Sophie-Scholl-Straﬂe 7893055 Regensburg"};
	    		for (String address : myList) {
		            preparedStatement = connect.prepareStatement("insert into  trash_bins.locations values (default, ?)");
			    	preparedStatement.setString(1, address);
		            preparedStatement.executeUpdate();	    			
	    		}
*/
	            File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
	            File[] listOfFiles = folder.listFiles();
	    		for (File file : listOfFiles) {
	    		    if (file.isFile()) {
	    		    	String address = file.getName().replaceAll(".xlsx", "");
	    	            preparedStatement = connect.prepareStatement("insert into  trash_bins.locations values (default, ?)");
	    		    	preparedStatement.setString(1, address);
	    	            preparedStatement.executeUpdate();
	    		    }
	    		}
	    	}	
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    private static boolean createLocationsTable2(String tableName, java.sql.Connection conn) throws SQLException {
		boolean result = false;
		java.sql.DatabaseMetaData dbm = conn.getMetaData();
		// check if "employee" table is there
		ResultSet tables = dbm.getTables(null, null, tableName, null);
		if (tables.next()) {
			result = true;
	        System.out.println("Table already exists " + tableName);
		}
		else {
	        System.out.println("Table does not exists, creating new " + tableName);
	    	String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName
	                + "(loc_id  INT NOT NULL AUTO_INCREMENT,"
	                + "address  VARCHAR(150) UNIQUE,"
	    			+ "PRIMARY KEY(loc_id))";
	        System.out.println("Table Query:  " + sqlCreate);
	        java.sql.Statement stmt = conn.createStatement();
	        stmt.executeUpdate(sqlCreate);
	        result = true;
		}
		return result;
}
    public static void writeAddressesToSQLDB2() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/trash_bins?"
                            + "user=root&password=");            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
 
            //create table distances
	    	String tableName = "distances";
	    	boolean isTablecreated = createAddressesTable2(tableName,connect);
	    	if(isTablecreated) {	
	    		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
	            File[] listOfFiles = folder.listFiles();
	            int count = 1;
	    		for (File file : listOfFiles) {
	    		    if (file.isFile() && count < 300) {
	 	    		    	String fileName = file.getName().replaceAll(".xlsx", "");
		    		    	System.out.println(count + " : fileName = " + fileName);
	    		    		readExelDataAndInsertToDB2(fileName, tableName, connect);	 
	    		    }
	    		    count++;
	    		}
	    	}
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    private static boolean createAddressesTable2(String tableName, java.sql.Connection conn) throws SQLException {
    			boolean result = false;
    			java.sql.DatabaseMetaData dbm = conn.getMetaData();
    			// check if "employee" table is there
    			ResultSet tables = dbm.getTables(null, null, tableName, null);
    			if (tables.next()) {
    				result = true;
    		        System.out.println("Table already exists " + tableName);
    			}
    			else {
    		        System.out.println("Table does not exists, creating new " + tableName);
    		    	String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName
    		                + "(from_loc_id  INT,"
    		                + "to_loc_id  INT,"
    		                + "distance_in_km  VARCHAR(50),"
    		    			+ "PRIMARY KEY(from_loc_id,to_loc_id))";
    		        java.sql.Statement stmt = conn.createStatement();
    		        stmt.executeUpdate(sqlCreate);
    		        result = true;
    			}
    			return result;
    }
    static void readExelDataAndInsertToDB2(String fName, String tablename, java.sql.Connection conn) {
        int start, end, count = 0;
    	try {
    		String filename = System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/"+fName+".xlsx";
            File f = new File(filename);
            if(f.exists() && !f.isDirectory()) { 
        		FileInputStream file = new FileInputStream(new File(filename));
                // Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                // Get first/desired sheet from the workbook
                XSSFSheet sheet = workbook.getSheetAt(0); 
                               
                start = 1;
                count = start;
                end = 750;
                Row row = sheet.getRow(1);
                while(count <= end)
                {
                	row = sheet.getRow(count);
                	if(row != null){
    	                String cellSource = row.getCell(0).toString();
    	                String cellDistination = row.getCell(1).toString(); 
    	                String cellDistance = row.getCell(2).toString(); 
    	                
    	                
    			    	int from_loc_id  = getAddressId(cellSource,connect);
    			    	int to_loc_id  = getAddressId(cellDistination,connect);
    	                if(!isEntryInDB(tablename,from_loc_id,to_loc_id,cellDistance,connect)) {
    	    		        System.out.println(count + " = Row not exist in DB with values = " + cellSource + " : " + cellDistination + " : " + cellDistance);
    	    		    	preparedStatement = connect.prepareStatement("insert into trash_bins."+tablename+" values (?, ?, ?)");
    	    		    	if(from_loc_id != -1 && to_loc_id != -1) {
    	    			        System.out.println("from_loc_id = " + from_loc_id);
    	    			        System.out.println("to_loc_id = " + to_loc_id);
    	    			    	preparedStatement.setInt(1,from_loc_id);
    	    			    	preparedStatement.setInt(2, to_loc_id);
    	    			    	preparedStatement.setString(3, cellDistance);
    	    			        try {
    	    			        	preparedStatement.executeUpdate();
    	    			        }
    	    			        catch (Exception e) {
    	    				        System.out.println("preparedStatement = " + e);
    	    			        }		    		
    	    		    	} else {
    	/*    		    		System.out.println("from_loc_id = " + cellSource + " = " +from_loc_id);
    	    			        System.out.println("to_loc_id = " + cellDistination + " = " +to_loc_id);*/
    	    		    	}                	
    	                } else {
    	    		        System.out.println(count + " = Row already exist in DB with values = " + cellSource + " : " + cellDistination + " : " + cellDistance);
    	                }
    	                count++;
                	} else {
                		break;
                	}
                }
            }
        }
        catch (Exception e) {
	        System.out.println("Getting values from row = " + count + " and file = "+fName);
        	e.printStackTrace();
        }
    }	

    static int getAddressId(String address, java.sql.Connection conn) {
        int loc_id = -1;
    	try {
           java.sql.Statement stmt = connect.createStatement();
            ResultSet rs;		 
            rs = stmt.executeQuery("SELECT * FROM trash_bins.locations WHERE address = '"+address+"'");
            while ( rs.next() ) {
            	loc_id = rs.getInt("loc_id");
            }
        } catch (Exception e) {
            System.err.println("Got an exception for address! "+address);
            System.err.println(e.getMessage());
        }
		return loc_id;
    }
    
    static boolean isEntryInDB(String tablename, int from_loc_id, int to_loc_id, String distance, java.sql.Connection conn) {
        boolean result = false;
    	try {
           java.sql.Statement stmt = connect.createStatement();
            ResultSet rs;		 
            String query = "SELECT from_loc_id FROM trash_bins."+tablename+" WHERE from_loc_id = "+from_loc_id+
            		" AND to_loc_id = "+to_loc_id+" AND distance_in_km = '"+distance+"'";
            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
            	if(rs.getInt("from_loc_id") > 0){
                	result = true;	
            	}
            }
        } catch (Exception e) {
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }
		return result;
    }
    
    // You need to close the resultSet
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //to be removed
    static void readExelDataAndInsertToDB22() { 
        // Setup the connection with the DB
        try {
			connect = DriverManager
			        .getConnection("jdbc:mysql://localhost/trash_bins?"
			                + "user=root&password=");
	        statement = connect.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
    	String[] myList = {"Stadlerstraﬂe 5 93053 Regensburg","Karl-Esser-Straﬂe 2 93049 Regensburg","An der Irler Hˆhe 38 93055 Regensburg","Hochweg 46 93049 Regensburg","Margaretenau 24 93049 Regensburg","Kager 7 93059 Regensburg","Riesengebirgstraﬂe 79 93057 Regensburg","Ernst-Reuter-Platz 2, 93047 Regensburg","Gr‰ﬂlstraﬂe 93059 Regensburg","David-Funk-Straﬂe 28 93055 Regensburg","Ziegetsdorfer Str. 24 93051 Regensburg","Auweg 21 93055 Regensburg","Irl 8 93055 Regensburg","Irl 19 93055 Regensburg","Sophie-Scholl-Straﬂe 78 93055 Regensburg"};
		for (String address : myList) {
			String fName = address;
	    	int start, end, count = 0;
	    	try {
	    		String filename = System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/"+fName+"2.xlsx";
	            File f = new File(filename);
	            if(f.exists() && !f.isDirectory()) { 
	        		FileInputStream file = new FileInputStream(new File(filename));
	                // Create Workbook instance holding reference to .xlsx file
	                XSSFWorkbook workbook = new XSSFWorkbook(file);
	                // Get first/desired sheet from the workbook
	                XSSFSheet sheet = workbook.getSheetAt(0); 
	                               
	                start = 1;
	                count = start;
	                end = 750;
	                Row row = sheet.getRow(1);
	                while(count <= end)
	                {
	                	row = sheet.getRow(count);
	                	if(row != null){
	    	                String cellSource = row.getCell(0).toString();
	    	                String cellDistination = row.getCell(1).toString(); 
	    	                String cellDistance = row.getCell(2).toString(); 
	    	                
	    	                
	    			    	int from_loc_id  = getAddressId(cellSource,connect);
	    			    	int to_loc_id  = getAddressId(cellDistination,connect);
	    			        System.out.println("cellSource = " + cellSource);
	    			        System.out.println("cellDistination = " + cellDistination);
	    	                if(!isEntryInDB("distances",from_loc_id,to_loc_id,cellDistance,connect)) {
	    	    		        System.out.println(count + " = Row not exist in DB with values = " + cellSource + " : " + cellDistination + " : " + cellDistance);
	    	    		    	preparedStatement = connect.prepareStatement("insert into trash_bins.distances values (?, ?, ?)");
	    	    		    	if(from_loc_id != -1 && to_loc_id != -1) {
	    	    			        System.out.println("from_loc_id = " + from_loc_id);
	    	    			        System.out.println("to_loc_id = " + to_loc_id);
	    	    			    	preparedStatement.setInt(1,from_loc_id);
	    	    			    	preparedStatement.setInt(2, to_loc_id);
	    	    			    	preparedStatement.setString(3, cellDistance);
	    	    			        try {
	    	    			        	preparedStatement.executeUpdate();
	    	    			        }
	    	    			        catch (Exception e) {
	    	    				        System.out.println("preparedStatement = " + e);
	    	    			        }		    		
	    	    			        
	    	    			        System.out.println("from_loc_id = " + from_loc_id);
	    	    			        System.out.println("to_loc_id = " + to_loc_id);
	    	    			    	preparedStatement.setInt(1, to_loc_id);
	    	    			    	preparedStatement.setInt(2,from_loc_id);
	    	    			    	preparedStatement.setString(3, cellDistance);
	    	    			        try {
	    	    			        	preparedStatement.executeUpdate();
	    	    			        }
	    	    			        catch (Exception e) {
	    	    				        System.out.println("preparedStatement = " + e);
	    	    			        }		    		
	    	    		    	} else {
	    	/*    		    		System.out.println("from_loc_id = " + cellSource + " = " +from_loc_id);
	    	    			        System.out.println("to_loc_id = " + cellDistination + " = " +to_loc_id);*/
	    	    		    	}                	
	    	                } else {
	    	    		        System.out.println(count + " = Row already exist in DB with values = " + cellSource + " : " + cellDistination + " : " + cellDistance);
	    	                }
	    	                count++;
	                	} else {
	                		break;
	                	}
	                }
	            }
	        }
	        catch (Exception e) {
		        System.out.println("Getting values from row = " + count + " and file = "+fName);
	        	e.printStackTrace();
	        }
		}
    }	
}