/*
 * Created on 2005-6-15
 * 
 */
package nc.vo.hr.tools.dbtool.util;


import nc.vo.logging.Debug;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * Describe:printTemplate
 * 		
 * @version 1.0	2005-6-15 15:35:46
 * @author 闫长海
 * 	private static final Log logger = LogFactory.getLog(LogFactory.class);
 */
public class DSLogger{
//    private static final Log logPlugin = LogFactory.getLog("nc.vo.dbtool.ds");
//     
    
    // 
    private static final String INFO_PREFIX = "提示: ";
    /**
     * 提示前缀
     */
    private static final String PREFIX = "调试: ";

    /**
     * 错误前缀
     */
    private static final String ERR_PREFIX = "错误: ";

    
    /**
     * 检查当前模块日志调试是否启用
     *
     * @return boolean
     */
    static public boolean isDebugEnabled() {
//        return logPlugin.isDebugEnabled();
    	return true;
    }


    /**
     * 日志调试信息
     *
     * @param msg
     */
    static public void debug(Object msg) {
//        System.out.println(msg);
//        logPlugin.debug(msg);
    	
    	System.out.println(PREFIX+msg);
//    	Debug.debug( msg.toString() );
    }
    
    static public void debug(Object msg, Throwable throwable) {
    	Debug.debug( msg.toString(), throwable);
    }
    
    /**
     * 检查当前模块日志提示信息是否启用
     *
     * @return boolean
     */
    static public boolean isInfoEnabled() {
//        return logPlugin.isInfoEnabled();
    	return true;
    }

    /**
     * 日志普通信息
     *
     * @param msg
     */
    static public void info(Object msg) {
//        logPlugin.info( msg);
//    	Debug.debug( msg.toString() );
    	System.out.println(INFO_PREFIX+msg);
    }


    /**
     * 检查当前模块日志警告是否启用
     *
     * @return boolean
     */
    static public boolean isWarnEnabled() {
//        return logPlugin.isWarnEnabled();
    	return true;
    }

    /**
     * 日志警告信息
     *
     * @param msg
     * @param throwable
     */
    static public void warn(Object msg, Throwable throwable) {
//        logPlugin.warn( msg, throwable);
//    	Debug.debug( msg.toString(),throwable );
    	System.out.println(PREFIX+msg);
    	throwable.printStackTrace();
    }

    /**
     * 日志警告信息
     *
     * @param msg
     */
    static public void warn(Object msg) {
//        logPlugin.warn(msg);
//    	Debug.debug( msg.toString() );
    	
    	System.out.println(PREFIX+msg);
    }

    /**
     * 检查当前模块日志错误报告是否启用
     *
     * @return boolean
     */
    static public boolean isErrorEnabled() {
//        return logPlugin.isErrorEnabled();
    	return true;
    }

    /**
     * 日志错误信息
     *
     * @param msg
     * @param throwable
     */
    static public void error(Object msg, Throwable throwable) {
//        logPlugin.error( msg, throwable);
//    	Debug.debug( msg.toString(),throwable );
    	System.out.println(ERR_PREFIX+msg);
    	throwable.printStackTrace();
    }



    /**
     * 日志错误信息
     *
     * @param msg
     */
    static public void error(Object msg) {
//        logPlugin.error( msg);
//    	Debug.debug( msg.toString() );
    	
    	System.out.println(ERR_PREFIX+msg);
    } 
}
