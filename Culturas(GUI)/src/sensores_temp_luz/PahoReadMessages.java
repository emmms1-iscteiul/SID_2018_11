package sensores_temp_luz;




public class PahoReadMessages  extends Thread {

	private SincronizacaoPaho sinc;
	
	public PahoReadMessages()	{
		sinc=new SincronizacaoPaho();
	}
	
	public void run() {
		while(true)	{
			try {
				sleep(2000);
				
				sinc.read();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Error Reading in PaohRead");
			}
			
		}

	}

	}
