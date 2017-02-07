package nc.ui.bankpub.orgnctobank;

import nc.ui.bankpub.selfbuttons.IButtonID;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.bankpub.orgnctobank.OrgNcToBankVO;
import nc.vo.trade.pub.HYBillVO;

/**
 * <b> UI控制器类</b><br>
 * 
 * <p>
 * 设置界面按钮，数据，是否平台相关等信息
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
	 * 设置界面按钮
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
	 * 是否平台无关
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
	 * 是否单表
	 * 
	 * @return boolean true:单表体，false:单表头
	 */
	public boolean isSingleDetail() {
		return true; // 单表头
	}

}
