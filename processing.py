from datetime import datetime as dt

# I/O for message headers
import json

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
#       print_message(): Prints the message with all necessary info held in the class.
#
#       format_header(): Formats the header for web socket communication

class message_header:
    message_id = 0
    header = subject = message = ""
    sender = date = None

    def __init__(self, message_id: int, sender: User, subject: str, message: str):
        self.message_id = message_id
        self.sender = sender
        self.subject = subject
        self.message = message
        self.update_date()
        self.format_header()
    
    # Method: format header
    # Purpose: Used for formatting a packet's header with data over the socket.
    #          Uses the json library for structure
    #
    # Output: Returns the json POST dump of the header to be sent
    def format_header(self):
        header = {

                    "STATUS" :  "POST"
                    "Message ID" : message_id
                    "Username" : sender.username
                    "Subject" : subject
                    "Date and Time" : date
                    "Body" : message
                    "Group ID" : sender.current_group

                 }
        # Dump the header to be sent
        return json.dumps(header)

    # Method: update date
    # Purpose: updates the date for the message.
    def update_date(self):
        self.date = date_and_time().to_string()
        self.format_header()
    
    # Method: print message
    # Purpose: Print the message. Used for viewing the board.
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
class InvalidKeyword(Exception):
    pass

# Class: Invalid Arguments, inherits Exception class
# Purpose: Raise an exception for missing or bad agruments
class InvalidArguments(Exception):
    pass

# Class: Invalid Number of Arguments, inherits Exception class
# Purpose: Raise an exception for incorrect number of arguments
class InvalidNumArguments(Exception):
    pass

# Class: User
# Purpose: Store user data, like current port, address, etc. for use later
#
# Inputs: username

class user:

    current_group = ""
    username = ""

    def __init__(self, username: str):
        self.username = username


    HELP_TABLE =        """
                        -----------------------------------------------------------------------Commands--------------------------------------------------------------------------------
                        
                        help                                     : Opens the commands section
                        connect -[address] -[port]               : Connects to the running bulletin board server using the address and port.
                        join                                     : Join the default message board.
                        post -[subject] -[message]               : Posts a message with a subject to the default board.
                        users                                    : Retrieves a list of users in the default group.
                        leave                                    : Leave the default group.
                        message -[id]                            : Retrieve the content of the message given by the id.
                        exit                                     : Exits the client program.
                        groups                                   : Retrieve a list of all groups that can be joined.
                        groupjoin -[name]                        : Join a specific group with given name.
                        grouppost -[name] -[subject] -[message]  : Posts a message with a subject to the board given by the name.
                        groupusers -[name]                       : Retrieves a list of users in the group given by the name.
                        groupleave -[name]                       : Leaves a specific group.
                        groupmessage -[name] -[id]               : Retrieves the content of a message using it's id posted earlier on a message board given by its name in a specific group.

                        """

    valid_num_args_dict = myDict = {
                                        **dict.fromkeys(['help', 'join', 'users', 'leave', 'exit', 'groups'], 0), 
                                        **dict.fromkeys(['message', 'groupjoin', 'groupusers', 'groupleave'], 1),
                                        **dict.fromkeys(['connect', 'post', 'groupmessage'], 2),
                                                         'grouppost': 3
                                    }

    def group_check(self, group: str):
        return (group == self.group)

    # Function: Parse input
    # Purpose: take inputs and follow actions like in a command line

    def parse_input(self):

        command = input()

        keyword = command.split(' ')[0]
        args = command.split(' -')[1:]
        
        if debug_flag:
            print("Keyword:")
            print(keyword)
            print("Arguments: ")
            print(args)

        try:

            if len(args) != self.valid_num_args_dict[keyword]:
                raise InvalidNumArguments

            match keyword:

                # Display the help function
                case "help":
                    print(self.HELP_TABLE)
                    return

                # Connect to the server
                case "connect":
                    # TODO: connect
                    pass
                    
                # Join the DEFAULT group
                case "join":
                    # TODO: join 
                    self.current_group = "DEFAULT"
                    pass 
                
                # Post to the DEFAULT group
                case "post":
                    # TODO: post
                    pass

                # Obtain the users in the DEFAULT group
                case "users":
                    # TODO: obtain the users in default
                    pass
                
                # Leave the DEFAULT group
                case "leave":
                    # TODO: Leave the group

                    self.current_group = ""
                    pass
                
                # Find a previous message in the DEFAULT group
                case "message":
                    # TODO: Retrieve the message
                    pass

                # Exit application
                case "exit":
                    # TODO: Disconnect from server
                    exit()
                
                # Display the groups that are available on the server
                case "groups":
                    # TODO: display all the groups
                    pass
                
                # Joins a specific group
                case "groupjoin":

                    # Check if they are already in the group, and return after
                    if (group_check(args[0])):
                        print("You're already in this group!")
                        return

                    # If they aren't, but they are in a group
                    if (self.current_group != ""):
                        print(f"Leaving group '{current_group}, joining {args[0]}'")
                        # TODO: Leave the group here

                    self.current_group = args[0]
                    # TODO: Join the group
                    pass
                
                # Posts in a specific group
                case "grouppost":
                    if (group_check(args[0])):
                        print(f"You must join the group '{args[0]}' first!")

                    # TODO: Post to the group
                    pass
                
                # Retrieves the current users in the group
                case "groupusers":
                    if (group_check(args[0])):
                        print(f"You must join the group '{args[0]}' first!")

                    # TODO: Retrieve the users in the group
                    pass
                
                # Leaves the group
                case "groupleave":
                    if (group_check(args[0])):
                        print(f"You are not in group '{args[0]}', you are in {self.current_group}.")

                    self.current_group = ""
                    # TODO: Leave the group                    
                    pass
                
                # Retrieve a message in the group
                case "groupmessage":
                    if (group_check(args[0])):
                        print(f"You must join the group '{args[0]}' first!")

                    # TODO: Retrieve the message
                    pass

        except InvalidKeyword:
            print(f"Invalid Keyword: '{keyword}'. Type 'help' for a list of commands.")
        
        except InvalidArguments:
            print(f"Invalid Argument: '{invalid_arg}' for '{keyword}'. Type 'help' for a list of commands.")

        except InvalidNumArguments:
            print(f"Invalid Arguments: {keyword} takes {valid_num_args_dict[keyword]} arguments, {len()} arguments were provided.")
        
        # For Final Product
        
        except:
            if(not debug_flag):
                print("An unknown error occured. Type 'help' for a list of commands.")

if debug_flag:
    u = user("Derek")
    u.parse_input()