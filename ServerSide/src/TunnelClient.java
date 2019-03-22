import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Classe che gestisce il tunnel privato TCP di connessione con il client
 * @see Thread per maggiori dettagli
 */

public class TunnelClient extends Thread {
	private Socket client;

	/**
	 * Costruttore per crare un tunnel di flussi privato tra client e server
	 */
	public TunnelClient(Socket client) {
		super();
		//Inizializzo client dell'istanza con il client passato come parametro
		this.client = client;

	}

	@Override
	public void run() {
		BufferedReader in = null;
		DataOutputStream out = null;

		try {
			//Inizializzo flusso di dati in input partendo dal flusso di input del socket
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//Inizializzo flusso di dati in output partendo dal flusso di output del socket
			out = new DataOutputStream(client.getOutputStream());
		}catch (Exception e){
			//Se ho eccezione stampo messaggio sul system error
			System.err.println(e.getMessage());
			return;
		}

		//Ciclo di polling dell'input
		while (true){
			try	{
				//Leggo intera riga
				String line = in.readLine();
				//Se la riga è il testo ESCI allora chiudo connessione
				//TODO: se il sistema di comandi è approvato allora implementare comandi (classe per comandi?)
				if(line == "ESCI"){
					//Chiudo socket
					client.close();
					return;
				}
			}catch (Exception e){

			}
		}

	}
}
