package com.job.service.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobServiceInput {
    private String jobTitle;
    private String department;
    private String location;
    private String jobType;
    private String experienceRequired;
    private String salaryRange;
    private String teamSize;
    private List<String> tags;
    private boolean remoteWorkAvailable;
    private boolean urgentHiring;
    private boolean publishImmediately;
    private String jobDescription;
    private String responsibilities;
    private String requirements;
    private String benefits;
    private String interviewProcess;
    private LocalDate applicationDeadline;
}
