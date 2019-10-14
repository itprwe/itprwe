package util;


import entity.Teacher;

import java.sql.*;
import java.util.Properties;

public class DbUtil {

    public static void main(String[] args) {


        try {
            /*//注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //第一种方法：一个参数
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC&username=root&password=");
            //两个个参数
            Properties p = new Properties();
            p.setProperty("username", "root");
            p.setProperty("password", "");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest？serverTimezone=UTC", p);
            //三个个参数
            DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "");*/


            //第二种方法：
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC", "root", "");

            if (!conn.isClosed()){
                System.out.println("数据库连接成功....");

                //3.创建用于向数据库发送sql的Statement对象
                String sql = "select * from teacher;";
//                String sql1 = "INSERT INTO teacher (NAME,sex,address) VALUES ('不是','女','哈哈哈哈哈或')";
                Statement st = conn.createStatement();//执行静态sql
//                conn.prepareStatement(sql);//执行动态sql
//                conn.prepareCall("{CALL demoSp(?,?)}");//数据库存储过程

                //4.执行sql
                ResultSet rs = st.executeQuery(sql);//返回一个结果集（ResultSet）对象
//                int rows = st.executeUpdate(sql1);//用于执行INSERT、UPDATE或DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等
//                boolean flag = st.execute(sql);//用于执行返回多个结果集、多个更新计数或二者组合的语句。

                //5.从代表结果集的ResultSet 中取出数据，进行操作
                while (rs.next()){//rs.getString(1) ; // 此方法比较高效
                    System.out.println(rs.getString(1)+rs.getString("Id")+rs.getString("name")+rs.getString("sex"));
                }
                //关闭记录集
                rs.close();
                //关闭声明
                st.close();
                //关闭连接对象
                conn.close();

            }





            //创建

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
