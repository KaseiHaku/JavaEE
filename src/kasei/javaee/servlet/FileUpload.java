package kasei.javaee.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;


//需要引入的 jar 文件：commons-fileupload-1.3.2、commons-io-2.5.jar。 servlet 3.0 之后不需要了
@WebServlet(name="FileUpload", urlPatterns = {"/Servlet/FileUpload"}, loadOnStartup = -1)
@MultipartConfig(
		location = "WEB-INF/upload/temp",	// 临时文件存放目录
		maxFileSize = 100*1024*1024,
		fileSizeThreshold = 10*1024*1024	// 当上传的文件大于 10 M 时，会生成临时文件
)
public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Collection<Part> parts = request.getParts();
		for (Part part: parts) {

			String contentType = part.getContentType();
			String submittedFileName = part.getSubmittedFileName();

			// 如果是文件上传
			if(submittedFileName != null){
				//得到文件后缀判断文件类型
				int indexOfDot = submittedFileName.lastIndexOf(".");
				String fileName = submittedFileName.substring(0, indexOfDot);
				String fileType = submittedFileName.substring(indexOfDot);

				// 同名文件覆盖问题对fileName 进行进一步处理,工具类UUID
				String serverFileName = UUID.randomUUID().toString()+"_" + submittedFileName;

				//将上传的文件保存在服务器受保护的WEB-INF的目录下，
				part.write("WEB-INF/upload/" + serverFileName);
				part.delete(); // 删除临时文件
			}

		}

	}
}
