package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-13 10:01:38
 * @author „∆≥§∫£
 * @deprecated
 */
public class SortColumn implements Serializable{
	  public static final int ORIGINAL = 0;
	    public static final int ASC = 1;			// ascend
	    public static final int DESC = 2;		// descend
	    
	    ////////////////////////////////////////        
	    private String 	name;
	    private int 	sortOrder = ASC;
	        
	    
	    /**
	     * 
	     */
	    public SortColumn() {
	        super();
	    }
	    
	    public SortColumn(String name, int sortOrder) {
	        super();
	        this.name = name;
	        this.sortOrder = sortOrder;
	    }
	    /**
	     * @return Returns the name.
	     */
	    public String getName() {
	        return name;
	    }
	    
	    /**
	     * @param name The name to set.
	     */
	    public void setName(String name) {
	        this.name = name;
	    }
	    /**
	     * @return Returns the sortOrder.
	     */
	    public int getSortOrder() {
	        return sortOrder;
	    }
	    /**
	     * @param sortOrder The sortOrder to set.
	     */
	    public void setSortOrder(int sortOrder) {
	        this.sortOrder = sortOrder;
	    }
}
