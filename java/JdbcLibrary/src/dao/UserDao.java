package dao;

import entity.User;

import java.lang.management.BufferPoolMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao{

    public User findUser(String username,String password){
        String sql = "select * from user where username=? and password=? ";

        return search(sql, new Object[]{username,password});

    }


    //根据条件查
    public User search(String sql, Object[] param){
        Connection conn = this.getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = this.prepareStatement(conn, sql, param);
            rs = pst.executeQuery();
            while (rs.next()){
                User user = new User();
                System.out.println(rs.getInt(1));
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setType(rs.getString(5));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,pst,rs);
        }
        return null;
    }

}
