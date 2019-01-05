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
}
