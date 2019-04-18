package com.Game;

import java.util.ArrayList;

public class Cartella {

	private Integer[] numeri;
	private ArrayList<Integer> winningRow;
	private boolean validCard;

	public Cartella(ArrayList<Integer> numeri) {
		this.numeri = numeri.toArray(new Integer[0]);
		this.winningRow = new ArrayList<>();
		this.validCard = true;
	}

	public Integer[] getNumeri(){
		return numeri;
	}

	public boolean isValidWinningRow(int r){
		return !winningRow.contains(r);
	}

	public void addWinningRow(int r){
		winningRow.add(r);
	}

	public void setInvalid(){
		validCard = false;
	}

	public boolean isValidCard() {
		return validCard;
	}
}
