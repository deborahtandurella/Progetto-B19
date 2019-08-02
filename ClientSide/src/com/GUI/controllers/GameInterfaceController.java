package com.GUI.controllers;

import com.GUI.CartellaComponent;
import com.GUI.NumbersBoard;
import com.Game.CallEnum;
import com.Game.CartellaFactory;
import com.Game.Cartella;
import com.Game.controllers.GameController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//successivamente da aggiustare con un input di più cartella

/**
 * JavaFX controller of GameInterface interface
 */
public class GameInterfaceController implements Initializable {

    @FXML
    AnchorPane anchor;

    @FXML
    AnchorPane anchor2;

    @FXML
    Button btn_ambo;

    @FXML
    Button btn_terna;

    @FXML
    Button btn_quaterna;

    @FXML
    Button btn_cinquina;

    @FXML
    Button btn_tombola;

    @FXML
    Button btn_next;

    @FXML
    Button btn_previous;

    @FXML
    ComboBox combobox;

    @FXML
    TextField textField;

    private int index;

    private GameController logicController;
    private CallEnum btnValue;

    private boolean nextBtn = false;
    private boolean previousBtn = false;

    // uguale a 1 solo per prova
    private int numCartelle = 1;

    //cartella di prova, in realtà dovrà essere un arraylist contenente tutte le cartelle
    private ArrayList<CartellaComponent> listaCartelle;


    public GameInterfaceController(ArrayList<CartellaComponent> listaCartelle, GameController controller) {
        this.listaCartelle = listaCartelle;
        this.logicController = controller;
        this.index = 0;
    }

    /**
     * Called method by click event button Ambo
     */
    public void amboEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.AMBO, index)){
            textField.setText("Complimenti hai fatto ambo!!");
            btn_ambo.setDisable(true);
            btn_ambo.setText("Ambo: "+ logicController.getPlayerName());
            String bip = "src/resources/cheer.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
    }

    /**
     * Called method by click event button Terna
     */
    public void ternaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.TERNA,index)){
            textField.setText("Complimenti hai fatto terna!!");
            btn_terna.setDisable(true);
            btn_terna.setText("Terna: "+ logicController.getPlayerName());
            String bip = "src/resources/cheer.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
    }

    /**
     * Called method by click event button Quaterna
     */
    public void quaternaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.QUATERNA, index)){
            textField.setText("Complimenti hai fatto quaterna!!");
            btn_quaterna.setDisable(true);
            btn_quaterna.setText("Quaterna: "+  logicController.getPlayerName());
            String bip = "src/resources/cheer.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
    }

    /**
     * Called method by click event button Cinquina
     */
    public void cinquinaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.CINQUINA, index)){
            textField.setText("Complimenti hai fatto cinquina!!");

            btn_cinquina.setDisable(true);
            btn_cinquina.setText("Cinquina: "+  logicController.getPlayerName());
            String bip = "src/resources/cheer.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
    }

    /**
     * Called method by click event button Tombola
     */
    public void tombolaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.TOMBOLA, index)){
            textField.setText("Complimenti hai fatto tombola!!");
            btn_tombola.setDisable(true);
            btn_tombola.setText("Tombola: "+  logicController.getPlayerName());
            String bip = "src/resources/cheer.mp3";
            Media hit = new Media(new File(bip).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
    }

    /**
     * Called method by click event button to go to next card
     */
    public void nextButton(ActionEvent event){
        index++;

        if(index >= logicController.getCartellaCount()){
            index = 0;
        }

        anchor.getChildren().remove(0);
        anchor.getChildren().add(listaCartelle.get(index));
        System.out.println(index);
    }

    /**
     * Called method by click event button to go to previous card
     */
    public void previousButton(ActionEvent event){
        index--;
        if(index <0){
            index = logicController.getCartellaCount()-1;
        }
        anchor.getChildren().remove(0);
        anchor.getChildren().add(listaCartelle.get(index));
        System.out.println(index);
    }



    //metodo che aggiorna il valore del bottone next
    public boolean nextEvent(ActionEvent event){
        return nextBtn = true;
    }

    //metodo che aggiorna il valore del bottone previous
    public boolean previousEvent(ActionEvent event){
        return previousBtn = true;
    }


    /**
     * First method called by JavaFX when starting GameInterface interface
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        anchor.getChildren().add(listaCartelle.get(index));

        logicController.startExtraction(this::updateExtractions);
        textField.setDisable(true);


    }

    /**
     * Called to update the interface every extractions
     */
    private void updateExtractions() {
        try {
            ArrayList<Integer> extractions = logicController.getExtractions();


            if (extractions.size() > 0) {
                NumbersBoard numbersBoard = (NumbersBoard) anchor2.getChildren().get(0);

                numbersBoard.updateGrid(extractions);

                textField.setText(logicController.getLastWinningPhrase());
            }
        }catch (Exception e){

        }
    }

}
