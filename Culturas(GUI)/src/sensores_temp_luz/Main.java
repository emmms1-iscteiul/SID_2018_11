package sensores_temp_luz;

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
		MongoMigration mongoM = new MongoMigration();
		MySemaphore semaphore=new MySemaphore();
		PahoReader pahoR = new PahoReader(mongoM,semaphore);
		MySQLMigration mySQL = new MySQLMigration(mongoM,semaphore);
//		
		pahoR.start();
		mySQL.start();		
	}

}
