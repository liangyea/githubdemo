/*
 * 创建日期 2006-4-27
 */
package nc.vo.hr.tools.dbtool.ds.impl;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-27 20:11:27
 * @author ych
 */
public class TransRowBuilder extends DefaultRowBuilder {

	
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IRowBuilder#buildRow(nc.vo.dbtool.ds.IDataTable)
	 */
	public DataRow buildRow(IDataTable table) {
		DataRow row = super.buildRow(table);
		row.setStatus( DataRow.STATE_INSERT_UPDATE );
		return row;
	}
	
}
