package sensores_temp_luz;

import java.util.ArrayList;
import java.util.List;

public class Anomalias {

	private int medicoes[] = {21, 29, 21, 50, 50, 23, 27};
	private List<Integer> anomalias = new ArrayList<Integer>();

	private int contadorDiferencas = 0;
	private int contadorMedicoes = 0;

	public void algoritmo(int limiteS, int limiteI) {

		int dif = (limiteS - limiteI)/2;
		int i = 0;

		while (i<medicoes.length) {
			if (i == 0) {
				if ((medicoes[i] < 12) && (medicoes[i] > limiteI + (limiteI * 0.05))) {
					System.out.println("ATEN��O: A CHEGAR AO LIMITE INFERIOR");
				}
				if ((medicoes[i] <= limiteI + (limiteI * 0.95))) {
					System.out.println("ATEN��O: EM ESTADO CR�TICO, ATINGIU O LIMITE INFERIOR");
				}
				if ((medicoes[i] <= (limiteS * 0.95)) && (medicoes[i] > 26)) {
					System.out.println("ATEN��O: A CHEGAR AO LIMITE SUPERIOR");
				}
				if ((medicoes[i] > limiteS * 0.95)) {
					System.out.println("ATEN��O: EM ESTADO CR�TICO, ATINGIU O LIMITE SUPERIOR");
				}
				anomalias.add(i,0);
				System.out.println("0� Anomalia: " + anomalias.get(i));
				i++;
			} else {
				anomalias.add(i,0);
				int difer = Math.abs(medicoes[i] - medicoes[i-1]);
				System.out.println("Diferen�a: " + difer);
				if (contadorMedicoes < 3) {
					if (contadorDiferencas >= 1) {
						contadorMedicoes++;
						System.out.println("1� IF - Contador Medi��es: " + contadorMedicoes);
					}
					if (difer >= dif) {
						if (contadorDiferencas == 0) {
							contadorMedicoes++;
							System.out.println("2� IF - Contador Medi��es: " + contadorMedicoes);
						}
						contadorDiferencas++;
						System.out.println("Contador Diferen�a: " + contadorDiferencas);
						if (contadorDiferencas == 2) {
							anomalias.add(i, medicoes[i-1]);
							System.out.println(anomalias.get(i));
							System.out.println("Anomalia - " + medicoes[i-1]);
							contadorDiferencas = 0;
							contadorMedicoes = 0;
						}
					}
				} 
				if (contadorMedicoes == 0 ) {
					System.out.println(medicoes[i]);
					if (anomalias.get(i).intValue() == 0) {
						if ((medicoes[i] < 12) && (medicoes[i] > limiteI + (limiteI * 0.05))) {
							System.out.println("ATEN��O: A CHEGAR AO LIMITE INFERIOR");
						}
						if ((medicoes[i] <= limiteI + (limiteI * 0.95))) {
							System.out.println("ATEN��O: EM ESTADO CR�TICO, ATINGIU O LIMITE INFERIOR");
						}
						if ((medicoes[i] <= (limiteS * 0.95)) && (medicoes[i] > 26)) {
							System.out.println("ATEN��O: A CHEGAR AO LIMITE SUPERIOR");
						}
						if ((medicoes[i] > limiteS * 0.95)) {
							System.out.println("ATEN��O: EM ESTADO CR�TICO, ATINGIU O LIMITE SUPERIOR");
						}
					}
				}
				System.out.println(i + " Anomalia: " + anomalias.get(i));
				i++;
			}
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anomalias a = new Anomalias();
		a.algoritmo(30,10);
	}

}
