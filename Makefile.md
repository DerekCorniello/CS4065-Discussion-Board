#CS4065-Discussion-Board
##University of Cincinnati - CS4065 Computer Networks Project 2 Source Code**
###Derek Corniello, Kyle Willoughby, Jack Vo**

---

Header Format:

        "STATUS"        :   "POST"
        "Message ID"    :   ID of message sent
        "Username"      :   Sender's username
        "Subject"       :   Subject of message
        "Date and Time" :   Date and time of message
        "Body"          :   Body of the message
        "Group ID"      :   ID of the group posting to

Issues and solutions we came across:

    - How to structure the packets coming through? We chose to use plaintext for:
        - Readability
        - Usability
        - Nativeness in Java

    - Originally coded in Python, but it made more sense to code in Java due to:
        - Usability
        - Readability
        - Java's server/client syntax
        - Background of programmers

    - Choosing which structures had which rights/actions to choose from:
        - Client file has everything needed to run the client side
        - Server file runs the server, then creates many client handlers
        - ClientHandler file holds the logic as to how the user and server communicate.
    
    - Deciding how to implement the connections with only using built-in functionality
        - We used a lot of our knowledge from Project 1 to help
    
    - Testing and debugging across different operating systems was very hard to do. 
        - We made a few features have different ways of accomplishing that through the use of identifying the running OS.
