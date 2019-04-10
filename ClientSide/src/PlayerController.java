public class PlayerController {

    private static LogicPlayer player;
    private static Client c;


    public static void startGame(String name) {
        player = new LogicPlayer(name);
        c = new Client();
        c.avviaClient();
    }

    public static void effettuaChiamataAmbo(){
        c.chiamata(1);
    }
    public static void effettuaChiamataTerno(){
        c.chiamata(2);
    }
    public static void effettuaChiamataQuaterna(){
        c.chiamata(3);
    }
    public static void effettuaChiamataCinquina(){
        c.chiamata(4);
    }
    public static void effettuaChiamataTombola(){
        c.chiamata(5);
    }



}
