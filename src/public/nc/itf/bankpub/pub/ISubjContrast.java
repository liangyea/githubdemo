package nc.itf.bankpub.pub;

import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.pub.BusinessException;

public interface ISubjContrast {
	public void clearSubjcontrast(String corp) throws BusinessException;

	public void copySubjcontrastFz(String standard, String[] target)
			throws BusinessException;

	public void copySubjcontrast(String standard, String[] target)
			throws BusinessException;

	public NCSubjContrastVO[] initSubjContrast(String pk_deptdoc)
			throws BusinessException;

	public String[] insertSubjContrast(NCSubjContrastVO[] vo)
			throws BusinessException;

	public NCSubjContrastVO[] querySubjContrast(String corp)
			throws BusinessException;

	public void updateSubjContrast(NCSubjContrastVO[] vo)
			throws BusinessException;

	public NCSubjContrastVO[] queryAllSubjContrast() throws BusinessException;

	public SubjAssConstrastVO[] querySubjAssConstrastBy(String pk_accsubj)
			throws BusinessException;

	public SubjAssConstrastVO[] saveSubjAssConstrast(
			SubjAssConstrastVO[] subjAssConstrastVos) throws BusinessException;

	public void deleteSubjAssConstrasts(SubjAssConstrastVO[] subjAssConstrastVos)
			throws BusinessException;

}
