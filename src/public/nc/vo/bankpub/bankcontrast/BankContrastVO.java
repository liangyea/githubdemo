// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 23:39:55
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   BankContrastVO.java

package nc.vo.bankpub.bankcontrast;

import nc.vo.pub.SuperVO;

public class BankContrastVO extends SuperVO
{

    public BankContrastVO()
    {
        pk_bankcontrast = null;
        pk_glorgbook = null;
        bankbm = null;
        bankmc = null;
        clerkcode = null;
        pk_system = null;
        orient = null;
        pk_deptdoc = null;
        pwd = "";
        terminalno = null;
        vdef1 = null;
        vdef2 = null;
        vdef3 = null;
        vdef4 = null;
        vdef5 = null;
        vdef6 = null;
        glorgbookcode = null;
        glorgbookname = null;
        deptcode = null;
        deptname = null;
    }

    public String getPKFieldName()
    {
        return "pk_bankcontrast";
    }

    public String getParentPKFieldName()
    {
        return null;
    }

    public String getTableName()
    {
        return "bank_bankcontrast";
    }

    public String getBankbm()
    {
        return bankbm;
    }

    public void setBankbm(String bankbm)
    {
        this.bankbm = bankbm;
    }

    public String getBankmc()
    {
        return bankmc;
    }

    public void setBankmc(String bankmc)
    {
        this.bankmc = bankmc;
    }

    public String getClerkcode()
    {
        return clerkcode;
    }

    public void setClerkcode(String clerkcode)
    {
        this.clerkcode = clerkcode;
    }

    public String getOrient()
    {
        return orient;
    }

    public void setOrient(String orient)
    {
        this.orient = orient;
    }

    public String getPk_bankcontrast()
    {
        return pk_bankcontrast;
    }

    public void setPk_bankcontrast(String pk_bankcontrast)
    {
        this.pk_bankcontrast = pk_bankcontrast;
    }

    public String getPk_glorgbook()
    {
        return pk_glorgbook;
    }

    public void setPk_glorgbook(String pk_glorgbook)
    {
        this.pk_glorgbook = pk_glorgbook;
    }

    public String getPk_system()
    {
        return pk_system;
    }

    public void setPk_system(String pk_system)
    {
        this.pk_system = pk_system;
    }

    public String getPk_deptdoc()
    {
        return pk_deptdoc;
    }

    public void setPk_deptdoc(String pk_deptdoc)
    {
        this.pk_deptdoc = pk_deptdoc;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getTerminalno()
    {
        return terminalno;
    }

    public void setTerminalno(String terminalno)
    {
        this.terminalno = terminalno;
    }

    public String getVdef1()
    {
        return vdef1;
    }

    public void setVdef1(String vdef1)
    {
        this.vdef1 = vdef1;
    }

    public String getVdef2()
    {
        return vdef2;
    }

    public void setVdef2(String vdef2)
    {
        this.vdef2 = vdef2;
    }

    public String getVdef3()
    {
        return vdef3;
    }

    public void setVdef3(String vdef3)
    {
        this.vdef3 = vdef3;
    }

    public String getVdef4()
    {
        return vdef4;
    }

    public void setVdef4(String vdef4)
    {
        this.vdef4 = vdef4;
    }

    public String getVdef5()
    {
        return vdef5;
    }

    public void setVdef5(String vdef5)
    {
        this.vdef5 = vdef5;
    }

    public String getVdef6()
    {
        return vdef6;
    }

    public void setVdef6(String vdef6)
    {
        this.vdef6 = vdef6;
    }

    public String pk_bankcontrast;
    public String pk_glorgbook;
    public String bankbm;
    public String bankmc;
    public String clerkcode;
    public String pk_system;
    public String orient;
    public String pk_deptdoc;
    public String pwd;
    public String terminalno;
    public String vdef1;
    public String vdef2;
    public String vdef3;
    public String vdef4;
    public String vdef5;
    public String vdef6;
    public String glorgbookcode;
    public String glorgbookname;
    public String deptcode;
    public String deptname;
}