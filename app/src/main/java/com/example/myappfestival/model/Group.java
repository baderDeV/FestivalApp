package com.example.myappfestival.model;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
   private Long id;
   private String groupeName;
   private String description;
   private Date dayShow;
   private TypeScene typeScene;

    public Group(Long id,String groupeName, String description, Date dayShow, TypeScene typeScene) {
        this.id=id;
        this.groupeName = groupeName;
        this.description = description;
        this.dayShow = dayShow;
        this.typeScene = typeScene;
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


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupeName='" + groupeName + '\'' +
                ", description='" + description + '\'' +
                ", dayShow=" + dayShow +
                ", typeScene=" + typeScene +
                '}';
    }
}