package JavaCode;
import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class ClientHandler implements Runnable {
    
    public Socket socket;
    public String current_group;
    public String username;

    public ClientHandler(Socket socket) {
       this.socket = socket; 
    }

    public void run()
    {
        // TODO: What does the run() function do with that?
        // This is necessary because to make an instance of the Socket in
        // Client.java, it needs to be Runnable, which means it needs to 
        // implement the run() function.
    }

    public String getTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(myFormatObj);
    }

    // Class: Invalid Keyword, inherits Exception class
    // Purpose: Raise an exception for a bad keyword
    public class InvalidKeyword extends Exception {
        public InvalidKeyword() {}
    }

    // Class: Invalid Number of Arguments, inherits Exception class
    // Purpose: Raise an exception for incorrect number of arguments
    public class InvalidArguments extends Exception {
        public InvalidArguments() {}
    }

   
    // TODO: What elsedoes the ClientHandler do?
}