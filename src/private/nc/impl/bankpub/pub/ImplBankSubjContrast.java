package nc.impl.bankpub.pub;

import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.IBankSubjContrast;
import nc.vo.bankpub.cache.BankSubjContrastCache;
import nc.vo.bankpub.subjcolshow.SubjColShowVO;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.pub.BusinessException;

public class ImplBankSubjContrast implements IBankSubjContrast {
	

	public void deleteBankSubjContrastVO(BankSubjContrastVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo ==null){
			return;
		}
		try{
			DataItfDAO  dao = new DataItfDAO();
			dao.deleteBankSubjContrastVO(vo);
			Logger.info("========更新外系统科目对照系统缓存========");
			BankSubjContrastCache.getInstance().updateVersion(vo);
			Logger.info("====End====更新外系统科目对照系统缓存========");
	      
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void insertBankSubjContrastVOs(BankSubjContrastVO[] vos)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vos==null || vos.length==0){
			return;
		}
		try{
			
			BaseDAO  dao = new BaseDAO();
			dao.insertVOArray(vos);
			Logger.info("========更新外系统科目对照系统缓存========");
			
			BankSubjContrastCache.getInstance().updateVersion(vos[0]);
			Logger.info("====End====更新外系统科目对照系统缓存========");
	      
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public BankSubjContrastVO[] queryAllBankSubjContrastVO()
			throws BusinessException {
		// TODO 自动生成方法存根
		BankSubjContrastVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryAllBankSubjContrastVO();
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void updateBankSubjContrastVO(BankSubjContrastVO vo)
			throws BusinessException {
		// TODO 自动生成方法存根
		if(vo == null){
			return ;
		}
		try{
			DataItfDAO  dao = new DataItfDAO();
			dao.updateBankSubjContrastVO(vo);
			Logger.info("========更新外系统科目对照系统缓存========");
			BankSubjContrastCache.getInstance().updateVersion(vo);
			Logger.info("====End====更新外系统科目对照系统缓存========");
	      
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public SubjColShowVO[] queryAllSubjColShowVO() throws BusinessException {
		// TODO 自动生成方法存根
		SubjColShowVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryAllSubjColShowVO();
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
		return vos;
	}

	public void insertSubjColShowVO(SubjColShowVO vo) throws BusinessException {
		// TODO 自动生成方法存根
		try{
			BaseDAO  dao = new BaseDAO();

			dao.insertVO(vo);
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public void updateSubjColShowVO(SubjColShowVO vo) throws BusinessException {
		// TODO 自动生成方法存根
		if(vo == null){
			return ;
		}
		try{
			DataItfDAO  dao = new DataItfDAO();
			dao.updateSubjColShowVO(vo);
			
		}catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
	    }
	}

	public String queryBankSubjContrastVOByVO(BankSubjContrastVO vo) throws BusinessException {
		// TODO 自动生成方法存根
		if(vo == null){
			return null;
		}
		String pk_accsubj = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			pk_accsubj = dao.queryBankSubjContrastVOByVO(vo);
			
		}catch(Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
		}
		return pk_accsubj;
	}

	public void copySubjcontrast(String pk_system , String standard, String[] target) throws BusinessException {
		// TODO 自动生成方法存根
    	try
    	{
    		if(target==null||target.length == 0)
    			return;
    		BankSubjContrastVO vo = new BankSubjContrastVO();
    		vo.setPk_system(pk_system);
    		vo.setPk_glorgbook(standard);
    		DataItfDAO  dao = new DataItfDAO();
    		BankSubjContrastVO[] banksubjvos =  dao.queryBankSubjContrastVOsByVO(vo);
    		if(banksubjvos==null || banksubjvos.length==0){
    			throw new Exception("基准账簿档案为空");
    		}
    		// 删除目标账本的科目对照
    		deleteBankSubjByPksystempkglorgbook(pk_system,target);
    		Vector v = new Vector();
    		ImplPubTools tool = new ImplPubTools();
    		for(int i=0;i<target.length;i++){
    			BankSubjContrastVO[] subjclone = new BankSubjContrastVO[banksubjvos.length];
    			for(int j=0;j<subjclone.length;j++){
    				String subjcode = tool.getStrByPk("bd_accsubj","subjcode","pk_accsubj",banksubjvos[j].getPk_accsubj());
    				String pk_accsubj = tool.getPksubjByCorpAndCode(target[i], subjcode);
    				subjclone[j] = new BankSubjContrastVO();
    				subjclone[j].setPk_glorgbook(target[i]);
    				subjclone[j].setPk_system(banksubjvos[j].getPk_system());
    				subjclone[j].setPk_accsubj(pk_accsubj);
    				subjclone[j].setDef1(banksubjvos[j].getDef1());
    				subjclone[j].setDef2(banksubjvos[j].getDef2());
    				subjclone[j].setDef3(banksubjvos[j].getDef3());
    				subjclone[j].setDef4(banksubjvos[j].getDef4());
    				subjclone[j].setDef5(banksubjvos[j].getDef5());
    				v.addElement(subjclone[j]);
    			}
    		}
    		BankSubjContrastVO[] vos =null;
    		if(v.size()>0){
    			vos =  new BankSubjContrastVO[v.size()];
    			v.copyInto(vos);
    		}
    		
    		insertBankSubjContrastVOs(vos);
    		
    	}
    	catch(Exception e)
    	{
    		throw new BusinessException(e.getMessage());
    	}
	}

	public void deleteBankSubjByPksystempkglorgbook(String pk_system, String[] pk_glorgbooks) throws BusinessException {
		// TODO 自动生成方法存根
		if(pk_system==null || pk_glorgbooks==null || pk_glorgbooks.length==0){
			return ;
		}
		try{
			DataItfDAO  dao = new DataItfDAO();
			dao.deleteBankSubjByPksystempkglorgbook(pk_system, pk_glorgbooks);
			
		}catch(Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
		}
	}

	public BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(String pk_glorgbook) throws BusinessException {
		// TODO 自动生成方法存根
		if(pk_glorgbook==null){
			return null;
		}
		BankSubjContrastVO[] vos = null;
		try{
			DataItfDAO  dao = new DataItfDAO();
			vos = dao.queryBankSubjContrastVOByPkglorgbook(pk_glorgbook);
		}catch(Exception e){
            e.printStackTrace();
            Logger.error(e.getMessage());
            throw new BusinessException (e.getMessage());
		}
		return vos;
	}
}
