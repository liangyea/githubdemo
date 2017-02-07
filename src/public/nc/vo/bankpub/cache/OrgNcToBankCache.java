package nc.vo.bankpub.cache;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.vo.bankpub.orgnctobank.OrgNcToBankVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;
import nc.vo.pub.BusinessException;

/**
 * 
 * 机构对照缓存 ok have update
 */
public class OrgNcToBankCache {

	private static OrgNcToBankCache uniqueInstance = new OrgNcToBankCache();

	private OrgNcToBankCache() {
	}

	public static OrgNcToBankCache getInstance() {
		return uniqueInstance;
	}

	private final String[] TABLE_NAME = { "bank_orgnctobank" };

	private final long TIME_OUT = 1000;// * 10 * 60;

	private Map cache = new VersionSensitiveMap(CacheToMapAdapter
			.getInstance("nc.vo.bankpub.cache.OrgNcToBankCache"),
			new TableVersionMonitor(TABLE_NAME, TIME_OUT));

	public synchronized OrgNcToBankVO getOrgNcToBankVOByBankCode(
			String BankCode) throws BusinessException {
		checkCacheData();
		Map[] contrastMaps = (Map[]) cache.get(TABLE_NAME[0]);
		return (OrgNcToBankVO) contrastMaps[1].get(BankCode);
	}

	public synchronized OrgNcToBankVO getOrgNcToBankVOByPkdeptdoc(
			String pk_deptdoc) throws BusinessException {
		checkCacheData();
		Map[] contrastMaps = (Map[]) cache.get(TABLE_NAME[0]);
		return (OrgNcToBankVO) contrastMaps[0].get(pk_deptdoc);
	}

	public synchronized String[] getAllPkdeptdocs() throws BusinessException {
		checkCacheData();
		Map[] contrastMaps = (Map[]) cache.get(TABLE_NAME[0]);
		return (String[]) contrastMaps[0].keySet().toArray(new String[0]);
	}

	public synchronized OrgNcToBankVO[] getAllOrgNcToBanks() throws BusinessException {
		checkCacheData();
		Map[] contrastMaps = (Map[]) cache.get(TABLE_NAME[0]);
		return (OrgNcToBankVO[]) contrastMaps[0].values().toArray(new OrgNcToBankVO[0]);
	}
	
	private void initCache() throws BusinessException {
		IUifService iuiService = (IUifService) NCLocator.getInstance().lookup(IUifService.class.getName());
		OrgNcToBankVO[] vos = (OrgNcToBankVO[]) iuiService.queryAll(OrgNcToBankVO.class);
		Map[] ncBankContrastMaps = new HashMap[2];
		ncBankContrastMaps[0] = new HashMap();
		ncBankContrastMaps[1] = new HashMap();
		if (vos != null) {
			for (int i = 0; i < vos.length; i++) {
				if (vos[i].getPk_deptdoc() == null
						|| vos[i].getBankcode() == null)
					continue;
//				List list = (ArrayList) ncBankContrastMaps[0].get(vos[i]
//						.getPk_glorgbook());
//				if (list == null) {
//					list = new ArrayList();
//					ncBankContrastMaps[0].put(vos[i].getPk_glorgbook(), list);
//				}
//				list.add(vos[i]);
				ncBankContrastMaps[0].put(vos[i].getPk_deptdoc(), vos[i]);
				ncBankContrastMaps[1].put(vos[i].getBankcode().trim(), vos[i]);
			}
		}
		cache.put(TABLE_NAME[0], ncBankContrastMaps);
	}

	public void updateVersion() {
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String key = TABLE_NAME[0];
		cv.updateVersion(key);
	}
	
	private void checkCacheData() throws BusinessException{
		Map[] bankContrastMap = (Map[]) cache.get(TABLE_NAME[0]);
		if (bankContrastMap == null) {
			initCache();
		}
	}

	public static void main(String[] args) {

	}

}
