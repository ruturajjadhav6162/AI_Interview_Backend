package com.job.service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@Table(name = "job_service")
public class JobService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private String jobTitle;
    private String department;
    private String location;
    private String jobType;
    private String experienceRequired;
    private String salaryRange;

    @Temporal(TemporalType.DATE)
    private LocalDate applicationDeadline;

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
    private int companyId;
//    private String aboutCompany;
}
