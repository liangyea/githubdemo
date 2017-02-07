package nc.vo.bankpub.cache;

import nc.itf.uap.busibean.SysinitAccessor;
import nc.ui.gl.datacache.CurrencyDataCache;
import nc.vo.bd.b21.CurrinfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

public class CurrCache {
	public static final String PARA_LOCAL_CURR = "BD301";

	public static final String PARA_LOCAL_CURR_GROUP = "BD211";

	public static final String RMB = "00010000000000000001";

	/** 取本位币主键 */
	public static String getLocalCurr(String pk_glorgbook)
			throws BusinessException {
		// 公司本位币
		SysInitVO initVo = SysinitAccessor.getInstance().getParaValue(
				OrgnizeTypeVO.ZHUZHANG_TYPE, pk_glorgbook, "BD301");
		if (initVo == null) {
			// 集团本位币
			initVo = SysinitAccessor.getInstance().getParaValue(
					OrgnizeTypeVO.COMPANY_TYPE, "0001", "BD211");
		}
		String pk_localCurr = null;
		if (initVo != null) {
			pk_localCurr = initVo.getPkvalue();
		}
		if (pk_localCurr == null || pk_localCurr.trim().length() == 0) {
			pk_localCurr = RMB;
		}
		return pk_localCurr;

	}

	public static UFDouble getRate(String pk_corp, String pk_localCurr,
			String pk_destCurr, UFDate date) throws BusinessException {
		CurrinfoVO currInfo =
				 CurrencyDataCache.getInstance().getCurrVO(pk_corp,
							pk_localCurr, pk_destCurr);
				
		//tian		
		/*CurrinfoQuery.getInstance().getCurrinfo(pk_corp,
				pk_localCurr, pk_destCurr);*/
		UFDouble currRate =
				CurrencyDataCache.getInstance().getCurrrate(pk_corp, pk_localCurr, pk_destCurr, date.toString());
				//tian
				/*CurrrateQuery.getInstance().getCurrrate(
				currInfo, date);*/
		if (currRate == null || currRate.equals(0)) {
			return null;
		}
		return currRate;
	}

}
