// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:46:58
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankNCContrastUICtrl.java

package nc.ui.bankpub.bankcontrast;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bill.ISingleController;

public class BankNCContrastUICtrl extends AbstractManageController
    implements ISingleController
{

    public BankNCContrastUICtrl()
    {
    }

    public String[] getCardBodyHideCol()
    {
        return null;
    }

    public int[] getCardButtonAry()
    {
        return (new int[] {
            1, 3, 0, 7, 32, 31, 8
        });
    }

    public int[] getListButtonAry()
    {
        return (new int[] {
            1, 3, 0, 7, 32, 30, 8
        });
    }

    public boolean isShowCardRowNo()
    {
        return false;
    }

    public boolean isShowCardTotal()
    {
        return false;
    }

    public String getBillType()
    {
        return "8800";
    }

    public String[] getBillVoName()
    {
        return (new String[] {
            nc.vo.bankpub.bankcontrast.MyBillVO.class.getName(), nc.vo.bankpub.bankcontrast.BankNCContrastVO.class.getName(), nc.vo.bankpub.bankcontrast.BankNCContrastVO.class.getName()
        });
    }

    public String getBodyCondition()
    {
        return null;
    }

    public String getBodyZYXKey()
    {
        return null;
    }

    public int getBusinessActionType()
    {
        return 1;
    }

    public String getChildPkField()
    {
        return null;
    }

    public String getHeadZYXKey()
    {
        return null;
    }

    public String getPkField()
    {
        return null;
    }

    public Boolean isEditInGoing()
        throws Exception
    {
        return null;
    }

    public boolean isExistBillStatus()
    {
        return false;
    }

    public boolean isLoadCardFormula()
    {
        return false;
    }

    public String[] getListBodyHideCol()
    {
        return null;
    }

    public String[] getListHeadHideCol()
    {
        return null;
    }

    public boolean isShowListRowNo()
    {
        return false;
    }

    public boolean isShowListTotal()
    {
        return false;
    }

    public boolean isSingleDetail()
    {
        return false;
    }
}