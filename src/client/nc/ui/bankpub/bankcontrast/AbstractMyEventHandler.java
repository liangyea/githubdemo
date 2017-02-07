// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:43:21
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   AbstractMyEventHandler.java

package nc.ui.bankpub.bankcontrast;

import nc.ui.pub.bduifactory.BaseManageEHD;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;

public class AbstractMyEventHandler extends BaseManageEHD
{

    public AbstractMyEventHandler(BillManageUI billUI, IControllerBase control)
    {
        super(billUI, control);
    }

    protected void onBoElse(int i)
        throws Exception
    {
    }
}