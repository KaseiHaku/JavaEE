package kasei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/S2CTest")
public class S2CTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public S2CTest() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		//设置 Cookie：Cookie不能包含以下字符：  [ ] ( ) = , " / ? @ : ;
		Cookie cookie = new Cookie("set-cookie", "来自服务器的数据");//创建Cookie对象
		cookie.setMaxAge(10);//设置cookie的最大存在时间,默认为-1表示直到浏览器关闭为止
		cookie.setDomain("localhost");//设置域
		cookie.setPath("/JavaWeb/");//设置路径
		cookie.setComment("注释：用于描述该cookie的用处");//设置注释
		//cookie.setSecure(true);//是否加密传输,设置以后只能被 部署到 https 协议的网站读取

		response.addCookie(cookie);//把cookie添加到http响应头中,当该页面响应时就会设置cookie了
	}
}
