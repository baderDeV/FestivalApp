package com.example.myappfestival.business.interfaces;

import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.TypeScene;
import com.example.myappfestival.model.User;

import java.util.List;

public interface GroupInterface {
    public List<Group> getAllGroups(TypeScene scene,String day);
    public Group getGroupById(Long id);
    public List<Group> getFavoritesGroup(User user,TypeScene scene,String day);
    public List<Group> getGroupesBySettingCriteria(String typeList,String day, TypeScene scene,User user);
    public void likeOrDislikeAGroup(User user,Group grp,boolean like);
}
