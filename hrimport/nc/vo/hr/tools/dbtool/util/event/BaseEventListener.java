/*
 * Created on 2005-6-9
 * 
 */
package nc.vo.hr.tools.dbtool.util.event;

import java.util.EventObject;

/**
 * Describe:printTemplate 		
 * @version 1.0	2005-6-9 14:41:47
 * @author „∆≥§∫£
 */
public interface BaseEventListener extends java.util.EventListener {
    public void listenEvent( EventObject event )throws EventException;
}
