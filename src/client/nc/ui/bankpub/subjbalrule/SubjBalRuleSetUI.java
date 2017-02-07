package nc.ui.bankpub.subjbalrule;

import nc.ui.pub.bduifactory.BaseManageUI;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bsdelegate.BDBusinessDelegator;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class SubjBalRuleSetUI extends BaseManageUI {

	@Override
	protected AbstractManageController createController() {
		// TODO Auto-generated method stub
		return new SubjBalRuleSetControl();
	}

	protected ManageEventHandler createEventHandler() {
	    return new SubjBalRuleSetEventHandler(this, getUIControl());
          }

	protected BusinessDelegator createBusinessDelegator() {
//    	if(getUIControl().getBusinessActionType() == nc.ui.trade.businessaction.IBusinessActionType.BD)
    		return new BDBusinessDelegator();
    	
    }
	
	public String getRefBillType() {
		return null;
	}
	
	/**
	 * �޸Ĵ˷�����ʼ��ģ��ؼ�����
	 */
	protected void initSelfData() {

	}

	public void setDefaultData() throws Exception {
	}
	
	/**
	 * �޸Ĵ˷������Ӻ�̨У��
	 */
	public Object getUserObject() {
		return null;
	}

	@Override
	public void setBodySpecialData(CircularlyAccessibleValueObject[] vos) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject vo, int intRow) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTotalHeadSpecialData(CircularlyAccessibleValueObject[] vos) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
