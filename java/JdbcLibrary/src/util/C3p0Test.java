package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Test {

    public static void main(String[] args) throws SQLException {

        Object[] obj = new Object[]{"dsd","dddd"};
        System.out.println(obj.length);



        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        /**
         * new ComboPooledDataSource(“名称”); 使用配置文件“命名配置”
         *   <named-config name="名称">
         *   <default-config>
         *
         *   new ComboPooledDataSource(); 使用配置文件“默认配置”
         */
        Connection connection = dataSource.getConnection();//直接获取连接

        System.out.println("====="+connection);

    }
}
