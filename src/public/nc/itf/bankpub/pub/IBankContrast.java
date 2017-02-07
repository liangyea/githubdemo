// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:47:04
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   IBankContrast.java

package nc.itf.bankpub.pub;

import nc.vo.bankpub.bankcontrast.BankContrastVO;
import nc.vo.pub.BusinessException;

public interface IBankContrast
{

    public abstract void maintainBankContrast(BankContrastVO abankcontrastvo[])
        throws BusinessException;

    public abstract void delete()
        throws BusinessException;

    public abstract BankContrastVO[] queryBankContrastVOs(String s)
        throws BusinessException;

    public abstract BankContrastVO[] queryBankContrastVOsDept(String s)
        throws BusinessException;

    public abstract BankContrastVO[] queryBankContrastVOsByPkglorgbook(String s, String s1)
        throws BusinessException;

    public abstract void deleteBankContrastVO(BankContrastVO bankcontrastvo)
        throws BusinessException;

    public abstract void insertBankContrastVOs(BankContrastVO abankcontrastvo[])
        throws BusinessException;

    public abstract void updateBankContrastVO(BankContrastVO bankcontrastvo)
        throws BusinessException;
}