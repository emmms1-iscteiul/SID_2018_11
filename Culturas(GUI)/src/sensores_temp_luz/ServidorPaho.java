package sensores_temp_luz;

public class ServidorPaho {

	public static void main(String[] args) {
		Thread read = new PahoReadMessages();
		Thread write = new PahoWriteMessages();
		
		read.start();
		for (int i = 0; i<10; i++) {
			write.start();
		}
		
	}
}
