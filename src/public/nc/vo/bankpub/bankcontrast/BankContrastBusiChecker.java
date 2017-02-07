// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:39:07
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankContrastBusiChecker.java

package nc.vo.bankpub.bankcontrast;

import nc.bs.trade.business.IBDBusiCheck;
import nc.vo.bankpub.cache.BankContrastCache;
import nc.vo.pub.AggregatedValueObject;

public class BankContrastBusiChecker
    implements IBDBusiCheck
{

    public BankContrastBusiChecker()
    {
    }

    public void check(int i, AggregatedValueObject aggregatedvalueobject, Object obj)
        throws Exception
    {
    }

    public void dealAfter(int intBdAction, AggregatedValueObject billVo, Object userObj)
        throws Exception
    {
        BankContrastCache.getInstance().updateVersion();
    }
}