package kasei.javaee.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) 
	{
		System.out.println("MyHttpSessionListener.sessionCreated(se) 执行");
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) 
	{
		System.out.println("MyHttpSessionListener.sessionDestroyed(se) 执行");
		HttpSessionListener.super.sessionDestroyed(se);
	}
}
