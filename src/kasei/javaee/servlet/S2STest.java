package kasei.javaee.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/S2STest")
public class S2STest extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public S2STest()
    {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");//设置响应内容类型
		PrintWriter out = response.getWriter();//获取输出流对象
		
		
		//方法2：
		HttpSession session = request.getSession();
		out.println("获得的Session的数据为："+session.getAttribute("MySession")+"<br/>");
		
		//方法3：
		ServletContext context = this.getServletContext();//获取ServletContext的实例，所有servlet共享一个
		context.setAttribute("key", "obj");//添加数据
		context.removeAttribute("key");//删除数据
		
		String str = (String)context.getAttribute("wife");//读取数据
		out.println("获得的ServletContext共享数据为："+str+"<br/>");	

		//方法4：
		out.println("获得的ServletRequest的数据为："+request.getAttribute("MyRequest")+"<br/>");
	}
}
