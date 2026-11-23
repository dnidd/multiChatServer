Server side stream sockets using ServerSocket.
Implements a chat server which can send and receive text messages.
Server can take incoming connection requests fom chat clients
and place all chat clients in separate threads to be able to connect to new/more chat clients.
Every chat client thread is listening for a chat client to send a message. When a message is sent the
chat server will send/broadcast the message to all connected clients. The chat server keeps track on
all connected chat clients in a data structure.

The Chat server can be executed in two ways:

1. java Server (default port 2000)
2. java Server <port>


### Execute server with client
Compile the java-files from within src: javac *.java<br>

1. Run this server, Server.class.
2. Use application in repository 'chatClient'.<br>
Test the server by executing the program chatClient running the Chat.class in two (or more) separate terminals.


Using synchronized not to interrupt other actions on data structure.
https://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html

