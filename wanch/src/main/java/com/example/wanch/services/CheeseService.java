package com.example.wanch.services;

import com.example.wanch.DTOs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@Service
public class CheeseService {

    private final RestClient restClient;
    private final String apiKey;
    private final ObjectMapper mapper;

    public CheeseService(RestClient.Builder builder, @Value("${api.spoonacular.key}") String apiKey, ObjectMapper mapper) {
        this.restClient = builder
                .baseUrl("https://api.spoonacular.com")
                .build();
        this.apiKey = (apiKey != null) ? apiKey.trim().replace("\"", "") : "";
        this.mapper = mapper;
    }

    public CheeseInformationDTO getCheeseDescription(String cheeseName) {
        if (cheeseName == null || cheeseName.isBlank()) {
            return null;
        }

        try {
            // 1. Search for ingredient ID using explicit template placeholders
            IngredientSearchResult searchResult = restClient.get()
                    .uri("/food/ingredients/search?query={query}&number=1&apiKey={key}",
                            cheeseName.trim().toLowerCase(),
                            this.apiKey)
                    .retrieve()
                    .body(IngredientSearchResult.class);

            if (searchResult == null || searchResult.results() == null || searchResult.results().isEmpty()) {
                return null;
            }

            Long ingredientId = searchResult.results().get(0).id();

            String rawJson =  restClient.get()
                    .uri("/food/ingredients/{id}/information?amount=1&apiKey={key}",
                            ingredientId,
                            this.apiKey)
                    .retrieve()
                    .body(String.class);

            System.out.println("raw json for: "+ cheeseName + " is "+ rawJson);
         return  mapper.readValue(rawJson, CheeseInformationDTO.class);
        } catch (Exception e) {
            System.err.println("Error fetching cheese description for " + cheeseName + ": " + e.getMessage());
            return null;
        }
    }
}