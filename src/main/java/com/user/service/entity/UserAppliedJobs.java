package com.user.service.entity;

import jakarta.persistence.*;
//import lombok.*;

@Entity
@Table(name = "user_registration")

public class UserAppliedJobs {
    public UserAppliedJobs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int jobId;
    private int companyId;

    public UserAppliedJobs(int id, String username, int jobId, int companyId) {
        this.id = id;
        this.username = username;
        this.jobId = jobId;
        this.companyId = companyId;
    }
}
