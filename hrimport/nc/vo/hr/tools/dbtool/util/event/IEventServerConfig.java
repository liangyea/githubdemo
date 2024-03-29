/* Generated by Together */

package nc.vo.hr.tools.dbtool.util.event;

/**
 * 事件服务器配置类，
 * 如事件处理类型，同步、异步
 * 等 
 * 目前不用
 * @author c*/
public interface IEventServerConfig {
    
    int EVENT_EXCUTE_SYNC = 0;
    int EVENT_EXCUTE_ASYNC = 1;
    
    
    int getEventExcuteType();
    
    /**
     * set event excute type 
     * @see EVENT_EXCUTE_SYNC, EVENT_EXCUTE_ASYNC
     * @param type
     */
    void setEventExuteType(int type);
}
