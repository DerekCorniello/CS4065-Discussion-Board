from datetime import datetime as dt

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
        print(f"Message ID:    {self.message_id}\nFrom:          {self.sender}\nSubject:       {self.subject}\nOn:            {self.date}\n\n{self.message}")
