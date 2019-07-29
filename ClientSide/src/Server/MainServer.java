package Server;

/**
 * Class that setup and execute the simulation of the server.
 */
public class MainServer {

    /**
     *Main method of the server
     */
    public static void main(String[] args) {
        try {
            new ApplicationServer(8282).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
