/*
 * Created on 2007-4-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nc.ui.bankpub.dealcode;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.event.TableModelEvent;

import nc.ui.pub.beans.table.ITableModel;

/**
 * @author lixiang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JKTableModel extends javax.swing.table.AbstractTableModel implements ITableModel,java.io.Serializable {

	/**表格数据向量的向量*/
	protected Vector m_dataVector;

	/**列标识向量*/
	protected Vector m_columnIdentifiers;

	/**跟踪行可编辑性的Hash表*/
	protected Hashtable m_htRowEditable;

	/**跟踪列的可编辑性的Hash表*/
	protected Hashtable m_htColEditable;

	/**跟踪排序后的行的索引*/
	private int[] m_indexes;

	/**保存所排序列的序号*/
	private int m_sortColumn = -1;

	/**控制单元格可编辑性的选择*/
	private boolean m_bAnd = false;

	/**排序的方向*/
	private boolean m_ascending = true;

	/**排序的列向量，即支持多个列一起排序*/
	//private Vector sortingColumns = new Vector();
    private int[] array = null;

/**
 * 无参构造函数
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel() {
	super();
	//***初始化
	m_htRowEditable = new Hashtable();
	m_htColEditable = new Hashtable();
	m_dataVector = new Vector();
	m_columnIdentifiers = new Vector();
	allocate();
	//***初始化***
}
/**
 * 构造函数
 * @param data java.lang.Object[][] 表格数据
 * @param columnNames java.lang.Object[] 表格列的名称
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel(Object[][] data, Object[] columnNames) {
	this(convertToVector(data), convertToVector(columnNames));
}
/**
 * 构造函数
 * @param data java.util.Vector 表格数据
 * @param columnNames java.util.Vector 列名
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel(Vector data, Vector columnNames) {
	//***初始化
	m_htRowEditable = new java.util.Hashtable();
	m_htColEditable = new Hashtable();
	allocate();
	//***初始化***

	//***设置数据
	setDataVector(data, columnNames);
	//***设置数据***
}
/**
 * 添加列
 * 默认为可编辑的
 * 列的数据为空
 * @param columnName java.lang.Object 列的名称
 * @exception
 * @see
 * @since v1.0
 */
public void addColumn(Object columnName) {
	addColumn(columnName, (Vector) null);
}
/**
 * 添加列
 * 默认为可编辑的
 * @param columnName java.lang.Object 列的名称
 * @param columnData java.util.Vector 列的数据
 * @exception
 * @see
 * @since v1.0
 */
public void addColumn(Object columnName, Vector columnData) {
	if (columnName == null)
		throw new IllegalArgumentException("addColumn() - null parameter");
	//添加列的名称
	m_columnIdentifiers.addElement(columnName);
	//在新列中填充null或columnData
	int index = 0;
	Enumeration enumeration = m_dataVector.elements();
	while (enumeration.hasMoreElements()) {
		Object value;
		if ((columnData != null) && (index < columnData.size()))
			value = columnData.elementAt(index);
		else
			value = null;
		((Vector) enumeration.nextElement()).addElement(value);
		index++;
	}
	//设置列的可编辑性为真
	setColEditable(getColumnCount() - 1, true);
	//产生事件通知
	fireTableStructureChanged();
}
/**
 * 添加一行以数组表示的数据
 * 默认为可编辑的
 * @param rowData java.lang.Object[]
 * @exception
 * @see
 * @since v1.0
 */
public void addRow(Object[] rowData) {
	addRow(convertToVector(rowData));
}
/**
 * 添加表头和空白行
 * 默认为不可编辑的
 * @param rowData java.lang.Object[] 表头数组
 * @param count int 所要添加的空白行的行数
 * @exception
 * @see
 * @since v1.0
 * @deprecated
 */
public void addRow(Object[] columnNames, int count) {
	addRow(convertToVector(columnNames), count);
}
/**
 * 添加空白行
 * 空白行默认为不可编辑的
 * @param count int 所要添加的空白行的行数
 * @exception
 * @see
 * @since v1.0
 */
public void addRow(int count) {
	for (int i = 0; i < count; i++) {
		Vector vec = new Vector();
		for (int j = 0; j < getColumnCount(); j++)
			vec.addElement("");
		addRow(vec);
//		setRowEditable(i,false);
	}

}
/**
 * 添加一行数据
 * 新添的行默认为可编辑的
 * @param rowData java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public void addRow(Vector rowData) {
	//在添加新行的同行改变排序的索引
	int[] indexesAdd = new int[m_indexes.length + 1];
	for (int i = 0; i < m_indexes.length; i++)
		indexesAdd[i] = m_indexes[i];
	indexesAdd[m_indexes.length] = m_indexes.length;
	m_indexes = indexesAdd;
	if (rowData == null) {
		rowData = new Vector(getColumnCount());
	} else {
		rowData.setSize(getColumnCount());
	}
	m_dataVector.addElement(rowData);
	setRowEditable(getRowCount() - 1, true);
	//产生事件通知
	newRowsAdded(new TableModelEvent(this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * 添加表头和空白行
 * 默认为不可编辑的
 * @param columns Vector 表格头向量
 * @param count int	所要添加的空白行的行数
 * @exception
 * @see
 * @since v1.0
 * @deprecated
 */
public void addRow(Vector columns, int count) {
	for (int i = 0; i < columns.size(); i++) {
		addColumn(columns.elementAt(i));
		setColEditable(i, false);
	}
	addRow(count);
}
/**
 * 初始化indexes数组，为排序做好准备
 * @exception
 * @see
 * @since v1.0
 */
protected void allocate() {
	m_indexes = new int[getRowCount()];
	for (int i = 0; i < m_indexes.length; i++)
		m_indexes[i] = i;
}
/**
 * 两行之间的比较（按照所要排序的列）
 * @createDate：(2000-12-27 8:58:17)
 * @see：
 * @since：v1.0
 * @return int
 * @param row1 int
 * @param row2 int
 */
private int compare(int row1, int row2) {
	int result = compareRowsByColumn(row1, row2, getSortColumn());
	//如果比较结果不为0，则如果是升序返回正数，降序返回负数
	//为0表示相等
	if (result != 0)
		return m_ascending ? result : -result;
	else
		return 0;
}
/**
 * 按列比较两行的大小
 * @createDate：(2000-12-27 9:02:25)
 * @see：
 * @since：v1.0
 * @return int
 * @param row1 int
 * @param row2 int
 * @param column int
 */
private int compareRowsByColumn(int row1, int row2, int column) {
	Class type = getColumnClass(column);
	//检查是否有null
	Object o1 = getValueAt(row1, column);
	Object o2 = getValueAt(row2, column);
	//如果都为null，则返回0
	if (o1 == null && o2 == null) {
		return 0;
	} else if (o1 == null) {
		//默认null比所有值小
		return -1;
	} else if (o2 == null) {
		return 1;
	}
	if (type.getSuperclass() == java.lang.Number.class) {
		Number n1 = (Number) o1;
		double d1 = n1.doubleValue();
		Number n2 = (Number) o2;
		double d2 = n2.doubleValue();
		if (d1 < d2)
			return -1;
		else if (d1 > d2)
			return 1;
		else
			return 0;
	} else if (type == java.util.Date.class) {
		java.util.Date d1 = (java.sql.Date) o1;
		long n1 = d1.getTime();
		java.sql.Date d2 = (java.sql.Date) o2;
		long n2 = d2.getTime();
		if (n1 < n2)
			return -1;
		else if (n1 > n2)
			return 1;
		else
			return 0;
	//} else if (type == String.class) {
		//String s1 = (String) o1;
		//String s2 = (String) o2;
		//int result = s1.compareTo(s2);
		//if (result < 0)
			//return -1;
		//else if (result > 0)
			//return 1;
		//else
			//return 0;
	} else if (type == Boolean.class) {
		Boolean bool1 = (Boolean) o1;
		boolean b1 = bool1.booleanValue();
		Boolean bool2 = (Boolean) o2;
		boolean b2 = bool2.booleanValue();
		if (b1 == b2)
			return 0;
		else if (b1)
			//默认false<true
			return 1;
		else
			return -1;
	} else {
		Object v1 = o1;
		String s1 = v1.toString();
		Object v2 = o2;
		String s2 = v2.toString();

		return nc.ui.pub.beans.util.MiscUtils.compareStringByBytes(s1,s2);
		
		//int result = s1.compareTo(s2);
		//if (result < 0)
			//return -1;
		//else if (result > 0)
			//return 1;
		//else
			//return 0;
	}
}
/**
 * 转换数据存储方式
 * 将二维数组转换成二维向量
 * @return java.util.Vector
 * @param objArray java.lang.Object[][]
 * @exception
 * @see
 * @since v1.0
 */
protected static Vector convertToVector(Object[][] objArray) {
	if (objArray == null)
		return null;
	Vector v = new Vector(objArray.length);
	for (int i = 0; i < objArray.length; i++) {
		v.addElement(convertToVector(objArray[i]));
	}
	return v;
}
/**
 * 转换数据存储方式
 * 将一维数组转换成一维向量
 * @return java.util.Vector
 * @param objArray java.lang.Object
 * @exception
 * @see
 * @since v1.0
 */
protected static Vector convertToVector(Object[] objArray) {
	if (objArray == null)
		return null;
	Vector v = new Vector(objArray.length);
	for (int i = 0; i < objArray.length; i++) {
		v.addElement(objArray[i]);
	}
	return v;
}
/**
 * 返回单元格可编辑性的选择
 * @see：
 * @since	：v1.0
 * @return boolean
 */
public boolean getAndEditable() {
	return m_bAnd;
}
/**
 * 返回指定列的对象类型
 * 以便使用默认的绘制器和编辑器
 * @return java.lang.Class
 * @param col int
 * @exception
 * @see
 * @since v1.0
 */
public Class getColumnClass(int col) {
	try {
		//取出表格数据的第0行
		Vector vec = (Vector) m_dataVector.elementAt(0);
		//返回第0行第col列的数据类型
		return vec.elementAt(col).getClass();
	} catch (NullPointerException e) {
		//如果有例外，则返回Object
		return Object.class;
	}
}
/**
 * 返回列数
 * @return int
 * @exception
 * @see
 * @since v1.0
 */

public int getColumnCount() {
	if (m_columnIdentifiers == null)
		return -1;
	else
		return m_columnIdentifiers.size();
}
/**
 * 返回一列的数据
 * @createDate：(2000-12-20 15:35:40)
 * @see：
 * @since：v1.0
 * @return java.lang.String[]
 * @param colIndex int 列索引
 */
public String[] getColumnData(int colIndex) {
	if (colIndex < getColumnCount()) {
		int size = getRowCount();
		String[] data = new String[size];
		if (getColumnClass(colIndex) == Boolean.class||getColumnClass(colIndex) == nc.vo.pub.lang.UFBoolean.class) {
			//如果是Boolean，转换
			for (int i = 0; i < size; i++) {
				Object temp = getValueAt(i, colIndex);
				if (temp == null)
					data[i] = "否";
				else {
					if (((Boolean) temp).booleanValue())
						data[i] = "是";
					else {
						data[i] = "否";
					}
				}
			}
		}
		else {
			for (int i = 0; i < size; i++) {
				Object temp = getValueAt(i, colIndex);
				if (temp == null)
					data[i] = null;
				else
					data[i] = temp.toString();
			}
		}
		return data;
	}
	else
		return null;
}
/**
 * 返回一列的数据
 * @createDate：(2000-12-20 15:35:40)
 * @see：
 * @since：v1.0
 * @return java.lang.String[]
 * @param colName String 列名称
 */
public String[] getColumnData(String columnName) {
	if (getRowCount() <= 0)
		return null;
	String[] aryColumn = getColumnNames();
	for (int i = 0; i < aryColumn.length; i++) {
		if (aryColumn[i].equals(columnName))
			return getColumnData(i);
	}
	return null;
}
/**
 * 返回指定列的名称
 * @return java.lang.String
 * @param column int
 * @exception
 * @see
 * @since v1.0
 */
public String getColumnName(int column) {
	if (m_columnIdentifiers == null || m_columnIdentifiers.size() <= column) {
		return super.getColumnName(column);
	}
	Object id = m_columnIdentifiers.elementAt(column);
	if (id == null) {
		return super.getColumnName(column);
	}
	else {
		return id.toString();
	}
}
/**
 * 返回列的名称
 * @createDate：(2000-12-29 9:22:51)
 * @see：
 * @since：v1.0
 * @return java.lang.String[]
 */
public String[] getColumnNames() {
	int count = getColumnCount();
	if (count <= 0)
		return null;
	String[] ary = new String[count];
	for (int i = 0; i < count; i++)
		ary[i] = getColumnName(i);
	return ary;
}
/**
 * 返回表格中的数据（数组）
 * @createDate：(2000-12-20 15:30:11)
 * @see：
 * @since：v1.0
 * @return java.lang.Object[][]
 */
public Object[][] getDataArray() {
	int size = getDataVector().size();
	Object[][] data = new Object[size][getColumnCount()];
	for (int i = 0; i < size; i++) {
		Vector row = (Vector) getDataVector().elementAt(i);
		for (int j = 0; j < getColumnCount(); j++) {
			data[i][j] = row.elementAt(j);
		}
	}
	return data;
}
/**
 * 返回表格中的数据（向量）
 * @return java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public Vector getDataVector() {
	return m_dataVector;
}
/**
 * 返回行数
 * @return int
 * @exception
 * @see
 * @since v1.0
 */
public int getRowCount() {
	if (m_dataVector == null)
		return 0;
	return m_dataVector.size();
}
/**
 * 返回当前所进行排序列的列号
 * @return int
 * @exception
 * @see
 * @since v1.0
 */
public int getSortColumn() {
	return m_sortColumn;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-4-8 14:19:31)
 * @return int[]
 */
public int[] getSortIndexes() {
	return m_indexes;
}
/**
 * 返回指定表格单元的对象
 * @param row int
 * @param column int
 * @exception
 * @see
 * @since v1.0
 */
public Object getValueAt(int row, int column) {
	Vector rowVector = (Vector) m_dataVector.elementAt(m_indexes[row]);
	return rowVector.elementAt(column);
}
/**
 * 判断表格单元的可编辑性
 * @return boolean
 * @param row int 行索引
 * @param column int 列索引
 * @exception
 * @see
 * @since v1.0
 */
public boolean isCellEditable(int row, int column) {
	if(array!=null && array.length!=0){
		for(int i=0;i<array.length;i++){
			if(array[i]==column){
				if (!getAndEditable())
					return isRowEditable(row) && isColumnEditable(array[i]);
				else
					//默认为非
					return isRowEditable(row) || isColumnEditable(array[i]);
			}
		}

	}
		//如果设置了与可编辑性
		if (getAndEditable())
			return isRowEditable(row) && isColumnEditable(column);
		else
			//默认为非
			return isRowEditable(row) || isColumnEditable(column);


}
public void setColNotEditable(int[] array){
	this.array = array;
}
/**
 * 判断列的可编辑性
 * @return boolean
 * @param col int 列的索引
 * @exception
 * @see
 * @since v1.0
 */
public boolean isColumnEditable(int col) {
	if (col >= 0 && col < getColumnCount()) {
		Boolean isEditable = (Boolean) m_htColEditable.get(new Integer(col));
		if (isEditable != null)
			return true;
	}
	return false;
}
/**
 * 判断行的可编辑性
 * @return boolean
 * @param row int 行的索引
 * @exception
 * @see
 * @since v1.0
 */
public boolean isRowEditable(int row) {
	if (m_indexes == null || row >= m_indexes.length)
		return false;
	Boolean isEditable = (Boolean) m_htRowEditable.get(new Integer(m_indexes[row]));
	if (isEditable != null)
		return true;
	else
		return false;
}
/**
 * 将startIndex到endIndex所在的行移到toIndex
 * @param startIndex int 所要移动的起始行索引
 * @param endIndex int	所要移动的结束行索引
 * @param toIndex int	移动的目标索引位置
 * @exception
 * @see
 * @since v1.0
 */
public void moveRow(int startIndex, int endIndex, int toIndex) {

	/**检查有效性*/
	if ((startIndex < 0) || (startIndex >= getRowCount()))
		throw new ArrayIndexOutOfBoundsException(startIndex);
	if ((endIndex < 0) || (endIndex >= getRowCount()))
		throw new ArrayIndexOutOfBoundsException(endIndex);
	if (startIndex > endIndex)
		throw new ArrayIndexOutOfBoundsException();

	/**当toIndex在startIndex和endIndex当中时，不移动，直接返回*/
	if ((startIndex <= toIndex) && (toIndex <= endIndex))
		return;

	boolean shift = toIndex < startIndex;

	/**首先删除所要移动的行，然后再插入*/
	for (int i = startIndex; i <= endIndex; i++) {
		Object aRow = m_dataVector.elementAt(i);
		m_dataVector.removeElementAt(i);
		m_dataVector.insertElementAt(aRow, toIndex);
		if (shift)
			toIndex++;
	}

	/**产生一个事件通知*/
	fireTableDataChanged();
}
/**
 * 此方法保证所有新增加的行具有相同的列数
 * @param event com.sun.java.swing.event.TableModelEvent
 * @exception
 * @see
 * @since v1.0
 */

private void newRowsAdded(TableModelEvent event) {
	int start = event.getFirstRow();
	int end = event.getLastRow();
	if (start < 0)
		start = 0;
	if (end < 0)
		end = getRowCount() - 1;

	/**让所有新增加的行具有相同的列数*/
	for (int i = start; i < end; i++)
		 ((Vector) m_dataVector.elementAt(i)).setSize(getColumnCount());

	/**产生一个事件通知*/
	fireTableChanged(event);
}
/**
 * 快速排序
 * @createDate：(2000-12-27 10:05:14)
 * @see：
 * @since：v1.0
 * @param array int[]
 * @param low int
 * @param high int
 */
private int[] quickSort(int[] array, int low, int high) {
	if (high - 1 < low)
		return array;
	//从左到右的游标
	int i = low;
	//从右到左的游标
	int j = high;
	//保存旧的值
	int pivot = i;
	int oldV = array[pivot];
	while (true) {
		//把i游标向右移
		boolean skip = false;
		do {
			i = i + 1;
			if (i >= array.length)
				break;
			int res = compare(array[i], array[pivot]);
			if (res >= 0)
				skip = false;
			else
				skip = true;
		}
		while (skip && i < j);
		//把j游标向左移
		skip = false;
		do {
			j = j - 1;
			if (j < 0)
				break;
			int res = compare(array[j], array[pivot]);
			if (res > 0)
				skip = true;
			else
				skip = false;
		}
		while (skip);
		if (i >= j)
			break;
		swap(array, i, j);
	}
	//设置pivot
	array[low] = array[j];
	array[j] = oldV;
	quickSort(array, low, j);
	quickSort(array, j + 1, high);
	return array;
}
/**
 * 删除一行数据
 * @param row int 所要删除行的索引
 * @exception
 * @see
 * @since v1.0
 */
public void removeRow(int row) {

	/**同时删除此行的可编辑性*/
	m_htRowEditable.remove(new Integer(m_indexes[row]));

	/**调整此行以后行的可编辑性*/
	for (int i = m_indexes[row]; i < m_dataVector.size(); i++) {
		Boolean isEditable = (Boolean) m_htRowEditable.get(new Integer(i));
		if (isEditable != null) {
			m_htRowEditable.remove(new Integer(i));
			m_htRowEditable.put(new Integer(i - 1), isEditable);
		}
	}

	/**真正删除此行*/
	m_dataVector.removeElementAt(m_indexes[row]);
	allocate();
	if (getSortColumn() != -1)
		sortByColumn(getSortColumn(), m_ascending);

	/**产生一个事件通知*/
	fireTableRowsDeleted(row, row);
}
/**
 * 设置单元格可编辑性的选择
 * @param b boolean
 * @exception
 * @see
 * @since v1.0
 */
public void setAndEditable(boolean b) {
	m_bAnd = b;
}
/**
 * 设置列的可编辑性
 * @param col int 列号
 * @param isEditable boolean
 * @exception
 * @see
 * @since v1.0
 */
public void setColEditable(int col, boolean isEditable) {
	if (col < getColumnCount() && col >= 0) {
		if (isEditable)
//			m_htColEditable.put(new Integer(col), new Boolean(isEditable));
			m_htColEditable.put(new Integer(col), new Boolean(isEditable));
		else
			m_htColEditable.remove(new Integer(col));

	}
}
/**
 * 设置列的名称
 * @param newIdentifiers java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public void setColumnIdentifiers(Vector newIdentifiers) {
	if (newIdentifiers != null) {
		m_columnIdentifiers = newIdentifiers;
	}
	else {
		m_columnIdentifiers = new Vector();
	}

	/**产生一个事件通知*/
	fireTableStructureChanged();
}
/**
 * 替换表格数据，并不改变其余的属性
 * @param data java.lang.Object[][]
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Object[][] data) {
	setDataVector(convertToVector(data));
}
/**
 * 设置表格的内容和列名
 * @param newData java.util.Vector 表格数据
 * @param columnNames java.util.Vector 列名称
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Object[][] newData, Object[] columnNames) {
	setDataVector(convertToVector(newData), convertToVector(columnNames));
}
/**
 * 替换表格数据，并不改变其余的属性
 * @param data java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Vector data) {
	if (data == null)
		throw new IllegalArgumentException("setDataVector() - Null parameter");

	/**安装新的数据*/
	m_dataVector = data;
	/**清除排序的索引*/
	m_htRowEditable.clear();
	/**重新初始化*/
	m_sortColumn = -1;
	allocate();
	/**使所有的行具有相同的长度，同时产生一个事件通知*/
	newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * 设置表格的内容和列名
 * @param newData java.util.Vector 表格数据
 * @param columnNames java.util.Vector 列名称
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Vector newData, Vector columnNames) {
	if (newData == null)
		throw new IllegalArgumentException("setDataVector() - Null parameter");

	/**清除旧的数据*/
	m_dataVector = new Vector(0);

	/**安装新的列，将会fireTableStructureChanged*/
	setColumnIdentifiers(columnNames);

	/**设置新的数据*/
	m_dataVector = newData;
	allocate();
	m_htRowEditable.clear();

	/**使所有的行具有相同的长度，同时产生一个事件通知*/
	newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * 设置行的可编辑性
 * @param row int 行的索引号
 * @param isEditable boolean 可编辑性
 * @exception
 * @see
 * @since v1.0
 */
public void setRowEditable(int row, boolean isEditable) {
	if (row < getRowCount() && row >= 0) {
		if (isEditable)
			m_htRowEditable.put(new Integer(m_indexes[row]), new Boolean(isEditable));
		else
			m_htRowEditable.remove(new Integer(m_indexes[row]));
	}
}
/**
 * 记住所排序的列，以便删除所选择的行后按此列重新排序
 * @param newSortColumn int
 * @exception
 * @see
 * @since v1.0
 */
public void setSortColumn(int newSortColumn) {
	m_sortColumn = newSortColumn;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-4-8 14:19:31)
 * @param newM_indexes int[]
 */
private void setSortIndexes(int[] newM_indexes) {
	m_indexes = newM_indexes;
}
/**
 * 设置表格单元的数据
 * @param objValue java.lang.Object 所要设置的对象
 * @param row int 表格单元所在的行
 * @param column int 表格单元所在的列
 * @exception
 * @see
 * @since v1.0
 */
public void setValueAt(Object objValue, int row, int column) {
	Vector rowVector = (Vector) m_dataVector.elementAt(m_indexes[row]);
	rowVector.setElementAt(objValue, column);

	/**产生一个事件通知*/
	fireTableChanged(new TableModelEvent(this, row, row, column));
}
/**
 * 按照列来排序
 * @createDate：(2000-12-26 20:16:55)
 * @see：
 * @since：v1.0
 * @param column int 列的索引
 * @param ascending boolean	升序还是降序
 */
public void sortByColumn(int column, boolean ascending) {
	//排序的方向
	m_ascending = ascending;
	//设置排序的列号
	setSortColumn(column);
	allocate();
	int[] array = quickSort((int[]) m_indexes.clone(), 0, m_indexes.length);
	m_indexes = array;
}
/**
 * 交换索引
 * @param i int
 * @param j int
 * @exception
 * @see
 * @since v1.0
 */
protected void swap(int[] array, int i, int j) {
	int temp = array[i];
	array[i] = array[j];
	array[j] = temp;
}

public void insertRow(int rownumber){
	
	
	for(int i=getRowCount();i>rownumber;i--){
		Object aRow = m_dataVector.elementAt(i-1);
		if(i==getRowCount()){
			m_dataVector.insertElementAt(aRow, i);
		}else{
			m_dataVector.setElementAt(aRow,i);
		}
		
	}
	Vector vec = new Vector();
	for (int j = 0; j < getColumnCount(); j++)
		vec.addElement("");

	
	//在添加新行的同行改变排序的索引
	int[] indexesAdd = new int[m_indexes.length + 1];
	for (int i = 0; i < m_indexes.length; i++)
		indexesAdd[i] = m_indexes[i];
	indexesAdd[m_indexes.length] = m_indexes.length;
	m_indexes = indexesAdd;
	if (vec == null) {
		vec = new Vector(getColumnCount());
	} else {
		vec.setSize(getColumnCount());
	}
	int row = getRowCount();
	m_dataVector.setElementAt(vec,rownumber);
//	m_htRowEditable.remove(new Integer(row));
	setRowEditable(rownumber, true);
	//产生事件通知
	newRowsAdded(new TableModelEvent(this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));

	

}
}
