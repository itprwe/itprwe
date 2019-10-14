package dao;

import entity.Emp;

import java.util.ArrayList;
import java.util.List;

public class EmpDao {

    public List<Emp> findAll(){
        List<Emp> list = new ArrayList<>();

        Emp emp1 = new Emp();
        emp1.setEmpno(1);
        emp1.setEmname("哈哈");
        emp1.setJob("经理");
        emp1.setSal(9000.0);
        list.add(emp1);

        Emp emp2 = new Emp();
        emp2.setEmpno(1);
        emp2.setEmname("呵呵");
        emp2.setJob("员工");
        emp2.setSal(5000.0);
        list.add(emp2);

        Emp emp3 = new Emp();
        emp3.setEmpno(1);
        emp3.setEmname("嘿嘿");
        emp3.setJob("部长");
        emp3.setSal(4000.0);
        list.add(emp3);

        Emp emp4 = new Emp();
        emp4.setEmpno(1);
        emp4.setEmname("哼哼");
        emp4.setJob("董事长");
        emp4.setSal(10000.0);
        list.add(emp4);

        return list;
    }

    public void save(Emp e){
        System.out.println("新增数据:"+e);
    }
}
