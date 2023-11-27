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

            // Obtain username
            bufferedWriter.newLine();
            bufferedWriter.flush();
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    // username getter function
    public String getUsername() {
        return this.username;
    }

    // group getter function
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

    // close all connections
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

        System.exit(0);
    }

    public static void clearScreen() {
        try {
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

        // Connect to an inputted port number
        Scanner scanner = new Scanner(System.in);
        int portNum = -1;
        System.out.println("Input connection port:");

        while (portNum == -1) {
            try{
                portNum = Integer.parseInt(scanner.nextLine());
            } catch(Exception e){
                System.out.println("Please input an integer port number!");
            }
        }
        
        // Connect to the server
        // Note: only can be run locally

        Socket socket = null;
        try{
                socket = new Socket("localhost", portNum);         
        } catch (Exception e) {
                System.out.println("Could not connect to server on port " + portNum + ".");
                //System.exit(1);
        }
        
        clearScreen();

        // Print welcome message
        System.out.println("<SERVER> WELCOME TO THE SERVER!\n");
        System.out.println("<SERVER> Enter your username: \n");
        String username = scanner.nextLine();

        // Create a client and prepare to send/receive messages
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendCommand();

        System.exit(0);
        scanner.close();
    }
}