package com.nobroker.smarthome.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.nobroker.smarthome.firebase.Firebase;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    private Firebase firebase;

    @GetMapping("/{appliance}/{state}")
    public String syncState(@PathVariable String state, @PathVariable String appliance) throws Exception {
        Firestore firestore  = firebase.getFireStore();


        if(!appliance.equalsIgnoreCase("bulb") &&  !appliance.equalsIgnoreCase("fan")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        }

        // Update an existing document
        DocumentReference docRef = firestore.collection("smarthomes").document(appliance);


        ApiFuture<WriteResult> future = docRef.update("state", state);

        WriteResult result = future.get();
        System.out.println("Write result: " + result);
        return "state updated to "+state;
    }
}
