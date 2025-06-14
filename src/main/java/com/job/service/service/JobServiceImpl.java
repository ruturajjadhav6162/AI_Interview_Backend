package com.job.service.service;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.entity.JobService;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface JobServiceImpl {
    ResponseEntity<JobResponse> addJob(JobServiceInput jobserviceinput);
    HashMap<String,Object> get_job_count_by_companyid(int company_id);
    List<JobService> getAllJobs();
}
