/* Generated by Together */

package nc.vo.hr.tools.dbtool.util.event;


/**
 * @author �Ƴ���
 * @example 
 * @see testcase	
 */
public class EventServerFactory {
    /** @link dependency */
    /*# IEventServer lnkIEventServer; */

    /** @link dependency */
    /*# IEventServerConfig lnkIEventServerConfig; */
/*    
    private static Map eventServerMap = null;
   
    *//**
     * get event server
     *//*
    public static IEventServer getEventServer(String eventServerName ) {
        return getEventServer( eventServerName, new EventServerConfig() );
    }

    public static IEventServer getEventServer(String eventServerName , IEventServerConfig config) {
        IEventServer eventServer = (IEventServer) getEventServerMap().get( eventServerName );
        if( eventServer  == null ) {
            eventServer = new EventServer( eventServerName, config);
            getEventServerMap().put( eventServerName, eventServer );
        }
        
        return eventServer;
    }
    *//**
     * @return Returns the eventServerMap.
     *//*
    protected static Map getEventServerMap() {
        if( eventServerMap==null ) {
            eventServerMap = new HashMap();
        }
        return eventServerMap;
    }*/
    
    public static IEventServer getEventServer(String eventServerName ) {
        return getEventServer( eventServerName, null );
    }

    public static IEventServer getEventServer(String eventServerName , IEventServerConfig config) {
        return new EventServer( eventServerName, config);
    }
}
