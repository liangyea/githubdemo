// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:41:17
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   MyBillVO.java

package nc.vo.bankpub.bankcontrast;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.trade.pub.HYBillVO;

// Referenced classes of package nc.vo.bankpub.bankcontrast:
//            BankNCContrastVO

public class MyBillVO extends HYBillVO
{

    public MyBillVO()
    {
    }

    public CircularlyAccessibleValueObject[] getChildrenVO()
    {
        return super.getChildrenVO();
    }

    public CircularlyAccessibleValueObject getParentVO()
    {
        return (BankNCContrastVO)super.getParentVO();
    }

    public void setChildrenVO(CircularlyAccessibleValueObject children[])
    {
        if(children == null || children.length == 0)
            super.setChildrenVO(null);
        else
            super.setChildrenVO(children);
    }

    public void setParentVO(CircularlyAccessibleValueObject parent)
    {
        super.setParentVO((BankNCContrastVO)parent);
    }
}