package com.logic;

import com.physical.Server;
import com.physical.TunnelClient;

import java.util.ArrayList;

/**
 * Questa classe gestisce l'entità del giocatore unificando la parte logica con quella "fisica" del collegamento
 */
public class Player {

	private String username;
	private PlayerState state;
	private ArrayList<Cartella> cartelle;
	private int n;

	/**
	 * Costruttore della classe
	 */
	public Player() {
		cartelle = new ArrayList<>();
		state = PlayerState.IDLE;
		username = null;
	}

	/**
	 * Metodo getter dell'username
	 * @return username del Player
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Metodo setter dell'username
	 * @param username username da settare
	 */
	public boolean setUsername(String username) {
		this.username = username;
		state = PlayerState.CONNECTED;
		if(!Server.addReadyPlayer()){
			//Il server ha rifiutato il passaggio di stato di questo player quindi ne chiude la connessione
			return false;
		}
		return true;
	}

	/**
	 * Metodo getter dello stato
	 * @return ritorna PlayerState corrispondente allo stato del player
	 */
	public PlayerState getState() {
		return state;
	}

	/**
	 * Metodo che aggiunge una cartella nella lista di cartelle
	 * @param c cartella da aggiungere
	 */
	public void addCartella(Cartella c) {
		cartelle.add(c);
	}

	/**
	 * Setter per il numero di cartelle.
	 * @param n numero di cartelle
	 */
	public void setNumeroCartelle(int n) {
		this.n = n;
	}

	/**
	 * Metodo getter numero cartelle
	 * @return n numero di cartelle
	 */
	public int getNumeroCartelle() {
		return n;
	}

	/**
	 * Ritorna la cartella in formato stringa csv
	 * @param n il numero della cartella
	 * @return la stringa in formato csv della cartella
	 */
	public String CartellaToString(int n) {
		return cartelle.get(n).toString();
	}
}
