package com.Game;

import java.util.ArrayList;

public class Cartella {

	private Integer[] numeri;

	public Cartella(ArrayList<Integer> numeri) {
		this.numeri = numeri.toArray(new Integer[0]);
	}

	public Integer[] getNumeri(){
		return numeri;
	}

}
