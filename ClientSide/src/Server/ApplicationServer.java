package Server;
import Server.servlets.AddPlayerServlet;
import Server.servlets.CheckCardServlet;
import Server.servlets.GetExtractionsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import javax.servlet.Servlet;

public class ApplicationServer {

        private int port;
        private Server server;

        public static final Sessione s = new Sessione();


        public ApplicationServer(int port) {
            this.port = port;
        }

        public void start() throws Exception {
            server = new Server(port);
            ServletContextHandler handler = new ServletContextHandler();
            handler.addServlet(new ServletHolder(new AddPlayerServlet()), "/addplayer");
            handler.addServlet(new ServletHolder(new GetExtractionsServlet()), "/extractions");
            handler.addServlet(new ServletHolder(new CheckCardServlet()), "/checkcard");

            server.setHandler(handler);
            server.start();
        }

        public void stop() throws Exception {
            server.stop();
        }
    }

