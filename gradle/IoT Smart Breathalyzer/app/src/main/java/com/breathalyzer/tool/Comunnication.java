package com.breathalyzer.tool;

import com.breathalyzer.socket.Socket;
import com.breathalyzer.socket.SocketListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Comunnication implements SocketListener {

    private JLabel alcohol;
    private JLabel temperature;
    private boolean run;
    private Socket server;

    public Comunnication(JLabel alcohol, JLabel temperature) {
        this.alcohol = alcohol;
        this.temperature = temperature;
        this.run = false;
    }
    
    public void runSocket() {
        while(run) {
            server = new Socket();
            server.addSocketListener(this);
            server.runServer();
        }
    }

    public boolean isRun() {
        return run;
    }
    
    public void startSocket() {
        this.run = true;
    }
    
    public void stopSocket() {
        try {
            this.run = false;
            server.forceClose();
        } catch (IOException ex) {
            Logger.getLogger(Comunnication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void changeValueSocket(Socket server) {
        System.out.println("New value: " + server.getMessage());
        String [] data = server.getMessage().split(":");
        alcohol.setText(data[0]);
        temperature.setText(data[1]);
    }
}
