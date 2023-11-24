package JavaCode;

import java.io.*;
import java.net.*;
import java.util.*;


public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String currentGroup;

    // Constructor
    public Client(Socket socket, String username) {
        try {
            // Establish the connection with the server
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //getter for username
    public String getUsername() {
        return this.username;
    }

    //getter for group
    public String getCurrentGroup() {
        return this.currentGroup;
    }

    // Send command to the server
    public void sendCommand() {
        try {

            // Try to send the user name first to connect
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            scanner.close();

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Listen for messages sent from the server
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroup;
                while (!socket.isClosed()) {
                    try {
                        // If message from group is null, error occured, close connection and end
                        msgFromGroup = bufferedReader.readLine();
                        if (msgFromGroup == null) {
                            closeEverything(socket, bufferedReader, bufferedWriter);
                            break;
                        }
                        System.out.println(msgFromGroup);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    // close everything, sockets, readers and writers.
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clear the cmd screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {

        // Connect to the server
        Socket socket = new Socket("localhost", 1234);
        Scanner scanner = new Scanner(System.in);

        clearScreen();

        // Print welcome message
        System.out.println("<SERVER> WELCOME TO THE SERVER!\n");
        System.out.print("<SERVER> Enter your username: \n\n");
        String username = scanner.nextLine();

        // Create a client and prepare to send/receive messages
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendCommand();

        System.exit(0);
        scanner.close();
    }
}