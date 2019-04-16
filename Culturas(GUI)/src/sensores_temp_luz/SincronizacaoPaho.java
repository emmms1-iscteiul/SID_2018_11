package sensores_temp_luz;

public class SincronizacaoPaho {

	private boolean pahoW;
	private boolean pahoR;
	
	
	
	public SincronizacaoPaho() {
		this.pahoW = false;
		this.pahoR = true;
	}
	
	public boolean isPahoW() {
		return pahoW;
	}

	public boolean isPahoR() {
		return pahoR;
	}

	public void write()	{
		pahoW=true;
		pahoR=false;
	}
	
	public void read()	{
		pahoW=false;
		pahoR=true;
		
	}

	public synchronized void sincronizarWrite() {
		
		while (isPahoW()) {
			try {
				System.out.println("write está wait");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//notifyAll();
		
	}
	
	public synchronized void sincronizarRead() {
		
		while (isPahoR()) {
			try {
				System.out.println("read está wait");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//notifyAll();
		
	}
	
}
