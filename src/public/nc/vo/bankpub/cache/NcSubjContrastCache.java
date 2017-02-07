package nc.vo.bankpub.cache;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dbcache.intf.ICacheVersionBS;
import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.ISubjContrast;
import nc.ui.bd.BDGLOrgBookAccessor;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.bd.access.IBdinfoConst;
import nc.vo.cache.ext.CacheToMapAdapter;
import nc.vo.cache.ext.TableVersionMonitor;
import nc.vo.cache.ext.VersionSensitiveMap;
import nc.vo.gl.pubvoucher.DetailVO;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

/**
 * 
 * 外系统科目对照系统缓存 have update
 */
public class NcSubjContrastCache {

	/**
	 * 余额对账科目对照缓存 ok
	 */
	private static NcSubjContrastCache uniqueInstance = new NcSubjContrastCache();

	private NcSubjContrastCache() {
	}

	public static NcSubjContrastCache getInstance() {
		return uniqueInstance;
	}

	private final String[] TABLE_NAME = {"bank_ncsubjcontrast"};

	private final long TIME_OUT = 1000;

	private Map cache = new VersionSensitiveMap(CacheToMapAdapter
			.getInstance("nc.vo.bankpub.cache.NcSubjContrastCache"),
			new TableVersionMonitor(TABLE_NAME, TIME_OUT));

	// 根据NC科目PK和分录的部门辅助核算取核心账号
	public synchronized String getBankAccountBySubjAndDetail(DetailVO detail, String pk_deptdoc)
			throws Exception {
		String bankaccount = null;
		Map subContrastMap = (Map) cache.get(TABLE_NAME[0]);
		if (subContrastMap == null) {
			initCache();
		}
		subContrastMap = (Map) cache.get(TABLE_NAME[0]);
		Map deptMap = (Map) subContrastMap.get(detail.m_pk_accsubj);
		bankaccount = (String) deptMap.get(pk_deptdoc);
		if (bankaccount == null || bankaccount.trim().contains("#")) {
			String pk_corp = BDGLOrgBookAccessor.getPk_corp(detail.m_pk_glorgbook);
			IBDAccessor accessor = BDAccessor.getBDAccessor(
					IBdinfoConst.DEPTDOC, OrgnizeTypeVO.COMPANY_TYPE, pk_corp);
			BddataVO[] fathers = accessor.getFatherDocs(pk_deptdoc);
			if (fathers != null) {
				for (int i = 0; i < fathers.length; i++) {
					if (deptMap.containsKey(fathers[i].getPk())) {
						bankaccount = (String) deptMap.get(fathers[i].getPk());
						if (bankaccount != null
								&& !bankaccount.trim().contains("#"))
							break;
					}
				}
			}
		}
		return bankaccount;
	}
	
	 public synchronized String getBankSubjcodeByPkaccsubj(String pk_accsubj)
     throws Exception
	 {
	     String bank_subjcode = null;
	     Map subContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
	     if(subContrastMap == null)
	         initCache();
	     subContrastMap = (Map)(Map)cache.get(TABLE_NAME[0]);
	     Object o = subContrastMap.get(pk_accsubj);
	     if(o != null)
	         bank_subjcode = o.toString().trim();
	     return bank_subjcode;
	 }


	 public void initCache()
     throws Exception
	 {
	     ISubjContrast ibc = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
	     NCSubjContrastVO vos[] = (NCSubjContrastVO[])null;
	     vos = ibc.queryAllSubjContrast();
	     Map subContrastMap = new HashMap();
	     if(vos != null)
	     {
	         for(int i = 0; i < vos.length; i++)
	             if(vos[i].getPk_accsubj() != null && vos[i].getBank_kmbm() != null && !"#".equals(vos[i].getBank_kmbm()))
	                 subContrastMap.put(vos[i].getPk_accsubj(), vos[i].getBank_kmbm().trim());
	
	     }
	     cache.put(TABLE_NAME[0], subContrastMap);
	 }


	public void updateVersion() {
		// 账簿级的版本维护,用于缓存判断
		ICacheVersionBS cv = (ICacheVersionBS) NCLocator.getInstance().lookup(
				"nc.bs.dbcache.intf.ICacheVersionBS");
		String key = TABLE_NAME[0];
		cv.updateVersion(key);
/*		String[] key1=TABLE_NAME;
		cv.updateVersions(key1);*/
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
