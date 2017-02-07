
package nc.vo.hr.tools.dbtool.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1713:00:44
 * @author 闫长海
 */
public final class StringHelper
{

    private StringHelper()
    {
    }

    public static final boolean isEmpty(String s)
    {
        return s == null || s.length() == 0;
    }

    public static final boolean isNotEmpty(String s)
    {
        return s != null && s.length() > 0;
    }

    private static boolean a(String s, char c)
    {
        int i = s.length();
        for(int j = 0; j < i; j++)
            if(s.charAt(j) == c)
                return false;

        return true;
    }

    public static final boolean isValueLegal(String s, String s1)
    {
        int i = s1.length();
        for(int j = 0; j < i; j++)
            if(!a(s, s1.charAt(j)))
                return false;

        return true;
    }

    public static final String[] split(String s, String s1)
    {
        int i = s.length();
        int j = s1.length();
        int k = 0;

        ArrayList<String> arraylist = new ArrayList<String>();
        int l;
        for(; k < i; k = l + j)
        {
            if((l = s.indexOf(s1, k)) < 0)
                break;
            arraylist.add(s.substring(k, l));
        }

        String as[] = new String[arraylist.size()];
        return arraylist.toArray(as);
    }

    public static final String toValidString(Object obj)
    {
        if(obj == null)
            return "";
        return obj.toString();
    }

    public static final String translate(String s, String s1, String s2)
        throws UnsupportedEncodingException
    {
        if(s != null)
            return new String(s.getBytes(s1), s2);
        return null;
    }

    public static final String translateFromISO(String s, String s1)
        throws UnsupportedEncodingException
    {
        if(s != null)
            return new String(s.getBytes("ISO8859-1"), s1);
        return null;
    }

    public static final String translateToISO(String s)
        throws UnsupportedEncodingException
    {
        if(s != null)
            return new String(s.getBytes(), "ISO8859-1");
        return null;
    }

    public static final String validate(String s)
    {
        if(s == null)
            return "";
        return s;
    }
    
    public static final String getObjArrayString(Object[] vals) {
    	if( vals==null ) {
    		return "";
    	}
    	StringBuffer str = new StringBuffer();
    	str.append("[");
    	for (int i = 0; i < vals.length; i++) {
			str.append( toValidString(vals[i]) ).append(",");
		}
    	str.setLength(str.length()-1);
    	str.append("]");
    	return str.toString();
    }
    
    /**
     * filter the string array.
     * @param sources
     * @param filters
     * @return
     */
    public static final String[] filterStrings(String[] sources, String[] filters ) {
    	if( sources==null || filters==null) {
    		return sources;
    	}
    	
    	List<Object> list = new ArrayList<Object>();
    	for (int i = 0; i < sources.length; i++) {
    		Object obj = sources[i];
    		boolean isAdd = true;
			for (int j = 0; j < filters.length; j++) {
				if( nc.vo.hr.tools.dbtool.util.ComparatorUtil.compare(obj, filters[j]) ==0 ) {
					isAdd = false;
					break;
				}
			}
			if( isAdd ) {
				list.add(obj);
			}
		}
    	
    	return list.toArray(new String[list.size()]);
    }

    // 是否存在重复
    public static void validateUnique(String[] tharray) throws java.lang.Exception {

    	String str = "";
    	if(tharray == null || tharray.length<1){
    		return;
    	}
    	for(int i=0;i<tharray.length;i++){
    		if(tharray[i]!=null && tharray[i].trim().length()>0 && str.indexOf("$@#"+tharray[i]+"$@#")!=-1){
    			//String[] arr={tharray[i]};
    			throw new Exception("存在重复 "+tharray[i]);
    		}
    		str+= "$@#"+tharray[i]+"$@#";
    	}
    }
    
}
