package sensores_temp_luz;

public class ServidorPaho {
	
	static PahoReadMessages read;
	static PahoWriteMessages write;
	

	public static void main(String[] args) {
		read = new PahoReadMessages();
		write = new PahoWriteMessages();
		
		read.start();
		write.start();
		
	}
}
