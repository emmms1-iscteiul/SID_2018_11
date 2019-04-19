package sensores_temp_luz;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoRead extends Thread {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		String string = "{\"tmp\":\"22.40\",\"hum\":\"61.30\",\"dat\":\"9/4/2019\",\"tim\":\"14:59:32\",\"cell\":\"10008\"\"sens\":\"wifi\"}";
		
		String[] stringSplitted = string.split(",");
		//System.out.println(stringSplitted[4].length());
		
	}

	public void run() {

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
