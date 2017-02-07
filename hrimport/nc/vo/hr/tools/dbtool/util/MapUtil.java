package nc.vo.hr.tools.dbtool.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1411:14:53
 * @author „∆≥§∫£
 */
public class MapUtil {
	
	/**
	 * remove the map value by value ,return the key.
	 * @param map
	 * @param val
	 * @return
	 */
	public static Object  removeValue(Map map,Object val ) {
		if( map==null || val==null ) {
			return null;
		}
		
		Iterator i = map.entrySet().iterator();
		Entry correctEntry = null;
		
		while (correctEntry == null && i.hasNext()) {
			Entry e = (Entry) i.next();
			if ( val.equals(e.getValue()) )
				correctEntry = e;
		}
		
		Object key = null;
		if (correctEntry !=null) {
			key = correctEntry.getKey();
		    i.remove();
		}
		
		return key;	
	}
}
