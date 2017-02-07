// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:45:52
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankNCContrastUICheckRuleGetter.java

package nc.ui.bankpub.bankcontrast;

import java.io.Serializable;

import nc.vo.trade.pub.IBDGetCheckClass2;

public class BankNCContrastUICheckRuleGetter
    implements IBDGetCheckClass2, Serializable
{

    public BankNCContrastUICheckRuleGetter()
    {
    }

    public String getUICheckClass()
    {
        return "nc.ui.bankpub.bankcontrast.BankNCContrastUICheckRule";
    }

    public String getCheckClass()
    {
        return "nc.vo.bankpub.bankcontrast.BankContrastBusiChecker";
    }
}