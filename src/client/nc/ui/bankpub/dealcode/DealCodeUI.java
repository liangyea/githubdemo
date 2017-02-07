package nc.ui.bankpub.dealcode;


import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IDealCode;
import nc.ui.ar.printadapter.PrintManager;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.vo.bankpub.dealcode.DealCodeVO;
import nc.vo.pub.BusinessException;

public class DealCodeUI extends ToftPanel {

	
	private String[] strbtn = {"保存","编辑","刷新","取消","打印"};
	private ButtonObject btn[] = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private DealCodeVO[] m_vos = null;
	private int state = 0;
	

	public String getTitle() {
		// TODO 自动生成方法存根
		return "接口交易码定义";
	}

	public void onButtonClicked(ButtonObject bo) {
		// TODO 自动生成方法存根
		try{
			
			String name = bo.getName();
			if(name.equals("保存")){
				onSave();
				return;
			}
			if(name.equals("编辑")){
				
				onModify();
				return;
			}
			if(name.equals("刷新")){
				onRefresh();
				return;
			}
			if(name.equals("取消")){
				onCance();
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
	
	public DealCodeUI(){
		super();
		initialize();
	}
	
	private void initialize() {
		
		
		this.setSize(495, 306);
		this.add(getContentPane());
		initTable();
		initCtrls();
		try {
			initDatas();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
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
	private void setButtonName(String name[]){
		btn = new ButtonObject[name.length];
		for(int i = 0;i<name.length;i++){
			btn[i] = new ButtonObject(name[i],name[i],2);
		}
		setButtons(btn);
		updateButtons();
	}
	private void onState(int state){
		boolean btnEnable[] = new boolean[]{false,true,true,false,true};
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
	private void initDatas() throws Exception {

		 try {
			onRefresh() ;
			onState(0);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	private void onRefresh() throws Exception{
		
		IDealCode id = (IDealCode)NCLocator.getInstance().lookup(IDealCode.class.getName());
		try {
			m_vos = id.queryDealCode();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
		Object[][] datas = null;
		if(m_vos!=null && m_vos.length!=0){
			datas = new Object[m_vos.length][3];
			for(int i=0;i<datas.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].getDealindex();
				datas[i][1] = new Object();
				datas[i][1] = m_vos[i].getDealcontent();
				datas[i][2] = new Object();
				datas[i][2] = m_vos[i].getDealcode();
			}
			tablemodel.setDataVector(datas);
			
		}else{
			
			throw new Exception("请导入交易码表");
		}
	}
	
	private void onModify() throws Exception{
		try{
			int currentRow = getTable().getSelectedRow();
			int count = getTable().getRowCount();
			if( currentRow < 0  || currentRow >=count){
				showWarningMessage("请选择一行。");
				return;
			}
			getTablemodel().setRowEditable(currentRow,true);
			getTablemodel().setColNotEditable(new int[]{0,1});
			getTablemodel().setColEditable(0,false);
			getTablemodel().setColEditable(1,false);
			getTable().setModel(getTablemodel());
			state = 2;
			onState(state);
		}catch(Exception e){
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
	private void onSave() throws Exception{
		
		try{
			getTable().getCellEditor().stopCellEditing();
		}catch(Exception ex){
		}
		int currentrow = getTable().getSelectedRow();
		if(getTable().getValueAt(currentrow, 2)==null){
			showWarningMessage("请填写交易码");
			return;
			
		}
		m_vos[currentrow].setDealcode(getTable().getValueAt(currentrow, 2).toString());
		IDealCode id = (IDealCode)NCLocator.getInstance().lookup(IDealCode.class.getName());
		try {
			id.updateDealCode(m_vos[currentrow]);
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		onRefresh() ;
		onState(0);
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
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("序号"); //1
			column.addElement("功能"); //2
			column.addElement("交易码"); //3
			tablemodel.setColumnIdentifiers(column);
			tablemodel.setColEditable(0,false);
			tablemodel.setColEditable(1,false);
		}
		return tablemodel;
	}
	
	
}
