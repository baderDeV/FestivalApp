package com.example.myappfestival.business.implementation;

import com.example.myappfestival.business.interfaces.UserInterface;
import com.example.myappfestival.business.util.ConnectionDatabase;
import com.example.myappfestival.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserImpl implements UserInterface {
    Logger logger = Logger.getLogger("UserImpl");
    @Override
    public int createUser(String username, String password) {
        Connection connection = ConnectionDatabase.getConnection();

        logger.info("connection = "+connection);
        String sql = "insert into user (username,password) values (?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        } finally {
            //ConnectionDatabase.closeConnection();
        }

        return 0;
    }

    @Override
    public User loginUser(String username, String password) {
        Connection connection = ConnectionDatabase.getConnection();
        String sql = "select * from user where username='"+username+"' and password='"+password+"'";
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                user = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection();
        }
        return user;
    }
}
