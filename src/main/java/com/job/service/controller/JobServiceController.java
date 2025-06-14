package com.job.service.controller;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.dto.UserDetailsFromToken;
import com.job.service.entity.JobService;
import com.job.service.service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job_service/")
public class JobServiceController {
    @Autowired
    private JobServices jobService;

    @PostMapping("create")
    public ResponseEntity<JobResponse> addJob(@RequestBody JobServiceInput jobserviceinput){
        return jobService.addJob(jobserviceinput);
    }

    @GetMapping("getcount")
    public ResponseEntity<HashMap<String,Object>> getCount(){
        UserDetailsFromToken userDetailsFromToken= (UserDetailsFromToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int companyId=userDetailsFromToken.getCompanyId();
        return ResponseEntity.ok(jobService.get_job_count_by_companyid(companyId));
    }
    
    @GetMapping("getJobs")
    public List<JobService> getJobService(){
        return jobService.getAllJobs();
    }
}
