package nc.ui.bankpub.vouchertype;


import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IVouType;
import nc.itf.uap.bd.vouchertype.IVoucherTypeQry;
import nc.ui.ar.printadapter.PrintManager;
import nc.ui.bankpub.dealcode.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.vo.bankpub.vouchertype.VouTypeVO;
import nc.vo.bd.b18.VouchertypeVO;
import nc.vo.pub.BusinessException;

public class VouTypeUI extends ToftPanel {

	
	private String[] strbtn = {"修改","保存","取消","刷新","打印"}; 
	private ButtonObject btn[] = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private UIComboBox cmbTrans = null;
	private int state = 0; 
	private int currentRow = 0;
	private VouTypeVO[] m_vos = null;
	
	
	@Override
	public String getTitle() {
		// TODO 自动生成方法存根
		return "NC系统联动核心凭证类别定义";
	}
	public VouTypeUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onButtonClicked(ButtonObject bo) {
		// TODO 自动生成方法存根
		try{
			
			String name = bo.getName();

			if(name.equals("修改")){
				
				onModify();
				return;
			}
			if(name.equals("保存")){
				onSave();
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
			if(name.equals("打印")){
				onPrint();
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
		this.add(getContentPane());
		initTable();
		initCtrls();
		initDatas();
		
	}
	private void initTable(){
		
		
        int[] wids = new int[getTable().getModel().getColumnCount()];
        for(int i=0;i<getTable().getModel().getColumnCount();i++){
        	wids[i] = 82;
        }
        getTable().setColumnWidth(wids);
	}
	private void initCtrls(){
		setButtonName(strbtn);
		
	}
	private void initDatas(){
		
		IVouType id = (IVouType)NCLocator.getInstance().lookup(IVouType.class.getName());
		try {
			m_vos = id.queryAllVouTypeVOs();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		IVoucherTypeQry bdvoutype = (IVoucherTypeQry)NCLocator.getInstance().lookup(IVoucherTypeQry.class.getName());
		VouchertypeVO[] typevos = null;
		VouchertypeVO[] addtypevos = null;
		try {
			typevos= bdvoutype.queryVoucherTypeVOs(null);
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(m_vos==null || m_vos.length==0){
			if(typevos!=null && typevos.length!=0){
				m_vos = new VouTypeVO[typevos.length];
				for(int i=0;i<typevos.length;i++){
					m_vos[i] = new VouTypeVO();
					m_vos[i].setPk_vouchertype(typevos[i].getPk_vouchertype());
					m_vos[i].setIstrans("N");
					
				}
				try {
					id.insertArrayVouTypeVO(m_vos);
				} catch (BusinessException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
		else
		{
			Hashtable hash = new Hashtable();
			Vector v = new Vector();
			for(int i=0;i<m_vos.length;i++){
				hash.put(m_vos[i].getPk_vouchertype().trim(), m_vos[i].getPk_vouchertype().trim());
	
			}
			for(int i=0;i<typevos.length;i++){
				
				if(!hash.containsKey(typevos[i].getPk_vouchertype().trim())){
					v.addElement(typevos[i]);
				}
			}
			if(v.size()>0){
				addtypevos = new VouchertypeVO[v.size()];
				v.copyInto(addtypevos);
				VouTypeVO[] tempvos = new VouTypeVO[v.size()];
				for(int i=0;i<addtypevos.length;i++){
					tempvos[i] = new VouTypeVO();
					tempvos[i].setPk_vouchertype(addtypevos[i].getPk_vouchertype());
					tempvos[i].setIstrans("N");
				}
				try {
					id.insertArrayVouTypeVO(tempvos);
				} catch (BusinessException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
			
			
			
		}

		onRefresh();
		onState(0);
		
	}
	private void setButtonName(String name[]){
		btn = new ButtonObject[name.length];
		for(int i = 0;i<name.length;i++){
			btn[i] = new ButtonObject(name[i],name[i],2);
		}
		setButtons(btn);
		updateButtons();
	}
	private void onState(int state){
		boolean btnEnable[] = new boolean[]{true,false,false,true,true};
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
	private void onModify() throws Exception{
		try{
			currentRow = getTable().getSelectedRow();
			int count = getTable().getRowCount();
			if( currentRow < 0  || currentRow >=count){
				showWarningMessage("请选择一行。");
				return;
			}
			getTablemodel().setRowEditable(currentRow,true);
			getTable().setModel(getTablemodel());
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
		int currentrow = getTable().getSelectedRow();
		if(getTable().getValueAt(currentrow, 2)==null){
			showWarningMessage("请选择是否联动核心.");
			return;
			
		}
		if(getTable().getValueAt(currentrow, 2).toString().equals("是")){
			m_vos[currentrow].setIstrans("Y");
		}else{
			m_vos[currentrow].setIstrans("N");
		}
		IVouType id = (IVouType)NCLocator.getInstance().lookup(IVouType.class.getName());
		try {
			id.updateVouTypeVOByVO(m_vos[currentrow]);
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		onState(0);
		
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
	private void onRefresh(){
		
		IVouType iv = (IVouType)NCLocator.getInstance().lookup(IVouType.class.getName());
		try {
			m_vos = iv.queryAllVouTypeVOs();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Object[][] datas = null;
		if(m_vos!=null && m_vos.length!=0){
			datas = new Object[m_vos.length][3];
			for(int i=0;i<datas.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].forshort;
				datas[i][1] = new Object();
				datas[i][1] = m_vos[i].vouchtypename;
				datas[i][2] = new Object();
				datas[i][2] = m_vos[i].getIstrans().equals("Y")?"是":"否";
			}
			
			
		}else{
			
			datas = new Object[0][3];
		}
		tablemodel.setDataVector(datas);
	}
	private void onPrint(){
		
    	PrintDirectEntry print = PrintManager.getDirectPrinter(getTable());
      	print.setTitle(getTitle());
      	print.preview();
	}
	private UIPanel getContentPane(){
		
		if(paneContent == null){
			paneContent = new UIPanel();
			paneContent.setLayout(new BorderLayout());
			paneContent.add(getTablePane(),java.awt.BorderLayout.CENTER);
		}
		return paneContent;
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
			table.getColumnModel().getColumn(2).setCellEditor(new UIRefCellEditor(getCmbTrans()));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("凭证类别简称"); //1
			column.addElement("凭证类别名称"); //2
			column.addElement("是否联动核心"); //3
			tablemodel.setColumnIdentifiers(column);
			
		}
		return tablemodel;
	}
	private UIComboBox getCmbTrans(){
		
		if(cmbTrans==null){
			
			cmbTrans = new UIComboBox();
			cmbTrans.addItem("是");
			cmbTrans.addItem("否");
			
		}
		return cmbTrans;
	}
}
