package nc.impl.bankpub.pub;

import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IDealCode;
import nc.vo.bankpub.cache.DealCodeCache;
import nc.vo.bankpub.dealcode.DealCodeVO;
import nc.vo.pub.BusinessException;

public class ImplDealCode implements IDealCode {

	public DealCodeVO[] queryDealCode() throws BusinessException {
		// TODO 自动生成方法存根
		DealCodeVO[] vos = null;
		try{
			DataDAO dao = new DataDAO();
			vos = dao.queryDealCode();
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		
		return vos;
	}

	public void updateDealCode(DealCodeVO vo) throws BusinessException {
		// TODO 自动生成方法存根
		if(vo==null){
			return ;
			
		}
		try{
			DataDAO dao = new DataDAO();
			dao.updateDealCode(vo);
			Logger.info("========传核心交易编码缓存========");
			DealCodeCache.getInstance().updateVersion();
			Logger.info("====End====传核心交易编码缓存========");
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public String queryDealCodeByIndex(String dealindex) throws BusinessException {
		// TODO 自动生成方法存根
		if(dealindex==null){
			return null;
		}
		String dealcode = null;
		try{
			DataDAO dao = new DataDAO();
			dealcode = dao.queryDealCodeByIndex(dealindex);
		
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return dealcode;
	}


}
