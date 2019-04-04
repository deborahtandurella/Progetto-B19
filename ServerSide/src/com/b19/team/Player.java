package com.b19.team;

/**
 * Questa classe gestisce l'entità del giocatore unificando la parte logica con quella "fisica" del collegamento
 */
public class Player {

	private TunnelClient physicalLink;
	private String username;
	private PlayerState state;

	/**
	 * Costruttore della classe
	 * @param physicalLink il tunnel client corrispondente per il giocatore
	 */
	public Player(TunnelClient physicalLink) {
		this.physicalLink = physicalLink;

		this.physicalLink.start();
		state = PlayerState.IDLE;
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
	public void setUsername(String username) {
		this.username = username;
		state = PlayerState.CONNECTED;
		if(!Server.addReadyPlayer()){
			//Il server ha rifiutato il passaggio di stato di questo player quindi ne chiude la connessione
			physicalLink.close();
		}
	}

	/**
	 * Metodo getter dello stato
	 * @return ritorna PlayerState corrispondente allo stato del player
	 */
	public PlayerState getState() {
		return state;
	}

	/**
	 * Metodo getter del tunnerl
	 * @return ritorna il tunnel client corrispettivo
	 */
	public TunnelClient getPhysicalLink() {
		return physicalLink;
	}
}
