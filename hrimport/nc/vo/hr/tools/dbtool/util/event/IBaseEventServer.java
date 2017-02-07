package nc.vo.hr.tools.dbtool.util.event;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Describe:NC_HR_PUB5.0
 * 		
 * @version 1.0	2006-6-3 10:40:40
 * @author „∆≥§∫£
 */
public interface IBaseEventServer {

    public void addEventListener(EventListener listener);

    public void removeEventListener(EventListener listener);

    public void sendEvent(EventObject event) throws Exception;

    /**
     * @return Returns the eventDispatcher.
     */
    public IEventDispatcher getEventDispatcher();

    /**
     * @param eventDispatcher The eventDispatcher to set.
     */
    public void setEventDispatcher(IEventDispatcher eventDispatcher);

    /**
     * @return Returns the source.
     */
    public Object getSource();

    /**
     * @param source The source to set.
     */
    public void setSource(Object source);

}