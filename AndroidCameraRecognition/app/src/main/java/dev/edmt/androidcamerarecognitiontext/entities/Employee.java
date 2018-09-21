package dev.edmt.androidcamerarecognitiontext.entities;

public class Employee {

    private String employeeId;
    private String name;
    private Float score;

    public Employee() {
    }

    public Employee(String employeeId, String name, Float score) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + "]";
    }
}