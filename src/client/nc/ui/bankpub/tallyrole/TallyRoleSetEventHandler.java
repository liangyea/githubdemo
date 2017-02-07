package nc.ui.bankpub.tallyrole;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bduifactory.BaseManageEHD;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.pub.SuperVO;
public class TallyRoleSetEventHandler extends BaseManageEHD {

	public TallyRoleSetEventHandler(BillManageUI billUI,
			IControllerBase control) {
		super(billUI, control);
		// TODO Auto-generated constructor stub
	}
	protected SuperVO[] loadInitVOs() throws Exception, ClassNotFoundException {
        SuperVO[] queryVos;
        String strWhere = " isnull(dr,0)=0 and pk_corp = '" + ClientEnvironment.getInstance().getCorporation().getPk_corp() + "' ";
        queryVos = getBusiDelegator().queryHeadAllData(
                Class.forName(getUIController().getBillVoName()[1]),
                getUIController().getBillType(), strWhere);
        return queryVos;
    }
	protected void onBoSave() throws Exception {
//		AggregatedValueObject billVO = getBillUI().getChangedVOFromUI();
//		TallyRoleVO roleVo = (TallyRoleVO) billVO.getParentVO();
//		roleVo.setPk_corp(ClientEnvironment.getInstance().getCorporation().getPk_corp());
		getBillCardPanelWrapper().getBillCardPanel().setHeadItem("pk_corp", ClientEnvironment.getInstance().getCorporation().getPk_corp());
		super.onBoSave();
	}

}
