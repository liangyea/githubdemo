package nc.itf.bankpub.pub;

import nc.bs.framework.common.NCLocator;

public class BankPubItfProxy {
	public static ISubjContrast getISubjItfProxy() {
		ISubjContrast ibc = (ISubjContrast) NCLocator.getInstance().lookup(
				ISubjContrast.class.getName());
		return ibc;
	}

}
