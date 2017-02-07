package nc.vo.bankpub.ncsubject.ass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import nc.vo.bankpub.gl.AssVOs;
import nc.vo.bankpub.util.VOUtil;
import nc.vo.glcom.ass.AssVO;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class SubjAssConstrastFactory {

	public static SubjAssConstrastVO createDefaultSubjAssConstrast(AssVOs assVos) {
		SubjAssConstrastVO vo = new SubjAssConstrastVO();
		for (int i = 0; i < assVos.size(); i++) {
			vo.addAssVo(createSimpleSubjAssConstrastVo(assVos.get(i)));
		}
		return vo;
	}

	public static SubjAssConstrastVO createSubjAssConstrast(
			SimplSubjAssConstrastVO[] assVos) {
		if (assVos == null || assVos.length == 0)
			return null;
		SubjAssConstrastVO vo = new SubjAssConstrastVO();
		for (int i = 0; i < assVos.length; i++) {
			vo.addAssVo(assVos[i]);
		}
		vo.setBank_zh(assVos[0].getBankzh());
		vo.setBank_zhname(assVos[0].getBankzhname());
		vo.setPk_accsubj(assVos[0].getPk_accsubj());
		return vo;
	}

	public static SubjAssConstrastVO[] createSubjAssConstrasts(
			SimplSubjAssConstrastVO[] assVos) {
		if (assVos == null || assVos.length == 0)
			return null;

		Map<String, ArrayList<CircularlyAccessibleValueObject>> map = VOUtil
				.groupBy(assVos, new String[] { "pk_subjassconstrast_rowid" });
		
		Iterator<String> ite = map.keySet().iterator();
		ArrayList<SubjAssConstrastVO> voLst = new ArrayList<SubjAssConstrastVO>();
		while (ite.hasNext()) {
			ArrayList<CircularlyAccessibleValueObject> lst = map.get(ite.next());
			SubjAssConstrastVO vo = createSubjAssConstrast(lst.toArray(new SimplSubjAssConstrastVO[0]));
			voLst.add(vo);
		}
		
		return voLst.toArray(new SubjAssConstrastVO[0]);
		
	}

	public static SimplSubjAssConstrastVO createSimpleSubjAssConstrastVo(
			AssVO assVo) {
		SimplSubjAssConstrastVO vo = new SimplSubjAssConstrastVO();
		vo.setAsstypecode(assVo.getChecktypecode());
		vo.setAsstypename(assVo.getChecktypename());
		vo.setAssvaluecode(assVo.getCheckvaluecode());
		vo.setAssvaluename(assVo.getCheckvaluename());
		vo.setPk_asstype(assVo.getPk_Checktype());
		vo.setPk_assvalue(assVo.getPk_Checkvalue());
		return vo;
	}

}
