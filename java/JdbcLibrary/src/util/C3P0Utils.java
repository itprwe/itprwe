package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Utils {

    //获取默认配置,在成员变量位置创建一个静态的ComboPooledDtatSource 对象
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 获得数据源（连接池）
     * @return
     */
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void CloseResource(Connection conn, Statement st , ResultSet rs){
        closeResultSet(rs);
        closeStaement(st);
        closeConn(conn);
    }


    /**
     *释放连接
     * @param conn
     *     连接
     */
    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn = null ;
            }
        }
    }


    public static void closeStaement(Statement st) {
        try {
            if(st!=null){
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            st = null;
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs = null;
        }
    }






}
