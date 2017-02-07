package nc.itf.bankpub.pub;

import nc.vo.bankpub.voucherelement.VoucherElementVO;
import nc.vo.pub.BusinessException;

public interface IVoucherElement {
	public void insertArrayVoucherElementVO(VoucherElementVO[] vos)throws BusinessException;
	public VoucherElementVO[] queryAllVoucherElementVOs() throws BusinessException;
	public void updateVoucherElementVOByVO(VoucherElementVO vo)throws BusinessException;
	public void deleteVoucherElementVOByVO(VoucherElementVO vo)throws BusinessException;
}
