// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-31 0:21:27
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   ImplBankContrast.java

package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IBankContrast;
import nc.vo.bankpub.bankcontrast.BankContrastVO;
import nc.vo.bankpub.cache.BankContrastCache;
import nc.vo.pub.BusinessException;

// Referenced classes of package nc.impl.bankpub.pub:
//            BankContrastDMO, DataItfDAO

public class ImplBankContrast
    implements IBankContrast
{

    public ImplBankContrast()
    {
    }

    public void maintainBankContrast(BankContrastVO vos[])
        throws BusinessException
    {
        try
        {
            BankContrastDMO dom = new BankContrastDMO();
            if(vos != null)
            {
                for(int i = 0; i < vos.length; i++)
                    if(vos[i].getPk_bankcontrast() == null || vos[i].getPk_bankcontrast().trim().length() == 0)
                        dom.insert(vos[i]);
                    else
                        dom.update(vos[i]);

            }
            Logger.info("========更新机构对照缓存缓存========");
            BankContrastCache.getInstance().updateVersion();
            Logger.info("========更新机构对照缓存缓存========");
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
    }

    public void delete()
        throws BusinessException
    {
        try
        {
            DataItfDAO dom = new DataItfDAO();
            dom.deleteBankContrast();
            Logger.info("========更新机构对照缓存缓存========");
            BankContrastCache.getInstance().updateVersion();
            Logger.info("========更新机构对照缓存缓存========");
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
    }

    public void deleteBankContrastVO(BankContrastVO vo)
        throws BusinessException
    {
        if(vo == null)
            return;
        try
        {
            BankContrastDMO dom = new BankContrastDMO();
            dom.deleteBankContrastVO(vo);
            Logger.info("========更新机构对照缓存缓存========");
            BankContrastCache.getInstance().updateVersion();
            Logger.info("========更新机构对照缓存缓存========");
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
    }

    public BankContrastVO[] queryBankContrastVOs(String orient)
        throws BusinessException
    {
        if(orient == null)
            return null;
        BankContrastVO vos[] = (BankContrastVO[])null;
        try
        {
            BankContrastDMO dom = new BankContrastDMO();
            vos = dom.queryBankContrastVOs(orient);
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
        return vos;
    }

    public void insertBankContrastVOs(BankContrastVO vos[])
        throws BusinessException
    {
        if(vos == null || vos.length == 0)
            return;
        try
        {
            BaseDAO dao = new BaseDAO();
            dao.insertVOArray(vos);
            Logger.info("========更新机构对照缓存缓存========");
            BankContrastCache.getInstance().updateVersion();
            Logger.info("========更新机构对照缓存缓存========");
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
    }

    public void updateBankContrastVO(BankContrastVO vo)
        throws BusinessException
    {
        if(vo == null)
            return;
        try
        {
            BankContrastDMO dom = new BankContrastDMO();
            dom.updateBankContrastVO(vo);
            Logger.info("========更新机构对照缓存缓存========");
            BankContrastCache.getInstance().updateVersion();
            Logger.info("========更新机构对照缓存缓存========");
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
    }

    public BankContrastVO[] queryBankContrastVOsDept(String orient)
        throws BusinessException
    {
        if(orient == null)
            return null;
        BankContrastVO vos[] = (BankContrastVO[])null;
        BankContrastDMO dom;
        try
        {
            dom = new BankContrastDMO();
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
        return vos;
    }

    public BankContrastVO[] queryBankContrastVOsByPkglorgbook(String orient, String pk_glorgbook)
        throws BusinessException
    {
        if(orient == null)
            return null;
        BankContrastVO vos[] = (BankContrastVO[])null;
        try
        {
            BankContrastDMO dom = new BankContrastDMO();
            vos = dom.queryBankContrastVOsByPkglorgbook(orient, pk_glorgbook);
        }
        catch(Exception e)
        {
            throw new BusinessException("Remote Call", e);
        }
        return vos;
    }
}