package nc.itf.bankpub.pub;


import nc.vo.bankpub.respondcode.RespondCodeVO;
import nc.vo.pub.BusinessException;

public interface IRespondCode {
	public void insertArrayRespondCodeVO(RespondCodeVO[] vos)throws BusinessException;
	public RespondCodeVO[] queryAllRespondCodeVOs() throws BusinessException;
	public void updateRespondCodeVOByVO(RespondCodeVO vo)throws BusinessException;
	public void deleteRespondCodeVOByVO(RespondCodeVO vo)throws BusinessException;
	public String getRespondInfoByRespongCode(String respondcode)throws BusinessException;
}
