package nc.vo.hr.tools.dbtool.util.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

/**
 * Describe:NC_HR_PUB5.0
 * 		simple event server.
 * @version 1.0	2006-6-3 10:27:55
 * @author „∆≥§∫£
 */
public class SimpleEventServer implements java.io.Serializable, IBaseEventServer{
    private static final long serialVersionUID = 691843646943571488L;

    /**
     * "listeners" lists all the generic listeners.
     *
     *  This is transient - its state is written in the writeObject method.
     */
    transient private java.util.Vector listeners;
    
    private Object source;
    private IEventDispatcher eventDispatcher;
    
    /**
     * 
     */
    public SimpleEventServer(Object source, IEventDispatcher eventDispatcher) {
        super();
        setSource(source);
        setEventDispatcher(eventDispatcher);
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#addEventListener(java.util.EventListener)
     */
    public void addEventListener( EventListener listener ) {
        getListeners().add(listener);
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#removeEventListener(java.util.EventListener)
     */
    public void removeEventListener( EventListener listener ) {
        getListeners().remove(listener);
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#sendEvent(java.util.EventObject)
     */
    public void sendEvent(EventObject event) throws Exception {
        for (int i = 0; i < getListeners().size(); i++) {
            EventListener listener = (EventListener)getListeners().get(i);
            getEventDispatcher().dispatchEvent(event, listener);
        }
    }

    /**
     * @return Returns the listeners.
     */
    public java.util.Vector getListeners() {
        if( listeners==null ) {
            listeners = new Vector();
        }
        return listeners;
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#getEventDispatcher()
     */
    public IEventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#setEventDispatcher(nc.vo.webframe.util.event.IEventDispatcher)
     */
    public void setEventDispatcher(IEventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#getSource()
     */
    public Object getSource() {
        return source;
    }

    /* (non-Javadoc)
     * @see nc.vo.webframe.util.event.IBaseEventServer#setSource(java.lang.Object)
     */
    public void setSource(Object source) {
        this.source = source;
    }
    
}
