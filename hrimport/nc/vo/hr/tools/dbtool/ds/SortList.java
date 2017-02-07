package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1413:34:28
 * @author „∆≥§∫£
 */
public class SortList implements Serializable {
	private static final long serialVersionUID = 3714838180429381659L;
	
	public static final int ORIGINAL = 0;
	public static final int ASC = 1; // ascend
	public static final int DESC = 2; // descend
	    
	private String[] colNames;
	private int[]	sortOrders;
	
	public SortList() {
		super();
		
	}
		
	public SortList(String colName, int sortOrder) {
		this( new String[] {colName}, new int[] {sortOrder} );
		if( colName== null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("colName is null");
		}
	}
	
	public SortList(String[] colNames, int[] sortOrders) {
		super();		
		setColNames(colNames);
		setSortOrders(sortOrders);
	}

	protected String[] getColNames() {
		return colNames;
	}

	protected void setColNames(String[] colNames) {
		if( colNames==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("colNames is null");
			return;
		}
		this.colNames = colNames;
	}

	protected int[] getSortOrders() {
		return sortOrders;
	}

	protected void setSortOrders(int[] sortOrders) {
		if( sortOrders==null ) {
			nc.vo.hr.tools.dbtool.util.DSLogger.debug("sortOrders is null");
			return;
		}
		this.sortOrders = sortOrders;
	}

	
}
