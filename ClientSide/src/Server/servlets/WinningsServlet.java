package Server.servlets;

import Server.ApplicationServer;
import com.jsoniter.output.JsonStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * (servlet) Manage the winnings
 */

public class WinningsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        //Winnings in a game (ambo,terna etc..)
        ArrayList<String> winnings = ApplicationServer.s.getWinnings();

        String json = JsonStream.serialize(winnings);
        json = "{\n\"winners\":"+json+"\n}";

        resp.getWriter().write(json);

    }
}
