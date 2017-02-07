package nc.vo.hr.tools.dbtool.ds.impl;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1415:56:42
 * @author „∆≥§∫£
 */
public class DataRowFactory {

	public static DataRow buildDefaultRow(IDataTable table ) {
		return table.getTableMeta().getRowBuilder().buildRow(table);
	}
	
	public static DataRow buildDefaultRow(IDataTable table, Object[] datas ) {
		DataRow row = new DataRow( table ) ;

			// by the colmeta set the default value ??
		row.setDatas(datas);
		return row;
	}
}
