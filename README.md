# My-Networks-Course-Projects

This is a simple web server and client.

The server uses TCP sockets and accepts the port number on which it will listen as a command-line flag.
It will simply print the contents of all messages it receives to stdout.
Additionally, it will echo those contents back to the client.

The client uses TCP sockets and accepts the server’s IP address as a command-line flag as well as accepts the server’s port number as a command-line flag
After connecting to the server, it will send any text the user types over the socket, and then print any reply received from the server to stdout.
It will continue to accept input and send packets (when the user hits return) until the user hits Ctrl-C or otherwise kills the process.
