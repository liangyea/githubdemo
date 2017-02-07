package nc.impl.bankpub.pub;

import javax.naming.NamingException;

import nc.bs.pub.DataManageObject;
import nc.bs.pub.SystemException;
import nc.vo.bankpub.currrate.CurrAdjustRateVO;
import nc.vo.bankpub.currrate.CurrrateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class CurrrateDMO extends DataManageObject {
    public CurrrateDMO() throws NamingException, SystemException {
        super();
        // TODO 自动生成构造函数存根
    }
    public void insertCurrrateVOs(CurrrateVO[] vos) throws java.sql.SQLException {
    	
    	try
    	{
    		
    		String pk = null;
    		for(int i = 0;i<vos.length;i++)
    		{
    			pk = getOID();
    			System.out.println("+++++++++++++主键"+pk);
    			vos[i].setPK_CURRRATE(pk);
    			String pk_currinfo = getCurrInfo(vos[i].getPK_CORP(),vos[i].getPK_CURRTYPE());
    			vos[i].setPK_CURRINFO(pk_currinfo);
    			super.executeUpdate(vos[i].getInsertSql());
    			super.executeUpdate("commit;");
    		}

    	}
    	catch(Exception e)
    	{
    		throw new java.sql.SQLException(e.getMessage());
    	}
      	
    }
    

    public void insertAdjustrateVOs(CurrAdjustRateVO[] vos) throws java.sql.SQLException {
    	
    	try
    	{
    		
    		String pk = null;
    		for(int i = 0;i<vos.length;i++)
    		{
    			pk = getOID();
    			System.out.println("+++++++++++++主键"+pk);
    			vos[i].setPK_ADJUSTRATE(pk);
    			super.executeUpdate(vos[i].getInsertSql());
    			super.executeUpdate("commit;");
    		}

    	}
    	catch(Exception e)
    	{
    		throw new java.sql.SQLException(e.getMessage());
    	}
      	
    }
    public CurrrateVO[] queryCurrrateVOs(UFDate date)throws BusinessException {
    	
    	if(date== null){
    		return null;
    	}
    	CurrrateVO[] vos = null;
    	try{
    		DataItfDAO dao = new DataItfDAO();
    		vos = dao.queryCurrrateVOs(date);
    	}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
    	}
    	return vos;
    }
    public String getCurrInfo(String pk_corp,String pk_currtype)throws BusinessException {
    	if(pk_corp==null || pk_currtype==null){
    		return null;
    	}
    	String pk_currinfo = null;
		try{
			DataItfDAO dao = new DataItfDAO();
			pk_currinfo = dao.getPkCurrInfo(pk_corp, pk_currtype);
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		 }
    	return pk_currinfo;
    }
}
