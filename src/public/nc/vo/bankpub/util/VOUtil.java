package nc.vo.bankpub.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;
public class VOUtil {

	/**
	 * 从vos中取出对应于attributeName的属性的值，构造成数组返回。 参数isFilterEquals用于标志是否过滤掉相同的元素。
	 * 创建日期：(2003-11-5 17:33:25)
	 * 
	 * @author：王伊烟
	 * @return java.lang.String[]
	 * @param vo
	 *            nc.vo.pub.CircularlyAccessibleValueObject[]
	 * @param attributeName
	 *            java.lang.String
	 */
	public static Object[] getAttributeValues(
			nc.vo.pub.CircularlyAccessibleValueObject[] vos,
			String attributeName, boolean isFilterEquals, boolean isFilterNull) {
		return getAttributeValues(vos, attributeName, isFilterEquals,
				isFilterNull, null);
	}

	public static String[] getvouType(nc.vo.bankpub.vouchertype.VouTypeVO[] vos,String type){
		
		ArrayList<String> temp = new ArrayList<String>();
		
		for (int i = 0; i < vos.length; i++) {
			Object a = vos[i].getAttributeValue(type);
			if(a!=null && a!="")
			    if(!temp.contains(a)){
			    	temp.add(a.toString());
			    }
		}
		
		
		return temp.toArray(new String[0]);
		
	}
	
	
	/**
	 * 从vos中取出对应于attributeName的属性的值，构造成数组返回。 参数isFilterEquals用于标志是否过滤掉相同的元素。
	 * 创建日期：(2003-11-5 17:33:25)
	 * 
	 * @author：王伊烟
	 * @return java.lang.String[]
	 * @param vo
	 *            nc.vo.pub.CircularlyAccessibleValueObject[]
	 * @param attributeName
	 *            java.lang.String
	 */
	public static List getLstAttributeValues(
			nc.vo.pub.CircularlyAccessibleValueObject[] vos,
			String attributeName, boolean isFilterEquals, boolean isFilterNull) {
		if (vos == null || vos.length == 0) {
			return new java.util.Vector();
		}
		java.util.Vector vec = new java.util.Vector();
		Object obj = null;

		// 不过滤相同的值
		if (!isFilterEquals) {
			// 不过滤空值
			if (!isFilterNull) {
				for (int i = 0; i < vos.length; i++) {
					vec.addElement(vos[i].getAttributeValue(attributeName));
				}
			}
			// 过滤空值
			else {
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (obj != null) {
						vec.addElement(obj);
					}
				}
			}
		}
		// 过滤相同的值
		else {
			// 不过滤空值
			if (!isFilterNull) {
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (!vec.contains(obj)) {
						vec.addElement(obj);
					}
				}
			} else {
				// 过滤空值
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (obj != null) {
						if (!vec.contains(obj)) {
							vec.addElement(obj);
						}
					}
				}
			}
		}
		return vec;
	}

	/**
	 * 从vos中取出对应于attributeName的属性的值，构造成数组返回。 参数isFilterEquals用于标志是否过滤掉相同的元素。
	 * 创建日期：(2003-11-5 17:33:25)
	 * 
	 * @author：王伊烟
	 * @return java.lang.String[]
	 * @param vo
	 *            nc.vo.pub.CircularlyAccessibleValueObject[]
	 * @param attributeName
	 *            java.lang.String
	 */
	public static Object[] getAttributeValues(
			nc.vo.pub.CircularlyAccessibleValueObject[] vos,
			String attributeName, boolean isFilterEquals, boolean isFilterNull,
			Class c) {  
		List vec = getLstAttributeValues(vos, attributeName,
				isFilterEquals, isFilterNull);
		if (vec.size() > 0) {
			if (c == null) {
				c = vec.get(0) == null ? Object.class : vec.get(0).getClass();
			}
			Object[] retObjs = (Object[]) java.lang.reflect.Array.newInstance(
					c, vec.size());
			vec.toArray(retObjs);
			return retObjs;
		}
		return null;
	}

	/**
	 * 从vos中取出对应于attributeName的属性的值，构造成数组返回。 参数isFilterEquals用于标志是否过滤掉相同的元素。
	 * 创建日期：(2003-11-5 17:33:25)
	 * 
	 * @author：王伊烟
	 * @return java.lang.String[]
	 * @param vo
	 *            nc.vo.pub.CircularlyAccessibleValueObject[]
	 * @param attributeName
	 *            java.lang.String
	 */
	public static java.util.List getLstAttributeValues(
			nc.vo.pub.CircularlyAccessibleValueObject[] vos,
			String attributeName, List filters, boolean isFilterEquals,
			boolean isFilterNull) {
		if (vos == null || vos.length == 0) {
			return new java.util.Vector();
		}
		java.util.Vector vec = new java.util.Vector();
		Object obj = null;
		boolean isFilter = true;

		if (filters == null || filters.size() == 0) {
			isFilter = false;
		}

		// 不过滤相同的值
		if (!isFilterEquals) {
			// 不过滤空值
			if (!isFilterNull) {
				for (int i = 0; i < vos.length; i++) {
					if (!isFilter) {

						vec.addElement(vos[i].getAttributeValue(attributeName));
					} else {
						if (!filters.contains(vos[i]
								.getAttributeValue(attributeName))) {
							vec.addElement(vos[i]
									.getAttributeValue(attributeName));
						}

					}

				}
			}
			// 过滤空值
			else {
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (obj != null) {

						if (!isFilter) {

							vec.addElement(obj);
						} else {
							if (!filters.contains(obj)) {
								vec.addElement(obj);
							}

						}

					}
				}
			}
		}
		// 过滤相同的值
		else {
			// 不过滤空值
			if (!isFilterNull) {
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (!vec.contains(obj)) {

						if (!isFilter) {

							vec.addElement(obj);
						} else {
							if (!filters.contains(obj)) {
								vec.addElement(obj);
							}

						}
					}
				}
			} else {
				// 过滤空值
				for (int i = 0; i < vos.length; i++) {
					obj = vos[i].getAttributeValue(attributeName);
					if (obj != null) {
						if (!vec.contains(obj)) {

							if (!isFilter) {

								vec.addElement(obj);
							} else {
								if (!filters.contains(obj)) {
									vec.addElement(obj);
								}

							}
						}
					}
				}
			}
		}
		return vec;
	}
	
	public static Map map(CircularlyAccessibleValueObject[] vos, String mapField) {
		Map map = new HashMap();
		if (vos == null || mapField == null) return map;
		for (int i = 0; i < vos.length; i++) {
			map.put(vos[i].getAttributeValue(mapField), vos[i]);
		}
		return map;
	}
	
	public static Map<String, ArrayList<CircularlyAccessibleValueObject>> groupBy(
			nc.vo.pub.CircularlyAccessibleValueObject[] vos,
			String[] groupFields) {
		if (vos == null || vos.length == 0 || groupFields == null) {
			return null;
		}
		java.util.HashMap retHash = new java.util.HashMap();

		ArrayList vecTmp = null;
		for (int i = 0; i < vos.length; i++) {
			StringBuffer groupValue = new StringBuffer();
			for (int ii = 0; ii < groupFields.length; ii++) {
				groupValue.append(vos[i].getAttributeValue(groupFields[ii]));
			}

			String key = groupValue.toString();
			if (retHash.containsKey(key)) {
				vecTmp = (ArrayList) retHash.get(key);
				vecTmp.add(vos[i]);
			} else {
				vecTmp = new ArrayList();
				vecTmp.add(vos[i]);
				retHash.put(key, vecTmp);
			}
		}
		return retHash;
	}
	
//	public static 
}
