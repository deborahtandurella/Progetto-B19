public class PlayerController {

    private static LogicPlayer player;
    private static Client c;

    public static void startGame(String name) {
        player = new LogicPlayer(name);
        c = new Client();
        c.avviaClient();
    }



}
