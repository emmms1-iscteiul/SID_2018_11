package sensores_temp_luz;

import java.util.concurrent.Semaphore;

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
		//MyBlockingQueue<BasicDBObject> queue = new MyBlockingQueue<BasicDBObject>();
		Semaphore sem = new Semaphore(1);
		PahoReader pahoR = new PahoReader(sem);
		MongoMigration mongoM = new MongoMigration(sem);
		MySQLMigration mySQLM = new MySQLMigration(sem);
		
		pahoR.start();
		mongoM.start();
		mySQLM.start();
		
	}

}
