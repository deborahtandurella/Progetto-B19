package com.GUI.controllers;

import com.Game.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class CartellaController {

    @FXML
    Button BTN_AMBO;

    @FXML
    Button BTN_TERNO;

    @FXML
    Button BTN_QUATERNA;

    @FXML
    Button BTN_CINQUINA;

    @FXML
    Button BTN_TOMBOLA;

    private GameController gameController;

    @FXML
    public void callAmbo(){
        call(1);
    }

    @FXML
    public void callTerno(){
        call(2);
    }

    @FXML
    public void callQuaterna(){
        call(3);
    }

    @FXML
    public void callCinquina(){
        call(4);
    }

    @FXML
    public void callTombola(){
        call(5);
    }


    private void call(int i){

    }

    public void InitStuff(String name){
        gameController = new GameController(name);
    }
}
