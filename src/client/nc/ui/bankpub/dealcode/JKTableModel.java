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

	/**�����������������*/
	protected Vector m_dataVector;

	/**�б�ʶ����*/
	protected Vector m_columnIdentifiers;

	/**�����пɱ༭�Ե�Hash��*/
	protected Hashtable m_htRowEditable;

	/**�����еĿɱ༭�Ե�Hash��*/
	protected Hashtable m_htColEditable;

	/**�����������е�����*/
	private int[] m_indexes;

	/**�����������е����*/
	private int m_sortColumn = -1;

	/**���Ƶ�Ԫ��ɱ༭�Ե�ѡ��*/
	private boolean m_bAnd = false;

	/**����ķ���*/
	private boolean m_ascending = true;

	/**���������������֧�ֶ����һ������*/
	//private Vector sortingColumns = new Vector();
    private int[] array = null;

/**
 * �޲ι��캯��
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel() {
	super();
	//***��ʼ��
	m_htRowEditable = new Hashtable();
	m_htColEditable = new Hashtable();
	m_dataVector = new Vector();
	m_columnIdentifiers = new Vector();
	allocate();
	//***��ʼ��***
}
/**
 * ���캯��
 * @param data java.lang.Object[][] �������
 * @param columnNames java.lang.Object[] ����е�����
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel(Object[][] data, Object[] columnNames) {
	this(convertToVector(data), convertToVector(columnNames));
}
/**
 * ���캯��
 * @param data java.util.Vector �������
 * @param columnNames java.util.Vector ����
 * @exception
 * @see
 * @since v1.0
 */
public JKTableModel(Vector data, Vector columnNames) {
	//***��ʼ��
	m_htRowEditable = new java.util.Hashtable();
	m_htColEditable = new Hashtable();
	allocate();
	//***��ʼ��***

	//***��������
	setDataVector(data, columnNames);
	//***��������***
}
/**
 * �����
 * Ĭ��Ϊ�ɱ༭��
 * �е�����Ϊ��
 * @param columnName java.lang.Object �е�����
 * @exception
 * @see
 * @since v1.0
 */
public void addColumn(Object columnName) {
	addColumn(columnName, (Vector) null);
}
/**
 * �����
 * Ĭ��Ϊ�ɱ༭��
 * @param columnName java.lang.Object �е�����
 * @param columnData java.util.Vector �е�����
 * @exception
 * @see
 * @since v1.0
 */
public void addColumn(Object columnName, Vector columnData) {
	if (columnName == null)
		throw new IllegalArgumentException("addColumn() - null parameter");
	//����е�����
	m_columnIdentifiers.addElement(columnName);
	//�����������null��columnData
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
	//�����еĿɱ༭��Ϊ��
	setColEditable(getColumnCount() - 1, true);
	//�����¼�֪ͨ
	fireTableStructureChanged();
}
/**
 * ���һ���������ʾ������
 * Ĭ��Ϊ�ɱ༭��
 * @param rowData java.lang.Object[]
 * @exception
 * @see
 * @since v1.0
 */
public void addRow(Object[] rowData) {
	addRow(convertToVector(rowData));
}
/**
 * ��ӱ�ͷ�Ϳհ���
 * Ĭ��Ϊ���ɱ༭��
 * @param rowData java.lang.Object[] ��ͷ����
 * @param count int ��Ҫ��ӵĿհ��е�����
 * @exception
 * @see
 * @since v1.0
 * @deprecated
 */
public void addRow(Object[] columnNames, int count) {
	addRow(convertToVector(columnNames), count);
}
/**
 * ��ӿհ���
 * �հ���Ĭ��Ϊ���ɱ༭��
 * @param count int ��Ҫ��ӵĿհ��е�����
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
 * ���һ������
 * �������Ĭ��Ϊ�ɱ༭��
 * @param rowData java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public void addRow(Vector rowData) {
	//��������е�ͬ�иı����������
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
	//�����¼�֪ͨ
	newRowsAdded(new TableModelEvent(this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * ��ӱ�ͷ�Ϳհ���
 * Ĭ��Ϊ���ɱ༭��
 * @param columns Vector ���ͷ����
 * @param count int	��Ҫ��ӵĿհ��е�����
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
 * ��ʼ��indexes���飬Ϊ��������׼��
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
 * ����֮��ıȽϣ�������Ҫ������У�
 * @createDate��(2000-12-27 8:58:17)
 * @see��
 * @since��v1.0
 * @return int
 * @param row1 int
 * @param row2 int
 */
private int compare(int row1, int row2) {
	int result = compareRowsByColumn(row1, row2, getSortColumn());
	//����ȽϽ����Ϊ0������������򷵻����������򷵻ظ���
	//Ϊ0��ʾ���
	if (result != 0)
		return m_ascending ? result : -result;
	else
		return 0;
}
/**
 * ���бȽ����еĴ�С
 * @createDate��(2000-12-27 9:02:25)
 * @see��
 * @since��v1.0
 * @return int
 * @param row1 int
 * @param row2 int
 * @param column int
 */
private int compareRowsByColumn(int row1, int row2, int column) {
	Class type = getColumnClass(column);
	//����Ƿ���null
	Object o1 = getValueAt(row1, column);
	Object o2 = getValueAt(row2, column);
	//�����Ϊnull���򷵻�0
	if (o1 == null && o2 == null) {
		return 0;
	} else if (o1 == null) {
		//Ĭ��null������ֵС
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
			//Ĭ��false<true
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
 * ת�����ݴ洢��ʽ
 * ����ά����ת���ɶ�ά����
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
 * ת�����ݴ洢��ʽ
 * ��һά����ת����һά����
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
 * ���ص�Ԫ��ɱ༭�Ե�ѡ��
 * @see��
 * @since	��v1.0
 * @return boolean
 */
public boolean getAndEditable() {
	return m_bAnd;
}
/**
 * ����ָ���еĶ�������
 * �Ա�ʹ��Ĭ�ϵĻ������ͱ༭��
 * @return java.lang.Class
 * @param col int
 * @exception
 * @see
 * @since v1.0
 */
public Class getColumnClass(int col) {
	try {
		//ȡ��������ݵĵ�0��
		Vector vec = (Vector) m_dataVector.elementAt(0);
		//���ص�0�е�col�е���������
		return vec.elementAt(col).getClass();
	} catch (NullPointerException e) {
		//��������⣬�򷵻�Object
		return Object.class;
	}
}
/**
 * ��������
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
 * ����һ�е�����
 * @createDate��(2000-12-20 15:35:40)
 * @see��
 * @since��v1.0
 * @return java.lang.String[]
 * @param colIndex int ������
 */
public String[] getColumnData(int colIndex) {
	if (colIndex < getColumnCount()) {
		int size = getRowCount();
		String[] data = new String[size];
		if (getColumnClass(colIndex) == Boolean.class||getColumnClass(colIndex) == nc.vo.pub.lang.UFBoolean.class) {
			//�����Boolean��ת��
			for (int i = 0; i < size; i++) {
				Object temp = getValueAt(i, colIndex);
				if (temp == null)
					data[i] = "��";
				else {
					if (((Boolean) temp).booleanValue())
						data[i] = "��";
					else {
						data[i] = "��";
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
 * ����һ�е�����
 * @createDate��(2000-12-20 15:35:40)
 * @see��
 * @since��v1.0
 * @return java.lang.String[]
 * @param colName String ������
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
 * ����ָ���е�����
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
 * �����е�����
 * @createDate��(2000-12-29 9:22:51)
 * @see��
 * @since��v1.0
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
 * ���ر���е����ݣ����飩
 * @createDate��(2000-12-20 15:30:11)
 * @see��
 * @since��v1.0
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
 * ���ر���е����ݣ�������
 * @return java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public Vector getDataVector() {
	return m_dataVector;
}
/**
 * ��������
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
 * ���ص�ǰ�����������е��к�
 * @return int
 * @exception
 * @see
 * @since v1.0
 */
public int getSortColumn() {
	return m_sortColumn;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-4-8 14:19:31)
 * @return int[]
 */
public int[] getSortIndexes() {
	return m_indexes;
}
/**
 * ����ָ�����Ԫ�Ķ���
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
 * �жϱ��Ԫ�Ŀɱ༭��
 * @return boolean
 * @param row int ������
 * @param column int ������
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
					//Ĭ��Ϊ��
					return isRowEditable(row) || isColumnEditable(array[i]);
			}
		}

	}
		//�����������ɱ༭��
		if (getAndEditable())
			return isRowEditable(row) && isColumnEditable(column);
		else
			//Ĭ��Ϊ��
			return isRowEditable(row) || isColumnEditable(column);


}
public void setColNotEditable(int[] array){
	this.array = array;
}
/**
 * �ж��еĿɱ༭��
 * @return boolean
 * @param col int �е�����
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
 * �ж��еĿɱ༭��
 * @return boolean
 * @param row int �е�����
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
 * ��startIndex��endIndex���ڵ����Ƶ�toIndex
 * @param startIndex int ��Ҫ�ƶ�����ʼ������
 * @param endIndex int	��Ҫ�ƶ��Ľ���������
 * @param toIndex int	�ƶ���Ŀ������λ��
 * @exception
 * @see
 * @since v1.0
 */
public void moveRow(int startIndex, int endIndex, int toIndex) {

	/**�����Ч��*/
	if ((startIndex < 0) || (startIndex >= getRowCount()))
		throw new ArrayIndexOutOfBoundsException(startIndex);
	if ((endIndex < 0) || (endIndex >= getRowCount()))
		throw new ArrayIndexOutOfBoundsException(endIndex);
	if (startIndex > endIndex)
		throw new ArrayIndexOutOfBoundsException();

	/**��toIndex��startIndex��endIndex����ʱ�����ƶ���ֱ�ӷ���*/
	if ((startIndex <= toIndex) && (toIndex <= endIndex))
		return;

	boolean shift = toIndex < startIndex;

	/**����ɾ����Ҫ�ƶ����У�Ȼ���ٲ���*/
	for (int i = startIndex; i <= endIndex; i++) {
		Object aRow = m_dataVector.elementAt(i);
		m_dataVector.removeElementAt(i);
		m_dataVector.insertElementAt(aRow, toIndex);
		if (shift)
			toIndex++;
	}

	/**����һ���¼�֪ͨ*/
	fireTableDataChanged();
}
/**
 * �˷�����֤���������ӵ��о�����ͬ������
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

	/**�����������ӵ��о�����ͬ������*/
	for (int i = start; i < end; i++)
		 ((Vector) m_dataVector.elementAt(i)).setSize(getColumnCount());

	/**����һ���¼�֪ͨ*/
	fireTableChanged(event);
}
/**
 * ��������
 * @createDate��(2000-12-27 10:05:14)
 * @see��
 * @since��v1.0
 * @param array int[]
 * @param low int
 * @param high int
 */
private int[] quickSort(int[] array, int low, int high) {
	if (high - 1 < low)
		return array;
	//�����ҵ��α�
	int i = low;
	//���ҵ�����α�
	int j = high;
	//����ɵ�ֵ
	int pivot = i;
	int oldV = array[pivot];
	while (true) {
		//��i�α�������
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
		//��j�α�������
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
	//����pivot
	array[low] = array[j];
	array[j] = oldV;
	quickSort(array, low, j);
	quickSort(array, j + 1, high);
	return array;
}
/**
 * ɾ��һ������
 * @param row int ��Ҫɾ���е�����
 * @exception
 * @see
 * @since v1.0
 */
public void removeRow(int row) {

	/**ͬʱɾ�����еĿɱ༭��*/
	m_htRowEditable.remove(new Integer(m_indexes[row]));

	/**���������Ժ��еĿɱ༭��*/
	for (int i = m_indexes[row]; i < m_dataVector.size(); i++) {
		Boolean isEditable = (Boolean) m_htRowEditable.get(new Integer(i));
		if (isEditable != null) {
			m_htRowEditable.remove(new Integer(i));
			m_htRowEditable.put(new Integer(i - 1), isEditable);
		}
	}

	/**����ɾ������*/
	m_dataVector.removeElementAt(m_indexes[row]);
	allocate();
	if (getSortColumn() != -1)
		sortByColumn(getSortColumn(), m_ascending);

	/**����һ���¼�֪ͨ*/
	fireTableRowsDeleted(row, row);
}
/**
 * ���õ�Ԫ��ɱ༭�Ե�ѡ��
 * @param b boolean
 * @exception
 * @see
 * @since v1.0
 */
public void setAndEditable(boolean b) {
	m_bAnd = b;
}
/**
 * �����еĿɱ༭��
 * @param col int �к�
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
 * �����е�����
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

	/**����һ���¼�֪ͨ*/
	fireTableStructureChanged();
}
/**
 * �滻������ݣ������ı����������
 * @param data java.lang.Object[][]
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Object[][] data) {
	setDataVector(convertToVector(data));
}
/**
 * ���ñ������ݺ�����
 * @param newData java.util.Vector �������
 * @param columnNames java.util.Vector ������
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Object[][] newData, Object[] columnNames) {
	setDataVector(convertToVector(newData), convertToVector(columnNames));
}
/**
 * �滻������ݣ������ı����������
 * @param data java.util.Vector
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Vector data) {
	if (data == null)
		throw new IllegalArgumentException("setDataVector() - Null parameter");

	/**��װ�µ�����*/
	m_dataVector = data;
	/**������������*/
	m_htRowEditable.clear();
	/**���³�ʼ��*/
	m_sortColumn = -1;
	allocate();
	/**ʹ���е��о�����ͬ�ĳ��ȣ�ͬʱ����һ���¼�֪ͨ*/
	newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * ���ñ������ݺ�����
 * @param newData java.util.Vector �������
 * @param columnNames java.util.Vector ������
 * @exception
 * @see
 * @since v1.0
 */
public void setDataVector(Vector newData, Vector columnNames) {
	if (newData == null)
		throw new IllegalArgumentException("setDataVector() - Null parameter");

	/**����ɵ�����*/
	m_dataVector = new Vector(0);

	/**��װ�µ��У�����fireTableStructureChanged*/
	setColumnIdentifiers(columnNames);

	/**�����µ�����*/
	m_dataVector = newData;
	allocate();
	m_htRowEditable.clear();

	/**ʹ���е��о�����ͬ�ĳ��ȣ�ͬʱ����һ���¼�֪ͨ*/
	newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
}
/**
 * �����еĿɱ༭��
 * @param row int �е�������
 * @param isEditable boolean �ɱ༭��
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
 * ��ס��������У��Ա�ɾ����ѡ����к󰴴�����������
 * @param newSortColumn int
 * @exception
 * @see
 * @since v1.0
 */
public void setSortColumn(int newSortColumn) {
	m_sortColumn = newSortColumn;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-4-8 14:19:31)
 * @param newM_indexes int[]
 */
private void setSortIndexes(int[] newM_indexes) {
	m_indexes = newM_indexes;
}
/**
 * ���ñ��Ԫ������
 * @param objValue java.lang.Object ��Ҫ���õĶ���
 * @param row int ���Ԫ���ڵ���
 * @param column int ���Ԫ���ڵ���
 * @exception
 * @see
 * @since v1.0
 */
public void setValueAt(Object objValue, int row, int column) {
	Vector rowVector = (Vector) m_dataVector.elementAt(m_indexes[row]);
	rowVector.setElementAt(objValue, column);

	/**����һ���¼�֪ͨ*/
	fireTableChanged(new TableModelEvent(this, row, row, column));
}
/**
 * ������������
 * @createDate��(2000-12-26 20:16:55)
 * @see��
 * @since��v1.0
 * @param column int �е�����
 * @param ascending boolean	�����ǽ���
 */
public void sortByColumn(int column, boolean ascending) {
	//����ķ���
	m_ascending = ascending;
	//����������к�
	setSortColumn(column);
	allocate();
	int[] array = quickSort((int[]) m_indexes.clone(), 0, m_indexes.length);
	m_indexes = array;
}
/**
 * ��������
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

	
	//��������е�ͬ�иı����������
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
	//�����¼�֪ͨ
	newRowsAdded(new TableModelEvent(this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));

	

}
}
