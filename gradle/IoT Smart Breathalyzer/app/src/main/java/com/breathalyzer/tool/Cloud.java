package com.breathalyzer.tool;

import com.breathalyzer.firebase.Firebase;
import com.breathalyzer.firebase.Setter;
import com.breathalyzer.model.Register;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cloud {
    
    private Setter cloud;

    public Cloud() throws ExecutionException, IOException {
        cloud = new Setter();
    }
    
    public boolean uploadRegisters (String identify, ArrayList<Register> registers) {
        try {
            cloud.createData(identify, registers);
            return true;
        } catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(Cloud.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }    
}
