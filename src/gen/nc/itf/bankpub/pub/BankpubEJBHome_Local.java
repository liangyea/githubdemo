package nc.itf.bankpub.pub;

/**
 * business service ejb wrapper 
 * Created by EJBGenerator
 * based on velocity technology
 */

public class BankpubEJBHome_Local extends nc.bs.mw.naming.HomeBase
    implements BankpubEJBHome{
   
	public BankpubEJBHome_Local() {
		super();
	}
	
	protected Object _createNewObject(){        
        return createEjb(nc.itf.bankpub.pub.BankpubEJB_Local.class,nc.itf.bankpub.pub.BankpubEJBEjbBean.class);
    }

	public nc.itf.bankpub.pub.BankpubEJBEjbObject create() throws javax.ejb.CreateException{
	    	
	    if (isStatelessSessBean()) {
			synchronized (this) {
				Object o =
					ftechStateLessEjbObject(
						nc.itf.bankpub.pub.BankpubEJB_Local.class);
				if (o != null) {
					return (nc.itf.bankpub.pub.BankpubEJBEjbObject) o;
				}
			}
		}
		nc.itf.bankpub.pub.BankpubEJB_Local local = (nc.itf.bankpub.pub.BankpubEJB_Local) _createNewObject();
		nc.itf.bankpub.pub.BankpubEJBEjbBean bean = (nc.itf.bankpub.pub.BankpubEJBEjbBean) local.getEjb();
		
		try {
            bean.ejbCreate();
        } catch(RuntimeException e) {
        	throw e;
        } catch (Exception e) {
        	nc.bs.logging.Logger.error("ejb create error", e);
            throw new javax.ejb.CreateException(e.getMessage());
        }
		return local;
	}	
}