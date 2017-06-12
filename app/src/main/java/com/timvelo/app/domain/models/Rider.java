package com.timvelo.app.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rider {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("forename")
    @Expose
    private String forename;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("flag")
    @Expose
    private Flag flag;
    @SerializedName("team")
    @Expose
    private Team team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rider withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Rider withForename(String forename) {
        this.forename = forename;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rider withName(String name) {
        this.name = name;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Rider withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Rider withFlag(Flag flag) {
        this.flag = flag;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Rider withTeam(Team team) {
        this.team = team;
        return this;
    }

}