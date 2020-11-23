package com.example.myappfestival;

import android.content.Context;

import com.example.myappfestival.business.implementation.UserImpl;
import com.example.myappfestival.business.interfaces.UserInterface;
import com.example.myappfestival.business.util.ConnectionDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myappfestival", appContext.getPackageName());
    }
    @Test
    public void testConnection(){
        Connection connection = ConnectionDatabase.getConnection();
        System.out.println("Test DB");
        assertNotNull(connection);
        ConnectionDatabase.closeConnection();
    }
    @Test
    public void testCreationConnection(){
        /*Connection connection = ConnectionDatabase.getConnection();
        String sql = "insert into user (username,password) values (?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"T");
            ps.setString(2,"fsd");

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionDatabase.closeConnection();*/
        UserInterface u = new UserImpl();
        int i = u.createUser("Anass","XDD");
        System.out.println("i = "+i);
    }
}