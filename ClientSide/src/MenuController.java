import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

    @FXML
    Button BTN_PLAY;


    @FXML
    TextField txt_username;

    @FXML
    ImageView imageView;

    @FXML
    public void ActionPlay(ActionEvent event) throws IOException {
        PlayerController.startGame(txt_username.getText());

        //metodo che cambia la scena
        Parent cartellaParent = FXMLLoader.load(getClass().getResource("Cartella.fxml"));

        Scene scene = new Scene(cartellaParent);

        Stage stage = (Stage) BTN_PLAY.getScene().getWindow();
        stage.setScene(scene);

    }

}
