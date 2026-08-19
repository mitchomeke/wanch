package com.example.wanch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WineDTO {
    @JsonProperty("wine")
    private String wineName;
    @JsonProperty("description")
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
        return wineDescription;
    }

    public void setWineDescription(String wineDescription) {
        this.wineDescription = wineDescription;
    }
}
