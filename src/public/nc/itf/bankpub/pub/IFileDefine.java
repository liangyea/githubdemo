package nc.itf.bankpub.pub;

import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.pub.BusinessException;

public interface IFileDefine {
	public void insertArrayFileDefineVO(FileDefineVO[] vos)throws BusinessException;
	public FileDefineVO[] queryAllFileDefineVOs() throws BusinessException;
	public void updateFileDefineVOByVO(FileDefineVO vo)throws BusinessException;
	public void deleteFileDefineVOByVO(FileDefineVO vo)throws BusinessException;
}
