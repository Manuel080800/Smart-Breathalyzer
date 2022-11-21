package com.breathalyzer.socket;

import java.util.EventListener;

public interface SocketListener extends EventListener {
    
    public void changeValueSocket(Socket server);
    
}
