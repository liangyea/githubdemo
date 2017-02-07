
package nc.bs.hr.tools.dbtool.ds.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.type.NullParamType;
import nc.vo.hr.tools.dbtool.ds.ColumnMeta;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.IColFilter;
import nc.vo.hr.tools.dbtool.ds.IDataTable;
import nc.vo.hr.tools.dbtool.ds.TableMeta;
import nc.vo.hr.tools.dbtool.ds.TableRelation;
import nc.vo.hr.tools.dbtool.ds.map.ColumnMap;
import nc.vo.hr.tools.dbtool.ds.map.TableMap;
import nc.vo.hr.tools.dbtool.util.StringHelper;
import nc.vo.hr.tools.dbtool.util.db.DBSession;
import nc.vo.hr.tools.dbtool.util.db.DBUtil;
import nc.vo.hr.tools.dbtool.util.variant.VariantHelper;
import nc.vo.pub.BusinessException;

/**
 * 
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-21 16:04:14
 * @author ych
 */
public class SqlGenHelper{
	// map fot the db table fld/type map.
	private static Map<String,Map<String,Integer>> cache = Collections.synchronizedMap(new WeakHashMap<String,Map<String,Integer>>());
	
    /**
     * 得到列的类型
     *
     * @param table
     * @return
     */
    static Map getColmnTypes(DBSession jdbcSession, String tableName) {
        Map result = cache.get(tableName);
        if (result == null) {
            Map<String,Integer> typeMap = new HashMap<String,Integer>();
            try {
            	ResultSet rsColumns = DBUtil.getColumnRS( jdbcSession, tableName );
            	
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME")
                            .toUpperCase();
                    int columnType = rsColumns.getShort("DATA_TYPE");
                    typeMap.put(columnName,Integer.valueOf(columnType));
                }
                cache.put( tableName, typeMap);
                result = typeMap;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据表名和列名称得到插入语句
     * 
     * @param table
     * @param names
     * @return
     */
    public static String getInsertSql(String table, String names[]) {
        StringBuffer buffer = new StringBuffer("INSERT INTO " + table + " (");
        for (int i = 0; i < names.length; i++) {
            buffer.append(names[i] + ",");
        }
        buffer.setLength(buffer.length() - 1);
        buffer.append(") VALUES (");
        for (int i = 0; i < names.length; i++) {
            buffer.append("?,");
        }
        buffer.setLength(buffer.length() - 1);
        buffer.append(")");
        return buffer.toString();
    }
    
	/**
	 * @param tableMap the standard tablemap
	 * @return
	 */
	static public String getInsertSql( TableMap tableMap ,IColFilter filter ) {			
		return getInsertSql( tableMap.getTableName(), getColNames( tableMap.getColNames(),filter) );
	}

	static public String[] getColNames( String[] colNames, IColFilter filter ) {
		if( filter==null || colNames==null ) {
			return colNames;
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < colNames.length; i++) {
			if( filter.isColVisible(colNames[i]) ) {
				list.add(colNames[i]);
			}
		}
		
		return list.toArray(new String[list.size()]);
	}

    /**
     * 根据表名和列名称得到更新语句
     * 
     * @param table
     * @param names
     * @return
     */
    public static  String getUpdateSql(String tableName, String[] names, String pkName) {
        return getUpdateSql(tableName, names, new String[] {pkName});
    }
    
    public static  String getUpdateSql(String tableName, String[] names, String[] pkNames) {
        StringBuffer sql = new StringBuffer("UPDATE " + tableName + " SET  ");
        for (int i = 0; i < names.length; i++) {
            sql.append(names[i] + "=?,");
        }
        sql.setLength(sql.length() - 1);
        
        sql.append(" WHERE ");
        
        for (int i = 0; i < pkNames.length; i++) {
        	sql.append(pkNames[i]).append("=? AND ");
		}
        sql.setLength(sql.length() - 4);
        
        return sql.toString();
    }


    public static String getDeleteByPKSql(String tableName, String pkName) {
        return "DELETE FROM " + tableName + " WHERE " + pkName + "=?";
    }

    public static String getDeleteSql(String tableName, String[] names) {
        StringBuffer sql = new StringBuffer("DELETE FROM " + tableName + " WHERE ");
        for (int i = 0; i < names.length; i++) {
            sql.append(names[i] + "=? AND ");
        }
        sql.setLength(sql.length() - 4);
        return  sql.toString();
    }
    /**
     * @param tableName
     * @param names
     * @param isAnd
     * @param fields
     * @return
     */
    public static  String getSelectSql(String tableName, String[] names,
            boolean isAnd, String[] fields) {
        StringBuffer sql = new StringBuffer();
        if (fields == null)
            sql.append("SELECT * FROM " + tableName);
        else {
    
            sql.append("SELECT ");
            for (int i = 0; i < fields.length; i++) {
                sql.append(fields[i] + ",");
    
            }
            sql.setLength(sql.length() - 1);
            sql.append(" FROM " + tableName);
        }
        String append = "AND ";
        if (!isAnd)
            append = "OR ";
        if (names == null || names.length == 0)
            return sql.toString();
        sql.append(" WHERE ");
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            sql.append(name + "=? ");
            if (i != names.length - 1)
                sql.append(append);
        }
        return sql.toString();
    
    }

     
    public static  String getSelectSql(String tableName, String[] fields
            ) {
        StringBuffer sql = new StringBuffer();
        if (fields == null)
            sql.append("SELECT * FROM " + tableName);
        else {
    
            sql.append("SELECT ");
            for (int i = 0; i < fields.length; i++) {
                sql.append(fields[i] + ",");
    
            }
            sql.setLength(sql.length() - 1);
            sql.append(" FROM " + tableName);
        }
       
        return sql.toString();
    
    }
    
    /**
     * get standard table map.
     * @param table
     * @return
     */
	public static TableMap getTableMap( DBSession jdbcSession, IDataTable table ) {
		   TableMap result = new TableMap();
        	
        	TableMap tableMap = table.getTableMeta().getTableMap();
        	
        	String tableName = table.getTableName();
        	if( tableMap.getTableName()!=null ) {
        		tableName = tableMap.getTableName();
        	}
        	
        	result.setTableName(tableName);
        	
            TableMeta tableMeta = table.getTableMeta();
            Map dbColType = getColmnTypes( jdbcSession, tableName );
            
            for (int i = 0; i < tableMeta.getColCount(); i++) {
            	ColumnMeta colMeta = tableMeta.getColMeta(i);
            	
            	String colName = colMeta.getColName();
            	String toColName = colName;
            	
            	ColumnMap colMap = tableMap.getColMap(colName);
            	if( colMap!=null && tableName.equalsIgnoreCase(colMap.getToTableName()) && colMap.getToColumnName()!=null ) {
            		toColName = colMap.getToColumnName();
            	}
            	
            	Integer type = (Integer) dbColType.get( toColName.toUpperCase() );
            	if( type!=null ) {	// if is availability
            		result.addColMap( new ColumnMap(colName,toColName, type.intValue()) );
            	}            	
			}
       
        return result;
	}

	/**
	 * @param row
	 * @param tableMap
	 * @param filter
	 * @return
	 * @throws BusinessException
	 */
	public static SQLParameter getInsertSqlParam(DataRow row, TableMap tableMap, IColFilter filter) throws BusinessException {
		
        SQLParameter parameter = new SQLParameter();
        for (int i = 0; i < tableMap.size(); i++) {
        	ColumnMap col = tableMap.getColMap(i);
        	if( filter!=null && !filter.isColVisible(col.getColumnName() )) {
        		continue;
        	}
        	
        	Object obj = row.getData( col.getColumnName() );

			obj = transObj( row, obj, col.getToDataType() );


        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( col.getToDataType() ) );
        	}
        }
		return parameter;
	}
	
	public static Object transObj(DataRow row, Object obj, int type ) throws BusinessException {
		return VariantHelper.transObj(row, obj, type);
	}

	/**
	 * @param table
	 * @param tableMap
	 * @return
	 */
	public static String getUpdateSql(IDataTable table, TableMap tableMap) {
		String[] pks = table.getTableMeta().getPrimaryKeyCols();
		pks = tableMap.getToColNames(pks);
        		
		// add for the 
		String[] flds = tableMap.getColNames();
		flds = StringHelper.filterStrings( flds, table.getTableMeta().getPrimaryKeyCols() );
		flds = StringHelper.filterStrings( flds, new String[]{"ts"} );

		flds = getColNames(flds, table.getTableMeta().getColFilter() );
        return  getUpdateSql(tableMap.getTableName(), flds, pks);
	}

	/**
	 * @param row
	 * @param tableMap
	 * @return
	 * @throws BusinessException
	 */
	public static SQLParameter getUpdateSqlParam(DataRow row, TableMap tableMap) throws BusinessException {
//		SQLParameter parameter = getInsertSqlParam(row, tableMap);
		
        SQLParameter parameter = new SQLParameter();
        
		String[] flds = tableMap.getColNames();
		flds = StringHelper.filterStrings( flds, row.getDataTable().getTableMeta().getPrimaryKeyCols() );
		flds = StringHelper.filterStrings( flds, new String[]{"ts"} );

		IColFilter filter = row.getDataTable().getTableMeta().getColFilter();
        for (int i = 0; i < flds.length; i++) {
        	ColumnMap col = tableMap.getColMap(flds[i]);
        	if( filter!=null && !filter.isColVisible(col.getColumnName()) ) {
        		continue;
        	}
        	
        	Object obj = row.getData( col.getColumnName() );

			obj = transObj( row, obj, col.getToDataType() );

        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( col.getToDataType() ) );
        	}
        }
        
		TableMeta tableMeta = row.getDataTable().getTableMeta();
		String[] cols = tableMeta.getPrimaryKeyCols();
		for (int i = 0; i < cols.length; i++) {
			ColumnMap col = tableMap.getColMap(cols[i]);
        	Object obj = row.getData( col.getColumnName() );
        	obj = transObj(row, obj, col.getToDataType() );

        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( col.getToDataType() ) );
        	}
		}
		
		return parameter;
	}

	/**
	 * @param tableMap
	 * @return
	 */
	public static String getDeleteSql(IDataTable table, TableMap tableMap) {
		String tableName = tableMap.getTableName();
		String[] pks = table.getTableMeta().getPrimaryKeyCols();
		pks = tableMap.getToColNames(pks);
				
		return getDeleteSql(tableName, pks);
	}

	/**
	 * @param row
	 * @param tableMap
	 * @return
	 * @throws BusinessException
	 */
	public static SQLParameter getDeleteSqlParam(DataRow row, TableMap tableMap) throws BusinessException {
		SQLParameter parameter = new SQLParameter();
		
		TableMeta tableMeta = row.getDataTable().getTableMeta();
		String[] cols = tableMeta.getPrimaryKeyCols();
		for (int i = 0; i < cols.length; i++) {
			ColumnMap col = tableMap.getColMap(cols[i]);
        	Object obj = row.getData( col.getColumnName() );
        	obj = transObj(row, obj, col.getToDataType() );

        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( col.getToDataType() ) );
        	}
		}
		
		return parameter;
	}
	
	/**
	 * add map later.
	 * @param relation
	 * @param flds
	 * @return
	 */
	public static String generalRefSql( TableRelation relation, String[] flds ) {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT ");
		for (int i = 0; i < flds.length; i++) {
			sqlBuf.append(flds[i]).append(",");
		}
		sqlBuf.setLength( sqlBuf.length()-1 );
		
		sqlBuf.append(" FROM ").append( relation.getParentTableName() ).append(" WHERE ");
		
		String[] pCols = relation.getParentCols();
		String[] cols = relation.getChildCols();
		for (int i = 0; i < cols.length; i++) {
			sqlBuf.append( pCols[i] ).append("=? AND ");
		}
		sqlBuf.setLength( sqlBuf.length() - 4 );
		
		return sqlBuf.toString();
	}
	
	/**
	 * later will add the table map.
	 * @param jdbcSession
	 * @param relation
	 * @param row
	 * @return
	 * @throws BusinessException
	 */
	public static SQLParameter getRefSQLParam(DBSession jdbcSession, TableRelation relation, DataRow row ) throws BusinessException {
		Map fldTypeMap = getColmnTypes( jdbcSession, relation.getParentTableName() );
		
        SQLParameter parameter = new SQLParameter();
        
        String[]  pcols = relation.getParentCols();
        String[] cols = relation.getChildCols();
        int dataType = java.sql.Types.VARCHAR;
        for (int i = 0; i < cols.length; i++) {
        	Object obj = row.getData( cols[i] );
//        	如果子表为兼职表，则公司主键需要取导入公司，而不是兼职公司
        	if("hi_psndoc_part".equalsIgnoreCase(relation.getChildTableName())&&"pk_corp".equalsIgnoreCase(cols[i])){
        		obj = ((DataTable)row.getDataTable()).getImportParam().getPk_corp();
        	}
        	Integer type = (Integer) fldTypeMap.get( pcols[i].toUpperCase() );
        	if( type!=null ) {
        		dataType = type.intValue();
        	}
        	//Object val = transObj(row, obj, dataType );

        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( dataType ) );
        	}
        }
		return parameter;
	}
	
	/**
	 * @param tableMap
	 * @param row
	 * @param cols
	 * @return
	 * @throws BusinessException
	 */
	public static SQLParameter getConstraintParameter( TableMap tableMap, DataRow row, String[] cols) throws BusinessException {
		SQLParameter parameter = new SQLParameter();
		 for (int i = 0; i < cols.length; i++) {
		 	
        	Object obj = row.getData( cols[i] );
        	ColumnMap colMap = tableMap.getColMap(cols[i]);
        	//Object val = transObj(row, obj, colMap.getToDataType() );

        	if( obj!=null ) {
        		parameter.addParam( obj );
        	}else {
        		parameter.addParam(new NullParamType( colMap.getToDataType() ) );
        	}
        }
		return parameter;
	}
	
	/**
	 * @param table
	 * @param tableMap
	 * @param cols2
	 * @return
	 */
	public static  String getConstraintSql(  TableMap tableMap, String[] cols) {		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) FROM ").append( tableMap.getTableName() );
		sql.append(" WHERE ");
		for (int i = 0; i < cols.length; i++) {
			String toCol = tableMap.getToColName(cols[i]);
			sql.append( toCol ).append("=? AND ");
		}
		sql.setLength(sql.length()-4);
		
		return sql.toString();
	}

}
