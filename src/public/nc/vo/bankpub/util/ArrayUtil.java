package nc.vo.bankpub.util;

public class ArrayUtil {
	public static Object[] addToArr(Object obj, Object[] objs) {
		return addToArr(obj, objs, null);
	}
	public static Object[] addToArr(Object obj, Object[] objs, Class c) {
	    if (obj == null)
	        return objs;
	    if (objs == null) {
	        objs = (Object[]) java.lang.reflect.Array.newInstance(obj.getClass(), 1);
	        objs[0] = obj;
	    } else {
	        Object[] tmpObjs =
	            (Object[]) java.lang.reflect.Array.newInstance(c == null ? obj.getClass() : c, objs.length + 1);
	        System.arraycopy(objs, 0, tmpObjs, 0, objs.length);
	        tmpObjs[objs.length] = obj;
	        objs = tmpObjs;
	    }
		return objs;
	}
	public static Object[] delFromArr(int index, Object[] objs) {
		return delFromArr(index, objs, null);
	}
	public static Object[] delFromArr(Object obj, Object[] objs, Class c) {
		return delFromArr(indexOf(obj, objs), objs, c);		
	}
	public static Object[] delFromArr(int index, Object[] objs, Class c) {
	    if (objs == null) return null;
	    if (index < 0 || index >= objs.length) return objs;
	    Object[] tmpObjs =
	        (Object[]) java.lang.reflect.Array.newInstance(
	        	c == null ? objs[0].getClass() : c,
	            objs.length - 1);
	    System.arraycopy(objs, 0, tmpObjs, 0, index);
	    System.arraycopy(objs, index + 1, tmpObjs, index, objs.length - index - 1);
	    objs = tmpObjs;

	    return objs;
	}
	public static Object[] insert(Object[] targets, Object obj, int index) {
		return insert(targets, obj, index, null);
	}
	public static Object[] insert(Object[] targets, Object obj, int index, Class c) {
	    Object[] tmpAry = null;
	    if (targets == null || targets.length == 0) {
	        tmpAry = (Object[]) java.lang.reflect.Array.newInstance(obj.getClass(), 1);
	        tmpAry[0] = obj;
	        return tmpAry;
	    }

	    tmpAry =
	        (Object[]) java.lang.reflect.Array.newInstance(
	        	c == null ? obj.getClass() : c,
	            targets.length + 1);
	    System.arraycopy(targets, 0, tmpAry, 0, index - 0);
	    System.arraycopy(targets, index, tmpAry, index + 1, targets.length - index);
	    tmpAry[index] = obj;
	    return tmpAry;
	}
	public static Object[] replaceToArr(Object obj, Object[] objs, int index) {
		if (objs == null || objs.length <= index || index < 0) return null;
		objs[index] = obj;
		return objs;
	}
	public static Object[] arrayCat(Object[] a1, Object[] a2) {
		return arrayCat(a1, a2, null);
	}
	public static Object[] arrayCat(Object[] a1, Object[] a2, Class c1) {
        if (a1 == null || a1.length == 0)
            return a2;
        else if (a2 == null || a2.length == 0)
            return a1;
        final Class c = c1 == null ? a1[0].getClass() : c1;
        final Object[] os = (Object[]) java.lang.reflect.Array.newInstance(c,
                a1.length + a2.length);
        System.arraycopy(a1, 0, os, 0, a1.length);
        System.arraycopy(a2, 0, os, a1.length, a2.length);
        return os;
	}
	public static int indexOf(Object subObj, Object[] superObjs) {
	    if (subObj == null) {
	        return -1;
	    }
	    if (subObj != null && superObjs == null) {
	        return -1;
	    }
	    for (int j = 0; j < superObjs.length; j++) {
	        if (subObj.equals(superObjs[j])) {
		        return j;
	        }
	    }
	    return -1;
	}
	
	public static String toString(Object[] objs) {
		return toString(objs, null);
	}
	public static String toString(Object[] objs, String delimiter) {
	    if (delimiter == null)
	        delimiter = ",";
	    String str = "";
	    if (objs != null) {
	        for (int i = 0; i < objs.length; i++) {
	            str += objs[i];
	            str += delimiter;
	        }
	    }
	    if (str.length() > 0) {
	        str = str.substring(0, str.length() - delimiter.length());
	    }
	    if (str.length() == 0) {
	        str = null;
	    }
	    return str;
	}
	public static String toString(int[] objs, String delimiter) {
	    if (delimiter == null)
	        delimiter = ",";
	    String str = "";
	    if (objs != null) {
	        for (int i = 0; i < objs.length; i++) {
	            str += objs[i];
	            str += delimiter;
	        }
	    }
	    if (str.length() > 0) {
	        str = str.substring(0, str.length() - delimiter.length());
	    }
	    if (str.length() == 0) {
	        str = null;
	    }
	    return str;
	}
}
