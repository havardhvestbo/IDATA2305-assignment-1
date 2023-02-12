# Opperativsystem-Assignment-1
How to start the program:
1. Go to Main.java
2. Create a object of SignleThreadedServer or MultiThreadedServer, depending on what you
    want to run.
3. Call the run method (is the same for both object).
4. Run Main.main(args).
5. Send HTTP GET requests on localhost:port

We discovered that there are actually two data transmissions from the client to the 
server when an HTTP GET request is sent. Since the first message contains no data, we 
'believe that it serves as the start of the TCP three-way handshake. The HTTP GET 
request appears in the second message.
The program is made in such a way that no matter what the client delivers, the server 
will always produce the same kind of response. The initial empty message will therefore 
receive the same response from the server as the actual request, according to this. 
The server will have to generate a message twice (which takes 10 seconds) while 
utilizing the single-threaded server. This reduces productivity. However, when the server
 is multi-threaded, both client messages can be processed simultaneously. As a result, 
the HTTP interaction time between a client and our server has been decreased from 20.04 
seconds to 10.02 seconds.
We made an effort to read the messages that the servers had received. The notion was 
that we could ascertain whether the communication had any substance or not. Instead of 
taking 10 seconds to create a response if it was empty, we could simply return an empty 
answer. The server would become caught in an endless loop while attempting to read the 
empty messages, therefore this did not work out. The client would delay delivering the 
actual request if the server did not respond to the empty message. It really 
demonstrates why multi-threading is advantageous when designing server software, as in
this assignment, because we just left the single-threaded server to generate a 
response for each message it received regardless of the content
