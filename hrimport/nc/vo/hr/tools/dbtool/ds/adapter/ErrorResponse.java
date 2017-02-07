package nc.vo.hr.tools.dbtool.ds.adapter;
/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-209:31:00
 * @author „∆≥§∫£
 */
public class ErrorResponse {
	  /**
	   * The response:
	   * <LI>1 = ABORT
	   * <LI>2 = IGNORE
	   * <LI>3 = RETRY
	   */
	    protected int response;
	    
	  public ErrorResponse() {
	    response  = ABORT;
	  }

	  /**
	   *   The response is "abort".
	   */
	  public static final int ABORT   = 1;
	  /**
	    *   The response is "ignore".
	   */
	  public static final int IGNORE  = 2;
	  /**
	    *   The response is "retry".
	   */
	  public static final int RETRY   = 3;

	 /**
	  * Returns a response value of ABORT.
	  * Call this method to cause the operation to fail with an exception throw.
	  */
	  public final void abort() { response  = ABORT; }

	/**
	 *  Returns a response value of IGNORE.
	 *  Call this method to cause the operation to fail without an exception throw.
	 */
	  public final void ignore() { response  = IGNORE; }

	  /**
	   *  Returns a reponse of RETRY.
	   *  Call this method to attempt to retry the operation that failed.
	   */
	  public final void retry() { response  =  RETRY; }

	  public final int getResponse() { return response;}

	  /**
	   * Tests to see if the response is ABORT. If it returns
	   * <strong>true</strong>, the response is ABORT.
	   */
	  public final boolean isAbort() { return response == ABORT; }
	  /**
	   * Tests to see if the response is IGNORE. If it returns
	   * <strong>true</strong>, the response is IGNORE.
	   */
	  public final boolean isIgnore() { return response == IGNORE; }
	  /**
	   * Tests to see if the response is RETRY. If it returns
	   * <strong>true</strong>, the response is RETRY.
	   */
	  public final boolean isRetry() { return response == RETRY; }


}
