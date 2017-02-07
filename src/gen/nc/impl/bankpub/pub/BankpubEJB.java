package nc.impl.bankpub.pub;

/**
 * business service proxy 
 * Created by EJBGenerator
 * based on velocity technology
 */

public class BankpubEJB implements 
	nc.itf.bankpub.pub.IVouType,nc.itf.bankpub.pub.IRuleSet
,nc.itf.bankpub.pub.IEspecialSubj
,nc.itf.bankpub.pub.ICurrContrast
,nc.itf.bankpub.pub.IBankContrast
,nc.itf.bankpub.pub.IDealCode
,nc.itf.bankpub.pub.ISystemType
,nc.itf.bankpub.pub.IBankSubjContrast
,nc.itf.bankpub.pub.IVoucherElement
,nc.itf.bankpub.pub.IDeptSubjContrast
,nc.itf.bankpub.pub.IFileDefine
,nc.itf.bankpub.pub.ISubjContrast
,nc.itf.bankpub.pub.ISubjBalRuleSet
,nc.itf.bankpub.pub.IRespondCode
,nc.itf.bankpub.pub.IPubTools
{
	
	private nc.itf.bankpub.pub.IVouType implVouType = null;
	private nc.itf.bankpub.pub.IRuleSet iRuleSet = null;
	private nc.itf.bankpub.pub.IEspecialSubj iEspecialSubj = null;
	private nc.itf.bankpub.pub.ICurrContrast iCurrContrast = null;
	private nc.itf.bankpub.pub.IBankContrast iBankContrast = null;
	private nc.itf.bankpub.pub.IDealCode iDealCode = null;
	private nc.itf.bankpub.pub.ISystemType iSystemType = null;
	private nc.itf.bankpub.pub.IBankSubjContrast iBankSubjContrast = null;
	private nc.itf.bankpub.pub.IVoucherElement iVoucherElement = null;
	private nc.itf.bankpub.pub.IDeptSubjContrast iDeptSubjContrast = null;
	private nc.itf.bankpub.pub.IFileDefine iFileDefine = null;
	private nc.itf.bankpub.pub.ISubjContrast iSubjContrast = null;
	private nc.itf.bankpub.pub.ISubjBalRuleSet iSubjBalRuleSet = null;
	private nc.itf.bankpub.pub.IRespondCode iRespondCode = null;
	private nc.itf.bankpub.pub.IPubTools iPubTools = null;
	
	public nc.itf.bankpub.pub.IVouType getImplVouType(){
		return implVouType;
	}
	public void setImplVouType(nc.itf.bankpub.pub.IVouType implVouType){
		this.implVouType = implVouType;
	}
	
	public nc.itf.bankpub.pub.IRuleSet getIRuleSet(){
		return iRuleSet;
	}
	public void setIRuleSet(nc.itf.bankpub.pub.IRuleSet iRuleSet){
		this.iRuleSet = iRuleSet;
	}
	
	public nc.itf.bankpub.pub.IEspecialSubj getIEspecialSubj(){
		return iEspecialSubj;
	}
	public void setIEspecialSubj(nc.itf.bankpub.pub.IEspecialSubj iEspecialSubj){
		this.iEspecialSubj = iEspecialSubj;
	}
	
	public nc.itf.bankpub.pub.ICurrContrast getICurrContrast(){
		return iCurrContrast;
	}
	public void setICurrContrast(nc.itf.bankpub.pub.ICurrContrast iCurrContrast){
		this.iCurrContrast = iCurrContrast;
	}
	
	public nc.itf.bankpub.pub.IBankContrast getIBankContrast(){
		return iBankContrast;
	}
	public void setIBankContrast(nc.itf.bankpub.pub.IBankContrast iBankContrast){
		this.iBankContrast = iBankContrast;
	}
	
	public nc.itf.bankpub.pub.IDealCode getIDealCode(){
		return iDealCode;
	}
	public void setIDealCode(nc.itf.bankpub.pub.IDealCode iDealCode){
		this.iDealCode = iDealCode;
	}
	
	public nc.itf.bankpub.pub.ISystemType getISystemType(){
		return iSystemType;
	}
	public void setISystemType(nc.itf.bankpub.pub.ISystemType iSystemType){
		this.iSystemType = iSystemType;
	}
	
	public nc.itf.bankpub.pub.IBankSubjContrast getIBankSubjContrast(){
		return iBankSubjContrast;
	}
	public void setIBankSubjContrast(nc.itf.bankpub.pub.IBankSubjContrast iBankSubjContrast){
		this.iBankSubjContrast = iBankSubjContrast;
	}
	
	public nc.itf.bankpub.pub.IVoucherElement getIVoucherElement(){
		return iVoucherElement;
	}
	public void setIVoucherElement(nc.itf.bankpub.pub.IVoucherElement iVoucherElement){
		this.iVoucherElement = iVoucherElement;
	}
	
	public nc.itf.bankpub.pub.IDeptSubjContrast getIDeptSubjContrast(){
		return iDeptSubjContrast;
	}
	public void setIDeptSubjContrast(nc.itf.bankpub.pub.IDeptSubjContrast iDeptSubjContrast){
		this.iDeptSubjContrast = iDeptSubjContrast;
	}
	
	public nc.itf.bankpub.pub.IFileDefine getIFileDefine(){
		return iFileDefine;
	}
	public void setIFileDefine(nc.itf.bankpub.pub.IFileDefine iFileDefine){
		this.iFileDefine = iFileDefine;
	}
	
	public nc.itf.bankpub.pub.ISubjContrast getISubjContrast(){
		return iSubjContrast;
	}
	public void setISubjContrast(nc.itf.bankpub.pub.ISubjContrast iSubjContrast){
		this.iSubjContrast = iSubjContrast;
	}
	
	public nc.itf.bankpub.pub.ISubjBalRuleSet getISubjBalRuleSet(){
		return iSubjBalRuleSet;
	}
	public void setISubjBalRuleSet(nc.itf.bankpub.pub.ISubjBalRuleSet iSubjBalRuleSet){
		this.iSubjBalRuleSet = iSubjBalRuleSet;
	}
	
	public nc.itf.bankpub.pub.IRespondCode getIRespondCode(){
		return iRespondCode;
	}
	public void setIRespondCode(nc.itf.bankpub.pub.IRespondCode iRespondCode){
		this.iRespondCode = iRespondCode;
	}
	
	public nc.itf.bankpub.pub.IPubTools getIPubTools(){
		return iPubTools;
	}
	public void setIPubTools(nc.itf.bankpub.pub.IPubTools iPubTools){
		this.iPubTools = iPubTools;
	}

	public void delete() 
		throws nc.vo.pub.BusinessException	{
	getIBankContrast().delete();

	}
	public void maintainBankContrast(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankContrast().maintainBankContrast(arg0);

	}
	public void deleteBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankContrast().deleteBankContrastVO(arg0);

	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOs(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankContrast().queryBankContrastVOs(arg0);

	}
	public void insertBankContrastVOs(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankContrast().insertBankContrastVOs(arg0);

	}
	public void updateBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankContrast().updateBankContrastVO(arg0);

	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsDept(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankContrast().queryBankContrastVOsDept(arg0);

	}
	public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsByPkglorgbook(java.lang.String arg0 ,java.lang.String arg1 ) 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankContrast().queryBankContrastVOsByPkglorgbook(arg0 ,arg1 );

	}
	public void deleteBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().deleteBankSubjContrastVO(arg0);

	}
	public void insertBankSubjContrastVOs(nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().insertBankSubjContrastVOs(arg0);

	}
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryAllBankSubjContrastVO() 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankSubjContrast().queryAllBankSubjContrastVO();

	}
	public void updateBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().updateBankSubjContrastVO(arg0);

	}
	public nc.vo.bankpub.subjcolshow.SubjColShowVO[] queryAllSubjColShowVO() 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankSubjContrast().queryAllSubjColShowVO();

	}
	public void insertSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().insertSubjColShowVO(arg0);

	}
	public void updateSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().updateSubjColShowVO(arg0);

	}
	public java.lang.String queryBankSubjContrastVOByVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankSubjContrast().queryBankSubjContrastVOByVO(arg0);

	}
	public void copySubjcontrast(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String[] arg2 ) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().copySubjcontrast(arg0 ,arg1  ,arg2 );

	}
	public void deleteBankSubjByPksystempkglorgbook(java.lang.String arg0 ,java.lang.String[] arg1 ) 
		throws nc.vo.pub.BusinessException	{
	getIBankSubjContrast().deleteBankSubjByPksystempkglorgbook(arg0 ,arg1 );

	}
	public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIBankSubjContrast().queryBankSubjContrastVOByPkglorgbook(arg0);

	}
	public void saveAll(nc.vo.bankpub.currcontrast.CurrContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getICurrContrast().saveAll(arg0);

	}
	public nc.vo.bankpub.currcontrast.CurrContrastVO[] queryAll() 
		throws nc.vo.pub.BusinessException	{
		return 	getICurrContrast().queryAll();

	}
	public nc.vo.bankpub.dealcode.DealCodeVO[] queryDealCode() 
		throws nc.vo.pub.BusinessException	{
		return 	getIDealCode().queryDealCode();

	}
	public void updateDealCode(nc.vo.bankpub.dealcode.DealCodeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIDealCode().updateDealCode(arg0);

	}
	public java.lang.String queryDealCodeByIndex(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIDealCode().queryDealCodeByIndex(arg0);

	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryNCSubjContrastbyPKDept(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIDeptSubjContrast().queryNCSubjContrastbyPKDept(arg0);

	}
	public nc.vo.bd.b02.AccsubjVO[] queryAccsubjbyPKDept(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIDeptSubjContrast().queryAccsubjbyPKDept(arg0);

	}
	public nc.vo.bd.b04.DeptdocVO[] queryAllDeptdoc() 
		throws nc.vo.pub.BusinessException	{
		return 	getIDeptSubjContrast().queryAllDeptdoc();

	}
	public void deleteEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIEspecialSubj().deleteEspecialSubjVOByVO(arg0);

	}
	public void insertArrayEspecialSubjVO(nc.vo.bankpub.especialsubj.EspecialSubjVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIEspecialSubj().insertArrayEspecialSubjVO(arg0);

	}
	public nc.vo.bankpub.especialsubj.EspecialSubjVO[] queryAllEspecialSubjVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getIEspecialSubj().queryAllEspecialSubjVOs();

	}
	public void updateEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIEspecialSubj().updateEspecialSubjVOByVO(arg0);

	}
	public void deleteFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIFileDefine().deleteFileDefineVOByVO(arg0);

	}
	public void insertArrayFileDefineVO(nc.vo.bankpub.filedefine.FileDefineVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIFileDefine().insertArrayFileDefineVO(arg0);

	}
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryAllFileDefineVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getIFileDefine().queryAllFileDefineVOs();

	}
	public void updateFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIFileDefine().updateFileDefineVOByVO(arg0);

	}
	public java.lang.String[] split(java.lang.String arg0 ,java.lang.String arg1 ) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().split(arg0 ,arg1 );

	}
	public java.lang.String getStrByPk(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getStrByPk(arg0 ,arg1  ,arg2  ,arg3 );

	}
	public java.lang.String getPubInitSys(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getPubInitSys(arg0);

	}
	public java.lang.String getStrByPk1(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getStrByPk1(arg0 ,arg1  ,arg2  ,arg3 );

	}
	public java.lang.String getPkcorpByPkglorgbook(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getPkcorpByPkglorgbook(arg0);

	}
	public java.lang.String[] getPkglorgbookByPkcorp(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getPkglorgbookByPkcorp(arg0);

	}
	public nc.vo.bankpub.currrate.CurrrateVO[] readCurrRateFile(nc.vo.bankpub.systemtype.SystemTypeVO arg0 ,nc.vo.pub.lang.UFDate arg1 ) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().readCurrRateFile(arg0 ,arg1 );

	}
	public void ExcuteUpdateSQL(java.lang.String arg0) 
		throws java.lang.Exception	{
	getIPubTools().ExcuteUpdateSQL(arg0);

	}
	public nc.vo.pub.lang.UFDate getServerDate() 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getServerDate();

	}
	public nc.vo.bd.b02.AccsubjVO[] getNcAccsujVosByCondition(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIPubTools().getNcAccsujVosByCondition(arg0);

	}
	public void deleteRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIRespondCode().deleteRespondCodeVOByVO(arg0);

	}
	public void insertArrayRespondCodeVO(nc.vo.bankpub.respondcode.RespondCodeVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIRespondCode().insertArrayRespondCodeVO(arg0);

	}
	public nc.vo.bankpub.respondcode.RespondCodeVO[] queryAllRespondCodeVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getIRespondCode().queryAllRespondCodeVOs();

	}
	public void updateRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIRespondCode().updateRespondCodeVOByVO(arg0);

	}
	public java.lang.String getRespondInfoByRespongCode(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getIRespondCode().getRespondInfoByRespongCode(arg0);

	}
	public void insert(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) 
		throws java.lang.Exception	{
	getIRuleSet().insert(arg0);

	}
	public void update(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) 
		throws java.lang.Exception	{
	getIRuleSet().update(arg0);

	}
	public void delByPK(java.lang.String arg0) 
		throws java.lang.Exception	{
	getIRuleSet().delByPK(arg0);

	}
	public void delBySystemPK(java.lang.String arg0) 
		throws java.lang.Exception	{
	getIRuleSet().delBySystemPK(arg0);

	}
	public nc.vo.bankpub.eliminaterule.RuleSetVO[] queryBySystemPK(java.lang.String arg0) 
		throws java.lang.Exception	{
		return 	getIRuleSet().queryBySystemPK(arg0);

	}
	public void delValue(java.lang.String arg0) 
		throws java.lang.Exception	{
	getIRuleSet().delValue(arg0);

	}
	public void insValue(nc.vo.bankpub.eliminaterule.EliminateRuleVO arg0) 
		throws java.lang.Exception	{
	getIRuleSet().insValue(arg0);

	}
	public nc.vo.bankpub.filedefine.FileDefineVO[] queryCols(java.lang.String arg0) 
		throws java.lang.Exception	{
		return 	getIRuleSet().queryCols(arg0);

	}
	public nc.vo.bankpub.eliminaterule.EliminateRuleVO[] queryValues(java.lang.String arg0) 
		throws java.lang.Exception	{
		return 	getIRuleSet().queryValues(arg0);

	}
	public nc.vo.bankpub.subjbalrule.SubjBalRuleVO[] importRules(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjBalRuleSet().importRules(arg0);

	}
	public void copySubjcontrast(java.lang.String arg0 ,java.lang.String[] arg1 ) 
		throws nc.vo.pub.BusinessException	{
	getISubjContrast().copySubjcontrast(arg0 ,arg1 );

	}
	public void clearSubjcontrast(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
	getISubjContrast().clearSubjcontrast(arg0);

	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] querySubjContrast(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().querySubjContrast(arg0);

	}
	public void copySubjcontrastFz(java.lang.String arg0 ,java.lang.String[] arg1 ) 
		throws nc.vo.pub.BusinessException	{
	getISubjContrast().copySubjcontrastFz(arg0 ,arg1 );

	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] initSubjContrast(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().initSubjContrast(arg0);

	}
	public java.lang.String[] insertSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().insertSubjContrast(arg0);

	}
	public void updateSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getISubjContrast().updateSubjContrast(arg0);

	}
	public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryAllSubjContrast() 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().queryAllSubjContrast();

	}
	public void deleteSubjAssConstrasts(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getISubjContrast().deleteSubjAssConstrasts(arg0);

	}
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] querySubjAssConstrastBy(java.lang.String arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().querySubjAssConstrastBy(arg0);

	}
	public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] saveSubjAssConstrast(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
		return 	getISubjContrast().saveSubjAssConstrast(arg0);

	}
	public void deleteSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getISystemType().deleteSystemTypeVOByVO(arg0);

	}
	public void insertArraySystemTypeVO(nc.vo.bankpub.systemtype.SystemTypeVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getISystemType().insertArraySystemTypeVO(arg0);

	}
	public nc.vo.bankpub.systemtype.SystemTypeVO[] queryAllSystemTypeVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getISystemType().queryAllSystemTypeVOs();

	}
	public void updateSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getISystemType().updateSystemTypeVOByVO(arg0);

	}
	public void deleteVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIVoucherElement().deleteVoucherElementVOByVO(arg0);

	}
	public void insertArrayVoucherElementVO(nc.vo.bankpub.voucherelement.VoucherElementVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getIVoucherElement().insertArrayVoucherElementVO(arg0);

	}
	public nc.vo.bankpub.voucherelement.VoucherElementVO[] queryAllVoucherElementVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getIVoucherElement().queryAllVoucherElementVOs();

	}
	public void updateVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getIVoucherElement().updateVoucherElementVOByVO(arg0);

	}
	public void insertArrayVouTypeVO(nc.vo.bankpub.vouchertype.VouTypeVO[] arg0) 
		throws nc.vo.pub.BusinessException	{
	getImplVouType().insertArrayVouTypeVO(arg0);

	}
	public nc.vo.bankpub.vouchertype.VouTypeVO[] queryAllVouTypeVOs() 
		throws nc.vo.pub.BusinessException	{
		return 	getImplVouType().queryAllVouTypeVOs();

	}
	public void updateVouTypeVOByVO(nc.vo.bankpub.vouchertype.VouTypeVO arg0) 
		throws nc.vo.pub.BusinessException	{
	getImplVouType().updateVouTypeVOByVO(arg0);

	}

}