package nc.vo.bankpub.cache;

import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankSubjContrast;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.ElementVersionSensitiveMap;
import nc.vo.cache.ext.ICacheVersionMonitor;
import nc.vo.cache.ext.ObjectCacheVersionMonitor;
import nc.vo.cache.ext.VersionMonitorFactory;

/**
 * 
 * 外系统科目对照系统缓存 ok
 */
public class BankSubjContrastCache {
	public final String uniqueKey = "bank_banksubjcontrast";

	private static BankSubjContrastCache uniqueInstance = new BankSubjContrastCache();

	private BankSubjContrastCache() {
	}

	public static BankSubjContrastCache getInstance() {
		return uniqueInstance;
	}

	private Map cache = new ElementVersionSensitiveMap(
			CacheToMapAdapter
					.getInstance("nc.vo.bankpub.cache.BankSubjContrastCache"),
			new BankSubjVersionMonitorFactory());

	private final static long TIME_OUT = 1000;

	class BankSubjVersionMonitorFactory implements VersionMonitorFactory {
		public ICacheVersionMonitor createVersionMonitor(Object key) {
			String pk_glorgbook = (String) key;
			return new ObjectCacheVersionMonitor(pk_glorgbook, TIME_OUT);
		}
	}

	private void initCache(String pk_glorgbook) throws Exception {
		IBankSubjContrast ib = (IBankSubjContrast)NCLocator.getInstance().lookup(IBankSubjContrast.class.getName());
		BankSubjContrastVO[] vos = ib
				.queryBankSubjContrastVOByPkglorgbook(pk_glorgbook);
		if (vos != null) {
			cache.put(pk_glorgbook + uniqueKey, vos);
		}
	}

	public synchronized BankSubjContrastVO[] getBankSubjContrastVOsByKey(
			String pk_glorgbook) throws Exception {
		BankSubjContrastVO[] vos = (BankSubjContrastVO[]) cache.get(pk_glorgbook
				+ uniqueKey);
		if (vos == null) {
			initCache(pk_glorgbook);
			vos = (BankSubjContrastVO[]) cache.get(pk_glorgbook + uniqueKey);
		}
		return vos;
	}
	
	public String getPKaccsubjByConditin(String pk_glorgbook,String bankaccsubj) throws Exception {
		String pk_accsubj = "";
		BankSubjContrastVO[] vos = getBankSubjContrastVOsByKey(pk_glorgbook);
		int lent = vos ==null?0:vos.length;
		for(int i= 0;i<lent;i++){
			
		}

		return pk_accsubj;
	}

	public void updateVersion(BankSubjContrastVO vo) {
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String key = vo.getPk_glorgbook() + uniqueKey;
		cv.updateVersion(key);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
