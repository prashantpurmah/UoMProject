package dev.edmt.androidcamerarecognitiontext.entities;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class EmployeeAssetModel {

    @Expose
    private String employeeId;
    @Expose
    private String assetId;

    private Date time_taken;
    private Date time_returned;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Date getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(Date time_taken) {
        this.time_taken = time_taken;
    }

    public Date getTime_returned() {
        return time_returned;
    }

    public void setTime_returned(Date time_returned) {
        this.time_returned = time_returned;
    }
}
