package com.job.service.repository;

import com.job.service.entity.JobService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobServiceRepository extends JpaRepository<JobService, Integer> {
    int countAllByCompanyId(int companyId);
}
