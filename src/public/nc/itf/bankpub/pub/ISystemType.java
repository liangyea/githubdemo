package nc.itf.bankpub.pub;

import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.pub.BusinessException;

public interface ISystemType {
	public void insertArraySystemTypeVO(SystemTypeVO[] vos)throws BusinessException;
	public SystemTypeVO[] queryAllSystemTypeVOs() throws BusinessException;
	public void updateSystemTypeVOByVO(SystemTypeVO vo)throws BusinessException;
	public void deleteSystemTypeVOByVO(SystemTypeVO vo)throws BusinessException;
}
