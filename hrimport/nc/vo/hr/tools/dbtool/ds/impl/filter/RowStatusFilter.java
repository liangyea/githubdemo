package nc.vo.hr.tools.dbtool.ds.impl.filter;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IRowFilter;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-21 10:33:25
 * @author „∆≥§∫£
 */
public class RowStatusFilter implements IRowFilter {

    private int rowStatus = -1;
    
    
    /**
     * 
     */
    public RowStatusFilter(int rowStatus) {
        super();
        this.rowStatus = rowStatus;
    }
    
    public boolean isRecordVisible(DataRow row) {
    	if( getRowStatus() == DataRow.STATE_ALL ) {	// all.
    		return true;    		
    	}
    	else if( getRowStatus()== DataRow.STATE_CHANGE ) {	 // all change.
    		if( row.getState()==DataRow.STATE_INSERT || row.getState()==DataRow.STATE_UPDATE 
    				|| row.getState()==DataRow.STATE_DELETE || row.getState()==DataRow.STATE_INSERT_UPDATE ) {
    			return true;
    		}
    		return false;   			
    	}else if( getRowStatus()== DataRow.STATE_ALL_UPDATE ) {
    		if( row.getState()==DataRow.STATE_INSERT || row.getState()==DataRow.STATE_UPDATE 
    				|| row.getState()==DataRow.STATE_INSERT_UPDATE ) {
    			return true;
    		}
    		return false; 
    	}
    	
    	
        return row.getState()== getRowStatus();
    }

    /**
     * @return the rowStatus
     */
    public int getRowStatus() {
        return rowStatus;
    }

    /**
     * @param rowStatus the rowStatus to set
     */
    public void setRowStatus(int rowStatus) {
        this.rowStatus = rowStatus;
    }

}
