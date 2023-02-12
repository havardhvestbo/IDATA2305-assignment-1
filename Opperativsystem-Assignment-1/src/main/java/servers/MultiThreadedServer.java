package servers;

import computation.AsyncSearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a Multi Threaded Server.
 *
 * @author Ole Kristian
 * @author Håvard vetsbø
 */
public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    /**
     * Runs the Multi Threaded Server
     */
    public void run() {
        System.out.println("Lisening ti port: " + serverPort);

        openServerSocket();
        Socket clientSocket;

        while (!isStopped()) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                // on receiving a request, execute the heavy computation in a new thread
                new Thread(
                        new AsyncSearchSimulator(
                                clientSocket,
                                "Multithreaded Server: "
                        )
                ).start();
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
    public synchronized void stop() {
        isStopped = true;
        try { serverSocket.close(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * Opens a server socket.
     */
    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}