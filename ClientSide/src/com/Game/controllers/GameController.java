package com.Game.controllers;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.Tomboliere;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {

	private Player p;
	private Tomboliere t;
	private Thread estrattore;
	private int n;

	public GameController(String playerName, int n) {

		p = new Player(playerName);
		t = new Tomboliere();
		for (int i = 0; i < n; i++) {
			p.addCartella(t.getCartella());
		}

		this.n = n;




	}

	public void startExtraction(Runnable updateFunction) {
		estrattore = new Thread(() -> {
			while (true){

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


				int numero = estraiNumero();
				updateFunction.run();
			}
		});

		estrattore.start();
	}

	public void stopExtractions(){
		estrattore.interrupt();
		estrattore.stop();
	}


	public Cartella getCartella(int index) {
		return p.getCartella(index);
	}
	public Integer[] getCartellaAsArray(int index) {return p.getCartella(index).getNumeri();}

	public boolean buttonControl(CallEnum callEnum, Cartella cartella) {
		return t.checkCall(callEnum,cartella);
	}

	public Integer estraiNumero()  {
		return t.getNumber();
	}

	public ArrayList<Integer> getExtractions() {



		return t.getExtractions();
	}

	public int getCartellaCount() {
		return n;
	}
}
