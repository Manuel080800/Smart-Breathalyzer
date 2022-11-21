package com.breathalyzer.socket;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Connection {
    private String message;

    public Server(String host, int port) throws IOException {
        super(host, port);    
    }    
    
    public void startServer() {
        try {            
            System.out.println("Starting server");
            super.createSocket();
            this.client = this.server.accept();
             
            System.out.println("Receiving messages from the client:");
            Scanner input = new Scanner(this.client.getInputStream());
            
            try {
                while((this.message = input.nextLine()) != null) {
                    System.out.println(this.message);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Completion of reading client messages");
            }
           
            System.out.println("Ending reception of client messages");
            
            this.client.close();
            this.server.close();
            System.out.println("Closing server");
        } catch (IOException ex) {
            System.out.println("An error occurred in the connection to the server:");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void forceClose () throws IOException {
        this.client.close();
        this.server.close();
    }

    public String getMessage() {
        return message;
    }
}
