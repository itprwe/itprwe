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

public class PagingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 每页3条记录
        int pageSize = 3;
        String spage = request.getParameter("page");
        if (spage == null || spage == "") {
            spage = "1";
        }
        int currentPage = Integer.parseInt(spage);
        BookDao bookDao=new BookDao();
        int totalPage = bookDao.totalPages(pageSize);

        List<Book> list = bookDao.getRecords(currentPage, pageSize);

        out.print("<h1>当前第: " + spage + "/" + totalPage + " 页</h1>");
        out.print("<table border='1' cellpadding='4' width='60%'>");
        out.print("<tr><th>书品编号</th><th>书名</th><th>摘要</th><th>上传人</th><th>上传时间</th>");
        for (Book b : list) {
            out.print("<tr><td>" + b.getCategoryId() + "</td><td>" + b.getTitle() + "</td><td>" + b.getSummary()+ "</td><td>" + b.getUploaduser()+ "</td><td>" + b.getCreatedate() + "</td></tr>");
        }
        out.print("</table>");

        out.print("<a href='PagingServlet?page=1'>首页</a> ");
        for (int i = 1; i <= totalPage; i++) {
            out.print("<a href='PagingServlet?page=" + i + "'>" + i + "</a> ");
        }

        out.print("<a href='PagingServlet?page=");
        if(currentPage<totalPage){
            currentPage++;
            out.print(currentPage);
        }
        out.print("'>下一页</a> ");

        out.print("<a href='PagingServlet?page=" + totalPage + "'>最后一页</a> ");
        out.close();

    }
}
