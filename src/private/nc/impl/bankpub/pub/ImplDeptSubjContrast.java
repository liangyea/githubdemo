package nc.impl.bankpub.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IDeptSubjContrast;
import nc.itf.doc.api.Accsubjdoc;
import nc.itf.uap.bd.dept.IDeptdocQry;
import nc.ui.bd.BDGLOrgBookAccessor;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.bd.b04.DeptdocVO;
import nc.vo.bd.b54.GlorgbookVO;
import nc.vo.pub.BusinessException;

public class ImplDeptSubjContrast implements IDeptSubjContrast {

	public NCSubjContrastVO[] queryNCSubjContrastbyPKDept(String pk_deptdoc) throws BusinessException {
		NCSubjContrastVO[] ncSubjContrastVOs =null;
		try{
		SubjectDMO subjDMO = new SubjectDMO();
		ncSubjContrastVOs = subjDMO.queryNCSubjContrastbyPKDept(pk_deptdoc);
		}catch(Exception e){
			throw new BusinessException(e);
		}
		return ncSubjContrastVOs;
	}

	public AccsubjVO[] queryAccsubjbyPKDept(String pk_deptdoc) throws BusinessException {
		// 根据部门主键查询主体账簿
//		IBDAccessor userAccessor = BDAccessor.getBDAccessor(IBdinfoConst.DEPTDOC, OrgnizeTypeVO., "0001");
		IDeptdocQry deptQry = (IDeptdocQry) NCLocator.getInstance().lookup(IDeptdocQry.class.getName());
		DeptdocVO deptVo = deptQry.findDeptdocVOByPK(pk_deptdoc);
		GlorgbookVO glorgbookVo = BDGLOrgBookAccessor.getDefaultGlOrgBookVOByPk_EntityOrg(deptVo.getPk_corp());
		// 根据主体账簿查询科目
		Accsubjdoc itf = (Accsubjdoc) NCLocator.getInstance().lookup(
				Accsubjdoc.class.getName());
		
		AccsubjVO[] accsubjvos =(AccsubjVO[]) itf.queryAccsubjVOs(glorgbookVo.m_pk_glorgbook ,null, false);

		return accsubjvos;
	}

	public DeptdocVO[] queryAllDeptdoc() throws BusinessException {
		IDeptdocQry deptQry = (IDeptdocQry) NCLocator.getInstance().lookup(IDeptdocQry.class.getName());
		DeptdocVO[] deptdocVOs = deptQry.queryAllDeptdocVO("1001");
		return deptdocVOs;
	}

}
