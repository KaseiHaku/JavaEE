package kasei.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@WebServlet(name="FileDownload", urlPatterns = {"/Servlet/FileDownload"}, asyncSupported = false)
@WebInitParam(name = "key", value = "value", description = "测试使用注解注入初始化参数")
public class FileDownload extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {


        String downloadFileName = "TestServletFileDownload.txt";

        response.reset(); // Clears any data that exists in the buffer as well as the status code and headers.
        // response.resetBuffer(); // Clears the content of the underlying buffer in the response without clearing headers or status code.
        downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
        response.setContentType("application/octet-stream"); // 表示时 二进制流 不知道下载的文件类型
        response.addHeader("Content-Disposition","attachment;filename=" + downloadFileName); // 表示时附件，需要下载
        ServletOutputStream os = response.getOutputStream(); // 获取输出流
        /*
        PrintWriter w = response.getWriter();// 这个是获取字符输出流
        w.println("one row");
        */

        String fileContent = "test servlet file download";
        os.write(fileContent.getBytes(Charset.forName("utf-8")));


        os.flush(); // 强制将所有缓存的内容写入到流中


    }
}
