package nc.itf.bankpub.pub;

import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.bd.b04.DeptdocVO;
import nc.vo.pub.BusinessException;

public interface IDeptSubjContrast {
	
	public NCSubjContrastVO[] queryNCSubjContrastbyPKDept(String pk_deptdoc) throws BusinessException;
	public AccsubjVO[] queryAccsubjbyPKDept(String pk_deptdoc) throws BusinessException;
	public DeptdocVO[] queryAllDeptdoc()throws BusinessException;
}
