from socket import *
from threading import *
import sys


class Client:

    def __init__(self):
        self.socket = None
        self.buffered_reader = None
        self.buffered_writer = None
        self.username = ""

    def main(self):
        try:
            # Connect to server
            self.socket = socket(AF_INET, SOCK_STREAM)
            self.socket.connect(("localhost", 1234))
            
            # Clear Screen
            print("\033[H\033[2J", end='')
            sys.stdout.flush()
            
            # Print welcome message
            print("<SERVER> WELCOME TO THE SERVER!\n")
            print("<SERVER> Enter your username:\n\n")
            self.username = input()

            # Create a client a
            self.buffered_reader = self.socket.makefile("r")
            self.buffered_writer = self.socket.makefile("w")

            if self.listen_for_message():
                print("Successfully connected to server")

            self.send_command()

            # sys.exit(0)
        except Exception as ex:
            print(f"Exception: {ex}")
            self.close_everything()


    def send_command(self):
        try:
            # Try to send the username first to connect
            self.buffered_writer.write(self.username + '\n')
            self.buffered_writer.flush()

            while self.socket.fileno() != -1:
                message_to_send = input()
                self.buffered_writer.write(message_to_send + '\n')
                self.buffered_writer.flush()

        except Exception as ex:
            print(f"Exception: {ex}")
            self.close_everything()

    def listen_for_message(self):
        def run():
            while self.socket.fileno() != -1:
                try:
                    msg_from_group = self.buffered_reader.readline().strip()
                    if not msg_from_group:
                        self.close_everything()
                        break
                    print(msg_from_group)
                except Exception as ex:
                    print(f"Exception: {ex}")
                    self.close_everything()
                    break

        Thread(target=run).start()

    def close_everything(self):
        try:
            if self.buffered_reader:
                self.buffered_reader.close()
            if self.buffered_writer:
                self.buffered_writer.close()
            if self.socket:
                self.socket.close()
        except Exception as ex:
            print(f"Exception: {ex}")


if __name__ == '__main__':
    client = Client()
    client.main()
