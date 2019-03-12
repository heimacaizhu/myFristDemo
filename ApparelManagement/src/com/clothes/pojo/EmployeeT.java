package com.clothes.pojo;

public class EmployeeT {
    private String emId;
    private String emName;
    private String emAcount;
    private String emPwd;
    private String emType;

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId;
    }

    public String getEmAcount() {
        return emAcount;
    }

    public void setEmAcount(String emAcount) {
        this.emAcount = emAcount;
    }

    public String getEmPwd() {
        return emPwd;
    }

    public void setEmPwd(String emPwd) {
        this.emPwd = emPwd;
    }

    public String getEmType() {
        return emType;
    }

    public void setEmType(String emType) {
        this.emType = emType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emId='" + emId + '\'' +
                ", emName='" + emName + '\'' +
                ", emAcount='" + emAcount + '\'' +
                ", emPwd='" + emPwd + '\'' +
                ", emType='" + emType + '\'' +
                '}';
    }
}
