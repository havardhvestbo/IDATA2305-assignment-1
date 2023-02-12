package computation;

import utils.ResponseGenerator;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Implements a Runnable interface and simulates an asynchronous search operation for Multi Threaed Server.
 *
 * @author Ole Kristian
 * @author Håvard Vestbø
 * @version 2.0
 */
public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket;
    protected String serverText;

    /**
     * Constructor for Async Search Simulator.
     * @param clientSocket the clinet socket port connecting to.
     * @param serverText
     */
    public AsyncSearchSimulator(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    /**
     * Runs the Async Search Simulator
     */
    public void run() {
        try {
            long time1 = System.currentTimeMillis();
            Thread.sleep(10 * (long) 1000);
            long time2 = System.currentTimeMillis();

            String body = ResponseGenerator.generatorResponseHTML(serverText, time1, time2);
            String header = ResponseGenerator.generatorResponseHeader(body.length());

            new PrintWriter(clientSocket.getOutputStream(), true).println(header + body);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}