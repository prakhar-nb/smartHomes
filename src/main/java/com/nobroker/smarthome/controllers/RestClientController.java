package com.nobroker.smarthome.controllers;


import com.nobroker.smarthome.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
}
