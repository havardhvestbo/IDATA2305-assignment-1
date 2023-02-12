import servers.MultiThreadedServer;
import servers.SingleThreadedServer;

public class Main {

    private static int serverPort = 8080;

    public static void main(String[] args) throws Exception {
        MultiThreadedServer multiThreadedServer = new MultiThreadedServer(serverPort);
        multiThreadedServer.run();
    }
}
