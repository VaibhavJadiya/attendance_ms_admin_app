package com.vk.creations.summerproject;

class AssignmentHelperClass {
    String name,link,subject;

    public AssignmentHelperClass() {
    }

    public AssignmentHelperClass(String name, String link, String subject) {
        this.name = name;
        this.link = link;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
