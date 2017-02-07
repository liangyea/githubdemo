package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IEspecialSubj;
import nc.vo.bankpub.especialsubj.EspecialSubjVO;
import nc.vo.pub.BusinessException;

public class ImplEspecialSubj implements IEspecialSubj {

	public void deleteEspecialSubjVOByVO(EspecialSubjVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return;
		}
		try{
			DataDAO dao = new DataDAO();
			dao.deleteEspecialSubjVOByVO(vo);
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void insertArrayEspecialSubjVO(EspecialSubjVO[] vos)
			throws BusinessException {
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

	public EspecialSubjVO[] queryAllEspecialSubjVOs() throws BusinessException {
		// TODO 自动生成方法存根
		EspecialSubjVO[] vos = null;
		
		try{
			DataDAO dao = new DataDAO();
			vos = dao.queryAllEspecialSubjVOs();
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void updateEspecialSubjVOByVO(EspecialSubjVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return;
		}
		try{
			
			DataDAO dao = new DataDAO();
			dao.updateEspecialSubjVOByVO(vo);
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

}
