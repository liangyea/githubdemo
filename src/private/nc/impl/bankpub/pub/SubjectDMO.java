package nc.impl.bankpub.pub;

import java.sql.SQLException;

import javax.naming.NamingException;

import nc.bs.pub.DataManageObject;
import nc.bs.pub.SystemException;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.pub.BusinessException;

public class SubjectDMO extends DataManageObject {

    /**
     * @throws javax.naming.NamingException
     * @throws nc.bs.pub.SystemException
     */
    public SubjectDMO() throws NamingException, SystemException {
        super();
        // TODO 自动生成构造函数存根
    }

    /**
     * @param dbName
     * @throws javax.naming.NamingException
     * @throws nc.bs.pub.SystemException
     */
    public SubjectDMO(String dbName) throws NamingException, SystemException {
        super(dbName);
        // TODO 自动生成构造函数存根
    }
    /**
     * 此处插入方法说明。
     * 创建日期：(2004-12-10 15:20:39)
     * @return nc.vo.bcd.tradeflow.SubjContrastVO[]
     * @param vo nc.vo.bcd.tradeflow.SubjContrastVO[]
     * @exception java.sql.SQLException 异常说明。
     */
    public NCSubjContrastVO[] insertSubjcontrast(NCSubjContrastVO[] vo) throws java.sql.SQLException 
    {
    	try
    	{
    		String pk = null;
    		for(int i = 0;i<vo.length;i++)
    		{
    			pk = getOID();
    			vo[i].setPk_ncsubjcontrast(pk);
    			super.executeUpdate(vo[i].getInsertSql());
    		}
    		return vo;
    	}
    	catch(Exception e)
    	{
    		throw new java.sql.SQLException(e.getMessage());
    	}
    }
    /**
     * 此处插入方法说明。
     * 创建日期：(2004-12-10 17:48:33)
     * @return nc.vo.bcd.tradeflow.SubjContrastVO[]
     * @param corp java.lang.String
     * @exception java.sql.SQLException 异常说明。
     */
    public NCSubjContrastVO[] querySubjContrast(String pk_glorgbook)
    	throws java.sql.SQLException {
    	try
    	{
    	
    		String sql = "select bcd.pk_ncsubjcontrast,bcd.pk_accsubj,bcd.bank_kmbm ,bd.subjcode,bd.subjname ,bd.pk_accsubj,bcd.istrans,bcd.isAccount,bcd.balbank_kmbm  ";
    		sql =
    			sql
    				+ " from bd_accsubj bd left outer join bank_ncsubjcontrast bcd on bd.pk_accsubj=bcd.pk_accsubj where bd.pk_glorgbook ='"
    				+ pk_glorgbook
    				+ "' order by bd.subjcode ";
    		java.util.Vector v = super.executeQuery(sql);
    		if (v == null || v.size() == 0)
    			return null;
    		NCSubjContrastVO vo[] = new NCSubjContrastVO[v.size()];
    		java.util.Vector v0 = null;
    		java.util.Vector vre = new java.util.Vector();
    		int iColNum = 0;
    		for (int i = 0; i < vo.length; i++)
    		{
    			vo[i] = new NCSubjContrastVO();
    			vo[i].setPk_glorgbook(pk_glorgbook);
    			v0 = (java.util.Vector) v.elementAt(i);

    			if (v0.elementAt(0) != null && v0.elementAt(0).toString().trim().length() != 0)
    			{
    				vo[i].setPk_ncsubjcontrast(v0.elementAt(0).toString());
    				vo[i].setPk_accsubj(v0.elementAt(1).toString());
    				vo[i].setBank_kmbm(v0.elementAt(2)==null?"":v0.elementAt(2).toString());
    				vo[i].nckmbm =v0.elementAt(3)==null?"": v0.elementAt(3).toString();
    				vo[i].nckmmc =v0.elementAt(4)==null?"": v0.elementAt(4).toString();
    				vo[i].setIstrans(v0.elementAt(6)==null?"":v0.elementAt(6).toString());
    				vo[i].setIsAccount(v0.elementAt(7)==null?"":v0.elementAt(7).toString());
    				vo[i].setBalbank_kmbm(v0.elementAt(8)==null?"":v0.elementAt(8).toString());
    			}
    			else
    			{
    				vo[i].setPk_ncsubjcontrast(getOID());
    				vo[i].setPk_accsubj(v0.elementAt(5).toString());
    				vo[i].nckmbm = v0.elementAt(3).toString();
    				vo[i].nckmmc = v0.elementAt(4).toString();
    				vo[i].setBank_kmbm("#");
    				vo[i].setIstrans("N");
    				vo[i].setIsAccount("N");
    				vo[i].setBalbank_kmbm("#");
    				super.executeUpdate(vo[i].getInsertSql());
    			}
    		}
    		return vo;
    	}
    	catch (Exception e)
    	{
    	    e.printStackTrace();
    		throw new java.sql.SQLException(e.getMessage());
    	}
    }
    
    public void updateSubjContrast(NCSubjContrastVO [] vo) throws SQLException {
        for (int i = 0; i < vo.length; i++) {
            executeUpdate(vo[i].getUpdateSql());
        }
    }
    
    public void clearSubjcontrast(String pk_glorgbook) throws SQLException {
		String sql = "delete from bank_ncsubjcontrast ";
		if(pk_glorgbook != null && !pk_glorgbook.trim().equals(""))
		{
			sql = sql + " where pk_glorgbook='"+pk_glorgbook+"'";
		}
		executeUpdate(sql);
    }
    
    
    public NCSubjContrastVO[] queryNCSubjContrastbyPKDept(String pk_deptdoc) throws BusinessException {

		try {

			String sql = "select  pk_accsubj,bank_kmbm ,pk_dept,pk_ncsubjcontrast from bank_ncsubjcontrast where pk_dept='"+pk_deptdoc+"'";
			java.util.Vector v = super.executeQuery(sql);
			if (v == null || v.size() == 0)
				return null;
			NCSubjContrastVO vo[] = new NCSubjContrastVO[v.size()];
			java.util.Vector v0 = null;
			java.util.Vector vre = new java.util.Vector();
			int iColNum = 0;
			for (int i = 0; i < vo.length; i++) {
				vo[i] = new NCSubjContrastVO();
				v0 = (java.util.Vector) v.elementAt(i);
				vo[i].setPk_accsubj(v0.elementAt(0).toString());
				vo[i].setBank_kmbm(v0.elementAt(1).toString());
				//vo[i].setPk_glorgbook(v0.elementAt(2).toString());
				vo[i].setPk_dept(v0.elementAt(2).toString());
				vo[i].setPk_ncsubjcontrast(v0.elementAt(3).toString());
			}
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

	}
    
   
}
