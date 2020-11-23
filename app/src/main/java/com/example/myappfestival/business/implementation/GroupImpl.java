package com.example.myappfestival.business.implementation;

import com.example.myappfestival.business.interfaces.GroupInterface;
import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.TypeScene;
import com.example.myappfestival.model.User;

import java.util.List;

public class GroupImpl implements GroupInterface {
    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public List<Group> getFavoritesGroup(User user) {
        return null;
    }

    @Override
    public List<Group> getGroupesBySettingCriteria(String day, TypeScene scene) {
        return null;
    }
}
