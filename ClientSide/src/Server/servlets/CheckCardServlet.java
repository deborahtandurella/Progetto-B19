package Server.servlets;

import Server.ApplicationServer;
import com.Game.CallEnum;
import com.Game.controllers.Player;
import com.jsoniter.output.JsonStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * (servlet) Control the request of a player checking the card
 */
public class CheckCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");

        ////String from request. Ex:U=username,N(number of cards)=1,CT=AMBO,LN(last number released)=56
        String queryString = req.getQueryString();

        //Parsing the string
        String[] params = queryString.split("&");

        try {
            //Control the strings
            if(params[0].startsWith("U=") && params[1].startsWith("C=") && params[2].startsWith("CT=") && params[3].startsWith("LN=")){

                //Obtain the parameters
                String username = params[0].substring(2);
                int iCartella = Integer.parseInt(params[1].substring(2));

                int LN= Integer.parseInt(params[3].substring(3));

                CallEnum call = CallEnum.valueOf(params[2].substring(3).toUpperCase());

                if(ApplicationServer.s.checkCall(username,iCartella,call,LN)) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"check\":\"true\"}");
                }else{
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write("{\"check\":\"false\"}");
                }




            }else{
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"check\":\"false\"}");
            }
        }catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"check\":\"false\"}");
        }





    }
}
