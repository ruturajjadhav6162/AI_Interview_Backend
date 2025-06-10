package com.job.service.service;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.entity.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface JobServiceImpl {
    ResponseEntity<JobResponse> addJob(JobServiceInput jobserviceinput);
}
