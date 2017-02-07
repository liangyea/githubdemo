package nc.vo.hr.tools.dbtool.ds.adapter;

import nc.vo.hr.tools.dbtool.util.BaseException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-209:19:07
 * @author „∆≥§∫£
 */
public class IOException extends BaseException{
	private static final long serialVersionUID = -1670786183163920602L;

	protected String getMsgForErrorCode(long errorCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IOException() {
		super();
		
	}

	public IOException(long errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
		
	}

	public IOException(long errorCode, String message) {
		super(errorCode, message);
		
	}

	public IOException(long errorCode, Throwable cause) {
		super(errorCode, cause);
		
	}

	public IOException(long errorCode) {
		super(errorCode);
		
	}

	public IOException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public IOException(String message) {
		super(message);
		
	}

	public IOException(Throwable cause) {
		super(cause);
		
	}

}
