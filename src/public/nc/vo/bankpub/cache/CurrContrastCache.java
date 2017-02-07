package nc.vo.bankpub.cache;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.ICurrContrast;
import nc.vo.bankpub.currcontrast.CurrContrastVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;
import nc.vo.pub.BusinessException;

public class CurrContrastCache {

	/**
	 * 币种对照缓存 ok have update
	 */
	private static CurrContrastCache uniqueInstance = new CurrContrastCache();

	private CurrContrastCache() {
	}

	public static CurrContrastCache getInstance() {
		return uniqueInstance;
	}

	private final String[] TABLE_NAME = { "bank_currcontrast" };

	private final long TIME_OUT = 1000;

	private Map cache = new VersionSensitiveMap(CacheToMapAdapter
			.getInstance("nc.vo.bankpub.cache.CurrContrastCache"),
			new TableVersionMonitor(TABLE_NAME, TIME_OUT));

	public synchronized String getBankCurrCodeByPkCurrType(String pk_currtype)
			throws Exception {
		String bankcurrcode = null;
		Map currContrastMap = (Map) cache.get(TABLE_NAME[0]);// 币种对照MAP
		if (currContrastMap == null) {
			initCache();
		}
		currContrastMap = (Map) cache.get(TABLE_NAME[0]);
		CurrContrastVO ccvo = (CurrContrastVO) currContrastMap.get(pk_currtype);
		if (ccvo != null) {
			bankcurrcode = ccvo.bank_currcode.trim();
		}
		return bankcurrcode;
	}
	
	public synchronized String getPkCurrTypeByBankCurrCode(String bankcurrcode)
	throws Exception {
		String pk_currtype = null;
		Map currContrastMap = (Map) cache.get(TABLE_NAME[0]);// 币种对照MAP
		if (currContrastMap == null) {
			initCache();
		}
		currContrastMap = (Map) cache.get(TABLE_NAME[0]);
		CurrContrastVO ccvo = (CurrContrastVO) currContrastMap.get(bankcurrcode);
		if (ccvo != null) {
			pk_currtype = ccvo.getPk_currtype().trim();
		}
		return pk_currtype;
}

	public CurrContrastVO[] getAllContrastVos() throws BusinessException  {
		Map currContrastMap = (Map) cache.get(TABLE_NAME[0]);// 币种对照MAP
		if (currContrastMap == null) {
			initCache();
		}
		currContrastMap = (Map) cache.get(TABLE_NAME[0]);
		return (CurrContrastVO[]) currContrastMap.values().toArray(new CurrContrastVO[0]);
	}

	private void initCache() throws BusinessException {
		CurrContrastVO[] vos = null;
		ICurrContrast icc = (ICurrContrast)NCLocator.getInstance().lookup(ICurrContrast.class.getName());
		vos = (CurrContrastVO[]) icc.queryAll();
		Map currContrastMap = new HashMap();
		if (vos != null) {
			for (int i = 0; i < vos.length; i++) {
				if (vos[i].getPk_currtype() == null || vos[i].getBank_currcode() == null) continue;
				currContrastMap.put(vos[i].getPk_currtype(), vos[i]);
				currContrastMap.put(vos[i].getBank_currcode().trim(), vos[i]);
			}
		}
		cache.put(TABLE_NAME[0], currContrastMap);

	}

	public void updateVersion() {
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String key = TABLE_NAME[0];
		cv.updateVersion(key);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
