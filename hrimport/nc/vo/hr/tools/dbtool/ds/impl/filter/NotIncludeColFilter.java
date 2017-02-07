/*
 * 创建日期 2006-6-5
 */
package nc.vo.hr.tools.dbtool.ds.impl.filter;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.IColFilter;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-6-5 16:06:03
 * @author ych
 */
public class NotIncludeColFilter implements IColFilter,Serializable {

	private String[] cols;
	
	
	/**
	 * 
	 */
	public NotIncludeColFilter(String[] colNames) {
		super();

		setCols(colNames);
	}
	
	/* （非 Javadoc）
	 * @see nc.vo.dbtool.ds.IColFilter#isColVisible(nc.vo.dbtool.ds.ColumnMeta)
	 */
	public boolean isColVisible(String colName) {
		if( colName==null || cols==null) {
			return true;
		}
		for (int i = 0; i < cols.length; i++) {
			if( colName.compareToIgnoreCase(cols[i])==0 ) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @return 返回 cols。
	 */
	public String[] getCols() {
		return cols;
	}
	/**
	 * @param cols 要设置的 cols。
	 */
	public void setCols(String[] cols) {
		this.cols = cols;
	}
}
