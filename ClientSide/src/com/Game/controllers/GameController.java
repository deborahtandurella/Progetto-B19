package com.Game.controllers;

import com.Game.Tomboliere;

public class GameController {

	private Player p;
	private Tomboliere t;

	public GameController(String playerName) {

		p = new Player(playerName);
		t = new Tomboliere();
		p.addCartella(t.getCartella());
	}



}
