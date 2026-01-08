package com.trainbook.dao;

import java.sql.*;
import com.trainbook.util.DBConnection;

public class UserDAO {

    public static boolean validate(String u, String p) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, u);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
