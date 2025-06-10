package com.job.service.service;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface JobServiceImpl {
    ResponseEntity<JobResponse> addJob(JobServiceInput jobserviceinput);
    HashMap<String,Object> get_job_count_by_companyid(int company_id);
}
