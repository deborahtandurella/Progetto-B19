package Server;

import com.Game.CallEnum;
import com.Game.Cartella;
import com.Game.CartellaFactory;
import com.Game.Tomboliere;
import com.Game.controllers.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Sessione {

    private Tomboliere t;
    private ArrayList<Player> players;
    private static final int minPlayerCount=1;
    private ArrayList<String> winnings;

    public Sessione() {
        t = new Tomboliere();
        players = new ArrayList<>();

        winnings = new ArrayList<>();

    }


    public void startExtractor(){
        Thread extractor = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                System.out.println(t.getNumber());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        extractor.start();
    }

    public Player addPLayer(String username, int n){

        Player tmpPlayer = new Player(username);

        for (int i = 0; i < n; i++) {
            tmpPlayer.addCartella(CartellaFactory.createCartella());
        }

        players.add(tmpPlayer);
        if(players.size()==minPlayerCount){
            startExtractor();
        }
        return tmpPlayer;


    }


    public ArrayList<Integer> getExtractions() {
        return t.getExtractions();
    }

    public boolean checkCall(String username, int iCartella, CallEnum call, int LN) throws Exception {

        int r = t.checkCall(call,players.get(findPlayer(username)).getCartella(iCartella));

        if(r>=0 && r != 15) {
            players.get(findPlayer(username)).getCartella(iCartella).addWinningRow(r);
            addWinning(players.get(findPlayer(username)),call);
            return true;
        }else if(r==15){
            players.get(findPlayer(username)).getCartella(iCartella).setInvalid();
            addWinning(players.get(findPlayer(username)),call);
            return true;
       }
        return false;
    }

    private void addWinning(Player player, CallEnum call) {
        winnings.add(player.getUsername()+":"+call.name());
    }

    private int findPlayer(String username) {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getWinnings() {

        return winnings;
    }
}
