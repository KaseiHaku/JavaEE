package kasei.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//需要引入的 jar 文件：commons-fileupload-1.3.2、commons-io-2.5.jar。
public class K03ServletUpload extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	
		//判断表格的提交方式是否是multipart/form-data类型
		if(!ServletFileUpload.isMultipartContent(request))
		{  
			out.println("不是文件上传处理");  
			return ;  
		}	 
		DiskFileItemFactory factory = new DiskFileItemFactory(); //创建一个解析工厂  
		factory.setSizeThreshold(1024*1024*10);//设置阀值，大于此值时创建临时文件 
		factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));//设置临时缓存文件的保存目录
		out.println(this.getServletContext().getRealPath("/temp"));
		  
		ServletFileUpload upload = new ServletFileUpload(factory);//得到解析器对象
		upload.setFileSizeMax(1024*1024*100);//设置文件上传的大小10M 
		upload.setHeaderEncoding("UTF-8");//设置保存文件的编码方式 
		try{  
		    
		    //定义规定上传文件的类型  
		    String[]arr = {".jpg",".jpeg",".png",".gif",".doc"};  
		    //将类型放到List中  
		    List fileStandType = Arrays.asList(arr);  
		    List items = new ArrayList();  
		    items = upload.parseRequest(request);  
		    //对请求进行解析,有几个输入项就会有几个FileItem对象  
		    Iterator it = items.iterator();  
		    while(it.hasNext())
		    {  
		        FileItem item = (FileItem)it.next();  
		        //判断输入元素的类型，  
		        if(item.isFormField())//是普通项  
		        {
		            //得到name属性  
		            String inputName=item.getFieldName();  
		            //得到相对应的值  
		            String inputValue=item.getString("UTF-8");//可指定字符编码，以防乱码  
		            out.println("inputName: "+inputName+" : "+"inutValue: "+inputValue);  
		        }
		        else//是上传文件输入项
		        {  
		            //获取上传文件名称  
		            String fileName=item.getName();  
		            //判断fileName是否为空即是否真的选择了上传文件,不为空继续  
		            if(!fileName.trim().equals(""))
		            {  
		            	out.println(fileName);
		                //对文件名进行处理得到文件名  
		                fileName=fileName.substring(fileName.lastIndexOf("\\")+1);  
		                 
		                //得到文件后缀判断文件类型  
		                String fileType=fileName.substring(fileName.lastIndexOf("."));  
		                System.out.println("fileType: "+fileType);  
		                //判断是否是制定的文件类型，暂时未用到  
		                if(!fileStandType.contains(fileType)){  
		                    //如果不是制定类型的文件跳转页面,  
		                    System.out.println("文件类型不匹配");    
		                    return ;  
		                }  
		                //文件已选择,得到输入流  
		                InputStream is = item.getInputStream();  
		                //将上传的文件保存在服务器受保护的WEB-INF的目录下，  
		                String savePath = this.getServletContext().getRealPath("WEB-INF/picture");  
		                //同名文件覆盖问题对fileName进行进一步处理,工具类UUID  
		                //fileName=UUID.randomUUID().toString()+"_"+fileName;  
		                //构建输出流  
		                FileOutputStream fos=new FileOutputStream(savePath+"\\"+fileName);  
		                byte[] buffer=new byte[1024];  
		                //int len=0;  
		                while(is.read(buffer)>0){  
		                    fos.write(buffer);  
		                    fos.flush();  
		                }  
		                is.close();  
		                fos.close();  
		                out.println("上传成功！"); 
		                item.delete();//在关闭流之后，删除临时缓存文件  
		            }  
		        }  
		    }       
		}
		catch(Exception e){  
		    request.setAttribute("finish","上传失败！");  
		} 
	}
}
