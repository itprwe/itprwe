package servlet;

import dao.BookDao;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class SelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Enumeration<String> p = request.getParameterNames();
        while (p.hasMoreElements()){
            String s = p.nextElement();
            System.out.println(s);
        }


        String opr=request.getParameter("opr");
        System.out.println("================"+opr);

        if(opr==null||opr.equals("list")){
            //刷新
            BookDao bookDao=new BookDao();
            List<Book> list=bookDao.findAll();
            request.getSession().setAttribute("list", list);
            response.sendRedirect("index.jsp");
        }
    }
}
