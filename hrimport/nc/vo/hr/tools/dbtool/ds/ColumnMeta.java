package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

import nc.vo.hr.tools.dbtool.ds.map.ITranstor;
import nc.vo.hr.tools.dbtool.util.DSLogger;

/**
 * Describe:HR_PUB3.5
 * 		data column.
 * @version 1.0	2006-4-12 16:29:05
 * @author 闫长海
 */
public class ColumnMeta implements Serializable,Cloneable {   
	private static final long serialVersionUID = -355167624596984605L;
    public static int COL_TYPE_DEFAULT = 0;
    public static int COL_TYPE_REF = 1;
    public static int COL_TYPE_FORMULA = 2;
    public static int COL_TYPE_INFO = 3;

	private String colName;
    private String localColName;
    
    // domain.
    private int 	dataType = java.sql.Types.VARCHAR;	// data type.	// java.sql.Types
    private Object defaultValue;
    private Object minValue;
    private Object maxValue;
    private int    precision;
    private int    maxLength;
    
    private boolean isVisiable;
    private int colType = COL_TYPE_DEFAULT;	// maybe calcu or ref col
    
    private Object     formatter;
//    private Object     exportFormatter;
    private Object     editMasker;
//    private Object	   ListSource;	// for combo or list or other domain data.
        
    private boolean isNotNull = false;	// 
    private boolean isPrimaryKey = false;
    private boolean isUnique=false;
    
//    private           AggDescriptor        aggDescriptor; // in datatable 
    
    private String formula;
    
    private int 	editType;
    
	private ITranstor transtor;	// for the genstor.maybe add gen pk or ts dr.
    
    //
    transient private TableMeta tableMeta;

    public IDataTable getDataTable() {
		return getTableMeta().getDataTable();
	}
    
	public ColumnMeta() {
		super();		
	}
	
	public ColumnMeta(String colName) {
		super();		
		setColName(colName);
	}
	
	public ColumnMeta(String colName,int colType) {
		super();		
		setColName(colName);
		setDataType(colType);
	}
	
	public ColumnMeta(String colName,TableMeta tableMeta ) {
		super();		
		setColName(colName);
		setTableMeta(tableMeta);
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public int getColType() {
		return colType;
	}

	public void setColType(int colType) {
		this.colType = colType;
	}



	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object getEditMasker() {
		return editMasker;
	}

	public void setEditMasker(Object editMasker) {
		this.editMasker = editMasker;
	}

    public Object getFormatter() {
		return formatter;
	}

	public void setFormatter(Object formatter) {
		this.formatter = formatter;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public boolean isNotNull() {
		return isNotNull;
	}

	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isVisiable() {
		return isVisiable;
	}

	public void setVisiable(boolean isVisiable) {
		this.isVisiable = isVisiable;
	}

	public String getLocalColName() {
		return localColName;
	}

	public void setLocalColName(String localColName) {
		this.localColName = localColName;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public Object getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}

	public Object getMinValue() {
		return minValue;
	}

	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
    
	public Object getData(int row) {
		if( getDataTable()==null ) {
			DSLogger.debug("data table is null");
			return null;
		}
		return getDataTable().getData(row, getColName() );
	}

	protected TableMeta getTableMeta() {
		return tableMeta;
	}

	protected void setTableMeta(TableMeta tableMeta) {
		this.tableMeta = tableMeta;
	}

	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String toString() {
		return getColName();
	}

	public int getEditType() {
		return editType;
	}

	public void setEditType(int editType) {
		this.editType = editType;
	}

	/**
	 * @return 返回 transtor。
	 */
	public ITranstor getTranstor() {
		return transtor;
	}
	
	/**
	 * @param transtor 要设置的 transtor。
	 */
	public void setTranstor(ITranstor transtor) {
		this.transtor = transtor;
	}
	
	public boolean isEntityCol() {
		return getColType()==COL_TYPE_DEFAULT;
	}
	
}
