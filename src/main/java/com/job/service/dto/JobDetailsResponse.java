package com.job.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class JobDetailsResponse {
    private int jobId;
    private String jobTitle;
    private String department;
    private String location;
    private String jobType;
    private String experienceRequired;
    private String salaryRange;
    private LocalDate applicationDeadline;
    private LocalDate createdAt;
    private String status;
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
    private String tokenWithCompanyId;
}
