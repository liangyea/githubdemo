package nc.itf.bankpub.pub;


import nc.vo.bankpub.subjbalrule.SubjBalRuleVO;
import nc.vo.pub.BusinessException;

public interface ISubjBalRuleSet {
	/** �Ӻ��Ĵ�NC��Ŀ���ձ����ɶ��˹��� */
	public SubjBalRuleVO[] importRules(String pk_glorgbook) throws BusinessException;

}
