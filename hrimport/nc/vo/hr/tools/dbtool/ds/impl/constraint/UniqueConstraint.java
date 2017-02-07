package nc.vo.hr.tools.dbtool.ds.impl.constraint;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.IRowHintBuilder;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.index.FindIndex;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.DataTableUtil;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		唯一性校验,可用于前台校验或内存中校验，只校验datatable中的数据
 * @version 1.0	2006-4-1713:14:20
 * @author 闫长海
 */
public class UniqueConstraint extends  AbstractConstraint {
		
	public UniqueConstraint(String[] cols) {
		super(cols);
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, DataRow row, ErrorResponse response) throws BusinessException {		
	}

	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IConstraint#CheckConstraint(nc.vo.dbtool.ds.IDataTable, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public void CheckConstraint(IDataAdapter dataAdapter, IDataTable table, ErrorResponse response)  throws BusinessException {
		if( cols==null ) {
			return;
		}
		
		FindIndex dataIndex = table.findDataIndex(cols);
		
		Map dataMap = dataIndex.getValMap();
		Iterator it = dataMap.keySet().iterator();
		
		StringBuffer msg = new StringBuffer();
		IRowHintBuilder rowHint = table.getTableMeta().getRowHintBuilder();
		while( it.hasNext() ) {
			List list = (List) dataMap.get( it.next() );
			if( list==null ) {
				continue;
			}
			if( list.size()>1 ) {
				for (int i = 0; i < list.size(); i++) {
					DataRow row = (DataRow) list.get(i);
					
					msg.append( rowHint.creHint(row) );
					msg.append("唯一性校验失败!");
					msg.append(" 校验数据为：" );
					msg.append( DataTableUtil.getRowHintStr( row, cols) ); 
					msg.append( "\n");
				}
				DSLogger.info(msg);
				throw new BusinessException( msg.toString() );
			}
		}		

	}
	
}
