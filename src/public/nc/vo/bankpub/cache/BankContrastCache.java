// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:42:11
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankContrastCache.java

package nc.vo.bankpub.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankContrast;
import nc.vo.bankpub.bankcontrast.BankContrastVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;
import nc.vo.pub.BusinessException;

public class BankContrastCache
{

    private BankContrastCache()
    {
        cache = new VersionSensitiveMap(CacheToMapAdapter.getInstance("nc.vo.bankpub.cache.BankContrastCache"), new TableVersionMonitor(TABLE_NAME, 1000L));
    }

    public static BankContrastCache getInstance()
    {
        return uniqueInstance;
    }

    public synchronized BankContrastVO getBankVoByPkglorgbook(String pk_glorgbook, String orient)
        throws Exception
    {
        String key = (new StringBuilder(String.valueOf(pk_glorgbook.trim()))).append(orient.trim()).toString();
        BankContrastVO vo = null;
        Map bankContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
        if(bankContrastMap == null)
            initCache();
        bankContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
        vo = (BankContrastVO)bankContrastMap.get(key);
        return vo;
    }

    public synchronized BankContrastVO getBankVoByBankCode(String bankcode, String orient)
        throws Exception
    {
        String key = (new StringBuilder(String.valueOf(bankcode.trim()))).append(orient).toString();
        BankContrastVO vo = null;
        Map bankContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
        if(bankContrastMap == null)
            initCache();
        bankContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
        vo = (BankContrastVO)bankContrastMap.get(key);
        return vo;
    }
    
    public synchronized BankContrastVO[] getAllBankContrastVOs()
    	throws BusinessException
    {
    	BankContrastVO[] vos = null;
    	BaseDAO dao = new BaseDAO();
    	Collection coll = dao.retrieveByClause(BankContrastVO.class, "isnull(dr,0)=0");
    	if(coll != null){
    		vos = (BankContrastVO[])coll.toArray(new BankContrastVO[0]);
    	}
    	return vos;
    }

    private void initCache()
        throws Exception
    {
        IBankContrast ibc = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
        BankContrastVO vos[] = (BankContrastVO[])null;
        vos = ibc.queryBankContrastVOs("0");
        Map bankContrastMap = new HashMap();
        if(vos != null)
        {
            for(int i = 0; i < vos.length; i++)
                if(vos[i].getPk_glorgbook() != null && vos[i].getBankbm() != null)
                {
                    bankContrastMap.put((new StringBuilder(String.valueOf(vos[i].getPk_glorgbook()))).append(vos[i].getOrient().trim()).toString(), vos[i]);
                    bankContrastMap.put((new StringBuilder(String.valueOf(vos[i].getBankbm().trim()))).append(vos[i].getOrient().trim()).toString(), vos[i]);
                }

        }
        cache.put(TABLE_NAME[0], bankContrastMap);
    }

    public void updateVersion()
    {
        ICacheVersionBS cv = (ICacheVersionBS)NCLocator.getInstance().lookup("nc.bs.dbcache.intf.ICacheVersionBS");
        String key = TABLE_NAME[0];
        cv.updateVersion(key);
    }

    public static void main(String args1[])
    {
    }

    private static BankContrastCache uniqueInstance = new BankContrastCache();
    private final String TABLE_NAME[] = {
        "bank_bankcontrast"
    };
    private final long TIME_OUT = 1000L;
    private Map cache;

}