package nc.vo.hr.tools.dbtool.ds.model;

import nc.vo.hr.tools.dbtool.ds.TableRelationList;
import nc.vo.hr.tools.dbtool.util.ListMap;
import nc.vo.hr.tools.dbtool.util.Properties;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1716:44:44
 * @author „∆≥§∫£
 */
public class BusiDataModel extends Properties{
	private static final long serialVersionUID = 88256486896285295L;

	private ListMap tableMetaList;
    private TableRelationList tableRelaionList;
    
	protected ListMap getTableMetaList() {
		return tableMetaList;
	}
	protected void setTableMetaList(ListMap tableMetaList) {
		this.tableMetaList = tableMetaList;
	}
	protected TableRelationList getTableRelaionList() {
		return tableRelaionList;
	}
	protected void setTableRelaionList(TableRelationList tableRelaionList) {
		this.tableRelaionList = tableRelaionList;
	}
}
