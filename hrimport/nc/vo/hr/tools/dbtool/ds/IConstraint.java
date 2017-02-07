package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		checkrule
 * @version 1.0	2006-4-1313:30:15
 * @author „∆≥§∫£
 */
public interface IConstraint extends Serializable{
	
    void CheckConstraint(IDataAdapter dataAdapter, IDataTable table, ErrorResponse response) throws BusinessException ;    
    void CheckConstraint( IDataAdapter dataAdapter, DataRow row, ErrorResponse response )throws BusinessException;
}
