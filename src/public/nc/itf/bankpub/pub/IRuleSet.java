package nc.itf.bankpub.pub;

import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;

public interface IRuleSet {
	/*
	 * ����ϵͳ���ѯ����*/
	public RuleSetVO[] queryBySystemPK(String fk_system) throws Exception;
	
	/*
	 * ����ϵͳ���ɾ��*/
	public void delBySystemPK(String fk_system) throws Exception;
	
	/*
	 * ���ݹ�������ɾ��*/
	public void delByPK(String pk_ruleset) throws Exception;
	
	/*
	 * �����¹���*/
	public void insert(RuleSetVO vo) throws Exception;
	
	/*
	 * ���¹���*/
	public void update(RuleSetVO vo) throws Exception;
	
	/*
	 * ɾ�������ù���ֵ*/
	public void delValue(String pk_eliminaterule) throws Exception;
	
	public void insValue(EliminateRuleVO vo) throws Exception;
	
	public EliminateRuleVO[] queryValues(String fk_ruleset) throws Exception;
	
	public FileDefineVO[] queryCols(String fk_ruleset) throws Exception;
}
