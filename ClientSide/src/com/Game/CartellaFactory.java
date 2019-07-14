package com.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generate rows and columns of a table and assign the numbers
 */
public class CartellaFactory {

	/**
	 * Create the card
	 *
	 * @return c the created card
	 */
	public static Cartella createCartella(){

		//Numbers of a card
		ArrayList<Integer> numeri = new ArrayList<>();

		//Create the first row of the card
		ArrayList<Integer> tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);

		//Create the second row of the card
		tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);

		//Create the third row of the card
		tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);

		//Create a card and assign the numbers
		Cartella c = new Cartella(numeri);
		return c;
	}

	/**
	 * Set the row of a table
	 *
	 * @param oldNum numbers that have been assigned previously to a card
	 *
	 * @return numeri numbers in the row
	 */
	private static ArrayList<Integer> createRow(ArrayList<Integer> oldNum){

		//Random number generator
		Random r = new Random();

		//Numbers in the row
		ArrayList<Integer> numeri = new ArrayList<>();

		//Assign the numbers to the arraylist
		for (int i = 0; i < 9; i++) {

			int n;

			while(true) {
				if (i == 0) {
					n = r.nextInt(8) + 1;
				} else {
					n = r.nextInt(10) + i * 10;
				}

				if(!oldNum.contains(n)){
					break;
				}
			}

			numeri.add(n);
		}

		//Counter
		int a = 0;

		//Assign the numbers to the row
		while(a <4){
			int n = r.nextInt(numeri.size());
			if(numeri.get(n) != 0) {
				numeri.set(n, 0);
				a++;
			}
		}

		return numeri;
	}

}
