package nc.vo.hr.tools.dbtool.ds.impl.filter;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IRowFilter;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1713:31:24
 * @author „∆≥§∫£
 */
public class ModifiedRowFilter implements IRowFilter {

	public boolean isRecordVisible(DataRow row) {
		return row.getState()==DataRow.STATE_UPDATE;
	}

}
