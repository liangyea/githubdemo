package nc.itf.bankpub.pub;

import nc.vo.bankpub.especialsubj.EspecialSubjVO;
import nc.vo.pub.BusinessException;

public interface IEspecialSubj {
	public void insertArrayEspecialSubjVO(EspecialSubjVO[] vos)throws BusinessException;
	public EspecialSubjVO[] queryAllEspecialSubjVOs() throws BusinessException;
	public void updateEspecialSubjVOByVO(EspecialSubjVO vo)throws BusinessException;
	public void deleteEspecialSubjVOByVO(EspecialSubjVO vo)throws BusinessException;
}
