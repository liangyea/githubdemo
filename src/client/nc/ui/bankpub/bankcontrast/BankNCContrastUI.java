// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:44:15
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankNCContrastUI.java

package nc.ui.bankpub.bankcontrast;

import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.CircularlyAccessibleValueObject;

// Referenced classes of package nc.ui.bankpub.bankcontrast:
//            AbstractBankNCContrastUI, MyEventHandler

public class BankNCContrastUI extends AbstractBankNCContrastUI
{

    public BankNCContrastUI()
    {
    }

    protected ManageEventHandler createEventHandler()
    {
        return new MyEventHandler(this, getUIControl());
    }

    public void setBodySpecialData(CircularlyAccessibleValueObject acircularlyaccessiblevalueobject[])
        throws Exception
    {
    }

    protected void setHeadSpecialData(CircularlyAccessibleValueObject circularlyaccessiblevalueobject, int i)
        throws Exception
    {
    }

    protected void setTotalHeadSpecialData(CircularlyAccessibleValueObject acircularlyaccessiblevalueobject[])
        throws Exception
    {
    }

    protected void initSelfData()
    {
    }

    public void setDefaultData()
        throws Exception
    {
    }
}