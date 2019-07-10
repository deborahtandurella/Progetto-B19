package com.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Create the card
 */
public class CartellaComponent extends GridPane {

    private ArrayList<Button> bottoni;

    /**
     * Set the general view of the card
     */
    public CartellaComponent() {
        super();

        for (int i = 0; i < 9; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(75));
        }

        for (int i = 0; i < 3; i++) {
            this.getRowConstraints().add(new RowConstraints(75));
        }

        this.setStyle("-fx-border-color: black; -fx-grid-lines-visible: true");

        this.getStylesheets().add("./resources/Style.CSS");
        bottoni = new ArrayList<>();

        for (int i = 0; i < 27; i++) {
            Button tmp = new Button();

            tmp.setPrefSize(75,75);

            bottoni.add(tmp);
        }




    }

    /**
     * Set the number of the cell of a table
     *
     * @param numeri numbers assigned to a card
     */
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
                        }else if (btn.getStyleClass().contains("chekkato")){
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
