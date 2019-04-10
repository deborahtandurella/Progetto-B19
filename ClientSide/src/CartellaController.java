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

    @FXML
    public void callAmbo(){
        PlayerController.effettuaChiamataAmbo();
    }

    @FXML
    public void callTerno(){
        PlayerController.effettuaChiamataTerno();
    }

    @FXML
    public void callQuaterna(){
        PlayerController.effettuaChiamataQuaterna();
    }

    @FXML
    public void callCinquina(){
        PlayerController.effettuaChiamataCinquina();
    }

    @FXML
    public void callTombola(){
        PlayerController.effettuaChiamataTombola();
    }
}
