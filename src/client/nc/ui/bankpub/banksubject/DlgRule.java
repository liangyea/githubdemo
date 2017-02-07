package nc.ui.bankpub.banksubject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankSubjContrast;
import nc.itf.bankpub.pub.IFileDefine;
import nc.ui.bankpub.system.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.subjcolshow.SubjColShowVO;
import nc.vo.pub.BusinessException;

public class DlgRule extends UIDialog implements ActionListener {

	private UIPanel contentpane = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private UIButton BnOK = null;
	private UIButton BnCancel = null;
	private UICheckBox chk = null;
	private SubjColShowVO m_columnvo = null;
	private FileDefineVO[] m_filevos = null;
	private Hashtable hash = new Hashtable();
	private Hashtable hashfile = new Hashtable();
	private String pk_system = null;
	private Vector m_return = null;
	private FileDefineVO[] revos = null; 
    public DlgRule(String pk_system) {
        super();
        this.pk_system = pk_system;
		initialize();

    }
    public void setPk_system(String pk_system){
    	this.pk_system = pk_system;
    }
    public void setTable(){
    	initdata();	
    }
    public void initialize(){
    	
        this.setContentPane(getContentpane());
        this.setTitle("选择规则");
        this.setSize(300, 279);
        initdata();	
    }
	private void initdata(){
		if(this.pk_system==null){
			return ; 
		}
		IBankSubjContrast ib = (IBankSubjContrast)NCLocator.getInstance().lookup(IBankSubjContrast.class.getName());
		SubjColShowVO[] m_columnvos = null;
		try {
			m_columnvos = ib.queryAllSubjColShowVO();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
//		Vector v = new Vector();
//		if(m_columnvos!=null && m_columnvos.length!=0){
//			for(int i=0;i<m_columnvos.length;i++){
//				if(m_columnvos[i].getPk_system().equals(this.pk_system)){
//					v.addElement(m_columnvos[i]);
//				}
//			}
//		}
//		if(v.size()>0){
//			m_columnvos = new SubjColShowVO[v.size()];
//			v.copyInto(m_columnvos);
//		}
		if(m_columnvos!=null && m_columnvos.length!=0){
			for(int i=0;i<m_columnvos.length;i++){
				m_return = new Vector();
				if(m_columnvos[i].getPk_system().trim().equals(pk_system.trim())){
					m_columnvo = new SubjColShowVO();
					m_columnvo = m_columnvos[i];
					if(m_columnvos[i].getDef1()==null){
						hash.put("", "xh");
					}else{
						hash.put(m_columnvos[i].getDef1().trim(), "xh");
						m_return.addElement(hashfile.get(m_columnvos[i].getDef1().trim()));
					}
					if(m_columnvos[i].getDef2()==null){
						hash.put("", "xh");
					}else{
						hash.put(m_columnvos[i].getDef2().trim(), "xh");
						m_return.addElement(hashfile.get(m_columnvos[i].getDef2().trim()));
					}
					if(m_columnvos[i].getDef3()==null){
						hash.put("", "xh");
					}else{
						hash.put(m_columnvos[i].getDef3().trim(), "xh");
						m_return.addElement(hashfile.get(m_columnvos[i].getDef3().trim()));
					}
					if(m_columnvos[i].getDef4()==null){
						hash.put("", "xh");
					}else{
						hash.put(m_columnvos[i].getDef4().trim(), "xh");
						m_return.addElement(hashfile.get(m_columnvos[i].getDef4().trim()));
					}
					if(m_columnvos[i].getDef5()==null){
						hash.put("", "xh");
					}else{
						hash.put(m_columnvos[i].getDef5().trim(), "xh");
						m_return.addElement(hashfile.get(m_columnvos[i].getDef5().trim()));
					}
				}
				
			}
			if(m_return.size()>0){
				revos = new FileDefineVO[m_return.size()] ;
				m_return.copyInto(revos);
			}
		}
		IFileDefine ifi = (IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
		try {
			m_filevos= ifi.queryAllFileDefineVOs();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Vector v = new Vector();
		if(m_filevos!=null && m_filevos.length!=0){
			for(int i=0;i<m_filevos.length;i++){
				if(m_filevos[i].getPk_system().equals(this.pk_system)){
					v.addElement(m_filevos[i]);
				}
			}
		}
		if(v.size()>0){
			m_filevos = new FileDefineVO[v.size()];
			v.copyInto(m_filevos);
		}else{
			m_filevos = null;
		}
		Object[][] data = new Object[0][3];
		if(m_filevos!=null && m_filevos.length!=0){
			data = new Object[m_filevos.length][3];
			for(int i=0;i<m_filevos.length;i++){
				data[i][0] = new Object();
				data[i][0] = getIsShow( m_filevos[i].getXh().toString().trim());
				data[i][1] = new Object();
				data[i][1] = m_filevos[i].getXh();
				data[i][2] = new Object();
				data[i][2] = m_filevos[i].getColumnname();
				hashfile.put(m_filevos[i].getXh().toString().trim(), m_filevos[i]);
			}
			
		}
		getTablemodel().setDataVector(data);
		getTable().setModel(getTablemodel());
		
	}
	private boolean getIsShow(String xh){
	
		if(hash.containsKey(xh.trim())){
			return true;
		}
		return false ;
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成方法存根
		if(e.getSource()== getBnOK()){
			onBnOK();
		}
	}
	private UIPanel getContentpane(){
		if(contentpane==null){
			contentpane = new UIPanel();
			contentpane.setLayout(new BorderLayout());
			UIPanel panel = new UIPanel(new FlowLayout(FlowLayout.LEFT));
			panel.add(getBnOK());
			panel.add(getBnCancel());
			contentpane.add(getTablePane(),BorderLayout.CENTER);
			contentpane.add(panel, BorderLayout.SOUTH);
		}
		return contentpane;
	}
	private UITablePane getTablePane(){
		if(tablepane==null){
			tablepane = new UITablePane();
			tablepane.setTable(getTable());
		}
		return tablepane;
	}
	private UITable getTable(){
		if(table==null){
			table = new UITable();
			table.setModel(getTablemodel());
			table.getColumnModel().getColumn(0).setCellEditor(new UIRefCellEditor(getCheckBox()));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("选择");
			column.addElement("序号");
			column.addElement("列名");
			tablemodel.setColumnIdentifiers(column);
			tablemodel.setColEditable(0, true);
			
		}
		return tablemodel;
	}
	private UICheckBox getCheckBox(){
		if(chk==null){
			chk = new UICheckBox();
		}
		return chk;
	}
	private UIButton getBnOK() {
		if (BnOK == null) {
			BnOK = new UIButton();
			BnOK.setBounds(90, 200, 60, 22);
			BnOK.setText("确定");
			BnOK.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					onBnOK();
				}
			});
		}
		return BnOK;
	}
	/**
	 * This method initializes BnCancel	
	 * 	
	 * @return nc.ui.pub.beans.UIButton	
	 */    
	private UIButton getBnCancel() {
		if (BnCancel == null) {
			BnCancel = new UIButton();
			BnCancel.setBounds(210, 200, 60, 22);
			BnCancel.setText("取消");
			BnCancel.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					onBnCancel();
				}
			});
		}
		return BnCancel;
	}
	private void onBnOK(){
		int count = getTable().getRowCount();
		SubjColShowVO vo = new SubjColShowVO();
		String[] xhArray = null;
 		Vector v = new Vector();
		for(int i =0 ;i< count;i++){
			if(getTable().getValueAt(i, 0).equals(new Boolean(true))){
				v.add(m_filevos[i].getXh());
			}
		}
		if(v.size()>0 && v.size()<6){
			xhArray = new String[5];
			for(int i=0;i<v.size();i++){
				xhArray[i] = v.get(i).toString();
			}

			vo.setPk_system(pk_system);
			vo.setDef1(xhArray[0]==null?"":xhArray[0]);
			vo.setDef2(xhArray[1]==null?"":xhArray[1]);
			vo.setDef3(xhArray[2]==null?"":xhArray[2]);
			vo.setDef4(xhArray[3]==null?"":xhArray[3]);
			vo.setDef5(xhArray[4]==null?"":xhArray[4]);
	
		}
		IBankSubjContrast iba = (IBankSubjContrast)NCLocator.getInstance().lookup(IBankSubjContrast.class.getName());
		try {
			if(m_columnvo==null)
				iba.insertSubjColShowVO(vo);
			else
			{
				vo.setPk_subjcolshow(m_columnvo.getPk_subjcolshow());
				iba.updateSubjColShowVO(vo);
			}
				
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		closeOK();
	}
	private void onBnCancel(){
		closeCancel();
	}
	public FileDefineVO[] getFileDefineVOs(){
		return revos;
	}
}
