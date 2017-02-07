package nc.vo.hr.tools.dbtool.ds.index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.util.StringHelper;

/**
 * Describe:HR_PUB3.5
 * 		only for the data eque find.
 * @version 1.0	2006-4-1815:36:04
 * @author 闫长海
 */
public class FindIndex implements Serializable{
	// map cols objs
	private Map<String,List<DataRow>> valMap;	// vals/datarowlist

	/**
	 * 
	 */
	public FindIndex() {
		super();
	}
	
	public FindIndex(IDataTable table,String[] cols) {
		super();
		
		setTable(table,  cols);
	}

	/**
	 * @return 返回 valMap。
	 */
	public Map<String,List<DataRow>> getValMap() {
		if( valMap==null ) {
			valMap = new HashMap<String,List<DataRow>>();
		}
		return valMap;
	}

	/**
	 * @param table 要设置的 table。
	 * @param cols
	 */
	public void setTable(IDataTable table, String[] cols) {
		
		// data index.
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			DataRow row = table.getRow(i);
			Object[] objs = row.getDatas(cols);
			String key = StringHelper.getObjArrayString( objs );
			List<DataRow> rowList = getValMap().get( key );
			if( rowList==null ) {
				rowList = new ArrayList<DataRow>();
				getValMap().put(key, rowList);
			}
			rowList.add(row);
		}
		
	}

	public DataRow[] findData( Object[] vals ) {
		if( vals==null ) {
			return new DataRow[0];
		}
		List<DataRow> list = getValMap().get( StringHelper.getObjArrayString( vals ) );
		if( list==null ) {
			return new DataRow[0];
		}
		return list.toArray( new DataRow[list.size()] );
	}
}
