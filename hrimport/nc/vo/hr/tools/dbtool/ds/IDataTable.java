package nc.vo.hr.tools.dbtool.ds;

import nc.vo.hr.tools.dbtool.ds.index.FindIndex;

/**
 * Describe:HR_PUB3.5
 * 		table data ,only data.
 * @version 1.0	2006-4-12 14:36:46
 * @author „∆≥§∫£
 */
public interface IDataTable extends IBaseDataTable{
	String getTableName();
    void setTableName(String tableName);
    
    // data oper.
    void setData( int row, int col, Object val);
    void setData( int row, String colName ,Object val);
    
    // row oper
    DataRowList getRowList();
    
    DataRow addRow();
    DataRow addRow(Object[] objs);
    DataRow removeRow( int index );
    DataRow insertRow( int row );        
    DataRow getRow(int index);
    
    // col oper.
    ColMetaList getColMetaList();
    void setColMetaList(ColMetaList cols);
        
    // table oper.
    void mergeTableData(IDataTable data );
    
    // filter  or use the dataview
    void setRowFilter(IRowFilter filter );
    IRowFilter getRowFilter();
    
    // index

    // params
    Object getParam(String key);
    void setParam(String key, Object value) ;
    
    // 
    public TableSet getTableSet();
    public void setTableSet(TableSet tableSet);
    
    // find data
    public DataRow[] findAll(String colNames[], Object vals[]);
    public DataRow[] findAll(String colName, Object val);
    
    public FindIndex findDataIndex(String[] colNames);
}
