package sensores_temp_luz;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Migração Mongo
 * 
 * @author Eduardo
 *
 */
public class MongoMigration {


	MongoClient mongoClient;
	DBCollection collectionGeneric;
	DBCollection collectionSucess;
	DB db;
	//boolean isEmpty;

	@SuppressWarnings("deprecation")
	public MongoMigration() {
		mongoClient = new MongoClient(new 
		MongoClientURI("mongodb://Pedro:27017,Pedro:27018,Pedro:27019/?replicaSet=replicaDemo"));
		db = mongoClient.getDB("Sensores");
		collectionGeneric = db.getCollection("Medicoes");
		collectionSucess = db.getCollection("MedicoesExportadas");
		//isEmpty=true;
	}

	/**
	 * Run
	 * @param document 
	 */
	public synchronized void insertValuesMongo(BasicDBObject document) {

		try { 
			collectionGeneric.insert(document);  
			System.out.println("Insert success.");
		} catch (Exception e) {}
	}


	public synchronized DBCursor getValuesMongoMedicoes() {
		return collectionGeneric.find();

	}
	
	public synchronized void removeValuesMongo(BasicDBObject document) {
		
		try { 
			collectionGeneric.remove(document);  
			System.out.println("Remove success.");
		} catch (Exception e) {}
		
	}

	public synchronized void insertValuesMongoSucess(BasicDBObject exportedDocument) {
		try { 
			collectionSucess.insert(exportedDocument); 
			System.out.println("Insert success.");
		} catch (Exception e) {}
		
	}

//	public void notEmpty()	{
//		isEmpty=false;
//	}


}