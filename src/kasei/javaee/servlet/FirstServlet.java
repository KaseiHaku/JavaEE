package kasei.javaee.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Java Web 整体结构：
 * （HTML、CSS、JSP）-->JS-->AJAX-->Servlet-->File
 *                      ^           |   |
 *                      |<--JSON----|   |-->JDBC-->Oracle
 * JSP 和 HTML 是一样的 ； PHP 只是把JSP和Servlet结合在一起了！
 * 
 * Servlet 基本知识点：
 * 是什么： 用纯 Java 代码编写的，处理Browser请求的，  Web 网页
 * 怎么配置：每一个 Servlet 类都需要在  web.xml 文件进行配置
 * 怎么用：直接浏览器输入 URL 转到指定 Servlet 类即可
 * 有什么特性：每次服务器接收到一个 Servlet 请求时，服务器会产生一个新的线程并调用服务。
 * 
 * Servlet 生命周期：
 * 1. init () 方法进行初始化。
 * 2. service() 方法来处理客户端的请求。
 * 3. 调用 destroy() 方法终止（结束）。
 * 4. 最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。
 * */

public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public FirstServlet() {
        super();
    }

    public void init() throws ServletException {
        message = "Hello World";
        System.out.println("Servlet 类已在启动时自动加载！");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /** 坑：首先要保证的是，你敲的代码是 utf-8 格式编码的，如果文件自身编码格式都不是 utf-8，那其他全扯淡
         * 坑：服务器无法控制浏览器对 url 的编码方式，只能猜测，如果正好相同，则可以解析，否则乱码，所以不能对 get 请求做编码定制，只能看运气
        * tomcat 默认采用的是 iso8859-1 对 url 解码的，如果需要修改，则通过修改 settings.xml 里面的 <connect> 标签实现
        * */
        request.setCharacterEncoding("UTF-8");// 设置 server 以 utf-8 格式读取 post 请求 正文的内容，坑：该方法对 get 请求不管用    
        response.setCharacterEncoding("UTF-8");// 设置服务器用 utf-8 对 响应正文 进行编码，然后传给客户端	
        response.setContentType("text/html;charset=utf-8");//设置响应内容为 html 文件，且编码格式为 utf-8，该编码需要跟上一条代码一致，同时告诉浏览器 post 请求正文用 utf-8 编码 
        PrintWriter out = response.getWriter();//该行代码要放在上面几句的最后，否则出错；PrintWriter 用于向客户端（即HTML文件）发送数据

        // 设置刷新自动加载的事件间隔为 5 秒
        response.setIntHeader("Refresh", 5);

        out.println("该文本显示位置是 Browser <hr/>");
        System.out.println("该文本显示位置是 Console");

        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<title> Servlet 类  </title>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/> ");
        out.println("</head><body>");


        out.println("纯 Java 代码编写 HTML 文件，一个Servlet 类 == 一个服务器端App！");

        out.println("<br/>html文件中的 / = http://127.0.0.1/");
        out.println("<br/>jsp、Servlet中的 / = http://127.0.0.1/webapp/");


        out.println("<br/>浏览器的请求URI:"+request.getRequestURI());		
        out.println("<br/>浏览器的请求URL:"+request.getRequestURL());
        out.println("<br/>获得web根（等于servlet中的  /）:"+request.getContextPath());
        out.println("<br/>web项目在服务器中的物理绝对地址:"+this.getServletContext().getRealPath("/qwer"));		
        out.println("<br/>classes目录在服务器中的物理绝对地址:"+this.getClass().getClassLoader().getResource("").getPath());			
        out.println("<br/>获得classpath:"+Thread.currentThread().getContextClassLoader().getResource("/").getPath());

        out.println("</body></html>");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /** Java Web Scope */
        ServletContext context = this.getServletContext();//获取ServletContext的实例，所有servlet共用
        ServletConfig config = this.getServletConfig();//对当前的servlet实例有效，本servlet共用
        HttpSession session = request.getSession();//仅在当前会话中有效
        ServletRequest MyRequest = request;//仅在一次请求中有效
        HttpServletResponse MyResponse = response;//仅在一次应答中有效


        /** Servlet 请求转发 */	
        context.getRequestDispatcher("/jsp.jsp").forward(request, response);//请求转发

        /** Servlet 页面重定向 */
        /* 方法1：
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "http://www.runoob.com");
        */ 

        /* 方法2：
        response.sendRedirect("http://www.baidu.com");
        */ 

        /* 方法3： 用于在服务器需要另外添加请求参数的情况*/
        //添加请求参数
        request.setAttribute("aa", "word");

        // 使用req对象获取RequestDispatcher对象
        RequestDispatcher dispatcher = request.getRequestDispatcher("/javaweb.jsp");//只能跳转该Web项目内的页面

        //使用RequestDispatcher实例进行页面跳转
        dispatcher.forward(request, response);//留头不留体
        //dispatcher.include(request, response);//留头又留体	
    }
}
