package com.plit.real.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaResponse {
    private Long id;
    private String url;
    private String originalName;
}
