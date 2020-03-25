package kasei.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 注意获取到的参数都是 String 类型，需要自行转换
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("ajax get 请求成功");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 注意获取到的参数都是 String 类型，需要自行转换
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("ajax post 请求成功");
        out.close();
    }
}
