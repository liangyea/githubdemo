package nc.ui.bankpub.orgbanktonc;

import nc.ui.bankpub.selfbuttons.SynchronizeBtnVO;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.CardEventHandler;

/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * 
 * <p>
 * �ڴ˴���Ӵ����������Ϣ
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
	 * �˷�������������
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

	/**
	 * ע���Զ��尴ť
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
				showErrorMessage("����ѡ��˾��");
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
