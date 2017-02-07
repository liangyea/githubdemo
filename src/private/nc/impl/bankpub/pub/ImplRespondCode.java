package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IRespondCode;
import nc.vo.bankpub.respondcode.RespondCodeVO;
import nc.vo.pub.BusinessException;

public class ImplRespondCode implements IRespondCode {

	public void deleteRespondCodeVOByVO(RespondCodeVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return;
		}
		try{
			DataDAO dao = new DataDAO();
			dao.deleteRespondCodeVOByVO(vo);
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void insertArrayRespondCodeVO(RespondCodeVO[] vos)
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

	public RespondCodeVO[] queryAllRespondCodeVOs() throws BusinessException {
		// TODO 自动生成方法存根
		RespondCodeVO[] revos = null;
		
		try{
			DataDAO dao = new DataDAO();
			revos  = dao.queryAllRespondCodeVOs();
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		
		
		return revos;
	}

	public void updateRespondCodeVOByVO(RespondCodeVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return;
		}
		try{
			DataDAO dao = new DataDAO();
			dao.updateRespondCodeVOByVO(vo);
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public String getRespondInfoByRespongCode(String respondcode) throws BusinessException {
		// TODO 自动生成方法存根
		if(respondcode==null){
			return null;
		}
		String respondinfo = null;
		
		try{
			DataDAO dao = new DataDAO();

		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		
		
		return respondinfo;
	}

}
