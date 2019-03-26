package com.b19.team;

//NB classe tester non necessaria javadoc
public class Main {

	//Membro statico del main in modo tale che da ogni punto del programma se c'è bisogno ci si può agganciare per interagire
	private static Server s;

	public static void main(String[] args) {

		//Inizializzo un nuovo server
		//NB: all'avvio del programma bisogna passare 3 argomenti (porta, host, numero massimo clients)
		//TODO: magari trovare una soluzione migliore anzichè passarlo come argomento del programma
		s = new Server(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]));

		s.start();

	}
}
