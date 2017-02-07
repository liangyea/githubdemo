/*
 * 创建日期 2006-4-27
 */
package nc.vo.hr.tools.dbtool.ds.impl;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.adapter.excel.ExcelAdapter;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-27 15:20:14
 * @author ych
 */
public class RowNoRowHintBuilder extends DefaultRowHintBuilder {

	
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IRowHintBuilder#creHint(nc.vo.dbtool.ds.DataRow)
	 */
	public String creHint(DataRow row) {
		String hint =  super.creHint(row);
		
		String msg="";
		
		msg += "行号 ";
		msg += row.getData( ExcelAdapter.ROWNO );
		msg +=" ";
		
		return msg + hint;
	}
}
