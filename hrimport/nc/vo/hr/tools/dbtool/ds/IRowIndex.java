package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1913:15:42
 * @author „∆≥§∫£
 */
public interface IRowIndex extends Serializable {

	public DataTable getTable(); 
	
	public abstract DataRow addRow();

	public abstract DataRow addRow(Object[] objs);

	public abstract DataRow removeRow(int index);
	
	public abstract boolean removeRow(DataRow row );

	public abstract DataRow insertRow(int row);

	public abstract DataRow getRow(int index);
	public abstract DataRow[] getRows();

	public abstract boolean isEmpty();

	public abstract int getSize();
	
	public void index();

}