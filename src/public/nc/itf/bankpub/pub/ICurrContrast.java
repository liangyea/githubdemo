package nc.itf.bankpub.pub;

import nc.vo.bankpub.currcontrast.CurrContrastVO;
import nc.vo.pub.BusinessException;
public interface ICurrContrast {
	public CurrContrastVO [] queryAll() throws BusinessException;
	public void saveAll(CurrContrastVO [] ccvos) throws BusinessException;
}
