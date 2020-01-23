package com.gpsolutions.edu.java.training.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Wladimir Litvinov
 */
@Data
@Builder
public class Course {
    private Long id;
    private String title;
    private String description;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate endDate;
    private String teacherName;
}
