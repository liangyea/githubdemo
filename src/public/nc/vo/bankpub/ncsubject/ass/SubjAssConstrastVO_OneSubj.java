package nc.vo.bankpub.ncsubject.ass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.vo.bankpub.util.CompareUtil;
import nc.vo.bankpub.util.StringUtils;
import nc.vo.glcom.ass.AssVO;

public class SubjAssConstrastVO_OneSubj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pk_accsubj;

	private List<SubjAssConstrastVO> subjAssConstrasts = new ArrayList<SubjAssConstrastVO>();

	public void add(SubjAssConstrastVO assVo) {
		subjAssConstrasts.add(assVo);
	}

	public void add(SubjAssConstrastVO[] subjAssConstrasts) {
		for (int i = 0; i < subjAssConstrasts.length; i++) {
			this.subjAssConstrasts.add(subjAssConstrasts[i]);
		}

	}

	public int size() {
		return subjAssConstrasts.size();
	}

	public SubjAssConstrastVO get(int i) {
		return subjAssConstrasts.get(i);
	}

	public void remove(int i) {
		subjAssConstrasts.remove(i);
	}

	public SubjAssConstrastVO findBy(String bankzh) {
		int i = indexBy(pk_accsubj, bankzh);
		if (i >= 0) {
			return get(i);
		}
		return null;
	}

	public String findBankzhBy(AssVO[] assVos) { 
		SubjAssConstrastVO vo = findBy(assVos);
		if (vo != null) return vo.getBank_zh();
		return null;
	}
	
	public SubjAssConstrastVO findBy(AssVO[] assVos) {
		if (assVos == null)
			return null;
		SubjAssConstrastVO vo = null;
		for (int i = 0; i < size(); i++) {
			boolean isMatch = true;
			vo = get(i);
			for (int ii = 0; ii < assVos.length; ii++) {
				if (assVos[ii] == null || StringUtils.isNull(assVos[ii].getPk_Checkvalue())) {
					continue;
				}
				SimplSubjAssConstrastVO simpVo = vo.getAssVo(assVos[ii]
						.getChecktypename());
				// 表示不需要用这个辅助核算来匹配
				if (simpVo == null
						|| StringUtils.isNull(simpVo.getPk_assvalue())) {
					continue;
				}
				if (!CompareUtil.equals(simpVo.getPk_assvalue(), assVos[ii]
						.getPk_Checkvalue())) {
					isMatch = false;
				}
			}
			if (isMatch) {
				return vo;
			}
		}
		return vo;
	}

	public int indexBy(String pk_accsubj, String bankzh) {
		for (int i = 0; i < subjAssConstrasts.size(); i++) {
			SubjAssConstrastVO assVo = get(i);
			if (CompareUtil.equals(pk_accsubj, assVo.getPk_accsubj())
					&& CompareUtil.equals(bankzh, assVo.getBank_zh()))
				return i;
		}
		return -1;
	}

	// public SubjAssConstrastVO findBy(String asstypename) {
	// int i = indexBy(asstypename);
	//		
	// if (i >= 0) {
	// return get(i);
	// }
	// return null;
	// }

	// public int indexBy(String asstypename) {
	// for (int i = 0; i < subjAssConstrasts.size(); i++) {
	// SubjAssConstrastVOs assVo = get(i);
	// if (CompareUtil.equals(asstypename, assVo.getChecktypename()))
	// return i;
	// }
	// return -1;
	// }

	public SubjAssConstrastVO[] toArray() {
		return subjAssConstrasts.toArray(new SubjAssConstrastVO[0]);
	}

	public String getPk_accsubj() {
		return pk_accsubj;
	}

	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}
}
