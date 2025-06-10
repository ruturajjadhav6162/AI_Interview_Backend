package com.job.service.service;

import com.job.service.dto.JobResponse;
import com.job.service.dto.JobServiceInput;
import com.job.service.entity.JobService;
import com.job.service.repository.JobServiceRepository;
import com.job.service.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JobServices implements JobServiceImpl{

    private JobServiceRepository jobServiceRepository;

    @Override
    public ResponseEntity<JobResponse> addJob(JobServiceInput jobserviceinput) {
        JobService jobservice = new JobService();
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
        jobservice.setApplicationDeadline(jobservice.getApplicationDeadline());

        jobServiceRepository.save(jobservice);

        JobResponse jobResponse = JobResponse.builder()
                .jobTitle(jobservice.getJobTitle())
                .jobDescription(jobservice.getJobDescription())
                .jobResponseMessage(new Response().getResponse())
                .build();

        return new ResponseEntity<>(jobResponse, HttpStatus.OK);
    }

    public int get_job_count_by_companyid(){
        int company_id=0;
        return (int)jobServiceRepository.countAllByCompanyId(company_id);
    }
}
