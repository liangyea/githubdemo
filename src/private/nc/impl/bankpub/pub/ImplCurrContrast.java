package nc.impl.bankpub.pub;

import nc.bs.logging.Logger;
import nc.bs.pub.SuperDMO;
import nc.itf.bankpub.pub.ICurrContrast;
import nc.vo.bankpub.cache.CurrContrastCache;
import nc.vo.bankpub.currcontrast.CurrContrastVO;
import nc.vo.pub.BusinessException;

public class ImplCurrContrast implements ICurrContrast {

	public CurrContrastVO[] queryAll() throws BusinessException {
		// TODO �Զ����ɷ������
        CurrContrastVO [] ccvos = null;
        try{
            SuperDMO dmo = new SuperDMO();
            ccvos = (CurrContrastVO []) dmo.queryAll(CurrContrastVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException (e.getMessage());
        }
        return ccvos;
	}

	public void saveAll(CurrContrastVO[] ccvos) throws BusinessException {
		// TODO �Զ����ɷ������
	       try {
	            SuperDMO dmo = new SuperDMO();
	            for (int i = 0; i < ccvos.length; i++) {
	                if(ccvos[i].getPk_currcontrast()==null || ccvos[i].getPk_currcontrast().trim().length()==0){
	                    dmo.insert(ccvos[i]);
	                } else {
	                    dmo.update(ccvos[i]);
	                }
	            }
	           Logger.info("========���±��ֶ��ջ���========");
	           CurrContrastCache.getInstance().updateVersion();
	           Logger.info("====End====���±��ֶ��ջ���========");
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BusinessException(e.getMessage());
	        }
	}

}
