package nc.vo.hr.tools.dbtool.ds.index;

import java.util.ArrayList;

import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IRowFilter;
import nc.vo.hr.tools.dbtool.ds.IRowIndex;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1913:03:23
 * @author „∆≥§∫£
 */
public class FilterIndex extends RowIndies{

	public FilterIndex(IRowIndex index) throws DSException {
		super(index);		
	}

	public void index() {
		// 
		IRowFilter filter = getTable().getRowFilter();
		if( filter==null ) {
			return;
		}
		
		 if( refRowList==null ) {
		 	refRowList = new ArrayList<DataRow>();
		 }
		refRowList.clear();
		
		int rowcount = getInnerIndex().getSize();
		for (int i = 0; i < rowcount; i++) {
			DataRow row = getInnerIndex().getRow(i);
			
			if( filter.isRecordVisible(row) ) {
				refRowList.add(row);
			}
		}		
	}



}
