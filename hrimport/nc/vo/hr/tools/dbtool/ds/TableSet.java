package nc.vo.hr.tools.dbtool.ds;

import nc.vo.hr.tools.dbtool.util.ListMap;
import nc.vo.hr.tools.dbtool.util.Properties;
/**
 * 
 * Describe:HR_PUB3.5
 * 		will excute the table set or table
 * @version 1.0	2006-4-13 14:26:58
 * @author „∆≥§∫£
 */
public class TableSet extends Properties{

	private static final long serialVersionUID = 2969509373004223888L;

	// datatables
	private ListMap<IDataTable> dataTableList;
	
    private TableRelationList tableRelaionList;    
	
	public ListMap<IDataTable> getDataTableList() {
		if( dataTableList==null ){
			dataTableList = new ListMap<IDataTable>();
			dataTableList.setKeyFld("tablename");
		}
		return dataTableList;
	}
	
	public int getDataTableCount() {
	    return getDataTableList().size();
	}

	IDataTable getDataTable(int index ) {
		IDataTable table = getDataTableList().get(index);
		if( table!=null ) {
			table.setTableSet(this);
		}
		return table;
	}
	
	void setDataTable(int index,  IDataTable dataTable ) {
	    
	    getDataTableList().set(index, dataTable);
		if( dataTable!=null ) {
			dataTable.setTableSet(this);
		}	    
	}
	
	public void addDataTable(IDataTable dataTable){
		getDataTableList().add(dataTable);
		if( dataTable!=null ) {
			dataTable.setTableSet(this);
		}
	}
	
	public void removeDataTable(IDataTable table) {
	    getDataTableList().remove(table);
		if( table!=null ) {
			table.setTableSet(null);
		}
	}
		
	IDataTable getDataTable(String tableName ) {
		IDataTable table = getDataTableList().getData(tableName);
		if( table!=null ) {
			table.setTableSet(this);
		}
		return table;
	}

	public IDataTable removeDataTable(String tableName) {
		IDataTable table =(IDataTable) getDataTableList().removeByKey(tableName);
		if( table!=null ) {
			table.setTableSet(null);
		}
		return table;
	}

	// tablerelaions
	public TableRelationList getTableRelationList() {
		if( tableRelaionList==null ) {
			tableRelaionList = new TableRelationList();
		}
		return tableRelaionList;
	}

	public void setTableRelationList(TableRelationList tableRelaionList) {
		this.tableRelaionList = tableRelaionList;
	}

	public int getTableRelationCount() {
		return getTableRelationList().size();
	}

	public TableRelation[] getTableRelations() {
		return getTableRelationList().getTableRelations();
	}

	public void setTableRelations(TableRelation[] tables) {
		getTableRelationList().setTableRelations(tables);
	}

	public TableRelation getTableRelation(int index) {
		return getTableRelationList().getTableRelation(index);
	}

	public void setTableRelation(int index, TableRelation tableRelaion) {
		getTableRelationList().setTableRelation(index, tableRelaion);
	}

	public void addTableRelation(TableRelation tableRelaion) {
		getTableRelationList().addTableRelation(tableRelaion);
	}	

	public void removeTableRelation(TableRelation relation) {
		getTableRelationList().removeTableRelation(relation);
	}
	
	public void removeTableRelation(String relationName ) {
		getTableRelationList().removeTableRelation(relationName);
	}	
}
