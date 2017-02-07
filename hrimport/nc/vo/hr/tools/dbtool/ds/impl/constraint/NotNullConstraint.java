/*
 * 创建日期 2006-4-29
 */
package nc.vo.hr.tools.dbtool.ds.impl.constraint;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.IRowHintBuilder;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.util.ComparatorUtil;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-29 11:04:11
 * @author ych
 */
public class NotNullConstraint extends  AbstractConstraint {
	
	/**
	 * @param cols
	 */
	public NotNullConstraint(String[] cols) {
		super(cols);
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.adapter.IDataAdapter, nc.vo.dbtool.ds.IDataTable, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, IDataTable table,
			ErrorResponse response) throws BusinessException {
		
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.adapter.IDataAdapter, nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, DataRow row,
			ErrorResponse response) throws BusinessException {
		StringBuffer msg  = new StringBuffer();
		IRowHintBuilder rowHint = row.getDataTable().getTableMeta().getRowHintBuilder();
		
		Object[] objs = row.getDatas( getCols() );
		
		String hint = rowHint.creHint(row) +" " ;
		for (int i = 0; i < getCols().length; i++) {
			if( ComparatorUtil.isEmpty(objs[i]) ) {
				String colName = row.getDataTable().getTableMeta().getColMeta(getCols()[i]).getLocalColName();
				if( colName==null ) {
					colName = getCols()[i];
				}
				msg.append( colName ).append(", ");
			}
		}
		
		if( msg.length()>0 ) {
			msg.setLength( msg.length()-2 );
			msg.append(" 数据不能为空!");
			
			hint += msg.toString();
			
			nc.vo.hr.tools.dbtool.util.DSLogger.info(hint);
			throw new BusinessException( hint );	
		}
	}

}
