package com.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DummyServer extends Thread{
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(4000);
        while (true){
            Socket socket = s.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while(true)
            System.out.println(in.readLine());


        }

    }

}
