package nc.ui.bankpub.system;

import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.ISystemType;
import nc.itf.uap.bd.vouchertype.IVoucherTypeQry;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.b18.VouchertypeVO;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;

public class SystemTypeUI extends ToftPanel {

	private String[] strbtn = {"新增","修改","保存","删除","取消","刷新"}; 
	private ButtonObject btn[] = null;
	private UIPanel paneTop = null;
	private UIPanel paneContent = null;
	private UITablePane tablepane = null;
	private UITable table = null;
	private JKTableModel tablemodel = null;
	private UIComboBox cmbdateformat = null;
	private UIComboBox cmbvouchertype = null;
	private UIComboBox cmbtransmether = null;
	private UIComboBox cmbisfixlen = null;
	private UIComboBox cmbisprealert = null;
	private int state = 0;  //计提凭证类型新增or修改
	private int currentRow = 0;
	private int initRow = 0;  // 主界面初始化行数
	private SystemTypeVO[] vos = null; 
	private SystemTypeVO[] tempvos = null; //暂存修改vo
	private Hashtable hashvouchtypepk = null;
	private	Hashtable hashsystemname = null;
	private Hashtable hashsystemcode = null;
	private JPasswordField pwField = null;
	
	public String getTitle() {
		// TODO 自动生成方法存根
		return null;
	}

	public SystemTypeUI() {
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
		this.setTitleText("计提凭证类型定义");
		
	}
	private void initDatas(){
		ISystemType is =(ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
		try {
			vos = is.queryAllSystemTypeVOs();
		} catch (BusinessException e1) {
			e1.printStackTrace();
			Debug.error(e1.getMessage(), e1);
		}
		hashsystemname = new Hashtable();
		hashsystemcode = new Hashtable();
		if(vos!=null && vos.length!=0){
			for(int i=0;i<vos.length;i++){
				hashsystemname.put(vos[i].getSystemname().trim(),"systemname");
				hashsystemcode.put(vos[i].getSystemcode().trim(),"systemcode");
			}	
		}
		VouchertypeVO[] vouchertypes = null;
		try {
			IVoucherTypeQry iv = (IVoucherTypeQry)NCLocator.getInstance().lookup(IVoucherTypeQry.class.getName());
			vouchertypes = iv.queryVoucherTypeVOs(null);
		} catch (BusinessException e) {
			e.printStackTrace();
			Debug.error(e.getMessage(), e);
		}
		hashvouchtypepk = new Hashtable();
		if(vouchertypes!=null && vouchertypes.length !=0){
			for(int i=0;i<vouchertypes.length;i++){
				hashvouchtypepk.put(vouchertypes[i].getVouchtypename().trim(), vouchertypes[i].getPk_vouchertype().trim());
				getCmbVoucherType().addItem(vouchertypes[i].getVouchtypename().toString().trim());
			}
		}


		try {
			onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onState(0);
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
	private void onSave() throws Exception {
		try{
			getTable().getCellEditor().stopCellEditing();
		}catch(Exception ex){
		}
		Hashtable hashbh = new Hashtable();
		Hashtable hashmc = new Hashtable();
		if(vos!=null && vos.length!=0){
			for(int i=0;i<initRow;i++){
				hashbh.put(vos[i].getSystemcode().trim(),"systemcode");
				hashmc.put(vos[i].getSystemname().trim(),"systemname");
			}	
		}
		ISystemType is =(ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());

		if(state==1)//新增
		{

			int row = getTable().getRowCount();
			tempvos = new SystemTypeVO[row-initRow];
			for(int i=initRow;i<row;i++){
				tempvos[i-initRow] = new SystemTypeVO();
				boolean trans = getTable().getValueAt(i,4).toString().trim().equals("FTP")?true:false;
				boolean isfix =  getTable().getValueAt(i,11).toString().trim().equals("是")?true:false;
				for(int j=0;j<17;j++){
					
					if(j==12 && getTable().getValueAt(i,j).toString().length()>1){
						showWarningMessage("分割符号只能设置一位！");
						return;
					}
					if(getTable().getValueAt(i,j) == null
							||	getTable().getValueAt(i,j).toString().trim().equalsIgnoreCase("")){
						if(j==0){
							showWarningMessage("请填写系统编号！");
							return;
						}
						if(j==1){
							showWarningMessage("请填写系统名称！");
							return;
						}
						if(j==2){
							showWarningMessage("请选择日期格式！");
							return;
						}
						if(j==3){
							showWarningMessage("请选择凭证类别！");
							return;
						}
						if(j==4){
							showWarningMessage("请选择传输方式！");
							return;
						}
						if(j==5 && trans){
							showWarningMessage("请填写FTP地址！");
							return;
						}
						if(j==6 && trans){
							showWarningMessage("请填写FTP端口！");
							return;
						}
						if(j==7 && trans){
							showWarningMessage("请填写用户名称！");
							return;
						}
						if(j==8 && trans){
							showWarningMessage("请填写密码！");
							return;
						}
						if(j==9 && trans){
							showWarningMessage("请填写FTP路径！");
							return;
						}
						if(j==10 && !trans){
							showWarningMessage("请填写本地路径！");
							return;
						}
						if(j==12 && !isfix){
							showWarningMessage("请填写分割符！");
							return;	
						}
						if(j==13){
							showWarningMessage("请填写数据文件名称！");
							return;	
						}
						if(j==14){
							showWarningMessage("请填写汇率文件名称！");
							return;	
						}
						if(j==15){
							showWarningMessage("请填写对账文件名称！");
							return;	
						}
						if(j==16){
							showWarningMessage("请填写文件后缀名称！");
							return;	
						}
						if(j==17){
							showWarningMessage("请选择是否执行预警操作！");
							return;	
						}
					}
					
				}
				
				tempvos[i-initRow].setSystemcode(getTable().getValueAt(i,0).toString().trim());
				tempvos[i-initRow].setSystemname(getTable().getValueAt(i,1).toString().trim());
				tempvos[i-initRow].setDateformat(getTable().getValueAt(i,2).toString().trim());
				tempvos[i-initRow].setVouchertype((String)hashvouchtypepk.get(getTable().getValueAt(i,3).toString().trim()));
				tempvos[i-initRow].setTransmether(getTable().getValueAt(i,4).toString().trim().equals("FTP")?"Y":"N");
				tempvos[i-initRow].setIPaddress(getTable().getValueAt(i,5)==null?"":getTable().getValueAt(i,5).toString().trim());
				tempvos[i-initRow].setPort(getTable().getValueAt(i,6)==null?"":getTable().getValueAt(i,6).toString().trim());
				tempvos[i-initRow].setUsername(getTable().getValueAt(i,7)==null?"":getTable().getValueAt(i,7).toString().trim());
				tempvos[i-initRow].setPassword(getPasswordField().getPassword()==null?"":String.valueOf(getPasswordField().getPassword()));
				tempvos[i-initRow].setFtppath(getTable().getValueAt(i,9)==null?"":getTable().getValueAt(i,9).toString().trim());
				tempvos[i-initRow].setLocalpath(getTable().getValueAt(i,10)==null?"":getTable().getValueAt(i,10).toString().trim());
				tempvos[i-initRow].setIsfixlen(getTable().getValueAt(i,11).toString().trim().equals("是")?"Y":"N");
				tempvos[i-initRow].setSeparator(getTable().getValueAt(i,12)==null?"":getTable().getValueAt(i,12).toString().trim());
				tempvos[i-initRow].setDatafilename(getTable().getValueAt(i,13)==null?"":getTable().getValueAt(i,13).toString().trim());
				tempvos[i-initRow].setRatefilename(getTable().getValueAt(i,14)==null?"":getTable().getValueAt(i,14).toString().trim());
				tempvos[i-initRow].setRatefilename(getTable().getValueAt(i,15)==null?"":getTable().getValueAt(i,15).toString().trim());
				tempvos[i-initRow].setSuffixname(getTable().getValueAt(i,16)==null?"":getTable().getValueAt(i,16).toString().trim());
				tempvos[i-initRow].setIsprealert(getTable().getValueAt(i,17).toString().trim().equals("是")?"Y":"N");
				
				if(hashbh.containsKey(tempvos[i-initRow].getSystemcode().trim())){
					showWarningMessage("系统编号不能重复！");
					return;
				}
				if(hashmc.containsKey(tempvos[i-initRow].getSystemname().trim())){
					showWarningMessage("系统名称不能重复！");
					return;
				}
				hashbh.put(tempvos[i-initRow].getSystemcode().trim(),"typecode");
				hashmc.put(tempvos[i-initRow].getSystemname().trim(),"typename");
			}
			try {
				is.insertArraySystemTypeVO(tempvos);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		if(state==2)//修改
		{
			hashbh.remove(vos[currentRow].getSystemcode().trim());
			hashmc.remove(vos[currentRow].getSystemname().trim());
			
			for(int j=0;j<17;j++){
				boolean trans = getTable().getValueAt(currentRow,4).toString().trim().equals("FTP")?true:false;
				boolean isfix =  getTable().getValueAt(currentRow,11).toString().trim().equals("是")?true:false;

				if(getTable().getValueAt(currentRow,j)==null 
						|| 	getTable().getValueAt(currentRow,j).toString().trim().equalsIgnoreCase("")){
					if(j==0){
						showWarningMessage("请填写系统编号！");
						return;
					}
					if(j==1){
						showWarningMessage("请填写系统名称！");
						return;
					}
					if(j==2){
						showWarningMessage("请选择日期格式！");
						return;
					}
					if(j==3){
						showWarningMessage("请选择凭证类别！");
						return;
					}
					if(j==5 && trans){
						showWarningMessage("请填写FTP地址！");
						return;
					}
					if(j==6 && trans){
						showWarningMessage("请填写FTP端口！");
						return;
					}
					if(j==7 && trans){
						showWarningMessage("请填写用户名称！");
						return;
					}
					if(j==8 && trans){
						showWarningMessage("请填写密码！");
						return;
					}
					if(j==9 && trans){
						showWarningMessage("请填写FTP路径！");
						return;
					}
					if(j==10 && !trans){
						showWarningMessage("请填写本地路径！");
						return;
					}
					if(j==12 && !isfix){
						showWarningMessage("请填写分割符！");
						return;	
					}
					if(j==13){
						showWarningMessage("请填写数据文件名称！");
						return;	
					}
					if(j==14){
						showWarningMessage("请填写汇率文件名称！");
						return;	
					}
					if(j==15){
						showWarningMessage("请填写对账文件名称！");
						return;	
					}
					if(j==16){
						showWarningMessage("请填写文件后缀名称！");
						return;	
					}
					if(j==17){
						showWarningMessage("请选择是否执行预警操作！");
						return;	
					}
				}
				
			}
			String typecode = getTable().getValueAt(currentRow,0).toString().trim();
			String typename = getTable().getValueAt(currentRow,1).toString().trim();
			String dataformat = getTable().getValueAt(currentRow,2).toString().trim();
			if(hashbh.containsKey(typecode.trim())){
				showWarningMessage("系统编号不能重复！");
				return;
			}
			if(hashmc.containsKey(typename.trim())){
				showWarningMessage("系统名称不能重复！");
				return;
			}
			SystemTypeVO vo = new SystemTypeVO();
			vo.setPk_system(vos[currentRow].getPk_system());
			vo.setSystemcode(typecode);
			vo.setSystemname(typename);
			vo.setDateformat(dataformat);
			vo.setVouchertype((String)hashvouchtypepk.get(getTable().getValueAt(currentRow,3).toString().trim()));
			vo.setTransmether(getTable().getValueAt(currentRow,4)==null?"":getTable().getValueAt(currentRow,4).toString().trim().equals("FTP")?"Y":"N");
			vo.setIPaddress(getTable().getValueAt(currentRow,5)==null?"":getTable().getValueAt(currentRow,5).toString().trim());
			vo.setPort(getTable().getValueAt(currentRow,6)==null?"":getTable().getValueAt(currentRow,6).toString().trim());
			vo.setUsername(getTable().getValueAt(currentRow,7)==null?"":getTable().getValueAt(currentRow,7).toString().trim());
			vo.setPassword(getPasswordField().getPassword()==null?"":String.valueOf(getPasswordField().getPassword()));
			vo.setFtppath(getTable().getValueAt(currentRow,9)==null?"":getTable().getValueAt(currentRow,9).toString().trim());
			vo.setLocalpath(getTable().getValueAt(currentRow,10)==null?"":getTable().getValueAt(currentRow,10).toString().trim());
			vo.setIsfixlen(getTable().getValueAt(currentRow,11).toString().trim().equals("是")?"Y":"N");
			vo.setSeparator(getTable().getValueAt(currentRow,12)==null?"":getTable().getValueAt(currentRow,12).toString().trim());
			vo.setDatafilename(getTable().getValueAt(currentRow,13)==null?"":getTable().getValueAt(currentRow,13).toString().trim());
			vo.setRatefilename(getTable().getValueAt(currentRow,14)==null?"":getTable().getValueAt(currentRow,14).toString().trim());
			vo.setAccountfilename(getTable().getValueAt(currentRow,15)==null?"":getTable().getValueAt(currentRow,15).toString().trim());
			vo.setSuffixname(getTable().getValueAt(currentRow,16)==null?"":getTable().getValueAt(currentRow,16).toString().trim());
			vo.setIsprealert(getTable().getValueAt(currentRow,17).toString().trim().equals("是")?"Y":"N");
			
			try {
				is.updateSystemTypeVOByVO(vo);
				
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
			showWarningMessage("请选择一行。");
			return;
		}
        int ire = showYesNoMessage("是否删除当前记录,请确认？");
        if(ire != UIDialog.ID_YES)
            return;
		try {
			
			ISystemType is =(ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
			is.deleteSystemTypeVOByVO(vos[row]);
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
	private void onRefresh() throws Exception {
		

		try {
			ISystemType is =(ISystemType)NCLocator.getInstance().lookup(ISystemType.class.getName());
			vos = is.queryAllSystemTypeVOs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		Object[][] datas = null;
		
		if(vos!=null && vos.length!=0){
			initRow = vos.length;
			datas = new Object[vos.length][18];
			for(int i=0;i<vos.length;i++){
				datas[i][0] = new Object();
				datas[i][0] = vos[i].getSystemcode().trim();
				datas[i][1] = new Object();
				datas[i][1] = vos[i].getSystemname().trim();
				datas[i][2] = new Object();
				datas[i][2] = vos[i].getDateformat();
				datas[i][3] = new Object();
				datas[i][3] = vos[i].vouchertypename.trim();//!!!!!!!!!!!
				datas[i][4] = new Object();
				if(vos[i].getTransmether().trim().equals("Y")){
					
					datas[i][4] = "FTP";
				}else{
					datas[i][4] = "非FTP";
				}
				datas[i][5] = new Object();
				datas[i][5] = vos[i].getIPaddress().trim();
				datas[i][6] = new Object();
				datas[i][6] = vos[i].getPort().trim();
				datas[i][7] = new Object();
				datas[i][7] = vos[i].getUsername().trim();
				datas[i][8] = new Object();
				datas[i][8] = vos[i].getPassword().trim();
				datas[i][9] = new Object();
				datas[i][9] = vos[i].getFtppath().trim();
				datas[i][10] = new Object();
				datas[i][10] = vos[i].getLocalpath().trim();
				datas[i][11] = new Object();
				if(vos[i].getIsfixlen().trim().equals("Y")){
					datas[i][11] = "是";
				}else{
					datas[i][11] = "否";
				}
				datas[i][12] = new Object();
				datas[i][12] = vos[i].getSeparator().trim();
				datas[i][13] = new Object();
				datas[i][13] = vos[i].getDatafilename().trim();
				datas[i][14] = new Object();
				datas[i][14] = vos[i].getRatefilename().trim();
				datas[i][15] = new Object();
				datas[i][15] = vos[i].getAccountfilename().trim();
				datas[i][16] = new Object();
				datas[i][16] = vos[i].getSuffixname().trim();
				if(vos[i].getIsprealert().trim().equals("Y")){
					datas[i][17] = "是";
				}else{
					datas[i][17] = "否";
				}
			}
			getTablemodel().setDataVector(datas);
		}else{
			initRow = 0;
			datas = new Object[0][18];
			getTablemodel().setDataVector(datas);
			
		}
		
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
			table.getColumnModel().getColumn(2).setCellEditor(new UIRefCellEditor(getCmbDateFormate()));
			table.getColumnModel().getColumn(3).setCellEditor(new UIRefCellEditor(getCmbVoucherType()));
			table.getColumnModel().getColumn(4).setCellEditor(new UIRefCellEditor(getCmbTransMether()));
			table.getColumnModel().getColumn(8).setCellEditor(new UIRefCellEditor(getPasswordField()));
			table.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer()
			{
				public void setValue(Object value)
				{
					super.setValue("******");
				}
			});
			table.getColumnModel().getColumn(11).setCellEditor(new UIRefCellEditor(getCmbIsFixLen()));
			table.getColumnModel().getColumn(17).setCellEditor(new UIRefCellEditor(getCmbIsPreAlert()));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return table;
	}
	private JKTableModel getTablemodel(){
		if(tablemodel == null){
			tablemodel = new JKTableModel();
			Vector column = new Vector();
			column.addElement("系统编码"); //1
			column.addElement("系统名称"); //2
			column.addElement("日期格式"); //3
			column.addElement("凭证类别"); //4
			column.addElement("传输方式"); //5
			column.addElement("FTP地址"); //6
			column.addElement("FTP端口"); //7
			column.addElement("用户"); //8
			column.addElement("密码"); //9
			column.addElement("FTP路径"); //10
			column.addElement("本地路径"); //11
			column.addElement("是否定长"); // 12
			column.addElement("分割符"); //13
			column.addElement("数据文件名称"); //14
			column.addElement("汇率文件名称"); //15
			column.addElement("对账文件名称"); //16
			column.addElement("文件后缀名称"); //17
			column.addElement("是否预警"); //18
			tablemodel.setColumnIdentifiers(column);

			
		}
		return tablemodel;
	}
	public UIComboBox getCmbDateFormate() {
		if(cmbdateformat == null){
			cmbdateformat = new UIComboBox();
			cmbdateformat.addItem("yyyymmdd");
			cmbdateformat.addItem("yyyy-mm-dd");
			cmbdateformat.addItem("yyyy/mm/dd");
			
		}
		return cmbdateformat;
	}
	public UIComboBox getCmbVoucherType() {
		if(cmbvouchertype == null){
			cmbvouchertype = new UIComboBox();
		}
		return cmbvouchertype;
	}
	public UIComboBox getCmbTransMether() {
		if(cmbtransmether == null){
			cmbtransmether = new UIComboBox();
			cmbtransmether.addItem("FTP");
			cmbtransmether.addItem("非FTP");
		}
		return cmbtransmether;
	}
	public UIComboBox getCmbIsFixLen() {
		if(cmbisfixlen == null){
			cmbisfixlen = new UIComboBox();
			cmbisfixlen.addItem("是");
			cmbisfixlen.addItem("否");
		}
		return cmbisfixlen;
	}
	public UIComboBox getCmbIsPreAlert() {
		if(cmbisprealert == null){
			cmbisprealert = new UIComboBox();
			cmbisprealert.addItem("是");
			cmbisprealert.addItem("否");
		}
		return cmbisprealert;
	}
	public JPasswordField getPasswordField(){
		if(pwField==null){
			pwField = new JPasswordField();
			pwField.setEchoChar('*');
		}
		return pwField;
	}
}
