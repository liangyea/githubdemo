/*
 * 创建日期 2006-4-21
 */
package nc.vo.hr.tools.dbtool.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import nc.jdbc.framework.*;
import nc.jdbc.framework.crossdb.*;
import nc.jdbc.framework.type.*;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-21 16:56:16
 * @author ych
 */
public class DBUtil {

	public final static int DB2 = 0;
	public final static int ORACLE = 1;
	public final static int SQLSERVER = 2;
	public final static int SYBASE = 3;
	public final static int UNKOWNDATABASE = -1;
	
	public static int getDBType(Connection con) {
        int iDBType = -1;
        if (con instanceof CrossDBConnection)
			try {
				iDBType = ((CrossDBConnection) con).getDatabaseType();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return iDBType;
	}
	
	public static DatabaseMetaData getMetaData( Connection con) {
            try {
			return con.getMetaData();
		} catch (SQLException e) {

		}

		return null;
    }
	
	public static String getScheme(Connection con) {
        String strSche = null;
        try {
            String schema = getMetaData(con).getUserName();
            switch (getDBType(con)) {
                case SQLSERVER:
                    strSche = "dbo";
                    break;
                case ORACLE:                	
                case DB2:
                    if (schema == null || schema.length() == 0)
                        System.out.println("ORACLE数据库模式不允许为空");
                    // ORACLE需将模式名大写
                    strSche = schema.toUpperCase();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strSche;
	}

	public static ResultSet getColumnRS( Connection con,String tableName) {
		ResultSet rsColumns = null;
		
		DatabaseMetaData dbMD = getMetaData(con);
		try {
			rsColumns = dbMD.getColumns(null,getScheme(con), tableName.toUpperCase(),"%");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsColumns;
	}
	
	/**
	 * @param jdbcSession
	 * @param tableName
	 * @return
	 */
	public static ResultSet getColumnRS(DBSession jdbcSession, String tableName) {
		ResultSet rsColumns = null;
				
		try {
			DatabaseMetaData dbMD = jdbcSession.getMetaData();
			rsColumns = dbMD.getColumns(null,jdbcSession.getScheme(), tableName.toUpperCase(),"%");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rsColumns;
	}
	
	
    public static boolean isBlob(int sqltype) {
        if (sqltype == java.sql.Types.LONGVARBINARY
                || sqltype == java.sql.Types.OTHER
                || sqltype == java.sql.Types.BLOB
                || sqltype == java.sql.Types.CLOB )
            return true;

        return false;
    }
    
//    private static void setBlob(int loc, String name, Object o,
//            PreparedStatement stmt) throws java.io.IOException,
//            java.sql.SQLException {
//        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
//        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
//        oos.writeObject(o);
//        oos.flush();
//        baos.flush();
//        ((CrossDBPreparedStatement) stmt).setBlob(loc, name, baos
//                .toByteArray());
//    }
    
//    public static PreparedStatement setValueToStmt(PreparedStatement stmt, int index,
//            Object o, int sqlType, String name) throws Exception {
//        //set value to stmt
//        if (isBlob(sqlType))
//            setBlob(index + 1, name, o, stmt);
//        else if (o == null) {
//            stmt.setNull(index + 1, sqlType);
//        } else {
//            switch (sqlType) {
//            case java.sql.Types.CHAR:
//            case java.sql.Types.VARCHAR:
//            case java.sql.Types.LONGVARCHAR:
//                stmt.setString(index + 1, o.toString());
//                break;
//            case java.sql.Types.INTEGER:
//            case java.sql.Types.SMALLINT:
//            case java.sql.Types.TINYINT:
//            case java.sql.Types.BIGINT:
//                stmt.setInt(index + 1, Integer.parseInt(o.toString()));
//                break;
//            case java.sql.Types.BIT:
//                stmt.setString((index + 1), o.toString());
//                break;
//            case java.sql.Types.DOUBLE:
//            case java.sql.Types.REAL:
//                stmt.setDouble(index + 1, Double.parseDouble(o.toString()));
//                break;
//            case java.sql.Types.FLOAT:
//                stmt.setFloat(index + 1, Float.parseFloat(o.toString()));
//                break;
//            case java.sql.Types.DECIMAL:
//            case java.sql.Types.NUMERIC:
//                stmt.setBigDecimal(index + 1,
//                        o instanceof UFDouble ? ((UFDouble) o).toBigDecimal()
//                                : new UFDouble(o.toString()).toBigDecimal());
//                break;
//            case java.sql.Types.DATE:
//            case java.sql.Types.TIMESTAMP:
//                stmt.setLong(index + 1, java.sql.Timestamp.parse(o.toString()));
//                break;
//            default:
//                throw new BusinessException("Not spported sqlType\"" + sqlType
//                        + "\"!");
//            }
//        }
//        return stmt;
//    }

	public static void setStatementParameter(PreparedStatement statement,
			SQLParameter params) throws SQLException {
		if (statement == null || params == null)
			throw new IllegalArgumentException("不能传入空的SQLParameter!");
		for (int i = 0; i < params.getCountParams(); i++) {
			Object param = params.get(i);
			if (param == null)
				throw new IllegalArgumentException("SQLParameter中的参数值不能为空");
			if (param instanceof NullParamType) {
				statement.setNull(i + 1, ((NullParamType) param).getType());
			} else if (param instanceof Integer) {
				statement.setInt(i + 1, ((Integer) param).intValue());
			} else if (param instanceof Short) {
				statement.setShort(i + 1, ((Short) param).shortValue());
			} else if (param instanceof Timestamp) {
				statement.setTimestamp(i + 1, (Timestamp) param);
			} else if (param instanceof Time) {
				statement.setTime(i + 1, (Time) param);
			} else if (param instanceof String) {
				String s = (String) param;
				statement.setString(i + 1, s);
			} else if (param instanceof UFBoolean) {
				statement.setString(i + 1, ((UFBoolean) param).toString());
			} else if (param instanceof UFDate) {
				statement.setString(i + 1, ((UFDate) param).toString());
			} else if (param instanceof UFDateTime) {
				statement.setString(i + 1, ((UFDateTime) param).toString());
			} else if (param instanceof Double) {
				statement.setDouble(i + 1, ((Double) param).doubleValue());
			} else if (param instanceof UFDouble) {
				statement.setDouble(i + 1, ((UFDouble) param).doubleValue());
			} else if (param instanceof Float) {
				statement.setFloat(i + 1, ((Float) param).floatValue());
			} else if (param instanceof Long) {
				statement.setFloat(i + 1, ((Long) param).longValue());
			} else if (param instanceof Boolean) {
				statement.setBoolean(i + 1, ((Boolean) param).booleanValue());
			} else if (param instanceof java.sql.Date) {
				statement.setDate(i + 1, (java.sql.Date) param);
			}
			// 如果是BLOB
			else if (param instanceof BlobParamType) {
				statement.setBytes(i + 1, ((BlobParamType) param).getBytes());
			}
			// 如果是CLOB
			else if (param instanceof ClobParamType) {
				ClobParamType clob = (ClobParamType) param;
				statement.setCharacterStream(i + 1, clob.getReader(), clob
						.getLength());
			} else {
				statement.setObject(i + 1, param);
			}
		}
	}
	

	private static String nullifyWhere(String query, int k) {
		StringBuffer result = new StringBuffer();
		char chars[] = query.toCharArray();
		int matchCounter = 0;
		int i = 0;
		for (i = 0; i < chars.length; i++) {
			char aChar = chars[i];
			if (aChar != '=')
				continue;
			if (matchCounter == k)
				break;
			matchCounter++;
		}

		int nextQuestioMark = query.indexOf('?', i);
		result.append(query.substring(0, i)).append(" is null ").append(
				query.substring(nextQuestioMark + 1));
		return result.toString();
	}


}
