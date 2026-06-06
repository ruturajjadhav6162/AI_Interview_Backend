package com.job.service.controller;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.dto.UserDetailsFromToken;
import com.job.service.entity.JobService;
import com.job.service.service.JobServices;
import com.job.service.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job_service/")
public class JobServiceController {
    @Autowired
    private JobServices jobService;

    private final WebClient webClient = WebClient.create("http://localhost:8002/companyProfessional");
    @Autowired
    private JWTUtil jWTUtil;

    @PostMapping("create")
    public ResponseEntity<JobResponse> addJob(@RequestBody JobServiceInput jobserviceinput){
        return jobService.addJob(jobserviceinput);
    }

    @GetMapping("getcount")
    public ResponseEntity<HashMap<String,Object>> getCount(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        int companyId=-1;
        if(token.startsWith("Bearer ")){
            token = token.substring(7);
            Claims body=jWTUtil.extractToken(token);
            String username = body.getSubject();
           companyId=webClient.get()
                    .uri("/id/{username}",username)
                    .header("Authorization","Bearer "+token)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
        }
        else {
            throw new RuntimeException("Invalid token");
        }
//        UserDetailsFromToken userDetailsFromToken= (UserDetailsFromToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        int companyId=userDetailsFromToken.getCompanyId();
        return ResponseEntity.ok(jobService.get_job_count_by_companyid(companyId));
    }
    
    @GetMapping("getJobs")
    public List<JobService> getJobService(){
        return jobService.getAllJobs();
    }

    @GetMapping("getJobById/{id}")
    public JobService getJobById(@PathVariable int id){
        return jobService.getJobById(id);
    }
}
