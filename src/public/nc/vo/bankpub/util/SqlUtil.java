package nc.vo.bankpub.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class SqlUtil {
	/** ���ݿ��ѯʱ��in�������������Ԫ�ظ��� */
	public static final int IN_EXPRESS_MAX_COUNT = 500;


	public static String getOrSqlBy(String[] items, String fieldName,
			String tableName) {
		String orSql = "";
		for (int i = 0; i < items.length; i++) {
			// ���������û�б���,���ϱ���
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
	
	/** ʹ�ô������� */
	public static String getOrParaSqlBy(String[] items, String fieldName,
			String tableName) {
		String orSql = "";
		for (int i = 0; i < items.length; i++) {
			// ���������û�б���,���ϱ���
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
	 * ȡ��ѭ��������
	 * 
	 * �ܳ���Ϊlength��ÿ��ǰ����Ԫ�ظ�����unitageLength���ܹ���Ҫǰ�����ٴ� �ܱ���length��Ԫ��
	 * 
	 * ���������Ҫ��getSqlInExpr���ʹ�ã�����
	 */
	public static int getCycleCount(int length, int unitageLength) {
		if (unitageLength < 1) {
			unitageLength = IN_EXPRESS_MAX_COUNT;
		}
		return (length % unitageLength == 0 ? length / unitageLength : (length
				/ unitageLength + 1));
	}

	/**
	 * ȡ��SQL��ѯ��in������䡣
	 * 
	 * �����ڲ�ѯʱ��in����е����Ԫ�ظ����������ƣ����Ե�objs�ĳ��ȳ���
	 * ������ʱ����Ҫѭ����ѯ��forIndex����ѭ����ѯʱ�ļ�������unitageLength ����in�������Ԫ�ظ�����ĿǰĬ��Ϊ500��
	 * 
	 * 
	 * ���������Ҫ��getCycleCount���ʹ�ã�����
	 * 
	 */
	public static String getSqlInExpr(Object[] objs, int forIndex,
			int unitageLength) {
		if (objs == null || objs.length == 0)
			// ���ܷ��ؿգ����������ݿ��ѯʱ���﷨����
			return "()";
		if (unitageLength < 1)
			// Ĭ��500
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
	 * ȡ��SQL��ѯ��in������䡣
	 * 
	 * �����ڲ�ѯʱ��in����е����Ԫ�ظ����������ƣ����Ե�vecObj�ĳ��ȳ���
	 * ������ʱ����Ҫѭ����ѯ��forIndex����ѭ����ѯʱ�ļ�������unitageLength ����in�������Ԫ�ظ�����ĿǰĬ��Ϊ500��
	 * 
	 * ���������Ҫ��getCycleCount���ʹ�ã�����
	 */
	public static String getSqlInExpr(Vector vecObj, int forIndex,
			int unitageLength) {
		if (vecObj == null || vecObj.size() == 0)
			// ���ܷ��ؿգ����������ݿ��ѯʱ���﷨����
			return "()";
		if (unitageLength < 1)
			// Ĭ��500
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
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 */
	public static String getInExpressByArray(int[] items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, false);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
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
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
	 */
	public static String getInExpressByArray(int[] items, boolean isWithInvertedComma) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length, isWithInvertedComma);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 */
	public static String getInExpressByArray(Object[] items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 */
	public static String getInExpressByArray(Object[] items, int beginIndex,
			int endIndex) {
		return getInExpressByArray(items, beginIndex, endIndex, true);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
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
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
	 */
	public static String getInExpressByArray(Object[] items, boolean isWithInvertedComma) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.length, isWithInvertedComma);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 */
	public static String getInExpressByArray(List items) {
		if (items == null) {
			return null;
		}
		return getInExpressByArray(items, 0, items.size());
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 */
	public static String getInExpressByArray(List items, int beginIndex, int endIndex) {
		return getInExpressByArray(items, beginIndex, endIndex, true);
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
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
			// ���ؿս������
			return "('')";
		}
	}

	/**
	 * ������itemsת����"('','items[i]','')"���ַ�����
	 * ����isWithInvertedComma��ʾ��()�е��ַ�����Ԫ���Ƿ�Я�����š�Ϊtrue�����ϸ�ʽ��Ϊfalse������(1,2,3)
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
		// ȡ��.ǰ��ĵ�һ���ָ���λ��
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
