/*
 * 创建日期 2006-4-24
 */
package nc.bs.hr.tools.dbtool.ds.adapter;

import java.util.ArrayList;
import java.util.List;

import nc.vo.hr.tools.dbtool.ds.ConstraintList;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.IConstraint;
import nc.vo.hr.tools.dbtool.ds.TableSet;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOException;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.adapter.ISaveListener;
import nc.vo.hr.tools.dbtool.ds.adapter.ResolverEvent;
import nc.vo.hr.tools.dbtool.ds.adapter.ResolverEventDispatcher;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.event.EventServerFactory;
import nc.vo.hr.tools.dbtool.util.event.IEventServer;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-24 15:44:25
 * @author ych
 */
public abstract class AbstractDataAdapter implements IDataAdapter {

    private IEventServer eventServer;
    
    // if ignore will buf the msg.
	private ErrorResponse response = new ErrorResponse();
    private StringBuffer msgBuf;
    
//    protected DataTable sourceTable;
//    protected TableMeta sourceTableMeta;
//    protected TableMeta toTableMeta;
    
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.adapter.IDataAdapter#load(nc.vo.dbtool.ds.DataTable, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	public DataTable load(DataTable table, IOParam param) throws IOException,
			BusinessException {
		// TODO auto gen
		return null;
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.adapter.IDataAdapter#load(nc.vo.dbtool.ds.TableSet, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	public TableSet load(TableSet tableSet, IOParam param) throws IOException,
			BusinessException {
		// TODO auto gen
		return null;
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.adapter.IDataAdapter#save(nc.vo.dbtool.ds.DataTable, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	public void save(DataTable table, IOParam param) throws IOException,
			BusinessException {
		
	    if( table==null ) {
            return;
        }
        
        // pre excute save.
        try {
            preSave( table, param );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException( e.getMessage() );
        }        
        
        // save
        //int rowCount = table.getRowCount();        
        table.setVisibility( DataRow.STATE_CHANGE );
        
        // excute map trans.
        excuteTranst(table,param);
        
        excuteSave( table, param );
        
        // set default
        table.setVisibility( DataRow.STATE_ALL );
        // saved
        try {
            endSave( table, param );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException( e.getMessage() );
        }

	}


	/**
	 * @param table
	 * @param param
	 * @throws IOException
	 */
	protected void excuteTranst(DataTable table, IOParam param)throws BusinessException, IOException{
		int rowCount = table.getRowCount();
    	// pre excute row
        for (int i = 0; i < rowCount; i++) {
        	DataRow row = table.getRow(i);
        	if( row==null ) {	// never
        		continue;
        	}        	

            // transtor(pk,reftrans), ts(server ts )
        	if( row.getState() != DataRow.STATE_DELETE ) {
		        // trans the pk
		        // trans the ts ,now no excute ,by system.
/*        		ColumnMeta[] cols = table.getTableMeta().getTransCols();
        		if( cols!=null ) {
        			for (int j = 0; j < cols.length; j++) {
						try {
							cols[j].getTranstor().transtor(this, row, cols[j].getColName(), param, null );
						} catch (DSException e1) {
							e1.printStackTrace();
							throw new IOException(e1.getMessage());
						}
					}
        		}*/

	            // trans the map col.          
/*        		ColumnMap[] colMaps = table.getTableMeta().getTableMap().getTransCols();
        		if( colMaps!=null ) {
        			for (int j = 0; j < cols.length; j++) {
						try {
							colMaps[j].getTranstor().transtor(this, row, colMaps[j].getColumnName(), param, null );
						} catch (DSException e1) {
							e1.printStackTrace();
							throw new IOException(e1.getMessage());
						}
					}
        		}*/
        	}       	
		}
	}

	/**
	 * @param table
	 * @param param
	 */
	protected void excuteSave(DataTable table, IOParam param)throws BusinessException{
		List<DataRow> insertList = new ArrayList<DataRow>();
		List<DataRow> updateList = new ArrayList<DataRow>();
		List<DataRow> insertUpdateList = new ArrayList<DataRow>();
		List<DataRow> deleteList = new ArrayList<DataRow>();
		
		for (int i = 0; i < table.getRowCount(); i++) {
			DataRow row = table.getRow(i);
			switch( row.getState() ) {
				case DataRow.STATE_INSERT:
					insertList.add(row);
					break;
				case DataRow.STATE_UPDATE:
					updateList.add(row);
					break;
				case DataRow.STATE_DELETE:
					deleteList.add(row);
					break;
				case DataRow.STATE_INSERT_UPDATE:
					insertUpdateList.add(row);
					break;
				default:
					break;
			}
		}
		
		// excute insert
		DataRow[] rows = insertList.toArray(new DataRow[insertList.size()] );
        insertRows( rows , param  );
        
        // excute update
        rows = updateList.toArray(new DataRow[updateList.size()] );
        updateRows( rows, param );
        
        // excute insertupdate
        rows = insertUpdateList.toArray(new DataRow[insertUpdateList.size()] );
        insertUpdateRows( rows, param  );
        
        // excute delete
        rows = deleteList.toArray(new DataRow[deleteList.size()] );
        deleteRows( rows, param  );
	}
	
	/**
	 * @param table
	 * @param param
     * @throws BusinessException
	 */
	private void deleteRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null ) {
			DSLogger.debug("will insert rows is null");
			return;
		}
		
		for (int i = 0; i < rows.length; i++) {			
			deleteRow( rows[i], param);
		}	
	}
	

	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	private boolean deleteRow(DataRow row, IOParam param) throws BusinessException {
		if( row==null ) {
			DSLogger.debug("will insert rows is null");
			return true;
		}
		sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_DELETE ) );	// before event.
		checkRow(row,param);		

		boolean result = true;
		result = excuteDeleteRow( row, param );
		
		sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_DELETE, ResolverEvent.TYPE_TIME_AFTER ) );	// after event.
		
		return result;
	}
	
	/**
	 * @param row
	 * @param param
	 * @return
	 */
	abstract protected boolean excuteDeleteRow(DataRow row, IOParam param)  throws BusinessException;

	/**
	 * @param table
	 * @param param
	 * @throws BusinessException
	 */
	protected void insertUpdateRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null ) {
			DSLogger.debug("will insert rows is null");
			return;
		}

		for (int i = 0; i < rows.length; i++) {
			insertUpdateRow( rows[i], param);
		}			
	}
	
	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	protected boolean insertUpdateRow(DataRow row, IOParam param) throws BusinessException {
		
		boolean result = true;
		try {
			result = insertRow(row,param);
		} catch (BusinessException e) {
			e.printStackTrace();		
			result = false;
		}
		
		// update Row
		if( result == false ) {
			result = updateRow( row, param);
		}
		
		return result;
	}
	
	/**
	 * @param table
	 * @param param
     * @throws BusinessException
	 */
	private void updateRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null ) {
			DSLogger.debug("will insert rows is null");
			return;
		}
		
		
		for (int i = 0; i < rows.length ; i++) {
			updateRow( rows[i], param);
		}	
	}
	
	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	protected boolean updateRow(DataRow row, IOParam param) throws BusinessException {
		if( row==null ) {
			DSLogger.debug("will insert row is null");
			return true;
		}

		sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_UPDATE ) );	// before event.
		checkRow(row,param);		
		
		boolean result = true;
		result = excuteUpdateRow(row, param);

		sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_UPDATE, ResolverEvent.TYPE_TIME_AFTER ) );	// after event.
		
		return result;
	}

	/**
	 * @param row
	 * @param param
	 * @return
	 */
	abstract protected boolean excuteUpdateRow(DataRow row, IOParam param) throws BusinessException;

	/**
	 * @param table
     * @throws BusinessException 
	 */
	protected  void insertRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null ) {
			DSLogger.debug("will insert rows is null");
			return;
		}
		
		
		for (int i = 0; i < rows.length; i++) {
			insertRow( rows[i], param);

		}		
	}
	
	private boolean insertRow(DataRow row, IOParam param)throws BusinessException {
		if( row==null ) {
			DSLogger.debug("will insert row is null");
			return true;
		}
		sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT)); // before
		// event.
		checkRow(row, param);

		boolean result = true;
		result = excuteInserRow( row,param );
		
		sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT,
				ResolverEvent.TYPE_TIME_AFTER)); // after event.

		return result;
	}
	
	
	/**
	 * @param row
	 * @param param
	 */
	abstract protected boolean excuteInserRow(DataRow row, IOParam param)throws BusinessException;

	/**
	 * check constraint.
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	protected void checkRow(DataRow row, IOParam param ) throws BusinessException{		
    	// check
    	ConstraintList consList = row.getDataTable().getTableMeta().getConstraintList();
    	for (int j = 0; j < consList.size(); j++) {
			IConstraint constraint = consList.getConstraint(j);
			constraint.CheckConstraint(this, row,response );				
		}
    	       		
	}
	
	protected void preSave(DataTable table, IOParam param) throws BusinessException {
        sendEvent(new ResolverEvent(table,ResolverEvent.TYPE_OPER_SAVE) );

        msgBuf = new StringBuffer();       
        
        // check
    	ConstraintList consList = table.getTableMeta().getConstraintList();
    	for (int j = 0; j < consList.size(); j++) {
			IConstraint constraint = consList.getConstraint(j);
			constraint.CheckConstraint(this, table ,response );				
		}
    }

	protected void endSave(DataTable table, IOParam param) throws BusinessException {
        sendEvent(new ResolverEvent(table,ResolverEvent.TYPE_OPER_SAVE, ResolverEvent.TYPE_TIME_AFTER )  );
    }
    
    
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.adapter.IDataAdapter#save(nc.vo.dbtool.ds.TableSet, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	public void save(TableSet tableSet, IOParam param) throws IOException,
			BusinessException {
		// TODO auto gen

	}
	
   public void addSaveListener(ISaveListener listener) {
        getEventServer().addEventListener(listener);
    }

    public void removeSaveListener(ISaveListener listener) {
        getEventServer().removeEventListener(ISaveListener.class, listener );
    }

    /**
     * @return the eventServer
     */
    public IEventServer getEventServer() {
        if( eventServer==null ) {
            eventServer = EventServerFactory.getEventServer( DBDataAdapter.class.getName() );
            eventServer.registerEvent(ResolverEvent.class, ISaveListener.class,new ResolverEventDispatcher() );
        }
        return eventServer;
    }
    
    public void sendEvent(ResolverEvent event ) throws BusinessException {
    	try {
			getEventServer().sendEvent(event);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
    }

}
