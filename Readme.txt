Neil Malhotra
1001084075



This is a server-client implementation in JAVA. Both classes FileClient and FileServer use an instance of CLIENTConnection to establish a connection between one another. I ran the code on omega and it runs perfectly. You can open several windows to see multithreaded implementation. The program uses the port number '4444' by default and establishes connection instantaneously. There is porper error handling in the code which implements the respective error codes.

Steps for running the program.

1. Upload all the files to omega.

2. compile the files using : javac FileServer.java CLIENTConnection.java FileClient.java

3. Run FileServer to run the server.(java FileServer)

4.Open a new terminal window to run FileClient(java FileClient)

5. Several new terminals can be opened for FileClient as it is a multi threaded implementation.

6. The FileClient can download any files in the directory as FileServer.

References:

http://tutorials.jenkov.com/java-multithreaded-servers/multithreaded-server.html

https://www.tutorialspoint.com/javaexamples/net_multisoc.htm
