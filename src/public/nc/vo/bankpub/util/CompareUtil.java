package nc.vo.bankpub.util;

public class CompareUtil {
	public static boolean equals(Object src, Object target) {
		if (src != null) return src.equals(target);
		if (target != null) return target.equals(src);
		return true;
	}
	/**
	 * 如果两个对象相等，返回0。
	 * 如果obj1大于obj2，返回正数。
	 * 如果obj1小于obj2，返回负数。
	 */
	public static int compare(Comparable obj1, Comparable obj2) {
		if (obj1 == null && obj2 == null) return 0;
		if (obj1 != null && obj2 == null) return 1;
		if (obj1 == null && obj2 != null) return -1;
		return obj1.compareTo(obj2);
	}
	/**
	 * 比较两个对象是否相等。
	 */
	public static boolean equals(Object[] obj1s, Object[] obj2s) {
		return isIncludedIn(obj1s, obj2s) && isIncludedIn(obj2s, obj1s);
	}
	 /**
	 * 判断subObj是否属于superObjs。
	 */
	public static boolean isBelongedTo(Object subObj, Object[] superObjs) {
		return ArrayUtil.indexOf(subObj, superObjs) > -1;
	}
	/**
	 * 判断日期compareDate是否处于beginDate和endDate之间：
	 * (1) 返回1，表示compareDate在endDate后；
	 * (2) 返回0，表示compareDate处于beginDate和endDate之间；
	 * (3) 返回-1，表示compareDate在beginDate前
	 */
	public static int isBtween(
	    String compareDate,
	    String beginDate,
	    String endDate) {
	    if (compareDate == null || beginDate == null || endDate == null)
	        return 0;
	    if (compareDate.compareTo(beginDate) < 0) {
	        return -1;
	    }
	    if (compareDate.compareTo(endDate) >= 0) {
	        return 1;
	    }
	    return 0;
	}
	/**
	 * 判断日期compareDate是否处于beginDate和endDate之间：
	 * (1) 返回1，表示compareDate在endDate后；
	 * (2) 返回0，表示compareDate处于beginDate和endDate之间；
	 * (3) 返回-1，表示compareDate在beginDate前
	 */
	public static int isBtween(String compareDate, nc.vo.pub.lang.UFDate beginDate, nc.vo.pub.lang.UFDate endDate) {
		return isBtween(compareDate, beginDate == null ? null : beginDate.toString(), endDate == null ? null : endDate.toString());
	}
	/**
	 * 判断日期compareDate是否处于beginDate和endDate之间：
	 * (1) 返回1，表示compareDate在endDate后；
	 * (2) 返回0，表示compareDate处于beginDate和endDate之间；
	 * (3) 返回-1，表示compareDate在beginDate前
	 */
	public static int isBtween(
	    nc.vo.pub.lang.UFDate compareDate,
	    nc.vo.pub.lang.UFDate beginDate,
	    nc.vo.pub.lang.UFDate endDate) {
	    return isBtween(
	        compareDate == null ? null : compareDate.toString(),
	        beginDate == null ? null : beginDate.toString(),
	        endDate == null ? null : endDate.toString());
	}
	/**
	 * 方法说明：判断参数part是否被whole包含，忽略大小写。用于定位时模糊匹配。
	 */
	public static boolean isIncluded(String part, String whole) {
		return isIncluded(part, whole, true);
	}
	/**
	 * 方法说明：判断参数part是否被whole包含。用于定位时模糊匹配。
	 */
	public static boolean isIncluded(
	    String part,
	    String whole,
	    boolean isIgnoreCase) {
	    if (part != null
	        && whole != null
	        && part.length() > 0
	        && part.length() <= whole.length()) {
		    //忽略大小写
	        if (isIgnoreCase) {
	            part = part.toLowerCase();
	            whole = whole.toLowerCase();
	        }
	        int pLength = part.length();
	        int wLength = whole.length();
	        int loop = wLength - pLength + 1;
	        int j;
	        for (int i = 0; i < loop; i++) {
	            if (whole.charAt(i) == part.charAt(0)) {
	                for (j = 1; j < pLength; j++) {
	                    if (part.charAt(j) != whole.charAt(i + j)) {
	                        break;
	                    }
	                }
	                if (j == pLength) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	/**
	 * 判断数组subObjs是否包含于superObjs。
	 */
	public static boolean isIncludedIn(Object[] subObjs, Object[] superObjs) {
		if (subObjs == null) {
			return true;
		}
		if (subObjs != null && superObjs == null) {
			return false;
		}
		if (subObjs.length > superObjs.length) {
			return false;
		}
		for (int i = 0; i < subObjs.length; i++) {
			if (!isBelongedTo(subObjs[i], superObjs)){
				return false;
			}		
		}
		return true;
	}

	/**
	 * 判断subObj是否属于superObjs。
	 */
	public static boolean isBelongedTo(int sub, int[] supers) {
	    for (int j = 0; j < supers.length; j++) {
	        if (sub == supers[j]) {
		        return true;
	        }
	    }
	    return false;
	}
}
