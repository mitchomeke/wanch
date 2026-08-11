package com.example.wanch.resources;

import java.util.List;

public record Cheese(
        String cheeseName,
        List<Wine> allWine
){}
