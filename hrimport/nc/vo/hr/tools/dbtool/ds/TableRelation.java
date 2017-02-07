package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		parent-child relation .
 * @version 1.0	2006-4-13 10:50:48
 * @author 闫长海
 */
public class TableRelation implements Serializable{
	private String name;
//	private ColumnMeta[] parentCols;
//	private ColumnMeta[] childCols;
	
	private String parentTableName;
	private String[] parentCols;
	private String childTableName;
	private String[] childCols;
		
	public TableRelation(String name) {
		super();	
		this.name = name;
	}
	
	/**
	 * 
	 */
	public TableRelation() {
		super();
	}
	
	public TableRelation(String parentTableName, String[] parentCols, String childTableName, String[] childCols ) {
		super();
		
		setParentTableName(parentTableName);
		setParentCols(parentCols);
		setChildTableName(childTableName);
		setChildCols(childCols);
	}
	
	
	// check 
	
	// parent child row oper. row filter and index.
	
	/**
	 * @return 返回 childCols。
	 */
	public String[] getChildCols() {
		return childCols;
	}
	/**
	 * @param childCols 要设置的 childCols。
	 */
	public void setChildCols(String[] childCols) {
		this.childCols = childCols;
	}
	/**
	 * @return 返回 childTableName。
	 */
	public String getChildTableName() {
		return childTableName;
	}
	/**
	 * @param childTableName 要设置的 childTableName。
	 */
	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}
	/**
	 * @return 返回 name。
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name。
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回 parentCols。
	 */
	public String[] getParentCols() {
		return parentCols;
	}
	/**
	 * @param parentCols 要设置的 parentCols。
	 */
	public void setParentCols(String[] parentCols) {
		this.parentCols = parentCols;
	}
/**
 * @return 返回 parentTableName。
 */
public String getParentTableName() {
	return parentTableName;
}
/**
 * @param parentTableName 要设置的 parentTableName。
 */
public void setParentTableName(String parentTableName) {
	this.parentTableName = parentTableName;
}
}
