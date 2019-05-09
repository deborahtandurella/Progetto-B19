package com.Game.controllers;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.Tomboliere;

public class GameController {

	private Player p;
	private Tomboliere t;

	public GameController(String playerName) {

		p = new Player(playerName);
		t = new Tomboliere();
		p.addCartella(t.getCartella());
	}


	public Integer[] getCartella() {
		return p.getCartella().getNumeri();
	}

	public boolean buttonControl(CallEnum callEnum, Cartella cartella) {
		return t.checkCall(callEnum,cartella);
	}

	public Integer estraiNumero()  {
		return t.getNumber();
	}

}
