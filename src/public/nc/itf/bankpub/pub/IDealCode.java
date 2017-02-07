package nc.itf.bankpub.pub;

import nc.vo.bankpub.dealcode.DealCodeVO;
import nc.vo.pub.BusinessException;

public interface IDealCode {

	public DealCodeVO[] queryDealCode()throws BusinessException;
	public void updateDealCode(DealCodeVO vo)throws BusinessException;
	public String queryDealCodeByIndex(String dealindex)throws BusinessException;
}
