package nc.ui.bankpub.orgbanktonc;

import nc.ui.bankpub.selfbuttons.SynchronizeBtnVO;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.trade.base.IBillOperate;
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

public class OrgBankToNcUI extends nc.ui.trade.card.BillCardUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798504641893408662L;
	
	public OrgBankToNcUI() {
		super();
	}
	
	/**
	 * 此方法关联控制器
	 */
	protected ICardController createController() {
		return new OrgBankToNcUICtrl();
	}

	protected CardEventHandler createEventHandler() {
		return new OrgBankToNcUIEH(this, getUIControl());
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

	/**
	 * 注册自定义按钮
	 */
	protected void initPrivateButton() {
		addPrivateButton(new SynchronizeBtnVO().getButtonVO());
	}

	public void doQueryAction(ILinkQueryData querydata) {
		String billId = querydata.getBillID();
		if (billId != null) {
			try {
				getBufferData().addVOToBuffer(loadHeadData(billId));
				getBufferData().setCurrentRow(getBufferData().getCurrentRow());
				setBillOperate(IBillOperate.OP_NO_ADDANDEDIT);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public boolean beforeEdit(nc.ui.pub.bill.BillEditEvent e) {
		if (e.getKey().equals("deptcode")) {
			String pk_corp = (String) getBillCardPanel().getBodyValueAt(e.getRow(), "pk_corp");
			if (pk_corp == null || pk_corp.length() == 0) {
				showErrorMessage("请先选择公司！");
				return false;
			}
			((UIRefPane)getBillCardPanel().getBodyItem("deptcode").getComponent()).getRefModel().setPk_corp(pk_corp);			
		}
		return true;
	}
	
	public  void afterEdit(nc.ui.pub.bill.BillEditEvent e) {
		if (e.getKey().equals("corpname")) {
			getBillCardPanel().setBodyValueAt(null, e.getRow(), "deptcode");
			getBillCardPanel().setBodyValueAt(null, e.getRow(), "deptname");
			getBillCardPanel().setBodyValueAt(null, e.getRow(), "pk_deptdoc");
		}
		
	}
}
