package nc.vo.hr.tools.dbtool.ds;

import java.util.HashMap;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ParamVO;
import nc.vo.hr.tools.dbtool.ds.impl.filter.RowFilterFactory;
import nc.vo.hr.tools.dbtool.ds.index.FindIndex;
import nc.vo.hr.tools.dbtool.ds.index.RowIndiesManager;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.Properties;
import nc.vo.hr.tools.dbtool.util.StringHelper;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1414:04:37
 * @author „∆≥§∫£
 */
public class DataTable implements IDataTable,INavigate {
	private static final long serialVersionUID = -2768006566217490184L;

	private DataRowList rowList;
	private TableMeta tableMeta;	// table meta.
	
	private IRowFilter rowFilter;
	private Properties params;
	private ParamVO importParam;
	transient private TableSet tableSet;

	private int currentRow = -1;

	private boolean isPaginate = false;

	private int pageSize;

	private int pageCount;

	private int pageIndex;
	
	// index
	IRowIndex	mainIndex;
	
	transient private Map<String,FindIndex> dataIndexMap;
	
	public DataTable() {
		super();		
	}	

	public DataTable(String tableName) {
		super();
		setTableName(tableName);
	}

	public DataRowList getRowList() {
		if( rowList==null ) {
			rowList = new DataRowList(this);
		}
		return rowList;
	}
	
	public String getTableName() {
		return getTableMeta().getTableName();
	}

	public void setTableName(String tableName) {
		getTableMeta().setTableName(tableName);
	}

	public void setData(int row, int col,Object val) {
		DataRow dataRow = getRow(row);
		if( dataRow==null ) {
			DSLogger.debug("the datarow is null");
			return;
		}
		dataRow.setData(col, val);
	}

	public void setData(int row, String colName,Object val) {
		DataRow dataRow = getRow(row);
		if( dataRow==null ) {
			DSLogger.debug("the datarow is null");
			return;
		}
		dataRow.setData(colName, val);
	}

	public DataRow addRow() {
		return getMainIndex().addRow();
	}

	public DataRow addRow(Object[] objs) {
		return getMainIndex().addRow(objs);
	}
	
	public DataRow removeRow(int index) {
		return getMainIndex().removeRow(index);
	}

	public DataRow insertRow(int row) {
		return getMainIndex().insertRow(row);
	}

	public DataRow[] getRows() {
		return getMainIndex().getRows();
	}
	
	public DataRow getRow(int row) {
		return getMainIndex().getRow(row);
	}
	
	public boolean isEmpty() {
		return getMainIndex().isEmpty();
	}

	public int getRowCount() {
		return getMainIndex().getSize();
	}

	public int getColCount() {
		return getTableMeta().getColMetaList().getColCount();
	}

	public Object getData(int row, int col) {
		DataRow dataRow = getRow(row);
		if( dataRow==null ) {
			DSLogger.debug("the datarow is null");
			return null;
		}
		return dataRow.getData(col);
	}

	public Object getData(int row, String colName) {
		DataRow dataRow = getRow(row);
		if( dataRow==null ) {
			DSLogger.debug("the datarow is null");
			return null;
		}
		return dataRow.getData(colName);
	}
	
	public TableMeta getTableMeta() {
		if (tableMeta == null) {
			tableMeta = new TableMeta();		
			tableMeta.setDataTable(this);
		}

		return tableMeta;
	}

	public void setTableMeta(TableMeta tableMeta) {
		if( tableMeta==null ) {
			return ;
		}
		this.tableMeta = tableMeta;
		this.tableMeta.setDataTable(this);
	}
		
	public ColMetaList getColMetaList() {
		return getTableMeta().getColMetaList();
	}

	public void setColMetaList(ColMetaList cols) {
		getTableMeta().setColMetaList(cols);
	}

	public void mergeTableData(IDataTable data) {
		// TODO Auto-generated method stub

	}

	public void setRowFilter(IRowFilter filter) {
		this.rowFilter = filter;
		getMainIndex().index();
	}

	public IRowFilter getRowFilter() {
		return this.rowFilter;
	}
	
	/**
     * @return Returns the params.
     */
    public Properties getParams() {
    	if( params==null  ) {
    		params = new Properties(); 
    	}
    	
        return params;
    }
    /**
     * @param params The params to set.
     */
    public void setParams(Properties params) {
        this.params = params;
    }
    
    public Object getParam(String key){
    	return getParams().getProperty(key);
    }
    
    public void setParam(String key, Object value) {
    	getParams().setProperty(key, value);
    }

    public TableSet getTableSet() {
		return tableSet;
	}

    public void setTableSet(TableSet tableSet) {
		this.tableSet = tableSet;
	}

    // navigator    
	public int getCurrentRow() {
		return currentRow;
	}

	public DataRow first() {
		return moveTo(0);
	}

	public DataRow next() {
		return moveTo( getCurrentRow()+1 );
	}

	public DataRow last() {
		return moveTo(getRowCount()-1);
	}

	public DataRow moveTo(int row) {
		if( row>= getRowCount() || row<0 ) {
			DSLogger.debug("the row index is invalid");
			return null;
		}
		currentRow = row;
		return getRow(currentRow);
	}

	public DataRow getCurrentData() {
		return getRow(currentRow);
	}
	
	public Object moveNext() {
		return next();
	}

	public Object getCurrent() {
		return getCurrentData();
	}

	// page 
	public boolean isPaginate() {
		return isPaginate;
	}
	public void setPageSize(int size) {
		this.pageSize = size;
	}

	public void setPageCount(int count) {
		this.pageCount = count;
	}

	public void setPageIndex(int index) {
		this.pageIndex = index;
	}

	public int getPageSize() {
		if( isPaginate()==false ) {
			return getRowCount();
		}
		return this.pageSize;
	}
	
	/**
	 * only for the init size of buf.
	 * @return
	 */
	int getInitRowSize() {
		if( isPaginate()==false ) {
			return getRowCount();
		}
		return this.pageSize;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	protected IRowIndex getMainIndex() {
		if( mainIndex== null ) {
			try {
				mainIndex = new RowIndiesManager(this);
			} catch (DSException e) {
				e.printStackTrace();
			}
		}
		return mainIndex;
	}

	public DataRow[] findAll(String[] colNames, Object[] vals) {
		if( colNames==null ) {
			return new DataRow[0];
		}
		
		FindIndex dataIndex = findDataIndex(colNames);
				
		return dataIndex.findData(vals);
	}

	/**
	 * @param colNames
	 * @return
	 */
	public FindIndex findDataIndex(String[] colNames) {
		String key = StringHelper.getObjArrayString( colNames );
		FindIndex dataIndex = getDataIndexMap().get( key );
		if( dataIndex==null ) {
			dataIndex = new FindIndex( this, colNames );
			getDataIndexMap().put( key, dataIndex );
		}
		return dataIndex;
	}

	public DataRow[] findAll(String colName, Object val) {
		return findAll(new String[] {colName},new Object[] {val});
	}

	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append( getTableName() ).append("\n");
		str.append( getTableMeta().toString() ).append("\n");
		for (int i = 0; i < getRowCount(); i++) {
			str.append( getRow(i).toString() ).append("\n");
		}		
		return str.toString();
	}		
    
    /**
     * row filter for the row visibility.
     * @param type  DataRow.
     * @return
     */
    public IRowFilter setVisibility(int type) {
        IRowFilter filter = RowFilterFactory.createRowFilter(type);
        
        setRowFilter(filter);
        return filter;
    }
	/**
	 * @return ∑µªÿ dataIndexMap°£
	 */
	protected Map<String,FindIndex> getDataIndexMap() {
		if( dataIndexMap ==null ) {
			dataIndexMap = new HashMap<String,FindIndex>();
		}
		return dataIndexMap;
	}
	public ParamVO getImportParam() {
		return importParam;
	}

	public void setImportParam(ParamVO importParam) {
		this.importParam = importParam;
	}

}
