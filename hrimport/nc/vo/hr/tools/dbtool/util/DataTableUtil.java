/*
 * 创建日期 2006-4-29
 */
package nc.vo.hr.tools.dbtool.util;

import nc.vo.hr.tools.dbtool.ds.ConstraintList;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IConstraint;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.impl.constraint.NotNullConstraint;
import nc.vo.hr.tools.dbtool.ds.impl.constraint.UniqueConstraint;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-29 10:45:12
 * @author ych
 */
public class DataTableUtil {

	public static String getRowHintStr(DataRow row, String[] cols) {
		if( cols==null ) {
			return "";
		}
		
		Object[] objs = row.getDatas(cols);
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cols.length; i++) {
			str.append(cols[i]).append("=");
			str.append( objs[i] ).append(" ");			
		}
		return str.toString();
	}	
	
	public static void checkUIConstraint( IDataTable table ) throws BusinessException {
    	
		ConstraintList consList = table.getTableMeta().getConstraintList();
    	for (int j = 0; j < consList.size(); j++) {
			IConstraint constraint = consList.getConstraint(j);
			if( constraint instanceof UniqueConstraint ) {
				constraint.CheckConstraint(null, table , null );
			}else if( constraint instanceof NotNullConstraint ) {
				checkRowConstraint( table, constraint );
			}
		}
	}

	/**
	 * @param table
	 * @param constraint
	 */
	private static void checkRowConstraint(IDataTable table, IConstraint constraint) throws BusinessException {
		for (int i = 0; i < table.getRowCount(); i++) {
			DataRow row = table.getRow(i);			
			constraint.CheckConstraint(null, row, null);
		}
	}
	
}
