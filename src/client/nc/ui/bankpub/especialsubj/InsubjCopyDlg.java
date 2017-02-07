package nc.ui.bankpub.especialsubj;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import nc.bs.logging.Logger;
import nc.ui.bd.GLOrgBookAccBO_Client;
import nc.ui.bd.ref.AbstractRefGridTreeModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.vo.bd.b54.GlorgbookVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class InsubjCopyDlg extends UIDialog implements ActionListener, ValueChangedListener {

	static class TbVO {
		private String name;

		private String pk;

		public TbVO(String name, String pk) {
			setName(name);
			setPk(pk);
		}

		/**
		 * @return 返回 name。
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name 要设置的 name。
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return 返回 pk。
		 */
		public String getPk() {
			return pk;
		}

		/**
		 * @param pk 要设置的 pk。
		 */
		public void setPk(String pk) {
			this.pk = pk;
		}

		public String toString() {
			return getName();
		}
	}

	static class TbModel extends DefaultTableModel {
		private String[] m_colName = new String[] {
				nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap", "UPPfidap-000863")/*@res "目的主体账簿编码"*/,
				nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap", "UPPfidap-000864") /*@res "目的主体账簿名称"*/
		};

		/* （非 Javadoc）
		 * @see nc.ui.pub.beans.table.VOTableModel#getColumnName(int)
		 */
		public String getColumnName(int col) {

			return m_colName[col];
		}

		/* （非 Javadoc）
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		public int getColumnCount() {

			return m_colName.length;
		}

		/* （非 Javadoc）
		 * @see javax.swing.table.TableModel#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int row, int column) {

			return false;
		}
	}

	private javax.swing.JPanel jContentPane = null;

	private UIPanel UIPanel = null;

	private UITablePane UITablePane = null;

	private UIPanel UIPanel1 = null;

	private UIRefPane srcGlOrgBookRef = null;

	private UIButton btnOk = null;

	private UIButton btnCancel = null;

	private UIRefPane desGlOrgBookRef = null;

	private UIButton btnAdd = null;

	private UIButton btnDelete = null;

	private int m_destSystem;

	private UICheckBox ckbIsCover = null;

	private String m_srcGlorgPk = null;

	private String m_srcGlbookPk = null;
	private GlorgbookVO[] destCopyGlorgBook = null;
	private boolean isCover = false;

	private UICheckBox selectedCopyCK = null;

	/**
	 * This method initializes UIPanel
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	private UIPanel getUIPanel() {
		if (UIPanel == null) {
			UILabel srcGlOrgBookLb = new UILabel();
			UILabel desGlOrgBookLb = new UILabel();
			GridBagConstraints gridBagConstraints00 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints01 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			UIPanel = new UIPanel();
			UIPanel.setLayout(new GridBagLayout());
			srcGlOrgBookLb.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
					"UPPfidap-000861")/*@res "来源主体账簿"*/);
			srcGlOrgBookLb.setILabelType(5/** 必输框 */
			);
			UIPanel.setPreferredSize(new java.awt.Dimension(491, 64));
			desGlOrgBookLb.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
					"UPPfidap-000862")/*@res "目的主体账簿"*/);
			desGlOrgBookLb.setILabelType(5/** 必输框 */
			);
			gridBagConstraints00.gridx = 0;
			gridBagConstraints00.gridy = 0;
			gridBagConstraints00.insets = new java.awt.Insets(1, 1, 1, 1);
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.insets = new java.awt.Insets(6, 1, 6, 1);
			gridBagConstraints01.gridx = 0;
			gridBagConstraints01.gridy = 1;
			gridBagConstraints01.insets = new java.awt.Insets(1, 1, 1, 1);
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.insets = new java.awt.Insets(6, 1, 6, 1);
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.insets = new java.awt.Insets(1, 6, 1, 1);
			gridBagConstraints31.gridx = 3;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.insets = new java.awt.Insets(1, 6, 1, 1);
			UIPanel.add(srcGlOrgBookLb, gridBagConstraints00);
			UIPanel.add(getSrcGlOrgBookRef(), gridBagConstraints10);
			UIPanel.add(desGlOrgBookLb, gridBagConstraints01);
			UIPanel.add(getDesGlOrgBookRef(), gridBagConstraints11);
			//            UIPanel.add(getBtnAdd(), gridBagConstraints21);
			UIPanel.add(getBtnDelete(), gridBagConstraints31);
		}
		return UIPanel;
	}

	/**
	 * This method initializes UITablePane
	 * 
	 * @return nc.ui.pub.beans.UITablePane
	 */
	private UITablePane getUITablePane() {
		if (UITablePane == null) {
			UITablePane = new UITablePane();
		}
		return UITablePane;
	}

	/**
	 * 获得表格对象
	 * 
	 * @return
	 */
	private UITable getTable() {
		return getUITablePane().getTable();
	}

	/**
	 * This method initializes UIPanel1
	 * 
	 * @return nc.ui.pub.beans.UIPanel
	 */
	private UIPanel getUIPanel1() {
		if (UIPanel1 == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			UIPanel1 = new UIPanel();
			UIPanel1.setLayout(flowLayout1);
			flowLayout1.setAlignment(java.awt.FlowLayout.RIGHT);
			flowLayout1.setVgap(5);
			flowLayout1.setHgap(10);
			UIPanel1.add(getSelectedCopyCK(), null);
			UIPanel1.add(getCkbIsCover(), null);
			UIPanel1.add(getBtnOk(), null);
			UIPanel1.add(getBtnCancel(), null);
		}
		return UIPanel1;
	}

	/**
	 * This method initializes srcGlorgRef
	 * 
	 * @return nc.ui.pub.beans.UIRefPane
	 */
	private UIRefPane getSrcGlOrgBookRef() {
		if (srcGlOrgBookRef == null) {
			srcGlOrgBookRef = new UIRefPane();
			srcGlOrgBookRef.setRefNodeName("主体账簿");
			srcGlOrgBookRef.setEnabled(false);
			srcGlOrgBookRef.setSize(150, 22);
		}
		return srcGlOrgBookRef;
	}

	/**
	 * This method initializes btnOk
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	private UIButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new UIButton();
			btnOk
					.setText(nc.ui.ml.NCLangRes.getInstance()
							.getStrByID("fidap", "UPPfidap-000502")/*@res "确定"*/);
			btnOk.setPreferredSize(new java.awt.Dimension(77, 21));
		}
		return btnOk;
	}

	/**
	 * This method initializes btnCancel
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	private UIButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new UIButton();
			btnCancel.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap",
					"UPPfidap-000501")/*@res "取消"*/);
			btnCancel.setPreferredSize(new java.awt.Dimension(77, 21));
		}
		return btnCancel;
	}

	/**
	 * This method initializes desGlorgRef
	 * 
	 * @return nc.ui.pub.beans.UIRefPane
	 */
	private UIRefPane getDesGlOrgBookRef() {
		if (desGlOrgBookRef == null) {
			desGlOrgBookRef = new UIRefPane();
			desGlOrgBookRef.setPk_corp("0001");
			desGlOrgBookRef.setRefNodeName("主体账簿");
			AbstractRefGridTreeModel model = (AbstractRefGridTreeModel) desGlOrgBookRef
					.getRefModel();

			model.setClassWherePart(" ISNULL(dr, 0) = 0 ");

			model.setWherePart(" ISNULL(bd_glorgbook.dr, 0) = 0 and isstartuse ='Y' ");
			desGlOrgBookRef.setSize(150, 22);
			desGlOrgBookRef.setMultiSelectedEnabled(true);
			desGlOrgBookRef.setTreeGridNodeMultiSelected(true);
		}
		return desGlOrgBookRef;
	}

	/**
	 * This method initializes btnDelete
	 * 
	 * @return nc.ui.pub.beans.UIButton
	 */
	private UIButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new UIButton();
			btnDelete.setText(nc.ui.ml.NCLangRes.getInstance()
					.getStrByID("common", "UC001-0000039")/*@res "删除"*/);
			btnDelete.setPreferredSize(new java.awt.Dimension(30, 22));
		}
		return btnDelete;
	}

	/**
	 * This method initializes ckbIsCover	
	 * 	
	 * @return nc.ui.pub.beans.UICheckBox	
	 */
	private UICheckBox getCkbIsCover() {
		if (ckbIsCover == null) {
			ckbIsCover = new UICheckBox();
			ckbIsCover.setPreferredSize(new java.awt.Dimension(180, 22));
			ckbIsCover.setText("科目分类编码相同时覆盖");
			ckbIsCover.setToolTipText("科目分类编码相同时覆盖");
		}
		return ckbIsCover;
	}

	/**
	 * This method initializes selectedCopyCK	
	 * 	
	 * @return nc.ui.pub.beans.UICheckBox	
	 */
	private UICheckBox getSelectedCopyCK() {
		if (selectedCopyCK == null) {
			selectedCopyCK = new UICheckBox();
			selectedCopyCK.setPreferredSize(new Dimension(180, 22));
			selectedCopyCK.setText(NCLangRes.getInstance().getStrByID("fidap", "UPPfidap-001016"));
			selectedCopyCK
					.setToolTipText("\u79d1\u76ee\u5206\u7c7b\u7f16\u7801\u76f8\u540c\u65f6\u8986\u76d6");
		}
		return selectedCopyCK;
	}

	public static void main(String[] args) {
	}

	/**
	 * This is the default constructor
	 */
	public InsubjCopyDlg(Container parent, String srcGlorgPk, String srcGlbookPk, int destSystem) {
		super(parent);
		setTitle(nc.ui.ml.NCLangRes.getInstance().getStrByID("fidap", "UPPfidap-000544")/*@res "按账簿复制"*/);
		setDestSystem(destSystem);

		m_srcGlorgPk = srcGlorgPk;
		m_srcGlbookPk = srcGlbookPk;

		try {
			CircularlyAccessibleValueObject rstVo = GLOrgBookAccBO_Client
					.getGlOrgBookVOByPk_GlorgAndPk_Glbook(srcGlorgPk, srcGlbookPk);
			if (rstVo != null) {
				getSrcGlOrgBookRef().setPK(rstVo.getPrimaryKey());
			} else {
				getSrcGlOrgBookRef().setPK(null);
			}
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			Logger.error(e);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			Logger.error(e);
		}
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(652, 391);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		getBtnDelete().addActionListener(this);
		getBtnOk().addActionListener(this);
		getBtnCancel().addActionListener(this);
		getDesGlOrgBookRef().addValueChangedListener(this);
		initTable();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getUIPanel(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getUITablePane(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getUIPanel1(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private void initTable() {
		getTable().setModel(new TbModel());

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getBtnDelete()) {
			onDelete();
		} else if (e.getSource() == getBtnOk()) {
			onOk();
		} else if (e.getSource() == getBtnCancel()) {
			onCancel();
		}

	}

	/**
	 * 取消按钮事件处理
	 */
	private void onCancel() {
		closeCancel();
	}

	/**
	 * 确定按钮事件处理
	 */
	private void onOk() {
		if (m_srcGlorgPk == null || m_srcGlorgPk.trim().length() == 0 || m_srcGlbookPk == null
				|| m_srcGlbookPk.trim().length() == 0) {
			//源主体或帐簿不能为空
			return;
		}

		int rowCount = getTable().getRowCount();
		if (rowCount <= 0) {
			//请增加目的主体和帐簿
			return;
		}

		isCover = getCkbIsCover().isSelected();
		destCopyGlorgBook = new GlorgbookVO[rowCount];
		for (int i = 0; i < rowCount; i++) {
			destCopyGlorgBook[i] = new GlorgbookVO();
			destCopyGlorgBook[i].setPk_glorg(((TbVO) getTable().getValueAt(i, 0)).getPk());
			destCopyGlorgBook[i].setPk_glbook(((TbVO) getTable().getValueAt(i, 1)).getPk());
		}

		closeOK();

	}

	/**
	 * 删除按钮事件处理
	 */
	private void onDelete() {
		int row = getTable().getSelectedRow();
		if (row > -1) {
			((TbModel) getTable().getModel()).removeRow(row);
		}
	}

	/**
	 * 增加按钮事件处理
	 */
	private void onAdd() {
		Object[] desOrgBookCodes = (Object[]) getDesGlOrgBookRef().getRefValues(
				"bd_glorgbook.glorgbookcode");
		Object[] desOrgBookNames = (Object[]) getDesGlOrgBookRef().getRefValues(
				"bd_glorgbook.glorgbookname");
		Object[] desOrgPks = (Object[]) getDesGlOrgBookRef().getRefValues("bd_glorgbook.pk_glorg");
		Object[] desBookPks = (Object[]) getDesGlOrgBookRef()
				.getRefValues("bd_glorgbook.pk_glbook");
		for (int i = 0; desOrgPks != null && i < desOrgPks.length; i++) {
			addOneOrgBookToTable((String) desOrgBookCodes[i], (String) desOrgBookNames[i],
					(String) desOrgPks[i], (String) desBookPks[i]);
		}
		getDesGlOrgBookRef().setPK(null);

	}

	/**
	 * @param desOrgBookCode
	 * @param desOrgBookName
	 * @param desOrgPk
	 * @param desBookPk
	 */
	private void addOneOrgBookToTable(String desOrgBookCode, String desOrgBookName,
			String desOrgPk, String desBookPk) {
		TbVO orgvo = new TbVO(desOrgBookCode, desOrgPk);

		TbVO bookvo = new TbVO(desOrgBookName, desBookPk);
		if (checkDest(desOrgPk, desBookPk)) {
			((DefaultTableModel) getTable().getModel()).addRow(new TbVO[] { orgvo, bookvo });
		}

	}

	/**
	 * 检查目的主体账簿是否已存在于表格中
	 * 
	 * @param desOrgPk
	 * @param desBookPk
	 */
	private boolean checkDest(String desOrgPk, String desBookPk) {
		if (desOrgPk == null || desOrgPk.trim().length() == 0 || desBookPk == null
				|| desBookPk.trim().length() == 0) {
			return false;
		}

		if (desOrgPk.equals(m_srcGlorgPk) && desBookPk.equals(m_srcGlbookPk)) {
			return false;
		}
		int rowCount = getTable().getRowCount();
		String pkorg, pkbook;
		for (int i = 0; i < rowCount; i++) {
			pkorg = ((TbVO) getTable().getValueAt(i, 0)).getPk();
			pkbook = ((TbVO) getTable().getValueAt(i, 1)).getPk();
			if (desOrgPk.equals(pkorg) && desBookPk.equals(pkbook)) {
				return false;
			}
		}

		return true;

	}

	/**
	 * 
	 * 是否覆盖原来的数据
	 * @return
	 */
	public boolean getIsCover() {
		return getCkbIsCover().isSelected();
	}

	public GlorgbookVO[] getDestGlOrgBooks() {
		return destCopyGlorgBook;
	}

	/**
	 * @return 返回 destSystem。
	 */
	private int getDestSystem() {
		return m_destSystem;
	}

	/**
	 * @param destSystem 要设置的 destSystem。
	 */
	private void setDestSystem(int destSystem) {
		m_destSystem = destSystem;
	}

	public void valueChanged(ValueChangedEvent event) {
		// TODO 自动生成方法存根
		if (event.getSource() == getDesGlOrgBookRef()) {
			onAdd();
		}

	}

	public void setIsCoverVisable(boolean isvisable) {
		getCkbIsCover().setVisible(isvisable);
		getSelectedCopyCK().setVisible(isvisable);
	}

	public boolean isSelectedOnly() {
		return getSelectedCopyCK().isSelected();
	}
}