package dao;

import entity.Course;
import entity.Student;

public class StudentDao {

    public Student find(){
        Student student = new Student();
        student.setName("张三");
        student.setAge(12);
        student.setSex("男");
        student.setInterests(new String[]{"篮球","羽毛球","乒乓球"});
        Course course = new Course();
        course.setCourseId(11);
        course.setDays(20);
        course.setName("历史");
        student.setCourse(course);

        return student;

    }
}
