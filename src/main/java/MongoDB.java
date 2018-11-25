
import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;  

public class MongoDB { 
   
   public static void ConnectToDB() {  
      
      // Creating a Mongo client 
      MongoClient mongo = null;
      MongoClient mongoClient = null;
	try {
		mongo = new MongoClient( "localhost" , 27017 );
	    mongoClient = new MongoClient();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    
     
	// Creating Credentials 
      DB db = mongoClient.getDB("TrashCollector");
      boolean auth = db.authenticate("username", "password".toCharArray());
	  
      System.out.println("Connected to the database successfully");  
      
      List<String> dbs = mongo.getDatabaseNames();
	  	for(String dbb : dbs){
	  		System.out.println(db);
	  	}
   } 
}