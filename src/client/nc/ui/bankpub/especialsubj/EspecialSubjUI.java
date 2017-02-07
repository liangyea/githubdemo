package nc.ui.bankpub.especialsubj;

import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IEspecialSubj;
import nc.ui.ar.printadapter.PrintManager;
import nc.ui.bankpub.dealcode.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.vo.bankpub.especialsubj.EspecialSubjVO;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.pub.BusinessException;

public class EspecialSubjUI extends ToftPanel {

	private String[] strbtn = {"新增","修改","保存","删除","取消","刷新","打印"}; 
	private ButtonObject btn[] = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private UIComboBox cmbdcorient = null;
	private UIRefPane refaccsubj = null;
	private int currentRow = 0;
	private int state = 0;
	private int initRow = 0; 
	private EspecialSubjVO[] m_vos = null;
	private EspecialSubjVO[] m_tempvos = null;
	private Hashtable hashsubj = new Hashtable();
	
	

	@Override
	public String getTitle() {
		// TODO 自动生成方法存根
		return "例外科目定义";
	}
	public EspecialSubjUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}
	@Override
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
	private void onAdd() throws Exception{
		try{
			getTablemodel().addRow(1);
			int row = getTable().getRowCount();
			getTablemodel().setRowEditable(row-1,true);
			getTable().setModel(getTablemodel());
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
		Hashtable hashmc = new Hashtable();
		if(m_vos!=null && m_vos.length!=0){
			for(int i=0;i<initRow;i++){
				hashbm.put(m_vos[i].getAccsubjcode().trim(),"accsubjcode");
				hashmc.put(m_vos[i].getAccsubjname().trim(),"accsubjname");
			}	
		}
		if(state==1)
		{
			int row = getTable().getRowCount();
			m_tempvos = new EspecialSubjVO[row-initRow];
			for(int i=initRow;i<row;i++){
				m_tempvos[i-initRow] = new EspecialSubjVO();
				for(int j=0;j<4;j++){
					if(getTable().getValueAt(i,j) == null
							||	getTable().getValueAt(i,j).toString().trim().equalsIgnoreCase(""))
					{
						if(j==0){
							showWarningMessage("请填写科目编码！");
							return;
							
						}
						if(j==1){
							
							showWarningMessage("请填写科目名称！");
							return;
						}
						if(j==2){
							
							showWarningMessage("请选择借贷方向！");
							return;
						}
						if(j==3){
							
							showWarningMessage("请参照入账科目！");
							return;
						}
						
					}
					
				}
				m_tempvos[i-initRow].setAccsubjcode(getTable().getValueAt(i, 0).toString());
				m_tempvos[i-initRow].setAccsubjname(getTable().getValueAt(i, 1).toString());
				m_tempvos[i-initRow].setDcflag(getTable().getValueAt(i, 2).toString().trim().equals("借")?"Y":"N");
				m_tempvos[i-initRow].setSubjcode((String)hashsubj.get(String.valueOf(new Integer(i))));
				if(hashbm.containsKey(m_tempvos[i-initRow].getAccsubjcode().trim())){
					showWarningMessage("科目编码不能重复！");
					return;
				}
				if(hashmc.containsKey(m_tempvos[i-initRow].getAccsubjname().trim())){
					showWarningMessage("科目名称不能重复！");
					return;
				}
				hashbm.put(m_tempvos[i-initRow].getAccsubjcode().trim(),"accsubjcode");
				hashmc.put(m_tempvos[i-initRow].getAccsubjname().trim(),"accsubjname");

			}
			try {
				IEspecialSubj i =(IEspecialSubj)NCLocator.getInstance().lookup(IEspecialSubj.class.getName());
				i.insertArrayEspecialSubjVO(m_tempvos);
			} catch (BusinessException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}
		if(state==2)//修改
		{
			
			hashbm.remove(m_vos[currentRow].getAccsubjcode().trim());
			hashmc.remove(m_vos[currentRow].getAccsubjname().trim());
			for(int j=0;j<4;j++){
				if(getTable().getValueAt(currentRow,j) == null
						||	getTable().getValueAt(currentRow,j).toString().trim().equalsIgnoreCase(""))
				{
					if(j==0){
						showWarningMessage("请填写科目编码！");
						return;
						
					}
					if(j==1){
						
						showWarningMessage("请填写科目名称！");
						return;
					}
					if(j==2){
						
						showWarningMessage("请选择借贷方向！");
						return;
					}
					if(j==3){
						
						showWarningMessage("请参照入账科目！");
						return;
					}
					
				}
				
			}
			if(hashbm.containsKey(m_vos[currentRow].getAccsubjcode().trim())){
				showWarningMessage("科目编码不能重复！");
				return;
			}
			if(hashmc.containsKey(m_vos[currentRow].getAccsubjname().trim())){
				showWarningMessage("科目名称不能重复！");
				return;
			}
			
			
			EspecialSubjVO vo = new EspecialSubjVO();
			vo.setPk_especialsubj(m_vos[currentRow].getPk_especialsubj());
			vo.setAccsubjcode(getTable().getValueAt(currentRow,0)==null?"":getTable().getValueAt(currentRow,0).toString().trim());
			vo.setAccsubjname(getTable().getValueAt(currentRow,1)==null?"":getTable().getValueAt(currentRow,1).toString().trim());
			vo.setDcflag(getTable().getValueAt(currentRow,2).toString().trim().equals("借")?"Y":"N");
			String subjcode = (String)hashsubj.get(String.valueOf(new Integer(currentRow)))==null?m_vos[currentRow].getSubjcode():(String)hashsubj.get(String.valueOf(new Integer(currentRow)));
			vo.setSubjcode(subjcode);
			try {
				IEspecialSubj i =(IEspecialSubj)NCLocator.getInstance().lookup(IEspecialSubj.class.getName());
				i.updateEspecialSubjVOByVO(vo);
			} catch (BusinessException e) {
				// TODO 自动生成 catch 块
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
			showWarningMessage("请选择一行。");
			return;
		}
        int ire = showYesNoMessage("是否删除当前记录,请确认？");
        if(ire != UIDialog.ID_YES)
            return;
		try {
			IEspecialSubj i =(IEspecialSubj)NCLocator.getInstance().lookup(IEspecialSubj.class.getName());
			i.deleteEspecialSubjVOByVO(m_vos[row]);
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
			IEspecialSubj i =(IEspecialSubj)NCLocator.getInstance().lookup(IEspecialSubj.class.getName());
			m_vos = i.queryAllEspecialSubjVOs();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Object[][] datas = null;
		if(m_vos!=null && m_vos.length!=0){
			initRow = m_vos.length;
			datas = new Object[m_vos.length][4];
			for(int i=0;i<datas.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].getAccsubjcode();
				datas[i][1] = new Object();
				datas[i][1] = m_vos[i].getAccsubjname();
				datas[i][2] = new Object();
				datas[i][2] = m_vos[i].getDcflag().trim().equals("Y")?"借":"贷";
				datas[i][3] = new Object();
				datas[i][3] = m_vos[i].subjname;
			}
			tablemodel.setDataVector(datas);
			
		}else{
			initRow = 0;
			datas = new Object[0][4];
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
			table.getColumnModel().getColumn(2).setCellEditor(new UIRefCellEditor(getCmbDCOrient()));
			table.getColumnModel().getColumn(3).setCellEditor(getUIRefCellEditor());
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("科目编码"); //1
			column.addElement("科目名称"); //2
			column.addElement("借贷方向"); //3
			column.addElement("入账科目"); //4
			tablemodel.setColumnIdentifiers(column);
			
		}
		return tablemodel;
	}
	private UIComboBox getCmbDCOrient(){
		if(cmbdcorient==null){
			cmbdcorient = new UIComboBox();
			cmbdcorient.addItem("借");
			cmbdcorient.addItem("贷");
			
		}
		return cmbdcorient;
		
	}
	private UIRefPane getRefAccsubj(){
		
		if(refaccsubj==null){
			refaccsubj = new UIRefPane();
			refaccsubj.setRefNodeName(RefNodeNameConst.ACCSUBJ);
//			refaccsubj.setWhereString("pk_glorgbook='0001'");
			refaccsubj.setNotLeafSelectedEnabled(false);
			
		}
		return refaccsubj;
	}
	private UIRefCellEditor getUIRefCellEditor()
	{
		UIRefCellEditor editor = new UIRefCellEditor(getRefAccsubj());
		editor.addCellEditorListener(new CellEditorListener(){


			public void editingStopped(ChangeEvent e) {
				// TODO Auto-generated method stub
				afterEdit();
			}

			public void editingCanceled(ChangeEvent e) {
				// TODO 自动生成方法存根
				
			}
			
			});
		return editor;
	}
	public void afterEdit() 
    {
		    int colcount = tablemodel.getColumnCount();
			currentRow = getTable().getSelectedRow();
			String pkaccsubj = refaccsubj.getRefPK();
    		if(pkaccsubj==null || pkaccsubj.length()==0){
    			return;
    		}
    		String subjname = refaccsubj.getRefName();
    		String subjcode = refaccsubj.getRefCode();
    		hashsubj.put(String.valueOf(new Integer(currentRow)),subjcode);
    		getTablemodel().setValueAt(subjname,currentRow,colcount-1);
	

    }
}
