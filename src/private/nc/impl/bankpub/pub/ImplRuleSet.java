package nc.impl.bankpub.pub;

import nc.itf.bankpub.pub.IRuleSet;
import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.pub.BusinessException;

public class ImplRuleSet implements IRuleSet {

	public void delByPK(String pk_ruleset) throws BusinessException {
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.delByPK(pk_ruleset);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}

	public void delBySystemPK(String fk_system) throws BusinessException {
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.delBySystemPK(fk_system);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}

	public void insert(RuleSetVO vo) throws BusinessException {
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.insert(vo);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}

	public RuleSetVO[] queryBySystemPK(String fk_system) throws BusinessException {
		RuleSetVO[] vos = null;
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			vos = dmo.queryBySystemPK(fk_system);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	      return vos;
	}
	
	public void update(RuleSetVO vo) throws Exception
	{
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.update(vo);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}
	
	public void delValue(String pk_eliminaterule) throws Exception
	{
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.delValue(pk_eliminaterule);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}
	
	public void insValue(EliminateRuleVO vo) throws Exception
	{
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			dmo.insValue(vo);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	}
	
	public FileDefineVO[] queryCols(String fk_ruleset) throws Exception
	{
		FileDefineVO[] vos = null;
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			vos = dmo.queryCols(fk_ruleset);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	      return vos;
	}
	
	public EliminateRuleVO[] queryValues(String fk_ruleset) throws Exception
	{
		EliminateRuleVO[] vos = null;
		try {
			RuleSetDMO dmo = new RuleSetDMO();
			vos = dmo.queryValues(fk_ruleset);
	      } catch (Exception e) {
	          throw new nc.vo.pub.BusinessException("Remote Call", e);
	      }
	      return vos;
	}

}
