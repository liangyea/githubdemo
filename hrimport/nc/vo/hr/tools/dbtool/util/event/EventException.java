/*
 * Created on 2005-6-9
 * 
 */
package nc.vo.hr.tools.dbtool.util.event;

/**
 * Describe:printTemplate
 * 		��Ϣ�쳣��������ҵ���쳣����,��������һ���쳣��
 * @version 1.0	2005-6-9 14:15:35
 * @author �Ƴ���
 */
public class EventException extends Exception {
    private static final long serialVersionUID = -5399497008479218182L;


    public static int NORMAL_EXCEPTION = 0;
    // 
    
    
    // ҵ���쳣�롡��ʹ��>100����    
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
