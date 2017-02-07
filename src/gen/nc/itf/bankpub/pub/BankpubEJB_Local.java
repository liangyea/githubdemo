package nc.itf.bankpub.pub;


/**
 * business service ejb wrapper 
 * Created by EJBGenerator
 * based on velocity technology
 */

public class BankpubEJB_Local extends nc.bs.mw.naming.BeanBase
    implements nc.itf.bankpub.pub.BankpubEJBEjbObject {
   
  public BankpubEJB_Local() {
	super();
  }

  private nc.itf.bankpub.pub.BankpubEJBEjbBean _getBeanObject() throws java.rmi.RemoteException {
    return (nc.itf.bankpub.pub.BankpubEJBEjbBean) getEjb();
  }

  public void delete() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delete    );
    Exception er = null;
	try{
				_getBeanObject().delete();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delete, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void maintainBankContrast(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_maintainBankContrast$BankContrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().maintainBankContrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_maintainBankContrast$BankContrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void deleteBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankContrastVO$BankContrastVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteBankContrastVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankContrastVO$BankContrastVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOs(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOs$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.bankcontrast.BankContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.bankcontrast.BankContrastVO[])_getBeanObject().queryBankContrastVOs(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOs$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void insertBankContrastVOs(nc.vo.bankpub.bankcontrast.BankContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertBankContrastVOs$BankContrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertBankContrastVOs(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertBankContrastVOs$BankContrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void updateBankContrastVO(nc.vo.bankpub.bankcontrast.BankContrastVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateBankContrastVO$BankContrastVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateBankContrastVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateBankContrastVO$BankContrastVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsDept(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOsDept$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.bankcontrast.BankContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.bankcontrast.BankContrastVO[])_getBeanObject().queryBankContrastVOsDept(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOsDept$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.bankcontrast.BankContrastVO[] queryBankContrastVOsByPkglorgbook(java.lang.String arg0 ,java.lang.String arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOsByPkglorgbook$String_arg0$String_arg1    );
    Exception er = null;
	nc.vo.bankpub.bankcontrast.BankContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.bankcontrast.BankContrastVO[])_getBeanObject().queryBankContrastVOsByPkglorgbook(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankContrastVOsByPkglorgbook$String_arg0$String_arg1, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void deleteBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankSubjContrastVO$BankSubjContrastVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteBankSubjContrastVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankSubjContrastVO$BankSubjContrastVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertBankSubjContrastVOs(nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertBankSubjContrastVOs$BankSubjContrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertBankSubjContrastVOs(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertBankSubjContrastVOs$BankSubjContrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryAllBankSubjContrastVO() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllBankSubjContrastVO    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.BankSubjContrastVO[])_getBeanObject().queryAllBankSubjContrastVO();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllBankSubjContrastVO, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateBankSubjContrastVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateBankSubjContrastVO$BankSubjContrastVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateBankSubjContrastVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateBankSubjContrastVO$BankSubjContrastVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcolshow.SubjColShowVO[] queryAllSubjColShowVO() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSubjColShowVO    );
    Exception er = null;
	nc.vo.bankpub.subjcolshow.SubjColShowVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcolshow.SubjColShowVO[])_getBeanObject().queryAllSubjColShowVO();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSubjColShowVO, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void insertSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertSubjColShowVO$SubjColShowVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertSubjColShowVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertSubjColShowVO$SubjColShowVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void updateSubjColShowVO(nc.vo.bankpub.subjcolshow.SubjColShowVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSubjColShowVO$SubjColShowVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateSubjColShowVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSubjColShowVO$SubjColShowVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public java.lang.String queryBankSubjContrastVOByVO(nc.vo.bankpub.subjcontrast.BankSubjContrastVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankSubjContrastVOByVO$BankSubjContrastVO_arg0    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().queryBankSubjContrastVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankSubjContrastVOByVO$BankSubjContrastVO_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void copySubjcontrast(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String[] arg2 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrast$String_arg0$String_arg1$StringS_arg2    );
    Exception er = null;
	try{
				_getBeanObject().copySubjcontrast(arg0 ,arg1  ,arg2 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrast$String_arg0$String_arg1$StringS_arg2, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void deleteBankSubjByPksystempkglorgbook(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankSubjByPksystempkglorgbook$String_arg0$StringS_arg1    );
    Exception er = null;
	try{
				_getBeanObject().deleteBankSubjByPksystempkglorgbook(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteBankSubjByPksystempkglorgbook$String_arg0$StringS_arg1, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] queryBankSubjContrastVOByPkglorgbook(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankSubjContrastVOByPkglorgbook$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.BankSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.BankSubjContrastVO[])_getBeanObject().queryBankSubjContrastVOByPkglorgbook(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBankSubjContrastVOByPkglorgbook$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void saveAll(nc.vo.bankpub.currcontrast.CurrContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_saveAll$CurrContrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().saveAll(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_saveAll$CurrContrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.currcontrast.CurrContrastVO[] queryAll() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAll    );
    Exception er = null;
	nc.vo.bankpub.currcontrast.CurrContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.currcontrast.CurrContrastVO[])_getBeanObject().queryAll();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAll, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.dealcode.DealCodeVO[] queryDealCode() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryDealCode    );
    Exception er = null;
	nc.vo.bankpub.dealcode.DealCodeVO[] o = null;
	try{
				o = (nc.vo.bankpub.dealcode.DealCodeVO[])_getBeanObject().queryDealCode();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryDealCode, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateDealCode(nc.vo.bankpub.dealcode.DealCodeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateDealCode$DealCodeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateDealCode(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateDealCode$DealCodeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public java.lang.String queryDealCodeByIndex(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryDealCodeByIndex$String_arg0    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().queryDealCodeByIndex(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryDealCodeByIndex$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryNCSubjContrastbyPKDept(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryNCSubjContrastbyPKDept$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.NCSubjContrastVO[])_getBeanObject().queryNCSubjContrastbyPKDept(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryNCSubjContrastbyPKDept$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bd.b02.AccsubjVO[] queryAccsubjbyPKDept(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAccsubjbyPKDept$String_arg0    );
    Exception er = null;
	nc.vo.bd.b02.AccsubjVO[] o = null;
	try{
				o = (nc.vo.bd.b02.AccsubjVO[])_getBeanObject().queryAccsubjbyPKDept(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAccsubjbyPKDept$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bd.b04.DeptdocVO[] queryAllDeptdoc() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllDeptdoc    );
    Exception er = null;
	nc.vo.bd.b04.DeptdocVO[] o = null;
	try{
				o = (nc.vo.bd.b04.DeptdocVO[])_getBeanObject().queryAllDeptdoc();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllDeptdoc, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void deleteEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteEspecialSubjVOByVO$EspecialSubjVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteEspecialSubjVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteEspecialSubjVOByVO$EspecialSubjVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArrayEspecialSubjVO(nc.vo.bankpub.especialsubj.EspecialSubjVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayEspecialSubjVO$EspecialSubjVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArrayEspecialSubjVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayEspecialSubjVO$EspecialSubjVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.especialsubj.EspecialSubjVO[] queryAllEspecialSubjVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllEspecialSubjVOs    );
    Exception er = null;
	nc.vo.bankpub.especialsubj.EspecialSubjVO[] o = null;
	try{
				o = (nc.vo.bankpub.especialsubj.EspecialSubjVO[])_getBeanObject().queryAllEspecialSubjVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllEspecialSubjVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateEspecialSubjVOByVO(nc.vo.bankpub.especialsubj.EspecialSubjVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateEspecialSubjVOByVO$EspecialSubjVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateEspecialSubjVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateEspecialSubjVOByVO$EspecialSubjVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void deleteFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteFileDefineVOByVO$FileDefineVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteFileDefineVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteFileDefineVOByVO$FileDefineVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArrayFileDefineVO(nc.vo.bankpub.filedefine.FileDefineVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayFileDefineVO$FileDefineVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArrayFileDefineVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayFileDefineVO$FileDefineVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.filedefine.FileDefineVO[] queryAllFileDefineVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllFileDefineVOs    );
    Exception er = null;
	nc.vo.bankpub.filedefine.FileDefineVO[] o = null;
	try{
				o = (nc.vo.bankpub.filedefine.FileDefineVO[])_getBeanObject().queryAllFileDefineVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllFileDefineVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateFileDefineVOByVO(nc.vo.bankpub.filedefine.FileDefineVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateFileDefineVOByVO$FileDefineVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateFileDefineVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateFileDefineVOByVO$FileDefineVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public java.lang.String[] split(java.lang.String arg0 ,java.lang.String arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_split$String_arg0$String_arg1    );
    Exception er = null;
	java.lang.String[] o = null;
	try{
				o = (java.lang.String[])_getBeanObject().split(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_split$String_arg0$String_arg1, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String getStrByPk(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getStrByPk$String_arg0$String_arg1$String_arg2$String_arg3    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().getStrByPk(arg0 ,arg1  ,arg2  ,arg3 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getStrByPk$String_arg0$String_arg1$String_arg2$String_arg3, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String getPubInitSys(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPubInitSys$String_arg0    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().getPubInitSys(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPubInitSys$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String getStrByPk1(java.lang.String arg0 ,java.lang.String arg1  ,java.lang.String arg2  ,java.lang.String arg3 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getStrByPk1$String_arg0$String_arg1$String_arg2$String_arg3    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().getStrByPk1(arg0 ,arg1  ,arg2  ,arg3 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getStrByPk1$String_arg0$String_arg1$String_arg2$String_arg3, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String getPkcorpByPkglorgbook(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPkcorpByPkglorgbook$String_arg0    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().getPkcorpByPkglorgbook(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPkcorpByPkglorgbook$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String[] getPkglorgbookByPkcorp(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPkglorgbookByPkcorp$String_arg0    );
    Exception er = null;
	java.lang.String[] o = null;
	try{
				o = (java.lang.String[])_getBeanObject().getPkglorgbookByPkcorp(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getPkglorgbookByPkcorp$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.currrate.CurrrateVO[] readCurrRateFile(nc.vo.bankpub.systemtype.SystemTypeVO arg0 ,nc.vo.pub.lang.UFDate arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_readCurrRateFile$SystemTypeVO_arg0$UFDate_arg1    );
    Exception er = null;
	nc.vo.bankpub.currrate.CurrrateVO[] o = null;
	try{
				o = (nc.vo.bankpub.currrate.CurrrateVO[])_getBeanObject().readCurrRateFile(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_readCurrRateFile$SystemTypeVO_arg0$UFDate_arg1, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void ExcuteUpdateSQL(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_ExcuteUpdateSQL$String_arg0    );
    Exception er = null;
	try{
				_getBeanObject().ExcuteUpdateSQL(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_ExcuteUpdateSQL$String_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.pub.lang.UFDate getServerDate() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getServerDate    );
    Exception er = null;
	nc.vo.pub.lang.UFDate o = null;
	try{
				o = (nc.vo.pub.lang.UFDate)_getBeanObject().getServerDate();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getServerDate, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bd.b02.AccsubjVO[] getNcAccsujVosByCondition(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getNcAccsujVosByCondition$String_arg0    );
    Exception er = null;
	nc.vo.bd.b02.AccsubjVO[] o = null;
	try{
				o = (nc.vo.bd.b02.AccsubjVO[])_getBeanObject().getNcAccsujVosByCondition(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getNcAccsujVosByCondition$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void deleteRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteRespondCodeVOByVO$RespondCodeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteRespondCodeVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteRespondCodeVOByVO$RespondCodeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArrayRespondCodeVO(nc.vo.bankpub.respondcode.RespondCodeVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayRespondCodeVO$RespondCodeVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArrayRespondCodeVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayRespondCodeVO$RespondCodeVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.respondcode.RespondCodeVO[] queryAllRespondCodeVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllRespondCodeVOs    );
    Exception er = null;
	nc.vo.bankpub.respondcode.RespondCodeVO[] o = null;
	try{
				o = (nc.vo.bankpub.respondcode.RespondCodeVO[])_getBeanObject().queryAllRespondCodeVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllRespondCodeVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateRespondCodeVOByVO(nc.vo.bankpub.respondcode.RespondCodeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateRespondCodeVOByVO$RespondCodeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateRespondCodeVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateRespondCodeVOByVO$RespondCodeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public java.lang.String getRespondInfoByRespongCode(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getRespondInfoByRespongCode$String_arg0    );
    Exception er = null;
	java.lang.String o = null;
	try{
				o = (java.lang.String)_getBeanObject().getRespondInfoByRespongCode(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_getRespondInfoByRespongCode$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void insert(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insert$RuleSetVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insert(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insert$RuleSetVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void update(nc.vo.bankpub.eliminaterule.RuleSetVO arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_update$RuleSetVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().update(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_update$RuleSetVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void delByPK(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delByPK$String_arg0    );
    Exception er = null;
	try{
				_getBeanObject().delByPK(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delByPK$String_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void delBySystemPK(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delBySystemPK$String_arg0    );
    Exception er = null;
	try{
				_getBeanObject().delBySystemPK(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delBySystemPK$String_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.eliminaterule.RuleSetVO[] queryBySystemPK(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBySystemPK$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.eliminaterule.RuleSetVO[] o = null;
	try{
				o = (nc.vo.bankpub.eliminaterule.RuleSetVO[])_getBeanObject().queryBySystemPK(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryBySystemPK$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void delValue(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delValue$String_arg0    );
    Exception er = null;
	try{
				_getBeanObject().delValue(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_delValue$String_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insValue(nc.vo.bankpub.eliminaterule.EliminateRuleVO arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insValue$EliminateRuleVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insValue(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insValue$EliminateRuleVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.filedefine.FileDefineVO[] queryCols(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryCols$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.filedefine.FileDefineVO[] o = null;
	try{
				o = (nc.vo.bankpub.filedefine.FileDefineVO[])_getBeanObject().queryCols(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryCols$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.eliminaterule.EliminateRuleVO[] queryValues(java.lang.String arg0) throws java.lang.Exception{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryValues$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.eliminaterule.EliminateRuleVO[] o = null;
	try{
				o = (nc.vo.bankpub.eliminaterule.EliminateRuleVO[])_getBeanObject().queryValues(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryValues$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof java.lang.Exception ){
    		throw (java.lang.Exception)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.subjbalrule.SubjBalRuleVO[] importRules(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_importRules$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.subjbalrule.SubjBalRuleVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjbalrule.SubjBalRuleVO[])_getBeanObject().importRules(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_importRules$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void copySubjcontrast(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrast$String_arg0$StringS_arg1    );
    Exception er = null;
	try{
				_getBeanObject().copySubjcontrast(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrast$String_arg0$StringS_arg1, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void clearSubjcontrast(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_clearSubjcontrast$String_arg0    );
    Exception er = null;
	try{
				_getBeanObject().clearSubjcontrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_clearSubjcontrast$String_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] querySubjContrast(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_querySubjContrast$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.NCSubjContrastVO[])_getBeanObject().querySubjContrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_querySubjContrast$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void copySubjcontrastFz(java.lang.String arg0 ,java.lang.String[] arg1 ) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrastFz$String_arg0$StringS_arg1    );
    Exception er = null;
	try{
				_getBeanObject().copySubjcontrastFz(arg0 ,arg1 );			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_copySubjcontrastFz$String_arg0$StringS_arg1, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] initSubjContrast(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_initSubjContrast$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.NCSubjContrastVO[])_getBeanObject().initSubjContrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_initSubjContrast$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public java.lang.String[] insertSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertSubjContrast$NCSubjContrastVOS_arg0    );
    Exception er = null;
	java.lang.String[] o = null;
	try{
				o = (java.lang.String[])_getBeanObject().insertSubjContrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertSubjContrast$NCSubjContrastVOS_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateSubjContrast(nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSubjContrast$NCSubjContrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateSubjContrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSubjContrast$NCSubjContrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] queryAllSubjContrast() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSubjContrast    );
    Exception er = null;
	nc.vo.bankpub.subjcontrast.NCSubjContrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.subjcontrast.NCSubjContrastVO[])_getBeanObject().queryAllSubjContrast();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSubjContrast, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void deleteSubjAssConstrasts(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteSubjAssConstrasts$SubjAssConstrastVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteSubjAssConstrasts(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteSubjAssConstrasts$SubjAssConstrastVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] querySubjAssConstrastBy(java.lang.String arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_querySubjAssConstrastBy$String_arg0    );
    Exception er = null;
	nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[])_getBeanObject().querySubjAssConstrastBy(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_querySubjAssConstrastBy$String_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] saveSubjAssConstrast(nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_saveSubjAssConstrast$SubjAssConstrastVOS_arg0    );
    Exception er = null;
	nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[] o = null;
	try{
				o = (nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO[])_getBeanObject().saveSubjAssConstrast(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_saveSubjAssConstrast$SubjAssConstrastVOS_arg0, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void deleteSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteSystemTypeVOByVO$SystemTypeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteSystemTypeVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteSystemTypeVOByVO$SystemTypeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArraySystemTypeVO(nc.vo.bankpub.systemtype.SystemTypeVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArraySystemTypeVO$SystemTypeVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArraySystemTypeVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArraySystemTypeVO$SystemTypeVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.systemtype.SystemTypeVO[] queryAllSystemTypeVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSystemTypeVOs    );
    Exception er = null;
	nc.vo.bankpub.systemtype.SystemTypeVO[] o = null;
	try{
				o = (nc.vo.bankpub.systemtype.SystemTypeVO[])_getBeanObject().queryAllSystemTypeVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllSystemTypeVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateSystemTypeVOByVO(nc.vo.bankpub.systemtype.SystemTypeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSystemTypeVOByVO$SystemTypeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateSystemTypeVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateSystemTypeVOByVO$SystemTypeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void deleteVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteVoucherElementVOByVO$VoucherElementVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().deleteVoucherElementVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_deleteVoucherElementVOByVO$VoucherElementVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArrayVoucherElementVO(nc.vo.bankpub.voucherelement.VoucherElementVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayVoucherElementVO$VoucherElementVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArrayVoucherElementVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayVoucherElementVO$VoucherElementVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.voucherelement.VoucherElementVO[] queryAllVoucherElementVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllVoucherElementVOs    );
    Exception er = null;
	nc.vo.bankpub.voucherelement.VoucherElementVO[] o = null;
	try{
				o = (nc.vo.bankpub.voucherelement.VoucherElementVO[])_getBeanObject().queryAllVoucherElementVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllVoucherElementVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateVoucherElementVOByVO(nc.vo.bankpub.voucherelement.VoucherElementVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateVoucherElementVOByVO$VoucherElementVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateVoucherElementVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateVoucherElementVOByVO$VoucherElementVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public void insertArrayVouTypeVO(nc.vo.bankpub.vouchertype.VouTypeVO[] arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayVouTypeVO$VouTypeVOS_arg0    );
    Exception er = null;
	try{
				_getBeanObject().insertArrayVouTypeVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_insertArrayVouTypeVO$VouTypeVOS_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
  public nc.vo.bankpub.vouchertype.VouTypeVO[] queryAllVouTypeVOs() throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllVouTypeVOs    );
    Exception er = null;
	nc.vo.bankpub.vouchertype.VouTypeVO[] o = null;
	try{
				o = (nc.vo.bankpub.vouchertype.VouTypeVO[])_getBeanObject().queryAllVouTypeVOs();			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);	  
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_queryAllVouTypeVOs, er);
 	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
	return o;
 }
  public void updateVouTypeVOByVO(nc.vo.bankpub.vouchertype.VouTypeVO arg0) throws nc.vo.pub.BusinessException{
    beforeCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateVouTypeVOByVO$VouTypeVO_arg0    );
    Exception er = null;
	try{
				_getBeanObject().updateVouTypeVOByVO(arg0);			
	}
	catch(Exception e){
		er = e;
	} catch(Throwable thr) {
		  nc.bs.logging.Logger.error("HGY:Unexpected error, tx will rb", thr);
		  er = new nc.bs.framework.exception.FrameworkEJBException("Fatal unknown error", thr);
	}
	try{
		afterCallMethod(nc.itf.bankpub.pub.BankpubEJB_Method_Const_Local.Method_updateVouTypeVOByVO$VouTypeVO_arg0, er);    	
	}
	catch(java.rmi.RemoteException remoteException){
		nc.bs.logging.Logger.error("HGY: Unexpected error when call afterCallMethod",  remoteException);
	} finally {
			}
	if( null != er ){
    	if( er instanceof nc.vo.pub.BusinessException ){
    		throw (nc.vo.pub.BusinessException)er;
    	}    

	if(er instanceof RuntimeException)
		throw (RuntimeException)er;
	else	
		throw new nc.bs.framework.exception.FrameworkEJBException(er.getMessage(),er);
	}
 }
}