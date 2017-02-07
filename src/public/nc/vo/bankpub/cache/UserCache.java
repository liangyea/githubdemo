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
	
	
	/** ȡ�����Ƶ������� */
	public static String getHX_Pk_prepared() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("�����Ƶ�");
		
		if (preparedVo == null) {
			throw new BusinessException("���ڼ����½��������û�[�����Ƶ�]��[�������]��[���ļ���]!");
		}
		
		return preparedVo.getPk();
	}
	
	/** ȡ������������� */
	public static String getHX_Pk_checked() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("�������");
		if (preparedVo == null) {
			throw new BusinessException("���ڼ����½��������û�[�����Ƶ�]��[�������]��[���ļ���]!");
		}
		
		return preparedVo.getPk();
	}
	
	/** ȡ������������� */
	public static String getHX_Pk_manager() throws BusinessException {
		BddataVO preparedVo = getUserAccessor().getDocByName("���ļ���");
		if (preparedVo == null) {
			throw new BusinessException("���ڼ����½��������û�[�����Ƶ�]��[�������]��[���ļ���]!");
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
