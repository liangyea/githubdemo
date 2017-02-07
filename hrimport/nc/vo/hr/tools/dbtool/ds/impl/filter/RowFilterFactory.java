package nc.vo.hr.tools.dbtool.ds.impl.filter;

import java.util.HashMap;
import java.util.Map;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.IRowFilter;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1713:33:24
 * @author „∆≥§∫£
 */
public class RowFilterFactory {


    private static Map<String,IRowFilter> map = new HashMap<String,IRowFilter>();

    // cre the filter for the visible.
    public static IRowFilter createRowFilter( int type )
    {
    	if( type== DataRow.STATE_ALL ) {	//??
    		return null;
    	}
    	
    	IRowFilter filter;
        if((filter = map.get(Integer.toString(type))) == null) {
        	filter = new RowStatusFilter(type);
        	map.put(Integer.toString(type), filter);
        }
        
        return filter;
    }
    
    public static IRowFilter createRowFilter( String type ) {
    	
    	return null;
    }
}
