package com.timvelo.app.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Race {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("flag")
    @Expose
    private Flag flag;
    @SerializedName("classification")
    @Expose
    private Classification classification;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("profilepic")
    @Expose
    private String profilepic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Race withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race withName(String name) {
        this.name = name;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Race withDate(String date) {
        this.date = date;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Race withProfile(Profile profile) {
        this.profile = profile;
        return this;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Race withFlag(Flag flag) {
        this.flag = flag;
        return this;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Race withClassification(Classification classification) {
        this.classification = classification;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Race withLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public Race withProfilepic(String profilepic) {
        this.profilepic = profilepic;
        return this;
    }

}