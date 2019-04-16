package com.Game.controllers;

import com.Game.Client;
import com.Game.LogicPlayer;

public class PlayerController {

    private static LogicPlayer player;
    private static Client c;


    public static void startGame(String name) {
        player = new LogicPlayer(name);
        c = new Client();
        c.avviaClient();
    }

    public static void chiamata(int i){
        c.chiamata(i);
    }



}
