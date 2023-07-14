package com.nobroker.smarthome.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class Firebase {

    private  GoogleCredentials credentials;
    @PostConstruct
    public void init() throws IOException {
        credentials = GoogleCredentials.fromStream(getClass().getClassLoader().getResourceAsStream("firebaseCred.json"));
    }

    public GoogleCredentials getCredentials() {
        return credentials;
    }
    public Firestore getFireStore(){
        FirestoreOptions options = FirestoreOptions.newBuilder().setProjectId("project-riva-921af")
                .setCredentials(getCredentials())
                .build();
        return options.getService();
    }


}
