/* Generated by Together */

package nc.vo.hr.tools.dbtool.util.event;

import java.util.EventListener;
import java.util.EventObject;

/**
 * 事件服务器
 * 提供事件注册：事件类、监听类、分发类
 * 事件监听注册
 * 事件产生
 * 事件分发等基本服务 
 * @author 闫长海
 */
public interface IEventServer {
    /** @link dependency */
    /*# java.util.EventObject lnkEventObject; */

    /** @link dependency */
    /*# java.util.EventListener lnkEventListener; */

    /** @link dependency */
    /*# IEventDispatcher lnkIEventDispatcher; */
    
    void registerEvent( Class eventClazz, Class eventListenerClazz );
    
    void registerEvent( Class eventClazz, Class eventListenerClazz, IEventDispatcher eventDispatcher );
    
    void addEventListener( Class listenerClazz, EventListener listener );
    void addEventListener( EventListener listener );
    
    void removeEventListener( Class listenerClazz, EventListener listener );
    void removeEventListener( EventListener listener );
    
    void sendEvent( EventObject event )throws Exception;
    
}
