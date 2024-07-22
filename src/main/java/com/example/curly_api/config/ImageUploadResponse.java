package com.example.curly_api.config;

import lombok.Data;

@Data
public class ImageUploadResponse {
    private int status;
    private String message;
    private String url;
}
