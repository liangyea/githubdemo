package nc.ui.bankpub.ncsubject.ass;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import nc.bs.dap.accrule.GLOrgBookBO;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.BankPubItfProxy;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pf.pub.DapCall;
import nc.ui.pf.pub.PfUIDataCache;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.beans.UIToolBar;
import nc.vo.bankpub.gl.AssVOs;
import nc.vo.bankpub.ncsubject.ass.SimplSubjAssConstrastVO;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bd.b52.GlbookVO;
import nc.vo.dap.accrule.GlOrgVO;
import nc.vo.fip.pub.Translator;
import nc.vo.pf.pub.BasedocVO;
import nc.vo.pub.ValueObject;

public class SubjAssConstrastEditDlg extends UIDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5117643185240545746L;

	private UIPanel ivjpnlCenterTable = null;

	private UIPanel ivjpnlCetTopLabel = null;

	private UIPanel ivjpnlTopControl = null;

	private UITablePane ivjtbpnlAssConstrast = null;

	private JPanel ivjUIDialogContentPane = null;

	private UIButton ivjBtnAdd = null;

	private UIButton ivjBtnInsert = null;

	private UIButton ivjBtnDelete = null;

	private UIButton ivjBtnEdit = null;

	private UIButton ivjBtnExit = null;

	private UIButton ivjBtnPrint = null;

	private UIButton ivjBtnSave = null;

	private UILabel ivjlbSubjectItem = null;

	private UIRefPane ivjRefSubjectValue = null;

	/** ������4�ֽ���״̬ */
	public static final int ADD = 1;

	public static final int EDIT = 2;

	public static final int MOVE = 3;

	public static final int OTHER = 0;

	private int m_nStatus = 0; // ��ť״̬

	private int m_tbRowNumber = 0; // ��ǰ���к�

	private UIButton ivjBtnCancel = null;

	private UIToolBar ivjUIToolBar1 = null;

	private UIScrollPane ivjUIScrollPane1 = null;

	private UIButton ivjBtnMatch = null;

	private SubjAssConstrastDataModel dataModel;

	/**
	 * 
	 * @param parent
	 *            ������
	 * @param title
	 *            ����
	 * @param ctlSys
	 *            ϵͳ����
	 * @param insubjclassvo
	 *            �ʿ�Ŀ����
	 */
	public SubjAssConstrastEditDlg(java.awt.Container parent, String title) {
		super(parent, title);
		initialize();
	}

	/**
	 * ��ť����Ӧ �������ڣ�(2001-8-13 11:49:03)
	 * 
	 * @param e
	 *            java.awt.event.ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		stopCellEditing();
		if (e.getSource() == getBtnAdd()) {
			onBtnAdd();
		} else if (e.getSource() == getBtnInsert()) {
			onBtnInsert();
		} else if (e.getSource() == getBtnEdit()) {
			onBtnEdit();
		} else if (e.getSource() == getBtnDelete()) {
			onBtnDelete();
		} else if (e.getSource() == getBtnSave()) {
			onBtnSave();
		} else if (e.getSource() == getBtnCancel()) {
			onBtnCancel();
		} else if (e.getSource() == getBtnExit()) {
			onBtnExit();
		}
		// else if (e.getSource() == getBtnPrint()) {
		// onBtnPrint();
		// }
		else if (e.getSource() == getBtnMatch()) {
			onBtnMatch();
		}
	}

	/**
	 * ���SubjAssConstrastVO������ �������ڣ�(2001-8-13 17:25:47)
	 * 
	 * @return boolean
	 * @param vo
	 *            nc.vo.dap.subjclass02.SubjAssConstrastVO
	 */

	/**
	 * ���� BtnAdd ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnAdd() {
		if (ivjBtnAdd == null) {
			try {
				ivjBtnAdd = new nc.ui.pub.beans.UIButton();
				ivjBtnAdd.setName("BtnAdd");
				ivjBtnAdd.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
						"fidap", "UPPfidap-000369")/* @res "����(A)" */);
				ivjBtnAdd.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnAdd.setBorderPainted(false);
				ivjBtnAdd.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnAdd.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnAdd;
	}

	/**
	 * ���� BtnAdd ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnInsert() {
		if (ivjBtnInsert == null) {
			try {
				ivjBtnInsert = new nc.ui.pub.beans.UIButton();
				ivjBtnInsert.setName("BtnInsert");
				ivjBtnInsert
						.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
								"fidap", "UPPfidap-000880")/* @res "����(A)" */);
				ivjBtnInsert.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnInsert.setBorderPainted(false);
				ivjBtnInsert.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnInsert.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnInsert;
	}

	/**
	 * ���� BtnMoveUp ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnCancel() {
		if (ivjBtnCancel == null) {
			try {
				ivjBtnCancel = new nc.ui.pub.beans.UIButton();
				ivjBtnCancel.setName("BtnCancel");
				ivjBtnCancel
						.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
								"fidap", "UPPfidap-000370")/* @res "ȡ��(C)" */);
				ivjBtnCancel.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnCancel.setBorderPainted(false);
				ivjBtnCancel.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnCancel.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnCancel;
	}

	/**
	 * ���� BtnDelete ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnDelete() {
		if (ivjBtnDelete == null) {
			try {
				ivjBtnDelete = new nc.ui.pub.beans.UIButton();
				ivjBtnDelete.setName("BtnDelete");
				ivjBtnDelete
						.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
								"fidap", "UPPfidap-000371")/* @res "ɾ��(D)" */);
				ivjBtnDelete.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnDelete.setBorderPainted(false);
				ivjBtnDelete.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnDelete.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnDelete;
	}

	/**
	 * ���� BtnEdit ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnEdit() {
		if (ivjBtnEdit == null) {
			try {
				ivjBtnEdit = new nc.ui.pub.beans.UIButton();
				ivjBtnEdit.setName("BtnEdit");
				ivjBtnEdit.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
						"fidap", "UPPfidap-000372")/* @res "�޸�(E)" */);
				ivjBtnEdit.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnEdit.setBorderPainted(false);
				ivjBtnEdit.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnEdit.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnEdit;
	}

	/**
	 * ���� BtnExit ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnExit() {
		if (ivjBtnExit == null) {
			try {
				ivjBtnExit = new nc.ui.pub.beans.UIButton();
				ivjBtnExit.setName("BtnExit");
				ivjBtnExit.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
						"fidap", "UPPfidap-000340")/* @res "�˳�" */);
				ivjBtnExit.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnExit.setBorderPainted(false);
				ivjBtnExit.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnExit.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnExit;
	}

	/**
	 * ���� BtnMatch ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnMatch() {
		if (ivjBtnMatch == null) {
			try {
				ivjBtnMatch = new nc.ui.pub.beans.UIButton();
				ivjBtnMatch.setName("BtnMatch");
				ivjBtnMatch.setToolTipText(nc.ui.ml.NCLangRes.getInstance()
						.getStrByID("fidap", "UPPfidap-000373")/*
																 * @res "ƥ��Ӱ������"
																 */);
				ivjBtnMatch
						.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
								"fidap", "UPPfidap-000374")/* @res "ƥ��(M)" */);
				ivjBtnMatch.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnMatch.setActionCommand(nc.ui.ml.NCLangRes.getInstance()
						.getStrByID("fidap", "UPPfidap-000375")/*
																 * @res "ƥ��"
																 */);
				ivjBtnMatch.setBorderPainted(false);
				ivjBtnMatch.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnMatch.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnMatch;
	}

	// /**
	// * ���� BtnPrint ����ֵ��
	// *
	// * @return nc.ui.pub.beans.UIButton
	// */
	// /* ���棺�˷������������ɡ� */
	// private nc.ui.pub.beans.UIButton getBtnPrint() {
	// if (ivjBtnPrint == null) {
	// try {
	// ivjBtnPrint = new nc.ui.pub.beans.UIButton();
	// ivjBtnPrint.setName("BtnPrint");
	// ivjBtnPrint.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("common",
	// "UC001-0000007")/* @res "��ӡ" */);
	// ivjBtnPrint.setMaximumSize(new java.awt.Dimension(60, 22));
	// ivjBtnPrint.setBorderPainted(false);
	// ivjBtnPrint.setPreferredSize(new java.awt.Dimension(60, 22));
	// ivjBtnPrint.setMinimumSize(new java.awt.Dimension(60, 22));
	// // user code begin {1}
	// // user code end
	// } catch (java.lang.Throwable ivjExc) {
	// // user code begin {2}
	// // user code end
	// handleException(ivjExc);
	// }
	// }
	// return ivjBtnPrint;
	// }

	/**
	 * ���� BtnSave ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIButton getBtnSave() {
		if (ivjBtnSave == null) {
			try {
				ivjBtnSave = new nc.ui.pub.beans.UIButton();
				ivjBtnSave.setName("BtnSave");
				ivjBtnSave.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
						"fidap", "UPPfidap-000376")/* @res "����(S)" */);
				ivjBtnSave.setMaximumSize(new java.awt.Dimension(60, 22));
				ivjBtnSave.setBorderPainted(false);
				ivjBtnSave.setPreferredSize(new java.awt.Dimension(60, 22));
				ivjBtnSave.setMinimumSize(new java.awt.Dimension(60, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjBtnSave;
	}

	/**
	 * ���� lbSubjectItem ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UILabel
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UILabel getlbSubjectItem() {
		if (ivjlbSubjectItem == null) {
			try {
				ivjlbSubjectItem = new nc.ui.pub.beans.UILabel();
				ivjlbSubjectItem.setName("lbSubjectItem");
				ivjlbSubjectItem
						.setPreferredSize(new java.awt.Dimension(80, 22));
				ivjlbSubjectItem
						.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
								"fidap", "UPPfidap-000377")/* @res "��Ŀ���ࣺ" */);
				ivjlbSubjectItem.setBounds(28, 4, 67, 22);
				ivjlbSubjectItem
						.setMaximumSize(new java.awt.Dimension(100, 22));
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjlbSubjectItem;
	}

	/**
	 * ���� lbSubjectValue ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UILabel
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIRefPane getRefSubjectValue() {
		if (ivjRefSubjectValue == null) {
			try {
				ivjRefSubjectValue = new nc.ui.pub.beans.UIRefPane();
				ivjRefSubjectValue.setName("lbSubjectValue");
				ivjRefSubjectValue.setText("");
				ivjRefSubjectValue.setRefNodeName("��ƿ�Ŀ");
				ivjRefSubjectValue.setBounds(104, 4, 142, 22);
				// user code begin {1}
				ivjRefSubjectValue.setEnabled(false);
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjRefSubjectValue;
	}

	/**
	 * ���� pnlCenterTable ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIPanel getCenterTable() {
		if (ivjpnlCenterTable == null) {
			try {
				ivjpnlCenterTable = new nc.ui.pub.beans.UIPanel();
				ivjpnlCenterTable.setName("pnlCenterTable");
				ivjpnlCenterTable.setLayout(new java.awt.BorderLayout());
				ivjpnlCenterTable.add(getCetTopLabel(), "North");
				ivjpnlCenterTable.add(getUIScrollPane1(), "Center");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjpnlCenterTable;
	}

	/**
	 * ���� pnlCetTopLabel ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIPanel getCetTopLabel() {
		if (ivjpnlCetTopLabel == null) {
			try {
				ivjpnlCetTopLabel = new nc.ui.pub.beans.UIPanel();
				ivjpnlCetTopLabel.setName("pnlCetTopLabel");
				ivjpnlCetTopLabel.setPreferredSize(new java.awt.Dimension(10,
						30));
				ivjpnlCetTopLabel.setLayout(null);
				ivjpnlCetTopLabel.add(getlbSubjectItem(), getlbSubjectItem()
						.getName());
				ivjpnlCetTopLabel.add(getRefSubjectValue(),
						getRefSubjectValue().getName());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjpnlCetTopLabel;
	}

	/**
	 * ���� pnlTopControl ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIPanel getTopControl() {
		if (ivjpnlTopControl == null) {
			try {
				ivjpnlTopControl = new nc.ui.pub.beans.UIPanel();
				ivjpnlTopControl.setName("pnlTopControl");
				ivjpnlTopControl
						.setPreferredSize(new java.awt.Dimension(14, 35));
				ivjpnlTopControl
						.setBorder(new javax.swing.border.EtchedBorder());
				ivjpnlTopControl.setLayout(new java.awt.BorderLayout());
				ivjpnlTopControl
						.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
				ivjpnlTopControl.add(getUIToolBar1(), "Center");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjpnlTopControl;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:48:10)
	 */
	public int getStatus() {
		return m_nStatus;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-14 9:35:29)
	 * 
	 * @return nc.vo.dap.subjclass02.SubjAssConstrastVO
	 * @param tablevos
	 *            nc.vo.pub.ValueObject
	 */
	public SubjAssConstrastVO[] getTableSubjAssConstrastVO(
			ValueObject[] tablevos) {
		if (tablevos == null)
			return null;
		SubjAssConstrastVO vos[] = new SubjAssConstrastVO[tablevos.length];
		for (int i = 0; i < tablevos.length; i++) {
			vos[i] = (SubjAssConstrastVO) tablevos[i];
		}
		return vos;
	}

	/**
	 * ���� tbpnlFactor ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UITablePane
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UITablePane gettbpnlAssConstrast() {
		if (ivjtbpnlAssConstrast == null) {
			try {
				ivjtbpnlAssConstrast = new nc.ui.pub.beans.UITablePane();
				ivjtbpnlAssConstrast.setName("ivjtbpnlAssConstrast");
				ivjtbpnlAssConstrast.setPreferredSize(new java.awt.Dimension(
						432, 300));
				ivjtbpnlAssConstrast.setBounds(0, 0, 746, 331);
				// ivjtbpnlAssConstrast.setTable(new SubjAssConstrastTable());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjtbpnlAssConstrast;
	}

	/**
	 * ���� UIDialogContentPane ����ֵ��
	 * 
	 * @return javax.swing.JPanel
	 */
	/* ���棺�˷������������ɡ� */
	private javax.swing.JPanel getUIDialogContentPane() {
		if (ivjUIDialogContentPane == null) {
			try {
				ivjUIDialogContentPane = new javax.swing.JPanel();
				ivjUIDialogContentPane.setName("UIDialogContentPane");
				ivjUIDialogContentPane.setLayout(new java.awt.BorderLayout());
				getUIDialogContentPane().add(getTopControl(), "North");
				getUIDialogContentPane().add(getCenterTable(), "Center");
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUIDialogContentPane;
	}

	/**
	 * ���� UIScrollPane1 ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIScrollPane
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIScrollPane getUIScrollPane1() {
		if (ivjUIScrollPane1 == null) {
			try {
				ivjUIScrollPane1 = new nc.ui.pub.beans.UIScrollPane();
				ivjUIScrollPane1.setName("UIScrollPane1");
				ivjUIScrollPane1.setPreferredSize(new java.awt.Dimension(454,
						410));
				getUIScrollPane1().setViewportView(gettbpnlAssConstrast());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUIScrollPane1;
	}

	/**
	 * ���� UIToolBar1 ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UIToolBar
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UIToolBar getUIToolBar1() {
		if (ivjUIToolBar1 == null) {
			try {
				ivjUIToolBar1 = new nc.ui.pub.beans.UIToolBar();
				ivjUIToolBar1.setName("UIToolBar1");

				ivjUIToolBar1.add(getBtnAdd(), getBtnAdd().getName());
				ivjUIToolBar1.add(getBtnInsert(), getBtnInsert().getName());
				ivjUIToolBar1.add(getBtnEdit(), getBtnEdit().getName());
				ivjUIToolBar1.add(getBtnDelete(), getBtnDelete().getName());
				// ivjUIToolBar1.add(getBtnMoveUp(), getBtnMoveUp().getName());
				// ivjUIToolBar1.add(getBtnMoveDown(),
				// getBtnMoveDown().getName());
				ivjUIToolBar1.add(getBtnSave(), getBtnSave().getName());
				// ivjUIToolBar1.add(getBtnMatch(), getBtnMatch().getName());
				ivjUIToolBar1.add(getBtnCancel(), getBtnCancel().getName());
				// ivjUIToolBar1.add(getBtnPrint(), getBtnPrint().getName());
				ivjUIToolBar1.add(getBtnExit(), getBtnExit().getName());
				// user code begin {1}
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUIToolBar1;
	}

	/**
	 * ÿ�������׳��쳣ʱ������
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* ��ȥ���и��е�ע�ͣ��Խ�δ��׽�����쳣��ӡ�� stdout�� */
		// System.out.println("--------- δ��׽�����쳣 ---------");
		exception.printStackTrace(System.out);
	}

	protected void hotKeyPressed(javax.swing.KeyStroke hotKey,
			java.awt.event.KeyEvent e) {

		int modifiers = hotKey.getModifiers();
		if (modifiers != 0) {
			// Combined hot key:
			if (modifiers == 10) {
				// ����alt +ctrl+ S:
				if (hotKey.getKeyCode() == KeyEvent.VK_S) {
					onBtnSave();
				} else if (hotKey.getKeyCode() == KeyEvent.VK_A) {
					onBtnAdd();
				} else if (hotKey.getKeyCode() == KeyEvent.VK_D) {
					onBtnDelete();
				} else if (hotKey.getKeyCode() == KeyEvent.VK_E) {
					onBtnEdit();
				} else if (hotKey.getKeyCode() == KeyEvent.VK_C) {
					onBtnCancel();
				} else if (hotKey.getKeyCode() == KeyEvent.VK_M) {
					onBtnMatch();
				}
			}
		}
	}

	/**
	 * ��ʼ���ࡣ
	 */
	/* ���棺�˷������������ɡ� */
	private void initialize() {
		try {
			// user code begin {1}
			// user code end
			setName("SuvjviewDlg");
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setSize(745, 400);
			setResizable(false);
			setContentPane(getUIDialogContentPane());
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}

		// initialize Button actionListener
		initConnections();
		// initialize Button Status
		setButtonStatus(null);
		// user code end
	}

	public void setDataModel(SubjAssConstrastDataModel dataModel) {
		if (this.dataModel != dataModel) {
			
			gettbpnlAssConstrast().setTable(createTable(dataModel));
			getTableModel().addVO_super(dataModel.toArray());
//			getTableModel().fireTableChanged(new TableModelEvent(getTableModel(), 0, dataModel.sizeAssConstrast() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//			getTableModel().fireTableChanged(new TableModelEvent(getTableModel(), TableModelEvent.INSERT));
			getRefSubjectValue().setPK(dataModel.getPk_accsubj());
			
			this.dataModel = dataModel;
		}
	}

	private SubjAssConstrastTable createTable(
			SubjAssConstrastDataModel dataModel) {
		SubjAssConstrastTable table = new SubjAssConstrastTable();
		table.setModel(createTableModel(dataModel));
		initTableCellEditor(table, dataModel);
		return table;
	}

	private SubjAssConstrastTableModel createTableModel(
			SubjAssConstrastDataModel dataModel) {
		SubjAssConstrastTableModel tableModel = new SubjAssConstrastTableModel(
				dataModel);
		return tableModel;
	}

	private void initTableCellEditor(SubjAssConstrastTable table, SubjAssConstrastDataModel dataModel) {

		BasedocVO basedocvo = null;
		AssVOs assVos = dataModel.getAssVos();
		for (int i = 0; i < assVos.size(); i++) {

			// ���ݶ�Ӧ�����������͵������õ�BasedocVO����
			basedocvo = PfUIDataCache
					.getBdinfo(assVos.get(i).getPk_Checktype());
			if (basedocvo == null) {
				MessageDialog.showWarningDlg(this, "��ʾ", "û��["
						+ assVos.get(i).getChecktypename() + "]���������������ݡ�");
				return;
			}
			// ����BasedocVO�õ�Ӱ�����صĲ���
			UIRefPane refPane = DapCall.getUIRefPane(basedocvo);
			UIRefCellEditor editor = new UIRefCellEditor(refPane);
			// �趨�б༭����
			table.getColumn(assVos.get(i).getChecktypename()).setCellEditor(
					editor);

		}

	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:48:27)
	 */
	public void initConnections() {
		getBtnAdd().addActionListener(this);
		getBtnInsert().addActionListener(this);
		getBtnCancel().addActionListener(this);
		getBtnDelete().addActionListener(this);
		getBtnEdit().addActionListener(this);
		getBtnExit().addActionListener(this);
		getBtnSave().addActionListener(this);
		// getBtnMoveDown().addActionListener(this);
		// getBtnMoveUp().addActionListener(this);
		// getBtnPrint().addActionListener(this);
		getBtnMatch().addActionListener(this);
		// m_refSubjcode.addValueChangedListener(this);
		// for (int i = 0; i < m_refFactor.length; i++) {
		// m_refFactor[i].addValueChangedListener(this);
		// }
	}

	/**
	 * ����ڵ� - ��������ΪӦ�ó�������ʱ���������������
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(java.lang.String[] args) {
		try {
			// SuvjviewDlg aSuvjviewDlg;
			// aSuvjviewDlg = new SuvjviewDlg(null, "test", "",
			// new InsubjclassVO());
			// aSuvjviewDlg.setModal(true);
			// aSuvjviewDlg.addWindowListener(new java.awt.event.WindowAdapter()
			// {
			// public void windowClosing(java.awt.event.WindowEvent e) {
			// System.exit(0);
			// };
			// });
			// aSuvjviewDlg.setVisible(true);
			//
			// java.awt.Insets insets = aSuvjviewDlg.getInsets();
			// aSuvjviewDlg.setSize(aSuvjviewDlg.getWidth() + insets.left
			// + insets.right, aSuvjviewDlg.getHeight() + insets.top
			// + insets.bottom);
			// aSuvjviewDlg.setResizable(false);//��ֹ�ı�Ի����С
		} catch (Throwable exception) {
			System.err.println("nc.ui.pub.beans.UIDialog �� main() �з����쳣");
			exception.printStackTrace(System.out);
		}
	}

	/**
	 * �����ƶ�һ��movevos������index����VO���� �������ڣ�(2001-8-14 10:33:42)
	 * 
	 * @param movevos
	 *            nc.vo.dap.subjclass02.SubjAssConstrastVO[]
	 * @param index
	 *            int
	 */
	public void moveDownTableVO(SubjAssConstrastVO[] movevos, int index) {
		if (movevos == null) {
			return;
		}
		if (index < 0 || index >= movevos.length - 1) {
			return;
		}
		// ����movevos��index��index+1��������
		swapTableVOs(movevos, index, index + 1);
	}

	/**
	 * �����ƶ�һ��movevos������index����VO���� �������ڣ�(2001-8-14 10:33:42)
	 * 
	 * @param movevos
	 *            nc.vo.dap.subjclass02.SubjAssConstrastVO[]
	 * @param index
	 *            int
	 */
	public void moveUpTableVO(SubjAssConstrastVO[] movevos, int index) {
		if (movevos == null) {
			return;
		}
		if (index <= 0 || index > movevos.length - 1) {
			return;
		}
		// ����movevos��index��index+1��������
		swapTableVOs(movevos, index, index - 1);
	}

	//	
	// private SubjAssConstrastTable getTable() {
	// return (SubjAssConstrastTable) getTable();
	// }
	//	
	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:46:05)
	 */
	public void onBtnAdd() {

		if (getStatus() != ADD) {
			getTableModel().backupSubjAssConstrasts();
			setButtonStatus(getBtnAdd()); // ���ð�ť״̬Ϊ�����ӡ�״̬
			m_nStatus = ADD; // ���ó���ǰ״̬Ϊ�����ӡ�
		}

		getTableModel().setEditable(true);
		// ����һ�п�VO��¼����
		getTableModel().addDefaultAssConstrast();

		int currRow = getTable().getRowCount() - 1;

		selectAndScrollToVisible(currRow);

	}

	public SubjAssConstrastTableModel getTableModel() {
		return (SubjAssConstrastTableModel) getTable().getModel();
	}

	private void selectAndScrollToVisible(int currRow) {
		// ����ѡ���ĵ�ǰ��
		getTable().setRowSelectionInterval(currRow, currRow);
		// ������¼����ĩ��
		Rectangle cellRec = getTable().getCellRect(currRow, 0, true);
		if (cellRec != null) {
			getTable().scrollRectToVisible(cellRec);
		}
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:47:48)
	 */
	public void onBtnCancel() {

		if (getStatus() == ADD || getStatus() == EDIT) {

			getTableModel().restoreSubjAssConstrasts();
		}

		getTableModel().setEditable(false);

		m_nStatus = OTHER; // ���ó���ǰ״̬

		// ���ð�ť״̬
		setButtonStatus(getBtnCancel());
	}

	public boolean checkLineChoosed() {
		if (getTable().getSelectedRow() < 0) {
			return false;
		}
		return true;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:46:51)
	 */
	public void onBtnDelete() {

		SubjAssConstrastVO delvo = null; // ɾ����VO����
		if (!checkLineChoosed()) {
			MessageDialog.showWarningDlg(this, nc.ui.ml.NCLangRes.getInstance()
					.getStrByID("fidap", "UPPfidap-000245")/* @res "��ʾ" */,
					nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
							"UPPfidap-000385")/* @res "����ѡ��һ�м�¼��ɾ��" */);
			return;
		}
		if (MessageDialog.showYesNoDlg(this, nc.ui.ml.NCLangRes.getInstance()
				.getStrByID("fidap", "UPPfidap-000335")/* @res "����" */,
				nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
						"UPPfidap-000235")/* @res "ȷʵҪɾ����?" */) == MessageDialog.ID_YES) {
			int nums = getTable().getSelectedRowCount();
			int[] rows = getTable().getSelectedRows();
			SubjAssConstrastVO[] deleteVos = new SubjAssConstrastVO[rows.length];
			for (int i = nums - 1; i >= 0; i--) {
				delvo = (SubjAssConstrastVO) getTableModel().getVO(rows[i]);
				deleteVos[i] = delvo;

			}
			// ���delvo����Ϊ��

			try {
				// ִ��ɾ��
				BankPubItfProxy.getISubjItfProxy().deleteSubjAssConstrasts(
						deleteVos);
				for (int i = 0; i < deleteVos.length; i++) {
					getTableModel().removeVO(deleteVos[i]);
				}

			} catch (Exception e) {
				Logger.error(e);
				MessageDialog.showErrorDlg(this, nc.ui.ml.NCLangRes
						.getInstance().getStrByID("fidap", "UPPfidap-000245")/*
																				 * @res
																				 * "��ʾ"
																				 */, nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
						"UPPfidap-000387")/*
											 * @res "ɾ������ʧ��"
											 */);
				return;
			}
			if (rows[0] == 0) {
				if (getTableModel().getRowCount() > 0) {
					selectAndScrollToVisible(0);
				}
			} else {
				selectAndScrollToVisible(rows[0] - 1);
			}
		}

	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:46:33)
	 */
	public void onBtnEdit() {

		// �����û�����ݣ����ܱ༭
		if (getTable().getRowCount() == 0) {
			return;
		}

		getTableModel().setEditable(true);
		// ���ݱ������
		getTableModel().backupSubjAssConstrasts();
		setButtonStatus(getBtnEdit()); // ���ð�ť״̬Ϊ�޸�״̬
		m_nStatus = EDIT; // ���ó���ǰ״̬Ϊ�޸�
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:48:27)
	 */
	public void onBtnExit() {
		closeOK();
	}

	/**
	 * ���ݶ��Ӱ������ƥ�����ɶ��ж��ձ� �������ڣ�(2004-2-26 15:39:41)
	 */
	private void onBtnMatch() {
		// // ��ѡ����Ӱ������ȡ��
		// Vector vFac = new Vector();
		//
		// for (int i = 0; i < m_insubjclassfactorVOs.length; i++) {
		// for (int j = 0; j < this.m_factorVOs.length; j++) {
		// if (m_insubjclassfactorVOs[i].getPk_factor().trim().equals(
		// m_factorVOs[j].getPk_factor())) {
		// vFac.addElement(m_factorVOs[j]);
		// break;
		// }
		// }
		// }
		// FactorVO factorVOs[] = new FactorVO[vFac.size()];
		// vFac.copyInto(factorVOs);
		// // ���������ɶԻ���
		// BatchUIDialog batDlg = new BatchUIDialog(this, m_insubjclassVO
		// .getFlag(), m_insubjclassVO, m_insubjclassVO.getPk_glorg(),
		// m_insubjclassVO.getPk_glbook());
		// batDlg.setFactorVOs(factorVOs);
		// if (batDlg.showModal() == UIDialog.ID_OK) {
		// try {
		// if (batDlg.getCreateType() == BatchUIDialog.CREATE_COVER) {// ��������
		// if (getTable().getRowCount() > 0) {
		// // ɾ��������Ѿ����ڵ�����
		// ((SubjAssConstrastVOTableModel) getTable().getModel())
		// .clearTable();
		// getTable().removeAll();
		// }
		// }
		// ListItem[][] items = batDlg.getCreateVO();
		// ((SubjAssConstrastVOTableModel) getTable().getModel())
		// .addMuiltData(items);
		// onBtnSave();
		// } catch (Throwable e) {
		// Logger.error(e);
		// MessageDialog.showErrorDlg(this,
		// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
		// "UPPfidap-000224")/* @res "����" */,
		// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
		// "UPPfidap-000389")/* @res "�������ɴ��������²�����" */);
		// }
		// }
	}

	/**
	 * �����¼���ơ� �������ڣ�(2001-8-13 14:49:41)
	 */
	// public void onBtnMoveDown() {
	// System.out.println("in btn MoveDown");
	// // �ж��Ƿ�ѡ���˶���
	// if (!chooseSingle()) {
	// MessageDialog.showWarningDlg(this, nc.ui.ml.NCLangRes.getInstance()
	// .getStrByID("fidap", "UPPfidap-000245")/* @res "��ʾ" */,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000384")/* @res "��ȷ��ѡ��һ�м�¼����" */);
	// return;
	// }
	// if (!checkLineChoosed()) {
	// MessageDialog.showWarningDlg(this, nc.ui.ml.NCLangRes.getInstance()
	// .getStrByID("fidap", "UPPfidap-000245")/* @res "��ʾ" */,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000390")/* @res "����ѡ��һ�м�¼���ƶ�" */);
	// return;
	// }
	// int tablevocount = getTable().getRowCount();// ��ñ����VO�ĸ���
	// // ���ݱ���е�VO����
	// if (m_tableBackupVOs == null) {
	// m_tableBackupVOs = getCurrTableVOs();
	// }
	// if (m_tableBackupVOs == null) {
	// return;
	// }
	// // �ƶ�
	// m_tbRowNumber = getTable().getSelectedRow();// Ҫ�ƶ��ļ�¼��
	// if (m_tbRowNumber >= tablevocount - 1 || m_tbRowNumber < 0) {// ����ƶ�����ĩ��
	// return;
	// }
	// m_swapSubjAssConstrastVOs = getCurrTableVOs();// ���Ʊ���е�VO����
	// moveDownTableVO(m_swapSubjAssConstrastVOs, m_tbRowNumber);// ���Ƽ�¼һ��
	// // ������ʾ
	// m_tablemodel.clearTable();
	// m_tablemodel.addVO(m_swapSubjAssConstrastVOs);
	// getTable().getSelectionModel().setSelectionInterval(m_tbRowNumber + 1,
	// m_tbRowNumber + 1);
	//
	// setButtonStatus(getBtnMoveDown());// ���ð�ť״̬Ϊ���ƶ���״̬
	// m_nStatus = MOVE;// ���ó���ǰ״̬Ϊ���ƶ���
	// }
	//
	// /**
	// * �����¼���ơ� �������ڣ�(2001-8-13 14:49:27)
	// */
	// public void onBtnMoveUp() {
	// System.out.println("in btn MoveDown");
	// // �ж��Ƿ�ѡ���˶���
	// if (!chooseSingle()) {
	// MessageDialog.showWarningDlg(this, nc.ui.ml.NCLangRes.getInstance()
	// .getStrByID("fidap", "UPPfidap-000245")/* @res "��ʾ" */,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000384")/* @res "��ȷ��ѡ��һ�м�¼����" */);
	// return;
	// }
	// if (!checkLineChoosed()) {
	// MessageDialog.showWarningDlg(this, nc.ui.ml.NCLangRes.getInstance()
	// .getStrByID("fidap", "UPPfidap-000245")/* @res "��ʾ" */,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000390")/* @res "����ѡ��һ�м�¼���ƶ�" */);
	// return;
	// }
	// int tablevocount = getTable().getRowCount();// ��ñ����VO�ĸ���
	// // ���ݱ���е�VO����
	// if (m_tableBackupVOs == null) {
	// m_tableBackupVOs = getCurrTableVOs();
	// }
	// if (m_tableBackupVOs == null) {
	// return;
	// }
	// // �ƶ�
	// m_tbRowNumber = getTable().getSelectedRow();// Ҫ�ƶ��ļ�¼��
	// if (m_tbRowNumber > tablevocount - 1 || m_tbRowNumber <= 0) {// ����ƶ���������
	// return;
	// }
	// m_swapSubjAssConstrastVOs = getCurrTableVOs();// ���Ʊ���е�VO����
	// moveUpTableVO(m_swapSubjAssConstrastVOs, m_tbRowNumber);// ���Ƽ�¼һ��
	// // ������ʾ
	// m_tablemodel.clearTable();
	// m_tablemodel.addVO(m_swapSubjAssConstrastVOs);
	// getTable().getSelectionModel().setSelectionInterval(m_tbRowNumber - 1,
	// m_tbRowNumber - 1);
	//
	// setButtonStatus(getBtnMoveUp());// ���ð�ť״̬Ϊ����״̬
	// m_nStatus = MOVE;// ���ó���ǰ״̬Ϊ
	// }
	public void onBtnInsert() {
		if (getTable().getRowCount() == 0) {
			onBtnAdd();
			return;
		}
		getTableModel().setEditable(true);
		int selRow = getTable().getSelectedRow();
		if (selRow < 0)
			return;

		if (getStatus() != ADD) {
			getTableModel().backupSubjAssConstrasts();
			setButtonStatus(getBtnAdd()); // ���ð�ť״̬Ϊ�����ӡ�״̬
			m_nStatus = ADD; // ���ó���ǰ״̬Ϊ�����ӡ�
		}

		getTableModel().insertDefaultAssConstrast(selRow);

		setButtonStatus(getBtnAdd());// ���ð�ť״̬Ϊ����״̬
		m_nStatus = ADD;// ���ó���ǰ״̬Ϊ
	}

	// /**
	// * �˴����뷽��˵���� �������ڣ�(2001-8-13 14:47:20)
	// */
	// public void onBtnPrint() {
	// if (MessageDialog.showOkCancelDlg(this,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000362")/* @res "���ձ��ӡ" */,
	// nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
	// "UPPfidap-000391")/* @res "�Ƿ��ӡ?��ӡ��رյ�ǰ���ձ�" */) == MessageDialog.ID_OK) {
	// this.closeOK();
	//
	// PrintEntry print = new PrintEntry(null, null);
	//
	// // if (m_insubjclassVO.getFlag() == InsubjclassVO.SUBJECTCLASS) {
	// PrintDataSource pds = new PrintDataSource();
	// print.setDataSource(pds);
	// print.setTemplateID(DapCall.getPkcorp(), "10140102", DapCall
	// .getOperator(), null);
	//
	// // } else if (m_insubjclassVO.getFlag() == InsubjclassVO.GLORG) {
	// // PrintDataSource pds = new PrintDataSource();
	// // print.setDataSource(pds);
	// // print.setTemplateID(DapCall.getPkcorp(), "10140101",
	// // DapCall.getOperator(), null);
	// //
	// // } else if (m_insubjclassVO.getFlag() == InsubjclassVO.GLBOOK) {
	// // PrintDataSource pds = new PrintDataSource();
	// // print.setDataSource(pds);
	// // print.setTemplateID(DapCall.getPkcorp(), "10140101",
	// // DapCall.getOperator(), null);
	// //
	// // }
	//
	// print.preview();
	// }
	// }

	private void stopCellEditing() {
		if (getTable().getCellEditor() != null) {
			getTable().getCellEditor().stopCellEditing();
		}
	}

	/**
	 * �����淽���� �������ڣ�(2001-8-13 14:47:20)
	 */
	public void onBtnSave() {

		// ������
		// SubjAssConstrastVO movedvos[] = getCurrTableVOs();
		// if (movedvos != null) {
		// reindexMovedVOs(movedvos); // ���µ�����¼�Ա��˳��
		// }

		try {
			dataModel.check();
			// SubjAssConstrastVO vo = (SubjAssConstrastVO)
			// getTableModel().getVO(getTable().getSelectedRow());
			SubjAssConstrastVO[] saveVos = dataModel.toArray();
			SubjAssConstrastVO[] newVos = BankPubItfProxy.getISubjItfProxy()
					.saveSubjAssConstrast(saveVos);
			for (int i = 0; i < newVos.length; i++) {
				
				// sav
				saveVos[i].setPk_subjassconstrast_rowid(newVos[i].getPk_subjassconstrast_rowid());
				
				SimplSubjAssConstrastVO[] vos = newVos[i]
						.getValidSubjAssConstrasts();
				if (vos != null) {
					for (int ii = 0; ii < vos.length; ii++) {
						SimplSubjAssConstrastVO oldVo = saveVos[i]
								.getAssVo(vos[ii].getAsstypename());
						if (oldVo != null) {
							oldVo.setPrimaryKey(vos[ii].getPrimaryKey());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e);
			MessageDialog.showErrorDlg(this, Translator.translate("fidap",
					"UPPfidap-000224")/* @res "����" */, e.getMessage());
			return;
		}

		getTableModel().setEditable(false);

		setButtonStatus(getBtnSave()); // ���ð�ť״̬Ϊ�����桱״̬
		m_nStatus = OTHER; // ���ó���ǰ״̬Ϊ�����桱

		MessageDialog.showHintDlg(this.getParent(), Translator.translate(
				"fidap", "UPPfidap-000245")/* @res "��ʾ" */, Translator
				.translate("fidap", "UPPfidap-000313")/* @res "����ɹ���" */);

	}

	// SubjAssConstrastVO[] reduceRow(SubjAssConstrastVO[] vos) {
	// int len = vos == null ? 0 : vos.length;
	// ArrayList<SubjAssConstrastVO> list = new ArrayList<SubjAssConstrastVO>();
	//
	// for (int i = 0; i < len; i++) {
	// if (isTemporary(vos[i].getSubjcode())) {
	// if (vos[i].doCheck()) {
	// continue;
	// }
	// }
	// list.add(vos[i]);
	// }
	// vos = new SubjAssConstrastVO[0];
	// vos = list.toArray(vos);
	// return vos;
	// }
	//
	// boolean isTemporary(String aim) {
	// return aim == null || aim.length() == 0;
	// }

	/**
	 * ���ð�ť״̬�� �������ڣ�(2001-8-13 14:54:02)
	 * 
	 * �޸� 2004-12-29 ��Ц��
	 * 
	 * @param btn
	 *            nc.ui.pub.beans.UIButton
	 */
	public void setButtonStatus(UIButton btn) {

		if (btn == getBtnAdd()) {
			getBtnAdd().setEnabled(true);
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
			// getBtnMoveUp().setEnabled(false);
			// getBtnMoveDown().setEnabled(false);
			getBtnSave().setEnabled(true);
			getBtnMatch().setEnabled(false);
			getBtnCancel().setEnabled(true);
			// getBtnPrint().setEnabled(false);
			getBtnExit().setEnabled(false);
		} else if (btn == getBtnEdit()) {
			getBtnAdd().setEnabled(true);
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
			// getBtnMoveUp().setEnabled(false);
			// getBtnMoveDown().setEnabled(false);
			getBtnSave().setEnabled(true);
			getBtnMatch().setEnabled(false);
			getBtnCancel().setEnabled(true);
			// getBtnPrint().setEnabled(false);
			getBtnExit().setEnabled(false);
		} else if (btn == getBtnDelete()) {

		}
		// else if (btn == getBtnMoveUp()) {
		// getBtnAdd().setEnabled(false);
		// getBtnEdit().setEnabled(false);
		// getBtnDelete().setEnabled(false);
		// getBtnMoveUp().setEnabled(true);
		// getBtnMoveDown().setEnabled(true);
		// getBtnSave().setEnabled(true);
		// getBtnMatch().setEnabled(false);
		// getBtnCancel().setEnabled(true);
		// getBtnPrint().setEnabled(false);
		// getBtnExit().setEnabled(false);
		// } else if (btn == getBtnMoveDown()) {
		// getBtnAdd().setEnabled(false);
		// getBtnEdit().setEnabled(false);
		// getBtnDelete().setEnabled(false);
		// getBtnMoveUp().setEnabled(true);
		// getBtnMoveDown().setEnabled(true);
		// getBtnSave().setEnabled(true);
		// getBtnMatch().setEnabled(false);
		// getBtnCancel().setEnabled(true);
		// getBtnPrint().setEnabled(false);
		// getBtnExit().setEnabled(false);
		// }
		else if (btn == getBtnSave()) {
			getBtnAdd().setEnabled(true);
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
			// getBtnMoveUp().setEnabled(true);
			// getBtnMoveDown().setEnabled(true);
			getBtnSave().setEnabled(false);
			getBtnMatch().setEnabled(true);
			getBtnCancel().setEnabled(false);
			// getBtnPrint().setEnabled(true);
			getBtnExit().setEnabled(true);
		} else if (btn == getBtnMatch()) {

		} else if (btn == getBtnCancel()) {
			getBtnAdd().setEnabled(true);
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
			// getBtnMoveUp().setEnabled(true);
			// getBtnMoveDown().setEnabled(true);
			getBtnSave().setEnabled(false);
			getBtnMatch().setEnabled(true);
			getBtnCancel().setEnabled(false);
			// getBtnPrint().setEnabled(true);
			getBtnExit().setEnabled(true);
		}
		// else if (btn == getBtnPrint()) {
		//
		// }
		else if (btn == getBtnExit()) {

		} else if (btn == null) {
			getBtnAdd().setEnabled(true);
			getBtnEdit().setEnabled(true);
			getBtnDelete().setEnabled(true);
			// getBtnMoveUp().setEnabled(true);
			// getBtnMoveDown().setEnabled(true);
			getBtnSave().setEnabled(false);
			getBtnMatch().setEnabled(true);
			getBtnCancel().setEnabled(false);
			// getBtnPrint().setEnabled(true);
			getBtnExit().setEnabled(true);
		}
		return;

	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2001-8-12 17:30:23)
	 */
	public void stop() {
		// for debug
		// System.out.println("stop");
		return;
	}

	/**
	 * //����movevos��idx1��idx2�������� �������ڣ�(2001-8-14 11:01:11)
	 * 
	 * @param movevos
	 *            nc.vo.dap.subjclass02.SubjAssConstrastVO[]
	 * @param idx1
	 *            int
	 * @param idx13
	 *            int
	 */
	public void swapTableVOs(SubjAssConstrastVO[] movevos, int idx1, int idx2) {
		if (movevos == null) {
			return;
		}
		if (idx1 < 0 || idx1 >= movevos.length) {
			return;
		}
		if (idx2 < 0 || idx2 >= movevos.length) {
			return;
		}
		SubjAssConstrastVO temvo = new SubjAssConstrastVO();
		temvo = movevos[idx1];
		movevos[idx1] = movevos[idx2];
		movevos[idx2] = temvo;

	}

	/**
	 * ������Pkת��Ϊ����
	 * 
	 * @param pk_glorg
	 * @return
	 */
	private String getGlorgNameByPk(String pk_glorg) {
		String name = null;
		try {
			//GLOrgBookBO a = new GLOrgBookBO;
		     //GlorgVO vo = new GLOrgBookBO().queryGlorgByPk(pk_glorg);
		     
		     GlOrgVO vo = new GLOrgBookBO().queryGlorgByPk(pk_glorg);
			if (vo != null) {
				name = vo.getGlorgname();
			}
		} catch (Exception e) {
			Logger.error(e);
		}
		return name;
	}

	/**
	 * ���˲�Pkת��Ϊ����
	 * 
	 * @param pk_glbook
	 * @return
	 */
	private String getGlbookNameByPk(String pk_glbook) {
		String name = null;
		try {
			GlbookVO vo = new GLOrgBookBO().queryGlbookByPk(pk_glbook);
			if (vo != null) {
				name = vo.getName();
			}
		} catch (Exception e) {
			Logger.error(e);
		}
		return name;
	}

	/**
	 * ���Table����
	 * 
	 * @return
	 */
	private UITable getTable() {
		return gettbpnlAssConstrast().getTable();
	}

	/**
	 * ���ö��ձ��Ƿ�ɱ༭
	 * 
	 * @param b
	 */
	public void setEditable(boolean b) {
		if (!b) {
			getBtnAdd().setEnabled(false);
			getBtnInsert().setEnabled(false);
			getBtnEdit().setEnabled(false);
			getBtnDelete().setEnabled(false);
			// getBtnMoveUp().setEnabled(false);
			// getBtnMoveDown().setEnabled(false);
			getBtnSave().setEnabled(false);
			getBtnMatch().setEnabled(false);
			getBtnCancel().setEnabled(false);
			// getBtnPrint().setEnabled(false);
			getBtnExit().setEnabled(true);
		}
	}
}
