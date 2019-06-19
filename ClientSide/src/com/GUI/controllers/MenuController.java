package com.GUI.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class MenuController {

    @FXML
    Button BTN_PLAY;


    @FXML
    TextField txt_username;

    @FXML
    ImageView imageView;

    @FXML
    StackPane parentPane;

    @FXML
    public void ActionPlay(ActionEvent event) throws IOException {


        //metodo che cambia la scena
        Parent cartellaParent = FXMLLoader.load(getClass().getResource("../../../resources/Cartella.fxml"));

        AnchorPane next = (AnchorPane) cartellaParent;

        next.setTranslateX(parentPane.getScene().getWidth());
        next.toFront();
        parentPane.getChildren().add(next);


        KeyValue kv = new KeyValue(next.translateXProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);

        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> removeAnchor("#menuAnchor"));
        timeline.play();



        //Scene transition

    }

    private void removeAnchor(String name){
        AnchorPane p = (AnchorPane) parentPane.getScene().lookup(name);
        if(p != null){
            parentPane.getChildren().remove(p);
        }
    }

}
