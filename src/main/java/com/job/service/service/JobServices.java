package com.job.service.service;

import com.job.service.dto.JobDetailsResponse;
import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.dto.UserDetailsFromToken;
import com.job.service.entity.JobService;
import com.job.service.repository.JobServiceRepository;
import com.job.service.utils.JWTUtil;
import com.job.service.utils.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class JobServices implements JobServiceImpl{
    @Autowired
    private JobServiceRepository jobServiceRepository;
    @Autowired
    JWTUtil jwtUtil;
    private JobDetailsResponse jobDetailsResponse;

    //    Uses Token Generated form CompanyService

    @Override
    public ResponseEntity<JobResponse> addJob(JobServiceInput jobserviceinput) {
        JobService jobservice = new JobService();
        UserDetailsFromToken userDetails = (UserDetailsFromToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int companyId = userDetails.getCompanyId();
        jobservice.setCompanyId(companyId);
        jobservice.setJobTitle(jobserviceinput.getJobTitle());
        jobservice.setDepartment(jobserviceinput.getDepartment());
        jobservice.setLocation(jobserviceinput.getLocation());
        jobservice.setJobType(jobserviceinput.getJobType());
        jobservice.setExperienceRequired(jobserviceinput.getExperienceRequired());
        jobservice.setSalaryRange(jobserviceinput.getSalaryRange());
        jobservice.setTeamSize(jobserviceinput.getTeamSize());
        jobservice.setTags(jobserviceinput.getTags());
        jobservice.setRemoteWorkAvailable(jobserviceinput.isRemoteWorkAvailable());
        jobservice.setUrgentHiring(jobserviceinput.isUrgentHiring());
        jobservice.setPublishImmediately(jobserviceinput.isPublishImmediately());
        jobservice.setJobDescription(jobserviceinput.getJobDescription());
        jobservice.setResponsibilities(jobserviceinput.getResponsibilities());
        jobservice.setRequirements(jobserviceinput.getRequirements());
        jobservice.setBenefits(jobserviceinput.getBenefits());
        jobservice.setInterviewProcess(jobserviceinput.getInterviewProcess());
//        jobservice.setCompanyId(companyId);
        jobservice.setApplicationDeadline(jobserviceinput.getApplicationDeadline());

        jobServiceRepository.save(jobservice);

        JobResponse jobResponse = JobResponse.builder()
                .jobTitle(jobservice.getJobTitle())
                .jobDescription(jobservice.getJobDescription())
                .jobResponseMessage(new Response().getResponse())
                .build();

        return new ResponseEntity<>(jobResponse, HttpStatus.OK);
    }

    //    Uses Token Generated form CompanyService

    @Override
    public HashMap<String, Object> get_job_count_by_companyid(int company_id){
//        int company_id=0;
        HashMap<String,Object> jobServiceMap=new HashMap<>();
        List<JobService> jobServiceList=jobServiceRepository.findAllByCompanyId(company_id);
        int count=jobServiceRepository.countAllByCompanyId(company_id);
        jobServiceMap.put("jobCount",count);
        jobServiceMap.put("jobServiceList",jobServiceList);
        jobServiceMap.put("topJobs", jobServiceList.stream().limit(5).toList());
        return jobServiceMap;
    }

    //    Uses Token Generated form UserService

    @Override
    public List<JobService> getAllJobs(){
        return jobServiceRepository.findAllByStatus("ACTIVE");
    }

    //    Uses Token Generated form CompanyService

    @Override
    public JobService getJobById(int id) {
        Optional<JobService> jobService = jobServiceRepository.findById(id).stream().filter(x -> x.getStatus().equals("ACTIVE")).findFirst();
        if (jobService.isPresent()) {
            return jobService.get();
        }
        throw new RuntimeException("Job not Found");
    }
}

