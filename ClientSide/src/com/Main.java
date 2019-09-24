package com;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Class that setup and execute the simulation of the client.
 */
public class Main extends Application {


    /**
     * com.Main client method
     */
    public static void main(String[] args) {
        //Start GUI
        launch(args);
    }

    /**
     * Start the main menu
     *
     * @param primaryStage  stage of the main menu
     * @throws Exception error of loading the stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Load and start the main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/StartingInterface.fxml"));


        Parent parent = loader.load();
        Scene scene = new Scene(parent,800, 500);

        //Press ESC to close the window
        scene.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    primaryStage.close();
                }
            }
        });

        //Set the primaryStage
        primaryStage.setTitle("Tombola Game!");
        primaryStage.setScene(scene);
        primaryStage.show();








    }



}