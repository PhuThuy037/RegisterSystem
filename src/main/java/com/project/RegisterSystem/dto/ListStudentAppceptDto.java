package com.project.RegisterSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListStudentAppceptDto {
    private String eventId;
    private String studentId;
    private String MSSV;
    private String eventName;
}
