package com.job.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobResponse {
    private String jobTitle;
    private String jobDescription;
    private String jobResponseMessage;

}
