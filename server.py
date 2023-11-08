from processing import message_header, debug_flag
import json

# Defined global var to keep track of group ids
GROUP_ID = 0

# Class: group
# Purpose: store and view messages posted in groups
#
# Methods: 
#
#       add(): appends a header to the group
#
#       update(): sends the last two messages in the group

class group:
    id = 0
    name = ""
    messages = []

    def __init__(self, name: str):
        global GROUP_ID
        self.id = GROUP_ID
        self.name = name
        GROUP_ID += 1
    
    # Method: add
    # Purpose: add a header to the group for printing
    #
    # Inputs: a message header to be added

    def add(self, message: message_header):
        self.messages.append(message_header)

    # Method: update
    # Purpose: update and send all viewable messages to client
    def update(self):
        index = max(len(self.messages) - 2, 0)
        headers = []
        
        for i in range(index, len(self.messages)):
            headers.append(self.messages[i].format_header())

        # TODO: Send to client for viewing

        # TODO: if it is empty, send some sort of special case


# Function: print_groups
# Purpose: creates a dict to pass to the user with group name and ID
def print_groups():
    groups_and_id = {}

    for group in active_groups:
        groups_and_id[group.name] = group.id

    # TODO: send this data across to client to be printed



# Initializes a list of active groups, with default being the only one
active_groups = [group("DEFAULT")]

if debug_flag:
    active_groups.append(group("derek's group"))
    print_groups()