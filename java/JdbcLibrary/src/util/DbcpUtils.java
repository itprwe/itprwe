package util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpUtils {


    private static DataSource dataSource;
    //获取配置文件
    static {
        try {
            //Properties类是用来操作properties文件的
            Properties prop = new Properties();
            //通过类加载器加载你的配置文件
            prop.load(DbcpTest.class.getClassLoader().getResourceAsStream("dbcp-config.properties"));
            //一个工厂创建一个数据源    把配置文件加载进来就知道有多少个连接了
            dataSource = BasicDataSourceFactory.createDataSource(prop);//得到一个数据源

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 获取数据源（连接池）
     * @return
     */
    //注释与否好像都可以？
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
