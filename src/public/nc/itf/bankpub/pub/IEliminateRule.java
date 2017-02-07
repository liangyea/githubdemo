package nc.itf.bankpub.pub;

import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.pub.BusinessException;

public interface IEliminateRule {	
	public EliminateRuleVO[] queryAll() throws BusinessException;
	public void delByPK(String pk) throws BusinessException;
	public void add(EliminateRuleVO vo) throws BusinessException;
	public void update(EliminateRuleVO vo) throws BusinessException;
	public FileDefineVO[] getCols() throws BusinessException;
}
