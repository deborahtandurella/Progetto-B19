package com.logic;

/**
 * Questo enum gestisce lo stato del giocatore, IDLE significa che il giocatore è connesso ma non ha inviato un username
 * mentre CONNECTED significa che il giocatore è connesso e ha un username quindi è pronto per giocare
 */
public enum PlayerState {

	IDLE,
	CONNECTED

}
