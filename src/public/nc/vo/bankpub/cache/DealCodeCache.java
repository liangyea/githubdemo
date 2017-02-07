package nc.vo.bankpub.cache;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IDealCode;
import nc.vo.bankpub.dealcode.DealCodeVO;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;

/**
 * 
 * 传核心交易编码缓存 ok have update
 */
public class DealCodeCache {

	private static DealCodeCache uniqueInstance = new DealCodeCache();

	private DealCodeCache() {
	}

	public static DealCodeCache getInstance() {
		return uniqueInstance;
	}

	private final String[] TABLE_NAME = { "bank_dealcode" };

	private final long TIME_OUT = 1000;

	private Map cache = new VersionSensitiveMap(CacheToMapAdapter
			.getInstance("nc.vo.bankpub.cache.DealCodeCache"),
			new TableVersionMonitor(TABLE_NAME, TIME_OUT));
	

	public synchronized String getDealCodeByDealIndex(String dealIndex)
	throws Exception {
		String dealcode = null;
		Map dealcodeMap = (Map) cache.get(TABLE_NAME[0]);
		if (dealcodeMap == null) {
			initCache();			
		}
		dealcodeMap = (Map) cache.get(TABLE_NAME[0]);
		Object o =  dealcodeMap.get(dealIndex);
		if (o != null) {
			dealcode = o.toString().trim();
		}
		return dealcode;
}
	private void initCache() throws Exception {
		IDealCode ibc = (IDealCode)NCLocator.getInstance().lookup(IDealCode.class.getName());
		DealCodeVO[] vos = null;
		vos = (DealCodeVO[]) ibc.queryDealCode();
		Map dealcodeMap = new HashMap();
		if (vos != null) {
			for (int i = 0; i < vos.length; i++) {
				if (vos[i].getDealcode() == null) continue;
				dealcodeMap.put(vos[i].getDealindex(),vos[i].getDealcode());				
			}

		}
		cache.put(TABLE_NAME[0], dealcodeMap);
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
