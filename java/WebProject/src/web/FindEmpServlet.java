package web;

import dao.EmpDao;
import entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class FindEmpServlet extends HttpServlet {


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        EmpDao empDao = new EmpDao();
        //获取所有数据
        List<Emp> list = empDao.findAll();

        ////将数据绑定到域对象request上
        req.setAttribute("emps",list);

        //转发到Jsp页面
        req.getRequestDispatcher("emps.jsp").forward(req,res);

    }
}
