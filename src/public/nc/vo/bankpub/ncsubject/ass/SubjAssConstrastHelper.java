package nc.vo.bankpub.ncsubject.ass;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uap.oid.OidGenerator;
import nc.ui.gl.datacache.SubjAssDataCache;
import nc.vo.bankpub.util.StringUtils;
import nc.vo.bd.b02.GlSubjAssVO;
import nc.vo.bd.b02.SubjassVO;
import nc.vo.glcom.ass.AssVO;

public class SubjAssConstrastHelper {
//	
	public static SubjAssConstrastVO[] fectchNorowIdVos(SubjAssConstrastVO[] srcVos) {
		if (srcVos == null) return null;
		List<SubjAssConstrastVO> lst = new ArrayList<SubjAssConstrastVO>();
		for (int i = 0; i < srcVos.length; i++) {
			if (StringUtils.isNull(srcVos[i].getPk_subjassconstrast_rowid())) {
				lst.add(srcVos[i]);
			}
		}
		return lst.toArray(new SubjAssConstrastVO[0]);
	}
	
	public static void fillRowIdsIfNo(SubjAssConstrastVO[] srcVos) {
		if (srcVos == null) return;
		List<SubjAssConstrastVO> lst = new ArrayList<SubjAssConstrastVO>();
		for (int i = 0; i < srcVos.length; i++) {
			if (StringUtils.isNull(srcVos[i].getPk_subjassconstrast_rowid())) {
				srcVos[i].setPk_subjassconstrast_rowid(OidGenerator.getInstance().nextOid());
			}
		}
		
	}
	public static SimplSubjAssConstrastVO[] catSimplSubjAssConstrasts(
			SubjAssConstrastVO[] subjAssConstrasts) {
		if (subjAssConstrasts == null)
			return null;
		List<SimplSubjAssConstrastVO> collect = new ArrayList<SimplSubjAssConstrastVO>();
		for (int i = 0; i < subjAssConstrasts.length; i++) {
			collect.addAll(subjAssConstrasts[i].toAssCollect());
		}
		return collect.toArray(new SimplSubjAssConstrastVO[0]);
	}

	public static SimplSubjAssConstrastVO[] fetchValidConstrasts(
			SimplSubjAssConstrastVO[] srcVos) {
		if (srcVos == null)
			return null;
		List<SimplSubjAssConstrastVO> lst = new ArrayList<SimplSubjAssConstrastVO>();
		for (int i = 0; i < srcVos.length; i++) {
			if (srcVos[i].isNull()) {
				continue;
			}
			lst.add(srcVos[i]);
		}

		return lst.toArray(new SimplSubjAssConstrastVO[0]);
	}

	
	public static SimplSubjAssConstrastVO[] fecthValitConstrasts(SubjAssConstrastVO[] subjAssConstrasts) {
		return fetchValidConstrasts(catSimplSubjAssConstrasts(subjAssConstrasts));
	}
	
	public static AssVO[] getAssVosBy(String pk_accsubj) {
		GlSubjAssVO assVos = SubjAssDataCache.getInstance().getAsssVOBySubjPKs(
				pk_accsubj);
		if (assVos == null || assVos.getAssVos() == null) {
			return null;
		} else {
			SubjassVO[] subjassVos = (SubjassVO[]) assVos.getAssVos().toArray(
					new SubjassVO[0]);
			AssVO[] assVosOther = new AssVO[subjassVos.length];
			for (int i = 0; i < assVosOther.length; i++) {
				assVosOther[i] = new AssVO();
				assVosOther[i].setPk_Checktype(subjassVos[i].getPk_bdinfo());
				assVosOther[i].setChecktypecode(subjassVos[i].getBdcode());
				assVosOther[i].setChecktypename(subjassVos[i].getBdname());

			}
			return assVosOther;
		}
	}
}
