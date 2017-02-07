package nc.ui.bankpub.vouelement;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IFileDefine;
import nc.itf.bankpub.pub.ISystemType;
import nc.itf.bankpub.pub.IVoucherElement;
import nc.ui.bankpub.dealcode.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bankpub.voucherelement.VoucherElementVO;
import nc.vo.pub.BusinessException;

public class VoucherElementUI extends ToftPanel implements ActionListener{
	
	private String[] strbtn = {"新增","修改","保存","删除","取消","刷新"};
	private ButtonObject btn[] = null;
	private UIPanel paneContent = null;
	private UIPanel paneTop = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private UILabel labsystem = null;
	private UIComboBox cmbsystem= null;
	private UIComboBox cmbfiledefine = null;
	private int state = 0; 
	private int currentRow = 0;
	private VoucherElementVO[] m_vos = null;
	private VoucherElementVO[] m_tempvos = null;
	private int initRow = 0;  // 主界面初始化行数
	private String pk_system = null; 
	
	public String getTitle() {
		// TODO 自动生成方法存根
		return null;
	}
	public VoucherElementUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}

	public void onButtonClicked(ButtonObject bo) {
		// TODO 自动生成方法存根
		try{
			
			String name = bo.getName();
			if(name.equals("新增")){
				onAdd();
				return;
			}
			if(name.equals("修改")){
				
				onModify();
				return;
			}
			if(name.equals("保存")){
				onSave();
				return;
			}
			if(name.equals("删除")){
				onDelete();
				return;
			}
			if(name.equals("取消")){
				onCance();
				return;
			}
			if(name.equals("刷新")){
				onRefresh();
				return;
			}
			
		}catch(Exception ex)
        {
            ex.printStackTrace();
            showErrorMessage("执行"+bo.getName()+"失败："+ex.getMessage());
        }
	}
	private void initialize(){
		this.setSize(495, 306);
		this.add(getContentpane());
		initTable();
		initCrols();
		initDatas();
		onState(0);
	}
	private void initTable(){

	}
	private void initCrols(){
		setButtonName(strbtn);
	}
	private void initDatas(){

    	try {
        	// 初始化系统列下拉表
        	ISystemType is = (ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
        	SystemTypeVO[] systemtypevos = null;
    		try {
    			systemtypevos = is.queryAllSystemTypeVOs();
    		} catch (BusinessException e1) {
    			// TODO 自动生成 catch 块
    			e1.printStackTrace();
    		}
    		getCmbSystem().removeActionListener(this);
    		getCmbSystem().removeAllItems();
        	if(systemtypevos!=null && systemtypevos.length!=0){
        		for(int i=0;i<systemtypevos.length;i++){
        			getCmbSystem().addItem(systemtypevos[i]);
        		}
        	}
        	getCmbSystem().addActionListener(this);
//        	文件列表
        	
        	IFileDefine ifi = (IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
        	FileDefineVO[] filedefines = ifi.queryAllFileDefineVOs();
        	if(filedefines!=null && filedefines.length!=0){
        		for(int i=0;i<filedefines.length;i++){
        			if(filedefines[i].getPk_system().equals(pk_system)){
        				getCmbFileDefine().addItem(filedefines[i]);
        			}
        			
        		}
        	}
        	onRefresh();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	private void setButtonName(String name[]){
		btn = new ButtonObject[name.length];
		for(int i = 0;i<name.length;i++){
			btn[i] = new ButtonObject(name[i],name[i],2);
		}
		setButtons(btn);
		updateButtons();
	}
	
	private void onAdd() throws Exception{
		try{
			tablemodel.addRow(1);
			int row = getTable().getRowCount();
			tablemodel.setRowEditable(row-1,true);
			getTable().setModel(tablemodel);
			state = 1;
			onState(state);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	private void onModify() throws Exception{
		try{
			currentRow = getTable().getSelectedRow();
			int count = getTable().getRowCount();
			if( currentRow < 0  || currentRow >=count){
				showWarningMessage("请选择一行。");
				return;
			}
			tablemodel.setRowEditable(currentRow,true);
			getTable().setModel(tablemodel);
			state = 2;
			onState(state);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	private void onSave() throws Exception{
		try{
			getTable().getCellEditor().stopCellEditing();
		}catch(Exception ex){
		}
		SystemTypeVO systemvo =(SystemTypeVO)getCmbSystem().getSelectedItem();
		IVoucherElement is =(IVoucherElement)NCLocator.getInstance().lookup(IVoucherElement.class.getName());
		if(state==1)//新增
		{

			int row = getTable().getRowCount();
			m_tempvos = new VoucherElementVO[row-initRow];
			for(int i=initRow;i<row;i++){
				m_tempvos[i-initRow] = new VoucherElementVO();
				for(int j=0;j<7;j++){
					if(getTable().getValueAt(i,j) == null
							||	getTable().getValueAt(i,j).toString().trim().equalsIgnoreCase("")){
						if(j==0){
							showWarningMessage("请选择机构对应列！");
							return;
						}
						if(j==1){
							showWarningMessage("请选择币种对应列！");
							return;
						}
						if(j==2 ){
							showWarningMessage("请选择原币金额对应列！");
							return;
						}
						if(j==3){
							showWarningMessage("请选择借贷方向对应列！");
							return;
						}
						if(j==4){
							showWarningMessage("请选择科目编码对应列！");
							return;
						}
						if(j==5){
							showWarningMessage("请选择日期对应列！");
							return;
						}
						//if(j==6){
							//showWarningMessage("请选择部门辅助对应列！");
							//return;
						//}
						if(j==6){
							showWarningMessage("请选择表内外科目标志对应列！");
							return;
						}
					}
					
				}
				FileDefineVO[] tmpVO = new FileDefineVO[8];
				if(getTable().getValueAt(i,0)!=null && getTable().getValueAt(i,0) instanceof FileDefineVO)
				{
					tmpVO[0] = (FileDefineVO)getTable().getValueAt(i,0);
				}
				if(getTable().getValueAt(i,1)!=null && getTable().getValueAt(i,1) instanceof FileDefineVO)
				{
					tmpVO[1] = (FileDefineVO)getTable().getValueAt(i,1);
				}
				if(getTable().getValueAt(i,2)!=null && getTable().getValueAt(i,2) instanceof FileDefineVO)
				{
					tmpVO[2] = (FileDefineVO)getTable().getValueAt(i,2);
				}
				if(getTable().getValueAt(i,3)!=null && getTable().getValueAt(i,3) instanceof FileDefineVO)
				{
					tmpVO[3] = (FileDefineVO)getTable().getValueAt(i,3);
				}
				if(getTable().getValueAt(i,4)!=null && getTable().getValueAt(i,4) instanceof FileDefineVO)
				{
					tmpVO[4] = (FileDefineVO)getTable().getValueAt(i,4);
				}
				if(getTable().getValueAt(i,5)!=null && getTable().getValueAt(i,5) instanceof FileDefineVO)
				{
					tmpVO[5] = (FileDefineVO)getTable().getValueAt(i,5);
				}
				//if(getTable().getValueAt(i,6)!=null && getTable().getValueAt(i,6) instanceof FileDefineVO)
				//{
					//tmpVO[6] = (FileDefineVO)getTable().getValueAt(i,6);
				//}
				if(getTable().getValueAt(i,6)!=null && getTable().getValueAt(i,6) instanceof FileDefineVO)
				{
					tmpVO[6] = (FileDefineVO)getTable().getValueAt(i,6);
				}
				m_tempvos[i-initRow].setPk_system(pk_system);
				m_tempvos[i-initRow].setPk_corpxh(tmpVO[0].getXh().toString());
				m_tempvos[i-initRow].setPk_currtypexh(tmpVO[1].getXh().toString());
				m_tempvos[i-initRow].setMoney(tmpVO[2].getXh().toString());
				m_tempvos[i-initRow].setDcorient(tmpVO[3].getXh().toString());
				m_tempvos[i-initRow].setSubjcode(tmpVO[4].getXh().toString());
				m_tempvos[i-initRow].setDatecol(tmpVO[5].getXh().toString());
				//m_tempvos[i-initRow].setDeptass(tmpVO[6].getXh().toString());
				m_tempvos[i-initRow].setInoutflag(tmpVO[6].getXh().toString());

			}
			try {
				is.insertArrayVoucherElementVO(m_tempvos);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		if(state==2)//修改
		{

			for(int j=0;j<8;j++){
				if(getTable().getValueAt(currentRow,j)==null 
						|| 	getTable().getValueAt(currentRow,j).toString().trim().equalsIgnoreCase("")){
					if(j==0){
						showWarningMessage("请选择机构对应列！");
						return;
					}
					if(j==1){
						showWarningMessage("请选择币种对应列！");
						return;
					}
					if(j==2 ){
						showWarningMessage("请选择原币金额对应列！");
						return;
					}
					if(j==3){
						showWarningMessage("请选择借贷方向对应列！");
						return;
					}
					if(j==4){
						showWarningMessage("请选择科目编码对应列！");
						return;
					}
					if(j==5){
						showWarningMessage("请选择日期对应列！");
						return;
					}
//					if(j==6){
//						showWarningMessage("请选择部门辅助对应列！");
//						return;
//					}
					if(j==6){
						showWarningMessage("请选择表内外科目标志对应列！");
						return;
					}
				}
				
			}
			FileDefineVO[] tmpVO = new FileDefineVO[8];
			if(getTable().getValueAt(currentRow,0)!=null && getTable().getValueAt(currentRow,0) instanceof FileDefineVO)
			{
				tmpVO[0] = (FileDefineVO)getTable().getValueAt(currentRow,0);
			}
			if(getTable().getValueAt(currentRow,1)!=null && getTable().getValueAt(currentRow,1) instanceof FileDefineVO)
			{
				tmpVO[1] = (FileDefineVO)getTable().getValueAt(currentRow,1);
			}
			if(getTable().getValueAt(currentRow,2)!=null && getTable().getValueAt(currentRow,2) instanceof FileDefineVO)
			{
				tmpVO[2] = (FileDefineVO)getTable().getValueAt(currentRow,2);
			}
			if(getTable().getValueAt(currentRow,3)!=null && getTable().getValueAt(currentRow,3) instanceof FileDefineVO)
			{
				tmpVO[3] = (FileDefineVO)getTable().getValueAt(currentRow,3);
			}
			if(getTable().getValueAt(currentRow,4)!=null && getTable().getValueAt(currentRow,4) instanceof FileDefineVO)
			{
				tmpVO[4] = (FileDefineVO)getTable().getValueAt(currentRow,4);
			}
			if(getTable().getValueAt(currentRow,5)!=null && getTable().getValueAt(currentRow,5) instanceof FileDefineVO)
			{
				tmpVO[5] = (FileDefineVO)getTable().getValueAt(currentRow,5);
			}
//			if(getTable().getValueAt(currentRow,6)!=null && getTable().getValueAt(currentRow,6) instanceof FileDefineVO)
//			{
//				//tmpVO[6] = (FileDefineVO)getTable().getValueAt(currentRow,6);
//			}
			if(getTable().getValueAt(currentRow,6)!=null && getTable().getValueAt(currentRow,6) instanceof FileDefineVO)
			{
				tmpVO[6] = (FileDefineVO)getTable().getValueAt(currentRow,6);
			}
			VoucherElementVO vo = new VoucherElementVO();
			vo.setPk_voucherelement(m_vos[currentRow].getPk_voucherelement());
			vo.setPk_system(pk_system);
			vo.setPk_corpxh(tmpVO[0].getXh().toString());
			vo.setPk_currtypexh(tmpVO[1].getXh().toString());
			vo.setMoney(tmpVO[2].getXh().toString());
			vo.setDcorient(tmpVO[3].getXh().toString());
			vo.setSubjcode(tmpVO[4].getXh().toString());
			vo.setDatecol(tmpVO[5].getXh().toString());
			//vo.setDeptass(tmpVO[6].getXh().toString());
			vo.setInoutflag(tmpVO[6].getXh().toString());
			try {
				is.updateVoucherElementVOByVO(vo);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		onRefresh();
		onState(0);
		getTable().getSelectionModel().setSelectionInterval(-1,-1);
		
	}
	private void onDelete() throws Exception{
		int row = getTable().getSelectedRow();
		int count = getTable().getRowCount();
		if( row < 0 || row>=count){
			showWarningMessage("请选择一行。");
			return;
		}
        int ire = showYesNoMessage("是否删除当前记录,请确认？");
        if(ire != UIDialog.ID_YES)
            return;
		try {
			
			IVoucherElement iv =(IVoucherElement)NCLocator.getInstance().lookup(IVoucherElement.class.getName());
			iv.deleteVoucherElementVOByVO(m_vos[row]);
			onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	private void onCance() throws Exception{
		try{
			onRefresh();
			onState(0);
			getTable().getSelectionModel().setSelectionInterval(-1,-1);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	private void onRefresh() throws Exception{

    	SystemTypeVO systemvo = (SystemTypeVO)getCmbSystem().getSelectedItem();
    	pk_system = systemvo.getPk_system();
    	//初始化列定义下来框
    	FileDefineVO[] fileallvos = null;
    	FileDefineVO[] filevos = null;
		IFileDefine ifi =(IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
		try {
			fileallvos = ifi.queryAllFileDefineVOs();
		} catch (BusinessException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		Hashtable hash = new Hashtable();
		Vector vvos = new Vector();
		if(fileallvos!=null && fileallvos.length!=0){
			for(int i=0;i<fileallvos.length;i++){
				if(pk_system.trim().equals(fileallvos[i].getPk_system().trim())){
					vvos.addElement(fileallvos[i]);
					hash.put(fileallvos[i].getXh().toString(), fileallvos[i]);
				}
			}
		}
		if(vvos.size()>0){
			filevos = new FileDefineVO[vvos.size()];
			vvos.copyInto(filevos);
		}
		getCmbFileDefine().removeAllItems();
		if(filevos!=null && filevos.length!=0){
			for(int i=0;i<filevos.length;i++){
				getCmbFileDefine().addItem(filevos[i]);
			}
		}
    	//初始化表
    	VoucherElementVO[] m_allvos = null;
    	IVoucherElement iv =(IVoucherElement)NCLocator.getInstance().lookup(IVoucherElement.class.getName());
		try {
			m_allvos = iv.queryAllVoucherElementVOs();
		} catch (BusinessException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		// 筛选当前系统列定义
//		SystemTypeVO vvo =(SystemTypeVO)getCmbSystem().getSelectedItem();
//		String pk_system = vvo.getPk_system();
		Vector vvvos = new Vector();
		if(m_allvos!=null && m_allvos.length!=0){
			for(int i=0;i<m_allvos.length;i++){
				if(pk_system.trim().equals(m_allvos[i].getPk_system().trim())){
					vvvos.addElement(m_allvos[i]);
				}
			}
		}
		Object[][] datas = null;
		if(vvvos.size()>0){
			m_vos = new VoucherElementVO[vvvos.size()];
			vvvos.copyInto(m_vos);
			initRow = m_vos.length;
			datas = new Object[m_vos.length][8];
			for(int i=0;i<m_vos.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = hash.get(m_vos[i].getPk_corpxh().trim());
				datas[i][1] = new Object();
				datas[i][1] = hash.get(m_vos[i].getPk_currtypexh().trim());
				datas[i][2] = new Object();
				datas[i][2] = hash.get(m_vos[i].getMoney().trim());
				datas[i][3] = new Object();
				datas[i][3] = hash.get(m_vos[i].getDcorient().trim());
				datas[i][4] = new Object();
				datas[i][4] = hash.get(m_vos[i].getSubjcode().trim());
				datas[i][5] = new Object();
				datas[i][5] = hash.get(m_vos[i].getDatecol().trim());
				//datas[i][6] = new Object();
				//datas[i][6] = hash.get(m_vos[i].getDeptass().trim());
				datas[i][6] = new Object();
				datas[i][6] = hash.get(m_vos[i].getInoutflag().trim());
			}
			getTablemodel().setDataVector(datas);
		}else{
			initRow = 0;
			datas = new Object[0][8];
			getTablemodel().setDataVector(datas);	
			m_vos = null;
		}

	}
	private void onState(int state){
		boolean btnEnable[] = new boolean[]{true,true,false,true,false,true};
		boolean btnEnableadd[] = new boolean[]{true,false,true,false,true,false};
		if(state==0) // 浏览
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(btnEnable[i]);
			updateButtons();
		}
		if(state==1) // 新增
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(btnEnableadd[i]);
			updateButtons();
		}
		if(state==2) //修改
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(!btnEnable[i]);
			updateButtons();
		}
	}
	public UIPanel getContentpane(){
		if(paneContent == null){
			paneContent = new UIPanel();
			paneContent.setLayout(new BorderLayout());
			paneContent.add(getPaneTop(),java.awt.BorderLayout.NORTH);
			paneContent.add(getTablepane(),java.awt.BorderLayout.CENTER);
			
		}
		return paneContent;
	}
	public UIPanel getPaneTop() {
		if(paneTop == null){
			paneTop = new UIPanel();
			paneTop.setLayout(null);
			paneTop.setPreferredSize(new java.awt.Dimension(1,35));
			labsystem = new UILabel();
			labsystem.setText("系统");
			labsystem.setBounds(5, 6, 40, 22);
			paneTop.add(labsystem);
			paneTop.add(getCmbSystem());

		}
		return paneTop; 
	}
	public UIComboBox getCmbSystem(){
		if(cmbsystem == null){
			cmbsystem = new UIComboBox();
			cmbsystem.setBounds(53, 6, 120, 20);
		}
		return cmbsystem;
	}
	public UIComboBox getCmbFileDefine(){
		if(cmbfiledefine == null){
			cmbfiledefine = new UIComboBox();
		}
		return cmbfiledefine;
	}
	public UITablePane getTablepane() {
		if(tablepane == null){
			tablepane = new UITablePane();
			tablepane.setName("tablepane");
			tablepane.setTable(getTable());
		}
		return tablepane;
	}
	public UITable getTable() {
		if(table == null){
			table = new UITable();
			table.setName("table");
			table.setModel(getTablemodel());
			table.getColumnModel().getColumn(0).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(1).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(2).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(3).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(4).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(5).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			//table.getColumnModel().getColumn(6).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.getColumnModel().getColumn(6).setCellEditor(new UIRefCellEditor(getCmbFileDefine()));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return table;
	}
	public JKTableModel getTablemodel() {
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("机构");
			column.addElement("币种");
			column.addElement("原币金额");
			column.addElement("借贷方向");
			column.addElement("科目");
			column.addElement("日期");
			//column.addElement("部门辅助核算");
			column.addElement("表内外科目标志");
			tablemodel.setColumnIdentifiers(column);
		}
		return tablemodel;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == getCmbSystem()){
            try {
            	onRefresh();
            } catch (Exception e1) {

                e1.printStackTrace();
            }
			
			
		}
		
	}
}
