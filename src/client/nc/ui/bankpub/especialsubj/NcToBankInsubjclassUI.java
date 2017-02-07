package nc.ui.bankpub.especialsubj;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import nc.bs.logging.Logger;
import nc.itf.fi.pub.Accsubj;
import nc.ui.bd.GLOrgBookAccBO_Client;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.dap.accrule.SelectGlPnl;
import nc.ui.dap.subjclass01.DapFactorDlg;
import nc.ui.dap.subjclass02.SubjviewDlg;
import nc.ui.dap.subjclass03.CopyDlg;
import nc.ui.fipf.pub.PubFunctionUI;
import nc.ui.pf.pub.DapCall;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.UITree;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.bd.b54.GlorgbookVO;
import nc.vo.dap.subjclass.InsubjclassCopyVO;
import nc.vo.dap.subjclass.InsubjclassVO;
import nc.vo.dap.subjclass01.InsubjclassfactorVO;
import nc.vo.fip.pub.Translator;
import nc.vo.pub.billtype.DapsystemVO;

/**
 * ���ʿ�Ŀ����UI�� �������ڣ�(01-8-8 17:04:41)
 * 
 * @author��ͯ־��
 */
public class NcToBankInsubjclassUI extends ToftPanel implements TreeSelectionListener, ValueChangedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6706363547435217116L;

	class TbModel extends nc.ui.pub.beans.table.VOTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 79138068462964706L;
		private ArrayList changedRows = new ArrayList();

		/**
		 * ȡ�ñ仯���кż���
		 * @return
		 */
		public int[] getChangedRows() {
			int[] rows;
			int len = changedRows.size();
			if (len > 0) {
				rows = new int[len];
				for (int i = 0; i < len; i++) {
					rows[i] = ((Integer) changedRows.get(i)).intValue();
				}
			} else {
				rows = new int[0];
			}

			return rows;
		}

		/**
		 * ����仯�м�¼����
		 *
		 */
		public void clearChangedRows() {
			changedRows.clear();
		}

		/**
		 * ��¼һ���仯���к�
		 * @param row
		 */
		public void addChangedRow(int row) {
			Integer i = new Integer(row);
			if (!changedRows.contains(i)) {
				changedRows.add(i);
			}
		}

		public TbModel(Class c) {
			super(c);
		}

		public String getColumnName(int col) {
			return m_aryTitles[col];
		}

		public int getColumnCount() {
			return m_aryTitles.length;
		}

		public boolean isCellEditable(int row, int col) {
			InsubjclassVO vo = (InsubjclassVO) getVO(row);
			if (col == 2)
				return false;
			if (getStatus() == ADD) {
				getAccsubjRefPane().setPK(vo.getDefaultsubjcode());
				return true;
			}
			if (getStatus() == EDIT) {
				getAccsubjRefPane().setPK(vo.getDefaultsubjcode());
				return true;
			}
			return false;
		}

		public Class getColumnClass(int col) {
			if (col == 4)
				return Boolean.class;
			else
				return String.class;
		}

		public void setValueAt(Object obj, int row, int col) {
			try {
				addChangedRow(row);
				InsubjclassVO vo = (InsubjclassVO) getVO(row);
				//getTable();
				switch (col) {
				case 0:
					vo.setInsubjclasscode((String) obj);
					break;
				case 1:
					vo.setInsubjclassname((String) obj);
					break;
				case 2:
					break;
				case 3:

					if (getAccsubjRefPane() != null) {
						getAccsubjRefPane().processFocusLost();
						vo.setDefaultsubjcode(getAccsubjRefPane().getRefPK());
						vo.setDefaultsubjname(getAccsubjRefPane().getRefName());
						getTable().repaint();
					}
					break;
				case 4:
					if (obj == null)
						vo.setCorp931("N");
					Boolean tmpB = (Boolean) obj;
					if (tmpB.booleanValue())
						vo.setCorp931("Y");
					else
						vo.setCorp931("N");
					break;
				case 5:
					vo.setMemo((String) obj);
					break;
				default:
					break;

				}
			} catch (Exception ex) {
				Logger.error(ex);
			}
		}

		public Object getValueAt(int row, int column) {
			InsubjclassVO vo = (InsubjclassVO) getVO(row);
			switch (column) {
			case 0:
				return vo.getInsubjclasscode();

			case 1:
				return vo.getInsubjclassname();

			case 2:
				return getFactorNames(vo.getFactorVOs());

			case 3:
				if (vo.getDefaultsubjcode() != null) {
					AccsubjVO accvo = getAccsubjVOByPk(vo.getDefaultsubjcode(), null);
					if (accvo == null)
						return "";
					vo.setDefaultsubjname(accvo.getDispname());
					return accvo.getDispname();
				}
				return "";

			case 4:
				if (vo.getCorp931() != null && vo.getCorp931().trim().equals("Y"))
					return new Boolean(true);
				else
					return new Boolean(false);
			case 5:
				return vo.getMemo();
			}
			return null;
		}

	}

	private UITablePane ivjUITablePane1 = null;

	private UITree ivjUITree1 = null;

	private ButtonObject m_boAdd = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000002")/* @res "����" */,
			Translator.uiTranslate("fidap", "UPPfidap-000213")/* @res "����һ��" */, 2, "����"); /*-=notranslate=-*/

	private ButtonObject m_boEdit = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000045")/* @res "�޸�" */,
			Translator.uiTranslate("fidap", "UPPfidap-000214")/* @res "�޸�һ��" */, 2, "�޸�"); /*-=notranslate=-*/

	private ButtonObject m_boDelete = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000039")/* @res "ɾ��" */,
			Translator.uiTranslate("fidap", "UPPfidap-000215")/* @res "ɾ��һ��" */, 2, "ɾ��"); /*-=notranslate=-*/

	private ButtonObject m_boSave = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000001")/* @res "����" */,
			Translator.uiTranslate("common", "UC001-0000001")/* @res "����" */, 2, "����"); /*-=notranslate=-*/

	private ButtonObject m_boCancel = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000008")/* @res "ȡ��" */,
			Translator.uiTranslate("common", "UC001-0000008")/* @res "ȡ��" */, 2, "ȡ��"); /*-=notranslate=-*/

	private ButtonObject m_boSpace = new ButtonObject("  ", "  ", 2);

	private ButtonObject m_boCopy = new ButtonObject(Translator.uiTranslate("common",
			"UC001-0000043")/* @res "����" */,
			Translator.uiTranslate("common", "UC001-0000043")/* @res "����" */, 2, "����"); /*-=notranslate=-*/

	private ButtonObject m_boCopyForSys = new ButtonObject(Translator.uiTranslate("fidap",
			"UPPfidap-000879")/* @res "��ϵͳ����" */, Translator.uiTranslate("fidap",
			"UPPfidap-000879")/* @res "��ϵͳ����" */, 2, "��ϵͳ����");
	private ButtonObject m_boCopyForOrgbook = new ButtonObject(Translator.uiTranslate("fidap",
			"UPPfidap-000544")/* @res "���˲�����" */, Translator.uiTranslate("fidap",
			"UPPfidap-000544")/* @res "���˲�����" */, 2, "���˲�����");
	{
		m_boCopy.addChildButton(m_boCopyForSys);
		m_boCopy.addChildButton(m_boCopyForOrgbook);
	}
	private ButtonObject m_boContrast = new ButtonObject(Translator.uiTranslate("fidap",
			"UPPfidap-000217")/* @res "���ձ�" */,
			Translator.uiTranslate("fidap", "UPPfidap-000217")/* @res "���ձ�" */, 2, "���ձ�"); /*-=notranslate=-*/

	private ButtonObject m_boFactor = new ButtonObject(Translator.uiTranslate("fidap",
			"UPPfidap-000210")/* @res "Ӱ������" */, Translator
			.uiTranslate("fidap", "UPPfidap-000210")/* @res "Ӱ������" */, 2, "Ӱ������"); /*-=notranslate=-*/

	private ButtonObject m_boHelp = new ButtonObject(Translator.uiTranslate("fidap",
			"UPPfidap-000218")/* @res "����" */,
			Translator.uiTranslate("fidap", "UPPfidap-000218")/* @res "����" */, 0, "����"); /*-=notranslate=-*/

	private ButtonObject[] m_MainButtonGroup = { m_boAdd, m_boEdit, m_boDelete, m_boSave,
			m_boCancel, m_boSpace, m_boCopy, m_boFactor, m_boContrast, m_boSpace, };

	/** ������4�ֽ���״̬ */
	public static final int ADD = 1;

	public static final int EDIT = 2;

	public static final int DELETE = 3;

	public static final int OTHER = 0;

	/** ��ǰ״̬ */
	private int status = 0;

	/** ��ǰ�༭�� */
	private int rowNumber = 0;

	/** Ϊ�༭���ݵ�vo */
	private InsubjclassVO[] bakupvo;// = new InsubjclassVO();

	/** ģ�����ݿ��Vector */
	private Vector vecDB = new Vector();

	/** ��ͷ */
	final String[] m_aryTitles = new String[] {
			Translator.uiTranslate("fidap", "UPPfidap-000317")/* @res "��Ŀ������" */,
			Translator.uiTranslate("common", "UC000-0001155")/* @res "����" */,
			Translator.uiTranslate("fidap", "UPPfidap-000210")/* @res "Ӱ������" */,
			Translator.uiTranslate("fidap", "UPPfidap-000318")/* @res "Ĭ�����˿�Ŀ" */,
			Translator.uiTranslate("fidap", "UPPfidap-000319")/* @res "�Ƿ����" */,
			Translator.uiTranslate("common", "UC000-0001376") /* @res "��ע" */};

	/** ���� */
	UIRefPane ref_AccSubj = null;
	private UIScrollPane ivjUIScrollPane1 = null;

	//private UIRefCellEditor m_refAccCellEditor = null;

	private UIPanel UIPanel = null;

	private SelectGlPnl selectGlPnl = null;

	//�����ʲ�pk
	private String m_pk_glorgbook;

	private static Hashtable htAccsubj = new Hashtable();

	/**
	 * ѡ����ϵͳ�ڵ�
	 */
	private DapsystemVO selectSystemVO = null;

	/**
	 * @return ���� pk_glorgbook��
	 */
	private String getPk_glorgbook() {
		return m_pk_glorgbook;
	}

	/**
	 * @param pk_glorgbook Ҫ���õ� pk_glorgbook��
	 */
	private void setPk_glorgbook(String pk_glorgbook) {
		m_pk_glorgbook = pk_glorgbook;
	}

	/**
	 * InsubjclassUI ������ע�⡣
	 */
	public NcToBankInsubjclassUI() {
		super();
		initialize();
	}

	/*���ݿ�Ŀ����ȡ�ÿ�Ŀ��VO*/
	private AccsubjVO getAccsubjVOByPk(String pk_accsubj, InsubjclassVO[] tempVOs) {
		if (pk_accsubj == null)
			return null;
		Object obj = htAccsubj.get(pk_accsubj);
		if (obj == null) {
			try {
				if (tempVOs != null) {
					Vector vec = new Vector();
					for (int i = 0; i < tempVOs.length; i++) {
						if (tempVOs[i].getDefaultsubjcode() != null)
							vec.addElement(tempVOs[i].getDefaultsubjcode());
					}
					if (vec.size() > 0) {
						String[] pks = new String[vec.size()];
						vec.copyInto(pks);
						AccsubjVO[] vos = Accsubj.queryByPks(pks);
						if (vos != null) {
							for (int i = 0; i < vos.length; i++) {
								htAccsubj.put(vos[i].getPk_accsubj(), vos[i]);
							}
						}
					}
				} else {
					AccsubjVO[] vos = Accsubj.queryByPks(new String[] { pk_accsubj });
					if (vos != null && vos.length > 0) {
						htAccsubj.put(vos[0].getPk_accsubj(), vos[0]);
					}
				}
			} catch (Exception ex) {
				Logger.error(ex);
			}
			obj = htAccsubj.get(pk_accsubj);
		}
		return (AccsubjVO) obj;
	}

	/**
	 * ����Ƿ�ѡ���˱���һ�С� �������ڣ�(01-7-18 22:39:42)
	 * 
	 * @return boolean
	 */
	public boolean checkLineChoosed() {
		if (getTable().getSelectedRow() < 0) {
			showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000226")/* @res "����ѡ��һ��" */);
			return false;
		}
		return true;

	}

	/**
	 * ���VO�ĺϷ��ԣ��Ϸ���true�� �������ڣ�(01-7-16 18:51:42)
	 * 
	 * @return boolean
	 * @param vo nc.vo.pub.ValueObject
	 */
	public InsubjclassVO[] checkVO(InsubjclassVO[] vos) {

		if (vos == null)
			return null;
		ArrayList alist = new ArrayList();
		for (int i = 0; i < vos.length; i++) {
			InsubjclassVO vo1 = vos[i];
			if (vo1.getPk_insubjclass() == null && vo1.getInsubjclasscode() == null
					&& vo1.getInsubjclassname() == null && vo1.getDefaultsubjcode() == null
					&& vo1.getDefaultsubjname() == null) {
				//ɾ������
				continue;
			}
			if (vo1.getInsubjclasscode() == null || vo1.getInsubjclasscode().trim().length() == 0) {
				showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000320")/* @res "������벻��Ϊ��!" */);
				return null;
			}

			if (vo1.getInsubjclassname() == null || vo1.getInsubjclassname().trim().length() == 0) {
				showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000321")/* @res "�������Ʋ���Ϊ��!" */);
				return null;
			}
			alist.add(vo1);
		}
		if (alist.size() > 0) {
			vos = new InsubjclassVO[alist.size()];
			alist.toArray(vos);
			return vos;
		}

		return null;
	}

	/**
	 * ����VO�� �������ڣ�(01-7-19 9:41:35)
	 */
	public void copyVO(InsubjclassVO voFrom, InsubjclassVO voTo) {
		voTo.setDefaultsubjcode(voFrom.getDefaultsubjcode());
		voTo.setDefaultsubjname(voFrom.getDefaultsubjname());
		voTo.setFactorVOs(voFrom.getFactorVOs());
		voTo.setInsubjclasscode(voFrom.getInsubjclasscode());
		voTo.setInsubjclassname(voFrom.getInsubjclassname());
		voTo.setMemo(voFrom.getMemo());
		voTo.setPk_billtype(voFrom.getPk_billtype());
		voTo.setPk_corp(voFrom.getPk_corp());
		voTo.setPk_insubjclass(voFrom.getPk_insubjclass());

		//2004-12-21 ��� -- ��Ц��
		voTo.setPk_glbook(voFrom.getPk_glbook());
		voTo.setPk_glorg(voFrom.getPk_glorg());
		voTo.setFlag(voFrom.getFlag());

	}

	/**
	 * ��¡InsubjclassVO����
	 * 
	 * @param vos
	 * @return
	 */
	private InsubjclassVO[] cloneInsubjclassVos(InsubjclassVO[] vos) {
		if (vos == null) {
			return null;
		}
		InsubjclassVO[] cloneVos = new InsubjclassVO[vos.length];
		for (int i = 0; i < vos.length; i++) {
			cloneVos[i] = new InsubjclassVO();
			copyVO(vos[i], cloneVos[i]);
		}
		return cloneVos;
	}

	/**
	 * ���ݱ������
	 *  
	 */
	private void backupTableData() {
		int rowNum = getTable().getRowCount();
		bakupvo = cloneInsubjclassVos((InsubjclassVO[]) getTableModel().getVOArray());
	}

	/**
	 * �ָ��������
	 *  
	 */
	private void retrieveTableData() {
		int[] rows = getTableModel().getChangedRows();
		for (int i = 0; i < rows.length; i++) {
			copyVO(bakupvo[rows[i]], (InsubjclassVO) getTableModel().getVO(rows[i]));
		}
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-8-8 17:51:35)
	 * 
	 * @return java.lang.String
	 */
	public String getCurrentSystem() {
		if (selectSystemVO == null) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) getUITree1().getSelectionPath()
					.getLastPathComponent();
			if (!node.isLeaf() || node.isRoot())
				return null;
			//		BilltypeVO vo=(BilltypeVO)node.getUserObject();
			selectSystemVO = (DapsystemVO) node.getUserObject();
		}
		return selectSystemVO.getSystypecode();
	}

	/**
	 * ��ȡ��ǰѡ��Ľڵ����ڵ����ڵ�
	 * @param syscode
	 * @return
	 */
	public DefaultMutableTreeNode getTreenode(String syscode) {
		TreeModel model = getUITree1().getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		DefaultMutableTreeNode aim = null;
		int childcount = root.getChildCount();
		for (int i = 0; i < childcount; i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
			DapsystemVO sys = (DapsystemVO) child.getUserObject();
			if (sys.getSystypecode().equalsIgnoreCase(syscode)) {
				aim = child;
				break;
			}
		}
		return aim;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-8-8 17:51:35)
	 * 
	 * @return java.lang.String
	 */
	public String getCurrentBillypeName() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) getUITree1().getSelectionPath()
				.getLastPathComponent();
		if (!node.isLeaf() || node.isRoot())
			return null;
		DapsystemVO vo = (DapsystemVO) node.getUserObject();
		return vo.getSystypename();
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-8-9 15:48:42)
	 */
	public String getFactorNames(InsubjclassfactorVO[] factors) {
		if (factors == null)
			return null;

		String str = "";
		for (int i = 0; i < factors.length; i++) {
			String name = factors[i] == null ? "" : factors[i].getFactorname();
			str += name;
			if (i < factors.length - 1)
				str += ",";
		}
		return str;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-8-9 9:17:48)
	 * 
	 * @return nc.vo.dap.subjclass.InsubjclassVO[]
	 * @param billtype java.lang.String
	 */
	public InsubjclassVO[] getFilterVOs(String billtype, String glorg, String glbook) {
		Vector v = new Vector();
		for (int i = 0; i < vecDB.size(); i++) {
			InsubjclassVO vo = (InsubjclassVO) vecDB.elementAt(i);
			if (vo.getFlag() == InsubjclassVO.SUBJECTCLASS && vo.getPk_billtype().equals(billtype)
					&& vo.getPk_glorg() != null && vo.getPk_glorg().equals(glorg)
					&& vo.getPk_glbook() != null && vo.getPk_glbook().equals(glbook)) {
				v.addElement(vo);
			}
		}
		if (v.size() > 0) {
			InsubjclassVO[] tmp = new InsubjclassVO[v.size()];
			v.copyInto(tmp);
			return tmp;
		}
		return null;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-7-18 16:01:35)
	 * 
	 * @return int
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-7-17 18:10:29)
	 * 
	 * @return nc.ui.pub.beans.UITable
	 */
	public UITable getTable() {
		UITable table = (UITable) getUITablePane1().getTable();
		return table;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(01-7-17 18:10:29)
	 * 
	 * @return nc.ui.pub.beans.UITable
	 */
	public TbModel getTableModel() {
		UITable table = (UITable) getUITablePane1().getTable();
		return (TbModel) table.getModel();
	}

	/**
	 * ����ʵ�ָ÷���������ҵ�����ı��⡣
	 * 
	 * @version (00-6-6 13:33:25)
	 * 
	 * @return java.lang.String
	 */
	public String getTitle() {
		return Translator.uiTranslate("fidap", "UPPfidap-000322")/* @res "���˿�Ŀ����" */;
	}

	/**
	 * �����ģ�͡� �������ڣ�(01-7-17 17:55:43)
	 */
	public DefaultTreeModel getTreeModel() {
		return (DefaultTreeModel) getUITree1().getModel();
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
				ivjUIScrollPane1.setPreferredSize(new java.awt.Dimension(200, 3));
				getUIScrollPane1().setViewportView(getUITree1());
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
	 * ���� UITablePane1 ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UITablePane
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UITablePane getUITablePane1() {
		if (ivjUITablePane1 == null) {
			try {
				ivjUITablePane1 = new nc.ui.pub.beans.UITablePane();
				ivjUITablePane1.setName("UITablePane1");
				ivjUITablePane1.setPreferredSize(new java.awt.Dimension(454, 403));
				//ivjUITablePane1.setBounds(0, 0, 774, 403);
				// user code begin {1}

				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUITablePane1;
	}

	/**
	 * ���� UITree1 ����ֵ��
	 * 
	 * @return nc.ui.pub.beans.UITree
	 */
	/* ���棺�˷������������ɡ� */
	private nc.ui.pub.beans.UITree getUITree1() {
		if (ivjUITree1 == null) {
			try {
				ivjUITree1 = new nc.ui.pub.beans.UITree();
				ivjUITree1.setName("UITree1");
				ivjUITree1.setBounds(0, 0, 78, 72);
				// user code begin {1}
				ivjUITree1.putClientProperty("JTree.lineStyle", "Angled");
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjUITree1;
	}

	/**
	 * ÿ�������׳��쳣ʱ������
	 * 
	 * @param exception java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* ��ȥ���и��е�ע�ͣ��Խ�δ��׽�����쳣��ӡ�� stdout�� */
		// System.out.println("--------- δ��׽�����쳣 ---------");
		// exception.printStackTrace(System.out);
	}

	/**
	 * ��ʼ�����ӡ� �������ڣ�(01-8-8 17:08:45)
	 */
	public void initConnections() {
		getUITree1().addTreeSelectionListener(this);

	}

	/**
	 * ��ʼ�����ݡ� �������ڣ�(01-8-8 17:08:37)
	 */
	public void initData() {
		try {
			InsubjclassVO[] tempVOs = InsubjclassBO_Client.queryAll(DapCall.getPkcorp());

			//For refresh
			vecDB.clear();
			//Get all the names
			if (tempVOs != null) {
				AccsubjVO vo = null;
				boolean doCache = true;
				for (int i = 0; i < tempVOs.length; i++) {
					if (tempVOs[i].getDefaultsubjcode() != null && doCache) {
						vo = getAccsubjVOByPk(tempVOs[i].getDefaultsubjcode(), tempVOs);
						doCache = false;
					}
					if (vo != null) {
						tempVOs[i].setDefaultsubjname(vo.getDispname());
					} else {
						//                		    tempVOs[i].setDefaultsubjname("N/A");
					}
				}
			}
			//End get names

			//Add to vector
			if (tempVOs != null) {
				for (int i = 0; i < tempVOs.length; i++) {
					vecDB.addElement(tempVOs[i]);
				}
			}

		} catch (Exception e) {
			Logger.error(e);
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
			this.setLayout(new BorderLayout());
			setName("InsubjclassUI");
			setSize(774, 419);
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}
		//fgj2002-10-18 �������β���

		//***************************
		this.add(getUIScrollPane1(), java.awt.BorderLayout.WEST);
		this.add(getUIPanel(), java.awt.BorderLayout.CENTER);
		initPk_glorgbook();
		initData();
		initUI();
		initConnections();
		// user code end
	}

	/**
	 * ��ʼ�������ʲ�pk
	 *  
	 */
	private void initPk_glorgbook() {
		String pk_glorg, pk_glbook, pk_glorgbook;
		pk_glorg = getSelectGlPnl().getSelectedPk_glorg();//getCurrGlOrg().getPk_glorg();
		pk_glbook = getSelectGlPnl().getSelectedPk_glbook();   // getCurrGlBook().getPk_glbook();
		try {
			pk_glorgbook = GLOrgBookAccBO_Client.getGlOrgBookVOByPk_GlorgAndPk_Glbook(pk_glorg,
					pk_glbook).getPrimaryKey();
			setPk_glorgbook(pk_glorgbook);
		} catch (Exception e) {
			Logger.error(e);
		}
	}

	/**
	 * ��ʼ��Table���ݡ� �������ڣ�(01-8-8 17:08:37)
	 */
	public void initTableData(String billType) {

		getTableModel().clearTable();

		if (billType == null)
			return;

		InsubjclassVO[] localvos = null;
		//tian
		localvos = getFilterVOs(billType, getSelectGlPnl().getSelectedPk_glorg()//    getCurrGlOrg().getPk_glorg(),
				,getSelectGlPnl().getSelectedPk_glbook());    //   getCurrGlBook().getPk_glbook());

		if (localvos != null)
			getTableModel().addVO(localvos);

		if (getTable().getRowCount() > 0)
			getTable().setRowSelectionInterval(0, 0);
	}

	private InsubjclassVO[] getCurrentVOS() {
		//tian
		return getFilterVOs(getCurrentSystem(), getSelectGlPnl().getSelectedPk_glorg()//   .getCurrGlOrg().getPk_glorg(),
				,getSelectGlPnl().getSelectedPk_glbook());//     getCurrGlBook().getPk_glbook());
	}

	/**
	 * ��ʼ������� �������ڣ�(01-7-18 17:49:19)
	 */
	public void initTableStyle() {
		javax.swing.table.TableColumnModel tm = getTable().getColumnModel();
		getTable().addSortListener();
		getTable().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//���ÿ�Ŀѡ��ĩ��
		//m_refAccCellEditor = new UIRefCellEditor(getUIRefAccSubj());
		//tm.getColumn(3).setCellEditor(m_refAccCellEditor);

		//��Ŀ���೤�Ȳ�����10
		UITextField txt1 = new UITextField();
		txt1.setMaxLength(10);

		//�������Ʋ�����50
		UITextField txt2 = new UITextField();
		txt2.setMaxLength(50);
		getTable().getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(txt1));
		getTable().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txt2));

		//������
		tm.removeColumn(tm.getColumn(5));
		tm.removeColumn(tm.getColumn(4));

		getTable().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	}

	/**
	 * ��ʼ�����档 �������ڣ�(01-8-8 17:10:06)
	 */
	public void initUI() {
		getUITree1().setModel(DapCall.getDapSystemTreeModel());
		getUITablePane1().getTable().setModel(new TbModel(InsubjclassVO.class));
		initTableStyle();
		setButtons(m_MainButtonGroup);
		if (getTreeModel().getChildCount(getTreeModel().getRoot()) > 0)
			getUITree1().setSelectionRow(1);
		initTableData(getCurrentSystem());

		setButtonStatus(null);
	}

	private UIRefPane getUIRefAccSubj() {

		return PubFunctionUI.getUIRefPane(getPk_glorgbook());
	}

	/**
	 * ����ڵ� - ��������ΪӦ�ó�������ʱ���������������
	 * 
	 * @param args java.lang.String[]
	 */
	public static void main(java.lang.String[] args) {
		try {
			javax.swing.JFrame frame = new javax.swing.JFrame();
			NcToBankInsubjclassUI aInsubjclassUI;
			aInsubjclassUI = new NcToBankInsubjclassUI();
			frame.setContentPane(aInsubjclassUI);
			frame.setSize(aInsubjclassUI.getSize());
			frame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				};
			});
			frame.setVisible(true);
			java.awt.Insets insets = frame.getInsets();
			frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight()
					+ insets.top + insets.bottom);

		} catch (Throwable exception) {
			System.err.println("nc.ui.pub.ToftPanel �� main() �з����쳣");
			exception.printStackTrace(System.out);
		}
	}

	private void stopTableEdit() {
		if (getTable().getCellEditor() != null) {
			getTable().getCellEditor().stopCellEditing();
		}

	}

	/**
	 * ���Ӱ�Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnAdd() {
		stopTableEdit();
		if (getCurrentSystem() == null) {
			showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000238")/* @res "����ѡ��һ����������" */);
			return;
		}

		getTable().setSortEnabled(false);

		if (getStatus() != ADD) {
			status = ADD;
			setButtonStatus(m_boAdd);
			getSelectGlPnl().setEnabled(false);//����״̬�²��ɸı���������˲�
			getUITree1().setEnabled(false);
		}

		InsubjclassVO vo = new InsubjclassVO();
		vo.setPk_glorg(getSelectGlPnl().getSelectedPk_glorg());   //getCurrGlOrg().getPk_glorg());
		vo.setPk_glbook(getSelectGlPnl().getSelectedPk_glbook());   //getCurrGlBook().getPk_glbook());
		vo.setFlag(InsubjclassVO.SUBJECTCLASS);

		//getAccsubjRefPane().setPK(null);
		getTableModel().addVO(vo);
		rowNumber = getTable().getRowCount() - 1;
		getTable().setRowSelectionInterval(rowNumber, rowNumber);
	}

	/**
	 * ȡ����Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnCancel() {
		getTable().editingStopped(new ChangeEvent(getTable()));

		if (status == ADD) {
			getTableModel().removeVO(rowNumber);
		}
		if (status == EDIT) {
			//�ָ���������
			retrieveTableData();
			//            copyVO(bakupvo, (InsubjclassVO)
			// getTableModel().getVO(rowNumber));
			getTableModel().fireTableRowsUpdated(rowNumber, rowNumber);
		}

		status = OTHER;
		setButtonStatus(m_boCancel);
		getUITree1().setEnabled(true);

		getTable().setSortEnabled(true);

		getSelectGlPnl().setEnabled(true);//�ָ��ı���������˲��Ŀ���״̬

		getTableModel().clearChangedRows();
	}

	/**
	 * ��Ŀ���հ�Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnContrast() {
		if (!checkLineChoosed())
			return;
		InsubjclassVO insubjclass = (InsubjclassVO) getTableModel().getVO(
				getTable().getSelectedRow());
		//tian
		SubjviewDlg dlg = new SubjviewDlg(this,
				Translator.uiTranslate("fidap", "UPPfidap-000323")/* @res "��Ŀ������ձ�" */,
				getCurrentSystem(), insubjclass);
		dlg.showModal();

		int[] oldIndex = getUITree1().getSelectionRows();
		getUITree1().setSelectionRows(oldIndex);

	}

	/**
	 * ������Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnCopyForSys() {
		String billTypeName = getCurrentBillypeName();
		if (billTypeName == null) {
			showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000227")/* @res "����ѡ��һ����������." */);
			return;
		}
		///tian
		InsubjclassVO[] currentBilltypeVOs = getFilterVOs(getCurrentSystem(), getSelectGlPnl().getSelectedPk_glorg()
				/*.getCurrGlOrg().getPk_glorg()*/, getSelectGlPnl().getSelectedPk_glbook());//.getCurrGlBook().getPk_glbook());
		if (currentBilltypeVOs == null)
			return;
		///tian
		CopyDlg dlg = new CopyDlg(this,
				Translator.uiTranslate("fidap", "UPPfidap-000229")/* @res "��Ŀ���ิ��" */,
				currentBilltypeVOs, billTypeName, getSelectGlPnl().getSelectedPk_glorg(),//getCurrGlOrg().getGlorgname(),
				
				
				getSelectGlPnl().getSelectedPk_glbook() /*  .getCurrGlBook().getName()*/, InsubjclassVO.SUBJECTCLASS);
		dlg.showModal();
		int[] oldIndex = getUITree1().getSelectionRows();
		onRefresh();
		getUITree1().setSelectionRows(oldIndex);
		showHintMessage(Translator.uiTranslate("fidap", "UPPfidap-000884")/* @res "���ݸ��Ƴɹ���." */);
	}

	/**
	 * ɾ����Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnDelete() {
		if (!checkLineChoosed())
			return;
		if (MessageDialog.showOkCancelDlg(this,
				Translator.uiTranslate("common", "UC001-0000039")/* @res "ɾ��" */, Translator
						.uiTranslate("fidap", "UPPfidap-000235")/* @res "ȷʵҪɾ����?" */) == MessageDialog.ID_OK) {
			try {
				int[] selectRows = getTable().getSelectedRows();
				InsubjclassVO[] selectVos = new InsubjclassVO[selectRows.length];
				for (int i = 0; i < selectRows.length; i++)
					selectVos[i] = (InsubjclassVO) getTableModel().getVO(selectRows[i]);

				InsubjclassBO_Client.delete(selectVos);
				for (int i = 0; i < selectVos.length; i++)
					vecDB.removeElement(selectVos[i]);
				initTableData(getCurrentSystem());
			} catch (Exception e) {
				showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000236")/* @res "ɾ��ʧ��!" */);
			}

		}
		showHintMessage(Translator.uiTranslate("fidap", "UPPfidap-000237")/* @res "ɾ���ɹ�!" */);
	}

	/**
	 * �޸İ�Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnEdit() {
		//����Ƿ�ѡ��
		if (!checkLineChoosed())
			return;

		getSelectGlPnl().setEnabled(false);//����״̬�²��ɸı���������˲�

		getTable().setSortEnabled(false);

		status = EDIT;

		setButtonStatus(m_boEdit);
		getUITree1().setEnabled(false);
		rowNumber = getTable().getSelectedRow();

		//�޸�ǰ������е����ݱ���
		backupTableData();

		String oldPK = ((InsubjclassVO) getTableModel().getVO(rowNumber)).getDefaultsubjcode();

	}

	/**
	 * Ӱ�����ذ�Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnFactor() {

		if (!checkLineChoosed())
			return;
		InsubjclassVO insubjclass = (InsubjclassVO) getTableModel().getVO(
				getTable().getSelectedRow());
		DapFactorDlg dlg = new DapFactorDlg(this, Translator
				.uiTranslate("fidap", "UPPfidap-000324")/* @res "��Ŀ����Ӱ������" */, insubjclass);
		dlg.showModal();
		if (dlg.getResult() == UIDialog.ID_OK) {
			insubjclass.setFactorVOs(dlg.getFactorVos());
			getTable().updateUI();
		}

	}

	/**
	 * ������Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnHelp() {
	}

	/**
	 * ���水Ŧ���� �������ڣ�(01-7-16 18:15:44)
	 */
	public void onBtnSave() {

		int[] rows = getUITree1().getSelectionRows();

		//EDIT Stop
		stopTableEdit();
		//2004-12-22 ���������޸Ĺ��� -- ��Ц��
		if (getStatus() == ADD || getStatus() == EDIT) {
			InsubjclassVO[] oldVOS = getCurrentVOS();
			int oldRows = oldVOS == null ? 0 : oldVOS.length;
			int newRows = getTableModel().getRowCount() - oldRows;
			InsubjclassVO[] newVOS = new InsubjclassVO[newRows];
			if (newVOS != null) {
				for (int i = oldRows; i < getTableModel().getRowCount(); i++) {
					newVOS[i - oldRows] = (InsubjclassVO) getTableModel().getVO(i);
					newVOS[i - oldRows].setPk_corp(DapCall.getPkcorp());
					newVOS[i - oldRows].setPk_billtype(getCurrentSystem());
					//tian
					newVOS[i - oldRows].setPk_glorg(getSelectGlPnl().getSelectedPk_glorg());   //getCurrGlOrg().getPk_glorg());
					newVOS[i - oldRows].setPk_glbook(getSelectGlPnl().getSelectedPk_glbook()); /*getCurrGlBook()
							.getPk_glbook());*/
				}
			}
			if (oldVOS != null) {
				for (int i = 0; i < oldVOS.length; i++) {
					oldVOS[i] = (InsubjclassVO) getTableModel().getVO(i);
					oldVOS[i].setPk_corp(DapCall.getPkcorp());
					oldVOS[i].setPk_billtype(getCurrentSystem());
					oldVOS[i].setPk_glorg(getSelectGlPnl().getSelectedPk_glorg());   //getCurrGlOrg().getPk_glorg());
					oldVOS[i].setPk_glbook(getSelectGlPnl().getSelectedPk_glbook());   //getCurrGlBook().getPk_glbook());
				}
			}
			try {
				newVOS = checkVO(newVOS);
				if (newVOS != null) {
					for (int i = 0; i < newVOS.length; i++)
						InsubjclassBO_Client.insert(newVOS[i]);
				}
				oldVOS = checkVO(oldVOS);
				if (oldVOS != null) {
					for (int i = 0; i < oldVOS.length; i++)
						InsubjclassBO_Client.update(oldVOS[i]);
				}
			} catch (Exception e) {
				showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000883")/* @res "���ڿ�Ŀ�������ƻ��߱����ظ���" */);
				return;
			}
		}
		status = OTHER;
		onRefresh();

		showHintMessage(nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap", "UPPfidap-000313")/*@res "����ɹ���"*/);
		setButtonStatus(m_boSave);

		getTable().setSortEnabled(true);
		getSelectGlPnl().setEnabled(true);//�ָ��ı���������˲��Ŀ���״̬
		getUITree1().setEnabled(true);

		getUITree1().setSelectionRows(rows);

	}

	/**
	 * ����ʵ�ָ÷�������Ӧ��ť�¼���
	 * 
	 * @version (00-6-1 10:32:59)
	 * 
	 * @param bo ButtonObject
	 */
	public void onButtonClicked(nc.ui.pub.ButtonObject bo) {
		if (bo == m_boAdd) {
			onBtnAdd();
		} else if (bo == m_boEdit) {
			onBtnEdit();
		} else if (bo == m_boDelete) {
			onBtnDelete();
		} else if (bo == m_boSave) {
			onBtnSave();
		} else if (bo == m_boCancel) {
			onBtnCancel();
		} else if (bo == m_boHelp) {
			onBtnHelp();
		} else if (bo == m_boCopyForSys) {
			onBtnCopyForSys();
		} else if (bo == m_boCopyForOrgbook) {
			onBtnCopyForOrgbook();
		} else if (bo == m_boContrast) {
			onBtnContrast();
		} else if (bo == m_boFactor) {
			onBtnFactor();
		}

	}

	/**
	 * ���˲�����
	 */
	private void onBtnCopyForOrgbook() {
		String billTypeName = getCurrentBillypeName();
		if (billTypeName == null) {
			showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000227")/* @res "����ѡ��һ����������." */);
			return;
		}
		String pk_glorg_src = getSelectGlPnl().getSelectedPk_glorg(); //getCurrGlOrg().getPk_glorg();
		String pk_glbook_src = getSelectGlPnl().getSelectedPk_glbook();   //getCurrGlBook().getPk_glbook();
		InsubjCopyDlg dlg = new InsubjCopyDlg(this, pk_glorg_src, pk_glbook_src, 1);
		if (dlg.showModal() == UIDialog.ID_OK) {
			try {
				boolean isCover = dlg.getIsCover();
				GlorgbookVO[] destVos = dlg.getDestGlOrgBooks();
				if (destVos != null && destVos.length > 0) {

					InsubjclassCopyVO copyVO = new InsubjclassCopyVO();
					copyVO.setPk_sys(getCurrentSystem());
					copyVO.setPk_glorg_src(pk_glorg_src);
					copyVO.setPk_glbook_src(pk_glbook_src);
					copyVO.setM_destGlorgbookvo(destVos);
					copyVO.setCoverClass(isCover);
					if (dlg.isSelectedOnly()) {
						copyVO.setList_pk(getSelectPKList());
					}

					InsubjclassBO_Client.copyForOrgbook(copyVO);
					int[] oldIndex = getUITree1().getSelectionRows();
					onRefresh();
					getUITree1().setSelectionRows(oldIndex);
				}
				showHintMessage(Translator.uiTranslate("fidap", "UPPfidap-000884")/* @res "���ݸ��Ƴɹ���." */);
			} catch (Exception ex) {
				Logger.error("���ƿ�Ŀ������ִ���!", ex);
				showErrorMessage(Translator.uiTranslate("fidap", "UPPfidap-000885")/* @res "���ݸ��Ƴ��ִ���." */);
			}
		}
	}

	private Set<String> getSelectPKList() {
		Set<String> listPk = new HashSet<String>();
		int[] selectRows = getTable().getSelectedRows();
		if (selectRows != null) {
			for (int i = 0; i < selectRows.length; i++) {
				InsubjclassVO selectVO = (InsubjclassVO) getTableModel().getVO(selectRows[i]);
				listPk.add(selectVO.getPk_insubjclass());
			}
		}
		return listPk;
	}

	/**
	 * ���´����ݿ�������� �������ڣ�(01-8-10 10:05:10)
	 */
	public void onRefresh() {
		initData();
		getUITree1().removeTreeSelectionListener(this);
		getUITree1().setModel(DapCall.getDapSystemTreeModel());

		if (getTreeModel().getChildCount(getTreeModel().getRoot()) > 0)
			getUITree1().setSelectionRow(1);
		initTableData(getCurrentSystem());

		getUITree1().addTreeSelectionListener(this);

	}

	/**
	 * ���ð�Ŧ��״̬�� �������ڣ�(01-7-16 18:35:23)
	 */
	public void setButtonStatus(ButtonObject btn) {
		if (btn == m_boAdd) {
			m_boAdd.setEnabled(true);
			m_boEdit.setEnabled(false);
			m_boDelete.setEnabled(false);
			m_boSave.setEnabled(true);
			m_boCancel.setEnabled(true);
			m_boCopy.setEnabled(false);
			m_boCopyForSys.setEnabled(false);
			m_boCopyForOrgbook.setEnabled(false);
			m_boContrast.setEnabled(false);
			m_boFactor.setEnabled(false);

		} else if (btn == m_boEdit) {
			m_boAdd.setEnabled(true);
			m_boEdit.setEnabled(false);
			m_boDelete.setEnabled(false);
			m_boSave.setEnabled(true);
			m_boCancel.setEnabled(true);
			m_boCopy.setEnabled(false);
			m_boCopyForSys.setEnabled(false);
			m_boCopyForOrgbook.setEnabled(false);
			m_boContrast.setEnabled(false);
			m_boFactor.setEnabled(false);
		} else if (btn == m_boSave) {
			m_boAdd.setEnabled(true);
			m_boEdit.setEnabled(true);
			m_boDelete.setEnabled(true);
			m_boSave.setEnabled(false);
			m_boCancel.setEnabled(false);
			m_boCopy.setEnabled(true);
			m_boCopyForSys.setEnabled(true);
			m_boCopyForOrgbook.setEnabled(true);
			m_boContrast.setEnabled(true);
			m_boFactor.setEnabled(true);
		} else if (btn == m_boCancel) {
			m_boAdd.setEnabled(true);
			m_boEdit.setEnabled(true);
			m_boDelete.setEnabled(true);
			m_boSave.setEnabled(false);
			m_boCancel.setEnabled(false);
			m_boCopy.setEnabled(true);
			m_boCopyForSys.setEnabled(true);
			m_boCopyForOrgbook.setEnabled(true);
			m_boContrast.setEnabled(true);
			m_boFactor.setEnabled(true);
		} else {
			m_boAdd.setEnabled(true);
			m_boEdit.setEnabled(true);
			m_boDelete.setEnabled(true);
			m_boSave.setEnabled(false);
			m_boCancel.setEnabled(false);
			m_boCopy.setEnabled(true);
			m_boCopyForSys.setEnabled(true);
			m_boCopyForOrgbook.setEnabled(true);
			m_boContrast.setEnabled(true);
			m_boFactor.setEnabled(true);

		}
		m_boSpace.setEnabled(false);
		for (int i = 0; i < m_MainButtonGroup.length; i++) {
			updateButton(m_MainButtonGroup[i]);
		}

	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2002-11-20 11:13:53)
	 * 
	 * @deprecated
	 */
	public void stop() {
		//System.out.println("aa");
	}

	/**
	 * Called whenever the value of the selection changes.
	 * 
	 * @param e the event that characterizes the change.
	 */
	public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
		if (e.getSource() == getUITree1()) {
			this.selectSystemVO = null;
			initTableData(getCurrentSystem());

			//            String year = ClientEnvironment.getInstance().getAccountYear();
			//            String period = ClientEnvironment.getInstance().getAccountMonth();
			//            String wpdate = getPeriodCondition(year, period);
			//            String wp1 = "pk_glorgbook = '"+getPk_glorgbook()+"' and ((stoped = 'N') OR (stoped IS NULL)) and "+ wpdate;
			//            String wp2 = "pk_glorgbook = '"+getPk_glorgbook()+"' and  "+ wpdate;
			//            String wp = "bd_accsubj.pk_accsubj in (select pk_accsubj from bd_accsubj where "+wp1+" union select pk_accsubj from bd_accsubj_histry where "+wp2+")";
			//
			//            ref_AccSubj.getRefModel().setWherePart(wp);
			//            ref_AccSubj.getRefModel().reloadData();
		}

	}

	/**
	 * This method initializes UIPanel
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	private UIPanel getUIPanel() {
		if (UIPanel == null) {
			BorderLayout borderLayout4 = new BorderLayout();
			UIPanel = new UIPanel();
			UIPanel.setLayout(borderLayout4);
			borderLayout4.setVgap(1);
			UIPanel.add(getSelectGlPnl(), java.awt.BorderLayout.NORTH);
			UIPanel.add(getUITablePane1(), java.awt.BorderLayout.CENTER);
		}
		return UIPanel;
	}

	/**
	 * This method initializes selectGlPnl
	 * 
	 * @return nc.ui.dap.accrule.SelectGlPnl
	 */
	private SelectGlPnl getSelectGlPnl() {
		if (selectGlPnl == null) {
			FlowLayout flowLayout8 = new FlowLayout();
			selectGlPnl = new SelectGlPnl();
			selectGlPnl.setLayout(flowLayout8);
			flowLayout8.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout8.setVgap(2);
			selectGlPnl.setPreferredSize(new java.awt.Dimension(310, 26));
			selectGlPnl.addValueChangedListener(this);
		}
		return selectGlPnl;
	}

	private UIRefPane getAccsubjRefPane() {
		if (ref_AccSubj == null) {
			//ref_AccSubj.setRefType(1);
			ref_AccSubj = PubFunctionUI.getUIRefPane(getPk_glorgbook());			
			UIRefCellEditor cellEditor = new UIRefCellEditor(ref_AccSubj);
			getUITablePane1().getTable().getColumnModel().getColumn(3).setCellEditor(cellEditor);
		}
		return ref_AccSubj;
	}

	/**
	 * ���������˲��仯�ļ�������
	 * 
	 * @param event
	 */
	public void valueChanged(ValueChangedEvent event) {
		if (event.getSource() == this.getSelectGlPnl()) {
			initPk_glorgbook();
			ref_AccSubj = null;
			initTableData(getCurrentSystem());
		} else if (event.getSource() == this.getAccsubjRefPane()) {
			int currRow = getUITablePane1().getTable().getSelectedRow();
			InsubjclassVO tempvo = (InsubjclassVO) getTableModel().getVO(currRow);
			tempvo.setDefaultsubjcode(getAccsubjRefPane().getRefPK());
			tempvo.setDefaultsubjname(getAccsubjRefPane().getRefName());
		}
	}

	/**
	 * �رմ��ڵĿͻ��˽ӿڡ����ڱ���������ɴ��ڹر�ǰ�Ĺ�����
	 * 
	 * @return boolean ����ֵΪtrue��ʾ�����ڹرգ�����ֵΪfalse��ʾ�������ڹرա�
	 * 
	 * �������ڣ�(2001-8-8 13:52:37)
	 */
	public boolean onClosing() {
		//������ڱ༭����ʾ�û�
		if (getStatus() == ADD || getStatus() == EDIT) {
			String msg = Translator.translate("UPPfidap-000886");
			//String msg = "���ڱ༭���Ƿ񱣴��رգ�";
			int res = showYesNoCancelMessage(msg);
			if (res == MessageDialog.ID_YES) {
				onBtnSave();
				return true;
			} else if (res == MessageDialog.ID_NO) {
				return true;
			} else if (res == MessageDialog.ID_CANCEL) {
				return false;
			}
		}
		return true;
	}

}