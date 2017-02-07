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
 * @author �Ƴ���
 * 	private static final Log logger = LogFactory.getLog(LogFactory.class);
 */
public class DSLogger{
//    private static final Log logPlugin = LogFactory.getLog("nc.vo.dbtool.ds");
//     
    
    // 
    private static final String INFO_PREFIX = "��ʾ: ";
    /**
     * ��ʾǰ׺
     */
    private static final String PREFIX = "����: ";

    /**
     * ����ǰ׺
     */
    private static final String ERR_PREFIX = "����: ";

    
    /**
     * ��鵱ǰģ����־�����Ƿ�����
     *
     * @return boolean
     */
    static public boolean isDebugEnabled() {
//        return logPlugin.isDebugEnabled();
    	return true;
    }


    /**
     * ��־������Ϣ
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
     * ��鵱ǰģ����־��ʾ��Ϣ�Ƿ�����
     *
     * @return boolean
     */
    static public boolean isInfoEnabled() {
//        return logPlugin.isInfoEnabled();
    	return true;
    }

    /**
     * ��־��ͨ��Ϣ
     *
     * @param msg
     */
    static public void info(Object msg) {
//        logPlugin.info( msg);
//    	Debug.debug( msg.toString() );
    	System.out.println(INFO_PREFIX+msg);
    }


    /**
     * ��鵱ǰģ����־�����Ƿ�����
     *
     * @return boolean
     */
    static public boolean isWarnEnabled() {
//        return logPlugin.isWarnEnabled();
    	return true;
    }

    /**
     * ��־������Ϣ
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
     * ��־������Ϣ
     *
     * @param msg
     */
    static public void warn(Object msg) {
//        logPlugin.warn(msg);
//    	Debug.debug( msg.toString() );
    	
    	System.out.println(PREFIX+msg);
    }

    /**
     * ��鵱ǰģ����־���󱨸��Ƿ�����
     *
     * @return boolean
     */
    static public boolean isErrorEnabled() {
//        return logPlugin.isErrorEnabled();
    	return true;
    }

    /**
     * ��־������Ϣ
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
     * ��־������Ϣ
     *
     * @param msg
     */
    static public void error(Object msg) {
//        logPlugin.error( msg);
//    	Debug.debug( msg.toString() );
    	
    	System.out.println(ERR_PREFIX+msg);
    } 
}
