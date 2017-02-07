package nc.itf.bankpub.pub;

import nc.bs.framework.server.util.NewObjectService;
import nc.bs.logging.Logger;
import nc.itf.gl.voucher.IVoucher;
import nc.itf.gl.voucher.IVoucherAudit;
import nc.itf.gl.voucher.IVoucherTally;

public class GLItfProxy {

	/** �Դ˷��������Ķ������һ���򵥵Ķ��󣬲��ᵱ��ejb���������ejb������ƣ��ٶ����õķ���ʧ�ܣ����Ҳ������쳣��
	 * ���ᵼ����������ʧ�ܣ����ʹ��lookup�ķ�ʽ����ʹ�������쳣��Ҳ�ᵼ����������ʧ�� */
	public static IVoucher newVoucherInstance() {
		long begin = System.currentTimeMillis();
		IVoucher itf = (IVoucher) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucher");
		Logger.info("����ImpVoucher��ʱ��" + (System.currentTimeMillis() - begin));
		return itf;
	}

	public static IVoucherAudit newVoucherAuditInstance() {
		long begin = System.currentTimeMillis();
		IVoucherAudit itf = (IVoucherAudit) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucherAudit");
		Logger.info("����ImpVoucherAudit��ʱ��" + (System.currentTimeMillis() - begin));
		return itf;
	}

	public static IVoucherTally newVoucherTallyInstance() {
		long begin = System.currentTimeMillis();
		IVoucherTally itf = (IVoucherTally) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucherTally");
		Logger.info("����ImpVoucherTally��ʱ��" + (System.currentTimeMillis() - begin));
		return itf;
	}
}
