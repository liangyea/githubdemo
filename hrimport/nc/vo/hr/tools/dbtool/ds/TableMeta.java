package nc.vo.hr.tools.dbtool.ds;

import java.util.ArrayList;
import java.util.List;

import nc.vo.hr.tools.dbtool.ds.impl.DefaultRowBuilder;
import nc.vo.hr.tools.dbtool.ds.impl.DefaultRowHintBuilder;
import nc.vo.hr.tools.dbtool.ds.map.TableMap;
import nc.vo.hr.tools.dbtool.util.Properties;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-12 14:56:15
 * @author 闫长海
 */
public class TableMeta extends Properties implements Cloneable{
	private static final long serialVersionUID = 397524695221039723L;
	
	private String schemeName;
	private String tableName;
	private String localTableName;
	
	private ColMetaList colMetaList;
//	private IQueryModel filter;	// for the query
	private ColumnMeta[] primaryKey;
	
	private ConstraintList constraintList;
	private SortList sortList;	
	private TableMap tableMap;		
	
	private IDataTable table;	
	private TableRelationList parentRelationList;
	private TableRelationList childRelationList;
    
    private TableRelationList refRelationList;
    
//    private String rowClassName;	//later realize
    private IRowHintBuilder rowHintBuilder;
    private IRowBuilder rowBuilder;
    
    private IColFilter colFilter;// for persist.
    
    public TableMeta() {
		super();		
	}
    
    public TableMeta(String tableName) {
		super();		
		setTableName( tableName);
	}
    
    public TableMeta(IDataTable table) {
		super();		
		setDataTable( table );
	}
    
	public IDataTable getDataTable() {
		return table;
	}
	public void setDataTable(IDataTable table) {
		this.table = table;
	}
    
	public String getSchemaName(){
    	return this.schemeName;
    }
    public void setSchemaName( String schemeName ) {
    	this.schemeName = schemeName;
    }

	public  String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public ColMetaList getColMetaList() {
		if( colMetaList==null ) {
			colMetaList = new ColMetaList(this);
		}
		
		return colMetaList;
	}
	
	public void setColMetaList(ColMetaList colMetaList) {
		this.colMetaList = colMetaList;
	}
	
	public ConstraintList getConstraintList() {
		if( constraintList==null ) {
			constraintList = new ConstraintList();
		}
		return constraintList;
	}
	public void setConstraintList(ConstraintList constraintList) {
		this.constraintList = constraintList;
	}
	public SortList getSortList() {
		return sortList;
	}
	public void setSortList(SortList sortList) {
		this.sortList = sortList;
	}
	public TableMap getTableMap() {
		if( tableMap==null ) {
			tableMap = new TableMap();
		}
		return tableMap;
	}
	public void setTableMap(TableMap tableMap) {
		this.tableMap = tableMap;
	}

	public ColumnMeta[] getPrimaryKey() {
		return primaryKey;
	}
	
	public String[] getPrimaryKeyCols() {
		ColumnMeta[] cols = getPrimaryKey();
		if( cols==null ) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < cols.length; i++) {
			list.add( cols[i].getColName() );
		}
		return list.toArray(new String[list.size()]);
	}

	public void setPrimaryKey(ColumnMeta[] primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setPrimaryKey(ColumnMeta colMeta) {
		this.primaryKey = new ColumnMeta[] {colMeta};
	}	
    
    // table ralations    
    public TableRelationList getParentRelationList() {
		return parentRelationList;
	}

    public void setParentRelationList(TableRelationList parentRelationList) {
		this.parentRelationList = parentRelationList;
	}
    
    public TableRelationList getChildRelationList() {
		return childRelationList;
	}

    public void setChildRelationList(TableRelationList childRelationList) {
		this.childRelationList = childRelationList;
	}

    public TableRelationList getRefRelationList() {
    	if( refRelationList==null ) {
    		refRelationList = new TableRelationList();
    	}
		return refRelationList;
	}

    public void setRefRelationList(TableRelationList refRelationList) {
		this.refRelationList = refRelationList;
	}
    
    // col oper
    public int getColCount() {
    	return getColMetaList().getColCount();
    }
    
    public String[] getColMetaNames() 
    {
    	return getColMetaList().getColMetaNames();
    }
    
    public ColumnMeta getColMeta(int col) {
        return getColMetaList().getColMeta(col);
    }
    
    public ColumnMeta getColMeta(String colName) {
    	return getColMetaList().getColMeta(colName);
    }
    
    public ColumnMeta[] getTransCols() {
    	int count = this.getColCount();
    	ArrayList<ColumnMeta> list = new ArrayList<ColumnMeta>();
    	
    	for (int i = 0; i < count; i++) {
    		ColumnMeta col = this.getColMeta(i);
    		if( col.getTranstor()!=null ) {
    			list.add( col );
    		}
		}
    	
    	
		return list.toArray(new ColumnMeta[list.size()]);
	}

	public IRowHintBuilder getRowHintBuilder() {
		if( rowHintBuilder==null ) {
			rowHintBuilder = new DefaultRowHintBuilder();
		}
		return rowHintBuilder;
	}

	public void setRowHintBuilder(IRowHintBuilder rowHintBuilder) {
		this.rowHintBuilder = rowHintBuilder;
	}

	/**
	 * @return 返回 localTableName。
	 */
	public String getLocalTableName() {
		return localTableName;
	}
	/**
	 * @param localTableName 要设置的 localTableName。
	 */
	public void setLocalTableName(String localTableName) {
		this.localTableName = localTableName;
	}
	/**
	 * @return 返回 rowBuilder。
	 */
	public IRowBuilder getRowBuilder() {
		if( rowBuilder==null ) {
			rowBuilder = new DefaultRowBuilder();
		}
		return rowBuilder;
	}
	/**
	 * @param rowBuilder 要设置的 rowBuilder。
	 */
	public void setRowBuilder(IRowBuilder rowBuilder) {
		this.rowBuilder = rowBuilder;
	}
	
	
	/* （非 Javadoc）
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getColMetaList().toString();
	}
	
	/**
	 * @return 返回 colFilter。
	 */
	public IColFilter getColFilter() {
		return colFilter;
	}
	
	/**
	 * @param colFilter 要设置的 colFilter。
	 */
	public void setColFilter(IColFilter colFilter) {
		this.colFilter = colFilter;
	}
}
