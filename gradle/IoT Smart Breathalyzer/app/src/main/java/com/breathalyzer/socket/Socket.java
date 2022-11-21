package com.breathalyzer.socket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Socket {
    private String message;
    private SocketListener listener;
    private Server server;
    
    public void runServer () {
        try {
            server = new Server("localhost", 2121);
            server.startServer();
            this.message = server.getMessage();

            if(listener != null) {
                     listener.changeValueSocket(this);
            }
        } catch (IOException ex) {
            System.err.println("A fatal error occurred while starting the server");
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMessage() {
        return message;
    }
    
    public void forceClose() throws IOException{
        server.forceClose();
    }

    public void addSocketListener(SocketListener listener) {
        this.listener = listener;
    }
}
