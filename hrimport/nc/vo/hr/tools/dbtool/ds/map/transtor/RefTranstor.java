/*
 * 创建日期 2006-4-27
 */
package nc.vo.hr.tools.dbtool.ds.map.transtor;

import java.util.List;

import nc.bs.hr.tools.dbtool.ds.adapter.DBDataAdapter;
import nc.bs.hr.tools.dbtool.ds.adapter.SqlGenHelper;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.TableMeta;
import nc.vo.hr.tools.dbtool.ds.TableRelation;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.map.ColumnMap;
import nc.vo.hr.tools.dbtool.ds.map.ITranstor;
import nc.vo.hr.tools.dbtool.util.StringHelper;
import nc.vo.hr.tools.dbtool.util.db.DBSession;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		backgroud ref trans for the import.if query will not use this trastor, maybe use link query.
 * @version 1.0	2006-4-27 14:04:20
 * @author ych
 */
public class RefTranstor implements ITranstor {

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.map.ITranstor#transtor(nc.vo.dbtool.ds.adapter.IDataAdapter, nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.map.ColumnMap, nc.vo.dbtool.ds.adapter.IOParam, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public Object transtor(IDataAdapter dataAdapter, DataRow row,
			ColumnMap colMap, IOParam param, ErrorResponse response)
			throws BusinessException {
		
		// 
		Object val = null;
		TableMeta tableMeta = row.getDataTable().getTableMeta();
		
		TableRelation relation  = tableMeta.getRefRelationList().getTableRelation( colMap.getToTableName() );
		if( relation==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("the table relaion is null");
			return null;
		}
		String sql = generalRefSql( relation, colMap.getToColumnName() );

		DBSession jdbcSession = ((DBDataAdapter)dataAdapter).getJdbcSession();
		
		SQLParameter params = SqlGenHelper.getRefSQLParam( jdbcSession, relation, row);
		
		String msg = "";
		try {
			List result = (List) jdbcSession.executeQuery(sql, params, new ArrayListProcessor() );
			
			if( result==null || result.size()<1 ) {
				msg += "数据匹配失败,可能数据不存在或设置不正确 "+colMap.getColumnName()+ getHintRowMsg( tableMeta, relation, row);
				((DBDataAdapter)dataAdapter).getMsgBuf().append( msg );
				return null;
			}
			if( result.size()>1 ) {
				msg += "匹配到多条数据,可能数据重复或设置不正确　"+colMap.getColumnName()+ getHintRowMsg( tableMeta, relation, row);
			}
			Object[] objs = (Object[]) result.get(0);
			if( objs==null || objs.length<1 ) {
				msg += "数据匹配失败,可能数据不存在或设置不正确 "+colMap.getColumnName()+ getHintRowMsg( tableMeta, relation, row );
			}
			val = objs[0];
		} catch (DbException e1) {
			e1.printStackTrace();
			throw new BusinessException("参照查找失败! \n"+e1.getMessage()+"\n");
		}finally{	
//			jdbcSession.closeStmt();
		}
		
		if( msg.length()>0 ) {
			if (response.isAbort()) {
				throw new BusinessException(msg);
			} else if( response.isIgnore() ) {
				((DBDataAdapter)dataAdapter).getMsgBuf().append( msg );
			}
		}
		
		row.setData(colMap.getColumnName(), val);
		return val;
	}
	
	private String generalRefSql( TableRelation relation, String fld ) {
		return SqlGenHelper.generalRefSql( relation , new String[] {fld} );
	}
	
	private String getHintRowMsg(TableMeta tableMeta,TableRelation relation, DataRow row ) {
		
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append( tableMeta.getRowHintBuilder().creHint(row) );
		strBuf.append(" ");
		
		String[] cols = relation.getChildCols();
		for(int i=0; i<cols.length; i++ ) {
			if( i>0 ) {
				strBuf.append(",");
			}
					
			Object obj = row.getData( cols[i] );
			if( obj!=null ) {
				strBuf.append(" ").append( cols[i] ).append("=");										
				strBuf.append( StringHelper.toValidString( obj) );
			}
		}
		strBuf.append(" \n");
		return strBuf.toString();
	}

}
