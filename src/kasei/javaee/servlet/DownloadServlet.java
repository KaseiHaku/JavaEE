package kasei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=haku.png");
        String absolutePath = this.getServletContext().getRealPath("/resource/img/haku.png");
        InputStream is = new FileInputStream(absolutePath);
        OutputStream os = response.getOutputStream();
        Integer b = null;
        while((b=is.read())!=-1){
            os.write(b);
        }
        is.close();
        os.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    /** todo 设置文件下载时的 http response header 参数
     * @param response http 返回对象
     * @param downloadFileName 下载的文件名
     * */
    public static void setDownLoadResponseHeader(HttpServletResponse response, String downloadFileName) throws IOException {
        response.reset(); // Clears any data that exists in the buffer as well as the status code and headers.
        // response.resetBuffer(); // Clears the content of the underlying buffer in the response without clearing headers or status code.
        downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
        response.setCharacterEncoding("UTF-8");//设置响应编码 
        response.setContentType("application/octet-stream"); // 表示时 二进制流 不知道下载的文件类型
        response.addHeader("Content-Disposition","attachment;filename=" + downloadFileName); // 表示时附件，需要下载
        // ServletOutputStream sos = response.getOutputStream(); // 获取输出流
        /*
        PrintWriter w = response.getWriter();// 这个是获取字符输出流
        w.println("one row");
        */
    }
    
}
