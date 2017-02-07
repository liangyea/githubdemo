/*
 * 创建日期 2006-4-29
 */
package nc.vo.hr.tools.dbtool.ds.impl.constraint;

import nc.vo.hr.tools.dbtool.ds.IConstraint;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-29 11:05:35
 * @author ych
 */
public abstract class AbstractConstraint implements IConstraint {
	protected String[] cols;

	/**
	 * @return 返回 cols。
	 */
	public String[] getCols() {
		return cols;
	}

	
	/**
	 * 
	 */
	public AbstractConstraint(String[] cols) {
		super();
		if( cols==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.error("the input param is null");
			throw new RuntimeException("the input param is null");
		}
		this.cols = cols;
	}
}
