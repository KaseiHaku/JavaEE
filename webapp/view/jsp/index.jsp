<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Genesis Java Web</title>
</head>
<body>
    最最原始的 Java Web 项目！<br/>
    <br/>
    Java Web 项目启动全过程详解：<br/>
    1. 读取 web.xml 文件中的 context-param 标签，转换成参数<br/>
    2. 根据第一步得到的参数创建 ServletContext 全局环境（上下文）容器<br/>
    3. 创建 listener 中类的实例，即创建监听器<br/>
    5. 加载 filter<br/>
    6. 加载 servlet<br/>
    <br/>
</body>
</html>
