package nc.ui.bankpub.filedefine;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IFileDefine;
import nc.itf.bankpub.pub.ISystemType;
import nc.ui.bankpub.system.JKTableModel;
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
import nc.vo.pub.BusinessException;

public class FileDefineUI extends ToftPanel implements ActionListener{

	private String[] strbtn = {"����","�޸�","����","ɾ��","ȡ��","ˢ��"}; 
	private ButtonObject btn[] = null;
	private UIPanel paneTop = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private UILabel labsystem = null;
	private JKTableModel tablemodel = null;
	private UIComboBox cmbisfix = null;
	private UIComboBox cmbtype = null;
	private UIComboBox cmbsystem= null;
	private int state = 0;  //����ƾ֤��������or�޸�
	private int currentRow = 0;
	private int initRow = 0;  // �������ʼ������
	private FileDefineVO[] m_allvos = null;
	private FileDefineVO[] m_vos = null; //��ǰϵͳ������ʾ
	private FileDefineVO[] m_tempvos = null;
	
	public String getTitle() {
		// TODO �Զ����ɷ������
		return null;
	}
	public FileDefineUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}
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
        	wids[i] = 135;
        }
        getTable().setColumnWidth(wids);
	}
	private void initCtrls(){
		setButtonName(strbtn);
		this.setTitleText("�ļ���ʽ�ж���");
		
	}
	private void initDatas(){

		try {
	    	// ��ʼ��ϵͳ
	    	ISystemType isy = (ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
	    	SystemTypeVO[] systemtypevos = null;
			try {
				systemtypevos = isy.queryAllSystemTypeVOs();
			} catch (BusinessException e1) {
				// TODO �Զ����� catch ��
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
	    	
	    	
			onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private void onAdd() throws Exception {
		
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
	private void onModify() throws Exception {
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
	private void onSave() throws Exception{
		if(getTable().getCellEditor()!=null)
			getTable().getCellEditor().stopCellEditing();
		SystemTypeVO systemvo =(SystemTypeVO)getCmbSystem().getSelectedItem();
		IFileDefine is =(IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
		if(state==1)//����
		{

			int row = getTable().getRowCount();
			m_tempvos = new FileDefineVO[row-initRow];
			for(int i=initRow;i<row;i++){
				m_tempvos[i-initRow] = new FileDefineVO();
				for(int j=1;j<4;j++){
					if(getTable().getValueAt(i,j) == null
							||	getTable().getValueAt(i,j).toString().trim().equalsIgnoreCase("")){
						if(j==1){
							showWarningMessage("����д�����ƣ�");
							return;
						}
						if(j==2){
							showWarningMessage("��ѡ���������ͣ�");
							return;
						}
						if(j==3 && systemvo.getIsfixlen().equals("Y")){
							showWarningMessage("�붨���г��ȣ�");
							return;
						}

					}
					if(j==3 && systemvo.getIsfixlen().equals("Y")){
						Integer colvalue = new Integer(getTable().getValueAt(currentRow,j).toString());
						if(colvalue.intValue()==0){
							showWarningMessage("�붨���г��ȣ�");
							return;
						}
					}
				}
				m_tempvos[i-initRow].setPk_system(systemvo.getPk_system());
				m_tempvos[i-initRow].setXh(Integer.valueOf(getTable().getValueAt(i,0).toString().trim()));
				m_tempvos[i-initRow].setColumnname(getTable().getValueAt(i,1).toString().trim());
				m_tempvos[i-initRow].setDatatype(getTable().getValueAt(i,2).toString().trim().equals("�ַ�")?"0":"1");
				m_tempvos[i-initRow].setLength(getTable().getValueAt(i,3).toString().trim().equals("")?Integer.valueOf("0"):Integer.valueOf(getTable().getValueAt(i,3).toString().trim()));
			}
			try {
				is.insertArrayFileDefineVO(m_tempvos);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		if(state==2)//�޸�
		{

			for(int j=1;j<4;j++){
				if(getTable().getValueAt(currentRow,j)==null 
						|| 	getTable().getValueAt(currentRow,j).toString().trim().equalsIgnoreCase("")){
					if(j==1){
						showWarningMessage("����д�����ƣ�");
						return;
					}
					if(j==2){
						showWarningMessage("��ѡ���������ͣ�");
						return;
					}
					if(j==3 && systemvo.getIsfixlen().equals("Y")){
						showWarningMessage("�붨���г��ȣ�");
						return;
					}

				}
				if(j==3 && systemvo.getIsfixlen().equals("Y")){
					Integer colvalue = new Integer(getTable().getValueAt(currentRow,j).toString());
					if(colvalue.intValue()==0){
						showWarningMessage("�붨���г��ȣ�");
						return;
					}
				}
			}

			FileDefineVO vo = new FileDefineVO();
			vo.setPk_system(systemvo.getPk_system());
			vo.setPk_filedefine(m_vos[currentRow].getPk_filedefine());
			vo.setXh(Integer.valueOf(getTable().getValueAt(currentRow,0).toString().trim()));
			vo.setColumnname(getTable().getValueAt(currentRow,1).toString().trim());
			vo.setDatatype(getTable().getValueAt(currentRow,2).toString().trim().equals("�ַ�")?"0":"1");
			vo.setLength(getTable().getValueAt(currentRow,3).toString().trim().equals("")?Integer.valueOf("0"):Integer.valueOf(getTable().getValueAt(currentRow,3).toString().trim()));
			try {
				is.updateFileDefineVOByVO(vo);
				
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
	private void onDelete() throws Exception {
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
			IFileDefine i =(IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
			i.deleteFileDefineVOByVO(m_vos[row]);
			onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	private void onCance() throws Exception {
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
		

    	SystemTypeVO svo = (SystemTypeVO)getCmbSystem().getSelectedItem();
		String pk_system = svo.getPk_system();
		
		IFileDefine is =(IFileDefine)NCLocator.getInstance().lookup(IFileDefine.class.getName());
		try {
			m_allvos = is.queryAllFileDefineVOs();
		} catch (BusinessException e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		// ɸѡ��ǰϵͳ�ж���
		Vector vvos = new Vector();
		if(m_allvos!=null && m_allvos.length!=0){
			for(int i=0;i<m_allvos.length;i++){
				if(pk_system.trim().equals(m_allvos[i].getPk_system().trim())){
					vvos.addElement(m_allvos[i]);
				}
			}
		}
		Object[][] datas = null;
		if(vvos.size()>0){
			m_vos = new FileDefineVO[vvos.size()];
			vvos.copyInto(m_vos);
			
			initRow = m_vos.length;
			datas = new Object[m_vos.length][10];
			for(int i=0;i<m_vos.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].getXh();
				datas[i][1] = new Object();
				datas[i][1] = m_vos[i].getColumnname().trim();
				datas[i][2] = new Object();
				datas[i][2] = m_vos[i].getDatatype().trim().equals("0")?"�ַ�":"��ֵ";
				datas[i][3] = new Object();
				datas[i][3] = m_vos[i].getLength();

			}
			getTablemodel().setDataVector(datas);
			
		}else{
			initRow = 0;
			datas = new Object[0][6];
			getTablemodel().setDataVector(datas);
			m_vos = null;
		}
		
//		if(m_vos!=null && m_vos.length!=0){
//			initRow = m_vos.length;
//			datas = new Object[m_vos.length][10];
//			for(int i=0;i<m_vos.length;i++){
//				datas[i][0] = new Object();
//				datas[i][0] = m_vos[i].getXh();
//				datas[i][1] = new Object();
//				datas[i][1] = m_vos[i].getColumnname().trim();
//				datas[i][2] = new Object();
//				datas[i][2] = m_vos[i].getDatatype().trim().equals("0")?"�ַ�":"��ֵ";
//				datas[i][3] = new Object();
//				datas[i][3] = m_vos[i].getLength();
//
//			}
//			getTablemodel().setDataVector(datas);
//			m_vos = null;
//		}else{
//			initRow = 0;
//			datas = new Object[0][6];
//			getTablemodel().setDataVector(datas);
//			m_vos = null;
//		}
	}
	private void onState(int state){
		boolean btnEnable[] = new boolean[]{true,true,false,true,false,true};
		boolean btnEnableadd[] = new boolean[]{true,false,true,false,true,false};
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

	private UIPanel getContentPane(){
		
		if(paneContent == null){
			paneContent = new UIPanel();
			paneContent.setLayout(new BorderLayout());
			paneContent.add(getPaneTop(),java.awt.BorderLayout.NORTH);
			paneContent.add(getTablePane(),java.awt.BorderLayout.CENTER);
		}
		return paneContent;
	}
	public UIPanel getPaneTop() {
		if(paneTop == null){
			paneTop = new UIPanel();
			paneTop.setLayout(null);
			paneTop.setPreferredSize(new java.awt.Dimension(1,35));
			labsystem = new UILabel();
			labsystem.setText("ϵͳ");
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
//			table.getColumnModel().getColumn(3).setCellEditor(new UIRefCellEditor(getCmbIsfix()));
			table.getColumnModel().getColumn(2).setCellEditor(new UIRefCellEditor(getCmbType()));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("���");
			column.addElement("����");
			column.addElement("��������");
			column.addElement("����");
			tablemodel.setColumnIdentifiers(column);
			
		}
		return tablemodel;
	}
	private UIComboBox getCmbIsfix(){
		if(cmbisfix == null){
			cmbisfix = new UIComboBox();
			cmbisfix.addItem("��");
			cmbisfix.addItem("��");
		}
		return cmbisfix;
	}
	private UIComboBox getCmbType(){
		if(cmbtype == null){
			cmbtype = new UIComboBox();
			cmbtype.addItem("�ַ�");
			cmbtype.addItem("��ֵ");
		}
		return cmbtype;
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
