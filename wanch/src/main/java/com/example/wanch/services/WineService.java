package com.example.wanch.services;

import com.example.wanch.WineDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WineService {
    private final RestClient restClient;

    public WineService(RestClient.Builder builder, @Value("${api.spoonacular.key}") String apiKey) {
        this.restClient = builder.baseUrl("https://api.spoonacular.com").defaultRequest(requestHeadersSpec ->
                requestHeadersSpec.header("x-api-key",apiKey)).build();
    }
    public WineDTO getWineDescription(String wineName){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/food/wine/description")
                        .queryParam("wine", wineName)
                        .build())
                .retrieve()
                .body(WineDTO.class);
    }
}
