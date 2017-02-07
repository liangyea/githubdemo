// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package nc.vo.hr.tools.dbtool.ds.impl.filter;

import nc.vo.hr.tools.dbtool.ds.DataRow;


public class DefaultRowFilter
    implements nc.vo.hr.tools.dbtool.ds.IRowFilter
{

    public DefaultRowFilter()
    {
    }

	public boolean isRecordVisible(DataRow row) {		
		return row.getState()==DataRow.STATE_DEFAULT;
	}

}
