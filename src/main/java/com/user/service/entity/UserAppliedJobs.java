package com.user.service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_registration")
@Data
public class UserAppliedJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int jobId;
    private int companyId;
}
