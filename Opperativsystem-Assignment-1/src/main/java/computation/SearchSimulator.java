package computation;

import utils.ResponseGenerator;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Simulate a search operation for Single Threaded Server
 *
 * @author Ole Kristian
 * @author Håvard Vestbø
 * @version 2.0
 */
public class SearchSimulator {

    /**
     * Sends a client request.
     * @throws Exception
     */
    public static void processClientRequest(Socket socket, String serverText) throws Exception {
        long time1 = System.currentTimeMillis();
        System.out.println("Request processing started at: " + time1);
        Thread.sleep(10 * 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Request processing ended at: " + time2);

        String body =  ResponseGenerator.generatorResponseHTML(serverText, time1, time2);
        String header = ResponseGenerator.generatorResponseHeader(body.length());

        new PrintWriter(socket.getOutputStream(), true).println(header + body);
    }

}
