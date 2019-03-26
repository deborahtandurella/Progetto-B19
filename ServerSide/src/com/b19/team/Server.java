import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe server che gestisce la connessione "fisica" a basso livello del programma
 * @see Thread per maggiori dettagli
 */

public class Server extends Thread {

	//Variabili interne di gestione
	//Porta per connessione TCP
	private int port;
	//Nome in DNS dell'host in cui startare il server
	private String host;
	//Numero massimo di clients
	private int maxClients;
	//Socket per il server
	private ServerSocket server;

	/**
	 * Costruttore della classe
	 * @param p porta su cui inizializzare il server
	 * @param h nome DNS su cui inizializzare il server
	 * @param maxC numero massimo di clients
	 */
	public Server(int p, String h,int maxC) {
		super();
		//Inizializzo le variabili con i valori passati dal costruttore
		this.port = p;
		this.host = h;
		this.maxClients = maxC;

		try {
			//Inizializzo socket effettivo del server passando la porta, il numero di client massimi, e creando con il Wrapper InetAddress un
			//nuovo indirizzo partendo dal nome DNS
			server = new ServerSocket(port,maxClients, InetAddress.getByName(host));
		} catch (IOException e) {
			//Se ho un'eccezione allora stampo errore
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		//Ciclo di polling delle connessioni del client
		while (true){
			try {
				//Polling di accettazione dei clients
				Socket client = server.accept();
				TunnelClient tunnel = new TunnelClient(client);
				tunnel.start();

			} catch (IOException e) {
				//Se ho eccezione allora stampo il messaggio di errore sul System error
				System.err.println(e.getMessage());
			}
		}
	}
}
