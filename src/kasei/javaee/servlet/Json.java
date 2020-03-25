package kasei.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Json extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建输入流来接收前端传过来的 json 字符串
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }


        // Gson gson = new Gson();
        // Map map = gson.fromJson(sb.toString(), Map.class); // 将 json 字符转换成对象
        // String jsonString = gson.toJson(map); // 将对象转换成 json 字符串






        // 采用 GSON 来解析 json 字符串，获得对象

        // 设置 response 的 contentType 为 json 给前端返回 json 字符串
        response.setContentType("text/html;charset=utf-8");

    }
}
