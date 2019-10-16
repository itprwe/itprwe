package dao;

import util.C3P0Utils;
import util.DbcpUtils;

import java.sql.*;

public class BaseDao {


    /***
     *
     * @author 数据库连接类
     *
     */
    private String driver ="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC";
    private String name="root";
    private String pwd="";
    Connection conn=null;


    /**
     * 获取连接
     * @return
     */
    protected Connection getConn(){
        try {
            Class.forName(driver);//1.注册驱动
            conn = DriverManager.getConnection(url, name, pwd);//2.获取连接
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
//
//    public Connection getCoon1(){
//        //获取连接
//        try {
//            Class.forName("driver");
//            conn = DriverManager.getConnection(url,name,pwd);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    public void closeAll1(Connection conn,PreparedStatement ps,ResultSet rs){
//        //有则关闭
//        if(rs!=null){
//            try {
//                if(rs!=null){
//                    rs.close();
//                }
//                if(ps!=null){
//                    ps.close();
//                }
//                if(conn!=null){
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public int executeUpdate1(String sql,Object[] obj){
//
//        int a = 0;
//        conn = getCoon1();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            int index = 1;
//            if(ps!=null&&obj!=null){
//                for (int i = 0; i <obj.length ; i++) {
//                    ps.setObject(index,obj[i]);
//                    index++;
//                }
//            }
//            a = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//            return a;
//
//    }


    /**
     * 关闭链接
     * @param conn
     * @param ps
     * @param rs
     */
    protected void closeAll(Connection conn , PreparedStatement ps, ResultSet rs){
        if(rs!=null)
            try {
                if(rs!=null)
                    rs.close();//5.关闭
                if(ps!=null)
                    ps.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    //执行增删改
    protected int executeUpdate(String sql,Object[] obj){
//        conn = getConn();

//        conn = C3P0Utils.getConnection();

        conn = DbcpUtils.getConnection();

        PreparedStatement ps = null;

        try {
            ps=prepareStatement(conn,sql,obj);
            int i=ps.executeUpdate();//4.创建的statement对象，执行
            return i;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //	e.printStackTrace();
            return 0;
        }finally{
            closeAll(conn, ps, null);
        }


    }

    /***
     * 查询方法
     */
    protected PreparedStatement prepareStatement(Connection conn,String sql,Object []ob){
        PreparedStatement ps=null;
        try {
            int index=1;
            ps = conn.prepareStatement(sql);//3.创建Statement\PreparedStatement对象
            if(ps!=null&&ob!=null){
                for (int i = 0; i < ob.length; i++) {
                    ps.setObject(index, ob[i]);
                    index++;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return ps;
    }
}
