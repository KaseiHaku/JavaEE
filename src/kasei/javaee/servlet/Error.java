package kasei.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/* 是什么：
 * 什么用：当一个 Servlet 抛出一个异常时，进行异常处理
 * 预备条件：必须在 web.xml 中使用 error-page 元素来指定对特定异常 或 HTTP 状态码 作出相应的 Servlet 调用
 * 完整执行过程：
 * 	当一个 Servlet 抛出一个异常时，
 * 	Web 容器在使用了 exception-type 元素的 web.xml 中搜索与抛出异常类型相匹配的配置。
 * */
public class Error extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Throwable throwable = (Throwable)
				request.getAttribute("javax.servlet.error.exception");//获取错误类型
		Integer statusCode = (Integer)
				request.getAttribute("javax.servlet.error.status_code");//获取错误状态码
		String servletName = (String)
				request.getAttribute("javax.servlet.error.servlet_name");//获取报错的servlet名
		String requestUri = (String)
				request.getAttribute("javax.servlet.error.request_uri");
		request.getAttribute("javax.servlet.error.exception_type");
		request.getAttribute("javax.servlet.error.message");


		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("出错了！");
	}
}
