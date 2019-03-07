package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCheckListener
 *
 */
@WebListener
public class SessionCheckListener implements HttpSessionListener, HttpSessionAttributeListener
{

    /**
     * Default constructor. 
     */
	private static int activeSession=0;
	
	public static int getActiveSession()
	{
		return activeSession;
	}
	
    @Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		activeSession++;
		System.out.println("세션등록 현재 : "+activeSession);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		activeSession--;
		System.out.println("세션삭제 현재 : "+activeSession);
		
	}



	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}






	public SessionCheckListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	/*activeSession++;*///id부여 : was로 구성,
    	
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
