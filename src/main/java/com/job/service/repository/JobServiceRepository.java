package com.job.service.repository;

import com.job.service.entity.JobService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobServiceRepository extends JpaRepository<JobService, Integer> {
    List<JobService> findAllByCompanyId(int companyId);
    int countAllByCompanyId(int companyId);
}
