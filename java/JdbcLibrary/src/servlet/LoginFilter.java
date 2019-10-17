package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        /**
         * * 在过滤器中可以直接获取session中的用户信息，如果user不为空，说明浏览器没关。放行。
         * 从session中获取不到user的信息
         * 先获取cookie，获取指定名称的cookie，
         * 如果cookie为空，放行。跳到提示登陆页
         * 如果cookie不为空，获取用户名和密码。去数据库查询。
         * 如果查询不到，cookie的信息不正确，放行（没有存入session中）。跳到提示登陆页
         * 如果查询到了，cookie中的信息是正确，把用户的信息保存到session中。放行。去想去的页面
         */
        // 从session中获取用户的信息
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String uri = req.getRequestURI();

        if(uri.contains("pleaselogin.jsp")){
            filterChain.doFilter(request,response);
            return;
        }

        // 从session获取用户的信息
        User user = (User) session.getAttribute("existUser");
        // 如果浏览器没关闭，session中的用户信息不为null的
        if(user != null){
            // 直接放行	自己访问SelectServlet（因为SelectServlet已经做过处理了，没有session默认让你去登陆）了。
            filterChain.doFilter(request,response);
        }else {
            // session中没有用户的信息
            // 获取指定名称的cookie
            Cookie[] cookies = req.getCookies();
            // 获取到cookie，就可以进行判断了
            Cookie cookie = getCookieByName(cookies,"autologin");
            // 如果cookie为null
            // 在你的浏览器中，根本就没有autologin的cookie
            if(cookie == null){
                // 直接放行	自己访问suc.jsp（因为suc.jsp已经做过处理了，没有session默认让你去登陆）了。
//                filterChain.doFilter(request,response);
                if(uri.contains("jdbc/login.jsp")||uri.contains("LoginServlet")){
                    filterChain.doFilter(request,response);
                }else{
                    //未登录的情况下打开一个需要登录的页面，会跳到提示登录的页面（login.jsp，LoginServlet不需要登录所以去除了）
                    resp.sendRedirect(req.getContextPath() +"/pleaselogin.jsp");
                }
            }else{
                // 从cookie中获取用户名和密码，去数据中查询
                String username = cookie.getValue().split("#itcast#")[0];
                String password = cookie.getValue().split("#itcast#")[1];
                // 你需要去数据库中进行查询
                UserDao dao = new UserDao();
                // 去数据库中查询指定名称和密码的用户
                User existUser = dao.findUser(username, password);
                // 查询出的用户为null
                if( existUser==null){
                    // 放行
//                    filterChain.doFilter(request,response);
                    if(uri.contains("jdbc/login.jsp")||uri.contains("LoginServlet")){
                        filterChain.doFilter(request,response);
                    }else{
                        //校验用户登录名密码不对的情况下打开一个需要登录的页面，会跳到提示登录的页面（login.jsp，LoginServlet不需要登录所以去除了）
                        resp.sendRedirect(req.getContextPath() +"/pleaselogin.jsp");
                    }
                }else{
                    // 存入到session中
                    session.setAttribute("existUser", existUser);
                    // 放行
                    filterChain.doFilter(request,response);
                }
            }
        }
    }

    public Cookie getCookieByName(Cookie [] cookies,String cookieName){
        if(cookies == null){
            return null;
        }else{
            for (Cookie cookie : cookies) {
                // 判断
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
            return null;
        }
    }

    @Override
    public void destroy() {

    }
}
