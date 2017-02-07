package nc.vo.bankpub.ncsubject.ass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.vo.bankpub.util.StringUtils;
import nc.vo.pub.ValidationException;
import nc.vo.pub.ValueObject;

public class SubjAssConstrastVO extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/** 会计科目 */
	private String pk_accsubj;

	/** 核心账户 */
	private String bank_zh;

	/** 核心帐户名称 */
	private String bank_zhname;

	
	private String pk_subjassconstrast_rowid;
	/** 辅助核算 */
	private Map<String, SimplSubjAssConstrastVO> subjAssConstrastMap = new HashMap<String, SimplSubjAssConstrastVO>();

	public void addAssVo(SimplSubjAssConstrastVO assVo) {
		subjAssConstrastMap.put(assVo.getAsstypename(), assVo);
	}

	public SimplSubjAssConstrastVO getAssVo(String asstypename) {
		if (asstypename == null) return null;
		return subjAssConstrastMap.get(asstypename);
	}

	public String getValue(String asstypename) {
		SimplSubjAssConstrastVO assVo = subjAssConstrastMap.get(asstypename);
		if (assVo != null)
			return assVo.getPk_assvalue();
		return null;
	}

	public String getBank_zh() {
		return bank_zh;
	}

	public void setBank_zh(String bank_zh) {
		this.bank_zh = bank_zh;
	}

	public String getPk_accsubj() {
		return pk_accsubj;
	}

	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}

	public boolean hasAssItem() {
		return subjAssConstrastMap.size() > 0;
	}

	public boolean hasAssValue() {
		Iterator<String> keys = subjAssConstrastMap.keySet().iterator();
		while (keys.hasNext()) {
			SimplSubjAssConstrastVO vo = subjAssConstrastMap.get(keys.next());
			if (!StringUtils.isNull(vo.getPk_assvalue())) {
				return true;
			}
		}
		return false;
	}

	public String toString_value() {
		Iterator<String> keys = subjAssConstrastMap.keySet().iterator();
		ArrayList<String> resultLst = new ArrayList<String>();
		while (keys.hasNext()) {
			SimplSubjAssConstrastVO vo = subjAssConstrastMap.get(keys.next());
			resultLst.add(vo.getPk_asstype());
			resultLst.add(vo.getPk_assvalue());
		}
		resultLst.add(getBank_zh());
		return resultLst.toString();
	}

	public String getBank_zhname() {
		return bank_zhname;
	}

	public void setBank_zhname(String bank_zhname) {
		this.bank_zhname = bank_zhname;
	}

	public SimplSubjAssConstrastVO[] toAssArray() {
		SimplSubjAssConstrastVO[] simpVos = subjAssConstrastMap.values()
				.toArray(new SimplSubjAssConstrastVO[0]);
		for (int i = 0; i < simpVos.length; i++) {
			simpVos[i].setPk_accsubj(pk_accsubj);
			simpVos[i].setBankzh(bank_zh);
			simpVos[i].setBankzhname(bank_zhname);
			simpVos[i].setPk_subjassconstrast_rowid(pk_subjassconstrast_rowid);
		}
		return simpVos;
	}

	public SimplSubjAssConstrastVO[] getValidSubjAssConstrasts() {
		ArrayList<SimplSubjAssConstrastVO> lst = new ArrayList<SimplSubjAssConstrastVO>();
		SimplSubjAssConstrastVO[] simpVos = toAssArray();
		for (int i = 0; i < simpVos.length; i++) {
			if (simpVos[i].isNull()) {
				continue;
			}
			lst.add(simpVos[i]);
		}
		return lst.toArray(new SimplSubjAssConstrastVO[0]);
	}

	public Collection<SimplSubjAssConstrastVO> toAssCollect() {
		// 赋会计科目、核心账户等
		toAssArray();
		return subjAssConstrastMap.values();
	}

	public String getPk_subjassconstrast_rowid() {
		return pk_subjassconstrast_rowid;
	}

	public void setPk_subjassconstrast_rowid(String pk_subjassconstrast_rowid) {
		this.pk_subjassconstrast_rowid = pk_subjassconstrast_rowid;
	}

}
