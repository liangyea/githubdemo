// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:40:24
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   AbstractBankNCContrastUI.java

package nc.ui.bankpub.bankcontrast;

import nc.ui.pub.bduifactory.BaseManageUI;
import nc.ui.pub.linkoperate.ILinkQuery;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.button.ButtonVOFactory;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.button.ButtonVO;

// Referenced classes of package nc.ui.bankpub.bankcontrast:
//            BankNCContrastUICtrl, MyDelegator, BankNCContrastUICheckRuleGetter

public abstract class AbstractBankNCContrastUI extends BaseManageUI
    implements ILinkQuery
{

    public AbstractBankNCContrastUI()
    {
    }

    protected AbstractManageController createController()
    {
        return new BankNCContrastUICtrl();
    }

    protected BusinessDelegator createBusinessDelegator()
    {
        return new MyDelegator();
    }

    protected void initPrivateButton()
    {
        int listButns[] = getUIControl().getListButtonAry();
        boolean hasCommit = false;
        boolean hasAudit = false;
        boolean hasCancelAudit = false;
        for(int i = 0; i < listButns.length; i++)
        {
            if(listButns[i] == 28)
                hasCommit = true;
            if(listButns[i] == 26)
                hasAudit = true;
            if(listButns[i] == 27)
                hasCancelAudit = true;
        }

        int cardButns[] = getUIControl().getCardButtonAry();
        for(int i = 0; i < cardButns.length; i++)
        {
            if(cardButns[i] == 28)
                hasCommit = true;
            if(cardButns[i] == 26)
                hasAudit = true;
            if(cardButns[i] == 27)
                hasCancelAudit = true;
        }

        if(hasCommit)
        {
            ButtonVO btnVo = ButtonVOFactory.getInstance().build(28);
            btnVo.setBtnCode(null);
            addPrivateButton(btnVo);
        }
        if(hasAudit)
        {
            ButtonVO btnVo2 = ButtonVOFactory.getInstance().build(26);
            btnVo2.setBtnCode(null);
            addPrivateButton(btnVo2);
        }
        if(hasCancelAudit)
        {
            ButtonVO btnVo3 = ButtonVOFactory.getInstance().build(27);
            btnVo3.setBtnCode(null);
            addPrivateButton(btnVo3);
        }
    }

    public Object getUserObject()
    {
        return new BankNCContrastUICheckRuleGetter();
    }

    public void doQueryAction(ILinkQueryData querydata)
    {
        String billId = querydata.getBillID();
        if(billId != null)
            try
            {
                setCurrentPanel("CARDPANEL");
                AggregatedValueObject vo = loadHeadData(billId);
                getBufferData().addVOToBuffer(vo);
                setListHeadData(new CircularlyAccessibleValueObject[] {
                    vo.getParentVO()
                });
                getBufferData().setCurrentRow(getBufferData().getCurrentRow());
                setBillOperate(6);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
    }
}