package Server.servlets;

import Server.ApplicationServer;
import com.Game.CallEnum;
import com.Game.controllers.Player;
import com.jsoniter.output.JsonStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");

        //esempio: U=username&C=1&CT=AMBO
        String queryString = req.getQueryString();

        //esempio: U=username,N=1,CT=AMBO,LN=ultimo numero uscito
        String[] params = queryString.split("&");

        try {
            //Controllo stringhe
            if(params[0].startsWith("U=") && params[1].startsWith("C=") && params[2].startsWith("CT=") && params[3].startsWith("LN=")){

                //Ottengo i parametri
                String username = params[0].substring(2);
                int iCartella = Integer.parseInt(params[1].substring(2));

                int LN= Integer.parseInt(params[3].substring(3));

                CallEnum call = CallEnum.valueOf(params[2].substring(3).toUpperCase());

                if(ApplicationServer.s.checkCall(username,iCartella,call,LN)) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"check\":\"true\"}");
                }else{
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("{\"check\":\"false\"}");
                }




            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"check\":\"false\"}");
            }
        }catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"check\":\"false\"}");
        }





    }
}
