package kasei.javaee.filter;

import java.io.IOException;
import javax.servlet.*;

public class PostCharacterEncodingFilter implements Filter {


    private String charsetEncoding;

    public void init(FilterConfig filterConfig) {
        charsetEncoding = filterConfig.getInitParameter("charsetEncoding");
        System.out.println("Filter init: charset ecode = " + charsetEncoding);
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(charsetEncoding);
        response.setCharacterEncoding(charsetEncoding);

        // 输出站点名称
        System.out.println("Filter 执行！");

        // 把请求传回过滤链
        chain.doFilter(request, response);
    }


    public void destroy() {
        // 在 Filter 实例被 Web 容器从服务移除之前调用
    }
}
