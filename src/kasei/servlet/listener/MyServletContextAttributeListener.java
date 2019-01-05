package kasei.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) 
	{

		ServletContextAttributeListener.super.attributeAdded(scae);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) 
	{

		ServletContextAttributeListener.super.attributeRemoved(scae);
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) 
	{
		ServletContextAttributeListener.super.attributeReplaced(scae);
	}

}
