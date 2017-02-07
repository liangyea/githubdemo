package nc.vo.bankpub.cache;

import nc.vo.bd.access.AccessorManager;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.BdinfoManager;
import nc.vo.bd.access.BdinfoVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.pub.BusinessException;

/** ������˾Ŀ¼����ƿ�Ŀ�����ŵ�����ѧλ�����塢��λ���еȻ�������������ʹ��������档
 *  �μ�IBdinfoConst��bd_bdinfo*/

public class BDAccessor {
	// ���û������ݻ������ӿ�pk->nameת��

	/**
	 * orgtypecode:
	 * orgid�����ͣ��������˲��������ǹ�˾������nc.vo.sm.nodepower.OrgnizeTypeVO.COMPANY_TYPE
	 */
	public static IBDAccessor getBDAccessor(String pk_bdinfo,
			String orgtypecode, String orgid) throws BusinessException {
		IBDAccessor accessor = AccessorManager.getAccessor(pk_bdinfo,
				orgtypecode,
				// �����ȡ����˾��ʹ�ù�˾����������ʹ�ü�������
				orgid);
		if (accessor == null) {
			BdinfoVO bdInfoVo = BdinfoManager.getBdInfoVO(pk_bdinfo);
			String name = bdInfoVo.getBdname();
			throw new BusinessException("ȡ [ " + name + " ] �����ݻ���ʧ�ܣ�����ϵͳ����Ա��ϵ��");
		}
		return accessor;
	}

	public static BddataVO getBDData(String pk_bdinfo, String orgtypecode,
			String orgid, String pk) throws BusinessException {
		BddataVO dataVo = getBDAccessor(pk_bdinfo, orgtypecode, orgid)
				.getDocByPk(pk);
		if (dataVo == null) {
			BdinfoVO bdInfoVo = BdinfoManager.getBdInfoVO(pk_bdinfo);
			String name = bdInfoVo.getBdname();
			throw new BusinessException("ȡ [ " + name
					+ " ] ������ʧ�ܣ����ܲ��ֱ������ط����õ����ݱ�ɾ��������ϵͳ����Ա��ϵ��");
		}
		return dataVo;
	}

	public static BdinfoVO getBdInfo(String pk_bdinfo) throws BusinessException {
		BdinfoVO vo = BdinfoManager.getBdInfoVO(pk_bdinfo);
		if (vo == null) {
			throw new BusinessException("ȡ��������������Ϣʧ�ܣ�����ϵͳ����Ա��ϵ��");
		}
		return vo;
	}

}
