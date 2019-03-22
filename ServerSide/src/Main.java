public class Main {
	public static void main(String[] args) {

		//Inizializzo un nuovo server
		//NB: all'avvio del programma bisogna passare 3 argomenti (porta, host, numero massimo clients)
		//TODO: magari trovare una soluzione migliore anzichè passarlo come argomento del programma
		Server s = new Server(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]));

		s.start();

	}
}
