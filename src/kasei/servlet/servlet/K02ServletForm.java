package kasei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class K02ServletForm extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String str = request.getParameter("name");//以<input>标签的 name 属性获取值
		String[] cars = request.getParameterValues("cars");//获取的都是选中的复选框的值挨个存入数组中
		Enumeration<String> d = request.getParameterNames();//获取当前请求中所有参数的完整列表

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(str + "<br/>");
		
		for(String str1:cars){
			out.println(str1 + "&nbsp;&nbsp;");
		}
		
		out.println("<br/>");
		for(;d.hasMoreElements();){
			out.println(d.nextElement() + "&nbsp;&nbsp;");
		}		
	}
}
