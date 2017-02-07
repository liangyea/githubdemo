package nc.vo.hr.tools.dbtool.ds.impl;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.ColumnMeta;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IRowHintBuilder;
import nc.vo.hr.tools.dbtool.util.StringHelper;

/**
 * Describe:HR_PUB3.5
 * 		cre by the primary key
 * @version 1.0	2006-4-2411:03:11
 * @author „∆≥§∫£
 */
public class DefaultRowHintBuilder implements IRowHintBuilder,Serializable {

	public DefaultRowHintBuilder() {
		super();		
	}

	public String creHint(DataRow row) {
		ColumnMeta[] cols = row.getDataTable().getTableMeta().getPrimaryKey();
		if( cols==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("the dataTable:"+row.getDataTable().getTableName() +" 's primary key is null");
			return "";
		}
		
		StringBuffer strBuf = new StringBuffer();
		for(int i=0; i<cols.length; i++ ) {
			if( i>0 ) {
				strBuf.append(",");
			}
					
			Object obj = row.getData(cols[i].getColName());
			if( obj!=null ) {
				strBuf.append(" ").append(cols[i].getColName() ).append("=");										
				strBuf.append( StringHelper.toValidString( obj) );
			}
		}
		
		return strBuf.toString();
	}

}
