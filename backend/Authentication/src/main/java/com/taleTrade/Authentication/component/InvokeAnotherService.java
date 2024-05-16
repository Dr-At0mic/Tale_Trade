package com.taleTrade.Authentication.component;

import com.taleTrade.Authentication.systemUtils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InvokeAnotherService {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private RestTemplate restTemplate;

    public void SendVerificationMail(String emailAddress, String activationToken) {
        try {
            taskExecutor.execute(() -> {
                try {
                    String url = SystemConstants.SEND_VERIFICATION_MAIL;
                    String requestBody = "{\"emailAddress\": \"" + emailAddress + "\", \"activationToken\": \"" + activationToken + "\"}";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                    HttpStatusCode statusCode = responseEntity.getStatusCode();
                    HttpHeaders responseHeaders = responseEntity.getHeaders();
                    String responseBody = responseEntity.getBody();
                    System.out.println("Status Code: " + statusCode);
                    System.out.println("Response Body: " + responseBody);
                } catch (Exception e) {
                    System.err.println("Error occurred while sending verification email: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Error occurred while scheduling task: " + e.getMessage());
        }
    }


}
