package nc.vo.hr.tools.dbtool.ds.map;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-13 10:10:25
 * @author 闫长海
 */
public class ColumnMap implements Serializable,Cloneable{
	private static final long serialVersionUID = 8136398401505675716L;

	private String columnName;	// source Column
	
	private String  toTableName;	// target table Name, maybe is same then it is null
	private String  toColumnName;	// target column name
	
	private int 	toDataType;		// target data type,maybe sql data type.
	
	private ITranstor transtor;		// transtor the source data to target data.

	
	/**
	 * 
	 */
	public ColumnMap() {
		super();
	}
	
	public ColumnMap(String columnName, String toColName, int toDataType ) {
		super();
		
		setColumnName(columnName);
		setToColumnName(toColName);
		setToDataType(toDataType);
	}
	
	public ColumnMap(String columnName,String toTable, String toColName ) {
		super();
		
		setColumnName(columnName);
		setToTableName(toTable);
		setToColumnName(toColName);
		setToDataType(toDataType);
	}
	
	// col trans.
	public ColumnMap(String columnName, String toColumnName ) {
		super();
		
		setColumnName(columnName);
		setToColumnName(toColumnName);
	}
	
	/**
	 * the col name is equal.
	 * @param toTableName
	 * @param toDataType
	 */
	public ColumnMap(String toColName, int toDataType ) {
		super();
		
		setColumnName(toColName);
		setToColumnName(toColName);
		setToDataType(toDataType);
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getToColumnName() {
		return toColumnName;
	}

	public void setToColumnName(String toColumnName) {
		this.toColumnName = toColumnName;
	}

	public int getToDataType() {
		return toDataType;
	}

	public void setToDataType(int toDataType) {
		this.toDataType = toDataType;
	}

	public String getToTableName() {
		return toTableName;
	}

	public void setToTableName(String toTableName) {
		this.toTableName = toTableName;
	}

	public ITranstor getTranstor() {
		return transtor;
	}

	public void setTranstor(ITranstor transtor) {
		this.transtor = transtor;
	}
	
	
	/* （非 Javadoc）
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() throws CloneNotSupportedException {
		// TODO auto gen
		return super.clone();
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getColumnName() +" "+getToTableName()+" "+ getToColumnName() +"" +getToDataType(); 
	}
}
