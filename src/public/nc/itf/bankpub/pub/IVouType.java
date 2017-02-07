package nc.itf.bankpub.pub;


import nc.vo.bankpub.vouchertype.VouTypeVO;
import nc.vo.pub.BusinessException;

public interface IVouType {
	public void insertArrayVouTypeVO(VouTypeVO[] vos)throws BusinessException;
	public VouTypeVO[] queryAllVouTypeVOs() throws BusinessException;
	public void updateVouTypeVOByVO(VouTypeVO vo)throws BusinessException;


}
