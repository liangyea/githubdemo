package nc.vo.hr.tools.dbtool.ds.index;

import java.io.Serializable;
import java.util.List;

import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.IRowIndex;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1913:12:22
 * @author „∆≥§∫£
 */
public abstract class RowIndies implements IRowIndex,Serializable{
	private IRowIndex innerIndex;
	
	protected transient List<DataRow>	refRowList;
	
	public DataTable getTable() {
		return getInnerIndex().getTable();
	}
	
	public RowIndies(IRowIndex index) throws DSException {
		super();		
		setInnerIndex(index);
	}

	public IRowIndex getInnerIndex() {
		return innerIndex;
	}

	public void setInnerIndex(IRowIndex innerIndex) throws DSException {
		if( innerIndex ==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("innerIndex is null");
		}
		this.innerIndex = innerIndex;
		index();
	}
	
	public DataRow addRow() {
		DataRow row = getInnerIndex().addRow();
		if( getRefRowList()!=null ) {
			getRefRowList().add(row);
		}
		return row;
	}

	public DataRow addRow(Object[] objs) {
		DataRow row = getInnerIndex().addRow(objs);
		if( getRefRowList()!=null ) {
			getRefRowList().add(row);
		}
		return row;
	}

	public DataRow removeRow(int index) {
		DataRow row =null;
		if( getRefRowList()!=null ) {
			row = getRefRowList().remove(index);
			getInnerIndex().removeRow(row);
		}else {
			row = getInnerIndex().removeRow(index);
		}

		return row;
	}

	public DataRow insertRow(int row) {
		DataRow dataRow =  null;
		if( getRefRowList()!=null ) {
			dataRow = getInnerIndex().addRow();
			getRefRowList().add(row, dataRow);
		}else {
			dataRow = getInnerIndex().insertRow(row);
		}
		return dataRow;
	}

	public DataRow getRow(int index) {
		if( getRefRowList()!=null ) {
			return getRefRowList().get(index);		
		}
		return getInnerIndex().getRow(index);
	}
	
	/* £®∑« Javadoc£©
	 * @see nc.vo.dbtool.ds.IRowIndex#getRows()
	 */
	public DataRow[] getRows() {
		if( getRefRowList()!=null ) {
			return getRefRowList().toArray(new DataRow[getRefRowList().size()]);		
		}
		return getInnerIndex().getRows();
	}

	public boolean isEmpty() {
		if( getRefRowList()!=null ) {
			return getRefRowList().isEmpty();
		}
		return getInnerIndex().isEmpty();
	}

	public int getSize() {
		if( getRefRowList()!=null ) {
			return getRefRowList().size();
		}
		return getInnerIndex().getSize();
	}

	protected List<DataRow> getRefRowList() {
/*		if( refRowList==null ) {	// for the null index.
			refRowList = new ArrayList();
		}*/
		return refRowList;
	}
	
	public boolean removeRow(DataRow row) {		
		boolean result = getInnerIndex().removeRow(row);
		if( getRefRowList()!=null ) {
			getRefRowList().remove(row);
		}
		return result;
	}
	
	public void index() {
		if( getInnerIndex()!=null ) {
			getInnerIndex().index();
		}
		
		// do yourself index.		
	}
	

	
}
