package Server.servlets;

import Server.ApplicationServer;
import com.Game.controllers.Player;
import com.jsoniter.output.JsonStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * (servlet) Add a player to the game
 */
public class AddPlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        //String from request. Ex: U=username&N(number of cards)=1
        String queryString = req.getQueryString();

        //Parsing the string. Ex: U=username,N=1
        String[] params = queryString.split("&");

        //Control the strings
        if(params[0].startsWith("U=") && params[1].startsWith("N=")){

            //Obtain the parameters
            String username = params[0].substring(2);
            int nCartelle = Integer.parseInt(params[1].substring(2));

            //Create the player
            Player p = ApplicationServer.s.addPLayer(username,nCartelle);

            //Traslation in json
            String s = JsonStream.serialize(p);
            resp.getWriter().write(s);


        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"valid\":\"false\"}");
        }





    }
}
