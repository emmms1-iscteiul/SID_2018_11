package sensores_temp_luz;



public class PahoWriteMessages extends Thread {

	private SincronizacaoPaho sinc;

	
	public PahoWriteMessages()	{
		sinc=new SincronizacaoPaho();
	}
	
	public void run() {
			sinc.write();
		
			
	}
}
