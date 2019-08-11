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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.omg.CORBA.Object;

import java.awt.event.KeyEvent;
import java.lang.Exception;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableBooleanValue;

/**
 * The controller of the graphic of the starting interface
 */
public class StartingInterfaceController implements Initializable {

    @FXML
    StackPane rootPane;

    @FXML
    AnchorPane anchorRoot;

    @FXML
    Button btn;

    @FXML
    VBox vbox;

    @FXML
    HBox hbox;

    @FXML
    TextField textField;

    @FXML
    ComboBox<String> comboBox;

    @FXML
    TextField ipText;

    private String numeroCartelle;
    private String username;
    private GameController gc;
    private ArrayList<CartellaComponent> listaCartelle;

    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    /**
     *Called by JavaFX when the player clicks the button "PLAY"
     */
    @FXML
    public void buttonEvent(ActionEvent event) throws Exception {

        gc = new GameController(textField.getText(),Integer.valueOf(comboBox.getValue()),ipText.getText());
        if(!gc.isValidUsername()) {

            //Show username error
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Bad Username");
            alert.setHeaderText("Sorry but this username is already taken");
            alert.setContentText("Please choose another one!");

            alert.showAndWait();

            return;
        }
        //ottengo da GameController una nuova cartella per crearne il componente

        for (int i = 0; i < gc.getCartellaCount(); i++) {
            Integer[] num = gc.getCartellaAsArray(i);
            CartellaComponent cartella = new CartellaComponent();
            cartella.setNumeri(num);
            listaCartelle.add(cartella);
        }

        NumbersBoard numbersBoard = new NumbersBoard();
        numbersBoard.initNumbers();
        numbersBoard.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));


        //carica l'interfaccia successiva
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../resources/GameInterface.fxml"));

        loader.setController(new GameInterfaceController(listaCartelle,gc));
        AnchorPane root = loader.load();
        AnchorPane a = (AnchorPane) root.getChildren().get(root.getChildren().size()-1);

        a.getChildren().add(numbersBoard);


        Scene scene = btn.getScene();
        root.translateXProperty().set(scene.getWidth());
        rootPane.getChildren().add(root);

        //proprietà di transizione della nuova interfaccia
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            rootPane.getChildren().remove(anchorRoot);
        });
        timeline.play();

        Stage currStage = (Stage) scene.getWindow();
        currStage.setScene(scene);
        currStage.setOnCloseRequest(e -> handleClose());
        currStage.show();
        currStage.setMaximized(true);

    }

    /**
     * Used to stop the extraction when the interface is closed
     */
    private void handleClose() {
        gc.stopExtractions();
    }

    /**
     * First method called by JavaFX when starting StartingInterface interface
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
        listaCartelle = new ArrayList<>();

        //element resizing in front of a box resizing
        vbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                btn.setPrefWidth(width/2);
                
            }
        });

        hbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                textField.setPrefWidth(width/4);

            }
        });

        hbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                comboBox.setPrefWidth(width/4);

            }
        });

        hbox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                ipText.setPrefWidth(width/4);
            }
        });

        //It permits to press ENTER instead to click on PLAY
        btn.setDefaultButton(true);
    }
}
