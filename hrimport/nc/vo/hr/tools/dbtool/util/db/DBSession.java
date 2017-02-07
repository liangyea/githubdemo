/*
 * �������� 2006-4-24
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
	     * �����в���JdbcSession����
	     *
	     * @param con ���ݿ�����
	     */
	    public DBSession(Connection con) {
	        dbType = DBUtil.getDbType(con);
	        this.conn = con;
	    }

	    /**
	     * ����Ĭ��JdbcSession��JdbcSession��Ĭ�ϴӵ�ǰ���ʵ�DataSource�õ�����
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
	 * ����JdbcSession����JdbcSession���ָ����DataSource�еõ�����
	 * @param dataSourceName ����Դ����
	 * @throws DbException   �����������Դ�������׳�DbException
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
	     * �Ƿ��Զ���Ӱ汾��Ϣ
	     *
	     * @param isAddTimeStamp 
	     */
	    public void setAddTimeStamp(boolean isAddTimeStamp) {
	        if (conn instanceof CrossDBConnection)
	            ((CrossDBConnection) conn).setAddTimeStamp(isAddTimeStamp);
	    }

	    /**
	     * �Ƿ���
	     *
	     * @param isTranslator
	     */
	    public void setSQLTranslator(boolean isTranslator) {
	        if (conn instanceof CrossDBConnection)
	            ((CrossDBConnection) conn).enableSQLTranslator(isTranslator);
	    }

	    

	    /**
	     * �����Զ��ύ
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
	     * �������񼶱�
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
	     * �ύ����
	     */
	    void commitTrans() throws DbException {
	        try {
	            conn.commit();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }

	    /**
	     * �ع�����
	     */
	    void rollbackTrans() throws DbException {
	        try {
	            conn.rollback();
	        } catch (SQLException e) {
	            throw ExceptionFactory.getException(dbType, e.getMessage(), e);
	        }
	    }


	    /**
	     * ����ֻ��
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
	     * �Ƿ�ֻ��
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
	     * ����ִ���������
	     *
	     * @param maxRows
	     */
	    public void setMaxRows(int maxRows) {
	        this.maxRows = maxRows;
	    }

	    /**
	     * �õ�ִ���������
	     *
	     * @return
	     */
	    public int getMaxRows() {
	        return maxRows;
	    }


	    /**
	     * ȡ����ѯ
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
	     * ִ���в�����ѯ
	     *
	     * @param sql       ��ѯSQL���
	     * @param parameter ��ѯ����
	     * @param processor ������������
	     * @return ��ѯ����
	     */
	    public Object executeQuery(String sql, SQLParameter parameter, ResultSetProcessor processor) throws DbException {
//	        if (!isSelectStatement(sql))
//	            throw new IllegalArgumentException(sql + "--���ǺϷ��Ĳ�ѯ���");
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
	     * ִ���޲�����ѯ
	     *
	     * @param sql       ��ѯSQL���
	     * @param processor ������������
	     * @return ��ѯ����
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
	     * ִ���и��²���
	     *
	     * @param sql       Ԥ����SQL���
	     * @param parameter ��������
	     * @return �仯����
	     */
	    public int executeUpdate(String sql, SQLParameter parameter) throws DbException {
//	        if (isSelectStatement(sql))
//	            throw new IllegalArgumentException(sql + "--���ǺϷ��ĸ������");
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
	     * ִ���޸��²���
	     *
	     * @param sql ����SQL���
	     * @return ��������
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
	     * ����в���������ѯ
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
	     * ����޲���������ѯ
	     *
	     * @param sql
	     */
	    public void addBatch(String sql) throws DbException {
	        addBatch(sql, null);
	    }


	    /**
	     * ִ����������
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
	     * �ر����ݿ�����
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
	     * ˽��Batch��
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
	            if (ps == null) {//���������û�и�PreparedStatement
	                ps = conn.prepareStatement(sql);
	                statementCache.put(sql, ps);
	            }
	            if (parameters != null)//���������Ϊ��
	            {
	                DBUtil.setStatementParameter(ps, parameters);
	            }
	            if (canBatched) { //������ݿ�֧����������
	                ps.addBatch();
	            } else {//�����֧����������
	                int updateRow = ps.executeUpdate();
	                rowCount +=  updateRow;
	            }
	            size++;
	        }

	        public int executeBatch() throws SQLException {
	            if (!canBatched)//�����֧����������
	                return rowCount;
	            //���֧����������
	            int totalRowCount = 0;
	            for (Iterator iterator = statementCache.values().iterator(); iterator.hasNext();) {
	                PreparedStatement ps = (PreparedStatement) iterator.next();
	                int[] rowCounts = ps.executeBatch();
	                for (int j = 0; j < rowCounts.length; j++) {
	                    if (rowCounts[j] == PreparedStatement.SUCCESS_NO_INFO) {
	                        // do nothing
	                    } else if (rowCounts[j] == PreparedStatement.EXECUTE_FAILED) {
	                        //throw new SQLException("����ִ�е� " + j + "��������");
	                    } else {
	                        totalRowCount += rowCounts[j];
	                    }
	                }
	            }
	            return totalRowCount;
	        }


	        /**
	         * ����������ѯ
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
	                        System.out.println("ORACLE���ݿ�ģʽ������Ϊ��");
	                    // ORACLE�轫ģʽ����д
	                    strSche = schema.toUpperCase();
	                    break;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return strSche;
		}
}
