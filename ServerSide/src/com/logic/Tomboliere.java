package com.b19.team;

import java.util.ArrayList;
import java.util.Random;

public class Tomboliere {

    public static Tomboliere instance;
    public static ArrayList<Cartella> cartelle;



    public Tomboliere() {
        if(instance == null){
            instance = this;
        }
    }

    public static Cartella generaCartella(){
        int[][] tmpCart = new int[3][9];
        int currCol = 0;
        int currRow = 0;

        Random r = new Random();

        ArrayList<Integer> usciti = new ArrayList<>();

        while(true){
            int num = r.nextInt(90)+1;

            if(!usciti.contains(num)){

                usciti.add(num);
            }


            break;
        }



        Cartella c = null;
        return c;
    }

}
