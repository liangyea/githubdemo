/*
 * Created on 2005-7-8
 * 
 */
package nc.vo.hr.tools.dbtool.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Dictionary;

/**
 * Describe:printTemplate
 * 		
 * @version 1.0	2005-7-8 10:21:25
 * @author „∆≥§∫£
 */
public class ComparatorUtil {

	
    public static int compare(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            return obj != null || obj1 != null ? obj != null ? 1 : -1 : 0;
        if((obj instanceof Date) && (obj1 instanceof Date))
        {
            long l = ((Date)obj).getTime();
            long l1 = ((Date)obj1).getTime();
            return l != l1 ? l <= l1 ? -1 : 1 : 0;
        }
        if((obj instanceof Number) && !(obj1 instanceof Number))
            try
            {
                obj1 = Double.valueOf(obj1.toString());
            }
            catch(Throwable throwable) { }
        if((obj1 instanceof Number) && !(obj instanceof Number))
            try
            {
                obj = Double.valueOf(obj.toString());
            }
            catch(Throwable throwable1) { }
        if((obj instanceof Number) && (obj1 instanceof Number))
        {
            double d = ((Number)obj).doubleValue() - ((Number)obj1).doubleValue();
            return d != 0.0D ? d <= 0.0D ? -1 : 1 : 0;
        }
        
        if( obj instanceof Object[] ) {
        	obj = nc.vo.hr.tools.dbtool.util.StringHelper.getObjArrayString((Object[])obj);
        }
        
        if( obj1 instanceof Object[] ) {
        	obj1 = nc.vo.hr.tools.dbtool.util.StringHelper.getObjArrayString((Object[])obj1);
        }
        return obj.toString().compareTo(obj1.toString());

    }

    public static int compare(Comparator comparator, Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            return obj != null || obj1 != null ? obj != null ? 1 : -1 : 0;
        if((obj instanceof Date) && (obj1 instanceof Date))
        {
            long l = ((Date)obj).getTime();
            long l1 = ((Date)obj1).getTime();
            return l != l1 ? l <= l1 ? -1 : 1 : 0;
        }
        if((obj instanceof Number) && !(obj1 instanceof Number))
            try
            {
                obj1 = Double.valueOf(obj1.toString());
            }
            catch(Throwable throwable) { }
        if((obj1 instanceof Number) && !(obj instanceof Number))
            try
            {
                obj = Double.valueOf(obj.toString());
            }
            catch(Throwable throwable1) { }
        if((obj instanceof Number) && (obj1 instanceof Number))
        {
            double d = ((Number)obj).doubleValue() - ((Number)obj1).doubleValue();
            return d != 0.0D ? d <= 0.0D ? -1 : 1 : 0;
        }
        return comparator.compare(obj, obj1);

    }

    public static boolean equals(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null) {
            return obj == null && obj1 == null;
        }

        return obj.equals(obj1);
    }
    
    public static boolean isValInArrays( String val, String[] vals) {
        if( val==null || vals==null ) {
            return false;
        }
        
        for (int i = 0; i < vals.length; i++) {
            if( val.equals(vals[i])) {
                return true;
            }
        }
        return false;
    }
    
	public static boolean isEmpty(Object value)
	{
		if (value == null)
			return true;
		if ((value instanceof String)
				&& (((String) value).trim().length() <= 0))
			return true;
		if ((value instanceof Object[]) && (((Object[]) value).length <= 0))
			return true;
		if ((value instanceof Collection) && ((Collection) value).size() <= 0)
			return true;
		if ((value instanceof Dictionary) && ((Dictionary) value).size() <= 0)
			return true;
		return false;
	}
}
