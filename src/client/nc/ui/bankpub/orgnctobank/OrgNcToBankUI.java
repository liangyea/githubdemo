package nc.ui.bankpub.orgnctobank;

import nc.ui.bankpub.selfbuttons.SynchronizeBtnVO;
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

public class OrgNcToBankUI extends nc.ui.trade.card.BillCardUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5302145242298882064L;
	
	public OrgNcToBankUI() {
		super();
	}
	
	/**
	 * �˷�������������
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
	 * ע���Զ��尴ť
	 */
	protected void initPrivateButton() {
		addPrivateButton(new SynchronizeBtnVO().getButtonVO());
	}

	/**
	 * �޸Ĵ˷������Ӻ�̨У��
	 */
	public Object getUserObject() {
		return null;
	}

	/**
	 * �޸Ĵ˷�����ʼ��ģ��ؼ�����
	 */
	protected void initSelfData() {
	}

	public void setDefaultData() throws Exception {
	}

}
