# Multiple Client/Server Discussion Board
**Derek Corniello, Kyle Willoughby, Jack Vo**
## How to run the code using command line 

- Make sure you clone the code, the navigate to the code root folder (Note: This will change if you rearrange the files, such as for submission)
- Open terminal and type

```bash
javac JavaCode/*.java
```

In a terminal, run

```bash
java JavaCode.Server
```

Enter in your desired port number, and ensure that the server is running before moving on.

In another terminal, run

```bash
java JavaCode.Client
```
Your Client will *By Default* join the public group. You may exit this group at any time.
## Samples

```
<SERVER> WELCOME TO THE SERVER!

<SERVER> Enter your username:

helloJack

<SERVER> Hello helloJack! You are now in the Public Group!
         Type /help to learn more about our commands!

/help

<SERVER> Server Commands:

         /users--<GroupID>: Print all users in a group provided the Group ID

         /groups: Print all groups available

         /join--<GroupID>: Join a group provided the Group ID

         /leave--<GroupID>: Leave a group provided the Group ID

         /post--<GroupID>--<Subject>--<Content>: Post a message in a group provided the Group ID, Message Subject and Message Content

         /msg--<MsgID>: Get a message provided the Message ID

         /exit: Leave all groups and exit the server

/users

<SERVER> ERROR. Please specify exactly 1 Group ID.

/groups

<SERVER> Available groups:

         Public Group - Group ID: PG - Joined
         Group 1      - Group ID: G1
         Group 2      - Group ID: G2
         Group 3      - Group ID: G3
         Group 4      - Group ID: G4
         Group 5      - Group ID: G5

/join--G1

<SERVER> Successfully joined Group G1.

/help

<SERVER> Server Commands:

         /users--<GroupID>: Print all users in a group provided the Group ID

         /groups: Print all groups available

         /join--<GroupID>: Join a group provided the Group ID

         /leave--<GroupID>: Leave a group provided the Group ID

         /post--<GroupID>--<Subject>--<Content>: Post a message in a group provided the Group ID, Message Subject and Message Content

         /msg--<MsgID>: Get a message provided the Message ID

         /exit: Leave all groups and exit the server

/exit

<SERVER> SEE YOU AGAIN!

```
