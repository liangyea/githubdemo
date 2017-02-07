package nc.ui.bankpub.tallyrole;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.bankpub.tallyrole.TallyRoleVO;
import nc.vo.trade.pub.HYBillVO;

public class TallyRoleSetControl extends AbstractManageController implements ISingleController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	/**
	 * 设置界面按钮
	 */
	public int[] getCardButtonAry() {
	        return new int[]{
//			IBillButton.Query,
			IBillButton.Add,
			IBillButton.Edit,
//			IBillButton.Line,
			IBillButton.Save,
			IBillButton.Cancel,
			IBillButton.Delete,
			
			IBillButton.Brow,
//			IBillButton.Prev,
//			
//			IBillButton.Next,
//			
//			IBillButton.Last,
			IBillButton.Refresh,
			
			IBillButton.Return
//			IBillButton.Refbill,
//			IBillButton.SelAll,
//			IBillButton.SelNone,

//			IBillButton.AddLine,
//			IBillButton.DelLine
	        };
	}

	public boolean isShowCardRowNo() {
		return true;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return "20029921";
	}


	public String[] getBillVoName() {
			return new String[]{
				HYBillVO.class.getName(),
				TallyRoleVO.class.getName(),
				TallyRoleVO.class.getName()
			};
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
		return "pk_tallyroles";
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return "pk_tallyroles";
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
	 * @return boolean true:单表体，false:单表头
	 */ 
	public boolean isSingleDetail() {
		return false; 
	}

	public String[] getListBodyHideCol() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getListButtonAry() {
		// TODO Auto-generated method stub
		return new int[]{
    			IBillButton.Add,
    			IBillButton.Edit,
    			IBillButton.Delete,
    			IBillButton.Card//,
//    			IBillButton.Refresh,
//    			IBillButton.Print//,
//    			IBillButton.ImportBill
//    			IBillButton.ExportBill,
    			};
	}

	public String[] getListHeadHideCol() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isShowListRowNo() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isShowListTotal() {
		// TODO Auto-generated method stub
		return false;
	}

}
