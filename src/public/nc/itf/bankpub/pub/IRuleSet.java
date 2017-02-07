package nc.itf.bankpub.pub;

import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;

public interface IRuleSet {
	/*
	 * 根据系统外查询规则*/
	public RuleSetVO[] queryBySystemPK(String fk_system) throws Exception;
	
	/*
	 * 根据系统外键删除*/
	public void delBySystemPK(String fk_system) throws Exception;
	
	/*
	 * 根据规则主键删除*/
	public void delByPK(String pk_ruleset) throws Exception;
	
	/*
	 * 插入新规则*/
	public void insert(RuleSetVO vo) throws Exception;
	
	/*
	 * 更新规则*/
	public void update(RuleSetVO vo) throws Exception;
	
	/*
	 * 删除已设置过滤值*/
	public void delValue(String pk_eliminaterule) throws Exception;
	
	public void insValue(EliminateRuleVO vo) throws Exception;
	
	public EliminateRuleVO[] queryValues(String fk_ruleset) throws Exception;
	
	public FileDefineVO[] queryCols(String fk_ruleset) throws Exception;
}
