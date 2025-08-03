package com.user.service.dto;

public class CompanyProfessionalCreationResponse {
    private String professionalName;
    private String username;
    private String email;
    private String professionalCompany;
    private long phone;

    public CompanyProfessionalCreationResponse(String professionalName, String username, String email, String professionalCompany, long phone) {
        this.professionalName = professionalName;
        this.username = username;
        this.email = email;
        this.professionalCompany = professionalCompany;
        this.phone = phone;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfessionalCompany() {
        return professionalCompany;
    }

    public void setProfessionalCompany(String professionalCompany) {
        this.professionalCompany = professionalCompany;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}


