package com.rean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
