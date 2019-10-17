package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findUser(username, password);

        if(user == null){
            //提示
            request.getSession().setAttribute("existUser", user);
            request.getSession().setAttribute("tips", "tips");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            // 把用户名和密码保存到cookie中，回写到浏览器。
            String autologin = request.getParameter("autologin");
            // 下一次自动登陆，把你的用户名和密码保存起来
            if("auto_ok".equals(autologin)){
                // 创建cookie，回写
                Cookie cookie = new Cookie("autologin",username+"#itcast#"+password);
                // 设置有效时间
                cookie.setMaxAge(60*60);
                // 回写
                response.addCookie(cookie);
            }
            // 把用户的信息保存到session中
            request.getSession().setAttribute("existUser", user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
