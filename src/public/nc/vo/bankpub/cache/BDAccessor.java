package nc.vo.bankpub.cache;

import nc.vo.bd.access.AccessorManager;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.BdinfoManager;
import nc.vo.bd.access.BdinfoVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.pub.BusinessException;

/** 包括公司目录、会计科目、部门档案、学位、民族、岗位序列等基本档案都可以使用这个缓存。
 *  参见IBdinfoConst、bd_bdinfo*/

public class BDAccessor {
	// 利用基础数据缓存来加快pk->name转换

	/**
	 * orgtypecode:
	 * orgid的类型，是主体账簿主键还是公司主键。nc.vo.sm.nodepower.OrgnizeTypeVO.COMPANY_TYPE
	 */
	public static IBDAccessor getBDAccessor(String pk_bdinfo,
			String orgtypecode, String orgid) throws BusinessException {
		IBDAccessor accessor = AccessorManager.getAccessor(pk_bdinfo,
				orgtypecode,
				// 如果能取到公司，使用公司主键，否则使用集团主键
				orgid);
		if (accessor == null) {
			BdinfoVO bdInfoVo = BdinfoManager.getBdInfoVO(pk_bdinfo);
			String name = bdInfoVo.getBdname();
			throw new BusinessException("取 [ " + name + " ] 的数据缓存失败，请与系统管理员联系！");
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
			throw new BusinessException("取 [ " + name
					+ " ] 的数据失败，可能部分被其他地方引用的数据被删除，请与系统管理员联系！");
		}
		return dataVo;
	}

	public static BdinfoVO getBdInfo(String pk_bdinfo) throws BusinessException {
		BdinfoVO vo = BdinfoManager.getBdInfoVO(pk_bdinfo);
		if (vo == null) {
			throw new BusinessException("取基础数据配置信息失败，请与系统管理员联系！");
		}
		return vo;
	}

}
