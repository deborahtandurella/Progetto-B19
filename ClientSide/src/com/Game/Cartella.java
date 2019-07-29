package com.Game;

import com.jsoniter.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Represents a generic card
 */
public class Cartella {

	//Numbers in a card
	private Integer[] numeri;

	//Row of a card that when there has been a winning
	@JsonIgnore
	private ArrayList<Integer> winningRow;

	//Show is the card is valid or not
	@JsonIgnore
	private boolean validCard;

	/**
	 * Constructor of the class Cartella
	 *
	 * @param numeri numbers in a card
	 */
	public Cartella(ArrayList<Integer> numeri) {
		this.numeri = numeri.toArray(new Integer[0]);
		this.winningRow = new ArrayList<>();
		this.validCard = true;
	}

	/**
	 * Get the numbers of a card
	 *
	 * @return numbers of a card
	 */
	public Integer[] getNumeri(){
		return numeri;
	}

	/**
	 * Test if a card has a winnin row
	 *
	 * @param r number of the row
	 * @return true, if in the row that we are controlling there are no winnings
	 * 		   false, in other cases
	 */
	@JsonIgnore
	public boolean isValidWinningRow(int r){
		return !winningRow.contains(r);
	}

	/**
	 * Add a row to the winnings rows
	 *
	 * @param r number of the row
	 */
	@JsonIgnore
	public void addWinningRow(int r){
		winningRow.add(r);
	}

	/**
	 * Make invalid  a card (the player can't use it to play anymore)
	 */
	@JsonIgnore
	public void setInvalid(){
		validCard = false;
	}

	/**
	 * Make valid a card (the player can use it to play)
	 *
	 * @return make valid a card
	 */
	@JsonIgnore
	public boolean isValidCard() {
		return validCard;
	}
}
