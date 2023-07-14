package com.nobroker.smarthome.controllers;


import com.google.cloud.firestore.*;
import com.nobroker.smarthome.firebase.Firebase;
import com.nobroker.smarthome.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rest")
public class RestClientController {

    @Autowired
    private RestClientService restClientService;



    @PostMapping("/getCall")
    public String makeRestGetCall(@RequestBody String url) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("param1", "param1");
        parameters.add("param2", "param2");
        parameters.add("param3", "param3");
        MediaType mediaType  = MediaType.APPLICATION_JSON;
        String bearerToken = "someToken";
        return restClientService.makeRestCall(url, parameters, bearerToken, mediaType, HttpMethod.GET);
    }



    @PostMapping("/postCall")
    public String makeRestPOSTCall(@RequestBody String url) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("param1", "param1");
        parameters.add("param2", "param2");
        parameters.add("param3", "param3");
        MediaType mediaType  = MediaType.APPLICATION_JSON;
        String bearerToken = "someToken";
        return restClientService.makeRestCall(url, parameters, bearerToken, mediaType, HttpMethod.POST);
    }

    @GetMapping("/addHSLead")
    public String addHSLead(){
        String url = "https://www.nobroker.in/admin/leads/home-service/add";
        MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED;
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name","Prakhar Pandey");
        parameters.add("phone","8209444272");
        parameters.add("email","prakhar.pandey@nobroker.in");
        parameters.add("servicesTypes","HS_ELECTRICIAN_LEAD");
        parameters.add("city","BANGALORE");
        parameters.add("source","SELLER_RM_LEAD");
        parameters.add("comment","Electrician needed");
        return restClientService.makeRestCall(url, parameters, null, mediaType, HttpMethod.POST);
    }
}
