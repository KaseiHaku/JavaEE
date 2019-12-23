package kasei.utility;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class HttpHeaderUtil {

    public static void downloadStream(HttpServletResponse response, String downloadFileName) throws IOException {
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
        os.flush(); // 强制将所有缓存的内容写入到流中
    }

}
