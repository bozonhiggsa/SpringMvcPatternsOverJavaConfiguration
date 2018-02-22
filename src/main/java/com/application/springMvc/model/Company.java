package com.application.springMvc.model;

/**
 * Model of a Company
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Company {

    private long companyId;
    private String companyName;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
