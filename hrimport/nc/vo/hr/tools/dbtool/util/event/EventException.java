/*
 * Created on 2005-6-9
 * 
 */
package nc.vo.hr.tools.dbtool.util.event;

/**
 * Describe:printTemplate
 * 		消息异常，可用于业务异常控制,并加入了一个异常码
 * @version 1.0	2005-6-9 14:15:35
 * @author 闫长海
 */
public class EventException extends Exception {
    private static final long serialVersionUID = -5399497008479218182L;


    public static int NORMAL_EXCEPTION = 0;
    // 
    
    
    // 业务异常码　请使用>100的数    
    private int errorCode = NORMAL_EXCEPTION;

    /**
     * 
     */
    public EventException() {
        super();

    }
    /**
     * @param message
     */
    public EventException(String message) {
        super(message);

    }
    /**
     * @param message
     * @param cause
     */
    public EventException(String message, Throwable cause) {
        super(message, cause);

    }
    
    /**
     * @param message
     */
    public EventException(String message, int errorCode ) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * @param message
     * @param cause
     */
    public EventException(String message, Throwable cause, int errorCode ) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * @param cause
     */
    public EventException(Throwable cause) {
        super(cause);

    }
    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }    
    
}
