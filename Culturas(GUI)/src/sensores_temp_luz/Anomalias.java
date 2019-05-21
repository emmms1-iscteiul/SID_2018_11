package sensores_temp_luz;

/**
 * Algoritmo deteção de anomalias
 * @author Eduardo
 *
 */
public class Anomalias {

	private int medicoes[] = {21, 29, 21, -1, -2, 23, 27};

	private int contadorDiferencas = 0;
	private int contadorMedicoes = 0;
	/**
	 * Algoritmo
	 * @param limiteS
	 * @param limiteI
	 */
	public void algoritmo(int limiteS, int limiteI) {

		int dif = (limiteS - limiteI)/2;
		int i = 0;

		while (i<medicoes.length) {
			if (i == 0) {
				if ((medicoes[i] < 12) && (medicoes[i] > limiteI + (limiteI * 0.05))) {
					System.out.println("ATENÇÃO: A CHEGAR AO LIMITE INFERIOR");
				}
				if ((medicoes[i] <= limiteI + (limiteI * 0.95))) {
					System.out.println("ATENÇÃO: EM ESTADO CRÍTICO, ATINGIU O LIMITE INFERIOR");
				}
				if ((medicoes[i] <= (limiteS * 0.95)) && (medicoes[i] > 26)) {
					System.out.println("ATENÇÃO: A CHEGAR AO LIMITE SUPERIOR");
				}
				if ((medicoes[i] > limiteS * 0.95)) {
					System.out.println("ATENÇÃO: EM ESTADO CRÍTICO, ATINGIU O LIMITE SUPERIOR");
				}
				i++;
			} else {
				int difer = Math.abs(medicoes[i] - medicoes[i-1]);
				if (contadorMedicoes < 3) {
					if (contadorDiferencas >= 1) {
						contadorMedicoes++;
					}
					if (difer >= dif) {
						if (contadorDiferencas == 0) {
							contadorMedicoes++;
						}
						contadorDiferencas++;
						if (contadorDiferencas == 2) {
							System.out.println("Anomalia - " + medicoes[i-1]);
							contadorDiferencas = 0;
							contadorMedicoes = 0;
						}
					}
				} 
				if (contadorMedicoes == 0 || contadorMedicoes == 3) {
					contadorMedicoes = 0;
					contadorDiferencas = 0;
					System.out.println(medicoes[i]);
					if ((medicoes[i] < 12) && (medicoes[i] > limiteI + (limiteI * 0.05))) {
						System.out.println("ATENÇÃO: A CHEGAR AO LIMITE INFERIOR");
					}
					if ((medicoes[i] <= limiteI + (limiteI * 0.95))) {
						System.out.println("ATENÇÃO: EM ESTADO CRÍTICO, ATINGIU O LIMITE INFERIOR");
					}
					if ((medicoes[i] <= (limiteS * 0.95)) && (medicoes[i] > 26)) {
						System.out.println("ATENÇÃO: A CHEGAR AO LIMITE SUPERIOR");
					}
					if ((medicoes[i] > limiteS * 0.95)) {
						System.out.println("ATENÇÃO: EM ESTADO CRÍTICO, ATINGIU O LIMITE SUPERIOR");
					}

				}
				i++;
			}
		}
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anomalias a = new Anomalias();
		a.algoritmo(30,10);
	}

}
