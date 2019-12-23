package kasei.web.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
	/** Session数据的钝化与活化：
	 * 不用的session数据序列化到本地文件中的过程，就是钝化；
	 * 当再次访问需要到该session的内容时，就会读取本地文件，再次放入内存中，这个过程就是活化。
	 * */
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		
		HttpSessionActivationListener.super.sessionDidActivate(se);
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		
		HttpSessionActivationListener.super.sessionWillPassivate(se);
	}
	
}
