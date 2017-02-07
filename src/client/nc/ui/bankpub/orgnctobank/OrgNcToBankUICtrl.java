package nc.ui.bankpub.orgnctobank;

import nc.ui.bankpub.selfbuttons.IButtonID;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.bankpub.orgnctobank.OrgNcToBankVO;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b> UI��������</b><br>
 * 
 * <p>
 * ���ý��水ť�����ݣ��Ƿ�ƽ̨��ص���Ϣ
 * </p>
 * <br>
 * 
 * 
 * @author authorName
 * @version tempProject version
 */

public class OrgNcToBankUICtrl implements ICardController, ISingleController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	/**
	 * ���ý��水ť
	 */
	public int[] getCardButtonAry() {
		return new int[] { IBillButton.Query, IBillButton.Edit,
				IBillButton.Save, IBillButton.Cancel, IBillButton.Refresh,
				IButtonID.SYNCRONIZE, IBillButton.Print };
	}

	public boolean isShowCardRowNo() {
		return true;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return "nc_vs_bank";
	}

	public String[] getBillVoName() {
		return new String[] { HYBillVO.class.getName(),
				OrgNcToBankVO.class.getName(),
				OrgNcToBankVO.class.getName() };
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	/**
	 * �Ƿ�ƽ̨�޹�
	 */
	public int getBusinessActionType() {
		return IBusinessActionType.BD;
	}

	public String getChildPkField() {
		return null;
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return null;
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
	}

	public boolean isExistBillStatus() {
		return false;
	}

	public boolean isLoadCardFormula() {
		return false;
	}

	/**
	 * �Ƿ񵥱�
	 * 
	 * @return boolean true:�����壬false:����ͷ
	 */
	public boolean isSingleDetail() {
		return true; // ����ͷ
	}

}
