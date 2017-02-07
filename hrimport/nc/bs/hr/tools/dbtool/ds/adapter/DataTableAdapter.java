/*
 * 创建日期 2006-4-24
 */
package nc.bs.hr.tools.dbtool.ds.adapter;

import nc.vo.hr.tools.dbtool.ds.ColumnMeta;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.TableMeta;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.adapter.excel.ExcelAdapter;
import nc.vo.hr.tools.dbtool.ds.map.ColumnMap;
import nc.vo.hr.tools.dbtool.ds.map.TableMap;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.variant.VariantHelper;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		for the data table trans.now only excute the add rows.
 * @version 1.0	2006-4-24 15:41:59
 * @author ych
 */
public class DataTableAdapter extends AbstractDataAdapter {
	private DataTable storeDataTable;
	private DataRow curDataRow;	// cur Row
	
	/**
	 * 
	 */
	public DataTableAdapter(DataTable storeDataTable) {
		super();
		this.storeDataTable = storeDataTable;
		if( storeDataTable==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.error("the store data table is null");
		}
	}

	/**
	 * @return 返回 storeDataTable。
	 */
	public DataTable getStoreDataTable() {
		return storeDataTable;
	}

	/* （非 Javadoc）
	 * @see nc.bs.dbtool.ds.adapter.AbstractDataAdapter#excuteDeleteRow(nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	protected boolean excuteDeleteRow(DataRow row, IOParam param) throws BusinessException {
		// find by pk 
		
		// delete
		
		return false;
	}

	/* （非 Javadoc）
	 * @see nc.bs.dbtool.ds.adapter.AbstractDataAdapter#excuteUpdateRow(nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	protected boolean excuteUpdateRow(DataRow row, IOParam param) throws BusinessException {
		
		// find by pk
		
		// update.
		
		// TODO auto gen
		return false;
	}

	/* （非 Javadoc）
	 * @see nc.bs.dbtool.ds.adapter.AbstractDataAdapter#excuteInserRow(nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.IOParam)
	 */
	protected boolean excuteInserRow(DataRow row, IOParam param) throws BusinessException {
		curDataRow = getStoreDataTable().addRow();
		
		TableMap tableMap = row.getDataTable().getTableMeta().getTableMap();
		//TableMeta tableMeta = storeDataTable.getTableMeta();
		
		IDataTable sourceTable = row.getDataTable();
		int colCount = getStoreDataTable().getColCount();
		
		ColumnMap colMap = null;
		
		String rowHint =  sourceTable.getTableMeta().getRowHintBuilder().creHint(row);
		if( isAllNull(row, sourceTable.getTableMeta() ) ) {
			DSLogger.info( rowHint );
			return true;
		}
		for (int i = 0; i < colCount; i++) {			
			ColumnMeta toCol = storeDataTable.getColMetaList().getColMeta(i);
			String sourceColName = toCol.getColName();
			
			
			if( tableMap!=null ) {	// if not exist tablemap.
				colMap = tableMap.getColMapByToColName( toCol.getColName() );			
				if( colMap!=null ) {
					sourceColName = colMap.getColumnName();
				}
			}
			
			// check the source col is exist
			if( row.getDataTable().getTableMeta().getColMeta(sourceColName)==null ) {
				continue;
			}
			Object obj = row.getData( sourceColName );
			// if exist map transtro
			if( colMap!=null && colMap.getTranstor()!=null ) {
				obj = colMap.getTranstor().transtor(this, row, colMap,  param, null);
			}
        	Object val = VariantHelper.transObj( row, obj, toCol.getDataType() );
			
			curDataRow.setData( toCol.getColName(), val );
		}
		
		return true;
	}
	
	private boolean isAllNull(DataRow row,TableMeta tableMeta ) {
		boolean isAllNull = true;
		
		for (int i = 0; i < tableMeta.getColCount(); i++) {
			if( tableMeta.getColMeta(i).getColName().equalsIgnoreCase(ExcelAdapter.ROWNO) )
			{
				continue;
			}
			if( row.getData(i)!=null ) {
				isAllNull = false;
				break;
			}
		}
		
		return isAllNull;
	}
	
	/**
	 * @return 返回 curDataRow。
	 */
	public DataRow getCurDataRow() {
		return curDataRow;
	}
}
