// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:44:56
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankNCContrastUICheckRule.java

package nc.ui.bankpub.bankcontrast;

import java.awt.Container;

import nc.ui.trade.check.BeforeActionCHK;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.trade.checkrule.ICheckRule;
import nc.vo.trade.checkrule.ICheckRules;
import nc.vo.trade.checkrule.ICheckRules2;
import nc.vo.trade.checkrule.ICompareRule;
import nc.vo.trade.checkrule.ISpecialChecker;
import nc.vo.trade.checkrule.IUniqueRule;
import nc.vo.trade.checkrule.IUniqueRules;
import nc.vo.trade.checkrule.VOChecker;

public class BankNCContrastUICheckRule extends BeforeActionCHK
    implements ICheckRules, IUniqueRules, ICheckRules2
{

    public BankNCContrastUICheckRule()
    {
    }

    public IUniqueRule[] getHeadUniqueRules()
    {
        return null;
    }

    public IUniqueRule[] getItemUniqueRules(String tablecode)
    {
        IUniqueRule unique[] = (IUniqueRule[])null;
        return unique;
    }

    public ISpecialChecker getSpecialChecker()
    {
        return null;
    }

    public boolean isAllowEmptyBody(String tablecode)
    {
        return true;
    }

    public ICheckRule[] getHeadCheckRules()
    {
        return null;
    }

    public ICheckRule[] getItemCheckRules(String tablecode)
    {
        return null;
    }

    public ICompareRule[] getHeadCompareRules()
    {
        return null;
    }

    public String[] getHeadIntegerField()
    {
        return null;
    }

    public String[] getHeadUFDoubleField()
    {
        return null;
    }

    public ICompareRule[] getItemCompareRules(String tablecode)
    {
        return null;
    }

    public String[] getItemIntegerField(String tablecode)
    {
        return null;
    }

    public String[] getItemUFDoubleField(String tablecode)
    {
        return null;
    }

    public void runBatchClass(Container container, String s, String s1, AggregatedValueObject aaggregatedvalueobject[], Object aobj[])
        throws Exception
    {
    }

    public void runClass(Container parent, String billType, String actionName, AggregatedValueObject vo, Object obj)
        throws Exception
    {
        if(actionName.equals("WRITE") && !VOChecker.check(vo, this))
            throw new BusinessException(VOChecker.getErrorMessage());
        else
            return;
    }
}