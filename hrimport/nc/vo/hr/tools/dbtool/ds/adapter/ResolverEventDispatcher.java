package nc.vo.hr.tools.dbtool.ds.adapter;

import java.util.EventListener;
import java.util.EventObject;

import nc.vo.hr.tools.dbtool.util.event.IEventDispatcher;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-20 16:57:06
 * @author „∆≥§∫£
 */
public class ResolverEventDispatcher implements IEventDispatcher {

    public void dispatchEvent(EventObject event, EventListener listener)
            throws Exception {
    	ResolverEvent e = (ResolverEvent)event;
    	ISaveListener listen = (ISaveListener)listener;
    	
    	switch( e.getType() ) {
    	case ResolverEvent.TYPE_OPER_INSERT:
    		if( e.getTimeType() == ResolverEvent.TYPE_TIME_BEFORE ) {
    			listen.insertingRow(e);
    		}else {
    			listen.insertedRow(e);
    		}
    		break;
    	case ResolverEvent.TYPE_OPER_UPDATE:
    		if( e.getTimeType() == ResolverEvent.TYPE_TIME_BEFORE ) {
    			listen.updatingRow(e);
    		}else {
    			listen.updatedRow(e);
    		}
    		break;
    	case ResolverEvent.TYPE_OPER_DELETE:
    		if( e.getTimeType() == ResolverEvent.TYPE_TIME_BEFORE ) {
    			listen.deletingRow(e);
    		}else {
    			listen.deletedRow(e);
    		}
    		break;
    	case ResolverEvent.TYPE_OPER_SAVE:
    		if( e.getTimeType() == ResolverEvent.TYPE_TIME_AFTER){
    			listen.savedTable(e);
    		}
    	default:
    		break;
    	}
    }

}
