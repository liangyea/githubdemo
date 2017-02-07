package nc.vo.bankpub.cache;

import java.util.HashMap;

import nc.bs.logging.Logger;
import nc.vo.glcom.tools.GLPubProxy;

public class DetailAssDataCache {
	public static DetailAssDataCache instance = new DetailAssDataCache();
	private static HashMap id_cache = new HashMap();
//	private static HashMap vos_cache = new HashMap();
/**
 * 此处插入方法说明。
 * 创建日期：(2003-9-4 12:01:18)
 */
public void clear() {
	id_cache.clear();
//	vos_cache.clear();

}
/**
 * 此处插入方法说明。
 * 创建日期：(2003-11-26 11:39:16)
 * @return java.lang.String
 * @param vos nc.vo.glcom.ass.AssVO[]
 */
//public nc.vo.glcom.ass.AssVO[] getAssvosByID(String id) {
//	AssVO[] vos = null;
//	vos = (AssVO[]) vos_cache.get(id);
//	if (id == null || id.length() == 0) {
//		return null;
//	}
//	if (vos == null || vos.length == 0) {
//		try {
//			vos = GLPubProxy.getRemoteFreevaluePub().queryAssvosByid(id);
//			AssVO[] newass = new AssVO[vos.length];
//			for (int i = 0; i < vos.length; i++) {
//				newass[i] =(AssVO) vos[i].clone();
//			}
//			vos_cache.put(id, newass);
//			StringBuffer s = new StringBuffer();
//			for (int i = 0; i < vos.length; i++) {
//				s.append(vos[i].getPk_Checktype());
//				s.append(vos[i].getPk_Checkvalue());
//			}
//			id_cache.put(s.toString().trim(), id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	return vos;
//}
/**
 * 此处插入方法说明。
 * 创建日期：(2003-11-26 11:39:16)
 * @return java.lang.String
 * @param vos nc.vo.glcom.ass.AssVO[]
 */
public String getIdByAssvos(nc.vo.glcom.ass.AssVO[] vos) {
	StringBuffer voskey = new StringBuffer();
	for (int i = 0; i < vos.length; i++) {
		voskey.append(vos[i].getPk_Checktype());
		voskey.append(vos[i].getPk_Checkvalue());
	}
	String freevalueid = (String) id_cache.get(voskey.toString().trim());

	if (freevalueid == null) {
		try {
			freevalueid = GLPubProxy.getRemoteFreevaluePub().getAssID(vos, new Boolean(true));
			id_cache.put(voskey.toString().trim(), freevalueid);
//			vos_cache.put(freevalueid, vos);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}

		return freevalueid;
	} else {
		return freevalueid;
	}

}
/**
 * 此处插入方法说明。
 * 创建日期：(2002-7-1 11:28:34)
 * @return nc.ui.gl.vouchertools.VoucherDataCenter
 */
public static DetailAssDataCache getInstance() {
	return instance;
}
}
