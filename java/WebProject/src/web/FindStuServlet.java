package web;

import dao.StudentDao;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class FindStuServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {


        StudentDao studentDao = new StudentDao();
        Student student = studentDao.find();

        //转发
        req.setAttribute("student",student);
        req.getRequestDispatcher("student.jsp").forward(req,res);

    }
}
