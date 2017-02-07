package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.vo.hr.tools.dbtool.ds.impl.DataRowFactory;

/**
 * 
 * @author ych
 *
 */
public class DataRowList implements Serializable{
	private static final long serialVersionUID = 6858617793415319252L;
	
	private List<DataRow> list;
	
	private IDataTable table;
	
	public DataRowList(IDataTable table) {
		super();
		this.table  = table;
	}
	
	public IDataTable getDataTable() {
		return table;
	}
	
	List<DataRow> getList() {
		if (list == null) {
			list = new ArrayList<DataRow>();
		}
		return list;
	}
    
    DataRow arrayAddRow(){
    	DataRow row = DataRowFactory.buildDefaultRow( getDataTable() );
    	getList().add(row);
    	return row;
    }
    
    public DataRow addRow(){    	
    	return getDataTable().addRow();
    }
    
    DataRow arrayAddRow(Object[] objs){
    	DataRow row = DataRowFactory.buildDefaultRow( getDataTable(),objs );
    	getList().add(row);
    	return row;
    }
    
    public DataRow addRow(Object[] objs){
    	return getDataTable().addRow(objs);
    }
    
    DataRow arrayRemoveRow( int index ){
    	return getList().remove(index);
    }
    
	public boolean arrayRemoveRow(DataRow row) {
		return getList().remove(row);
	}
	
    public DataRow removeRow( int index ){
    	return getDataTable().removeRow(index);
    }
    
    DataRow arrayInsertRow( int row ){    
    	DataRow datarow = DataRowFactory.buildDefaultRow( getDataTable() );
    	getList().add(row, datarow);
    	return datarow;
    }
    
    public DataRow insertRow( int row ){    
    	return getDataTable().insertRow(row);
    }
    
    DataRow arrayGetRow(int index) {
    	return getList().get(index);
    }
    
    DataRow[] arrayGetRows() {
    	return getList().toArray(new DataRow[getList().size()]);
    }
    
    public DataRow getRow(int index) {
    	return getDataTable().getRow(index);
    }

	boolean isArrayEmpty() {
		return getList().isEmpty();
	}
	
	public boolean isEmpty() {
		return getDataTable().isEmpty();
	}

	int arraySize() {
		return getList().size();
	}	
	
	public int size() {
		return getDataTable().getRowCount();
	}

	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String toString() {
		String str="";
		for (int i = 0; i < arraySize(); i++) {
			str+= arrayGetRow(i).toString() +"\n";
		}
		return str;
	}
	
	// later will add datarow oper.
}
