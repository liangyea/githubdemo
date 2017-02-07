package nc.itf.bankpub.pub;

import nc.bs.framework.server.util.NewObjectService;
import nc.bs.logging.Logger;
import nc.itf.gl.voucher.IVoucher;
import nc.itf.gl.voucher.IVoucherAudit;
import nc.itf.gl.voucher.IVoucherTally;

public class GLItfProxy {

	/** 以此方法创建的对象就是一个简单的对象，不会当作ejb，不会参与ejb事务控制，假定调用的方法失败，而且捕获了异常，
	 * 不会导致整个事务失败，如果使用lookup的方式，则即使捕获了异常，也会导致整个事务失败 */
	public static IVoucher newVoucherInstance() {
		long begin = System.currentTimeMillis();
		IVoucher itf = (IVoucher) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucher");
		Logger.info("创建ImpVoucher耗时：" + (System.currentTimeMillis() - begin));
		return itf;
	}

	public static IVoucherAudit newVoucherAuditInstance() {
		long begin = System.currentTimeMillis();
		IVoucherAudit itf = (IVoucherAudit) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucherAudit");
		Logger.info("创建ImpVoucherAudit耗时：" + (System.currentTimeMillis() - begin));
		return itf;
	}

	public static IVoucherTally newVoucherTallyInstance() {
		long begin = System.currentTimeMillis();
		IVoucherTally itf = (IVoucherTally) NewObjectService.newInstance("gl",
				"nc.impl.gl.voucher.ImpVoucherTally");
		Logger.info("创建ImpVoucherTally耗时：" + (System.currentTimeMillis() - begin));
		return itf;
	}
}
