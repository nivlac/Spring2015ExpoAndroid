package com.cs100w.calvin.spring2015expoapp;

/**
 * TeamProject class to hold information about each team
 */
public class TeamProject {

   private String id;
   private String projectName;
   private String projectSummary;
   private String pathToImg;
   private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }

    public String getPathToImg() {
        return pathToImg;
    }

    public void setPathToImg(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public TeamProject(String pathToImg, String id, String projectName, String projectSummary) {
        this.pathToImg = pathToImg;
        this.id = id;
        this.projectName = projectName;
        this.projectSummary = projectSummary;
        rating = 0;
    }
}
