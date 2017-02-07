/*
 * 创建日期 2006-4-29
 */
package nc.vo.hr.tools.dbtool.ds.impl.constraint;

import nc.bs.hr.tools.dbtool.ds.adapter.DBDataAdapter;
import nc.bs.hr.tools.dbtool.ds.adapter.SqlGenHelper;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.map.TableMap;
import nc.vo.hr.tools.dbtool.util.DataTableUtil;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		唯一性校验,到数据库中进行校验.
 * @version 1.0	2006-4-29 8:55:47
 * @author ych
 */
public class DBUniqueConstraint extends  AbstractConstraint {

	/**
	 * 
	 */
	public DBUniqueConstraint(String[] cols) {
		super(cols);
	}
	
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, DataRow row, ErrorResponse response)
			throws BusinessException {		
		
		// 数据库查找
		DBDataAdapter adapter = (DBDataAdapter)dataAdapter;
		IDataTable table = row.getDataTable();
		
		TableMap tableMap = adapter.getTableMap( table );
		
		String sql = SqlGenHelper.getConstraintSql( tableMap , cols );
		SQLParameter params = SqlGenHelper.getConstraintParameter( tableMap,row,cols );
		
		try {
			Object[] vals = (Object[]) adapter.getJdbcSession().executeQuery(sql, params, new ArrayProcessor());
			if( vals!=null ) {
				Integer size = (Integer) vals[0];
				if( size!=null && size.intValue()>0 ) {
					String msg = table.getTableMeta().getRowHintBuilder().creHint(row); 
					msg += "唯一性校验失败! 校验数据为：";
					msg +=	DataTableUtil.getRowHintStr( row, cols);		
					msg +=  "\n";
					
					throw new UniqueConstraintException(msg);
				}
			}
		} catch (DbException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}finally {
//			adapter.getJdbcSession().closeStmt();
		}
	}


	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.IDataTable, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, IDataTable table, ErrorResponse response) {

	}


}
