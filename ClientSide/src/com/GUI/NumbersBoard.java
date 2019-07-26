package com.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * Show a table of extracted numbers
 */
public class NumbersBoard extends GridPane {

    private ArrayList<Label> labels;

    public NumbersBoard() {
        super();

        for (int i = 0; i < 10; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(60));
        }

        for (int i = 0; i < 9; i++) {
            this.getRowConstraints().add(new RowConstraints(60));
        }

        this.setStyle("-fx-border-color: #aaaaaa; -fx-grid-lines-visible: true; -fx-font-size: 30; -fx-text-fill: white");

        this.getStylesheets().add("./resources/Style.CSS");


        labels = new ArrayList<>();

        for (int i = 0; i < 90; i++) {
            Label tmp = new Label(String.valueOf(i+1));

            tmp.setPrefSize(60,60);

            tmp.getStyleClass().add("labeldef");

            labels.add(tmp);

        }




    }


    public void initNumbers(){

        for (int rows = 0; rows < 9; rows++) {
            for (int cols = 0; cols < 10; cols++) {
                int indice = cols + rows*10;
                //labels.get(indice).setText(String.valueOf(indice));
                this.add(labels.get(indice),cols,rows);
                //System.out.println(indice);
            }
        }



    }

    public void updateGrid(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int n = numbers.get(i)-1;
            this.getChildren().get(n).getStyleClass().add("extracted");
        }
    }


}
