package nc.itf.bankpub.pub;

import nc.vo.bankpub.subjcolshow.SubjColShowVO;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.pub.BusinessException;

public interface IBankSubjContrast {
	public BankSubjContrastVO[] queryAllBankSubjContrastVO()throws BusinessException;
	public BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(String pk_glorgbook)throws BusinessException;
	public String queryBankSubjContrastVOByVO(BankSubjContrastVO vo)throws BusinessException;
	public void insertBankSubjContrastVOs(BankSubjContrastVO[] vos)throws BusinessException;
	public void updateBankSubjContrastVO(BankSubjContrastVO vo)throws BusinessException;
	public void deleteBankSubjContrastVO(BankSubjContrastVO vo)throws BusinessException;
	public void deleteBankSubjByPksystempkglorgbook(String pk_system ,String[] pk_glorgbooks) throws BusinessException;
	public SubjColShowVO[] queryAllSubjColShowVO()throws BusinessException;
	public void insertSubjColShowVO(SubjColShowVO vo )throws BusinessException;
	public void updateSubjColShowVO(SubjColShowVO vo )throws BusinessException;
	public void copySubjcontrast(String pk_system,String standard, String[] target)throws BusinessException;	
}
