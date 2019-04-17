package com.Game;

import java.util.ArrayList;
import java.util.Random;

public class CartellaFactory {


	public static Cartella createCartella(){

		ArrayList<Integer> numeri = new ArrayList<>();

		while (numeri.size()<27){

			ArrayList<Integer> tmpNum = createRow();

			boolean flag = false;

			for (int i = 0; i < tmpNum.size(); i++) {
				if(numeri.contains(tmpNum.get(i)) && tmpNum.get(i) != 0){
					flag = true;
					break;
				}
			}

			if(!flag){
				//Numeri vanno bene
				numeri.addAll(tmpNum);
			}

		}


		Cartella c = new Cartella(numeri);
		return c;
	}

	private static ArrayList<Integer> createRow(){
		Random r = new Random();

		ArrayList<Integer> numeri = new ArrayList<>();

		for (int i = 1; i < 10; i++) {
			int n = r.nextInt(10*i)+(10*i-9);
			numeri.add(n);
		}

		ArrayList<Integer> indici = new ArrayList<>();
		int a = 0;


		while(a <4){
			int n = r.nextInt(numeri.size());
			if(!indici.contains(n)){
				a++;
				numeri.set(n,0);
				indici.add(n);
			}
		}

		return numeri;
	}

}
