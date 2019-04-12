package com.physical;

import com.logic.Player;
import com.logic.Tomboliere;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Classe server che gestisce la connessione "fisica" a basso livello del programma
 * @see Thread per maggiori dettagli
 */

public class Server extends Thread {

	public static int readyPlayer = 0;
	private static int maxPlayers = 10;
	private static boolean acceptPlayers = true;
	//Variabili interne di gestione
	//Porta per connessione TCP
	private int port;
	//Numero massimo di clients
	private int maxClients;
	//Socket per il server
	private ServerSocket server;

	private ArrayList<TunnelClient> clients;


	/**
	 * Costruttore della classe
	 * @param p porta su cui inizializzare il server
	 * @param h nome DNS su cui inizializzare il server
	 * @param maxC numero massimo di clients
	 */
	public Server(int p, InetAddress address,int maxC) {
		super();
		//Inizializzo le variabili con i valori passati dal costruttore
		this.port = p;
		this.maxClients = maxC;
		this.clients = new ArrayList<>();

		try {
			//Inizializzo socket effettivo del server passando la porta, il numero di client massimi, e creando con il Wrapper InetAddress un
			//nuovo indirizzo partendo dal nome DNS
			server = new ServerSocket(port,maxClients, address);
		} catch (IOException e) {
			//Se ho un'eccezione allora stampo errore
			System.err.println(e.getMessage());
		}


	}


	/**
	 * Metodo statico che serve per notificare che un nuovo player attivo è stato aggiunto
	 * @return ritorna true se andato tutto bene altrimenti ritorna false se ho raggiunto il limite
	 */
	public static boolean addReadyPlayer() {
		if(readyPlayer>=maxPlayers){
			return false;
		}
		readyPlayer++;
		if(readyPlayer==maxPlayers){
			acceptPlayers = false;
		}

		return true;
	}

	@Override
	public void run() {
		System.out.println("Server listening on port " + port);
		//Ciclo di polling delle connessioni del client
		while (true) {

			//Controllo se posso accettare i players
			if (acceptPlayers) {
				try {
					//Polling di accettazione dei clients
					Socket client = server.accept();

					//Creo nuovo player
					TunnelClient c = new TunnelClient(client,new Player());
					c.start();
					//Loggo la connessione
					System.out.println("Connesso " + client.getInetAddress());

					clients.add(c);

				} catch (IOException e) {
					//Se ho eccezione allora stampo il messaggio di errore sul System error
					System.err.println(e.getMessage());
				}
			}else{
				//TODO: Start the game
				for(TunnelClient c: clients){
					c.generateCartelle();
					c.notificaCartelle();
				}
			}
		}
	}

	/**
	 * Questo metodo invia un messaggio in broadcast a tutti i player connessi
	 * @param message messaggio da inviare
	 */
	public void sendBroadcastPacket(String message){
		for(TunnelClient c: clients){
			c.sendPacket(message);
		}
	}
}
