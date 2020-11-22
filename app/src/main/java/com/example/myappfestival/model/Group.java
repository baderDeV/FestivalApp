package com.example.myappfestival.model;

import java.util.Date;

public class Group {
   private Long id;
   private String groupeName;
   private String description;
   private Date dayShow;
   private TypeScene typeScene;
   private boolean isplayed;

    public Group(String groupeName, String description, Date dayShow, TypeScene typeScene, boolean isplayed) {
        this.groupeName = groupeName;
        this.description = description;
        this.dayShow = dayShow;
        this.typeScene = typeScene;
        this.isplayed = isplayed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDayShow() {
        return dayShow;
    }

    public void setDayShow(Date dayShow) {
        this.dayShow = dayShow;
    }

    public TypeScene getTypeScene() {
        return typeScene;
    }

    public void setTypeScene(TypeScene typeScene) {
        this.typeScene = typeScene;
    }

    public boolean isIsplayed() {
        return isplayed;
    }

    public void setIsplayed(boolean isplayed) {
        this.isplayed = isplayed;
    }
}