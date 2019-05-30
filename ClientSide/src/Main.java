import com.GUI.CartellaComponent;
import com.GUI.controllers.StartingInterfaceController;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
       /* Client client =  new Client();

        client.avviaClient(); */


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("resources/MenuStage.fxml"));

        //Integer[] num = CartellaFactory.createCartella().getNumeri();




        //CartellaComponent cartella1 = new CartellaComponent();
        //cartella1.setNumeri(num);
        //Pane root = new Pane();



        //root.getChildren().add(cartella1);
        //Scene scene = new Scene(root);

        //scene.getStylesheets().add(this.getClass().getResource("resources/Style.CSS").toExternalForm() );


        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/StartingInterface.fxml"));


        Parent parent = loader.load();
        Scene scene = new Scene(parent,600,400);


        primaryStage.setTitle("Tombola Game!");
        primaryStage.setScene(scene);
        primaryStage.show();








    }



}