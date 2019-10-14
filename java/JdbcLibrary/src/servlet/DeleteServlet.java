package servlet;

import dao.BookDao;
import entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        Book book = new Book();
        book.setId(Integer.parseInt(request.getParameter("id")));
        BookDao bookDao = new BookDao();

        int delete = bookDao.delete(book);
        if(delete>0){
            List<Book> listrm=bookDao.findAll();
            request.getSession().setAttribute("list", listrm);
            out.print("<script>alert('删除成功！！！');location.href='index.jsp';</script>");
        }else{
            out.print("<script>alert('删除失败！！！');location.href='index.jsp';</script>");
        }

        out.flush();
        out.close();

    }
}
