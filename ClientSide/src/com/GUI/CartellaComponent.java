package com.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.util.ArrayList;

public class CartellaComponent extends GridPane {

    private ArrayList<Button> bottoni;

    public CartellaComponent() {
        super();

        for (int i = 0; i < 9; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(100));
        }

        for (int i = 0; i < 3; i++) {
            this.getRowConstraints().add(new RowConstraints(100));
        }

        bottoni = new ArrayList<>();

        for (int i = 0; i < 28; i++) {
            Button tmp = new Button();


            tmp.setPrefSize(100,100);

            bottoni.add(tmp);
        }




    }




    public void setNumeri(Integer[] numeri){

        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 9; cols++) {
                int indice = cols + rows*9;
                if(numeri[indice] != 0) {
                    bottoni.get(indice).setText(String.valueOf(numeri[indice]));

                    bottoni.get(indice).setOnAction(event -> {
                        Button btn = (Button) event.getSource();



                        if(btn.getStyleClass().contains("checkNUM")){

                            btn.getStyleClass().remove("checkNUM");
                            btn.getStyleClass().add("chekkato");
                        }else {
                            btn.getStyleClass().remove("chekkato");
                            btn.getStyleClass().add("checkNUM");
                        }

                    });
                    bottoni.get(indice).getStyleClass().add("checkNUM");
                    this.add(bottoni.get(indice), cols, rows);

                }
            }
        }

        
        
    }


}
