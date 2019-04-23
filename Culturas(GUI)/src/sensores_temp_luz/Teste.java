package sensores_temp_luz;

public class Teste {
	public double[] valores = new double[]{15,15.3,15.6,16,16,18,18,32,18,20,40,40,40,40,20,20,20,24,25,26,27,28,29,30,31,32,33,34,15,15,15,15,15,15,14,15,30,32,15,15,15,15,5,5,10,12,12,12,12,12,12,12,-10,12,12,12,12};
	public double[] valoresNew=new double[valores.length];

	public void putValoresNew(){
		for(int i = 0; i<valores.length; i++){
			if(i == 0){
				valoresNew[i] = valores[i];
			}else{
				valoresNew[i] = (valores[i] + valoresNew[i-1])/2;
			}
		}
	}	

	public void media() {
		for(int i = 0; i < valoresNew.length; i++) {
			System.out.println(i+1 + " - " + valoresNew[i]);
		}
	}

	public void diferença()	{
		double r;
		for(int i=0;i<valoresNew.length;i++)	{
			if(i>0){
				r=valoresNew[i]-valoresNew[i-1];
				System.out.println("R: "+Math.abs(r));
				if(Math.abs(r)>3)	{
					System.out.println("ALERTA!");
				}
			}

		}
	}

}
