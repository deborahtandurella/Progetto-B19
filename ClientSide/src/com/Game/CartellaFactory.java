package com.Game;

import java.util.ArrayList;
import java.util.Random;

public class CartellaFactory {


	public static Cartella createCartella(){

		ArrayList<Integer> numeri = new ArrayList<>();


		ArrayList<Integer> tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);

		tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);

		tmpNum = createRow(numeri);
		numeri.addAll(tmpNum);


		Cartella c = new Cartella(numeri);
		return c;
	}

	private static ArrayList<Integer> createRow(ArrayList<Integer> oldNum){
		Random r = new Random();

		ArrayList<Integer> numeri = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

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

		int a = 0;

		while(a <5){
			int n = r.nextInt(numeri.size());
			numeri.set(n,0);
			a++;
		}

		return numeri;
	}

}
