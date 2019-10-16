package util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DbcpTest {

    public static void main(String[] args) {

        DataSource ds=null;
        try {
            //Properties类是用来操作properties文件的
            Properties prop = new Properties();
            //通过类加载器加载你的配置文件
            prop.load(DbcpTest.class.getClassLoader().getResourceAsStream("dbcp-config.properties"));
            //一个工厂创建一个数据源    把配置文件加载进来就知道有多少个连接了
            ds = BasicDataSourceFactory.createDataSource(prop);//得到一个数据源

            //获取连接
            Connection conn = ds.getConnection();

            System.out.println("======"+conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
