package nc.impl.bankpub.pub;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IFileDefine;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.pub.BusinessException;

public class ImplFileDefine implements IFileDefine {

	public void deleteFileDefineVOByVO(FileDefineVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return ;
		}
		try{
			DataItfDAO dao = new DataItfDAO();
			dao.deleteFileDefineVOByVO(vo);
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void insertArrayFileDefineVO(FileDefineVO[] vos)
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

	public FileDefineVO[] queryAllFileDefineVOs() throws BusinessException {
		// TODO 自动生成方法存根
		FileDefineVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryAllFileDefineVOs();
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;

	}

	public void updateFileDefineVOByVO(FileDefineVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		try{
			
			DataItfDAO  dao = new DataItfDAO();
			dao.updateFileDefineVOByVO(vo);
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

}
