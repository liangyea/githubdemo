// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:49:29
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   MyEventHandler.java

package nc.ui.bankpub.bankcontrast;

import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.pub.SuperVO;

// Referenced classes of package nc.ui.bankpub.bankcontrast:
//            AbstractMyEventHandler

public class MyEventHandler extends AbstractMyEventHandler
{

    public MyEventHandler(BillManageUI billUI, IControllerBase control)
    {
        super(billUI, control);
    }

    protected SuperVO[] loadInitVOs()
        throws Exception, ClassNotFoundException
    {
        String strWhere = " 1=1 and orient='1' ";
        SuperVO queryVos[] = getBusiDelegator().queryHeadAllData(Class.forName(getUIController().getBillVoName()[1]), getUIController().getBillType(), strWhere);
        return queryVos;
    }
}