package kasei.javaee.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {
	@Override
	public void requestInitialized(ServletRequestEvent sre)
	{
		System.out.println("MyServletRequestListener.requestInitialized(sre) 执行");
		ServletRequestListener.super.requestInitialized(sre);
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) 
	{
		System.out.println("MyServletRequestListener.requestDestroyed(sre) 执行");
		ServletRequestListener.super.requestDestroyed(sre);
	}
}
