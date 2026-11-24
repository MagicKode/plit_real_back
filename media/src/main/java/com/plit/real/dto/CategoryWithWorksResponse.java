package com.plit.real.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryWithWorksResponse {

    private Long id;
    private String name;
    private String coverImage;
    private List<MediaResponse> works;
}
