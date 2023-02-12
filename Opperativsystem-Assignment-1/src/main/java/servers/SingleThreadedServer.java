package servers;

import computation.SearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a Single Threaded Server.
 *
 * @author Ole Kristian
 * @author Håvard Vetsbø
 * @version 2.0
 */
public class SingleThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    /**
     * Runs the server.
     */
    public void run() {
        System.out.println("Single-threaded server listening on port: " + serverPort);

        openServerSocket();
        Socket clientSocket;
        while (!isStopped()) {
            /* Listens for incoming messages from clients. */
            try {
                clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            } catch (Exception e) {
                stop();
                e.printStackTrace();
            }
        }
        System.out.println("Server Stopped.");
    }

    /**
     * Set the server to stopped or not.
     * @return if the server is stop or not as a boolean, true if stopt, fals if not.
     */
    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    /**
     * Stops the server.
     */
    public synchronized void stop()  {
        isStopped = true;
        try {
            serverSocket.close();
        }
        catch (Exception e) { e.printStackTrace();
        }
    }

    /**
     * Open a server socket.
     */
    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the address of the client and proceeds to request processing.
     */
    private void handleClient(Socket clientSocket) throws Exception {
        System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort());
        SearchSimulator.processClientRequest(clientSocket, "Singlethreaded Server: ");
    }
}