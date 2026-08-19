package com.example.wanch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheeseDTO {
    @JsonProperty("cheese")
    private String cheeseName;
    @JsonProperty("description")
    private String cheeseDescription;

    public CheeseDTO(String cheeseDescription, String cheeseName) {
        this.cheeseDescription = cheeseDescription;
        this.cheeseName = cheeseName;
    }
    public CheeseDTO(){}

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String cheeseName) {
        this.cheeseName = cheeseName;
    }

    public String getCheeseDescription() {
        return cheeseDescription;
    }

    public void setCheeseDescription(String cheeseDescription) {
        this.cheeseDescription = cheeseDescription;
    }
}
