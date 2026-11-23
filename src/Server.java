import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Entry point for chat server.
 * If args are present - use args, else use default port.
 * When client connects, ServerSocket returns Socket.
 * Socket injected to Server.
 */
public class Server {

    private final ServerSocket serverSocket;

    private static final int DEFAULT_PORT = 2000;

    /**
     * Constructor.
     * Creating a ServerSocket to communicate with connecting client
     * @param serverSocket = ServerSocket
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public static void main(String[] args) throws IOException {

        int port = DEFAULT_PORT;

        // Check if default port or args port will be used.
        if(args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Port must be an integer. Usage: java Server <port>");
                return;
            }

        } else if (args.length > 1) {
                System.err.println("Usage: java Server <port>");
            }

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Server server = new Server(serverSocket);
            server.startServer();
        } catch (IOException e) {
            System.err.println("Server connection failed." );
        }

    }


    /**
     * serverSocket.accept() returns Socket when connection to client accepted.
     * Server communicates with client with returned Socket.
     * Create ClientHandler to be used in new separate Thread.
     * New Thread contains connection between client and server.
     * Server can handle multiple clients.
     */
    public void startServer() {
        System.out.println("Starting server on port " + serverSocket.getLocalPort());

        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(socket, ip);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            terminateServerSocket();
        }
    }


    public void terminateServerSocket() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}













