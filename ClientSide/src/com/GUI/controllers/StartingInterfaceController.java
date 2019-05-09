package com.GUI.controllers;

import com.GUI.CartellaComponent;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import com.Game.controllers.GameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import java.lang.Exception;
import java.net.URL;
import java.util.ResourceBundle;

public class StartingInterfaceController implements Initializable {

    @FXML
    Button btn;

    @FXML
    TextField textField;

    @FXML
    ComboBox<String> comboBox;

    private String numeroCartelle;
    private String username;
    private GameController gc;
    private CartellaComponent listaCartelle;



    //da sostituire successivamente il parametro con un arraylist che conterrà tutte le cartelle
    public void setListaCartelle(CartellaComponent cartella){
        this.listaCartelle = cartella;
    }

    public CartellaComponent getListaCartelle() {
        return listaCartelle;
    }

    public void setNumeroCartelle(String numeroCartelle) {
        this.numeroCartelle = numeroCartelle;
    }

    public String getNumeroCartelle() {
        return numeroCartelle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    @FXML
    public void buttonEvent(ActionEvent event) throws Exception {
        //ottengo da GameController una nuova cartella per crearne il componente
        Integer[] num = gc.getCartella();
        CartellaComponent cartella = new CartellaComponent();
        setNumeroCartelle(comboBox.getValue());
        setUsername(textField.getText());

        //variabili utili
        cartella.setNumeri(num);
        setListaCartelle(cartella);

        //collegamento con GameInterface
        Stage primaryStage = new Stage();
        AnchorPane root = new FXMLLoader().load(getClass().getResource("../../../resources/GameInterface.fxml"));
        AnchorPane a = (AnchorPane) root.getChildren().get(0);
        a.getChildren().add(cartella);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();



    }

    //setto il combobox, oggetto gamecontroller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
        gc = new GameController("Ciao");

    }
}
