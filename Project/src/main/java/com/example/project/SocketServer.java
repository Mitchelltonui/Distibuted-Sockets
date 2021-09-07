package com.example.project;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {
    //socket server port on which it will listen
    private static final int port = 1234;
    //public static Message Input Variable
    public static String Input = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //create the socket server object
        ServerSocket server = new ServerSocket(port);

        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();

            if(message.equalsIgnoreCase("end")){
                //create ObjectOutputStream object
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //write object to Socket if the end of input messages is reached
                oos.writeObject(ServerProtocol.processInput());
                oos.close();

            }
            else{
                String[] splitted =message.split(":"); // split incoming streams into key value pairs
                /*
                 * Displaying received key value data for this ObjectInputStream */
                System.out.println("Message received; Key: "+splitted[0]+", Value: "+splitted[1]);
                //Storing message input to a global static variable
                Input = splitted[1];

            }
            //close resources
            ois.close();
            socket.close();

            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        //Shutting down server listening incase client sends exit request
        System.out.println("Closing Socket server!!");
        server.close();
    }
}