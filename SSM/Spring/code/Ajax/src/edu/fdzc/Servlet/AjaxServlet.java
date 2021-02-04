package edu.fdzc.Servlet;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CAI
 * @time 2021/1/19
 */
@WebServlet("/ajax")
public class AjaxServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Student student1 = new Student("aaa", "aaa@qq.com", 19);
        Student student2 = new Student("bbb", "bbb@qq.com", 20);
        Student student3 = new Student("ccc", "ccc@qq.com", 21);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        Gson gson = new Gson();
        String json = gson.toJson(students);
        System.out.println(json);

        response.getWriter().write(json);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
