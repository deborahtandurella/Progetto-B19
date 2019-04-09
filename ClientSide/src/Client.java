import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.net.*;

public class Client extends Thread {
    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;
    Scanner tastiera = new Scanner(System.in);

    public void avviaClient() {
        try {
            socket = new Socket("localhost", 4000);
// Apre i canali I/O
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
// Legge dal serer
            out.println(chiamata());
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String chiamata(){
        int b = 0;
        String s = null;

        b = tastiera.nextInt();
        switch (b){
            case 1: s = "Ambo";
                break;
            case 2: s = "Terno";
                break;
            case 3: s = "Quaterna";
                break;
            case 4: s = "Cinquina";
                break;
            case 5: s = "Tombola";
                break;
            }
         //il problema è che non è completamente funzionante
        return s;
    }

}
