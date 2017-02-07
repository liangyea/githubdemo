package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import nc.vo.hr.frame.util.collection.CollectionUtil;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.ListMap;
import nc.vo.hr.tools.dbtool.util.variant.VariantHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;

/**
 * Describe:HR_PUB3.5
 * 		// data row must is interface,can well extends.
 * @version 1.0	2006-4-12 15:33:55
 * @author 闫长海
 */
public class DataRow extends CircularlyAccessibleValueObject implements Serializable,Cloneable{
	private static final long serialVersionUID = -4405528307478317421L;
	
	private IDataTable table;
	private ListMap<Object> dataList;
	
//	private int	state = STATE_INSERT;
		
	public final static int STATE_DEFAULT	= 0;	// ROW STATE
	public final static int STATE_INSERT	= 2;
	public final static int STATE_DELETE	= 3;
	public final static int STATE_UPDATE	= 1;
		
	public final static int STATE_INSERT_UPDATE	= 4;	// will check is exist,if exist will updta, else will add new.
	public final static int STATE_LOG_DEL		= 5;		// LOG DEL.
    public final static int STATE_CHANGE 		= 6;
    public final static int STATE_ALL 		= 7;
    public final static int STATE_ALL_UPDATE	= 8;	// STATE_CHANGE - STATE_DELETE
	
	public DataRow(IDataTable table) {
		super();		
		
		this.table = table;
		setStatus( STATE_INSERT );
	}

	public ListMap<Object> getList() {
		if (dataList == null) {
			dataList = new ListMap<Object>();
			if( getDataTable()!=null ) {
				CollectionUtil.setListSize(dataList, table.getColCount() );
				dataList.setMap( getDataTable().getColMetaList().getList().getMap() );
			}
		}
		return dataList;
	}

	public IDataTable getDataTable() {
		return table;
	}
	
    public int getRowSize() {
    	return getList().size();
    }
    
    public Object getData(int col) {
    	return getList().get(col);
    }
    
    public Object getPrimaryKeyData() {
    	ColumnMeta[] cols = table.getTableMeta().getPrimaryKey();
    	if( cols!=null && cols[0]!=null ) {
    		return getData( cols[0].getColName() );
    	}
    	return null;
    }
    
    public Object[] getPrimaryKeyDatas() {
    	ColumnMeta[] cols = table.getTableMeta().getPrimaryKey();
    	if( cols==null  ) {
    		return null;
    	}
    	
    	List<Object> list = new ArrayList<Object>();
    	for (int i = 0; i < cols.length; i++) {
			Object obj = getData( cols[i].getColName() );
			list.add(obj);
		}
    	return list.toArray(new Object[list.size()]);
    }
    
    
	/* （非 Javadoc）
	 * @see nc.vo.pub.ValueObject#getPrimaryKey()
	 */
	public String getPrimaryKey() throws BusinessException {
		ColumnMeta[] cols = table.getTableMeta().getPrimaryKey();
    	if( cols==null || cols[0]==null ) {
    		return null;
    	}
    	return cols[0].getColName();
	}
	/* （非 Javadoc）
	 * @see nc.vo.pub.ValueObject#setPrimaryKey(java.lang.String)
	 */
	public void setPrimaryKey(String key) throws BusinessException {
		String pk = getPrimaryKey();
		
		this.setData(pk, key);
	}
	
    public Object getData(String colName) {
    	return getList().getData(colName);
    }

    public Object[] getDatas(String[] colNames) {
    	if( colNames==null ) {
    		return null;
    	}
    	List<Object> list = new ArrayList<Object>();
    	for (int i = 0; i < colNames.length; i++) {
			list.add( getData(colNames[i]));
		}
    	return list.toArray(new Object[list.size()]);
    }
    
    public void setData(int col, Object val) {
    	getList().set(col, val);
    }
    
    public void setData(String colName, Object val) {  
    	// get the index by the col and update.    	
    	getList().addByKey(colName, val);
    }
    
    public void setDatas(Object[] datas) {
    	if( datas==null ) {
    		DSLogger.debug("the setDatas params is null");
    		return;
    	}
    	
    	if( datas.length> getDataTable().getColCount() ) {
    		DSLogger.debug("the setDatas datas large the col count");
    	}    	
    	
    	getList().addAll( Arrays.asList(datas) );    	
    }
    
    public Object[] getDatas() {
    	return getList().toArray(new Object[getRowSize()] );
    }
    
    
    // data type oper.

    public BigDecimal getBigDecimal(String s)
    {
        return VariantHelper.parseBigDecimal(getData(s));
    }

    public boolean getBoolean(String s)
    {
        return VariantHelper.parseBoolean(getData(s));
    }

    public byte getByte(String s)
    {
        return VariantHelper.parseByte(getData(s));
    }

    public Date getDate(String s)
    {
        return VariantHelper.parseDate(getData(s));
    }

    public double getDouble(String s)
    {
        return VariantHelper.parseDouble(getData(s));
    }

    public float getFloat(String s)
    {
        return VariantHelper.parseFloat(getData(s));
    }

    public int getInt(String s)
    {
        return VariantHelper.parseInt(getData(s));
    }

    public long getLong(String s)
    {
        return VariantHelper.parseLong(getData(s));
    }

    public short getShort(String s)
    {
        return VariantHelper.parseShort(getData(s));
    }

    public int getState()
    {
        return getStatus();
    }

	public void setState(int state) {
		this.setStatus(state);
	}
	
    public String getString(String s)
    {
        return VariantHelper.parseString(getData(s));
    }

    public boolean isNull(String s)
    {
        return getData(s) == null;
    }
    
    public void setBigDecimal(String s, BigDecimal bigdecimal)
    {
        setData(s, bigdecimal);
    }

    public void setBoolean(String s, boolean flag)
    {
        setData(s, VariantHelper.toObject(flag));
    }

    public void setByte(String s, byte byte0)
    {
        setData(s, VariantHelper.toObject(byte0));
    }

    public void setDate(String s, Date date)
    {
        setData(s, VariantHelper.toObject(date));
    }

    public void setDouble(String s, double d1)
    {
        setData(s, VariantHelper.toObject(d1));
    }

    public void setFloat(String s, float f)
    {
        setData(s, VariantHelper.toObject(f));
    }

    public void setInt(String s, int i)
    {
        setData(s, VariantHelper.toObject(i));
    }

    public void setLong(String s, long l)
    {
        setData(s, VariantHelper.toObject(l));
    }

    public void setNull(String s)
    {
        setData(s, null);
    }

    public void setShort(String s, short word0)
    {
        setData(s, VariantHelper.toObject(word0));
    }

    public void setString(String s, String s1)
    {
        setData(s, s1);
    }
    
    // child /parend row oper.
    public DataRow[] getChildRows(String relationName) {    	
    	return null;
    }

    public DataRow getParentRow(String relationName) {
    	return null;
    }

	public String toString() {
		return getList().toString();
	}

	/* （非 Javadoc）
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
	 */
	public String[] getAttributeNames() {
		
		return null;
	}

	/* （非 Javadoc）
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
	 */
	public Object getAttributeValue(String attributeName) {		
		return getData(attributeName);
	}

	/* （非 Javadoc）
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String, java.lang.Object)
	 */
	public void setAttributeValue(String name, Object value) {
		setData(name,value);
	}

	/* （非 Javadoc）
	 * @see nc.vo.pub.ValueObject#getEntityName()
	 */
	public String getEntityName() {
		return getDataTable().getTableName();
	}

	/* （非 Javadoc）
	 * @see nc.vo.pub.ValueObject#validate()
	 */
	public void validate() throws ValidationException {
		// TODO auto gen
		
	}
        
	public boolean isAllNull() {
		boolean isAllNull = true;
		for (int i = 0; i < getRowSize(); i++) {
			if( getData(i)!=null ) {
				isAllNull = false;
				break;
			}
		}
		
		return isAllNull;
	}
}
