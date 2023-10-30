from datetime import datetime as dt

# Debug flag for testing.
debug_flag = True

# Class: date and time
# Purpose: hold and format a date and time structure
#
# Inputs: None
#
# Functions:
#
#       to_string(): converts the date and time into a string to be printed.

class date_and_time:
    hour = minute = sec = day = month = year = 0
    
    def __init__(self):
        now = dt.now()
        self.year = now.year
        self.month = now.month
        self.day = now.day
        self.hour = now.hour
        self.minute = now.minute
        self.sec = now.second

    def to_string(self):
        return(f"{self.month}-{self.day}-{self.year} @ {self.hour}:{self.minute}:{self.sec}")
        
# Class: message header
# Purpose: Store information about a message for it to be sent later on and read in
# 
# Inputs: id, name of sender, a subject and a message
#
# Functions:
#       
#       update_date(): Changes current time 
#
#       print_message(): prints the message with all necessary info held in the class.

class message_header:
    message_id = 0
    sender = subject = message = ""
    date = None

    def __init__(self, message_id: int, sender: str, subject: str, message: str):
        self.message_id = message_id
        self.sender = sender
        self.subject = subject
        self.message = message
        self.update_date()
    
    def update_date(self):
        self.date = date_and_time().to_string()
    
    # Format:
    #
    # Message ID:    [id]
    # From:          [Username]
    # Subject:       [Subject]
    # On:            [dateandtime]
    #
    # [Message]

    def print_message(self):
        if self.subject == "" or self.subject == None: # if there is no subject, change the subject line
            self.subject = "[No Subject]"
        # Print in the above format
        print((
            "Message ID:    {}\n"
            "From:          {}\n"
            "Subject:       {}\n"
            "On:            {}\n"
                             "\n"
            "{}"
        ).format(self.message_id, self.sender, self.subject, self.date, self.message))

# Class: Invalid Keyword, inherits Exception class
# Purpose: Raise an exception for a bad keyword
class InvalidKeyWord(Exception):
    pass

# Class: Invalid Arguments, inherits Exception class
# Purpose: Raise an exception for incorrect number of arguments
class InvalidArguments(Exception):
    pass

# Class: User
# Purpose: Store user data, like current port, address, etc. for use later
#
# Inputs: username

class user(username: str):

    HELP_TABLE =        """
                        -----------------------------------------------------------------------Commands--------------------------------------------------------------------------------
                        
                        help                                 : Opens the commands section
                        connect [address] [port]             : Connects to the running bulletin board server using the address and port.
                        join                                 : Join the default message board.
                        post [subject] [message]             : Posts a message with a subject to the default board.
                        users                                : Retrieves a list of users in the default group.
                        leave                                : Leave the default group.
                        message [id]                         : Retrieve the content of the message given by the id.
                        exit                                 : Exits the client program.
                        groups                               : Retrieve a list of all groups that can be joined.
                        groupjoin [name]                     : Join a specific group with given name.
                        grouppost [name] [subject] [message] : Posts a message with a subject to the board given by the name.
                        groupusers [name]                    : Retrieves a list of users in the group given by the name.
                        groupleave [name]                    : Leaves a specific group.
                        groupmessage [name] [id]             : Retrieves the content of a message using it's id posted earlier on a message board given by its name in a specific group.

                        """

    # Function: Parse input
    # Purpose: take inputs and follow actions like in a command line

    def parse_input():
        

        command = input()

        keyword = command.split(' ')[0]
        args = command.split(' ')[1:]
        
        if debug_flag:
            print("Keyword": + keyword)
            print("Arguments": + args)

        try:
            match keyword:
                case "help":

                    break
                case "connect":

                    break
        
                case "join":

                    break

                case "post":

                    break

                case "users":

                    break

                case "leave":

                    break

                case "message":

                    break

                case "exit"

                    break

                case "groups":

                    break

                case "groupjoin":

                    break

                case "grouppost":

                    break

                case "groupusers":

                    break

                case "groupleave":

                    break

                case "groupmessage":

                    break

                case _:
                    raise(InvalidKeyword())
        
        except InvalidKeyword:
