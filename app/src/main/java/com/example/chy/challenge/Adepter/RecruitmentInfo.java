package com.example.chy.challenge.Adepter;

/**
 * Created by 77588 on 2016/10/29.
 */

public class RecruitmentInfo {
    private String title;
    private String company_name;
    private String realname;
    private String myjob;
    private String count;
    private String company_score;
    private String salary;
    private String distance;
    private String address;
    private String experience;
    private String education;
    private String work_property;

    public RecruitmentInfo(String title, String company_name, String realname, String myjob, String count, String company_score, String welfare,
                           String distance, String address, String experience, String education, String work_property) {
        this.title = title;
        this.company_name = company_name;
        this.realname = realname;
        this.myjob = myjob;
        this.count = count;
        this.company_score = company_score;
        this.salary = welfare;
        this.distance = distance;
        this.address = address;
        this.experience = experience;
        this.education = education;
        this.work_property = work_property;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getRealname() {
        return realname;
    }

    public String getMyjob() {
        return myjob;
    }

    public String getCount() {
        return count;
    }

    public String getCompany_score() {
        return company_score;
    }

    public String getSalary() {
        return "￥" + salary + "K";
    }

    public String getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public String getExperience() {
        return experience;
    }

    public String getEducation() {
        return education;
    }

    public String getWork_property() {
        return work_property;
    }
}
