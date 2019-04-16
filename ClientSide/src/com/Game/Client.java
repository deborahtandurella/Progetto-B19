package com.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {


    private BufferedReader in = null;
    private PrintStream out = null;
    private Socket socket = null;
    private Scanner tastiera = new Scanner(System.in);
    private Boolean inGame = false;

    public void avviaClient() {
        try {
            socket = new Socket("localhost", 4000);
            // Apre i canali I/O
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            inGame = true;
            // Legge dal serer
            /* while(inGame){
                out.println(chiamata());
                out.flush();
                out.close();
                in.close();
            } */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void chiamata(int i){

     try {
         String s = null;

         switch (i) {
             case 1:
                 s = "Ambo";
                 break;
             case 2:
                 s = "Terno";
                 break;
             case 3:
                 s = "Quaterna";
                 break;
             case 4:
                 s = "Cinquina";
                 break;
             case 5:
                 s = "Tombola";
                 break;
         }

         out.println(s);
         out.flush();
     }catch (Exception e){
         System.out.println(e.getMessage());
     }

    }

    public void close(){
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
