package nc.ui.bankpub.eliminaterule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IFileDefine;
import nc.itf.bankpub.pub.IRuleSet;
import nc.itf.bankpub.pub.ISystemType;
import nc.ui.bankpub.system.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITextField;
import nc.vo.bankpub.eliminaterule.EliminateRuleVO;
import nc.vo.bankpub.eliminaterule.RuleSetVO;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.pub.BusinessException;

public class RuleSetDialog extends UIDialog implements ActionListener{

	private UIPanel c_panel = null;
	private UIPanel c_northpanel = null;
	private UILabel UILabel = null;
	private UITextField c_ruleName = null;
	private UIPanel c_southpane = null;
	private JButton c_sure = null;
	private JButton c_cancel = null;
	private UIScrollPane c_centerpane = null;
	private UITable c_table = null;
	private JKTableModel c_tableModel = null;
	private UIComboBox c_combox = null;
	
	private RuleSetVO ruleVO = null;
	private FileDefineVO[] defineVOS = null;
	private FileDefineVO[] allDefineVOS = null;
	private String pk_system = null;
	/**
	 * This method initializes 
	 * 
	 */
	public RuleSetDialog(RuleSetVO tmpVO) {
		super();
		ruleVO = tmpVO;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(490, 440));
        this.setContentPane(getC_panel());
        refreshData();
        refreshTable();
	}
	
	public void refreshData()
	{
		IFileDefine ie = (IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
		try {
			allDefineVOS = ie.queryAllFileDefineVOs();
		} catch (Exception e) {e.printStackTrace();}
		SystemTypeVO vo = (SystemTypeVO)getC_combox().getSelectedItem();
		pk_system = vo.getPk_system();
		Vector v = new Vector();
		if(allDefineVOS!=null && allDefineVOS.length!=0){
	
			for(int i=0;i<allDefineVOS.length;i++){
				if(this.pk_system.equals(allDefineVOS[i].pk_system)){
					v.addElement(allDefineVOS[i]);
				}
			}
		}
		if(v.size()>0){
			allDefineVOS = new FileDefineVO[v.size()];
			v.copyInto(allDefineVOS);
		}else{
			allDefineVOS = null;
		}
		if(ruleVO!=null)
			defineVOS = ruleVO.getFileDefines();
	}

	public void refreshTable()
	{
		if(allDefineVOS==null||allDefineVOS.length<=0)
		{
			getC_tableModel().setDataVector(new Object[0][0]);
			getC_table().updateUI();
			return;
		}
		Object[][] obj = new Object[allDefineVOS.length][2];
		Hashtable defineHash = new Hashtable();
		if(defineVOS!=null && defineVOS.length>0)
		for(int i=0;i<defineVOS.length;i++)
		{
			defineHash.put(defineVOS[i].getPk_filedefine(), defineVOS[i]);
		}
		for(int i=0;i<allDefineVOS.length;i++)
		{
			obj[i][0]=allDefineVOS[i].getColumnname();
			if(defineHash.containsKey(allDefineVOS[i].getPk_filedefine()))
				obj[i][1] = new Boolean(true);
			else
				obj[i][1] = false;
		}
		getC_tableModel().setDataVector(obj);
		getC_table().updateUI();
	}
	/**
	 * This method initializes c_panel	
	 * 	
	 * @return nc.ui.pub.beans.UIPanel	
	 */
	private UIPanel getC_panel() {
		if (c_panel == null) {
			c_panel = new UIPanel();
			c_panel.setLayout(new BorderLayout());
			c_panel.add(getC_northpanel(), BorderLayout.NORTH);
			c_panel.add(getC_southpane(), BorderLayout.SOUTH);
			c_panel.add(getC_centerpane(), BorderLayout.CENTER);
		}
		return c_panel;
	}

	/**
	 * This method initializes c_northpanel	
	 * 	
	 * @return nc.ui.pub.beans.UIPanel	
	 */
	private UIPanel getC_northpanel() {
		if (c_northpanel == null) {
			UILabel la1 = new UILabel();
			la1.setPreferredSize(new Dimension(60, 22));
			la1.setText("规则名称：");
			UILabel la2 = new UILabel();
			la2.setPreferredSize(new Dimension(40, 22));
			la2.setText("系统：");
			UILabel = new UILabel();
			c_northpanel = new UIPanel();
			c_northpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			c_northpanel.add(la1);
			c_northpanel.add(getC_ruleName());
			c_northpanel.add(la2);
			c_northpanel.add(getC_combox());
		}
		return c_northpanel;
	}

	/**
	 * This method initializes c_ruleName	
	 * 	
	 * @return nc.ui.pub.beans.UITextField	
	 */
	private UITextField getC_ruleName() {
		if (c_ruleName == null) {
			c_ruleName = new UITextField();
			c_ruleName.setPreferredSize(new Dimension(200, 22));
			if(ruleVO!=null)
				c_ruleName.setText(ruleVO.getRulename());
		}
		return c_ruleName;
	}

	public UILabel getUILabel() {
		if(UILabel == null)
		{
			UILabel = new UILabel("规则名称：");
			UILabel.setPreferredSize(new Dimension(80, 22));
		}
		return UILabel;
	}

	/**
	 * This method initializes c_southpane	
	 * 	
	 * @return nc.ui.pub.beans.UIPanel	
	 */
	private UIPanel getC_southpane() {
		if (c_southpane == null) {
			c_southpane = new UIPanel();
			c_southpane.add(getC_sure(), null);
			c_southpane.add(getC_cancel(), null);
		}
		return c_southpane;
	}

	/**
	 * This method initializes c_sure	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getC_sure() {
		if (c_sure == null) {
			c_sure = new JButton();
			c_sure.setText("确定");
			c_sure.addActionListener(this);
		}
		return c_sure;
	}

	/**
	 * This method initializes c_cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getC_cancel() {
		if (c_cancel == null) {
			c_cancel = new JButton();
			c_cancel.setText("取消");
			c_cancel.addActionListener(this);
		}
		return c_cancel;
	}

	/**
	 * This method initializes c_centerpane	
	 * 	
	 * @return nc.ui.pub.beans.UIScrollPane	
	 */
	private UIScrollPane getC_centerpane() {
		if (c_centerpane == null) {
			c_centerpane = new UIScrollPane();
			c_centerpane.setViewportView(getC_table());
		}
		return c_centerpane;
	}

	/**
	 * This method initializes c_table	
	 * 	
	 * @return nc.ui.pub.beans.UITable	
	 */
	private UITable getC_table() {
		if (c_table == null) {
			c_table = new UITable();
			c_table.setModel(getC_tableModel());
			c_table.getColumnModel().getColumn(1).setCellEditor(new UIRefCellEditor(new UICheckBox()));
		}
		return c_table;
	}

	public JKTableModel getC_tableModel() {
		if(c_tableModel == null)
		{
			c_tableModel = new JKTableModel();
			Vector v = new Vector();
			v.add("列名");
			v.add("选择");
			c_tableModel.setColumnIdentifiers(v);
			c_tableModel.setColEditable(1, true);
		}
		return c_tableModel;
	}
	
	public UIComboBox getC_combox() {
		if(c_combox == null)
		{
			c_combox = new UIComboBox();
			ISystemType ie = (ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
			SystemTypeVO[] vos = null;
			int selIdx = -1;
			try {vos = ie.queryAllSystemTypeVOs();} catch (BusinessException e) {e.printStackTrace();}
			if(vos!=null&&vos.length>0)
			{
				for(int i=0;i<vos.length;i++)
				{
					c_combox.addItem(vos[i]);
					if(ruleVO!=null && vos[i].getPk_system().equals(ruleVO.getFk_system()))
						selIdx = i;
				}
			}
			if(c_combox.getItemCount()>0)
				if(selIdx>0)
					c_combox.setSelectedIndex(selIdx);
				else
					c_combox.setSelectedIndex(0);
			c_combox.addActionListener(this);
		}
		return c_combox;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton && e.getSource()==getC_sure())
		{
			RuleSetVO insVO = (ruleVO==null?new RuleSetVO():ruleVO);
			insVO.setRulename(getC_ruleName().getText());
			if(getC_combox().getSelectedItem()!=null)
					insVO.setFk_system(((SystemTypeVO)getC_combox().getSelectedItem()).getPk_system());
			Vector defines = new Vector();
			Hashtable nums = new Hashtable();
			if(allDefineVOS!=null && allDefineVOS.length>0)
			for(int i=0;i<allDefineVOS.length;i++)
			{
				if(((Boolean)getC_tableModel().getValueAt(i, 1)).booleanValue() == true)
				{
					defines.add(allDefineVOS[i]);
					nums.put(new Integer(allDefineVOS[i].getXh()), new Integer(allDefineVOS[i].getXh()));
				}
					
			}
			if(defines.size()>0)
			{
				FileDefineVO[] tmpDefines = new FileDefineVO[defines.size()];
				defines.copyInto(tmpDefines);
				insVO.setFileDefines(tmpDefines);
			}
			if(insVO.getColValues()!=null && insVO.getColValues().length>0)
			{
				for(int i=0;i<insVO.getColValues().length;i++)
				{
					EliminateRuleVO tmp = insVO.getColValues()[i];
					for(int j=1;j<=16;j++)
					{
						if(nums.containsKey(new Integer(j))) continue;
						Class clas = tmp.getClass();
						try {
							Method method = clas.getMethod("setCol"+j,new Class[]{String.class});
							method.invoke(tmp, new Object[]{""});
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			IRuleSet ie = (IRuleSet)NCLocator.getInstance().lookup(IRuleSet.class.getName());
			try{ie.update(insVO);}catch(Exception ev){ev.printStackTrace();}
			closeOK();
		}
		if(e.getSource() instanceof JButton && e.getSource()==getC_cancel())
		{
			closeCancel();
		}
		if(e.getSource() instanceof UIComboBox && e.getSource()==getC_combox())
		{
			refreshData();
			refreshTable();
		}
	}

//	class OwnCheckBox extends UICheckBox
//	{
//		private Object userObject = null;
//		public OwnCheckBox(Object obj)
//		{
//			super();
//			userObject = obj;
//			if(userObject!=null)
//				setText(userObject.toString());
//			
//		}
//		public String getText()
//		{
//			if(userObject!=null)
//				return userObject.toString();
//			else
//				return "无值";
//		}
//		
//		public Object getUserObject() {
//			return userObject;
//		}
//		public void setUserObject(Object userObject) {
//			this.userObject = userObject;
//		}
//	}
	
}
