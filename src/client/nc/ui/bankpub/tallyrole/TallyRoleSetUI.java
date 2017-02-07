package nc.ui.bankpub.tallyrole;

import nc.ui.pub.bduifactory.BaseManageUI;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bsdelegate.BDBusinessDelegator;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class TallyRoleSetUI extends BaseManageUI {

	@Override
	protected AbstractManageController createController() {
		// TODO Auto-generated method stub
		return new TallyRoleSetControl();
	}

	protected ManageEventHandler createEventHandler() {
	    return new TallyRoleSetEventHandler(this, getUIControl());
          }

	protected BusinessDelegator createBusinessDelegator() {
//    	if(getUIControl().getBusinessActionType() == nc.ui.trade.businessaction.IBusinessActionType.BD)
    		return new BDBusinessDelegator();
    	
    }
	
	public String getRefBillType() {
		return null;
	}
	
	/**
	 * 修改此方法初始化模板控件数据
	 */
	protected void initSelfData() {

	}

	public void setDefaultData() throws Exception {
	}
	
	/**
	 * 修改此方法增加后台校验
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
