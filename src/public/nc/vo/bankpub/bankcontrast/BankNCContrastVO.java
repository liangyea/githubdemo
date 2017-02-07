// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:40:32
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankNCContrastVO.java

package nc.vo.bankpub.bankcontrast;

import java.util.ArrayList;

import nc.vo.pub.NullFieldException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.ValidationException;

public class BankNCContrastVO extends SuperVO
{

    public String getBankbm()
    {
        return bankbm;
    }

    public void setBankbm(String newBankbm)
    {
        bankbm = newBankbm;
    }

    public String getBankmc()
    {
        return bankmc;
    }

    public void setBankmc(String newBankmc)
    {
        bankmc = newBankmc;
    }

    public String getOrient()
    {
        return orient;
    }

    public void setOrient(String newOrient)
    {
        orient = newOrient;
    }

    public String getPk_deptdoc()
    {
        return pk_deptdoc;
    }

    public void setPk_deptdoc(String newPk_deptdoc)
    {
        pk_deptdoc = newPk_deptdoc;
    }

    public String getPk_bankcontrast()
    {
        return pk_bankcontrast;
    }

    public void setPk_bankcontrast(String newPk_bankcontrast)
    {
        pk_bankcontrast = newPk_bankcontrast;
    }

    public String getPk_glorgbook()
    {
        return pk_glorgbook;
    }

    public void setPk_glorgbook(String newPk_glorgbook)
    {
        pk_glorgbook = newPk_glorgbook;
    }

    public String getClerkcode()
    {
        return clerkcode;
    }

    public void setClerkcode(String newClerkcode)
    {
        clerkcode = newClerkcode;
    }

    public String getPk_system()
    {
        return pk_system;
    }

    public void setPk_system(String newPk_system)
    {
        pk_system = newPk_system;
    }

    public void validate()
        throws ValidationException
    {
        ArrayList errFields = new ArrayList();
        StringBuffer message = new StringBuffer();
        message.append("下列字段不能为空:");
        if(errFields.size() > 0)
        {
            String temp[] = (String[])errFields.toArray(new String[0]);
            message.append(temp[0]);
            for(int i = 1; i < temp.length; i++)
            {
                message.append(",");
                message.append(temp[i]);
            }

            throw new NullFieldException(message.toString());
        } else
        {
            return;
        }
    }

    public String getParentPKFieldName()
    {
        return null;
    }

    public String getPKFieldName()
    {
        return "pk_bankcontrast";
    }

    public String getTableName()
    {
        return "BANK_BANKCONTRAST";
    }

    public BankNCContrastVO()
    {
    }

    public BankNCContrastVO(String newPk_bankcontrast)
    {
        pk_bankcontrast = newPk_bankcontrast;
    }

    public String getPrimaryKey()
    {
        return pk_bankcontrast;
    }

    public void setPrimaryKey(String newPk_bankcontrast)
    {
        pk_bankcontrast = newPk_bankcontrast;
    }

    public String getEntityName()
    {
        return "BANK_BANKCONTRAST";
    }

    public String bankbm;
    public String bankmc;
    public String orient;
    public String pk_deptdoc;
    public String pk_bankcontrast;
    public String pk_glorgbook;
    public String clerkcode;
    public String pk_system;
    public static final String BANKBM = "bankbm";
    public static final String BANKMC = "bankmc";
    public static final String ORIENT = "orient";
    public static final String PK_DEPTDOC = "pk_deptdoc";
    public static final String PK_BANKCONTRAST = "pk_bankcontrast";
    public static final String PK_GLORGBOOK = "pk_glorgbook";
    public static final String CLERKCODE = "clerkcode";
    public static final String PK_SYSTEM = "pk_system";
}