package com.user.service.dto;


public class CompanyProfessionalAndCompanyRegistration {
        private String companyName;
        private String username;

        public CompanyProfessionalAndCompanyRegistration(String companyName, String username) {
                this.companyName = companyName;
                this.username = username;
        }

        public String getCompanyName() {
                return companyName;
        }

        public void setCompanyName(String companyName) {
                this.companyName = companyName;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }
}
