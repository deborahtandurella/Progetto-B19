package Server;

public class MainServer {
    public static void main(String[] args) {
        try {
            new ApplicationServer(8282).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
