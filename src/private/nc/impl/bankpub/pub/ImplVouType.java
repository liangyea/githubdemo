package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IVouType;
import nc.vo.bankpub.vouchertype.VouTypeVO;
import nc.vo.pub.BusinessException;

public class ImplVouType implements IVouType {


	public void insertArrayVouTypeVO(VouTypeVO[] vos) throws BusinessException {
		// TODO 自动生成方法存根
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

	public VouTypeVO[] queryAllVouTypeVOs() throws BusinessException {
		// TODO 自动生成方法存根
		VouTypeVO[] vos = null;
		try{
			DataDAO dao = new DataDAO();
			vos = dao.queryAllVouTypeVOs();
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void updateVouTypeVOByVO(VouTypeVO vo) throws BusinessException {
		// TODO 自动生成方法存根
		try{
			
			DataDAO dao = new DataDAO();
			dao.updateVouTypeVOByVO(vo);
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

}
