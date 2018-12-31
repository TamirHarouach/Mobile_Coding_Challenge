package com.yugiha.mobile_coding_challenge;

public class RepoClass {
    String repo_Name,repo_Description,Repo_ImageUrl,Repo_Owner_Name;
    int Stars;

    public RepoClass(String repo_Name, String repo_Description, String repo_ImageUrl, String repo_Owner_Name, int stars) {
        this.repo_Name = repo_Name;
        this.repo_Description = repo_Description;
        Repo_ImageUrl = repo_ImageUrl;
        Repo_Owner_Name = repo_Owner_Name;
        Stars = stars;
    }

    public String getRepo_Name() {
        return repo_Name;
    }

    public String getRepo_Description() {
        return repo_Description;
    }


    public String getRepo_ImageUrl() {
        return Repo_ImageUrl;
    }


    public String getRepo_Owner_Name() {
        return Repo_Owner_Name;
    }


    public int getStars() {
        return Stars;
    }

}
