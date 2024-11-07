package com.project.RegisterSystem.dto.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseStatusDto {
    private int status;
    private String message;
}
