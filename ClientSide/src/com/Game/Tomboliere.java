package com.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tomboliere {

	private ArrayList<Integer> numeriUsciti;
	private Random r;

	public Tomboliere() {

		numeriUsciti = new ArrayList<>();
		r = new Random();
	}

	/**
	 * Estrattore numeri
	 * @return ritorna un numero estratto
	 */
	public synchronized Integer getNumber(){
		int n;
		while (true){
			n = r.nextInt(90)+1;
			if(!numeriUsciti.contains(n)){
				numeriUsciti.add(n);
				break;
			}
		}

		return n;
	}

	public synchronized ArrayList<Integer> getExtractions(){
		return numeriUsciti;
	}

	/**
	 * Generatore nuova cartella
	 * @return ritorna una nuova cartella
	 */

	public Cartella getCartella() {
		return CartellaFactory.createCartella();
	}

	/**
	 * Metodo che controlla la chiamata
	 * @param callType tipo di chiamata (vedi CallEnum)
	 * @return ritorna true se la chiamata è corretta altrimenti ritorna false;
	 */

	public synchronized int checkCall(CallEnum callType, Cartella cartella, int LN) {
		//Controllo se la cartella è valida (ovvero non ho fatto tombola)
		if(cartella.isValidCard()){

			ArrayList<Integer> numeriUsciti= getSubExtractions(LN);
			//Ottengo i numeri
			Integer[] numeri = cartella.getNumeri();

			//Ottengo quanti numeri devono matchare per la chiamata (es. AMBO allora sono 2)
			int matchN = getNumberForCallType(callType);

			//Tengo traccia di quanti ne ho matchati
			int currN;

			//Controllo ambo,terna,quaterna,cinquina
			for (int i = 0; i < 3; i++) {

				//Giro riga per riga
				Integer[] tmpNum = Arrays.copyOfRange(numeri, 9 * i, 9 * i + 9);

				//Azzero numeri matchati
				currN = 0;


				//Controllo riga per riga
				for (int j = 0; j < tmpNum.length; j++) {

					//Se ho un numero che non è zero e che è contenuto nei numeri usciti, allora aggiungo 1 ai numeri matchati
					if (tmpNum[j] != 0) {
						if (numeriUsciti.contains(tmpNum)) {
							currN++;
						}
					}
				}

				//Se ho matchato tanti numeri quanti sono quelli necessari per la chiamata e nella stessa riga non ho già avuto una vincita
				if (currN >= matchN && cartella.isValidWinningRow(i)) {
					//Allora aggiungo la riga vincente e ritorno true
					cartella.addWinningRow(i);
					return i;
				}

			}

			//Controlla tombola
			currN = 0;
			//Giro tutti i numeri
			for (int i = 0; i < 27; i++) {
				//Se numeriUsciti contiene il numero della cartella ed è diverso da zero
				if (numeriUsciti.contains(numeri[i]) && numeri[i] != 0) {
					//Allora incremento contatore
					currN++;
				}
			}
			//Se ho matchato tutti i numeri allora ritorno true e setto la cartella come non valida
			if (currN >= matchN) {
				cartella.setInvalid();
				return 15;
			}
		}
		return -1;
	}

	private ArrayList<Integer> getSubExtractions(int LN) {
		ArrayList<Integer> red=new ArrayList<>();

		for(int i=0;i<numeriUsciti.size();i++){
			if(numeriUsciti.get(i)==LN){
				return red;
			}
			red.add(numeriUsciti.get(i));
		}
		return red;
	}

	//Membro interno che ritorna il numero di match necessari per il tipo di chaimata
	private int getNumberForCallType(CallEnum callType) {
		switch (callType){
			case AMBO:
				return 2;
			case TERNA:
				return 3;
			case QUATERNA:
				return 4;
			case CINQUINA:
				return 5;
			case TOMBOLA:
				return 15;
		}
		return -1;
	}


}
