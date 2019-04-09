import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController{

    @FXML
    Button BTN_PLAY;



    @FXML
    TextField txt_username;

    @FXML
    ImageView imageView;

    @FXML
    public void ActionPlay(){
        PlayerController.startGame(txt_username.getText());
    }

}
