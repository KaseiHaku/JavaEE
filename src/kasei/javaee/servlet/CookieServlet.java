package kasei.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/* Cookie
 * 是什么：是一种服务器留在用户计算机上的小文件。
 * 有什么用：保存Web数据到本地
 * 预备条件：
 * 完整执行过程：无论使用何种服务端技术，只要服务器发送回的HTTP响应中包含如下形式的头，则视为服务器要求设置一个cookie：
	 Set-cookie:name=name;expires=date;path=path;domain=domain
	    支持cookie的浏览器都会对此作出反应，即创建cookie文件并保存（也可能是内存cookie），
	    用户以后在每次发出请求时，浏览器都要判断当前所有的cookie中有没有没失效（根据expires属性判断）
	    并且匹配了path属性的cookie信息，如果有的话，会以下面的形式加入到请求头中发回服务端：
	    Cookie: name="zj"; Path="/linkage"
	    服务端的动态脚本会对其进行分析，并做出相应的处理，当然也可以选择直接忽略
	    
	    
1.domain表示的是cookie所在的域，默认为请求的地址，如网址为www.jb51.net/test/test.aspx，
那么domain默认为www.jb51.net。而跨域访问，如域A为t1.test.com，域B为t2.test.com，
那么在域A生产一个令域A和域B都能访问的cookie就要将该cookie的domain设置为.test.com；
如果要在域A生产一个令域A不能访问而域B能访问的cookie就要将该cookie的domain设置为t2.test.com。
2.path表示cookie所在的目录，asp.net默认为/，就是根目录。在同一个服务器上有目录如下：/test/,/test/cd/,/test/dd/，
现设一个cookie1的path为/test/，cookie2的path为/test/cd/，那么test下的所有页面都可以访问到cookie1，
而/test/和/test/dd/的子页面不能访问cookie2。这是因为cookie能让其path路径下的页面访问。
3.浏览器会将domain和path都相同的cookie保存在一个文件里，cookie间用*隔开。
4.含值键值对的cookie：以前一直用的是nam=value单键值对的cookie，一说到含多个子键值对的就蒙了。
现在总算弄清楚了。含多个子键值对的cookie格式是name=key1=value1&key2=value2。
可以理解为单键值对的值保存一个自定义的多键值字符串，其中的键值对分割符为&，当然可以自定义一个分隔符，
但用asp.net获取时是以&为分割符。
 * */
public class CookieServlet extends HttpServlet
{
	/* Cookie 结构
	 * cookie名：  name=""
	 * expire：
	 * domain：设置 cookie 适用的域；如：www.kasei.com
	 * path：设置 cookie 适用的路径；如：./dir (表示 www.kasei.com/dir)
	 * */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset= utf-8");
		PrintWriter out = response.getWriter();
		
		String cookieValue = java.net.URLEncoder.encode("把该中文进行Cookie编码","UTF-8");//把中文进行Cookie编码
		String str = java.net.URLDecoder.decode(cookieValue,"UTF-8");//对中文编码后的 cookieValue 引用进行解码
		out.println("<!DOCTYPE html>\n<html><head><title> Servlet Cookie </title></head><body>");
		out.println("编码后的中文："+cookieValue+"<br/>");
		out.println("解码后的中文："+str+"<br/>");
		
		
		//Servlet 设置 Cookie：见 Cookie.jsp
		

		//Servlet 读取 Cookie
		String name = null;
		String value = null;
		javax.servlet.http.Cookie[] cookies = request.getCookies();//获取与该域相关的 Cookie 数组
		for(javax.servlet.http.Cookie cookie :cookies)
		{
			name = cookie.getName();//获取该cookie的名字
			value = cookie.getValue();//获取该cookie的值
			out.println("<hr/><h3>读取的cookie</h3>名字："+name+"<br/>"+"值:"+value+"<br/>");
		}
		
		out.println("</body></html>");
		
		
		//Servlet 删除 Cookie
		/* 读取一个现有的 cookie，并把它存储在 Cookie 对象中。
		 * 使用 setMaxAge() 方法设置 cookie 的年龄为零，来删除现有的 cookie。
		 * 把这个 cookie 添加到响应头
		 */	
	}
}
