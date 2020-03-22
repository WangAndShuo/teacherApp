package com.classproject.teacherapp.entity;


import lombok.Data;

@Data
public class UserInfo {

    private String uuid;

    private String name;

    private String sex;

    private Integer age;

    private String brithday;

    private String address;

    private String school;

    private String college;

    private String education;

    public String getUuid() {
        return uuid;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBrithday() {
        return brithday;
    }


    public void setBrithday(String brithday) {
        this.brithday = brithday == null ? null : brithday.trim();
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSchool() {
        return school;
    }


    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getCollege() {
        return college;
    }


    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }


    public String getEducation() {
        return education;
    }


    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }
}