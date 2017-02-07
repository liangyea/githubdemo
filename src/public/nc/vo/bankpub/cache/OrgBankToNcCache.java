package nc.vo.bankpub.cache;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.vo.bankpub.orgbanktonc.OrgBankToNcVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;
import nc.vo.pub.BusinessException;

/**
 * 
 * 机构对照缓存 ok have update
 */
public class OrgBankToNcCache {

	private static OrgBankToNcCache uniqueInstance = new OrgBankToNcCache();

	private OrgBankToNcCache() {
	}

	public static OrgBankToNcCache getInstance() {
		return uniqueInstance;
	}

	private final String[] TABLE_NAME = { "bank_ncbankcontrast" };

	private final long TIME_OUT = 1000;// * 10 * 60;

	private Map cache = new VersionSensitiveMap(CacheToMapAdapter
			.getInstance("nc.vo.bankpub.cache.OrgBankToNcCache"),
			new TableVersionMonitor(TABLE_NAME, TIME_OUT));

	public synchronized OrgBankToNcVO getOrgBankToNcVOByBankCode(
			String BankCode) throws BusinessException {
		checkCacheData();
		Map<String, OrgBankToNcVO> contrastMaps = (Map<String, OrgBankToNcVO>) cache.get(TABLE_NAME[0]);
		return (OrgBankToNcVO) contrastMaps.get(BankCode);
	}

	public synchronized OrgBankToNcVO[] getAllOrgBankToNcVos() throws BusinessException {
		checkCacheData();
		Map<String, OrgBankToNcVO> contrastMaps = (Map<String, OrgBankToNcVO>) cache.get(TABLE_NAME[0]);
		return (OrgBankToNcVO[]) contrastMaps.values().toArray(new OrgBankToNcVO[0]);
	}
	
	private void initCache() throws BusinessException {
		IUifService iuiService = (IUifService) NCLocator.getInstance().lookup(IUifService.class.getName());
		OrgBankToNcVO[] vos = (OrgBankToNcVO[]) iuiService.queryAll(OrgBankToNcVO.class);
		Map<String, OrgBankToNcVO> banktoncvosMap = new HashMap<String, OrgBankToNcVO>();
		if (vos != null) {
			for (int i = 0; i < vos.length; i++) {
				banktoncvosMap.put(vos[i].bankcode.trim(), vos[i]);
			}
		}
		cache.put(TABLE_NAME[0], banktoncvosMap);
	}

	public void updateVersion() {
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String key = TABLE_NAME[0];
		cv.updateVersion(key);
	}
	
	private void checkCacheData() throws BusinessException{
		Map<String, OrgBankToNcVO> orgbanktoncvosMap = (Map<String, OrgBankToNcVO>) cache.get(TABLE_NAME[0]);
		if (orgbanktoncvosMap == null) {
			initCache();
		}
	}

	public static void main(String[] args) {

	}

}
