package com.cs100w.calvin.spring2015expoapp;

/**
 * Created by calvin on 3/15/15.
 *
 * Student class to hold data about each student.
 */
public class Student {

    private String id;
    private String name;
    private String summary;
    private String skillA;
    private String skillB;
    private String pathToImg;
    private String linkedInURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSkillA() {
        return skillA;
    }

    public void setSkillA(String skillA) {
        this.skillA = skillA;
    }

    public String getSkillB() {
        return skillB;
    }

    public void setSkillB(String skillB) {
        this.skillB = skillB;
    }

    public String getPathToImg() {
        return pathToImg;
    }

    public void setPathToImg(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    public String getLinkedInURL() {
        return linkedInURL;
    }

    public void setLinkedInURL(String linkedInURL) {
        this.linkedInURL = linkedInURL;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private String project;

    public Student(String id, String name, String summary, String skillA, String skillB, String pathToImg, String linkedInURL, String project) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.skillA = skillA;
        this.skillB = skillB;
        this.pathToImg = pathToImg;
        this.linkedInURL = linkedInURL;
        this.project = project;
    }
}
