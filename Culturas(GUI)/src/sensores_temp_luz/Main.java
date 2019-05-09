package sensores_temp_luz;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PahoReader pahoR = new PahoReader();
		MongoMigration mongoM = new MongoMigration();
		
		pahoR.start();
		mongoM.start();
	}

}
