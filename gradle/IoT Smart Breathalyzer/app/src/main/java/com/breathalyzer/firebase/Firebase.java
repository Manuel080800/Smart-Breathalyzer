package com.breathalyzer.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Firebase {
    
    private final String key;
    private final String url;

    public Firebase() {
        key = "key.json";
        url = "https://alcoholimetro-inteligente-default-rtdb.firebaseio.com";
    }
    
    public FirebaseDatabase connect () throws FileNotFoundException, IOException {
       
        FileInputStream serviceAccount = new FileInputStream(key);

        FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl(url)
          .setDatabaseAuthVariableOverride(null)
          .build();

        FirebaseApp defaultApp = FirebaseApp.initializeApp(options);
        return FirebaseDatabase.getInstance(defaultApp);
    }    
    
}
