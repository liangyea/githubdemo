/*
 * 创建日期 2006-4-27
 */
package nc.vo.hr.tools.dbtool.ds.impl;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.IRowBuilder;
import nc.vo.hr.tools.dbtool.ds.TableMeta;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-27 20:05:56
 * @author ych
 */
public class DefaultRowBuilder implements IRowBuilder,Serializable {

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IRowBuilder#buildDefaultRow(nc.vo.dbtool.ds.IDataTable)
	 */
	public DataRow buildRow(IDataTable table) {
		DataRow row = new DataRow( table ) ;

		TableMeta meta = table.getTableMeta();
		for (int i = 0; i < meta.getColCount(); i++) {
			Object defaultObj = meta.getColMeta(i).getDefaultValue();
			if( defaultObj !=null ) {
				row.setData(i, defaultObj);
			}
		}
		return row;
	}

}
