package nc.vo.bankpub.cache;

import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.bd.access.IBdinfoConst;
import nc.vo.pub.BusinessException;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

public class UserCache {

	public static IBDAccessor getUserAccessor() throws BusinessException {
		IBDAccessor accessor = BDAccessor.getBDAccessor(IBdinfoConst.SM_USER,
				OrgnizeTypeVO.COMPANY_TYPE, "0001");
		return accessor;
	}
	
	public static IBDAccessor getUserAccessor(String unitcode) throws BusinessException {
		IBDAccessor accessor = BDAccessor.getBDAccessor(IBdinfoConst.SM_USER,
				OrgnizeTypeVO.COMPANY_TYPE, unitcode);
		return accessor;
	}
	
	
	/** 取核心制单人主键 */
	public static String getHX_Pk_prepared() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("核心制单");
		
		if (preparedVo == null) {
			throw new BusinessException("请在集团下建立虚拟用户[核心制单]、[核心审核]、[核心记账]!");
		}
		
		return preparedVo.getPk();
	}
	
	/** 取核心审核人主键 */
	public static String getHX_Pk_checked() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("核心审核");
		if (preparedVo == null) {
			throw new BusinessException("请在集团下建立虚拟用户[核心制单]、[核心审核]、[核心记账]!");
		}
		
		return preparedVo.getPk();
	}
	
	/** 取核心审核人主键 */
	public static String getHX_Pk_manager() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("核心记账");
		if (preparedVo == null) {
			throw new BusinessException("请在集团下建立虚拟用户[核心制单]、[核心审核]、[核心记账]!");
		}
		
		return preparedVo.getPk();
	}
	

	/**
	 * 
	 */
	public static String getUserCode(String pk_corp,String pk_user) throws BusinessException {
		BddataVO preparedVo = getUserAccessor(pk_corp).getDocByPk(pk_user);
		if (preparedVo == null) {
			return "";
		}		
		return preparedVo.getName();
	}
}
