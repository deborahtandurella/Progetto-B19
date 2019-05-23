package Server;

import com.Game.controllers.Player;
import com.jsoniter.output.JsonStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends HttpServlet {

    private List<String> names = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");

        //esempio: U=username&N=1
        String queryString = req.getQueryString();

        //esempio: U=username,N=1
        String[] params = queryString.split("&");

        //Controllo stringhe
        if(params[0].startsWith("U=") && params[1].startsWith("N=")){

            //Ottengo i parametri
            String username = params[0].substring(2);
            int nCartelle = Integer.parseInt(params[1].substring(2));

            //Creo il giocatore
            Player p = ApplicationServer.s.addPLayer(username,nCartelle);

            //Traduzione in json
            String s = JsonStream.serialize(p);
            resp.getWriter().write(s);


        }else{
            resp.getWriter().write("{\"valid\":\"false\"}");
        }





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        names.add(req.getParameter("name"));

        write(resp, "added");
    }

    private void write(HttpServletResponse resp, String message) throws IOException {
        resp.getWriter().write(message);
    }
}
