package nc.vo.hr.tools.dbtool.ds;

import nc.vo.hr.tools.dbtool.util.BaseException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1415:39:09
 * @author ãÆ³¤º£
 */
public class DSException extends BaseException {
	private static final long serialVersionUID = 1926108146897542417L;


	public static long	EXCEPTION_BASE_CODE = 0;
    // 
    public static long	ROW_HAVE_REFED			= EXCEPTION_BASE_CODE;
    public static long	PARAM_IS_NULL			= EXCEPTION_BASE_CODE+1;
    
    public static String[] msgs = new String[] {
            "this row have refed by other datatable, not use by this datatable ",
            "the input param is null,please check the input of function",
    };
    
    
    public DSException() {
		super();
		
	}


	public DSException(long errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
		
	}


	public DSException(long errorCode, String message) {
		super(errorCode, message);
		
	}


	public DSException(long errorCode, Throwable cause) {
		super(errorCode, cause);
		
	}


	public DSException(long errorCode) {
		super(errorCode);
		
	}


	public DSException(String message, Throwable cause) {
		super(message, cause);
		
	}


	public DSException(String message) {
		super(message);
		
	}


	public DSException(Throwable cause) {
		super(cause);
		
	}


	/* (non-Javadoc)
     * @see nc.ui.print.util.exception.BaseException#getMsgForErrorCode(long)
     */
    protected String getMsgForErrorCode(long errorCode) {
        if( errorCode>=EXCEPTION_BASE_CODE && errorCode<EXCEPTION_BASE_CODE+msgs.length ) {
            return msgs[(int) (errorCode- EXCEPTION_BASE_CODE) ];
        }
        return "";
    }
    
}
