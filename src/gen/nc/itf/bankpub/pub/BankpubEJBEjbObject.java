package nc.itf.bankpub.pub;

/**
 * business service ejb wrapper 
 * Created by EJBGenerator
 * based on velocity technology
 */

public interface BankpubEJBEjbObject extends  javax.ejb.EJBLocalObject,nc.itf.bankpub.pub.IBankContrast,nc.itf.bankpub.pub.IBankSubjContrast,nc.itf.bankpub.pub.ICurrContrast,nc.itf.bankpub.pub.IDealCode,nc.itf.bankpub.pub.IDeptSubjContrast,nc.itf.bankpub.pub.IEspecialSubj,nc.itf.bankpub.pub.IFileDefine,nc.itf.bankpub.pub.IPubTools,nc.itf.bankpub.pub.IRespondCode,nc.itf.bankpub.pub.IRuleSet,nc.itf.bankpub.pub.ISubjBalRuleSet,nc.itf.bankpub.pub.ISubjContrast,nc.itf.bankpub.pub.ISystemType,nc.itf.bankpub.pub.IVoucherElement,nc.itf.bankpub.pub.IVouType{
	public void delete() throws nc.vo.pub.BusinessException;
	public void maintainBankContrast(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public void deleteBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOs(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void insertBankContrastVOs(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public void updateBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsDept(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsByPkglorgbook(java.lang.String arg0 ,java.lang.String arg1 ) throws nc.vo.pub.BusinessException;
	public void deleteBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException;
	public void insertBankSubjContrastVOs(nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryAllBankSubjContrastVO() throws nc.vo.pub.BusinessException;
	public void updateBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcolshow.SubjColShowVO[] queryAllSubjColShowVO() throws nc.vo.pub.BusinessException;
	public void insertSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) throws nc.vo.pub.BusinessException;
	public void updateSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String queryBankSubjContrastVOByVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException;
	public void copySubjcontrast(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String[] arg2 ) throws nc.vo.pub.BusinessException;
	public void deleteBankSubjByPksystempkglorgbook(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void saveAll(nc.vo.bankpub.currcontrast.CurrContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.currcontrast.CurrContrastVO[] queryAll() throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.dealcode.DealCodeVO[] queryDealCode() throws nc.vo.pub.BusinessException;
	public void updateDealCode(nc.vo.bankpub.dealcode.DealCodeVO arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String queryDealCodeByIndex(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryNCSubjContrastbyPKDept(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bd.b02.AccsubjVO[] queryAccsubjbyPKDept(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bd.b04.DeptdocVO[] queryAllDeptdoc() throws nc.vo.pub.BusinessException;
	public void deleteEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArrayEspecialSubjVO(nc.vo.bankpub.especialsubj.EspecialSubjVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.especialsubj.EspecialSubjVO[] queryAllEspecialSubjVOs() throws nc.vo.pub.BusinessException;
	public void updateEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) throws nc.vo.pub.BusinessException;
	public void deleteFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArrayFileDefineVO(nc.vo.bankpub.filedefine.FileDefineVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryAllFileDefineVOs() throws nc.vo.pub.BusinessException;
	public void updateFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String[] split(java.lang.String arg0 ,java.lang.String arg1 ) throws nc.vo.pub.BusinessException;
	public java.lang.String getStrByPk(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) throws nc.vo.pub.BusinessException;
	public java.lang.String getPubInitSys(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String getStrByPk1(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) throws nc.vo.pub.BusinessException;
	public java.lang.String getPkcorpByPkglorgbook(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String[] getPkglorgbookByPkcorp(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.currrate.CurrrateVO[] readCurrRateFile(nc.vo.bankpub.systemtype.SystemTypeVO arg0 ,nc.vo.pub.lang.UFDate arg1 ) throws nc.vo.pub.BusinessException;
	public void ExcuteUpdateSQL(java.lang.String arg0) throws java.lang.Exception;
	public nc.vo.pub.lang.UFDate getServerDate() throws nc.vo.pub.BusinessException;
	public nc.vo.bd.b02.AccsubjVO[] getNcAccsujVosByCondition(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void deleteRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArrayRespondCodeVO(nc.vo.bankpub.respondcode.RespondCodeVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.respondcode.RespondCodeVO[] queryAllRespondCodeVOs() throws nc.vo.pub.BusinessException;
	public void updateRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String getRespondInfoByRespongCode(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void insert(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) throws java.lang.Exception;
	public void update(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) throws java.lang.Exception;
	public void delByPK(java.lang.String arg0) throws java.lang.Exception;
	public void delBySystemPK(java.lang.String arg0) throws java.lang.Exception;
	public nc.vo.bankpub.eliminaterule.RuleSetVO[] queryBySystemPK(java.lang.String arg0) throws java.lang.Exception;
	public void delValue(java.lang.String arg0) throws java.lang.Exception;
	public void insValue(nc.vo.bankpub.eliminaterule.EliminateRuleVO arg0) throws java.lang.Exception;
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryCols(java.lang.String arg0) throws java.lang.Exception;
	public nc.vo.bankpub.eliminaterule.EliminateRuleVO[] queryValues(java.lang.String arg0) throws java.lang.Exception;
	public nc.vo.bankpub.subjbalrule.SubjBalRuleVO[] importRules(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void copySubjcontrast(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException;
	public void clearSubjcontrast(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] querySubjContrast(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public void copySubjcontrastFz(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] initSubjContrast(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public java.lang.String[] insertSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public void updateSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryAllSubjContrast() throws nc.vo.pub.BusinessException;
	public void deleteSubjAssConstrasts(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] querySubjAssConstrastBy(java.lang.String arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] saveSubjAssConstrast(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) throws nc.vo.pub.BusinessException;
	public void deleteSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArraySystemTypeVO(nc.vo.bankpub.systemtype.SystemTypeVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.systemtype.SystemTypeVO[] queryAllSystemTypeVOs() throws nc.vo.pub.BusinessException;
	public void updateSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) throws nc.vo.pub.BusinessException;
	public void deleteVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArrayVoucherElementVO(nc.vo.bankpub.voucherelement.VoucherElementVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.voucherelement.VoucherElementVO[] queryAllVoucherElementVOs() throws nc.vo.pub.BusinessException;
	public void updateVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) throws nc.vo.pub.BusinessException;
	public void insertArrayVouTypeVO(nc.vo.bankpub.vouchertype.VouTypeVO[] arg0) throws nc.vo.pub.BusinessException;
	public nc.vo.bankpub.vouchertype.VouTypeVO[] queryAllVouTypeVOs() throws nc.vo.pub.BusinessException;
	public void updateVouTypeVOByVO(nc.vo.bankpub.vouchertype.VouTypeVO arg0) throws nc.vo.pub.BusinessException;
}