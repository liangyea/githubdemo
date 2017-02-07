package nc.vo.hr.tools.dbtool.ds.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.vo.hr.tools.dbtool.util.ListDoubleMap;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-13 10:46:47
 * @author 闫长海
 */
public class TableMap implements Serializable {
	private String tableName;
	
	private ListDoubleMap<ColumnMap> colMapList;
//	private ListMap colMapList;
	
	public ListDoubleMap<ColumnMap> getList() {
		if (colMapList == null) {
			colMapList = new nc.vo.hr.tools.dbtool.util.ListDoubleMap<ColumnMap>("columnName","toColumnName");	// 以tocolumnName索引可能会重复，因为可能会有不同表,以后ListMap修改为支持多索引
		}
		return colMapList;
	}
	
	public boolean isEmpty() {
		return getList().isEmpty();
	}
	
	public int size() {
		return getList().size();
	}
	
	public void addColMap(ColumnMap colMap) {
		getList().add(colMap);
	}
	
	public ColumnMap removeMap(int index) {
		return getList().remove(index);
	}
	
	public ColumnMap getColMap(int index ) {
		return getList().get(index);
	}
	
	/**
	 * get columnmap by the to col name.
	 * @param colName
	 * @return
	 */
	public ColumnMap getColMap(String colName) {
		return getList().getData(colName);
	}
	
	public ColumnMap getColMapByToColName(String toColName) {
		return (ColumnMap) getList().getDataByKey2(toColName);
	}
	
	public ColumnMap[] getColMaps() {
		return getList().toArray( new ColumnMap[size()] );
	}
	
	public void setColMaps(ColumnMap[] colMaps) {
		if( colMaps==null ) {
			return ;
		}
		
		getList().addAll( Arrays.asList(colMaps) );
	}
	
    public ColumnMap[] getTransCols() {
    	ArrayList<ColumnMap> list = new ArrayList<ColumnMap>();
    	
    	for (int i = 0; i < this.size(); i++) {
    		ColumnMap col = this.getColMap(i);
    		if( col.getTranstor()!=null ) {
    			list.add( col );
    		}
		}
    	
    	
		return list.toArray(new ColumnMap[list.size()]);
	}
	/**
	 * @return 返回 tableName。
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName 要设置的 tableName。
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getToColName( String colName ) {
		ColumnMap colMap = getColMap(colName);
		if( colMap==null || colMap.getToColumnName()==null ) {
			return colName;
		}
		return  colMap.getToColumnName();
	}
	
	public String[] getToColNames( String[] colNames ) {
		if( colNames==null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < colNames.length; i++) {
			list.add( colNames[i] );
		}
		
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * get the all cols.
	 * @return
	 */
	public String[] getColNames() {

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < size(); i++) {
			list.add( this.getColMap(i).getColumnName() );
		}
		
		return list.toArray(new String[list.size()]);
	}
}
