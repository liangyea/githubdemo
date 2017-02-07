package nc.ui.bankpub.orgbanktonc;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.ui.bankpub.selfbuttons.IButtonID;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;
import nc.vo.bankpub.cache.OrgBankToNcCache;
import nc.vo.bankpub.orgbanktonc.OrgBankToNcVO;
import nc.vo.bd.b04.DeptdocVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;

/**
 * 
 *该类是AbstractMyEventHandler抽象类的实现类， 主要是重载了按钮的执行动作，用户可以对这些动作根据需要进行修改
 * 
 * @author author
 *@version tempProject version
 */

public class OrgBankToNcUIEH extends CardEventHandler {

	public OrgBankToNcUIEH(BillCardUI billUI, ICardController control) {
		super(billUI, control);
	}

	protected void onBoElse(int intBtn) throws Exception {
		switch (intBtn) {
		case IButtonID.SYNCRONIZE:
			onSynchronizeData();
			refreshUI();
			MessageDialog.showHintDlg(getBillUI(), "提示", "从部门档案同步数据成功！");
			break;
		}
	}
	
	private void refreshUI() throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(
				IUifService.class.getName());
		OrgBankToNcVO[] queryVos = (OrgBankToNcVO[]) uifService
				.queryByCondition(OrgBankToNcVO.class, "dr = 0");

		getBufferData().clear();
		AggregatedValueObject vo = getBillUI().getVOFromUI();
		vo.setChildrenVO(queryVos);
		getBufferData().addVOToBuffer(vo);
		updateBuffer();
	}

	protected void onBoSave() throws Exception {
		this.getBillCardPanelWrapper().getBillCardPanel().getBillData().dataNotNullValidate();
		// 核心机构编码必须唯一
		checkBankcodeUniqueness();
		super.onBoSave();
		OrgBankToNcCache.getInstance().updateVersion();
	}
	
	private void checkBankcodeUniqueness() throws Exception {
		AggregatedValueObject checkVO = getBillUI().getVOFromUI();
		OrgBankToNcVO[] bodyVos = (OrgBankToNcVO[]) checkVO.getChildrenVO();
		if (bodyVos == null || bodyVos.length == 0) return;
		Map<String, Integer> bankcodeMap = new HashMap<String, Integer>();
		for (int i = 0; i < bodyVos.length; i++) {
			if (bodyVos[i].bankcode.trim().equals("#")) continue;
			if (!bankcodeMap.containsKey(bodyVos[i].bankcode)){
				bankcodeMap.put(bodyVos[i].bankcode, new Integer(i+1));
			} else {
				throw new Exception("第" + (i+1) + "行和第" + bankcodeMap.get(bodyVos[i].bankcode) + "行的核心机构编码重复！");
			}
		}
	}
	
	protected void onBoRefresh() throws Exception {
		refreshUI();
	}
	
	protected void onSynchronizeData() throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(
				IUifService.class.getName());
		OrgBankToNcVO[] queryVos = (OrgBankToNcVO[]) uifService
				.queryByCondition(OrgBankToNcVO.class, "dr = 0");
		DeptdocVO[] deptvos = (DeptdocVO[]) uifService
		.queryByCondition(DeptdocVO.class, "dr = 0");
		if (deptvos != null && deptvos.length > 0) {
			/*
			 * deptsMap用来清除“对照”中有，而“部门档案”中已经不存在的部门
			 */
			Map<String, DeptdocVO> deptsMap = new HashMap<String, DeptdocVO>();
			for (int i = 0; i < deptvos.length; i++) {
				deptsMap.put(deptvos[i].m_pk_corp + deptvos[i].m_pk_deptdoc, deptvos[i]);
			}
			
			/*
			 * queryvosMap用来存储部门档案中已经设置了相应的核心网点的部门档案记录
			 */
			Map<String, String> queryvosMap = new HashMap<String, String>();
			Vector<OrgBankToNcVO> obtnvector = new Vector<OrgBankToNcVO>();
			// 取出已经存在的有效对照
			if (queryVos != null && queryVos.length > 0) {
				// 得到需要删除的对照主键
				Vector<String> delpks = new Vector<String>();
				for (int j = 0; j < queryVos.length; j++) {
					if (deptsMap.containsKey(queryVos[j].pk_corp + queryVos[j].pk_deptdoc)) {
						obtnvector.addElement(queryVos[j]);
						queryvosMap.put(queryVos[j].pk_corp + queryVos[j].pk_deptdoc, "exist");
					} else {
						delpks.addElement(queryVos[j].pk_orgbanktonc);
					}
				}
				if (delpks.size() > 0) {
					StringBuffer whereSql = new StringBuffer().append("pk_orgbanktonc in (");
					for (int i = 0; i < delpks.size(); i++) {
						whereSql.append("'").append(delpks.elementAt(i)).append("',");
					}
					whereSql.delete(whereSql.length()-1, whereSql.length());
					whereSql.append(")");
					uifService.deleteByWhereClause(OrgBankToNcVO.class, whereSql.toString());
				}
			}

			// 创建有待设置的对照
			if (queryVos == null || queryVos.length == 0 || deptvos.length > queryvosMap.size()) {
				Vector<OrgBankToNcVO> newvos = new Vector<OrgBankToNcVO>();
				for (int k = 0; k < deptvos.length; k++) {
					if (!queryvosMap.containsKey(deptvos[k].m_pk_corp + deptvos[k].m_pk_deptdoc)) {
						OrgBankToNcVO obtnvo = new OrgBankToNcVO();
						obtnvo.setBankcode("#");
						obtnvo.setBankname("#");
						obtnvo.setPk_corp(deptvos[k].m_pk_corp);
						obtnvo.setPk_deptdoc(deptvos[k].m_pk_deptdoc);
						obtnvo.setDr(0);
						obtnvo.setStatus(VOStatus.NEW);
						newvos.addElement(obtnvo);
					}
				}
				OrgBankToNcVO[] tmpObtnvos = new OrgBankToNcVO[newvos.size()];
				newvos.copyInto(tmpObtnvos);
				String[] pks = uifService.insertAry(tmpObtnvos);
				for (int m = 0; m < pks.length; m++) {
					tmpObtnvos[m].setPrimaryKey(pks[m]);
					obtnvector.addElement(tmpObtnvos[m]);
				}
			}
			
			queryVos = new OrgBankToNcVO[obtnvector.size()];
			obtnvector.copyInto(queryVos);
			uifService.updateAry(queryVos);
		} else {
			uifService.deleteByWhereClause(OrgBankToNcVO.class, "");
		}
		OrgBankToNcCache.getInstance().updateVersion();
	}
	
	public void onBoAdd(ButtonObject bo) throws Exception {
		getBillUI().setBillOperate(IBillOperate.OP_EDIT);
		onBoLineAdd();
	}
}