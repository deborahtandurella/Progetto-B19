package com.Game;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.net.*;

public class Client extends Thread {
    BufferedReader in = null;
    PrintStream out = null;
    Socket socket = null;
    Scanner tastiera = new Scanner(System.in);
    Boolean inGame = false;

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
                 inGame = false;
                 break;
         }

         out.println(s);
         out.flush();

            if(!inGame) {
             out.close();
             in.close();
            }
     }catch (Exception e){
         System.out.println(e.getMessage());
     }

    }


}
