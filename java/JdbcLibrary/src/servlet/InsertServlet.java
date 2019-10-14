package servlet;

import dao.BookDao;
import entity.Book;
import org.omg.CORBA.INTERNAL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InsertServlet extends HttpServlet {

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
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String uploaduser = request.getParameter("uploaduser");
        String createdate = request.getParameter("createdate");
        book.setCategoryId(categoryId);
        book.setTitle(title);
        book.setSummary(summary);
        book.setUploaduser(uploaduser);
        book.setCreatedate(createdate);

        BookDao bookDao = new BookDao();
        int insert = bookDao.insert(book);

        if(insert>0){
            out.print("true");
            //刷新
            List<Book> listrm=bookDao.findAll();
            request.getSession().setAttribute("list", listrm);
            out.print("<script>alert('添加成功！！！');location.href='index.jsp';</script>");
        }else{
            out.print("false");
        }

        out.flush();
        out.close();



    }
}
