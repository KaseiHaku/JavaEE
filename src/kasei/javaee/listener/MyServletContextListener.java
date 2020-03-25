package kasei.javaee.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{	
		System.out.println("MyServletContextListener.contextInitialized() 执行");
		ServletContextListener.super.contextInitialized(sce);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		System.out.println("MyServletContextListener.contextDestroyed() 执行");
		ServletContextListener.super.contextDestroyed(sce);
	}	
}
