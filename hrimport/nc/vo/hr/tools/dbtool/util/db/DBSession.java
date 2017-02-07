/*
 * 创建日期 2006-4-24
 */
package nc.vo.hr.tools.dbtool.util.db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.jdbc.framework.ConnectionFactory;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.crossdb.CrossDBConnection;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.exception.ExceptionFactory;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.jdbc.framework.util.DBUtil;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-24 13:41:02
 * @author ych
 */
public class DBSession {
	public final static int DB2 = 0;
	public final static int ORACLE = 1;
	public final static int SQLSERVER = 2;
	public final static int SYBASE = 3;
	public final static int UNKOWNDATABASE = -1;
	   private Connection conn = null;
	    private int maxRows = 0;
	    private int dbType = 0;
	    private int timeoutInSec = 0;
	    private int fetchSize = 0;
	    private PreparedStatement prepStatement = null;
	    private String lastSQL = null;
	    private Batch batch;
	    private DatabaseMetaData dbmd = null;

	    /**
	     * 构造有参数JdbcSession对象
	     *
	     * @param con 数据库连接
	     */
	    public DBSession(Connection con) {
	        dbType = DBUtil.getDbType(con);
	        this.conn = con;
	    }

	    /**
	     * 构造默认JdbcSession该JdbcSession会默认从当前访问的DataSource得到连接
	     *
	     *  
	     */
	    public DBSession() throws DbException {

	        try {

	            Connection con = ConnectionFactory.getConnection();
	            dbType = DBUtil.getDbType(con);
	            this.conn = con;
	        } catch (SQLException e) {
	            throw  ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }

	    }
	/**
	 * 构造JdbcSession，该JdbcSession会从指定的DataSource中得到连接
	 * @param dataSourceName 数据源名称
	 * @throws DbException   如果访问数据源出错则抛出DbException
	 */
	    public DBSession(String dataSourceName) throws DbException {
	        try {
	            Connection con = ConnectionFactory.getConnection(dataSourceName);
	            dbType = DBUtil.getDbType(con);
	            this.conn = con;
	        } catch (SQLException e) {
	            throw  ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }

	    }

	    /**
	     * 是否自动添加版本信息
	     *
	     * @param isAddTimeStamp 
	     */
	    public void setAddTimeStamp(boolean isAddTimeStamp) {
	        if (conn instanceof CrossDBConnection)
	            ((CrossDBConnection) conn).setAddTimeStamp(isAddTimeStamp);
	    }

	    /**
	     * 是否翻译
	     *
	     * @param isTranslator
	     */
	    public void setSQLTranslator(boolean isTranslator) {
	        if (conn instanceof CrossDBConnection)
	            ((CrossDBConnection) conn).enableSQLTranslator(isTranslator);
	    }

	    

	    /**
	     * 设置自动提交
	     *
	     * @param autoCommit
	     */
	    void setAutoCommit(boolean autoCommit) throws DbException {
	        try {
	            conn.setAutoCommit(autoCommit);

	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    public int getFetchSize() {
	        return fetchSize;
	    }

	    public void setFetchSize(int fetchSize) {
	        this.fetchSize = fetchSize;
	    }

	    /**
	     * 设置事务级别
	     *
	     * @param level
	     */
	    void setTransactionIsolation(int level) throws DbException {
	        try {
	            conn.setTransactionIsolation(level);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * 提交事务
	     */
	    void commitTrans() throws DbException {
	        try {
	            conn.commit();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * 回滚事务
	     */
	    void rollbackTrans() throws DbException {
	        try {
	            conn.rollback();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }


	    /**
	     * 设置只读
	     *
	     * @param readOnly
	     */
	    public void setReadOnly(boolean readOnly) throws DbException {
	        try {
	            conn.setReadOnly(readOnly);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * 是否只读
	     *
	     * @return
	     */
	    public boolean isReadOnly() throws DbException {
	        try {
	            return conn.isReadOnly();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * 设置执行最大行数
	     *
	     * @param maxRows
	     */
	    public void setMaxRows(int maxRows) {
	        this.maxRows = maxRows;
	    }

	    /**
	     * 得到执行最大行数
	     *
	     * @return
	     */
	    public int getMaxRows() {
	        return maxRows;
	    }


	    /**
	     * 取消查询
	     */
	    public void cancelQuery() throws DbException {
	        try {
	            if (prepStatement != null)
	                prepStatement.cancel();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage());
	        }
	    }


	    /**
	     * 执行有参数查询
	     *
	     * @param sql       查询SQL语句
	     * @param parameter 查询参数
	     * @param processor 结果集处理对象
	     * @return 查询对象
	     */
	    public Object executeQuery(String sql, SQLParameter parameter, ResultSetProcessor processor) throws DbException {
//	        if (!isSelectStatement(sql))
//	            throw new IllegalArgumentException(sql + "--不是合法的查询语句");
	        Object result = null;
	        ResultSet rs = null;

	        try {            	
	            if ((!sql.equalsIgnoreCase(lastSQL)) || (prepStatement == null)) {
	            	if( prepStatement!=null ) {
	            		prepStatement.close();
	            	}
	                prepStatement = conn.prepareStatement(sql);
	                lastSQL = sql;
	            }

	            prepStatement.clearParameters();
	            if (parameter != null) {
	                DBUtil.setStatementParameter(prepStatement, parameter);
	            }
	            if (timeoutInSec > 0)
	                prepStatement.setQueryTimeout(timeoutInSec);
	            if (maxRows > 0)
	                prepStatement.setMaxRows(maxRows);
	            rs = prepStatement.executeQuery();
	            result = processor.handleResultSet(rs);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        } finally {
	            closeRs(rs);
	        }
	        
	        return result;
	    }


	    /**
	     * 执行无参数查询
	     *
	     * @param sql       查询SQL语句
	     * @param processor 结果集处理对象
	     * @return 查询对象
	     */
	    public Object executeQuery(String sql, ResultSetProcessor processor) throws DbException {
	        Object result = null;
	        ResultSet rs = null;
	        Statement st = null;
	        try {
	            st = conn.createStatement();
	            if (timeoutInSec > 0)
	                st.setQueryTimeout(timeoutInSec);
	            if (maxRows > 0)
	                st.setMaxRows(maxRows);
	            rs = st.executeQuery(sql);
	            result = processor.handleResultSet(rs);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        } finally {
	            closeRs(rs);
	            closeStmt(st);
	        }
	        return result;
	    }


	    /**
	     * 执行有更新操作
	     *
	     * @param sql       预编译SQL语句
	     * @param parameter 参数对象
	     * @return 变化行数
	     */
	    public int executeUpdate(String sql, SQLParameter parameter) throws DbException {
//	        if (isSelectStatement(sql))
//	            throw new IllegalArgumentException(sql + "--不是合法的更新语句");
	        int updateRows = 0;

	        try {
	   //        System.out.println("old sql--"+lastSQL);
	      //      System.out.println("new slq--"+sql);

	            if ((!sql.equalsIgnoreCase(lastSQL)) || (prepStatement == null)) {
	            	if( prepStatement!=null ) {
	            		prepStatement.close();
	            	}
	                prepStatement = conn.prepareStatement(sql);
	                lastSQL = sql;
	            }
	            if (maxRows > 0)
	                prepStatement.setMaxRows(maxRows);
	            if (fetchSize > 0)
	                prepStatement.setFetchSize(fetchSize);
	            prepStatement.clearParameters();
	            if (parameter != null) {
	                DBUtil.setStatementParameter(prepStatement, parameter);
	            }
	            updateRows = prepStatement.executeUpdate();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	        return updateRows;
	    }


	    /**
	     * 执行无更新操作
	     *
	     * @param sql 更新SQL语句
	     * @return 更新行数
	     */
	    public int executeUpdate(String sql) throws DbException {
	        //   return executeUpdate(sql,null);
	        int updateRows = 0;
	        Statement st = null;
	        try {
	            st = conn.createStatement();
	            if (maxRows > 0)
	                st.setMaxRows(maxRows);
	            updateRows = st.executeUpdate(sql);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        } finally {
	            closeStmt(st);
	        }
	        return updateRows;
	    }


	    /**
	     * 添加有参数批量查询
	     *
	     * @param sql
	     * @param parameters
	     */
	    public void addBatch(String sql, SQLParameter parameters) throws DbException {
	        try {
	            if (batch == null) {
	                batch = new Batch();
	            }
	            batch.addBatch(sql, parameters);
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * 添加无参数批量查询
	     *
	     * @param sql
	     */
	    public void addBatch(String sql) throws DbException {
	        addBatch(sql, null);
	    }


	    /**
	     * 执行批量更新
	     *
	     * @return
	     */
	    public int executeBatch() throws DbException {
	        try {
	            int rows = 0;
	            if (batch != null) {
	                try {
	                    rows = batch.executeBatch();
	                } finally {
	                    batch.cleanupBatch();
//	                    batch = null;
	                }
	            }
	            return rows;
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }


	    /**
	     * 关闭数据库连接
	     */
	    public void closeAll() {
	        closeStmt(prepStatement);
	        closeConnection(conn);
	    }
	    
/*	   
 		public void closeStmt() {
	        closeStmt( prepStatement );
	    }
*/
	    
	    private void closeConnection(Connection con) {
	        try {
	            if (con != null) {
	                con.close();
	                con = null;
	            }
	        } catch (SQLException e) {
	        }
	    }


	    private void closeStmt(Statement stmt) {
	        try {
	            if (stmt != null) {
	                stmt.close();
	                stmt = null;
	            }
	        } catch (SQLException e) {
	        }
	    }


	    private void closeRs(ResultSet rs) {
	        try {
	            if (rs != null) {
	                rs.close();
	                rs = null;
	            }
	        } catch (SQLException e) {
	        }
	    }

	    private boolean isSupportBatch() throws SQLException {
	        return getMetaData().supportsBatchUpdates();
	    }

	    public DatabaseMetaData getMetaData() throws SQLException {
	        if (dbmd == null)
	            dbmd = conn.getMetaData();
	        return dbmd;
	    }

	    /**
	     * 私有Batch类
	     */
	    private class Batch {
	        private int size;
	        private Map<String,PreparedStatement> statementCache = new HashMap<String,PreparedStatement>();
	        private int rowCount = 0;
	        boolean canBatched = false;

	        public Batch() throws SQLException {
	            this.size = 0;
	            canBatched = isSupportBatch();
	        }

	        public int getSize() {
	            return size;
	        }

	        public void addBatch(String sql, SQLParameter parameters) throws SQLException {
	            PreparedStatement ps = statementCache.get(sql);
	            if (ps == null) {//如果缓存中没有该PreparedStatement
	                ps = conn.prepareStatement(sql);
	                statementCache.put(sql, ps);
	            }
	            if (parameters != null)//如果参数不为空
	            {
	                DBUtil.setStatementParameter(ps, parameters);
	            }
	            if (canBatched) { //如果数据库支持批量更新
	                ps.addBatch();
	            } else {//如果不支持批量更新
	                int updateRow = ps.executeUpdate();
	                rowCount +=  updateRow;
	            }
	            size++;
	        }

	        public int executeBatch() throws SQLException {
	            if (!canBatched)//如果不支持批量更新
	                return rowCount;
	            //如果支持批量更新
	            int totalRowCount = 0;
	            for (Iterator iterator = statementCache.values().iterator(); iterator.hasNext();) {
	                PreparedStatement ps = (PreparedStatement) iterator.next();
	                int[] rowCounts = ps.executeBatch();
	                for (int j = 0; j < rowCounts.length; j++) {
	                    if (rowCounts[j] == PreparedStatement.SUCCESS_NO_INFO) {
	                        // do nothing
	                    } else if (rowCounts[j] == PreparedStatement.EXECUTE_FAILED) {
	                        //throw new SQLException("批量执行第 " + j + "条语句出错！");
	                    } else {
	                        totalRowCount += rowCounts[j];
	                    }
	                }
	            }
	            return totalRowCount;
	        }


	        /**
	         * 清理批量查询
	         */
	        public void cleanupBatch() throws DbException {
	            for (Iterator iterator = statementCache.values().iterator(); iterator.hasNext();) {
	                PreparedStatement ps = (PreparedStatement) iterator.next();
	                closeStmt(ps);
	            }
	            statementCache.clear();
	            size = 0;
	            rowCount = 0;
	        }
	    }
	    
	    
	    // add oper.
	    
/*		public int getDBType( ) {
	        int iDBType = -1;
	        if (conn instanceof UFConnection)
				try {
					iDBType = ((UFConnection) conn).getDatabaseType();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        return iDBType;
		}*/
		
		public String getScheme() {
	        String strSche = null;
	        try {
	            String schema = getMetaData().getUserName();
	            switch ( dbType ) {
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
}
