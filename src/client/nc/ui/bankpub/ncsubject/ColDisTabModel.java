package nc.ui.bankpub.ncsubject;

/**
 * TableMode 实现如下功能：
 
 	隐藏列、调整列的显示顺序
 	设置行、列、表编辑状态
 	实现多表头
 	单元格写入触发事件及管理
	 	
 * 创建日期：(2001-6-20 12:54:42)
 * @author：金冬梅
 */
 
 import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class ColDisTabModel extends nc.ui.pub.beans.table.NCTableModel {
	private String[] m_sColIdentifier = null;//表头
	private Object[][] m_oData = null;//表格内的数据

	private int[] m_iColDisplay = null;//显示列及顺序

	private boolean[] m_bColEditable = null;//列是否可以编辑
	private boolean[] m_bRowEditable = null;//行是否可以编辑
	protected boolean m_bEditable = false;//表是否可以编辑

	private String[] m_sFullColumn = null;//对于多表头情况，为全路径列标识

	private PropertyChangeSupport m_propertyChangeSupport = null;//单元格数据写入事件管理
/**
 * SFTableModel 构造子注解。
 */
public ColDisTabModel() {
	super();
	m_propertyChangeSupport = new PropertyChangeSupport(this);
	//cellAtt = new DefaultCellAttribute(numRows,columnNames.size());
	
}
/**
 * 增加行
 * 创建日期：(2001-6-22 14:30:27)
 */
public void addRow() {
	int iCol = m_sColIdentifier.length;
	if(m_oData==null || m_oData.length==0){
		m_oData = new Object[1][iCol];
	}else{
		Object[][] oData = m_oData;
		int iRow = oData.length;
		m_oData = new Object[iRow+1][iCol];
		for(int i=0;i<iRow;i++)
			for(int j=0;j<iCol;j++)
				m_oData[i][j] = oData[i][j];
	}
	fireTableDataChanged();
	fireTableRowsInserted(m_oData.length-1,m_oData.length-1);
}
/**
 * 删除行
 * 创建日期：(2001-6-22 14:30:27)
 */
public void delRow(int iDelRow) {
	if(iDelRow<0)
		return;
	int iCol = m_oData[0].length;
	int iRow = m_oData.length;
	Object[][] oData = m_oData;
	m_oData = new Object[iRow-1][iCol];
	int idx = 0;
	for(int j=0;j<iCol;j++){
		idx = 0;
		while(idx<iDelRow){
			m_oData[idx][j] = oData[idx][j];
			idx++;
		}
		idx++;
		while(idx<iRow){
			m_oData[idx-1][j] = oData[idx][j];
			idx++;
		}
	}
	fireTableDataChanged();
}
/**
 * 在此处插入方法说明。
 * 创建日期：(2001-9-6 16:38:05)
 * @return int[]
 * @param param java.beans.PropertyChangeEvent
 */
public static int[] getCellPosition(PropertyChangeEvent param) {
	String eventName=param.getPropertyName();
	int idx=eventName.indexOf(",");
	int iRow=(Integer.valueOf(eventName.substring(0,idx))).intValue();
	int iCol=(Integer.valueOf(eventName.substring(idx+1))).intValue();
	int[] iCellPosition={iRow,iCol};
	return iCellPosition;
}
public int[] getColDisplay(){
	return m_iColDisplay;
}
/**
 * 获得列类型
 */
public Class getColumnClass(int iCol) {
	//return String.class;
	try{
		if(m_oData[0][iCol] instanceof Boolean)
			return Boolean.class;
		return String.class;
	}catch(Exception e){
		return Object.class;
	}
}
/**
 * getColumnCount 方法注解。
 */
public int getColumnCount() {
	if(m_sColIdentifier == null)
		return 0;
	if(m_iColDisplay!=null)
		return m_iColDisplay.length;
	return m_sColIdentifier.length;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2001-6-20 14:50:36)
 * @return java.lang.String
 * @param column int
 */
public String getColumnName(int iCol) {
	if(m_iColDisplay==null)
		return m_sColIdentifier[iCol];
	return m_sColIdentifier[m_iColDisplay[iCol]];
}
/**
 * 获得表格数据
 * 创建日期：(2001-6-20 13:22:15)
 * @return nc.vo.arap.pub.ClientVO[]
 */
public Object[][] getData() {
	return m_oData;
}
public String[] getFullColumn(){
	return m_sFullColumn;
}
/**
 * 在此处插入方法说明。
 * 创建日期：(2001-9-6 8:53:08)
 * @return java.beans.PropertyChangeSupport
 */
public PropertyChangeSupport getPropertyChangeSupport() {
	return m_propertyChangeSupport;
}
/**
 * getRowCount 方法注解。
 */
public int getRowCount() {
	if(m_oData==null)
		return 0;
	return m_oData.length;
}
/**
 * getValueAt 方法注解。
 */
public Object getValueAt(int iRow, int iCol) {
	Object re = null;
	if(m_iColDisplay==null)
		re = m_oData[iRow][iCol];
	else
		re = m_oData[iRow][m_iColDisplay[iCol]];
	if(re==null)
		return "";
	return re;	
}
/**
 * 按指定位置插入行 可以送入插入行数据 如果不需要送入数据，送空
 * 创建日期：(2001-6-22 14:30:27)
 */
public void insertRow(int iPos,Object[] oRowData) {
	int iCol = m_sColIdentifier.length;
	if(m_oData==null || m_oData.length==0){
		m_oData = new Object[1][iCol];
		if(oRowData!=null)
			m_oData[0] = oRowData;
	}else{
		Object[][] oData = m_oData;
		int iRow = oData.length;
		m_oData = new Object[iRow+1][iCol];
		int idx;
		for(idx=0;idx<iPos;idx++){
			for(int j=0;j<iCol;j++)
				m_oData[idx][j] = oData[idx][j];
		}
		if(oRowData!=null)
			m_oData[idx] = oRowData;
		idx++;	
		while(idx<iRow+1){
			for(int j=0;j<iCol;j++)
				m_oData[idx][j] = oData[idx-1][j];
			idx++;	
		}
	}
	fireTableDataChanged();
	fireTableRowsInserted(iPos,iPos);
}
/**
 * 获得单元格是否可以编辑
 * 创建日期：(2001-6-21 13:39:11)
 * @return boolean
 */
public boolean isCellEditable(int iRow,int iCol) {
	if(!m_bEditable)
		return false;
	if(m_bEditableCell != null && m_bEditableCell.length != 0)
	{
		return m_bEditableCell[iRow][iCol];
	}
	if(m_bColEditable!=null && m_bRowEditable!=null){
		if(m_iColDisplay!=null){
			return m_bColEditable[m_iColDisplay[iCol]]&&m_bRowEditable[iRow];
		}else
			return m_bColEditable[iCol]&&m_bRowEditable[iRow];
	}else if(m_bColEditable!=null){
		if(m_iColDisplay!=null){
			return m_bColEditable[m_iColDisplay[iCol]];
		}else
			return m_bColEditable[iCol];
	}else if(m_bRowEditable!=null){
		return m_bRowEditable[iRow];
	}else{//全为空
		return m_bEditable;
	}
	
}
/**
 * 设置显示列。通过此方法可以实现隐藏列和改变列的显示顺序
 * 创建日期：(2001-6-20 13:02:12)
 * @param iDis int[]
 */
public void setColDisplay(int[] iDis) {
	m_iColDisplay = iDis;
	fireTableStructureChanged();
}
/**
 * 设置是否可以编辑
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setColEditable(boolean[] bEditable) {
	m_bColEditable = bEditable;
}
/**
 * 设置是否可以编辑
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setColEditable(int iCol,boolean b) {
	if(m_bColEditable==null){
		int iColC =  m_sColIdentifier.length;
		m_bColEditable = new boolean[iColC];
		for(int i=0;i<iColC;i++)
			m_bColEditable[i] = false;
	}
	m_bColEditable[iCol] = b;
}
/**
 * 设置显示列（在此之前会清空所有的变量，以前的设置会变为无效）
 * 创建日期：(2001-6-20 13:00:39)
 * @param o java.lang.Object[]
 */
public void setColIdentifier(String[] sColIdentifier) {
	m_iColDisplay = null;
	m_oData = null;
	m_sColIdentifier = sColIdentifier;
	fireTableStructureChanged();
}
/**
 * 设置表格数据
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setData(Object[][] oData) {
	m_oData = oData;
	fireTableDataChanged();
}
/**
 * 设置是否可以编辑
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setEditable(boolean bEditable) {
	m_bEditable = bEditable;
}
public void setFullColumn(String[] s) {
	m_sFullColumn = s;
}
/**
 * 设置是否可以编辑
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setRowEditable(boolean[] bEditable) {
	m_bRowEditable = bEditable;
}
/**
 * 设置是否可以编辑
 * 创建日期：(2001-6-20 13:01:25)
 * @param voData nc.vo.arap.pub.ClientVO[]
 */
public void setRowEditable(int iRow,boolean b) {
	if(m_bRowEditable==null){
		int iRowC = getRowCount();
		m_bRowEditable = new boolean[iRowC];
		for(int i=0;i<iRowC;i++)
			m_bRowEditable[i] = false;
	}
	m_bRowEditable[iRow] = b;
}
/**
 * 设置表格的单元格内容
 */
public void setValueAt(Object oValue, int iRow, int iCol) 
{
	
	Object oldValue = m_oData[iRow][iCol];
	if(m_iColDisplay == null)
		m_oData[iRow][iCol] = oValue;
	else
		m_oData[iRow][m_iColDisplay[iCol]] = oValue;
	m_propertyChangeSupport.firePropertyChange(iRow+","+iCol,oldValue,oValue);
}

	boolean m_bEditableCell[][] = null;
	private java.util.Vector m_vSetdata = null;

/**
 * 此处插入方法说明。
 * 创建日期：(2002-10-29 10:14:17)
 * @return java.lang.String[]
 */
public String[] getColNames() 
{
	return m_sColIdentifier;
}

/**
 * 此处插入方法说明。
 * 创建日期：(2002-7-22 18:27:06)
 * @return nc.vo.bank_cvp.compile.datastruct.HashContext
 */
public nc.vo.bank_cvp.compile.datastruct.HashContext getCtxParm() {
	return null;
}

/**
 * 此处插入方法说明。
 * 创建日期：(2002-7-22 19:45:13)
 * @return java.lang.String[][]
 */
public String[][] getElemRelation() {
	return null;
}

public Object[][] getResultData() {
	return null;
}

/**
 * 此处插入方法说明。
 * 创建日期：(2005-1-8 11:51:20)
 * @return java.util.Vector
 */
public java.util.Vector getSetdata() {
	return m_vSetdata;
}

/**
 * 此处插入方法说明。
 * 创建日期：(2003-6-11 14:17:07)
 * @param bedit boolean
 */
public void setEditableCell(boolean[][] bedit) 
{
	m_bEditableCell = bedit;
}

/**
 * 此处插入方法说明。
 * 创建日期：(2005-1-8 11:51:50)
 * @param v java.util.Vector
 */
public void setVectorData(java.util.Vector v) 
{
	m_vSetdata = v;
	Object data[][] = null;
	if(v == null || v.size() == 0)
	{
		
	}
	else
	{
		Object []oo = (Object[])v.elementAt(0);
		data = new Object[v.size()][oo.length];
	}
	setData(data);
	fireTableDataChanged();
}
}