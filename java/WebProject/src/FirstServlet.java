import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Enumeration;

//@WebServlet(name = "FirstSe/rvlet")
public class FirstServlet extends HttpServlet {

//    private transient ServletConfig config;


//    @Override重写这个方法没有调用super.init(config)所以就获取不到servletConfig对象了
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 每个servlet开头写，解决编码问题
         */
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /**
         * 获取代表当前Servlet在web.xml中的配置信息
         */
        ServletConfig config = this.getServletConfig();  //拿到init方法中的ServletConfig对象

        String servletName = config.getServletName();//获取当前servlet在web.xml中的配置名称
        System.out.println(servletName);

        String val = config.getInitParameter("name");//获取当前servlet中配置的初始换参数<init-param>中的
        System.out.println(val);

        ServletContext servletContext = config.getServletContext();//获取代表当前web应用的ServletContext对象
        System.out.println(servletContext);

        Enumeration<String> initParameterNames = config.getInitParameterNames();//获取当前servlet中配置的所有初始化参数
        while (initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = config.getInitParameter(name);
            System.out.println(name+":"+value);
        }

        /**
         * 代表当前web应用,每个WEB应用程序都创建一个对应的ServletContext
         * 做为域对象可以在整个web应用范围内共享数据
         */
        System.out.println("==========================================");
        //获取ServletContext
//        ServletContext context1 = config.getServletContext();//ServletConfig对象中维护了ServletContext对象的引用
//        ServletContext context2 = this.getServletConfig().getServletContext();
        ServletContext context3 = this.getServletContext();

        context3.setAttribute("haha","hehe");
        String haha = (String) context3.getAttribute("haha");
        System.out.println(haha);
        String itprwe = context3.getInitParameter("itprwe");// 获取单个的Context里面的初始化参数
        System.out.println(itprwe);
        Enumeration<String> initParameterNames1 = context3.getInitParameterNames();// 一次性获取Context里所有的初始化参数
        while (initParameterNames1.hasMoreElements()){
            String s = initParameterNames1.nextElement();
            String initParameter = context3.getInitParameter(s);
            System.out.println(s+":"+initParameter);
        }

        /**
         * ServletContext做为域对象可以在整个web应用范围内共享数据(JSP的application域)
         * 数据的增删取(该对象读写的变量是可以被所有servlet共用的setAttribute()/getAttribute())
         * 加载资源
         * 可以获取服务器信息、Servlet版本以及项目的映射名称等信息
         */

        System.out.println("============================================");
        ServletContext context = this.getServletContext();
        context.setAttribute("addkey","addvalue");//增
        context.getAttribute("addkey");//|取
        System.out.println((String)context.getAttribute("addkey"));
        context.removeAttribute("addkey");//删
        System.out.println((String)context.getAttribute("addkey"));

        System.out.println("========加载资源===========");
        String realPath = context.getRealPath("/index.jsp");
        System.out.println(realPath);

        System.out.println("=====可以获取服务器信息、Servlet版本以及项目的映射名称等信息=====");
        String serverInfo = context.getServerInfo();//获取tomcat服务器信息
        int majorVersion = context.getMajorVersion();//最大的ServletAPI版本
        int minorVersion = context.getMinorVersion();//最小的ServletAPI版本
        String contextPath = context.getContextPath();//获取项目名称
        System.out.println("服务器信息"+serverInfo);
        System.out.println("支持最大的ServletAPI版本:"+majorVersion);
        System.out.println("支持最小的ServletAPI版本:"+minorVersion);
        System.out.println("项目名称::"+contextPath);

        /**
         *  Servlet的请求转发
         *  请求转发:服务器内部进行资源流转 （一次请求一次响应，来实现资源流转）
         *  请求重定向:302+Location（两次请求两次响应）
         */
        System.out.println("======================================");
//        context.getRequestDispatcher("/hello").forward(request,response);
        //补充:request对象也可以实现请求转发,response可以实现重定向
//        request.getRequestDispatcher("/hello").forward(request,response);
//        response.sendRedirect("http://localhost:8088/webproject/hello");


        /**
         * 用ServletContext统计各个servlet访问总量
         */
        ServletContext commonContext = this.getServletContext();
        Integer count = (Integer) commonContext.getAttribute("count");
        commonContext.setAttribute("count",++count);
        System.out.println("当前访问量："+count);


    }

    public void
    doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
