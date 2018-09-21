package dev.edmt.androidcamerarecognitiontext;

import com.google.gson.annotations.Expose;

public class LoginEntity {

    @Expose
    private String employeeId;
    @Expose
    private String password;

    public LoginEntity() { }

    public LoginEntity(String employeeId, String password) {
        this.employeeId = employeeId;
        this.password = password;
    }

    public String getUserId() {
        return employeeId;
    }

    public void setUserId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
