package com.breathalyzer.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private final String host;
    private final int port;
    protected String welcome;
    protected ServerSocket server;
    protected Socket client;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;   
    }
    
    public ServerSocket getServer() {
        return server;
    }

    public Socket getClient() {
        return client;
    }
    
    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
    
    public void createSocket() throws IOException {
        this.server = new ServerSocket(this.port);
        this.client = new Socket();
    }
}
