package com.cprack.domain;

public class Employee {
    private Integer staffId;

    private String staffName;

    private String staffGender;

    private String staffEmail;

    private Integer gid;

    private Department department;

    public Employee() {
    }

    public Employee(Integer staffId, String staffName, String staffGender, String staffEmail, Integer gid) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.staffEmail = staffEmail;
        this.gid = gid;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender == null ? null : staffGender.trim();
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail == null ? null : staffEmail.trim();
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}