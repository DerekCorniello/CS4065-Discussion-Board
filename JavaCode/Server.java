package JavaCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    // Server constructor
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {

                // Accepting new clients while the server socket is not closed
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected");

                // Create a client handler for each client
                ClientHandler clientHandler = new ClientHandler(socket);

                // Create a thread for the client, and run it
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {

        }
    }

    // function to close the server socket
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        // Open a socket on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        //Ensure port is open on port 1234
        System.out.println("Port number is: " + serverSocket.getLocalPort());

        //Create a new server object and run it
        Server server = new Server(serverSocket);
        server.startServer();
    }
}