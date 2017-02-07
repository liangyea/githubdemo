package nc.vo.hr.tools.dbtool.ds.adapter;

import java.util.EventListener;

import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-209:27:55
 * @author „∆≥§∫£
 */
public interface ISaveListener extends EventListener{
    
    public void saveing(ResolverEvent e)  throws  BusinessException ;
    public void saved(ResolverEvent e)  throws  BusinessException ;
    
	/**
	 * This method is called when just before the insertion of a row into the data set is resolved to the server.
	 *
	 * @param row           The row that is about to be resolved.
	 * @param response      Specify a response of ABORT, IGNORE, or RETRY for this error.
	 *                      These constants are defined in util.ErrorResponse.
	 */
	  public void insertingRow(ResolverEvent e)  throws  BusinessException ;

	  /**
	   * This method is called just before the deletion of a row from the data set is resolved on the server.
	   *
	   * @param row                     The row that is to be deleted.
	   * @param response                Specify a response of ABORT, IGNORE, or RETRY for this error.
	   *                                These constants are defined in util.ErrorResponse. Note that an
	   *                                ABORT response causes all insert, update, and delete operations
	   *                                in the same transaction to be rolled back.
	   * @throws BusinessException
	   */
	  public void deletingRow(ResolverEvent e) throws  BusinessException;

	  /**
	   * This method is called just before modifications to a row in the data set are resolved on the server.
	   * @param row             The row that has been modified.
	   * @param oldRow          The original row.
	   * @param response        Specify a response of ABORT, IGNORE, or RETRY for this error.
	   *                        These constants are defined in util.ErrorResponse.
	   */
	  public void updatingRow(ResolverEvent e)  throws  BusinessException;

	  /**
	   * This method is called when the insertion of a row into the data set has been resolved on the server.
	   * @param row   The row that has been inserted and resolved.
	   */
	  public void insertedRow(ResolverEvent e) throws  BusinessException;

	  /**
	   * This method is called when the deletion of a row from the data set has been resolved on the server.
	   * @param row   The row that has been deleted.
	   */
	  public void deletedRow(ResolverEvent e)  throws  BusinessException;

	  /**
	   * This method is called when modifications to a row in the data set have been resolved on the server.
	   * @param row       The row that has been modified.
	   * @param oldRow    The original row.
	   */
	  public void updatedRow(ResolverEvent e) throws  BusinessException;

	  /**
	   * This method is called when an exception is thrown during resolution of an insertion into the DataSet.
	   *
	    @param dataSet  The original DataSet passsed in to be resolved.  This can be used
	                    to position the any controls bound to it if user interaction is needed.

	    @param row      The row with the problem.  This can be modified to correct the problem
	                    and retry the operation.

	    @param ex       The exception that caused the error.  Note that this may be a chained
	                    exception.  @see DataSetException.getExceptionChain().

	    @param response Specify abort/ignore/retry response for this error.
	                    @see com.borland.jb.util.ErrorResponse  Note that an abort response causes
	                    the all insert/update/delete operations in the same transaction to be
	                    rolled back.

	  */
	  public void insertError(ResolverEvent e) throws  BusinessException;

	  /**
	   * This method is called when an exception is thrown during resolution of a deletion from the DataSet.
	   *
	    @param dataSet  The original DataSet passed in to be resolved. This can be used to
	                    position any controls bound to it if user interaction is needed.

	    @param row      The row with the problem, positioned at the row that caused the error.
	                    This can be modified to correct the problem and retry the operation.

	    @param ex       The exception that caused the error. Note that this may be a chained exception.

	    @param response Specify abort/ignore/retry response for this error.
	                    @see com.borland.jb.util.ErrorResponse  Note that an ABORT response causes
	                    the all insert/update/delete operations in the same transaction to be
	                    rolled back.

	    @see            com.borland.dx.dataset.DataSetException

	  */
	  public void deleteError(ResolverEvent e) throws  BusinessException;

	  /**
	   * This method is called when an exception is thrown during resolution of modifications to a
	   * row in the DataSet.
	   *
	    @param dataSet  The original DataSet passsed in to be resolved.  This can be used
	                    to position the any controls bound to it if user interaction is needed.

	    @param row      The row with the problem.  This can be modified to correct the problem
	                    and retry the operation.

	    @param oldRow   The original row.

	    @param updRow   The row to use for the next update query.  At input this is a copy of
	                    the original row.  Pass this to DataSet.refetchRow().

	    @param ex       The exception that caused the error.  Note that this may be a chained
	                    exception.  @see DataSetException.getExceptionChain().

	    @param response Specify abort/ignore/retry response for this error.
	                    @see com.borland.jb.util.ErrorResponse  Note that an abort response causes
	                    the all insert/update/delete operations in the same transaction to be
	                    rolled back.

	  */
	  public void updateError(ResolverEvent e)  throws  BusinessException;
	  public void savedTable(ResolverEvent e) throws BusinessException;
}
