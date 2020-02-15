package com.example.smarteducation;

class UserInformation {

    public String userId, name, classs, phone;

    public UserInformation(String userId, String name, String classs, String phone) {
        this.userId = userId;
        this.name = name;
        this.classs = classs;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
