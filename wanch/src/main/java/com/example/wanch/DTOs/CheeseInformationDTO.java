package com.example.wanch.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CheeseInformationDTO(Long id, String name,
                                   @JsonProperty("aisle") String description,
                                   List<String> consistency){}
