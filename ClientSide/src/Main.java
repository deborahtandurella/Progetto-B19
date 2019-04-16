import com.Game.Cartella;
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

        int[] num = {4, 16, 43, 76, 80, 5,17,44,77,81,6,18,45,78,82};

        Cartella cartella1 = new Cartella();
        cartella1.setNumeri(num);
        Pane root = new Pane();
        root.getChildren().add(cartella1);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



}
