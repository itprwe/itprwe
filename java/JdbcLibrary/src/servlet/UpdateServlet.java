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

public class UpdateServlet extends HttpServlet {

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

        BookDao booking=new BookDao();
        int id=Integer.parseInt(request.getParameter("id"));
        int categoryId=Integer.parseInt(request.getParameter("categoryId"));
        String title=request.getParameter("title");
        String summary=request.getParameter("summary");
        String uploaduser=request.getParameter("uploaduser");
        String createdate=request.getParameter("createdate");
        Book rm=new Book();
        rm.setId(id);
        rm.setCategoryId(categoryId);
        rm.setTitle(title);
        rm.setSummary(summary);
        rm.setUploaduser(uploaduser);
        rm.setCreatedate(createdate);
        int i=booking.update(rm);
        if(i>0){
            //刷新
            List<Book> listrm=booking.findAll();
            request.getSession().setAttribute("list", listrm);
            out.print("<script>alert('修改成功！！！');location.href='index.jsp';</script>");
        }else{
            out.print("<script>alert('修改失败！！！');location.href='update.jsp';</script>");
        }

        out.flush();
        out.close();
    }
}
