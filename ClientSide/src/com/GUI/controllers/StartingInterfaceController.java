package com.GUI.controllers;

import com.GUI.CartellaComponent;
import com.GUI.NumbersBoard;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import com.Game.controllers.GameController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.lang.Exception;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartingInterfaceController implements Initializable {

    @FXML
    StackPane rootPane;

    @FXML
    AnchorPane anchorRoot;

    @FXML
    Button btn;

    @FXML
    TextField textField;

    @FXML
    ComboBox<String> comboBox;

    private String numeroCartelle;
    private String username;
    private GameController gc;
    private ArrayList<CartellaComponent> listaCartelle;



    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    @FXML
    public void buttonEvent(ActionEvent event) throws Exception {

        gc = new GameController(textField.getText(),Integer.valueOf(comboBox.getValue()));
        //ottengo da GameController una nuova cartella per crearne il componente

        for (int i = 0; i < gc.getCartellaCount(); i++) {
            Integer[] num = gc.getCartellaAsArray(i);
            CartellaComponent cartella = new CartellaComponent();
            cartella.setNumeri(num);
            listaCartelle.add(cartella);
        }

        NumbersBoard numbersBoard = new NumbersBoard();
        numbersBoard.initNumbers();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/GameInterface.fxml"));

        loader.setController(new GameInterfaceController(listaCartelle,gc));
        AnchorPane root = loader.load();
        AnchorPane a = (AnchorPane) root.getChildren().get(root.getChildren().size()-1);

        a.getChildren().add(numbersBoard);

/*
        Scene scene = btn.getScene();
        root.translateYProperty().set(scene.getHeight());
        rootPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            rootPane.getChildren().remove(anchorRoot);
        });
        timeline.play();
*/


        Stage currStage = (Stage) textField.getScene().getWindow();
        currStage.setScene(new Scene(root, 800, 560));
        currStage.setOnCloseRequest(e -> handleClose());
        currStage.show();



    }

    private void handleClose() {
        gc.stopExtractions();
    }

    //setto il combobox, oggetto gamecontroller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
        listaCartelle = new ArrayList<>();

    }
}
