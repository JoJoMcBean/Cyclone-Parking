package com.example.cyparking;

public class DefaultUserSchema extends UserSchema {
    private String username;
    private String password;
    private String userType;
    private String email;

    private String licenseNum;
    private String creditCardNum;

    public DefaultUserSchema(String username, String password, String userType, String email, String licenseNum, String creditCardNum) {
        super(username, password, userType, email);
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
}
