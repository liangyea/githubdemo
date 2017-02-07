package nc.ui.bankpub.eliminaterule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IRuleSet;
import nc.itf.bankpub.pub.ISystemType;
import nc.ui.bankpub.system.JKTableModel;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UISplitPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITree;
import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.pub.BusinessException;

public class EliminateRuleUI extends ToftPanel implements ActionListener, TreeSelectionListener
{
	
	private ButtonObject m_add = new ButtonObject("增加","增加",2);
	private ButtonObject m_del = new ButtonObject("删除","删除",2);
	private ButtonObject m_mod = new ButtonObject("修改","修改",2);	
	private ButtonObject m_sav = new ButtonObject("保存","保存",2);
	private ButtonObject m_ref = new ButtonObject("刷新","刷新",2);
	private ButtonObject m_can = new ButtonObject("取消","取消",2);
	private ButtonObject m_delRule = new ButtonObject("删除规则","删除规则",2);
	private ButtonObject m_ruleSet = new ButtonObject("规则设置","规则设置",2);
	private ButtonObject[] m_buts = new ButtonObject[]{m_add, m_del, m_mod, m_sav, m_ref, m_can, m_delRule, m_ruleSet};
	
	private UIScrollPane c_scrollPane = null;
	private UITable c_table = null;
	private JKTableModel c_tableModel = null;
	private UIPanel c_panel = null;
	private UIComboBox c_combox = null;
	private UISplitPane c_splitPane = null;
	private UITree c_tree = null;
	
	private RuleSetVO[] m_ruls = null;
	public RuleSetVO m_selRule = null;
	public final static int m_insert = 1;
	public final static int m_cancel = 2;
	public final static int m_modify = 3;
	
	public int m_state = m_cancel;
	public int m_selRow = -1;
	
	public EliminateRuleUI()
	{
		super();
		init();
	}
	
	public void init()
	{
		this.setLayout(new BorderLayout());
		this.setButtons(m_buts);
		this.updateButtons();
		this.add(getC_panel(),BorderLayout.NORTH);
		this.add(getC_splitPane(), java.awt.BorderLayout.CENTER);
		refreshTable();
		refreshTree();
		onState(m_cancel);
	}
	
	public String getTitle() {
		return "排除规则设置";
	}

	public void onButtonClicked(ButtonObject selBut) {
		if(selBut==m_add)
			on_add();
		if(selBut==m_del)
			on_del();
		if(selBut==m_mod)
			on_mod();
		if(selBut==m_sav)
			on_sav();
		if(selBut==m_ref)
			on_ref();
		if(selBut==m_can)
			on_can();
		if(selBut == m_delRule)
			on_delRule();
		if(selBut==m_ruleSet)
			on_ruleSet();
	}

	public void on_add()
	{
		if(getC_tableModel().getColumnCount()<=0){MessageDialog.showErrorDlg(this, "错误", "暂无设定规则列，不能添加行");return;}
		getC_tableModel().addRow(1);
		m_selRow = getC_tableModel().getRowCount()-1;
		getC_tableModel().setRowEditable(m_selRow,true);
		m_state = m_insert;
		onState(m_state);
	}
	
	public void on_del()
	{
		m_selRow = getC_table().getSelectedRow();
		if(m_selRow<0){MessageDialog.showErrorDlg(this, "错误", "请选择一行进行删除操作");return;}
		EliminateRuleVO delVO = m_selRule.getColValues()[m_selRow];
		IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
		try {ie.delValue(delVO.getPk_eliminaterule());m_selRule.setColValues(ie.queryValues(m_selRule.getPk_ruleset()));} catch (Exception e) {e.printStackTrace();}
		refreshTable();
		onState(m_cancel);
	}
	
	public void on_mod()
	{
		m_selRow = getC_table().getSelectedRow();
		if(m_selRow<0){MessageDialog.showErrorDlg(this, "错误", "请选择一行进行修改操作");return;}
		getC_tableModel().setRowEditable(m_selRow,true);
		m_state = m_modify;
		onState(m_state);
	}
	
	public void on_sav()
	{
		if(getC_table().getCellEditor()!=null) getC_table().getCellEditor().stopCellEditing();
		EliminateRuleVO vo = new EliminateRuleVO();
		FileDefineVO[] defines = m_selRule.getFileDefines();
		if(defines==null || defines.length<=0)
		{
			MessageDialog.showErrorDlg(this, "错误", "无法取到列值");
			return;
		}
		for(int i=0;i<defines.length;i++)
		{
			int xh = defines[i].getXh();
			Class clas = vo.getClass();
			Object cellValue = getC_tableModel().getValueAt(m_selRow, i);
			Class[] args = new Class[]{cellValue.getClass()};
			try 
			{
				Method method = clas.getMethod("setCol"+xh, args);
				method.invoke(vo, new Object[]{cellValue});
			} catch (Exception e) {e.printStackTrace();} 
		}
		if(m_state == m_insert)
			vo.setFk_ruleset(m_selRule.getPk_ruleset());
		if(m_state == m_modify)
		{
			vo.setPk_eliminaterule(m_selRule.getColValues()[m_selRow].getPk_eliminaterule());
			vo.setFk_ruleset(m_selRule.getPk_ruleset());
		}
		IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
		try {ie.insValue(vo);m_selRule.setColValues(ie.queryValues(m_selRule.getPk_ruleset()));} catch (Exception e) {e.printStackTrace();}
		refreshTable();
		m_state = m_cancel;
		onState(m_state);
	}
	
	public void on_ref()
	{
		refreshTree();
		refreshTable();
		m_state = m_cancel;
		onState(m_state);
	}
	
	public void on_can()
	{
		refreshTable();
		onState(m_cancel);
	}
	
	public void on_delRule()
	{
		if(m_selRule == null)
		{
			MessageDialog.showErrorDlg(this,"错误","请选择规则完成删除规则操作");
			return;
		}
		IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
		try {ie.delByPK(m_selRule.getPk_ruleset());} catch (Exception e) {e.printStackTrace();}
		try {m_ruls = ie.queryBySystemPK(m_selRule.getFk_system());} catch (Exception e1) {e1.printStackTrace();}
		refreshTree();
		refreshTable();
		m_state = m_cancel;
		onState(m_state);
	}
	
	public void on_ruleSet()
	{
		RuleSetDialog dialog = new RuleSetDialog(m_selRule);
		dialog.showModal();
		refreshTree();
		refreshTable();
	}
	
	public void onState(int state)
	{
		if(state == m_insert || state == m_modify)
		{
			m_add.setEnabled(false);
			m_del.setEnabled(false);
			m_mod.setEnabled(false);
			m_sav.setEnabled(true);
			m_ref.setEnabled(false);
			m_can.setEnabled(true);
			m_ruleSet.setEnabled(false);
			m_delRule.setEnabled(false);
			c_tree.setEnabled(false);
			c_combox.setEnabled(false);
			updateButtons();
		}
		if(state == m_cancel)
		{
			m_add.setEnabled(true);
			m_del.setEnabled(true);
			m_mod.setEnabled(true);
			m_sav.setEnabled(false);
			m_ref.setEnabled(true);
			m_can.setEnabled(false);
			m_ruleSet.setEnabled(true);
			m_delRule.setEnabled(true);
			c_tree.setEnabled(true);
			c_combox.setEnabled(true);
			m_state = m_cancel;
			m_selRow = -1;
			updateButtons();
		}
	}
	/*
	 * 刷新表，刷新表体，表头
	 * */
	public void refreshTable()
	{
		if(m_selRule!=null)
		{
			FileDefineVO[] defines = m_selRule.getFileDefines();
			EliminateRuleVO[] colValues = m_selRule.getColValues();
			Object obj[][] = null;
			if(defines!=null && defines.length>0)
			{
				Vector colNames = new Vector();
				for(int i=0;i<defines.length;i++)
				{
					colNames.add(defines[i].getColumnname());
				}
				getC_tableModel().setColumnIdentifiers(colNames);		
				if(colValues!=null && colValues.length>0)
				{
					obj = new Object[colValues.length][defines.length];
					for(int row=0;row<colValues.length;row++)
					{
						EliminateRuleVO tmpValues = colValues[row];
						Class clas = tmpValues.getClass();
						for(int col=0;col<defines.length;col++)
						{
							int colNum = defines[col].getXh().intValue();
							obj[row][col]=null;
							try {
								Method method = clas.getMethod("getCol"+colNum,null);
								obj[row][col] = method.invoke(tmpValues, null);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				else
					obj = new Object[0][0];
				getC_tableModel().setDataVector(obj);
			}
			else
			{
				getC_tableModel().setColumnIdentifiers(null);
				getC_tableModel().setDataVector(new Object[0][0]);
			}
		}
		else
		{
			getC_tableModel().setColumnIdentifiers(null);
			getC_tableModel().setDataVector(new Object[0][0]);
		}
		getC_table().updateUI();
	}

	public void refreshTree()
	{
		SystemTypeVO sysTypeVO = (SystemTypeVO)getC_combox().getSelectedItem();
		if(sysTypeVO!=null)
		{
			IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
			try {m_ruls = ie.queryBySystemPK(sysTypeVO.getPk_system());} catch (Exception e1) {e1.printStackTrace();}
		}
		else
			m_ruls = null;
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)getC_tree().getModel().getRoot();
		root.removeAllChildren();
		if(m_ruls!=null && m_ruls.length>0)
			for(int i=0;i<m_ruls.length;i++)
			{
				DefaultMutableTreeNode tempVO = new DefaultMutableTreeNode();
				tempVO.setUserObject(m_ruls[i]);
				root.add(tempVO);
			}
		getC_tree().updateUI();
		m_selRule = null;
	}
	
	public UIPanel getC_panel() {
		if(c_panel == null)
		{
			c_panel = new UIPanel();
			c_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label = new JLabel("系统");
			c_panel.add(label);
			c_panel.add(getC_combox());
		}
		return c_panel;
	}

	public UIScrollPane getC_scrollPane() {
		if(c_scrollPane == null)
		{
			c_scrollPane = new UIScrollPane();
			c_scrollPane.setViewportView(getC_table());
			
		}
		return c_scrollPane;
	}

	public UITable getC_table() {
		if(c_table == null)
		{
			c_table = new UITable();
			c_table.setModel(getC_tableModel());
		}
		return c_table;
	}

	public JKTableModel getC_tableModel() {
		if(c_tableModel == null)
		{
			c_tableModel = new JKTableModel();
		}
		return c_tableModel;
	}

	public UISplitPane getC_splitPane() {
		if(c_splitPane == null)
		{
			c_splitPane = new UISplitPane();
			c_splitPane.setLeftComponent(getC_tree());
			c_splitPane.setRightComponent(getC_scrollPane());
		}
		return c_splitPane;
	}

	public UITree getC_tree() {
		if(c_tree==null)
		{
			c_tree = new UITree(new DefaultMutableTreeNode("已设置规则"));
			c_tree.addTreeSelectionListener(this);
		}
		return c_tree;
	}

	public UIComboBox getC_combox() {
		if(c_combox == null)
		{
			c_combox = new UIComboBox();
			ISystemType ie = (ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
			SystemTypeVO[] vos = null;
			try {vos = ie.queryAllSystemTypeVOs();} catch (BusinessException e) {e.printStackTrace();}
			if(vos!=null&&vos.length>0)
			{
				for(int i=0;i<vos.length;i++)
				{
					c_combox.addItem(vos[i]);
				}
			}
			c_combox.addActionListener(this);
			if(c_combox.getItemCount()>0)
				c_combox.setSelectedIndex(0);
		}
		return c_combox;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof UIComboBox && e.getSource()==getC_combox())
		{
			SystemTypeVO sysTypeVO = (SystemTypeVO)((UIComboBox)e.getSource()).getSelectedItem();
			if(sysTypeVO!=null)
			{
				IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
				try {m_ruls = ie.queryBySystemPK(sysTypeVO.getPk_system());} catch (Exception e1) {e1.printStackTrace();}
			}
			refreshTree();
			refreshTable();
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
		if(e.getSource() instanceof UITree && e.getSource() == getC_tree())
		{
			TreePath selPath = getC_tree().getSelectionPath();
			if(selPath!=null)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)selPath.getLastPathComponent();
				Object obj = node.getUserObject();
				if(obj instanceof RuleSetVO)
					m_selRule = (RuleSetVO)obj;
				else
					m_selRule = null;
			}
			else
				m_selRule = null;
			refreshTable();
		}
	}



}
