package Server.servlets;

import Server.ApplicationServer;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * (servlet) Manage the number extracted
 */

public class GetExtractionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Numbers extracted
        ArrayList<Integer> extr = ApplicationServer.s.getExtractions();

        //Serialize from Array to Json
        String extrJson = JsonStream.serialize(extr);

        extrJson = "{\n\"numbers\":"+extrJson+"}";

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(extrJson);


    }
}
