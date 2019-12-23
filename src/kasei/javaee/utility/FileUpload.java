package kasei;

import cn.linkey.factory.BeanCtx;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileCleaningTracker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/** todo Java Web 文件上传处理 Demo
 * @attention 把上传的文件放在用户访问不到的地方: 如果客户端上传了一个jsp，上面写着一段让服务器关机的代码。之后客户端访问这个jsp，jsp里边的代码就会执行
 * @attention 上传文件，同名覆盖问题： 使用 UUID + "_" + 原始文件名 即可
 * @attention 使文件分散到不同的目录下： 使用日期打散上传文件，或者使用 hash code 也行
 * */
public class FileUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try {
            traditionalFileUpload(req);
            streamFileUpload(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }


    private void traditionalFileUpload(HttpServletRequest req) throws IOException, FileUploadException {
        if(ServletFileUpload.isMultipartContent(req)){

            DiskFileItemFactory factory = new DiskFileItemFactory();

            FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(req.getServletContext());
            factory.setFileCleaningTracker(fileCleaningTracker); // 配置临时文件清理实例

            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);  // 临时文件保存目录

            factory.setSizeThreshold(1024*1024*10); // 上传文件在内存中的最大值

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024*1024*10);  // 配置允许上传的文件最大值

            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    // other code
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    // other code

                    if("直接写入文件"){
                        File uploadedFile = new File("");
                        item.write(uploadedFile);
                    } else {
                        InputStream uploadedStream = item.getInputStream();
                        // other code
                        uploadedStream.close();
                    }

                    // Process a file upload in memory
                    byte[] data = item.get();

                    // 以下功能点，请查看官方文档
                    // 功能点： 病毒扫描
                    // 功能点： 返回上传进度给用户，

                }
            }

        } else {
            System.out.println("不是文件上传");
        }

    }

    private void streamFileUpload(HttpServletRequest req) throws IOException, FileUploadException {
        if(ServletFileUpload.isMultipartContent(req)){
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(BeanCtx.getRequest());
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    System.out.println("Form field " + name + " with value "
                            + Streams.asString(stream) + " detected.");
                } else {
                    System.out.println("File field " + name + " with file name "
                            + item.getName() + " detected.");
                    // Process the input stream

                }
            }

        } else {
            System.out.println("不是文件上传");
        }
    }


}
