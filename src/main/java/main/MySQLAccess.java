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

    public static void writeallAddressesToSQLDB() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/trash_bins_addresses?"
                            + "user=root&password=");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // PreparedStatements can use variables and are more efficient

    		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
            File[] listOfFiles = folder.listFiles();
    		for (File file : listOfFiles) {
    		    if (file.isFile()) {
    		    	String address = file.getName().replaceAll(".xlsx", "");
    	            preparedStatement = connect.prepareStatement("insert into  trash_bins_addresses.alladdresses values (default, ?)");
    		    	preparedStatement.setString(1, address);
    	            preparedStatement.executeUpdate();
    		    }
    		}
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    
    public static void writeAddressesToSQLDB() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/trash_bins_addresses?"
                            + "user=root&password=");            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // PreparedStatements can use variables and are more efficient
    		File folder = new File(System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/");
            File[] listOfFiles = folder.listFiles();
            int counter = 1;
    		for (File file : listOfFiles) {
    		    if (file.isFile()) {
    		    	String fileName = file.getName().replaceAll(".xlsx", "");
    		    	String tableName = "address_"+counter;
    		    	boolean isTablecreated = createTable(tableName,connect);
    		    	if(isTablecreated) {
    		    		readExelDataAndInsertToDB(fileName, tableName, counter, connect);
    		    	}	
    		    	counter++;
    		    }
    		}
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    
    private static boolean createTable(String tableName, java.sql.Connection conn) throws SQLException {
    			boolean result = false;
    			java.sql.DatabaseMetaData dbm = conn.getMetaData();
    			// check if "employee" table is there
    			ResultSet tables = dbm.getTables(null, null, tableName, null);
    			if (tables.next()) {
    				result = false;
    		        System.out.println("Table already exists " + tableName);
    			}
    			else {
    		        System.out.println("Table does not exists, creating new " + tableName);
    		    	String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName
    		                + "(id INT NOT NULL AUTO_INCREMENT,"
    		                + "pid INT,"
    		                + "source VARCHAR(50),"
    		                + "distination VARCHAR(50) UNIQUE,"
    		                + "distance_in_km VARCHAR(20),"
    		    			+ "PRIMARY KEY(id),"
    		        		+ "FOREIGN KEY (pid) REFERENCES alladdresses(id))";
    		        java.sql.Statement stmt = conn.createStatement();
    		        stmt.executeUpdate(sqlCreate);
    		        result = true;
    			}
    			return result;
    }

    static void readExelDataAndInsertToDB(String fName, String tablename, int counter, java.sql.Connection conn) {
    	try {
    		String filename = System.getProperty("user.dir") + "/data/gmap_distances/TourSheet_Complete/"+fName+".xlsx";
            FileInputStream file = new FileInputStream(new File(filename));
    	
            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0); 
            int start, end, count;
                
            start = 2;
            count = start;
            end = 750;
            Row row = sheet.getRow(2);
            while(count <= end)
            {
            	row = sheet.getRow(count);
                String cellSource = row.getCell(0).getStringCellValue();
                String cellDistination = row.getCell(1).getStringCellValue(); 
                String cellDistance = row.getCell(2).getStringCellValue(); 
                
		    	preparedStatement = connect.prepareStatement("insert into  trash_bins_addresses."+tablename+" values (default, ?, ?, ?, ?)");
		    	preparedStatement.setInt(1, counter);
		    	preparedStatement.setString(2, cellSource);
		    	preparedStatement.setString(3, cellDistination);
		    	preparedStatement.setString(4, cellDistance);
		        System.out.println("preparedStatement = " + preparedStatement);
		        try {
		        	preparedStatement.executeUpdate();
		        }
		        catch (Exception e) {
			        System.out.println("preparedStatement = " + e);
		        }
		        count++;
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
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

        }
    }

}