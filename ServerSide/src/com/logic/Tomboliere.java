package com.logic;

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

    /**
     * Metodo statico che genera una cartella
     * @return ritorna la cartella creata
     */
    public static Cartella generaCartella(){

        int[] flags = new int[9];
        ArrayList<Integer> numeritmp = new ArrayList<>();

        Random r = new Random();


        while (numeritmp.size() < 15){

            int n = r.nextInt(90);

            if(n < 10){

                if(flags[0]<2){
                    flags[0]++;
                    numeritmp.add(n);
                }

            }else if(n<20){

                if(flags[1]<2){
                    flags[1]++;
                    numeritmp.add(n);
                }

            }else if(n < 30){
                if(flags[2]<2){
                    flags[2]++;
                    numeritmp.add(n);
                }
            }else if(n<40){
                if(flags[3]<2){
                    flags[3]++;
                    numeritmp.add(n);
                }
            } else if (n<50) {
                if(flags[4]<2){
                    flags[4]++;
                    numeritmp.add(n);
                }
            }else if(n<60){
                if(flags[5]<2){
                    flags[5]++;
                    numeritmp.add(n);
                }
            }else if(n < 70){
                if(flags[6]<2){
                    flags[6]++;
                    numeritmp.add(n);
                }
            }else if(n<80){
                if(flags[7]<2){
                    flags[7]++;
                    numeritmp.add(n);
                }
            } else if (n<90) {
                if(flags[8]<2){
                    flags[8]++;
                    numeritmp.add(n);
                }
            }

        }

        Integer[] cardNumbers = numeritmp.toArray(new Integer[numeritmp.size()]);

        Cartella c = new Cartella(cardNumbers);
        return c;
    }

}
