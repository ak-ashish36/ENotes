package com.database;

import com.User.userDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {

    public Connection con;

    public userDAO(Connection con) {
        this.con = con;
    }

    public boolean adduser(userDetails us) {
        boolean f = false;
        try {
            String query = "INSERT INTO public.users(fullname, email, password) VALUES(?,?,?)";       //id,fullname,email,password

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, us.getName());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getPassword());
            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public userDetails getUser(userDetails us) {
        userDetails user = null;
        try {
            String query = "SELECT * FROM public.users WHERE email=? AND password =?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, us.getEmail());
            ps.setString(2, us.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new userDetails();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public userDetails getUser_fromID(int userId) {
        userDetails user = null;
        try {
            String query = "SELECT * FROM public.users WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new userDetails();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
