package nc.vo.bankpub.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class SqlUtil {
	/** 数据库查询时，in条件语句中最大的元素个数 */
	public static final int IN_EXPRESS_MAX_COUNT = 500;


	public static String getOrSqlBy(String[] items, String fieldName,
			String tableName) {
		String orSql = "";
		for (int i = 0; i < items.length; i++) {
			// 如果关联列没有表名,加上表名
			if (fieldName.indexOf(".") < 0) {
				fieldName = tableName + "." + fieldName;
			}

			orSql += fieldName + " = '" + items[i] + "' ";
			if (i != items.length - 1) {
				orSql += " or ";
			}
		}
		if (orSql.trim().length() > 0) {
			orSql = " ( " + orSql + " ) ";
		}
		return orSql;
	}
	
	/** 使用待定参数 */
	public static String getOrParaSqlBy(String[] items, String fieldName,
			String tableName) {
		String orSql = "";
		for (int i = 0; i < items.length; i++) {
			// 如果关联列没有表名,加上表名
			if (fieldName.indexOf(".") < 0) {
				fieldName = tableName + "." + fieldName;
			}

			orSql += fieldName + " = ? ";
			if (i != items.length - 1) {
				orSql += " or ";
			}
		}
		if (orSql.trim().length() > 0) {
			orSql = " ( " + orSql + " ) ";
		}
		return orSql;
	}


	
	
	
	
	/**
	 * 取得循环次数。
	 * 
	 * 总长度为length，每次前进的元素个数是unitageLength，总共需要前进多少次 能遍历length个元素
	 * 
	 * 这个方法需要和getSqlInExpr结合使用！！！
	 */
	public static int getCycleCount(int length, int unitageLength) {
		if (unitageLength < 1) {
			unitageLength = IN_EXPRESS_MAX_COUNT;
		}
		return (length % unitageLength == 0 ? length / unitageLength : (length
				/ unitageLength + 1));
	}

	/**
	 * 取得SQL查询的in条件语句。
	 * 
	 * 由于在查询时，in语句中的最大元素个数存在限制，所以当objs的长度超过
	 * 该限制时，需要循环查询，forIndex既是循环查询时的计数器，unitageLength 则是in语句的最大元素个数，目前默认为500。
	 * 
	 * 
	 * 这个方法需要和getCycleCount结合使用！！！
	 * 
	 */
	public static String getSqlInExpr(Object[] objs, int forIndex,
			int unitageLength) {
		if (objs == null || objs.length == 0)
			// 不能返回空，否则在数据库查询时，语法出错
			return "()";
		if (unitageLength < 1)
			// 默认500
			unitageLength = IN_EXPRESS_MAX_COUNT;

		int length = objs.length;

		String inStr = null;
		if ((forIndex + 1) * unitageLength < length) {
			inStr = getInExpressByArray(objs, forIndex * unitageLength, (forIndex + 1)
					* unitageLength);
		} else {
			inStr = getInExpressByArray(objs, forIndex * unitageLength, length);
		}

		return inStr;
	}

	/**
	 * 取得SQL查询的in条件语句。
	 * 
	 * 由于在查询时，in语句中的最大元素个数存在限制，所以当vecObj的长度超过
	 * 该限制时，需要循环查询，forIndex既是循环查询时的计数器，unitageLength 则是in语句的最大元素个数，目前默认为500。
	 * 
	 * 这个方法需要和getCycleCount结合使用！！！
	 */
	public static String getSqlInExpr(Vector vecObj, int forIndex,
			int unitageLength) {
		if (vecObj == null || vecObj.size() == 0)
			// 不能返回空，否则在数据库查询时，语法出错
			return "()";
		if (unitageLength < 1)
			// 默认500
			unitageLength = IN_EXPRESS_MAX_COUNT;

		int length = vecObj.size();

		String inStr = null;
		if ((forIndex + 1) * unitageLength < length) {
			inStr = getInExpressByArray(vecObj, forIndex * unitageLength,
					(forIndex + 1) * unitageLength);
		} else {
			inStr = getInExpressByArray(vecObj, forIndex * unitageLength, length);
		}

		return inStr;
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 */
	public static String getInExpressByArray(int[] items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, false);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(int[] items, int beginIndex, int endIndex,
			boolean isWithInvertedComma) {
		if (items == null || items.length == 0 || beginIndex < 0
				|| endIndex > items.length) {
			throw new IllegalArgumentException();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		if (isWithInvertedComma) {
			for (int i = beginIndex; i < endIndex; i++) {
				buffer.append(" '").append(items[i]).append("', ");
			}
		} else {
			for (int i = beginIndex; i < endIndex; i++) {
				buffer.append(items[i]).append(", ");
			}
		}
		buffer.setCharAt(buffer.length() - 2, ')');
		return buffer.toString();
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(int[] items, boolean isWithInvertedComma) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length, isWithInvertedComma);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 */
	public static String getInExpressByArray(Object[] items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 */
	public static String getInExpressByArray(Object[] items, int beginIndex,
			int endIndex) {
		return getInExpressByArray(items, beginIndex, endIndex, true);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(Object[] items, int beginIndex,
			int endIndex, boolean isWithInvertedComma) {
		if (items == null || items.length == 0 || beginIndex < 0
				|| endIndex > items.length) {
			throw new IllegalArgumentException();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		if (isWithInvertedComma) {
			for (int i = beginIndex; i < endIndex; i++) {
				buffer.append(" '").append(items[i]).append("', ");
			}
		} else {
			for (int i = beginIndex; i < endIndex; i++) {
				buffer.append(items[i]).append(", ");
			}
		}
		buffer.setCharAt(buffer.length() - 2, ')');
		return buffer.toString();
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(Object[] items, boolean isWithInvertedComma) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length, isWithInvertedComma);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 */
	public static String getInExpressByArray(List items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.size());
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 */
	public static String getInExpressByArray(List items, int beginIndex, int endIndex) {
		return getInExpressByArray(items, beginIndex, endIndex, true);
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(List items, int beginIndex, int endIndex,
			boolean isWithInvertedComma) {
		// if (items == null
		// //|| items.size() == 0
		// || beginIndex < 0
		// || endIndex > items.size()) {
		// throw new IllegalArgumentException();
		// }
		if (items != null && items.size() > 0) {
			if (beginIndex < 0)
				beginIndex = 0;
			if (endIndex > items.size())
				endIndex = items.size();
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			if (isWithInvertedComma) {
				for (int i = beginIndex; i < endIndex; i++) {
					buffer.append(" '").append(items.get(i)).append("', ");
				}
			} else {
				for (int i = beginIndex; i < endIndex; i++) {
					buffer.append(items.get(i)).append(", ");
				}
			}
			buffer.setCharAt(buffer.length() - 2, ')');
			return buffer.toString();
		} else {
			// 返回空将会出错
			return "('')";
		}
	}

	/**
	 * 将数组items转化成"('','items[i]','')"的字符串。
	 * 参数isWithInvertedComma表示在()中的字符串的元素是否携带引号。为true，如上格式，为false，则如(1,2,3)
	 */
	public static String getInExpressByArray(List items, boolean isWithInvertedComma) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.size(), isWithInvertedComma);
	}


	public static String getTableNameBeforeDot(String sql) {
		if (sql == null)
			return null;
		// 取得.前面的第一个分隔符位置
		int index = sql.lastIndexOf(' ');
		if (index > -1) {
			sql = sql.substring(index).trim();
			index = sql.lastIndexOf('=');
			if (index > -1) {
				sql = sql.substring(index + 1);
			} else {
				index = sql.lastIndexOf('>');
				if (index > -1) {
					sql = sql.substring(index + 1);
				} else {
					index = sql.lastIndexOf('<');
					if (index > -1) {
						sql = sql.substring(index + 1);
					} else {
						index = sql.lastIndexOf('+');
						if (index > -1) {
							sql = sql.substring(index + 1);
						} else {
							index = sql.lastIndexOf('-');
							if (index > -1) {
								sql = sql.substring(index + 1);
							} else {
								index = sql.lastIndexOf('(');
								if (index > -1) {
									sql = sql.substring(index + 1);
								}
							}
						}
					}
				}
			}
		}
		return sql;
	}

	public static List getAllTableNames(String sql) {
		if (sql == null)
			return null;
		int dotIndex = sql.indexOf('.');
		List list = new ArrayList();
		String str;
		String tableName;
		while (dotIndex > 0 && dotIndex <= sql.trim().length()) {
			str = sql.substring(0, dotIndex).trim();
			tableName = getTableNameBeforeDot(str);
			if (!list.contains(tableName)) {
				list.add(tableName);
			}
			sql = sql.substring(dotIndex + 1).trim();
			dotIndex = sql.indexOf('.');
		}
		return list;
	}
}
