CS4065-Discussion-Board
University of Cincinnati - CS4065 Computer Networks Project 2 Source Code

JSON Headers:

    Current Statuses:

        POST -> (From Client to Server) Post the message

            POST Header Format:

            "STATUS"        :   "POST"
            "Message ID"    :   ID of message sent
            "Username"      :   Sender's username
            "Subject"       :   Subject of message
            "Date and Time" :   Date and time of message
            "Body"          :   Body of the message
            "Group ID"      :   ID of the group posting to

        OK -> (From Server to Client) Message successfully posted

            OK Header Format:

            "STATUS"        :   "OK"
            "Message ID"    :   ID of message posted
            "Group ID"      :   ID of group posting to

        BAD -> (From Server to Client) Message not posted
        
            BAD Header Format:

            "STATUS"        :   "BAD"
            "Message ID"    :   ID of message posted
            "Group ID"      :   ID of group posting to
            "Error Code"    :   Code back for why we cannot post
            (TODO: Either do an error code or message)
