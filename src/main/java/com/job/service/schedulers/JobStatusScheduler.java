package com.job.service.schedulers;

import com.job.service.entity.JobService;
import com.job.service.repository.JobServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobStatusScheduler {
    @Autowired
    private JobServiceRepository jobServiceRepository;

    @Scheduled(cron = "0 0 12 * * *" )
    public void jobStatusScheduler() {
        List<JobService> jobServices = jobServiceRepository.findAllByStatus("ACTIVE");
        jobServices.forEach(jobService -> {
            if(LocalDate.now().equals(jobService.getApplicationDeadline())){
            jobService.setStatus("EXPIRED");
            jobServiceRepository.save(jobService);
            System.out.println(jobService.getJobTitle()+" "+jobService.getApplicationDeadline()+" "+jobService.getStatus());
        }});
    }
}
