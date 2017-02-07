// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:41:28
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   AbstractMyDelegator.java

package nc.ui.bankpub.bankcontrast;

import java.util.Hashtable;

import nc.ui.trade.bsdelegate.BDBusinessDelegator;

public abstract class AbstractMyDelegator extends BDBusinessDelegator
{

    public AbstractMyDelegator()
    {
    }

    public Hashtable loadChildDataAry(String tableCodes[], String key)
        throws Exception
    {
        Hashtable dataHashTable = new Hashtable();
        return dataHashTable;
    }

    public String getBodyCondition(Class bodyClass, String key)
    {
        return null;
    }
}