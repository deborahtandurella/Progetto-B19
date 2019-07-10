import com.GUI.CartellaComponent;
import com.GUI.NumbersBoard;
import com.GUI.controllers.StartingInterfaceController;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class that setup and execute the simulation.
 */
public class Main extends Application {

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
       /* Client client =  new Client();

        client.avviaClient(); */


        launch(args);
    }

    /**
     * Start the main menu
     *
     * @param primaryStage stage of the main menu
     * @throws Exception error of loading the stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("resources/MenuStage.fxml"));

        //Integer[] num = CartellaFactory.createCartella().getNumeri();




        //CartellaComponent cartella1 = new CartellaComponent();
        //NumbersBoard numbersBoard = new NumbersBoard();
        //numbersBoard.initNumbers();
        //cartella1.setNumeri(num);
        //Pane root = new Pane();
        //root.getChildren().add(numbersBoard);



        //root.getChildren().add(cartella1);
        //Scene scene = new Scene(root);

        //scene.getStylesheets().add(this.getClass().getResource("resources/Style.CSS").toExternalForm() );

        //Load and start the main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/StartingInterface.fxml"));


        Parent parent = loader.load();
        Scene scene = new Scene(parent,600,400);


        primaryStage.setTitle("Tombola Game!");
        primaryStage.setScene(scene);
        primaryStage.show();








    }



}