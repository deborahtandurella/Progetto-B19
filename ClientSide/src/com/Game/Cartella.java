package com.Game;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.util.ArrayList;

public class Cartella extends GridPane {

    private ArrayList<Button> bottoni;

    public Cartella() {
        super();

        for (int i = 0; i < 9; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(100));
        }

        for (int i = 0; i < 3; i++) {
            this.getRowConstraints().add(new RowConstraints(100));
        }

        bottoni = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Button tmp = new Button();

            tmp.setPrefWidth(100);
            bottoni.add(tmp);
        }




    }




    public void setNumeri(int[] numeri){
        int a = 0;
        for (int i = 0; i < 5; i++) {

            if(numeri[i]<=10){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 0,0);
            }

            if(numeri[i]>10 && numeri[i]<=20){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 1,0);
            }

            if(numeri[i]>20 && numeri[i]<=30){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 2,0);
            }

            if(numeri[i]>30 && numeri[i]<=40){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 3,0);
            }

            if(numeri[i]>40 && numeri[i]<=50){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 4,0);
            }

            if(numeri[i]>50 && numeri[i]<=60){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 5,0);
            }

            if(numeri[i]>60 && numeri[i]<=70){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 6,0);
            }

            if(numeri[i]>70 && numeri[i]<=80){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 7,0);
            }

            if(numeri[i]>80 && numeri[i]<=90){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 8,0);
            }
            bottoni.get(a).setOnAction((ActionEvent e) -> {
                Button b =(Button) e.getSource();
                b.getStyleClass().removeAll("checkNUM");
                b.getStyleClass().add("chekkato");
            });

          a++;
        }

        for (int i = 5; i < 10; i++) {

            if(numeri[i]<=10){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 0,1);
            }

            if(numeri[i]>10 && numeri[i]<=20){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 1,1);
            }

            if(numeri[i]>20 && numeri[i]<=30){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 2,1);
            }

            if(numeri[i]>30 && numeri[i]<=40){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 3,1);
            }

            if(numeri[i]>40 && numeri[i]<=50){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 4,1);
            }

            if(numeri[i]>50 && numeri[i]<=60){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 5,1);
            }

            if(numeri[i]>60 && numeri[i]<=70){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 6,1);
            }

            if(numeri[i]>70 && numeri[i]<=80){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 7,1);
            }

            if(numeri[i]>80 && numeri[i]<=90){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 8,1);
            }
            bottoni.get(a).setOnAction((ActionEvent e) -> {
                Button b =(Button) e.getSource();
                b.getStyleClass().removeAll("checkNUM");
                b.getStyleClass().add("chekkato");
            });
            a++;

        }

        for (int i = 10; i < 15 ; i++) {

            if(numeri[i]<=10){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 0,2);
            }

            if(numeri[i]>10 && numeri[i]<=20){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 1,2);
            }

            if(numeri[i]>20 && numeri[i]<=30){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 2,2);
            }

            if(numeri[i]>30 && numeri[i]<=40){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 3,2);
            }

            if(numeri[i]>40 && numeri[i]<=50){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 4,2);
            }

            if(numeri[i]>50 && numeri[i]<=60){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 5,2);
            }

            if(numeri[i]>60 && numeri[i]<=70){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 6,2);
            }

            if(numeri[i]>70 && numeri[i]<=80){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 7,2);
            }

            if(numeri[i]>80 && numeri[i]<=90){
                bottoni.get(a).setText(String.valueOf(numeri[i]));
                this.add(bottoni.get(a), 8,2);
            }
            bottoni.get(a).setOnAction((ActionEvent e) -> {
                Button b =(Button) e.getSource();
                b.getStyleClass().removeAll("checkNUM");
                b.getStyleClass().add("chekkato");
            });
            a++;

        }
        
        
    }


}
