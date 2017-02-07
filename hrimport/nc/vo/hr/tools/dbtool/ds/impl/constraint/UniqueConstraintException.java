/*
 * 创建日期 2006-6-8
 */
package nc.vo.hr.tools.dbtool.ds.impl.constraint;

import nc.vo.hr.tools.dbtool.ds.DSException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-6-8 9:59:27
 * @author ych
 */
public class UniqueConstraintException extends DSException {
	
	/**
	 * 
	 */
	public UniqueConstraintException() {
		super();

	}
	/**
	 * @param errorCode
	 */
	public UniqueConstraintException(long errorCode) {
		super(errorCode);

	}
	/**
	 * @param errorCode
	 * @param message
	 */
	public UniqueConstraintException(long errorCode, String message) {
		super(errorCode, message);

	}
	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public UniqueConstraintException(long errorCode, String message,
			Throwable cause) {
		super(errorCode, message, cause);

	}
	/**
	 * @param errorCode
	 * @param cause
	 */
	public UniqueConstraintException(long errorCode, Throwable cause) {
		super(errorCode, cause);

	}
	/**
	 * @param message
	 */
	public UniqueConstraintException(String message) {
		super(message);

	}
	/**
	 * @param message
	 * @param cause
	 */
	public UniqueConstraintException(String message, Throwable cause) {
		super(message, cause);

	}
	/**
	 * @param cause
	 */
	public UniqueConstraintException(Throwable cause) {
		super(cause);

	}
}
