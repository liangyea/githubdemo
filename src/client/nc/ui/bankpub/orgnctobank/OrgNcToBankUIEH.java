package nc.ui.bankpub.orgnctobank;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.itf.dap.priv.IGLOrgBook;
import nc.itf.uap.bd.multibook.IGLOrgBookAcc;
import nc.itf.uif.pub.IUifService;
import nc.ui.bankpub.selfbuttons.IButtonID;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;
import nc.vo.bankpub.cache.OrgNcToBankCache;
import nc.vo.bankpub.orgnctobank.OrgNcToBankVO;
import nc.vo.bd.b04.DeptdocVO;
import nc.vo.bd.b54.GlorgVO;
import nc.vo.bd.b54.GlorgbookVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;

/**
 * 
 * ������AbstractMyEventHandler�������ʵ���࣬ ��Ҫ�������˰�ť��ִ�ж������û����Զ���Щ����������Ҫ�����޸�
 * 
 * @author author
 * @version tempProject version
 */

public class OrgNcToBankUIEH extends CardEventHandler {

	public OrgNcToBankUIEH(BillCardUI billUI,
			ICardController control) {
		super(billUI, control);
	}

	protected void onBoElse(int intBtn) throws Exception {
		switch (intBtn) {
		case IButtonID.SYNCRONIZE:
			onSynchronizeData();
			refreshUI();
			MessageDialog.showHintDlg(getBillUI(), "��ʾ", "�Ӳ��ŵ���ͬ�����ݳɹ���");
			break;
		}
	}

	private void refreshUI() throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(
				IUifService.class.getName());
		OrgNcToBankVO[] queryVos = (OrgNcToBankVO[]) uifService
				.queryByCondition(OrgNcToBankVO.class, "dr = 0");

		getBufferData().clear();
//		AggregatedValueObject vo = getBillUI().getVOFromUI();
		AggregatedValueObject vo = (AggregatedValueObject) Class.forName(
				getUIController().getBillVoName()[0]).newInstance();
		vo.setChildrenVO(queryVos);
		getBufferData().addVOToBuffer(vo);
		updateBuffer();
	}

	protected void onBoBodyQuery(String strWhere) throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(IUifService.class.getName());
		GlorgbookVO[] glorgbookVOs = getInitNcBankContrastVOs();
		if (glorgbookVOs == null) return;
//		NcBankContrastVO[] queryVos = (NcBankContrastVO[]) getBusiDelegator().queryByCondition(
//				Class.forName(getUIController().getBillVoName()[2]),
//				strWhere == null ? "" : strWhere);
//		NcBankContrastVO[] queryVos = (NcBankContrastVO[]) uifService.queryAll(NcBankContrastVO.class);
		OrgNcToBankVO[] queryVos = OrgNcToBankCache.getInstance().getAllOrgNcToBanks();
		Map<String, OrgNcToBankVO> ncbvomap = new HashMap<String, OrgNcToBankVO>();
		if (queryVos != null) {
			for (int i = 0; i < queryVos.length; i++) {
				ncbvomap.put(queryVos[i].pk_corp, queryVos[i]);
			}
		}
		if (queryVos == null || queryVos.length == 0 || glorgbookVOs.length > queryVos.length) {
			queryVos = new OrgNcToBankVO[glorgbookVOs.length];
			for (int j = 0; j < glorgbookVOs.length; j++) {
				if (ncbvomap.containsKey(glorgbookVOs[j].m_pk_glorgbook)) {
					queryVos[j] = ncbvomap.get(glorgbookVOs[j].m_pk_glorgbook);
				} else {
					queryVos[j] = new OrgNcToBankVO();
					queryVos[j].setPk_corp(glorgbookVOs[j].m_pk_glorgbook);
					queryVos[j].setClerkcode("##");
					queryVos[j].setBankcode("##");
					queryVos[j].setBankname("##");
					queryVos[j].setClerkcode("##");
					queryVos[j].setStatus(VOStatus.NEW);
					String pk = uifService.insert(queryVos[j]);
					queryVos[j].setPrimaryKey(pk);
				}
			}
		}
		uifService.updateAry(queryVos);
		OrgNcToBankCache.getInstance().updateVersion();
		
		getBufferData().clear();
		AggregatedValueObject vo = (AggregatedValueObject) Class.forName(
				getUIController().getBillVoName()[0]).newInstance();
		vo.setChildrenVO(queryVos);
		getBufferData().addVOToBuffer(vo);
		updateBuffer();
	}

	private GlorgbookVO[] getInitNcBankContrastVOs() throws Exception {
		IGLOrgBook igb = (IGLOrgBook) NCLocator.getInstance().lookup(
				IGLOrgBook.class.getName());
		GlorgVO[] glorgVOs = igb.queryGlorgs(null);
		String pk_glorgs[] = null;
		if (glorgVOs != null) {
			pk_glorgs = new String[glorgVOs.length];
			for (int i = 0; i < glorgVOs.length; i++) {
				pk_glorgs[i] = glorgVOs[i].getPrimaryKey();
			}
		}
		IGLOrgBookAcc igf = (IGLOrgBookAcc) NCLocator.getInstance().lookup(
				IGLOrgBookAcc.class.getName());
		GlorgbookVO[] glvos = igf.getGLOrgBookVOsByPk_GlOrgs(pk_glorgs, null);
		return glvos;
	}

	protected void onBoSave() throws Exception {
		this.getBillCardPanelWrapper().getBillCardPanel().getBillData().dataNotNullValidate();
		super.onBoSave();
		OrgNcToBankCache.getInstance().updateVersion();
	}	   	
	
	protected void onBoRefresh() throws Exception {
		refreshUI();
	}

	protected void onSynchronizeData() throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(
				IUifService.class.getName());
		OrgNcToBankVO[] queryVos = (OrgNcToBankVO[]) uifService
				.queryByCondition(OrgNcToBankVO.class, "dr = 0");
		DeptdocVO[] deptvos = (DeptdocVO[]) uifService
		.queryByCondition(DeptdocVO.class, "dr = 0");
		if (deptvos != null && deptvos.length > 0) {
			/*
			 * deptsMap������������ա����У��������ŵ��������Ѿ������ڵĲ���
			 */
			Map<String, DeptdocVO> deptsMap = new HashMap<String, DeptdocVO>();
			for (int i = 0; i < deptvos.length; i++) {
				deptsMap.put(deptvos[i].m_pk_corp + deptvos[i].m_pk_deptdoc, deptvos[i]);
			}
			
			/*
			 * queryvosMap�����洢���ŵ������Ѿ���������Ӧ�ĺ�������Ĳ��ŵ�����¼
			 */
			Map<String, String> queryvosMap = new HashMap<String, String>();
			Vector<OrgNcToBankVO> obtnvector = new Vector<OrgNcToBankVO>();
			// ȡ���Ѿ����ڵ���Ч����
			if (queryVos != null && queryVos.length > 0) {
				// �õ���Ҫɾ���Ķ�������
				Vector<String> delpks = new Vector<String>();
				for (int j = 0; j < queryVos.length; j++) {
					if (deptsMap.containsKey(queryVos[j].pk_corp + queryVos[j].pk_deptdoc)) {
						obtnvector.addElement(queryVos[j]);
						queryvosMap.put(queryVos[j].pk_corp + queryVos[j].pk_deptdoc, "exist");
					} else {
						delpks.addElement(queryVos[j].pk_orgnctobank);
					}
				}
				if (delpks.size() > 0) {
					StringBuffer whereSql = new StringBuffer().append("pk_orgnctobank in (");
					for (int i = 0; i < delpks.size(); i++) {
						whereSql.append("'").append(delpks.elementAt(i)).append("',");
					}
					whereSql.delete(whereSql.length()-1, whereSql.length());
					whereSql.append(")");
					uifService.deleteByWhereClause(OrgNcToBankVO.class, whereSql.toString());
				}
			}

			// �����д����õĶ���
			if (queryVos == null || queryVos.length == 0 || deptvos.length > queryvosMap.size()) {
				Vector<OrgNcToBankVO> newvos = new Vector<OrgNcToBankVO>();
				for (int k = 0; k < deptvos.length; k++) {
					if (!queryvosMap.containsKey(deptvos[k].m_pk_corp + deptvos[k].m_pk_deptdoc)) {
						OrgNcToBankVO obtnvo = new OrgNcToBankVO();
						obtnvo.setBankcode("#");
						obtnvo.setBankname("#");
						obtnvo.setClerkcode("#");
						obtnvo.setPk_corp(deptvos[k].m_pk_corp);
						obtnvo.setPk_deptdoc(deptvos[k].m_pk_deptdoc);
						obtnvo.setDr(0);
						obtnvo.setStatus(VOStatus.NEW);
						newvos.addElement(obtnvo);
					}
				}
				OrgNcToBankVO[] tmpObtnvos = new OrgNcToBankVO[newvos.size()];
				newvos.copyInto(tmpObtnvos);
				String[] pks = uifService.insertAry(tmpObtnvos);
				for (int m = 0; m < pks.length; m++) {
					tmpObtnvos[m].setPrimaryKey(pks[m]);
					obtnvector.addElement(tmpObtnvos[m]);
				}
			}
			
			queryVos = new OrgNcToBankVO[obtnvector.size()];
			obtnvector.copyInto(queryVos);
			uifService.updateAry(queryVos);
		} else {
			uifService.deleteByWhereClause(OrgNcToBankVO.class, "");
		}
		OrgNcToBankCache.getInstance().updateVersion();
	}
}