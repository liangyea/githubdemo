package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IVoucherElement;
import nc.vo.bankpub.voucherelement.VoucherElementVO;
import nc.vo.pub.BusinessException;

public class ImplVoucherElement implements IVoucherElement {

	public void deleteVoucherElementVOByVO(VoucherElementVO vo)
			throws BusinessException {
		// TODO �Զ����ɷ������
		if(vo==null){
			return ;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			dao.deleteVoucherElementVOByVO(vo);
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void insertArrayVoucherElementVO(VoucherElementVO[] vos)
			throws BusinessException {
		// TODO �Զ����ɷ������
		if(vos==null || vos.length==0){
			return;
		}
		try{
			
			BaseDAO  dao = new BaseDAO();
			dao.insertVOArray(vos);
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public VoucherElementVO[] queryAllVoucherElementVOs()
			throws BusinessException {
		// TODO �Զ����ɷ������
		VoucherElementVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryAllVoucherElementVOs();
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void updateVoucherElementVOByVO(VoucherElementVO vo)
			throws BusinessException {
		// TODO �Զ����ɷ������
		try{
			
			DataItfDAO  dao = new DataItfDAO();
			dao.updateVoucherElementVOByVO(vo);
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

}
