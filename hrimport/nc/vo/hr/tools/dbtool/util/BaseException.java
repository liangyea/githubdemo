/*
 * Created on 2005-7-14
 * 
 */
package nc.vo.hr.tools.dbtool.util;

import nc.vo.pub.BusinessException;

/**
 * Describe:printTemplate
 * 		add exception code
 * @version 1.0	2005-7-14 10:22:19
 * @author „∆≥§∫£
 */
public abstract class BaseException extends BusinessException {
    private long errorCode = 0;    
    
    /**
     * 
     */
    public BaseException() {
        super();

    }
    /**
     * @param message
     */
    public BaseException(String message) {
        super(message);
    }
    
    /**
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseException(long errorCode) {
        super();
        this.errorCode = errorCode;
    }
    
    public BaseException(long errorCode, String message ) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BaseException(long errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
    
    public BaseException(long errorCode, String message , Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);

    }
    /**
     * @return Returns the errorCode.
     */
    protected long getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode The errorCode to set.
     */
    protected void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getMsgForErrorCode() {
        return getMsgForErrorCode(this.errorCode);
    }
    
    /**
     * @param l
     */
    abstract protected String getMsgForErrorCode(long errorCode);
    
    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {
    	if( getMsgForErrorCode()==null ) {
    		return super.getMessage();
    	}
        String msg = super.getMessage() +"\n"+ getMsgForErrorCode() ; 
        return msg;
    }
}
