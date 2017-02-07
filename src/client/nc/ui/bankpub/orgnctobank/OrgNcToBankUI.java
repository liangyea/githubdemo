package nc.ui.bankpub.orgnctobank;

import nc.ui.bankpub.selfbuttons.SynchronizeBtnVO;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.CardEventHandler;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * 
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 
 * 
 * @author author
 * @version tempProject version
 */

public class OrgNcToBankUI extends nc.ui.trade.card.BillCardUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5302145242298882064L;
	
	public OrgNcToBankUI() {
		super();
	}
	
	/**
	 * 此方法关联控制器
	 */
	protected ICardController createController() {
		return new OrgNcToBankUICtrl();
	}

	protected CardEventHandler createEventHandler() {
		return new OrgNcToBankUIEH(this, getUIControl());
	}

	public String getRefBillType() {
		return null;
	}

	/**
	 * 注册自定义按钮
	 */
	protected void initPrivateButton() {
		addPrivateButton(new SynchronizeBtnVO().getButtonVO());
	}

	/**
	 * 修改此方法增加后台校验
	 */
	public Object getUserObject() {
		return null;
	}

	/**
	 * 修改此方法初始化模板控件数据
	 */
	protected void initSelfData() {
	}

	public void setDefaultData() throws Exception {
	}

}
