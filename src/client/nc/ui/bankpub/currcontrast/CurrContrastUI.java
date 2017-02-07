package nc.ui.bankpub.currcontrast;


import java.awt.BorderLayout;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.ICurrContrast;
import nc.itf.uap.bd.currtype.ICurrtype;
import nc.itf.uif.pub.IUifService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.vo.bankpub.currcontrast.CurrContrastVO;
import nc.vo.bd.b20.CurrtypeVO;
import nc.vo.pub.VOStatus;

public class CurrContrastUI extends ToftPanel  implements ValueChangedListener, BillEditListener {

    
    public ButtonObject m_btnEdit = new ButtonObject ("修改","修改",2);
    public ButtonObject m_btnSave = new ButtonObject ("保存","保存",2);
    public ButtonObject m_btnCancel = new ButtonObject ("取消","取消",2);
    public ButtonObject m_btnRefresh = new ButtonObject ("刷新","刷新",2);
    public ButtonObject [] m_btns = new ButtonObject [] {
            m_btnEdit,m_btnSave,m_btnCancel,m_btnRefresh
    };
    
	private BillScrollPane billTable = null;
    /**
     * 
     */
    public CurrContrastUI() {
        super();
		initialize();
        // TODO 自动生成构造函数存根
    }

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(553, 282);
        this.add(getBillTable(), java.awt.BorderLayout.CENTER);
		setButtons(m_btns);
        

        try {
            initTable();
        	getBillTable().addEditListener(this);
            setBrowseStatus();
            onRefresh();
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.showErrorDlg(this,"错误",e.getMessage());
        }
	}
    /* （非 Javadoc）
     * @see nc.ui.pub.ToftPanel#getTitle()
     */
    public String getTitle() {
        // TODO 自动生成方法存根
        return "币种对照表";
    }

    /* （非 Javadoc）
     * @see nc.ui.pub.ToftPanel#onButtonClicked(nc.ui.pub.ButtonObject)
     */
    public void onButtonClicked(ButtonObject bo) {
        // TODO 自动生成方法存根
        
        try {
            if(bo==m_btnSave){
                onSave();
            }
            if(bo==m_btnEdit){
                onEdit();
            }
            if(bo==m_btnCancel){
                onMyCancel();
            }
            if(bo==m_btnRefresh){
                onRefresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.showErrorDlg(this,"错误",e.getMessage());
        }
    }

	/**
	 * This method initializes billScrollPane	
	 * 	
	 * @return nc.ui.pub.bill.BillScrollPane	
	 */    
	private BillScrollPane getBillTable() {
		if (billTable == null) {
			billTable = new BillScrollPane();
		}
		return billTable;
	}
	
	public void initTable() throws Exception {
	    String [] names = {"主键","币种主键","NC币种","大机币种编码","大机币种名称"};
	    String [] keys = {"pk_currcontrast","pk_currtype","currcode","bank_currcode","bank_currname"};
	    
	    BillModel model = new BillModel();
	    
	    BillItem [] items = new BillItem [names.length];
	    for (int i = 0; i < items.length; i++) {
	        items[i] = new BillItem();
	        items[i].setName(names[i]);
	        items[i].setKey(keys[i]);
	        items[i].setWidth(150); //设列宽
	        items[i].setEdit(true);
	        items[i].setShow(true); 
	        items[i].setDataType(BillItem.STRING);
	        items[i].setPos(BillItem.BODY);
	        ((UIRefPane)items[i].getComponent()).setMaxLength(500);
        }
	    
	    //主键列不显示
	    items[0].setShow(false);
	    items[1].setShow(false);
	    
//	    items[2].setEdit(false);
	    items[3].setEdit(true);
	    items[4].setEdit(true);
	          
	    items[2].setIDColName("pk_currtype");
	    items[2].setLoadFormula(new String[]{"currcode->getColValue(bd_currtype,currtypecode,pk_currtype,pk_currtype)"});
	    
	    model.setBodyItems(items);
		getBillTable().setTableModel(model);
		getBillTable().setRowNOShow(true);
		getBillTable().getTable().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		
	}
	
	public void setEditStatus(){
	    m_btnSave.setEnabled(true);
	    m_btnCancel.setEnabled(true);
	    m_btnEdit.setEnabled(false);
	    m_btnRefresh.setEnabled(false);
	    updateButtons();
	    
	    BillItem[] items = getBillTable().getTableModel().getBodyItems();
	    for (int i = 0; i < items.length; i++) {
            if(items[i].getKey().equals("bank_currcode") 
            		|| items[i].getKey().equals("bank_currname") ){
                items[i].setEdit(true);
            } else {
                items[i].setEdit(false);
            }
        }
	}
	
	public void setBrowseStatus(){
	    m_btnSave.setEnabled(false);
	    m_btnCancel.setEnabled(false);
	    m_btnEdit.setEnabled(true);
	    m_btnRefresh.setEnabled(true);
	    updateButtons();

	    
	    BillItem[] items = getBillTable().getTableModel().getBodyItems();
	    for (int i = 0; i < items.length; i++) {
            items[i].setEdit(false);
        }
	}
	
	public void onEdit() throws Exception {
	    setEditStatus();
	}
	
	public void onSave() throws Exception {
		if (getBillTable().getTable().isEditing()) {
		    getBillTable().getTable().editingStopped(new javax.swing.event.ChangeEvent(this));
		}
		CurrContrastVO[] allVO =(CurrContrastVO[]) getBillTable().getTableModel().getBodyValueVOs("nc.vo.bankpub.currcontrast.CurrContrastVO");
		checkVO(allVO);
		ICurrContrast ic = (ICurrContrast)NCLocator.getInstance().lookup(ICurrContrast.class.getName());
		ic.saveAll(allVO);
		onRefresh();
	    setBrowseStatus();
	}
	
	public void onMyCancel() throws Exception {
	    onRefresh();
	    setBrowseStatus();
	}
	
	public void onRefresh() throws Exception {
		IUifService uifService = (IUifService) NCLocator.getInstance().lookup(IUifService.class.getName());
		ICurrContrast ic = (ICurrContrast)NCLocator.getInstance().lookup(ICurrContrast.class.getName());
		ICurrtype it = (ICurrtype)NCLocator.getInstance().lookup(ICurrtype.class.getName());
	    CurrtypeVO [] ctvos = it.queryAllCurrtypeVO(null);
	    CurrContrastVO [] ccvos = ic.queryAll();
	    HashMap hmCc = new HashMap();
	    if(ccvos!=null && ccvos.length!=0){
		    for (int i = 0; i < ccvos.length; i++) {
	            hmCc.put(ccvos[i].getPk_currtype(),ccvos[i]);
	        }
	    }
	    if(ccvos==null || ccvos.length==0 || ctvos.length > ccvos.length){
	        ccvos = new CurrContrastVO [ctvos.length];
	        for (int i = 0; i < ctvos.length; i++) {
	            if(hmCc.containsKey(ctvos[i].getPk_currtype())){
	                ccvos[i] = (CurrContrastVO) hmCc.get(ctvos[i].getPk_currtype());
	            } else {
	                ccvos[i] = new CurrContrastVO();
	                ccvos[i].setPk_currtype(ctvos[i].getPk_currtype());
	                ccvos[i].setBank_currcode("##");
	                ccvos[i].setBank_currname("##");
	                ccvos[i].setStatus(VOStatus.NEW);
					String pk = uifService.insert(ccvos[i]);
					ccvos[i].setPrimaryKey(pk);
	            }
            }
	    }
	    getBillTable().getTableModel().setBodyDataVO(ccvos);
	    getBillTable().getTableModel().updateValue();
	    getBillTable().getTableModel().execLoadFormula();	    
	    setBrowseStatus();
	}

    /* （非 Javadoc）
     * @see nc.ui.pub.beans.ValueChangedListener#valueChanged(nc.ui.pub.beans.ValueChangedEvent)
     */
    public void valueChanged(ValueChangedEvent event) {
        // TODO 自动生成方法存根
        
    }

    /* （非 Javadoc）
     * @see nc.ui.pub.bill.BillEditListener#afterEdit(nc.ui.pub.bill.BillEditEvent)
     */
    public void afterEdit(BillEditEvent e) {
        // TODO 自动生成方法存根
        
    }

    /* （非 Javadoc）
     * @see nc.ui.pub.bill.BillEditListener#bodyRowChange(nc.ui.pub.bill.BillEditEvent)
     */
    public void bodyRowChange(BillEditEvent e) {
        // TODO 自动生成方法存根
        
    }
    
    public void checkVO(CurrContrastVO[] allVO) throws Exception {
        for (int i = 0; i < allVO.length; i++) {
    		allVO[i].validate();
    	}
    }
 }
