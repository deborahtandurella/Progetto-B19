package com.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class that manages the cards and extracts the cards
 */
public class Tomboliere {

	//ArrayList of numbers that have been extracted
	private ArrayList<Integer> numeriUsciti;

	//Variable that generate casual numbers
	private Random r;

	//Arraylist of the winnings
	private ArrayList<CallEnum> wins;

	/**
	 * Constructor of the class Tomboliere
	 */
	public Tomboliere() {

		numeriUsciti = new ArrayList<>();
		r = new Random();
		wins = new ArrayList<>();
	}

	/**
	 * Number extractor
	 *
	 * @return an extracted number
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

	/**
	 * Get an ArrayList of extracted numbers
	 */
	public synchronized ArrayList<Integer> getExtractions(){
		return numeriUsciti;
	}

	/**
	 * Generator of a new card
	 *
	 * @return a new card
	 */

	public Cartella getCartella() {
		return CartellaFactory.createCartella();
	}

	/**
	 * It is called when is necessary to control the call made by a player
	 *
	 * @param callType type of call made by a player
	 * @param cartella the card of the player
	 * @return -1, if every type of call is wrong;
	 * 			i, if one of the call: AMBO,TERNA,QUATERNA e CINQUINA is correct;
	 * 			15, if the call TOMBOLA is correct.
	 */

	public synchronized int checkCall(CallEnum callType, Cartella cartella) {


		if(wins.contains(callType)) return -1;

		//Control if the card is valid (is valid when the player didn't make TOMBOLA)
		if(cartella.isValidCard()){

			//ArrayList<Integer> numeriUsciti= getSubExtractions(LN);
			//Obtain the numbers of a card
			Integer[] numeri = cartella.getNumeri();

			//Get how many numbers have to match for the call (eg AMBO then they are 2)
			int matchN = getNumberForCallType(callType);

			//Counter of matched numbers
			int currN = 0;

			//Control of AMBO,TERNA. QUATERNA, CINQUINA
			for (int i = 0; i < 3; i++) {

				//Save the numbers line by line
				Integer[] tmpNum = Arrays.copyOfRange(numeri, 9 * i, 9 * i + 9);

				currN = 0;

				//Control line by line
				for (int j = 0; j < tmpNum.length; j++) {

					//if there is a number not equal to 0 and it is in the extracted numbers, so increase by 1 the counter of matched numbers
					if (tmpNum[j] != 0) {
						if (numeriUsciti.contains(tmpNum[j])) {
							currN++;
						}
					}
				}

				//If are matched as many numbers as those that are needed for the call and in the same line I have not already had a win
				//so add the winning line to the card and return the index of the line
				if (currN >= matchN && cartella.isValidWinningRow(i)) {
					cartella.addWinningRow(i);
					wins.add(callType);
					return i;
				}

			}
		}

		//Control if a player made TOMBOLA
		if(callType==CallEnum.TOMBOLA) {
			int currN = 0;

			Integer[] numeri = cartella.getNumeri();

			//cycle all the numbers of a card
			for (int i = 0; i < 27; i++) {
				if (numeriUsciti.contains(numeri[i]) && numeri[i] != 0) {
					currN++;
				}
			}

			//If all numbers are matched, so return 15 and the card becomes invalid
			if (currN >= 15) {
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
