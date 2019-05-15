package sensores_temp_luz;

import com.mongodb.BasicDBObject;

/**
 * Main
 * @author Eduardo
 *
 */
public class Main {
	
	
/**
 * Main
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyBlockingQueue<BasicDBObject> queue = new MyBlockingQueue<BasicDBObject>();
		PahoReader pahoR = new PahoReader(queue);
		MongoMigration mongoM = new MongoMigration(queue);
		
		
		
		pahoR.start();
		mongoM.start();
	}

}
