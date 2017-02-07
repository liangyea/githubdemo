package nc.bs.hr.tools.dbtool.ds.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.generator.SystemTsGenerator;
import nc.vo.hr.tools.dbtool.ds.ColumnMeta;
import nc.vo.hr.tools.dbtool.ds.ConstraintList;
import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.IConstraint;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.TableMeta;
import nc.vo.hr.tools.dbtool.ds.TableRelation;
import nc.vo.hr.tools.dbtool.ds.TableSet;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOException;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.adapter.ISaveListener;
import nc.vo.hr.tools.dbtool.ds.adapter.ResolverEvent;
import nc.vo.hr.tools.dbtool.ds.adapter.ResolverEventDispatcher;
import nc.vo.hr.tools.dbtool.ds.impl.constraint.DBUniqueConstraint;
import nc.vo.hr.tools.dbtool.ds.impl.constraint.UniqueConstraintException;
import nc.vo.hr.tools.dbtool.ds.map.ColumnMap;
import nc.vo.hr.tools.dbtool.ds.map.ITranstor;
import nc.vo.hr.tools.dbtool.ds.map.TableMap;
import nc.vo.hr.tools.dbtool.ds.map.transtor.RefTranstor;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.db.DBSession;
import nc.vo.hr.tools.dbtool.util.event.IBaseEventServer;
import nc.vo.hr.tools.dbtool.util.event.SimpleEventServer;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;

/**
 * Describe:HR_PUB3.5
 * 		blob,later may extends AbstractDataAdapter.this will change like the datatable's excute.
 * @version 1.0	2006-4-20 15:13:24
 * @author 闫长海
 */
public class DBDataAdapter extends nc.bs.pub.DataManageObject implements IDataAdapter {
    public static int SAVE_TYPE_COMMON = 0;
    public static int SAVE_TYPE_IMPORT = 1;
    
    private IBaseEventServer eventServer;
    
    // if ignore will buf the msg.
	private ErrorResponse response = new ErrorResponse();
    private StringBuffer msgBuf;
    
    private DBSession	jdbcSession;	// test maybe have some bug.
    
	private Map<IDataTable,TableMap> tableMapCache = new HashMap<IDataTable,TableMap>();	// the standard table map.

    private DBUniqueConstraint dbUniqueConstraint;	//目前只会有一个
    
    public DBDataAdapter() throws NamingException{
        super();        
    }

    public DataTable load(DataTable table, IOParam param) throws IOException {
        return null;
    }

    public TableSet load(TableSet tableSet, IOParam param) throws IOException {
        return null;
    }

    /**
     * save to db.
     * @throws BusinessException
     */
    public void save(DataTable table, IOParam param) throws IOException, BusinessException {
        if( table==null ) {
            return;
        }
        
        if( param!=null && param.getSaveListener()!=null ) {
        	addSaveListener( param.getSaveListener() );
        }
                
        // pre excute save.
        try {
			preSave(table, param);

			// excute the pre data transtor
//			excuteTranst(table, param);
			
			// save
			excuteSave(table, param);

			// saved

			endSave(table, param);
		} catch (IOException e) {
			throw e;			
		} catch (BusinessException e) {
			throw e;			
		}
        catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}finally {
			getJdbcSession().closeAll();
			jdbcSession = null;
		}
    }
    
    /**
	 * @param table
	 * @param param
	 * @throws BusinessException
	 */
	protected void excuteSave(DataTable table, IOParam param) throws BusinessException {
		
		List<DataRow> insertList = new ArrayList<DataRow>();
		List<DataRow> updateList = new ArrayList<DataRow>();
		List<DataRow> insertUpdateList = new ArrayList<DataRow>();
		List<DataRow> deleteList = new ArrayList<DataRow>();
		
		ColumnMap[] transCols = table.getTableMeta().getTableMap().getTransCols();
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
						
			excuteRowTrastor(row, transCols, param);			
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
	 * now only for the pre trans,not like data trans will map trans.
	 * @param row
	 * @param transCols
	 * @param param
	 * @throws BusinessException
	 * @throws DSException
	 */
	private void excuteRowTrastor(DataRow row, ColumnMap[] transCols, IOParam param) throws BusinessException {		
			if( transCols.length<1 ) {
				return;
			}
			for (int i = 0; i < transCols.length; i++) {
				ITranstor transtor = transCols[i].getTranstor();
				if( transtor!=null ) {
					transtor.transtor(this, row, transCols[i], param, response);
				}
			}
	}

	/**
	 * @param table
     * @throws BusinessException 
	 */
	protected  boolean insertRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null || rows.length<1 ) {
			DSLogger.debug("will insert rows is null \n");
			return true;
		}
		
		boolean result = true;

		IDataTable table =rows[0].getDataTable();
		TableMap tableMap = getTableMap( table );
				

		
		for (int i = 0; i < rows.length; i++) {
			DataRow row = rows[i];

			try {
				sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT)); // before
				
				checkInsertRow(row, param);

				// cre insert sql
				String sql = SqlGenHelper.getInsertSql( tableMap,table.getTableMeta().getColFilter() );
				
				// ts
				UFDateTime ts = new UFDateTime(new SystemTsGenerator()
						.generateTS());
				row.setData("ts", ts);

				if (row.getPrimaryKey() != null && row.getPrimaryKeyData()==null ) {
					String oid = null;
					String pk_corp = (String) row.getData("pk_corp");					
					if( pk_corp!=null ) {
						oid = this.getOID(pk_corp);
					}else {
						oid = this.getOID();
					}
					row.setData(table.getTableMeta().getPrimaryKey()[0]
							.getColName(), oid);
				}

				SQLParameter params = SqlGenHelper.getInsertSqlParam(row, tableMap, table.getTableMeta().getColFilter() );
				getJdbcSession().addBatch(sql, params);
			} catch (Exception e) {
				DSLogger.debug("插入行失败:", e);
				result = false;

				sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT,response) );

				String msg = "插入行失败:"
						+ table.getTableMeta().getRowHintBuilder().creHint(row)
						+ "异常信息为：" + e.getMessage() +"\n";
				if (response.isAbort()) {
					if( e instanceof UniqueConstraintException ) {
						throw new UniqueConstraintException(msg);		
					}
					throw new BusinessException(msg);				
				} else if (response.isIgnore()) {
					getMsgBuf().append(msg);
				}
			}

			sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT,
					ResolverEvent.TYPE_TIME_AFTER)); // after event.			
		}//end for		
		
		try {
			getJdbcSession().executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			throw new BusinessException( e.getMessage() );	//todo: will excute the every row.
		} 
		
		return result;
	}
	


	// 
	public boolean getTableFldNames( TableMeta  tableMeta, String tableName, String[] fldNames, String[] toFldNames ){
		tableName = tableMeta.getTableName();
		
		TableMap tableMap = tableMeta.getTableMap();
		if( tableMap.getTableName()!=null ) {
			tableName = tableMap.getTableName();
		}
		
		List<String> fldList = new ArrayList<String>();
		List<String> toFldList = new ArrayList<String>();
		
		// db fld type map
		Map fldTypeMap = SqlGenHelper.getColmnTypes( getJdbcSession(), tableName );
		for (int i = 0; i < tableMeta.getColCount(); i++) {
			if(  !tableMeta.getColMeta(i).isEntityCol() ) {
				continue;
			}
			String colName = tableMeta.getColMeta(i).getColName();
			String toColName = colName;
			ColumnMap colMap = tableMap.getColMap(colName);
			if( colMap!=null && colMap.getToColumnName()!=null ) {
				toColName = colMap.getToColumnName();
			}
			if( fldTypeMap.get(toColName)!=null ) {
				fldList.add(colName);
				toFldList.add(toColName);
			}			
		}
		
		fldNames = fldList.toArray(new String[fldList.size()]);
		toFldNames = toFldList.toArray(new String[toFldList.size()]);
		
		return true;
	}
		
	/**
	 * @param table
	 * @param param
     * @throws BusinessException
	 */
	private boolean deleteRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null || rows.length<1) {
			DSLogger.debug("will insert rows is null");
			return true;
		}
		
		boolean result = true;
		IDataTable table =rows[0].getDataTable();		
		TableMap tableMap = getTableMap( table );
		
		// cre insert sql
		String sql = SqlGenHelper.getDeleteSql(table, tableMap );
		
		for (int i = 0; i < rows.length; i++) {			
			DataRow row = rows[i];
			sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_DELETE ) );	// before event.
			//checkInsertRow(row,param); // later add lock.			
			
			try {						
				SQLParameter params = SqlGenHelper.getDeleteSqlParam( row, tableMap );
				getJdbcSession().addBatch(sql, params);				
			} catch (Exception e) {
				DSLogger.debug("删除行失败:", e);
				result = false;

				sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT));

				String msg = "删除行失败:"
						+ table.getTableMeta().getRowHintBuilder().creHint(row)
						+ "异常信息为：" + e.getMessage() +"\n";
				if (response.isAbort()) {
					throw new BusinessException(msg);
				} else if (response.isIgnore()) {
					msgBuf.append(msg);
				}
			}
			
			sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_DELETE, ResolverEvent.TYPE_TIME_AFTER ) );	// after event.
		}// end for	
		
		try {
			getJdbcSession().executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			throw new BusinessException( e.getMessage() );	//todo: will excute the every row.
		} 
		
		return result;
	}
	
	/**
	 * @param table
	 * @param param
     * @throws BusinessException
	 */
	private boolean updateRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null || rows.length<1 ) {
			DSLogger.debug("will insert rows is null");
			return true;
		}
		
		boolean result = true;

		IDataTable table =rows[0].getDataTable();
		TableMap tableMap = getTableMap( table );
		
		for (int i = 0; i < rows.length ; i++) {
			
			DataRow row  =rows[i];
			sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_UPDATE ) );	// before event.
			
			// get update Sql
			String sql = SqlGenHelper.getUpdateSql( table, tableMap );
			
			// checkInsertRow(row,param); // later add lock.		
			UFDateTime ts = new UFDateTime(new SystemTsGenerator().generateTS());
			row.setData("ts", ts);
			
			try {					
				SQLParameter params = SqlGenHelper.getUpdateSqlParam( row, tableMap );
				getJdbcSession().addBatch(sql, params);				
			} catch (Exception e) {
				DSLogger.debug("更新行失败:", e);
				result = false;

				sendEvent(new ResolverEvent(row, ResolverEvent.TYPE_OPER_INSERT));

				String msg = "更新行失败:"
						+ table.getTableMeta().getRowHintBuilder().creHint(row)
						+ "异常信息为：" + e.getMessage() +"\n";
				if (response.isAbort()) {
					throw new BusinessException(msg);
				} else if (response.isIgnore()) {
					msgBuf.append(msg);
				}
			}

			sendEvent( new ResolverEvent(row, ResolverEvent.TYPE_OPER_UPDATE, ResolverEvent.TYPE_TIME_AFTER ) );	// after event.			
		}	//end for
		
		try {
			getJdbcSession().executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			throw new BusinessException( e.getMessage() );	//todo: will excute the every row.
		} 
		
		return result;
	}
	
	/**
	 * @param table
	 * @param param
	 * @throws BusinessException
	 */
	protected void insertUpdateRows(DataRow[] rows, IOParam param) throws BusinessException {
		if( rows==null || rows.length<1) {
			DSLogger.debug("will insert rows is null");
			return;
		}

		for (int i = 0; i < rows.length; i++) {
			insertUpdateRow( rows[i], param);
		}			
		
		// todo : will change batch insert and batch update.
	}

	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	private boolean insertUpdateRow(DataRow row, IOParam param) throws BusinessException {
		boolean result = false;
		try {
			result = insertRow(row,param);
		} catch (BusinessException e) {
			// query the pk
//			if( dbUniqueConstraint==null || !(e instanceof UniqueConstraintException) ) {	// 若没设联合唯一，则不能自动更新。
			if( dbUniqueConstraint==null ) {
				throw e;
			}
			nc.vo.hr.tools.dbtool.util.DSLogger.debug(e.getMessage());
//			e.printStackTrace();	// 下面会再更新，不用打出异常.					
		}

		if( result == true ) {
			return result;
		}
		
		// query the pk
		TableMeta tableMeta = row.getDataTable().getTableMeta();
		TableRelation pkRelation  = new TableRelation(tableMeta.getTableName(), dbUniqueConstraint.getCols(),
				tableMeta.getTableName(), dbUniqueConstraint.getCols() );
		tableMeta.getRefRelationList().addTableRelation(pkRelation);
		
		ColumnMap colMap = new ColumnMap( tableMeta.getPrimaryKeyCols()[0],tableMeta.getTableName(), tableMeta.getPrimaryKeyCols()[0] );
		RefTranstor trans = new RefTranstor();
		Object pk = trans.transtor(this, row, colMap, param, response);
		
		// update Row		
		result = updateRow( row, param);
				
		return result;
	}

	private  boolean insertRow(DataRow row, IOParam param) throws BusinessException{

		return insertRows( new DataRow[] {row}, param );
	}

	/**
	 * check constraint.
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	private void checkInsertRow(DataRow row, IOParam param ) throws BusinessException{		
    	// now only check db constraint.
/*    	ConstraintList consList = row.getDataTable().getTableMeta().getConstraintList();
    	for (int j = 0; j < consList.size(); j++) {
			IConstraint constraint = consList.getConstraint(j);
			if( constraint instanceof DBUniqueConstraint ) {
				constraint.CheckConstraint(this, row ,response );	
			}
		}*/
		if( dbUniqueConstraint!=null ) {
			dbUniqueConstraint.CheckConstraint(this, row ,response );
		}
    	       		
	}
	/**
	 * @param row
	 * @param param
	 */
	private void logDelRow(DataRow row, IOParam param) {
		// TODO 自动生成方法存根
		
	}

	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	private boolean deleteRow(DataRow row, IOParam param) throws BusinessException {

		return deleteRows( new DataRow[] {row},param );
	}

	/**
	 * @param row
	 * @param param
	 * @throws BusinessException
	 */
	private boolean updateRow(DataRow row, IOParam param) throws BusinessException {

		return updateRows( new DataRow[]{row}, param);
		
	}



	private ColumnMeta[] getMapTransCols(DataTable table) {
    	int count = table.getColCount();
    	ArrayList<ColumnMeta> list = new ArrayList<ColumnMeta>();
    	
    	for (int i = 0; i < count; i++) {
    		ColumnMeta col = table.getTableMeta().getColMeta(i);
    		if( col.getTranstor()!=null ) {
    			list.add( col );
    		}
		}
    	
    	
		return list.toArray(new ColumnMeta[list.size()]);
	}
    

	private void preSave(DataTable table, IOParam param) throws BusinessException {
        msgBuf = new StringBuffer();
        
        // check
    	ConstraintList consList = table.getTableMeta().getConstraintList();
    	for (int j = 0; j < consList.size(); j++) {
			IConstraint constraint = consList.getConstraint(j);
			if(!(constraint instanceof DBUniqueConstraint) ) {
				constraint.CheckConstraint(this, table ,response );
			}else {
				dbUniqueConstraint = (DBUniqueConstraint) constraint;
			}
		}
    	
        sendEvent(new ResolverEvent(table,ResolverEvent.TYPE_OPER_SAVE) );
    }

    private void endSave(DataTable table, IOParam param) throws BusinessException {
        sendEvent(new ResolverEvent(table,ResolverEvent.TYPE_OPER_SAVE, ResolverEvent.TYPE_TIME_AFTER )  );
    }
    
    public void save(TableSet tableSet, IOParam param) throws IOException {
    }

    public void addSaveListener(ISaveListener listener) {
        getEventServer().addEventListener(listener);
    }

    public void removeSaveListener(ISaveListener listener) {
        getEventServer().removeEventListener( listener );
    }

    /**
     * @return the eventServer
     */
    public IBaseEventServer getEventServer() {
        if( eventServer==null ) {            
        	eventServer = new SimpleEventServer(this, new ResolverEventDispatcher());
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
	/**
	 * @return 返回 jdbcSession。
	 * @throws SQLException
	 */
	public DBSession getJdbcSession() throws RuntimeException {
		if( jdbcSession ==null ) {
				try {
					jdbcSession = new DBSession(this.getConnection());
				} catch (SQLException e) {
					e.printStackTrace();
					nc.vo.hr.tools.dbtool.util.DSLogger.error("the get connection fail \n");
					throw new RuntimeException("error:the get connection fail \n");
				}
		}
		return jdbcSession;
	}
	/**
	 * @return 返回 msgBuf。
	 */
	public String getMsg() {
		if( msgBuf==null ) {
			return "";
		}
		
		return msgBuf.toString();
	}
	/**
	 * @return 返回 msgBuf。
	 */
	public StringBuffer getMsgBuf() {
		if( msgBuf==null ) {
			msgBuf = new StringBuffer();
		}
		return msgBuf;
	}
	
	/**
	 * get the stand tableMap
	 * @param table
	 * @return
	 */
	public TableMap getTableMap( IDataTable table ) {
		TableMap result = tableMapCache.get(table);
        if (result == null) {
        	result = nc.bs.hr.tools.dbtool.ds.adapter.SqlGenHelper.getTableMap( getJdbcSession(),table);
        	tableMapCache.put(table, result);
        }
        return result;
	}
	
}
