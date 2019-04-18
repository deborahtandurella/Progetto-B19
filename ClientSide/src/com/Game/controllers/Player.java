package com.Game.controllers;

import com.Game.Cartella;

public class Player {

	private String username;
	private Cartella c;



	public Player(String username) {
		this.username = username;

	}

	public void addCartella(Cartella cartella) {
		c = cartella;
	}

	public Cartella getCartella() {
		return c;
	}

	public String getUsername() {
		return username;
	}
}
