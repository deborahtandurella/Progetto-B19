package com.Game.controllers;

import com.Game.Cartella;

import java.util.ArrayList;

public class Player {

	private String username;
	private ArrayList<Cartella> cartelle;



	public Player(String username) {
		this.username = username;
		cartelle = new ArrayList<>();
	}

	public void addCartella(Cartella cartella) {
		cartelle.add(cartella);
	}

	public Cartella getCartella(int index) {
		return cartelle.get(index);
	}

	public String getUsername() {
		return username;
	}
}
