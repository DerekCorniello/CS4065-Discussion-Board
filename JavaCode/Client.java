package JavaCode;

import java.io.*;
import java.net.*;
import java.util.*;
// import java.time.LocalDateTime; // Import the LocalDateTime class
// import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String currentGroup;
    // private static StringBuilder HELP_TABLE = new StringBuilder();
    // private static Map<String, Integer> validNumArgsDict = new HashMap<>();
    // private static boolean debugFlag;

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

    public String getUsername() {
        return this.username;
    }

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

    // close everything
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // public String getTime() {
    // LocalDateTime date = LocalDateTime.now();
    // DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy
    // HH:mm:ss");

    // return date.format(myFormatObj);
    // }

    // Class: Invalid Keyword, inherits Exception class
    // Purpose: Raise an exception for a bad keyword
    // public class InvalidKeyword extends Exception {
    // public InvalidKeyword() {
    // }
    // }

    // // Class: Invalid Number of Arguments, inherits Exception class
    // // Purpose: Raise an exception for incorrect number of arguments
    // public class InvalidArguments extends Exception {
    // public InvalidArguments() {
    // }
    // }

    // private boolean groupCheck(String group) {
    // return group.equals(this.currentGroup);
    // }

    // private void parseInput() {
    // Scanner scanner = new Scanner(System.in);
    // String command = scanner.nextLine();

    // String[] commandParts = command.split(" ");
    // String keyword = commandParts[0];
    // String[] args = command.substring(keyword.length()).trim().split(" -");

    // try {
    // if (args.length != validNumArgsDict.get(keyword)) {
    // throw new InvalidArguments();
    // }

    // switch (keyword) {
    // case "help":
    // System.out.println(HELP_TABLE);
    // break;

    // case "connect":
    // // TODO: connect
    // break;

    // case "join":
    // // TODO: join
    // currentGroup = "DEFAULT";
    // break;

    // case "post":
    // // TODO: post
    // break;

    // case "users":
    // // TODO: obtain the users in default
    // break;

    // case "leave":
    // // TODO: Leave the group
    // currentGroup = "";
    // break;

    // case "message":
    // // TODO: Retrieve the message
    // break;

    // case "exit":
    // // TODO: Disconnect from server
    // System.exit(0);
    // break;

    // case "groups":
    // // TODO: display all the groups
    // break;

    // case "groupjoin":
    // // Check if they are already in the group, and return after
    // if (groupCheck(args[0])) {
    // System.out.println("You're already in this group!");
    // return;
    // }

    // // If they aren't, but they are in a group
    // if (!currentGroup.isEmpty()) {
    // System.out.println("Leaving group '" + currentGroup + "', joining '" +
    // args[0] + "'");
    // // TODO: Leave the group here
    // }

    // // TODO: Check if the group is valid,
    // // If not, ask them to create one,
    // // and update the server.

    // currentGroup = args[0];
    // // TODO: Join the group
    // break;

    // case "grouppost":
    // if (groupCheck(args[0])) {
    // System.out.println("You must join the group '" + args[0] + "' first!");
    // }
    // // TODO: Post to the group
    // break;

    // case "groupusers":
    // if (groupCheck(args[0])) {
    // System.out.println("You must join the group '" + args[0] + "' first!");
    // }
    // // TODO: Retrieve the users in the group
    // break;

    // case "groupleave":
    // if (groupCheck(args[0])) {
    // System.out.println("You are not in group '" + args[0] + "', you are in " +
    // currentGroup + ".");
    // }
    // currentGroup = "";
    // // TODO: Leave the group
    // break;

    // case "groupmessage":
    // if (groupCheck(args[0])) {
    // System.out.println("You must join the group '" + args[0] + "' first!");
    // }
    // // TODO: Retrieve the message
    // break;

    // default:
    // throw new InvalidKeyword();
    // }
    // } catch (InvalidKeyword e) {
    // System.out.println("Invalid Keyword: '" + keyword + "'.\nType 'help' for a
    // list of commands.");
    // } catch (InvalidArguments e) {
    // System.out.println("Invalid Arguments: " + keyword + " takes " +
    // validNumArgsDict.get(keyword) +
    // " arguments, " + args.length + " arguments were provided.\nType 'help' for a
    // list of commands.");
    // } catch (Exception e) {
    // if (!debugFlag) {
    // System.out.println("An unknown error occurred. Type 'help' for a list of
    // commands.");
    // }
    // }
    // }

    public static void main(String[] args) throws IOException {

        // region constant variables
        // HELP_TABLE.append("-----------------------------------------------------------------------Commands--------------------------------------------------------------------------------\n")
        // .append("\nhelp : Opens the commands section\n")
        // .append("connect -[address] -[port] : Connects to the running bulletin board
        // server using the address and port.\n")
        // .append("join : Join the default message board.\n")
        // .append("post -[subject] -[message] : Posts a message with a subject to the
        // default board.\n")
        // .append("users : Retrieves a list of users in the default group.\n")
        // .append("leave : Leave the default group.\n")
        // .append("message -[id] : Retrieve the content of the message given by the
        // id.\n")
        // .append("exit : Exits the client program.\n")
        // .append("groups : Retrieve a list of all groups that can be joined.\n")
        // .append("groupjoin -[name] : Join a specific group with given name.\n")
        // .append("grouppost -[name] -[subject] -[message] : Posts a message with a
        // subject to the board given by the name.\n")
        // .append("groupusers -[name] : Retrieves a list of users in the group given by
        // the name.\n")
        // .append("groupleave -[name] : Leaves a specific group.\n")
        // .append("groupmessage -[name] -[id] : Retrieves the content of a message
        // using its id posted earlier on a message board given by its name in a
        // specific group.\n");

        // validNumArgsDict.put("help", 0);
        // validNumArgsDict.put("join", 0);
        // validNumArgsDict.put("users", 0);
        // validNumArgsDict.put("leave", 0);
        // validNumArgsDict.put("exit", 0);
        // validNumArgsDict.put("groups", 0);
        // validNumArgsDict.put("message", 1);
        // validNumArgsDict.put("groupjoin", 1);
        // validNumArgsDict.put("groupusers", 1);
        // validNumArgsDict.put("groupleave", 1);
        // validNumArgsDict.put("connect", 2);
        // validNumArgsDict.put("post", 2);
        // validNumArgsDict.put("groupmessage", 2);
        // validNumArgsDict.put("grouppost", 3);
        // endregion

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
    }
}