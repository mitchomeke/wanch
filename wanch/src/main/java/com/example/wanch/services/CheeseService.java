package com.example.wanch.services;

import com.example.wanch.CheeseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CheeseService {
    private final RestClient restClient;

    public CheeseService(RestClient.Builder builder,   @Value("${api.spoonacular.key}") String apiKey) {
        this.restClient = builder.baseUrl("https://api.spoonacular.com").defaultRequest(
                requestHeadersSpec -> requestHeadersSpec.header("x-api-key",apiKey)
        ).build();
    }
    public CheeseDTO getCheeseDescription(String cheeseName){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/food/ingredients/search")
                        .queryParam("query",cheeseName)
                        .build())
                .retrieve()
                .body(CheeseDTO.class);
    }
}
