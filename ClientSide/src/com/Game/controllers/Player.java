package com.Game.controllers;

import com.Game.Cartella;

import java.util.ArrayList;

/**
 * A player that plays Tombola
 */
public class Player {

	//Name of the player
	private String username;

	//Arraylist of the cards of a player
	private ArrayList<Cartella> cartelle;

	/**
	 * Constructor of the class Player
	 *
	 * @param username name of the player
	 */
	public Player(String username) {
		this.username = username;
		cartelle = new ArrayList<>();
	}

	/**
	 * Assign a card
	 *
	 * @param cartella card that we want to add
	 */
	public void addCartella(Cartella cartella) {
		cartelle.add(cartella);
	}

	/**
	 *Get a card
	 *
	 * @param index position of the card in the Arraylist
	 * @return a card in the Arraylist
	 */
	public Cartella getCartella(int index) {
		return cartelle.get(index);
	}

	/**
	 * Get the usarname of a player
	 *
	 * @return username of a player
	 */
	public String getUsername() {
		return username;
	}
}
