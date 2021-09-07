package com.example.project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedHashMap;

public class SocketClient {

    public static String feedback = "sending";
    /*
     * public socketClient(Hashmap) returns String socketServer response
     * */
    public boolean socketClient(LinkedHashMap detailsMap) throws IOException, ClassNotFoundException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectOutputStream oos;

        for(int i=0; i< detailsMap.size();i++){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 1234);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server...");

            //Getting Key and Value from LinkedHashMap to send to Server
            Object key = detailsMap.keySet().toArray()[i];
            Object value = detailsMap.get(key);
            oos.writeObject(key+":"+value);

        }

        socket = new Socket(host.getHostName(), 1234);
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("end");

        //read the server response message
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            String message;
            message = (String) ois.readObject();
            System.out.println("Response Message: " + message);

            //Assigning message variable to public static feedback
            feedback = message;

        }
        //close after use
        oos.close();

        return true;

    }

}