package Server;
import Server.servlets.AddPlayerServlet;
import Server.servlets.CheckCardServlet;
import Server.servlets.GetExtractionsServlet;
import Server.servlets.WinningsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import javax.servlet.Servlet;

/**
 * Setup the connection and the element of the game for the server side
 */
public class ApplicationServer {

        //Port number for the connection
        private int port;

        //Server of the game
        private Server server;

        //Create a new game session
        public static final Sessione s = new Sessione();

    /**
     * Constructor method of the server
     *
     * @param port port number
     */
    public ApplicationServer(int port) {
            this.port = port;
        }


    /**
     *Setup and create the server
     */
    public void start() throws Exception {

            //Create the server
            server = new Server(port);

            //Setup the handlers
            ServletContextHandler handler = new ServletContextHandler();
            handler.addServlet(new ServletHolder(new AddPlayerServlet()), "/addplayer");
            handler.addServlet(new ServletHolder(new GetExtractionsServlet()), "/extractions");
            handler.addServlet(new ServletHolder(new CheckCardServlet()), "/checkcard");
            handler.addServlet(new ServletHolder(new WinningsServlet()), "/winnings");

            server.setHandler(handler);
            server.start();
        }


    /**
     * Stop the server
     */
    public void stop() throws Exception {
            server.stop();
        }
    }

