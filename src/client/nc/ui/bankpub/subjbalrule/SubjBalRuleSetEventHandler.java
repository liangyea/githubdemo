package nc.ui.bankpub.subjbalrule;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.ISubjBalRuleSet;
import nc.ui.pub.bduifactory.BaseManageEHD;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.bankpub.subjbalrule.SubjBalRuleFactory;
import nc.vo.bankpub.subjbalrule.SubjBalRuleVO;

public class SubjBalRuleSetEventHandler extends BaseManageEHD {

	public SubjBalRuleSetEventHandler(BillManageUI billUI,
			IControllerBase control) {
		super(billUI, control);
		// TODO Auto-generated constructor stub
	}

	protected void onBoImport() throws Exception {
		if (getBufferData().getVOBufferSize() > 0) {
			if (MessageDialog.showOkCancelDlg(getBillUI(), "规则导入",
					"已经设置了对账规则，是否继续导入？", MessageDialog.ID_CANCEL) != UIDialog.ID_OK)
				return;
		}

		GlorgbookSelDlg dlg = new GlorgbookSelDlg(getBillUI());
		if (dlg.showModal() == UIDialog.ID_OK) {
			ISubjBalRuleSet subjBalRuleSet = (ISubjBalRuleSet) NCLocator
					.getInstance().lookup(ISubjBalRuleSet.class.getName());
			SubjBalRuleVO[] ruleVos = subjBalRuleSet.importRules(dlg
					.getPk_glorgbook());
			if (ruleVos != null) {

				getBillUI().getBufferData().addVOsToBuffer(
						SubjBalRuleFactory.createBillVos(ruleVos));
				
				getBillUI().setListHeadData(getBillUI().getBufferData().getAllHeadVOsFromBuffer());
				getBillUI().getBufferData().updateView();

				MessageDialog.showHintDlg(getBillUI(), "提示", "成功导入对账规则，共["
						+ ruleVos.length + "]条！");
			}

		}
		// getUIController().get
		//		
		// UIRefPane refPane = new UIRefPane();
		// refPane.setRefNodeName(IBdinfoConst.GLORGBOOK);

	}

}
