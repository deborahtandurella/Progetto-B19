package Server;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import com.Game.Tomboliere;
import com.Game.controllers.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that manages the game session
 */
public class Sessione {

    //The manager of the game
    private Tomboliere t;

    //Players of the session
    private ArrayList<Player> players;

    //Minimum number of the player of a session=2
    private static final int minPlayerCount=2;

    //Winnings in the session
    private ArrayList<String> winnings;

    //Server side extractor Thread
    private Thread extractor;


    /**
     * Constructor of the class Sessione
     */
    public Sessione() {
        t = new Tomboliere();
        players = new ArrayList<>();
        winnings = new ArrayList<>();

    }


    /**
     * Start the extraction of the numbers
     */
    public void startExtractor(){

        extractor = new Thread(() -> {
            try {
                //Manage the waiting time to start the extraction
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                System.out.println(t.getNumber());
                try {
                    //Manage the waiting time from an extraction to another extraction
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        extractor.start();
    }

    /**
     * Add a player to the session
     *
     * @param username name of the player
     * @param n number of cards
     * @return the new player of the session
     */
    public Player addPLayer(String username, int n){

        //A player of the session
        Player tmpPlayer = new Player(username);

        //Assign the cards to the player
        for (int i = 0; i < n; i++) {
            tmpPlayer.addCartella(CartellaFactory.createCartella());
        }

        //Add the player to the list of players
        players.add(tmpPlayer);

        //Check if there are the conditions to start the extractions
        if(players.size()==minPlayerCount){
            startExtractor();
        }
        return tmpPlayer;
    }

    /**
     * Get the number come out
     *
     * @return the numbers extracted
     */
    public ArrayList<Integer> getExtractions() {
        return t.getExtractions();
    }


    /**
     * Check if the winning call(ambo,terna, ecc..) from a player is right
     *
     * @param username the name of the player
     * @param iCartella index of the card
     * @param call type of the call
     *
     * @return true if the call is right; false if the call is wrong
     */
    public boolean checkCall(String username, int iCartella, CallEnum call) throws Exception {

        //Use the checkCall method of the Tomboliere
        int r = t.checkCall(call,players.get(findPlayer(username)).getCartella(iCartella));

        //Control if there are the conditions of the call
        if(r>=0 && r != 15) {
            players.get(findPlayer(username)).getCartella(iCartella).addWinningRow(r);
            addWinning(players.get(findPlayer(username)),call);
            return true;
        }else if(r==15){
            players.get(findPlayer(username)).getCartella(iCartella).setInvalid();
            addWinning(players.get(findPlayer(username)),call);
            endGame();
            return true;
       }
        return false;
    }

    /**
     * Stops extraction Thread
     */
    private void endGame() {
        extractor.interrupt();
        extractor.stop();
    }

    /**
     * Add the winning to the arraylist of winnings
     *
     * @param player that makes the winning
     * @param call the type of winning
     */
    private void addWinning(Player player, CallEnum call) {
        winnings.add(player.getUsername()+":"+call.name());
    }

    /**
     * Search a player in the session
     *
     * @param username of the searched player
     * @return the index of the player in the Arraylist of the players of the session, if there is that player in the session
     *         -1, if there isn't that player in the session
     */
    private int findPlayer(String username) {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the winnings of a session
     *
     * @return Arraylist of winnings
     */
    public ArrayList<String> getWinnings() {
        return winnings;
    }
}
