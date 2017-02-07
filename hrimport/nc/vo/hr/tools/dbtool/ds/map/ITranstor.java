package nc.vo.hr.tools.dbtool.ds.map;


import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.pub.BusinessException;

/**
 * 
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1714:54:07
 * @author „∆≥§∫£
 */
public interface ITranstor extends Serializable{
	
	/**
	 * 
	 * @param dataAdapter
	 * @param row
	 * @param colName
	 * @param param
	 * @param response
	 * @throws DSException
	 * @throws BusinessException
	 */
    Object transtor( IDataAdapter dataAdapter, DataRow row, ColumnMap colMap, IOParam param, ErrorResponse response)
    		throws BusinessException;
    
}
