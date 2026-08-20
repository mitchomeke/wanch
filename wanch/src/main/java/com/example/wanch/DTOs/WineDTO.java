package com.example.wanch.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WineDTO {
    @JsonProperty("wine")
    private String wineName;
    @JsonProperty("wineDescription")
    private String wineDescription;


    public WineDTO(String wineName, String wineDescription) {
        this.wineName = wineName;
        this.wineDescription = wineDescription;
    }
    public WineDTO(){}

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getWineDescription() {
        if (wineDescription == null){
            return "No detailed description available for this wine selection yet.";
        }
        return wineDescription;
    }

    public void setWineDescription(String wineDescription) {
        this.wineDescription = wineDescription;
    }
}
