package com.nobroker.smarthome.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class RestClientService {

    private static ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(RestClientService.class);

    static {
        objectMapper = new ObjectMapper();
    }

    public String makeRestCall(String url,
                               MultiValueMap<String, String> parameters,
                               String bearerToken,
                               MediaType mediaType,
                               HttpMethod method) {

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        if(!bearerToken.isBlank()){
            headers.set("Authorization", bearerToken);
        }

//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    method,
                    entity,
                    String.class
            );

            HttpStatusCode statusCode = response.getStatusCode();
            if(statusCode.equals(HttpStatus.OK)){
                return response.getBody();
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("Unable to make the rest call ", e);
            return null;
        }

    }

    public String makeRestCall(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
