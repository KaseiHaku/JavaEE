package kasei.web.filter;

import java.io.IOException;
import javax.servlet.*;

public class PostCharacterEncodingFilter implements Filter {

    public void init(FilterConfig fConfig) {
        String site = fConfig.getInitParameter("Site"); // 获取初始化参数
        System.out.println("网站名称: " + site);
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 输出站点名称
        System.out.println("Filter 执行！");

        // 把请求传回过滤链
        chain.doFilter(request, response);
    }


    public void destroy() {
        // 在 Filter 实例被 Web 容器从服务移除之前调用
    }
}
