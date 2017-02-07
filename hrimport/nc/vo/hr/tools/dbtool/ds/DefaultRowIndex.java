package nc.vo.hr.tools.dbtool.ds;

import nc.vo.hr.tools.dbtool.util.DSLogger;


/**
 * Describe:HR_PUB3.5
 * 		will direct oper the datarowlist array.
 * @version 1.0	2006-4-1910:31:07
 * @author „∆≥§∫£
 */
public class DefaultRowIndex implements IRowIndex {
	private DataTable table;

	public DefaultRowIndex(DataTable table) {
		super();
		this.table = table;
		if( table==null ) {
			DSLogger.debug("table is null");
		}
	}

	public DataTable getTable() {
		return table;
	}
	
	public DataRowList getRowList() {
		return getTable().getRowList();
	}
		
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#addRow()
	 */
	public DataRow addRow() {
		DataRow row = getRowList().arrayAddRow();
		return row;
    }
	
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#addRow(java.lang.Object[])
	 */
	public DataRow addRow(Object[] objs) {
		DataRow row = getRowList().arrayAddRow(objs);
		return row;
    }
    
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#removeRow(int)
	 */
	public DataRow removeRow( int index ) {
		DataRow row  = null;
		row = getRowList().arrayRemoveRow( index );	
		
		return row;
    }
	
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#insertRow(int)
	 */
	public DataRow insertRow( int row ) {
		return getRowList().arrayInsertRow(row);
    	
    }
    
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#getRow(int)
	 */
	public DataRow getRow(int index) {
		return getRowList().arrayGetRow(index);
    }
	
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#isEmpty()
	 */
	public boolean isEmpty() {
		return getRowList().isArrayEmpty();
	}
	
	/* (non-Javadoc)
	 * @see nc.vo.dbtool.ds.IRowIndex#getSize()
	 */
	public int getSize() {
		return getRowList().arraySize();
	}

	public void index() {
		// default the row index is null ,will use the datarowlist.
		// do nothing.
	}

	public boolean removeRow(DataRow row) {
		return getRowList().arrayRemoveRow(row);
	}

	/* £®∑« Javadoc£©
	 * @see nc.vo.dbtool.ds.IRowIndex#getRows()
	 */
	public DataRow[] getRows() {
		return getRowList().arrayGetRows();
	}
	
}
