package com.Game;

import com.jsoniter.annotation.JsonIgnore;

import java.util.ArrayList;

public class Cartella {


	private Integer[] numeri;
	@JsonIgnore
	private ArrayList<Integer> winningRow;
	@JsonIgnore
	private boolean validCard;

	public Cartella(ArrayList<Integer> numeri) {
		this.numeri = numeri.toArray(new Integer[0]);
		this.winningRow = new ArrayList<>();
		this.validCard = true;
	}


	public Integer[] getNumeri(){
		return numeri;
	}
	@JsonIgnore
	public boolean isValidWinningRow(int r){
		return !winningRow.contains(r);
	}
	@JsonIgnore
	public void addWinningRow(int r){
		winningRow.add(r);
	}
	@JsonIgnore
	public void setInvalid(){
		validCard = false;
	}
	@JsonIgnore
	public boolean isValidCard() {
		return validCard;
	}
}
