package nc.ui.bankpub.respondcode;

import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IRespondCode;
import nc.ui.ar.printadapter.PrintManager;
import nc.ui.bankpub.dealcode.JKTableModel;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.vo.bankpub.respondcode.RespondCodeVO;
import nc.vo.pub.BusinessException;

public class RespondCodeUI extends ToftPanel {

	private String[] strbtn = {"����","�޸�","����","ɾ��","ȡ��","ˢ��","��ӡ"}; 
	private ButtonObject btn[] = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private int state = 0;
	private int currentRow =0;
	private int initRow = 0;
	private RespondCodeVO[] m_vos = null;
	private RespondCodeVO[] m_tempvos = null;
	@Override
	public String getTitle() {
		// TODO �Զ����ɷ������
		return "��Ӧ�붨��";
	}
	public RespondCodeUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onButtonClicked(ButtonObject bo) {
		// TODO �Զ����ɷ������
		try{
			
			String name = bo.getName();
			if(name.equals("����")){
				onAdd();
				return;
			}
			if(name.equals("�޸�")){
				
				onModify();
				return;
			}
			if(name.equals("����")){
				onSave();
				return;
			}
			if(name.equals("ɾ��")){
				onDelete();
				return;
			}
			if(name.equals("ȡ��")){
				onCance();
				return;
			}
			if(name.equals("ˢ��")){
				onRefresh();
				return;
			}
			if(name.equals("��ӡ")){
				onPrint();
				
			}
			
		}catch(Exception ex)
        {
            ex.printStackTrace();
            showErrorMessage("ִ��"+bo.getName()+"ʧ�ܣ�"+ex.getMessage());
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
		boolean btnEnable[] = new boolean[]{true,true,false,true,false,true,true};
		boolean btnEnableadd[] = new boolean[]{true,false,true,false,true,false,false};
		if(state==0) // ���
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(btnEnable[i]);
			updateButtons();
		}
		if(state==1) // ����
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(btnEnableadd[i]);
			updateButtons();
		}
		if(state==2) //�޸�
		{
			for(int i =0;i<btn.length;i++)
				btn[i].setEnabled(!btnEnable[i]);
			updateButtons();
		}
	}
	private void onAdd() throws Exception{
		try{
			getTablemodel().addRow(1);
			int row = getTable().getRowCount();
			getTablemodel().setRowEditable(row-1,true);
			getTable().setModel(getTablemodel());
			getTable().setValueAt(new Integer(row), row-1, 0);
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
				showWarningMessage("��ѡ��һ�С�");
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
	private void onSave(){
		if(getTable().getCellEditor()!=null)
			getTable().getCellEditor().stopCellEditing();
		Hashtable hashbm = new Hashtable();
		if(m_vos!=null && m_vos.length!=0){
			for(int i=0;i<initRow;i++){
				hashbm.put(m_vos[i].getRespondcode().trim(),"respondcode");

			}	
		}
		if(state==1)//����
		{
			int row = getTable().getRowCount();
			m_tempvos = new RespondCodeVO[row-initRow];
			for(int i=initRow;i<row;i++){
				for(int j=0;j<4;j++){
					if(getTable().getValueAt(i,j) == null
							||	getTable().getValueAt(i,j).toString().trim().equalsIgnoreCase("")){
						
						if(j==1){
							showWarningMessage("����д��Ӧ�룡");
							return;
						}
						if(j==2){
							showWarningMessage("����д��ʾ��Ϣ��");
							return;
						}
						if(j==3){
							showWarningMessage("����д˵����");
							return;
						}
						
						
					}
					
					
				}
				m_tempvos[i-initRow] = new RespondCodeVO();
				m_tempvos[i-initRow].setRespondindex(getTable().getValueAt(i,0).toString().trim());
				m_tempvos[i-initRow].setRespondcode(getTable().getValueAt(i,1).toString().trim());
				m_tempvos[i-initRow].setHintinfo(getTable().getValueAt(i,2).toString().trim());
				m_tempvos[i-initRow].setMemo(getTable().getValueAt(i,3).toString().trim());
				
				if(hashbm.containsKey(m_tempvos[i-initRow].getRespondcode().trim())){
					showWarningMessage("��Ӧ�벻���ظ���");
					return;
				}
				hashbm.put(m_tempvos[i-initRow].getRespondcode().trim(),"respondcode");
			}
			try {
				IRespondCode i =(IRespondCode)NCLocator.getInstance().lookup(IRespondCode.class.getName());
				i.insertArrayRespondCodeVO(m_tempvos);
			} catch (BusinessException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			
		}
		if(state==2)
		{
			hashbm.remove(m_vos[currentRow].getRespondcode().trim());
			
			
			for(int j=0;j<4;j++){
				if(getTable().getValueAt(currentRow,j)==null 
						|| 	getTable().getValueAt(currentRow,j).toString().trim().equalsIgnoreCase(""))
				{
					
					if(j==1){
						showWarningMessage("����д��Ӧ�룡");
						return;
					}
					if(j==2){
						showWarningMessage("����д��ʾ��Ϣ��");
						return;
					}
					if(j==3){
						showWarningMessage("����д˵����");
						return;
					}
				}
				
			}
			String respondcode = getTable().getValueAt(currentRow,1).toString().trim();
			if(hashbm.containsKey(respondcode.trim())){
				showWarningMessage("��Ӧ�벻���ظ���");
				return;
			}
			
			
			RespondCodeVO vo = new RespondCodeVO();
			vo.setPk_respondcode(m_vos[currentRow].getPk_respondcode());
			vo.setRespondindex(getTable().getValueAt(currentRow,0)==null?"":getTable().getValueAt(currentRow,0).toString().trim());
			vo.setRespondcode(getTable().getValueAt(currentRow,1)==null?"":getTable().getValueAt(currentRow,1).toString().trim());
			vo.setHintinfo(getTable().getValueAt(currentRow,2)==null?"":getTable().getValueAt(currentRow,2).toString().trim());
			vo.setMemo(getTable().getValueAt(currentRow,3)==null?"":getTable().getValueAt(currentRow,3).toString().trim());
			try {
				IRespondCode i =(IRespondCode)NCLocator.getInstance().lookup(IRespondCode.class.getName());
				i.updateRespondCodeVOByVO(vo);
			} catch (BusinessException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
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
			showWarningMessage("��ѡ��һ�С�");
			return;
		}
        int ire = showYesNoMessage("�Ƿ�ɾ����ǰ��¼,��ȷ�ϣ�");
        if(ire != UIDialog.ID_YES)
            return;
		try {
			IRespondCode i =(IRespondCode)NCLocator.getInstance().lookup(IRespondCode.class.getName());
			i.deleteRespondCodeVOByVO(m_vos[row]);
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
	private void onRefresh(){
		try {
			IRespondCode i =(IRespondCode)NCLocator.getInstance().lookup(IRespondCode.class.getName());
			m_vos = i.queryAllRespondCodeVOs();
		} catch (BusinessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		Object[][] datas = null;
		if(m_vos!=null && m_vos.length!=0){
			initRow = m_vos.length;
			datas = new Object[m_vos.length][4];
			for(int i=0;i<m_vos.length;i++ ){
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].getRespondindex();
				datas[i][1] = new Object();
				datas[i][1] = m_vos[i].getRespondcode();
				datas[i][2] = new Object();
				datas[i][2] = m_vos[i].getHintinfo();
				datas[i][3] = new Object();
				datas[i][3] = m_vos[i].getMemo();
			}
			
		}else{
			initRow = 0;
			datas = new Object[0][4];
			
		}
		tablemodel.setDataVector(datas);
	}
	private void onPrint() throws Exception{
		
		
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
			column.addElement("���"); //1
			column.addElement("��Ӧ��"); //2
			column.addElement("��Ӧ���Ӧ��ʾ��Ϣ"); //3
			column.addElement("˵��"); //4
			tablemodel.setColumnIdentifiers(column);
			tablemodel.setColNotEditable(new int[]{0});
			
		}
		return tablemodel;
	}
}
