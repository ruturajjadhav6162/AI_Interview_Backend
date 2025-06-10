package com.job.service.controller;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.entity.JobService;
import com.job.service.service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/job_service/")
public class JobServiceController {
    @Autowired
    private JobServices jobService;

    @PostMapping("create")
    public ResponseEntity<JobResponse> addJob(@RequestBody JobServiceInput jobserviceinput){
        return jobService.addJob(jobserviceinput);
    }

    @GetMapping("getcount/{company_id}")
    public ResponseEntity<HashMap<String,Object>> getCount(@PathVariable int company_id){
        return ResponseEntity.ok(jobService.get_job_count_by_companyid(company_id));
    }
}
