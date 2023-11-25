package JavaCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    // Constructor for Server object
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // Method to start the server
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {

                // Accepting new clients while the server socket is not closed
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected");

                // Create a client handler for each client
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);

                thread.start();

            }
        } catch (IOException e) {

        }
    }

    //Method to close the server socket
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

        // Open a new server socket on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Port number is: " + serverSocket.getLocalPort());

        // Start the server
        Server server = new Server(serverSocket);
        server.startServer();

    }

}