package com.example.myappfestival.business.interfaces;

import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.TypeScene;
import com.example.myappfestival.model.User;

import java.util.List;

public interface GroupInterface {
    public List<Group> getAllGroups();
    public List<Group> getFavoritesGroup(User user);
    public List<Group> getGroupesBySettingCriteria(String day, TypeScene scene);
}
