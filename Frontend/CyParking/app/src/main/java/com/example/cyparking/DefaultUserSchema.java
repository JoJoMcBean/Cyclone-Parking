package com.example.cyparking;

public class DefaultUserSchema {

        private String username;
        private String password;
        private String userType;
        private String email;

        private String licenseNum;
        private String creditCardNum;

        public DefaultUserSchema(String username, String password, String userType, String email, String licenseNum, String creditCardNum) {
            this.username = username;
            this.password = password;
            this.userType = userType;
            this.email = email;
            this.licenseNum = licenseNum;
            this.creditCardNum = creditCardNum;
        }

        public String getLicenseNum() {
            return licenseNum;
        }

        public void setLicenseNum(String licenseNum) {
            this.licenseNum = licenseNum;
        }

        public String getCreditCardNum() {
            return creditCardNum;
        }

        public void setCreditCardNum(String creditCardNum) {
            this.creditCardNum = creditCardNum;
        }

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getUserType() {
            return userType;
        }


}
