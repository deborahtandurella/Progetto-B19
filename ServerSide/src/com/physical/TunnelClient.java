package com.physical;

import com.logic.Player;
import com.logic.Tomboliere;

import java.io.*;
import java.net.Socket;


/**
 * Classe che gestisce il tunnel privato TCP di connessione con il client
 * @see Thread per maggiori dettagli
 */

public class TunnelClient extends Thread {
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private Player player;


	/**
	 * Costruttore per crare un tunnel di flussi privato tra client e server
	 */
	public TunnelClient(Socket client, Player player) {
		super();
		//Inizializzo client dell'istanza con il client passato come parametro
		this.client = client;
		this.player = player;
		in = null;
		out = null;


	}

	@Override
	public void run() {
		try {
			//Inizializzo flusso di dati in input partendo dal flusso di input del socket
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//Inizializzo flusso di dati in output partendo dal flusso di output del socket
			out = new PrintWriter(client.getOutputStream());


			//System.out.println("Sto scrivendo");
		}catch (Exception e){
			//Se ho eccezione stampo messaggio sul system error
			System.err.println(e.getMessage());
		}

		//Ciclo di polling dell'input
		while (true){
			try	{
				//Ricevo pacchetto e lo gestisco
				receivePacket(in.readLine());
			}catch (Exception e){
				System.err.println(e.getMessage());
			}
		}

	}

	/**
	 * Metodo che gestisce la comunicazione in uscita
	 * @param message messaggio da inviare
	 */

	public void sendPacket(String message){
		out.println(message);
		out.flush();
	}

	/**
	 * Metodo che gestisce la comunicazione in entrata
	 * @param message messaggio ricevuto
	 */
	private void receivePacket(String message){
		//TODO gestione comandi

		//Se il comando è NUEMERO_CARTELLE allora prendo il numero di cartelle (es. NUMERO_CARTELLE 3 => n = 3)
		if(message.startsWith("NUMERO_CARTELLE ")){
			if(player.getUsername() != null){
				int n = message.charAt(16);
				if(n > 0){
					player.setNumeroCartelle(n);
				}
			}
			return;
		}
		//Se il comando è USERNAME prendo l'username del player (es. USERNAME ciao => username = ciao)
		if(message.startsWith("USERNAME ")){
			String username = message.split("\\s+")[1];
			if(!username.equals("") && username != null){
				if(!player.setUsername(username)){
					try {
						client.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * Questo metodo, se chiamato, chiude la connessione con il socket client
	 */
	public void close() {
		try {
			in.close();
			out.close();
			client.close();
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
	}


	/**
	 * Metodo che genera N cartelle per il player associato a questo tunnel
	 */
	public void generateCartelle() {
		for (int i = 0; i < player.getNumeroCartelle(); i++) {
			player.addCartella(Tomboliere.generaCartella());
		}
	}

	/**
	 * Metodo che notifica al client le sue cartelle
	 */
	public void notificaCartelle() {
		for (int i = 0; i < player.getNumeroCartelle(); i++) {
			String message = player.CartellaToString(i);

			message = "CARTELLA " + message;

			sendPacket(message);
		}
	}
}
