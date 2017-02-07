// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-31 0:21:59
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankContrastDMO.java

package nc.impl.bankpub.pub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import nc.bs.pub.DataManageObject;
import nc.bs.pub.SuperDMO;
import nc.bs.pub.SystemException;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.bankpub.bankcontrast.BankContrastVO;

public class BankContrastDMO extends DataManageObject
{

    public BankContrastDMO()
        throws NamingException, SystemException
    {
    }

    public BankContrastDMO(String dbName)
        throws NamingException, SystemException
    {
        super(dbName);
    }

    public void update(BankContrastVO bankcontrastvo)
        
    {        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
			String sql = "update bank_bankcontrast set pk_glorgbook=?, clerkcode = ?, bankbm = ?,bankmc = ?, ";
			sql = (new StringBuilder(String.valueOf(sql))).append(" pwd =?, terminalno=? ").toString();
			sql = (new StringBuilder(String.valueOf(sql))).append(" where pk_glorgbook =? and orient=?").toString();
			con = getConnection();
			stmt = con.prepareStatement(sql);
			if(bankcontrastvo.getPk_glorgbook() == null)
			    stmt.setNull(1, 1);
			else
			    stmt.setString(1, bankcontrastvo.getPk_glorgbook().trim());
			if(bankcontrastvo.getClerkcode() == null)
			    stmt.setNull(2, 1);
			else
			    stmt.setString(2, bankcontrastvo.getClerkcode().trim());
			if(bankcontrastvo.getBankbm() == null)
			    stmt.setNull(3, 1);
			else
			    stmt.setString(3, bankcontrastvo.getBankbm().trim());
			if(bankcontrastvo.getBankmc() == null)
			    stmt.setNull(4, 1);
			else
			    stmt.setString(4, bankcontrastvo.getBankmc().trim());
			if(bankcontrastvo.getPwd() == null)
			    stmt.setNull(5, 1);
			else
			    stmt.setString(5, bankcontrastvo.getPwd().trim());
			if(bankcontrastvo.getTerminalno() == null)
			    stmt.setNull(6, 1);
			else
			    stmt.setString(6, bankcontrastvo.getTerminalno().trim());
			stmt.setString(7, bankcontrastvo.getPk_glorgbook().trim());
			if(bankcontrastvo.getOrient() == null)
			    stmt.setNull(8, 1);
			else
			    stmt.setString(8, bankcontrastvo.getOrient().trim());
			/*if(bankcontrastvo.getPk_system() == null)
			    stmt.setNull(9, 1);
			else
			    stmt.setString(9, bankcontrastvo.getPk_system().trim());*/
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null)
				    stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(con != null)
				    con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    }
    
    public void delete(){
        PersistenceManager sessionManager = null;
        JdbcSession session = null;
        try {
			sessionManager = PersistenceManager.getInstance();
			session = sessionManager.getJdbcSession();
			String sql = "delete from bank_bankcontrast";
			session.executeUpdate(sql);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sessionManager != null)
                sessionManager.release();
		}
    }

    public String insert(BankContrastVO bankcontrastvo)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        String keys = null;
		try {
			String sql = "insert into bank_bankcontrast( pk_bankcontrast,pk_glorgbook,  ";
			sql = (new StringBuilder(String.valueOf(sql))).append(" clerkcode,bankbm,bankmc,pk_system,orient,pwd,terminalno ) ").toString();
			sql = (new StringBuilder(String.valueOf(sql))).append(" values( ?, ?, ?, ?,?,?,?,?,?) ").toString();
			keys = getOID();
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, keys);
			if(bankcontrastvo.getPk_glorgbook() == null)
			    stmt.setNull(2, 1);
			else
			    stmt.setString(2, bankcontrastvo.getPk_glorgbook());
			if(bankcontrastvo.getClerkcode() == null)
			    stmt.setNull(3, 1);
			else
			    stmt.setString(3, bankcontrastvo.getClerkcode());
			if(bankcontrastvo.getBankbm() == null)
			    stmt.setNull(4, 1);
			else
			    stmt.setString(4, bankcontrastvo.getBankbm());
			if(bankcontrastvo.getBankmc() == null)
			    stmt.setNull(5, 1);
			else
			    stmt.setString(5, bankcontrastvo.getBankmc());
			if(bankcontrastvo.getPk_system() == null)
			    stmt.setNull(6, 1);
			else
			    stmt.setString(6, bankcontrastvo.getPk_system());
			if(bankcontrastvo.getOrient() == null)
			    stmt.setNull(7, 1);
			else
			    stmt.setString(7, bankcontrastvo.getOrient());
			if(bankcontrastvo.getPwd() == null)
			    stmt.setNull(8, 1);
			else
			    stmt.setString(8, bankcontrastvo.getPwd());
			if(bankcontrastvo.getTerminalno() == null)
			    stmt.setNull(9, 1);
			else
			    stmt.setString(9, bankcontrastvo.getTerminalno());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null)
				    stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(con != null)
				    con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return keys;
    }

    public void deleteBankContrastVO(BankContrastVO vo)
    {
    	if(vo == null)
            return;
        PersistenceManager sessionManager = null;
        JdbcSession session = null;
        try {
			String sql = (new StringBuilder("delete from bank_bankcontrast where pk_bankcontrast='")).append(vo.getPk_bankcontrast()).append("'").toString();
			sessionManager = PersistenceManager.getInstance();
			session = sessionManager.getJdbcSession();
			session.executeUpdate(sql);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sessionManager != null)
                sessionManager.release();
		}
    }

    public BankContrastVO[] queryBankContrastVOs(String orient)
    {
    	if(orient == null)
            return null;
        BankContrastVO vos[] = null;
        PersistenceManager sessionManager = null;
        JdbcSession session = null;
        
        try {
			StringBuffer sb = new StringBuffer();
			sb.append(" select b.pk_bankcontrast,b.pk_glorgbook,b.bankbm,b.bankmc, ");
			sb.append(" b.clerkcode,b.pk_system ,b.orient,b.pwd,b.terminalno ");
			sb.append(" from bank_bankcontrast b ");
			sb.append(" where 1=1 ");
			if(orient != null && orient.length() > 0)
			    sb.append((new StringBuilder(" and orient='")).append(orient).append("'").toString());
			sb.append(" and isnull(dr,0)=0");
			sessionManager = PersistenceManager.getInstance();
			session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sb.toString(), new ArrayListProcessor());
			Object res[] = list.toArray();
			Vector v = new Vector();
			if(res != null && res.length != 0)
			{
			    for(int i = 0; i < res.length; i++)
			    {
			        Object o[] = (Object[])res[i];
			        BankContrastVO tmpvo = new BankContrastVO();
			        tmpvo.setPk_bankcontrast(o[0] != null ? o[0].toString().trim() : "");
			        tmpvo.setPk_glorgbook(o[1] != null ? o[1].toString().trim() : "");
			        tmpvo.setBankbm(o[2] != null ? o[2].toString().trim() : "");
			        tmpvo.setBankmc(o[3] != null ? o[3].toString().trim() : "");
			        tmpvo.setClerkcode(o[4] != null ? o[4].toString().trim() : "");
			        tmpvo.setPk_system(o[5] != null ? o[5].toString().trim() : "");
			        tmpvo.setOrient(o[6] != null ? o[6].toString().trim() : "");
			        tmpvo.setPwd(o[7] != null ? o[7].toString().trim() : "");
			        tmpvo.setTerminalno(o[8] != null ? o[8].toString().trim() : "");
			        v.addElement(tmpvo);
			    }

			}
			if(v.size() > 0)
			{
			    vos = new BankContrastVO[v.size()];
			    v.copyInto(vos);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sessionManager != null)
                sessionManager.release();
		}
        return vos;
    }

    public void updateBankContrastVO(BankContrastVO vo)
        throws Exception
    {
        if(vo == null)
            return;
        if(vo.getPrimaryKey() == null || vo.getPrimaryKey().trim().length() == 0)
        {
            (new SuperDMO()).insert(vo);
            return;
        } else
        {
            (new SuperDMO()).update(vo);
            return;
        }
    }
    
    public BankContrastVO[] queryBankContrastVOsByPkglorgbook(String orient, String pk_glorgbook)
    {
    	if(orient == null)
            return null;
        BankContrastVO vos[] = null;
        PersistenceManager sessionManager = null;
        JdbcSession session = null;
        try {
			String sql = (new StringBuilder("select b.pk_bankcontrast,b.pk_glorgbook,b.bankbm,b.bankmc,b.clerkcode,b.pk_system,b.pk_deptdoc, g.glorgbookcode,g.glorgbookname,d.deptcode,d.deptname,b.pwd,b.terminalno  from bank_bankcontrast b,bd_glorgbook g,bd_deptdoc d   where b.pk_glorgbook=g.pk_glorgbook and b.pk_deptdoc=d.pk_deptdoc and b.orient='")).append(orient).append("' ").toString();
			if(pk_glorgbook != null)
			    sql = (new StringBuilder(String.valueOf(sql))).append(" and b.pk_glorgbook='").append(pk_glorgbook).append("'").toString();
			sessionManager = PersistenceManager.getInstance();
			session = sessionManager.getJdbcSession();
			List list = (List)session.executeQuery(sql, new ArrayListProcessor());
			Object res[] = list.toArray();
			Vector v = new Vector();
			if(res != null && res.length != 0)
			{
			    for(int i = 0; i < res.length; i++)
			    {
			        Object o[] = (Object[])res[i];
			        BankContrastVO tmpvo = new BankContrastVO();
			        tmpvo.setPk_bankcontrast(o[0] != null ? o[0].toString() : "");
			        tmpvo.setPk_glorgbook(o[1] != null ? o[1].toString() : "");
			        tmpvo.setBankbm(o[2] != null ? o[2].toString() : "");
			        tmpvo.setBankmc(o[3] != null ? o[3].toString() : "");
			        tmpvo.setClerkcode(o[4] != null ? o[4].toString() : "");
			        tmpvo.setPk_system(o[5] != null ? o[5].toString() : "");
			        tmpvo.setPk_deptdoc(o[6] != null ? o[6].toString() : "");
			        tmpvo.glorgbookcode = o[7] != null ? o[7].toString() : "";
			        tmpvo.glorgbookname = o[8] != null ? o[8].toString() : "";
			        tmpvo.deptcode = o[9] != null ? o[9].toString() : "";
			        tmpvo.deptname = o[10] != null ? o[10].toString() : "";
			        tmpvo.setPwd(o[11] != null ? o[11].toString().trim() : "");
			        tmpvo.setTerminalno(o[12] != null ? o[12].toString().trim() : "");
			        v.addElement(tmpvo);
			    }

			}
			if(v.size() > 0)
			{
			    vos = new BankContrastVO[v.size()];
			    v.copyInto(vos);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(sessionManager != null)
                sessionManager.release();
		}
        return vos;
    }
}