package com.GUI.controllers;

import com.GUI.CartellaComponent;
import com.Game.CallEnum;
import com.Game.CartellaFactory;
import com.Game.Cartella;
import com.Game.controllers.GameController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//successivamente da aggiustare con un input di più cartella

public class GameInterfaceController implements Initializable {

    @FXML
    AnchorPane anchor;

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
    ComboBox comboBox;

    @FXML
    TextField textField;

    private GameController logicController;
    private CallEnum btnValue;
    private StartingInterfaceController firstController;

    // deve diventare un arraylist
    private Cartella cartelle;
    private boolean nextBtn = false;
    private boolean previousBtn = false;

    // uguale a 1 solo per prova
    private int numCartelle = 1;

    //cartella di prova, in realtà dovrà essere un arraylist contenente tutte le cartelle
    private CartellaComponent listaCartelle = new CartellaComponent();

    public void amboEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.AMBO, cartelle)){
            textField.setText("Complimenti hai fatto ambo!!");
        };

    }

    public void ternaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.TERNA, cartelle)){
            textField.setText("Complimenti hai fatto terna!!");
        };
    }

    public void quaternaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.QUATERNA, cartelle)){
            textField.setText("Complimenti hai fatto quaterna!!");
        };
    }

    public void cinquinaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.CINQUINA, cartelle)){
            textField.setText("Complimenti hai fatto cinquina!!");
        };
    }

    public void tombolaEvent(ActionEvent event){
        if (logicController.buttonControl(btnValue.TOMBOLA, cartelle)){
            textField.setText("Complimenti hai fatto tombola!!");
        };
    }

    //da implementare successivamente
    public void nextButton(ActionEvent event){

    }

    //da implementare successivamente
    public void previousButton(ActionEvent event){

    }



    //metodo che aggiorna il valore del bottone next
    public boolean nextEvent(ActionEvent event){
        return nextBtn = true;
    }

    //metodo che aggiorna il valore del bottone previous
    public boolean previousEvent(ActionEvent event){
        return previousBtn = true;
    }



    //da sostituire successivamente il parametro con un arraylist che conterrà tutte le cartelle
    public void setListaCartelle(CartellaComponent cartella){
        Integer[] num = cartelle.getNumeri();

        CartellaComponent cartella1 = new CartellaComponent();
        cartella1.setNumeri(num);
        listaCartelle = cartella;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //per la fase di prova metto numCartele = 1, in realtà dovra essere uguale a ciò che setta il giocatore
        //numCartelle = 1;
        //devo mettere la cartellacomponent e agganciarle agli anchorpane

        setListaCartelle(firstController.getListaCartelle());

        anchor.getChildren().add(listaCartelle);


        ArrayList<Integer> aggNum = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    int numero = logicController.estraiNumero();
                    aggNum.add(numero);
                    ObservableList<Integer> list = FXCollections.observableArrayList(aggNum);
                    comboBox.setValue((Integer) numero);
                    comboBox.setItems(list);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
