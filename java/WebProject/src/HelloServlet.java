import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 每个servlet开头写，解决编码问题
         */
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        /**
         * 用ServletContext统计各个servlet访问总量
         */
        ServletContext commonContext = this.getServletContext();
        Integer count = (Integer) commonContext.getAttribute("count");
        commonContext.setAttribute("count",++count);
        System.out.println("当前访问量："+count);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
