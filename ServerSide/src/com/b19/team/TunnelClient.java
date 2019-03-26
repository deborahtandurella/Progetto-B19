package com.b19.team;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Classe che gestisce il tunnel privato TCP di connessione con il client
 * @see Thread per maggiori dettagli
 */

public class TunnelClient extends Thread {
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Costruttore per crare un tunnel di flussi privato tra client e server
	 */
	public TunnelClient(Socket client) {
		super();
		//Inizializzo client dell'istanza con il client passato come parametro
		this.client = client;

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
				//TODO: se il sistema di comandi è approvato allora implementare comandi (classe per comandi?)

			}catch (Exception e){
				System.err.println(e.getMessage());
			}
		}

	}
}
