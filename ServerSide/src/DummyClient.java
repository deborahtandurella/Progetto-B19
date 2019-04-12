import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
//Test purposes only

public class DummyClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",4000);
        BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while (true){
            System.out.println(b.readLine());
            System.out.println("Non sto leggendo niente");
        }
    }
}
