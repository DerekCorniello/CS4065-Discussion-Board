package JavaCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    public int clients;

    // Constructor for Server object
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clients = 0;
        updateMessage();
    }

    // Method to start the server
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {

                // Accepting new clients while the server socket is not closed
                Socket socket = serverSocket.accept();
                clients++;
                updateMessage();

                // Create a client handler for each client
                ClientHandler clientHandler = new ClientHandler(socket, this);

                Thread thread = new Thread(clientHandler);
                
                thread.start();
                
            }
        } catch (IOException e) {

        }
    }

    // Method to update server message
    public void updateMessage() {
        clearScreen();
        System.out.println("Now hosting on port " + serverSocket.getLocalPort() + ".");
        System.out.println("With " + clients + " active clients.");
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

     public static void clearScreen() {
        try {
            // Check if the operating system is Windows
            if (System.getProperty("os.name").startsWith("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.flush();
    }


    public static void main(String[] args) throws IOException {

        // Open a new server socket on inputted port
        int portNum = -1;
        System.out.println("Input host port:");
        Scanner scanner = new Scanner(System.in);
        while (portNum == -1) {
            try{
                portNum = Integer.parseInt(scanner.nextLine());
            } catch(Exception e){
                System.out.println("Please input an integer port number!");
            }
        }
        scanner.close();
        clearScreen();

        // Open a socket on the port number
        ServerSocket serverSocket = new ServerSocket(portNum);

        // Start the server
        Server server = new Server(serverSocket);
        server.startServer();

    }

}