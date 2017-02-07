package nc.vo.bankpub.cache;

import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.BankPubItfProxy;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO_OneSubj;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.ElementVersionSensitiveMap;
import nc.vo.cache.ext.ICacheVersionMonitor;
import nc.vo.cache.ext.ObjectCacheVersionMonitor;
import nc.vo.cache.ext.VersionMonitorFactory;
import nc.vo.glcom.ass.AssVO;

public class SubjAssConstrastCache {
	public final String uniqueKey = "bank_banksubjcontrast";

	private static SubjAssConstrastCache uniqueInstance = new SubjAssConstrastCache();

	private SubjAssConstrastCache() {
	}

	public static SubjAssConstrastCache getInstance() {
		return uniqueInstance;
	}

	private Map cache = new ElementVersionSensitiveMap(
			CacheToMapAdapter
					.getInstance("nc.vo.bankpub.cache.SubjAssConstrastCache"),
			new SubjAssConstrastVersionMonitorFactory());

	private final static long TIME_OUT = 1000 * 60 * 10;

	class SubjAssConstrastVersionMonitorFactory implements VersionMonitorFactory {
		public ICacheVersionMonitor createVersionMonitor(Object key) {
//			String pk_glorgbook = (String) key;
			return new ObjectCacheVersionMonitor((String) key, TIME_OUT);
		}
	}

	private synchronized void initCache(String pk_accsubj) throws Exception {
		SubjAssConstrastVO[] vos = BankPubItfProxy.getISubjItfProxy().querySubjAssConstrastBy(pk_accsubj);
		SubjAssConstrastVO_OneSubj vo = new SubjAssConstrastVO_OneSubj();
		if (vos != null) {
			vo.add(vos);
			cache.put(pk_accsubj + uniqueKey, vo);
		}
		
	}

	public String findBankzhBy(String pk_accsubj, AssVO[] assVos) throws Exception {
		return getSubjAssConstrastsBy(pk_accsubj).findBankzhBy(assVos);
	}
	
	public synchronized SubjAssConstrastVO_OneSubj getSubjAssConstrastsBy(
			String pk_accsubj) throws Exception {
		SubjAssConstrastVO_OneSubj vos = (SubjAssConstrastVO_OneSubj) cache.get(pk_accsubj
				+ uniqueKey);
		if (vos == null) {
			initCache(pk_accsubj);
			vos = (SubjAssConstrastVO_OneSubj) cache.get(pk_accsubj + uniqueKey);
		}
		return vos;
	}
	
//	public String getPKaccsubjByConditin(String pk_glorgbook,String bankaccsubj) throws Exception {
//		String pk_accsubj = "";
//		BankSubjContrastVO[] vos = getBankSubjContrastVOsByKey(pk_glorgbook);
//		int lent = vos ==null?0:vos.length;
//		for(int i= 0;i<lent;i++){
//			
//		}
//
//		return pk_accsubj;
//	}

	public void updateVersions(SubjAssConstrastVO[] vos) {
		if (vos == null) return;
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String[] keys = new String[vos.length];
		for (int i = 0; i < vos.length; i++) {
			keys[i] = vos[i].getPk_accsubj() + uniqueKey;
		}
		
		cv.updateVersions(keys);
	}
}
