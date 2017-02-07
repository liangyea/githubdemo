package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;
import java.util.Arrays;

import nc.vo.hr.tools.dbtool.util.ListMap;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1716:24:16
 * @author „∆≥§∫£
 */
public class TableRelationList implements Serializable{
    private ListMap<TableRelation> tableRelaionList;
    
	public ListMap<TableRelation> getTableRelationList() {
		if( tableRelaionList==null ) {
			tableRelaionList = new ListMap<TableRelation>("parentTableName");
		}
		return tableRelaionList;
	}

	public void setTableRelationList(ListMap<TableRelation> tableRelaionList) {
		this.tableRelaionList = tableRelaionList;
	}

	public int size() {
		return getTableRelationList().size();
	}

	public TableRelation[] getTableRelations() {
		return getTableRelationList().toArray(
				new TableRelation[getTableRelationList().size()]);
	}

	public void setTableRelations(TableRelation[] tables) {
		if (tables == null) {
			return;
		}
		getTableRelationList().addAll(Arrays.asList(tables));
	}

	public TableRelation getTableRelation(int index) {
		return getTableRelationList().get(index);
	}

	public void setTableRelation(int index, TableRelation tableRelaion) {
		getTableRelationList().set(index, tableRelaion);
	}

	public void addTableRelation(TableRelation tableRelaion) {
		getTableRelationList().add(tableRelaion);
	}	

	public void removeTableRelation(TableRelation relation) {
		getTableRelationList().remove(relation);
	}
	
	public void removeTableRelation(String relationName ) {
		getTableRelationList().removeByKey(relationName);
	}

	/**
	 * @param toTableName
	 */
	public TableRelation getTableRelation(String parentTableName) {
		return getTableRelationList().getData(parentTableName);
	}
}
