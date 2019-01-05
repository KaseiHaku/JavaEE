package kasei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

/* Session 是什么：一段Web服务器内存中的存储区
 * Session 的作用是什么：用于存储有关用户的信息
 * Session 的预备条件、知识：Session 是个容器类
 * Session 的完整运行过程：
 * 1、当用户请求来自应用程序的 Web 页时，
 *   如果该用户还没有会话，则 Web 服务器将自动创建一个 Session 对象。
 *   当会话过期或被放弃后，服务器将终止该会话
 * 2、每个Session有一个sessionID，以cookie的形式返回给浏览器端
 * 3、浏览器在访问页面是把cookie传给服务器获取SessionID
 */
public class K06ServletSession extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		HttpSession session = null;
		String sessionID = null;
		String key = null;
		String value = null;
		Date createTime = null;
		Date lastAccessTime = null;
				
		//如果不存在 session 会话，则会创建一个 session 对象
		session = request.getSession(true);//true 没有session时自动新建一个session
		session.setAttribute("key", "keyValue");//向session中存数据
		if(session.isNew())// 判断该session是不是新建的
		{
			sessionID = session.getId();//获取该session的sessionID	
			response.setContentType("text/html;charset= utf-8");
			response.getWriter().println("session创建成功，sessionID="+sessionID);		
		}
		else
		{
			sessionID = session.getId();//获取该session的sessionID		
			// 获取 session 创建时间
			createTime = new Date(session.getCreationTime());
			// 获取该网页的最后一次访问时间
			lastAccessTime = new Date(session.getLastAccessedTime());
			//取该session中 key 键的值
			value = (String)session.getAttribute("key");
			session.removeAttribute("key"); //删除指定键
			session.invalidate(); //删除所有session中保存的键	
			session.setMaxInactiveInterval(30);//session在这段时间内保持回话有效
			
			//设置日期输出的格式  
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		    response.setContentType("text/html;charset= utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>\n<html><head><title> Session  </title></head><body>");
			out.println("该session已存在，<br/>sessionID="+sessionID+"<br/>key键的值="+value);
			out.println("<br/>该session创建时间："+df.format(createTime)+"<br/>最后一次访问时间："+df.format(lastAccessTime));
			out.println("</body></html>");
		}
	}
}
