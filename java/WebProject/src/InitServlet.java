import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {

    /**
     * <load-on-startup></load-on-startup>，它的含义是：标记容器是否在启动的时候就加载这个servlet。
     * 当值>=0时，表示容器在应用启动时就加载这个servlet；
     * 当是一个负数时或者没有指定时，则指示容器在该servlet被选择时才加载。正数的值越小，启动该servlet的优先级越高
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("count",0);
    }
}
