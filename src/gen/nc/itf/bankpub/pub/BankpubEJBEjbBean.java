package nc.itf.bankpub.pub;

import nc.bs.framework.server._ServerForEJBNCLocator;
import nc.bs.pub.BusinessObject;

/**
 * business service ejb wrapper 
 * Created by EJBGenerator
 * based on velocity technology
 */

public class  BankpubEJBEjbBean extends BusinessObject{
	private Object thisService;
	
	public void ejbActivate(){}
	public void ejbPassivate(){}
	public void ejbRemove(){}
	
	public BankpubEJBEjbBean() {
		thisService=(Object)_ServerForEJBNCLocator.lookup("nc.itf.bankpub.pub.BankpubEJB");
	}
	public void ejbCreate(){
		if(thisService == null)
			thisService=(Object)_ServerForEJBNCLocator.lookup("nc.itf.bankpub.pub.BankpubEJB");
	}
	
	public  Object getService() {
		if(thisService == null)
			thisService=(Object)_ServerForEJBNCLocator.lookup("nc.itf.bankpub.pub.BankpubEJB");			
		return thisService;
	}
	public void delete() throws Exception{
		
		((nc.itf.bankpub.pub.IBankContrast )getService()).delete();
		
	}
	public void maintainBankContrast(nc.vo.bankpub.bankcontrast.BankContrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankContrast )getService()).maintainBankContrast(arg0);
		
	}
	public void deleteBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankContrast )getService()).deleteBankContrastVO(arg0);
		
	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOs(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankContrast )getService()).queryBankContrastVOs(arg0);		
	}
	public void insertBankContrastVOs(nc.vo.bankpub.bankcontrast.BankContrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankContrast )getService()).insertBankContrastVOs(arg0);
		
	}
	public void updateBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankContrast )getService()).updateBankContrastVO(arg0);
		
	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsDept(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankContrast )getService()).queryBankContrastVOsDept(arg0);		
	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsByPkglorgbook(java.lang.String  arg0 ,java.lang.String  arg1 ) throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankContrast )getService()).queryBankContrastVOsByPkglorgbook(arg0 ,arg1 );		
	}
	public void deleteBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).deleteBankSubjContrastVO(arg0);
		
	}
	public void insertBankSubjContrastVOs(nc.vo.bankpub.subjcontrast.BankSubjContrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).insertBankSubjContrastVOs(arg0);
		
	}
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryAllBankSubjContrastVO() throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankSubjContrast )getService()).queryAllBankSubjContrastVO();		
	}
	public void updateBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).updateBankSubjContrastVO(arg0);
		
	}
	public nc.vo.bankpub.subjcolshow.SubjColShowVO[] queryAllSubjColShowVO() throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankSubjContrast )getService()).queryAllSubjColShowVO();		
	}
	public void insertSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).insertSubjColShowVO(arg0);
		
	}
	public void updateSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).updateSubjColShowVO(arg0);
		
	}
	public java.lang.String queryBankSubjContrastVOByVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankSubjContrast )getService()).queryBankSubjContrastVOByVO(arg0);		
	}
	public void copySubjcontrast(java.lang.String  arg0 ,java.lang.String  arg1  ,java.lang.String[]  arg2 ) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).copySubjcontrast(arg0 ,arg1  ,arg2 );
		
	}
	public void deleteBankSubjByPksystempkglorgbook(java.lang.String  arg0 ,java.lang.String[]  arg1 ) throws Exception{
		
		((nc.itf.bankpub.pub.IBankSubjContrast )getService()).deleteBankSubjByPksystempkglorgbook(arg0 ,arg1 );
		
	}
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IBankSubjContrast )getService()).queryBankSubjContrastVOByPkglorgbook(arg0);		
	}
	public void saveAll(nc.vo.bankpub.currcontrast.CurrContrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ICurrContrast )getService()).saveAll(arg0);
		
	}
	public nc.vo.bankpub.currcontrast.CurrContrastVO[] queryAll() throws Exception{
		
		return ((nc.itf.bankpub.pub.ICurrContrast )getService()).queryAll();		
	}
	public nc.vo.bankpub.dealcode.DealCodeVO[] queryDealCode() throws Exception{
		
		return ((nc.itf.bankpub.pub.IDealCode )getService()).queryDealCode();		
	}
	public void updateDealCode(nc.vo.bankpub.dealcode.DealCodeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IDealCode )getService()).updateDealCode(arg0);
		
	}
	public java.lang.String queryDealCodeByIndex(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IDealCode )getService()).queryDealCodeByIndex(arg0);		
	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryNCSubjContrastbyPKDept(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IDeptSubjContrast )getService()).queryNCSubjContrastbyPKDept(arg0);		
	}
	public nc.vo.bd.b02.AccsubjVO[] queryAccsubjbyPKDept(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IDeptSubjContrast )getService()).queryAccsubjbyPKDept(arg0);		
	}
	public nc.vo.bd.b04.DeptdocVO[] queryAllDeptdoc() throws Exception{
		
		return ((nc.itf.bankpub.pub.IDeptSubjContrast )getService()).queryAllDeptdoc();		
	}
	public void deleteEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IEspecialSubj )getService()).deleteEspecialSubjVOByVO(arg0);
		
	}
	public void insertArrayEspecialSubjVO(nc.vo.bankpub.especialsubj.EspecialSubjVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IEspecialSubj )getService()).insertArrayEspecialSubjVO(arg0);
		
	}
	public nc.vo.bankpub.especialsubj.EspecialSubjVO[] queryAllEspecialSubjVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.IEspecialSubj )getService()).queryAllEspecialSubjVOs();		
	}
	public void updateEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IEspecialSubj )getService()).updateEspecialSubjVOByVO(arg0);
		
	}
	public void deleteFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IFileDefine )getService()).deleteFileDefineVOByVO(arg0);
		
	}
	public void insertArrayFileDefineVO(nc.vo.bankpub.filedefine.FileDefineVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IFileDefine )getService()).insertArrayFileDefineVO(arg0);
		
	}
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryAllFileDefineVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.IFileDefine )getService()).queryAllFileDefineVOs();		
	}
	public void updateFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IFileDefine )getService()).updateFileDefineVOByVO(arg0);
		
	}
	public java.lang.String[] split(java.lang.String  arg0 ,java.lang.String  arg1 ) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).split(arg0 ,arg1 );		
	}
	public java.lang.String getStrByPk(java.lang.String  arg0 ,java.lang.String  arg1  ,java.lang.String  arg2  ,java.lang.String  arg3 ) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getStrByPk(arg0 ,arg1  ,arg2  ,arg3 );		
	}
	public java.lang.String getPubInitSys(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getPubInitSys(arg0);		
	}
	public java.lang.String getStrByPk1(java.lang.String  arg0 ,java.lang.String  arg1  ,java.lang.String  arg2  ,java.lang.String  arg3 ) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getStrByPk1(arg0 ,arg1  ,arg2  ,arg3 );		
	}
	public java.lang.String getPkcorpByPkglorgbook(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getPkcorpByPkglorgbook(arg0);		
	}
	public java.lang.String[] getPkglorgbookByPkcorp(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getPkglorgbookByPkcorp(arg0);		
	}
	public nc.vo.bankpub.currrate.CurrrateVO[] readCurrRateFile(nc.vo.bankpub.systemtype.SystemTypeVO  arg0 ,nc.vo.pub.lang.UFDate  arg1 ) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).readCurrRateFile(arg0 ,arg1 );		
	}
	public void ExcuteUpdateSQL(java.lang.String  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IPubTools )getService()).ExcuteUpdateSQL(arg0);
		
	}
	public nc.vo.pub.lang.UFDate getServerDate() throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getServerDate();		
	}
	public nc.vo.bd.b02.AccsubjVO[] getNcAccsujVosByCondition(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IPubTools )getService()).getNcAccsujVosByCondition(arg0);		
	}
	public void deleteRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRespondCode )getService()).deleteRespondCodeVOByVO(arg0);
		
	}
	public void insertArrayRespondCodeVO(nc.vo.bankpub.respondcode.RespondCodeVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRespondCode )getService()).insertArrayRespondCodeVO(arg0);
		
	}
	public nc.vo.bankpub.respondcode.RespondCodeVO[] queryAllRespondCodeVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.IRespondCode )getService()).queryAllRespondCodeVOs();		
	}
	public void updateRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRespondCode )getService()).updateRespondCodeVOByVO(arg0);
		
	}
	public java.lang.String getRespondInfoByRespongCode(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IRespondCode )getService()).getRespondInfoByRespongCode(arg0);		
	}
	public void insert(nc.vo.bankpub.eliminaterule.RuleSetVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).insert(arg0);
		
	}
	public void update(nc.vo.bankpub.eliminaterule.RuleSetVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).update(arg0);
		
	}
	public void delByPK(java.lang.String  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).delByPK(arg0);
		
	}
	public void delBySystemPK(java.lang.String  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).delBySystemPK(arg0);
		
	}
	public nc.vo.bankpub.eliminaterule.RuleSetVO[] queryBySystemPK(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IRuleSet )getService()).queryBySystemPK(arg0);		
	}
	public void delValue(java.lang.String  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).delValue(arg0);
		
	}
	public void insValue(nc.vo.bankpub.eliminaterule.EliminateRuleVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IRuleSet )getService()).insValue(arg0);
		
	}
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryCols(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IRuleSet )getService()).queryCols(arg0);		
	}
	public nc.vo.bankpub.eliminaterule.EliminateRuleVO[] queryValues(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.IRuleSet )getService()).queryValues(arg0);		
	}
	public nc.vo.bankpub.subjbalrule.SubjBalRuleVO[] importRules(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjBalRuleSet )getService()).importRules(arg0);		
	}
	public void copySubjcontrast(java.lang.String  arg0 ,java.lang.String[]  arg1 ) throws Exception{
		
		((nc.itf.bankpub.pub.ISubjContrast )getService()).copySubjcontrast(arg0 ,arg1 );
		
	}
	public void clearSubjcontrast(java.lang.String  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISubjContrast )getService()).clearSubjcontrast(arg0);
		
	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] querySubjContrast(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).querySubjContrast(arg0);		
	}
	public void copySubjcontrastFz(java.lang.String  arg0 ,java.lang.String[]  arg1 ) throws Exception{
		
		((nc.itf.bankpub.pub.ISubjContrast )getService()).copySubjcontrastFz(arg0 ,arg1 );
		
	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] initSubjContrast(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).initSubjContrast(arg0);		
	}
	public java.lang.String[] insertSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[]  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).insertSubjContrast(arg0);		
	}
	public void updateSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISubjContrast )getService()).updateSubjContrast(arg0);
		
	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryAllSubjContrast() throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).queryAllSubjContrast();		
	}
	public void deleteSubjAssConstrasts(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISubjContrast )getService()).deleteSubjAssConstrasts(arg0);
		
	}
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] querySubjAssConstrastBy(java.lang.String  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).querySubjAssConstrastBy(arg0);		
	}
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] saveSubjAssConstrast(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[]  arg0) throws Exception{
		
		return ((nc.itf.bankpub.pub.ISubjContrast )getService()).saveSubjAssConstrast(arg0);		
	}
	public void deleteSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISystemType )getService()).deleteSystemTypeVOByVO(arg0);
		
	}
	public void insertArraySystemTypeVO(nc.vo.bankpub.systemtype.SystemTypeVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISystemType )getService()).insertArraySystemTypeVO(arg0);
		
	}
	public nc.vo.bankpub.systemtype.SystemTypeVO[] queryAllSystemTypeVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.ISystemType )getService()).queryAllSystemTypeVOs();		
	}
	public void updateSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.ISystemType )getService()).updateSystemTypeVOByVO(arg0);
		
	}
	public void deleteVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IVoucherElement )getService()).deleteVoucherElementVOByVO(arg0);
		
	}
	public void insertArrayVoucherElementVO(nc.vo.bankpub.voucherelement.VoucherElementVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IVoucherElement )getService()).insertArrayVoucherElementVO(arg0);
		
	}
	public nc.vo.bankpub.voucherelement.VoucherElementVO[] queryAllVoucherElementVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.IVoucherElement )getService()).queryAllVoucherElementVOs();		
	}
	public void updateVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IVoucherElement )getService()).updateVoucherElementVOByVO(arg0);
		
	}
	public void insertArrayVouTypeVO(nc.vo.bankpub.vouchertype.VouTypeVO[]  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IVouType )getService()).insertArrayVouTypeVO(arg0);
		
	}
	public nc.vo.bankpub.vouchertype.VouTypeVO[] queryAllVouTypeVOs() throws Exception{
		
		return ((nc.itf.bankpub.pub.IVouType )getService()).queryAllVouTypeVOs();		
	}
	public void updateVouTypeVOByVO(nc.vo.bankpub.vouchertype.VouTypeVO  arg0) throws Exception{
		
		((nc.itf.bankpub.pub.IVouType )getService()).updateVouTypeVOByVO(arg0);
		
	}
}
	