package com.example.moviedbtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String id;
    private String name;
    private String url;
    private String publicationDate;
    private boolean favorit;
}
