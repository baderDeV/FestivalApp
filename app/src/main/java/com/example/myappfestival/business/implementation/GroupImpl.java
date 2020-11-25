package com.example.myappfestival.business.implementation;

import com.example.myappfestival.business.interfaces.GroupInterface;
import com.example.myappfestival.business.util.ConnectionDatabase;
import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.TypeScene;
import com.example.myappfestival.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GroupImpl implements GroupInterface {
    Logger log = Logger.getLogger("SQLGroups");
    @Override
    public List<Group> getAllGroups(TypeScene scene,String day) {
        Connection connection = ConnectionDatabase.getConnection();
        String sql = "select * from groups";
        String whereClause="";
        if(scene!=null){
            whereClause=whereClause+"typeScene='"+(scene+"'");
        }
        if (whereClause.length()!=0){
            sql=sql+" where "+whereClause;
        }
        List<Group> group = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            log.info("GET ALL GROUPS");
            while (rs.next()){
                TypeScene ts = null;
                String valueTs = rs.getString("typescene");
                if(valueTs.equals("scene_acoustique")){
                    ts = TypeScene.scene_acoustique;
                } else if(valueTs.equals("scene_amplifie")){
                    ts = TypeScene.scene_amplifie;
                } else{
                    //nothing
                }
                java.sql.Date dateShow = rs.getDate("dayShow");
                String dayName = (new SimpleDateFormat("EEEE")).format(dateShow.getTime());
                log.info("DATA GETED : "+dayName+" / "+day);
                if(day.equals("NONE") || dayName.equals(day)) {
                    Group g = new Group(rs.getLong("id"),
                            rs.getString("groupName"),
                            rs.getString("description"),
                            rs.getDate("dayShow"),
                            ts);
                    group.add(g);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection();
        }
        return group;
    }

    @Override
    public Group getGroupById(Long id) {
        Connection connection = ConnectionDatabase.getConnection();
        String sql = "select * from groups where id="+id;
        Group group = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                TypeScene ts = null;
                String valueTs = rs.getString("typescene");
                if(valueTs.equals("scene_acoustique")){
                    ts = TypeScene.scene_acoustique;
                } else if(valueTs.equals("scene_amplifie")){
                    ts = TypeScene.scene_amplifie;
                } else{
                    //nothing
                }
                group = new Group(rs.getLong("id"),
                        rs.getString("groupName"),
                        rs.getString("description"),
                        rs.getDate("dayShow"),
                        ts);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection();
        }
        return group;
    }

    @Override
    public List<Group> getFavoritesGroup(User user,TypeScene scene,String day) {
        Connection connection = ConnectionDatabase.getConnection();
        String sql = "select g.* from groups g join favoritegroup fg on g.id=fg.id_group where fg.isliked=1 and fg.id_user="+user.getId();
        String whereClause="";
        if(scene!=null){
            whereClause=whereClause+"g.typeScene='"+(scene+"'");
        }
        if (whereClause.length()!=0){
            sql=sql+" and "+whereClause;
        }
        List<Group> group = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                TypeScene ts = null;
                String valueTs = rs.getString("typescene");
                if(valueTs.equals("scene_acoustique")){
                    ts = TypeScene.scene_acoustique;
                } else if(valueTs.equals("scene_amplifie")){
                    ts = TypeScene.scene_amplifie;
                } else{
                    //nothing
                }

                java.sql.Date dateShow = rs.getDate("dayShow");
                String dayName = (new SimpleDateFormat("EEEE")).format(dateShow.getTime());

                if(day.equals("NONE") || dayName.equals(day)){
                    Group g = new Group(rs.getLong("id"),
                            rs.getString("groupName"),
                            rs.getString("description"),
                            rs.getDate("dayShow"),
                            ts);
                    group.add(g);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection();
        }
        return group;
    }

    @Override
    public List<Group> getGroupesBySettingCriteria(String typeList,String day, TypeScene scene,User user) {
        List<Group> grps = new ArrayList<>();
        if(!typeList.equals("NONE") || !day.equals("NONE") || scene!=null){
            if(typeList.equals("All_Groups")){
                grps = getAllGroups(scene,day);
            } else if(typeList.equals("Favorite_Group")){
                grps = getFavoritesGroup(user,scene,day);
            } else{
                //
            }
        }
        return grps;
    }

    @Override
    public void likeOrDislikeAGroup(User user, Group grp,boolean like) {
        Connection connection = ConnectionDatabase.getConnection();
        String sql = "select fg.* from favoritegroup fg where fg.id_group="+grp.getId()+" and fg.id_user="+user.getId();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                sql = "update favoritegroup set isliked="+(like?1:0)+" where id_user="+user.getId()+" and id_group="+grp.getId();
                ps = connection.prepareStatement(sql);
                ps.executeUpdate();
            } else{
                sql = "insert into favoritegroup (id_user,id_group,isliked) values (?,?,?)";
                ps = connection.prepareStatement(sql);

                ps.setLong(1,user.getId());
                ps.setLong(2,grp.getId());
                ps.setInt(3,(like?1:0));

                ps.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection();
        }
    }
}
