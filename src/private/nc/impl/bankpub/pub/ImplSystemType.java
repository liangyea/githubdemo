package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.ISystemType;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.pub.BusinessException;

public class ImplSystemType implements ISystemType {

	public void deleteSystemTypeVOByVO(SystemTypeVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return ;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			dao.deleteSystemTypeVOByVO(vo);
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		
	}

	public void insertArraySystemTypeVO(SystemTypeVO[] vos)
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

	public SystemTypeVO[] queryAllSystemTypeVOs() throws BusinessException {
		// TODO 自动生成方法存根
		SystemTypeVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryAllSystemTypeVOs();
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void updateSystemTypeVOByVO(SystemTypeVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		try{
			
			DataItfDAO  dao = new DataItfDAO();
			dao.updateSystemTypeVOByVO(vo);
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

}
