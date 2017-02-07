package nc.itf.bankpub.pub;


import nc.vo.bankpub.subjbalrule.SubjBalRuleVO;
import nc.vo.pub.BusinessException;

public interface ISubjBalRuleSet {
	/** 从核心传NC科目对照表生成对账规则 */
	public SubjBalRuleVO[] importRules(String pk_glorgbook) throws BusinessException;

}
