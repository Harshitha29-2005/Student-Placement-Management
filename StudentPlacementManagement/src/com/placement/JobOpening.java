
package com.placement;

public class JobOpening {

    private int jobId;
    private int companyId;
    private String jobTitle;
    private double salaryLpa;

    public JobOpening(int jobId, int companyId,
                      String jobTitle, double salaryLpa) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.jobTitle = jobTitle;
        this.salaryLpa = salaryLpa;
    }

    public int getJobId() {
        return jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalaryLpa() {
        return salaryLpa;
    }
}
