package com.breathalyzer.firebase;

import com.breathalyzer.model.Register;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Setter {
    
    private static FirebaseDatabase database;
    private final String path;

    public Setter() throws ExecutionException, IOException {
        database = new Firebase().connect();
        path = "daily-analysis";
    }
    
    public void createData(String identify, Register register) throws ExecutionException, InterruptedException {
        DatabaseReference set = database.getReference(path).child(identify);
        Map <String, Register> registers = new HashMap<>();
        
        registers.put(String.valueOf(register.getId()), register);

        ApiFuture <Void> future = set.setValueAsync(registers);
        future.get();
    }
    
    public void createData(String identify, ArrayList<Register> register) throws ExecutionException, InterruptedException {
        DatabaseReference set = database.getReference(path).child(identify);
        Map <String, Register> registers = new HashMap<>();
        
        register.forEach((element) -> {
            registers.put(String.valueOf(element.getId()), element);
        });

        ApiFuture <Void> future = set.setValueAsync(registers);
        future.get();
    }
    
//    public static void main(String[] args) {
//        
//        try {
//            Setter set = new Setter();
//            Register data = new Register(0, "ihj", "khjg", "knjhf", "khjgh", 0, 0, "hgj", "ojukjghf", "khjgfgd");
//            ArrayList<Register> register = new ArrayList<>();
//            register.add(data);
//            set.createData("test", data);
//            set.createData("test 2", register);
//        } catch (ExecutionException ex) {
//            Logger.getLogger(Setter.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Setter.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Setter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}
