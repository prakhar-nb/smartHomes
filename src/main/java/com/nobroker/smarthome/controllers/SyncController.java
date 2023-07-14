package com.nobroker.smarthome.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.nobroker.smarthome.firebase.Firebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    private Firebase firebase;

    @GetMapping("/bulb/{state}")
    public String syncState(@PathVariable String state) throws Exception {
        Firestore firestore  = firebase.getFireStore();

        // Update an existing document
        DocumentReference docRef = firestore.collection("smarthomes").document("bulb");


        ApiFuture<WriteResult> future = docRef.update("state", state);

        WriteResult result = future.get();
        System.out.println("Write result: " + result);
        return "state updated to "+state;
    }
}
