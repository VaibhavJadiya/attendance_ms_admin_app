package com.vk.creations.summerproject;

class AttendanceHelperClass {
    String name,username;

    public AttendanceHelperClass() {
    }

    public AttendanceHelperClass(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
