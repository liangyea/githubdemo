// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:50:27
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   NCBankContrastUI.java

package nc.ui.bankpub.bankcontrast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankContrast;
import nc.itf.bankpub.pub.ISystemType;
import nc.itf.dap.priv.IGLOrgBook;
import nc.itf.uap.bd.multibook.IGLOrgBookAcc;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.vo.bankpub.bankcontrast.BankContrastVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.b54.GlorgVO;
import nc.vo.bd.b54.GlorgbookVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;

public class NCBankContrastUI extends ToftPanel
    implements ValueChangedListener, BillEditListener
{

    public NCBankContrastUI()
    {
        ivjm_refDwbm = null;
        ivjUILabel1 = null;
        ivjUIPanel1 = null;
        ivjUIPanel = null;
        m_boPrint = new ButtonObject("打印", "打印", 2);
        m_boEdit = new ButtonObject("修改", "修改", 2);
        m_boSave = new ButtonObject("确定", "确定", 2);
        m_boCancel = new ButtonObject("取消", "取消", 2);
        m_boRefresh = new ButtonObject("刷新", "刷新", 2);
        m_aryButtons = (new ButtonObject[] {
            m_boEdit, m_boSave, m_boCancel, m_boRefresh
        });
        m_iState = 0;
        ivjTable1 = null;
        UILabel = null;
        nothPanel = null;
        cmbSystem = null;
        m_vos = null;
        hashsystem = null;
        initialize();
    }

    public void afterEdit(BillEditEvent e)
    {
        if(e.getKey().equals("icategory"))
        {
            Object a = e.getValue();
            e.getTableCode();
        }
    }

    public void bodyRowChange(BillEditEvent billeditevent)
    {
    }

    public boolean checkVO(BankContrastVO allVO[])
    {
        try
        {
            for(int i = 0; i < allVO.length; i++)
                allVO[i].validate();

        }
        catch(ValidationException e)
        {
            MessageDialog.showErrorDlg(this, "数据合法性检测", e.getMessage());
            return false;
        }
        return true;
    }

    private BankContrastVO[] getInitClerkCollate(BankContrastVO vis[])
    {
        BankContrastVO vos[] = (BankContrastVO[])null;
        try
        {
            IGLOrgBook igb = (IGLOrgBook)NCLocator.getInstance().lookup(nc.itf.dap.priv.IGLOrgBook.class.getName());
            GlorgVO vo1[] = igb.queryGlorgs(null);
            String pk_glorgs[] = (String[])null;
            if(vo1 != null)
            {
                pk_glorgs = new String[vo1.length];
                for(int i = 0; i < vo1.length; i++)
                    pk_glorgs[i] = vo1[i].getPrimaryKey();

            }
            IGLOrgBookAcc igf = (IGLOrgBookAcc)NCLocator.getInstance().lookup(nc.itf.uap.bd.multibook.IGLOrgBookAcc.class.getName());
            GlorgbookVO glvos[] = igf.getGLOrgBookVOsByPk_GlOrgs(pk_glorgs, null);
            if(glvos != null && glvos.length > 0)
            {
                vos = new BankContrastVO[glvos.length];
                for(int i = 0; i < glvos.length; i++)
                {
                    vos[i] = new BankContrastVO();
                    vos[i].setPk_system((String)hashsystem.get(getCmbSystem().getSelectedItem().toString().trim()));
                    vos[i].setPk_glorgbook(glvos[i].m_pk_glorgbook);
                    vos[i].setClerkcode("**");
                    vos[i].setPwd("**");
                    vos[i].setBankbm("##");
                    vos[i].setBankmc("##");
                    vos[i].setOrient("0");
                    vos[i].setTerminalno("##");
                    vos[i].setStatus(2);
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return vos;
    }

    private BillScrollPane getTablePanel()
    {
        if(ivjTable1 == null)
            try
            {
                ivjTable1 = new BillScrollPane();
                ivjTable1.setName("Table1");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjTable1;
    }

    public String getTitle()
    {
        return "网点柜员对照设置";
    }

    private UILabel getUILabel1()
    {
        if(ivjUILabel1 == null)
            try
            {
                ivjUILabel1 = new UILabel();
                ivjUILabel1.setName("UILabel1");
                ivjUILabel1.setText("组织机构");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUILabel1;
    }

    private UIPanel getUIPanel1()
    {
        if(ivjUIPanel1 == null)
            try
            {
                ivjUIPanel1 = new UIPanel();
                ivjUIPanel1.setName("UIPanel1");
                ivjUIPanel1.add(getUILabel1(), "North");
                ivjUIPanel1.add(getUILabel(), "Center");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIPanel1;
    }

    private void handleException(Throwable throwable)
    {
    }

    public void initData()
    {
        try
        {
            ISystemType is = (ISystemType)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISystemType.class.getName());
            SystemTypeVO systemtypevos[] = is.queryAllSystemTypeVOs();
            hashsystem = new Hashtable();
            if(systemtypevos != null && systemtypevos.length != 0)
            {
                for(int i = 0; i < systemtypevos.length; i++)
                {
                    getCmbSystem().addItem(systemtypevos[i].getSystemname());
                    hashsystem.put(systemtypevos[i].getSystemname().trim(), systemtypevos[i].getPk_system());
                }
            }
            IBankContrast bo = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
            m_vos = bo.queryBankContrastVOs("0");
            if(m_vos == null || m_vos.length == 0)
                m_vos = getInitClerkCollate(m_vos);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        setButtonState();
    }

    private void initialize()
    {
        try
        {
            setSize(495, 306);
            add(getPane());
        }
        catch(Throwable ivjExc)
        {
            handleException(ivjExc);
        }
        setButtons(m_aryButtons);
        initTable();
        initListener();
        initData();
        onRefresh();
        setButtonState();
    }

    public void initListener()
    {
        getTablePanel().addEditListener(this);
    }

    public void initTable()
    {
        String names[] = {
            "主键", "公司主键", "NC机构编码", "NC机构名称", "柜员号", "外系统机构编码", "外系统机构名称"
        };
        BillModel oModel = new BillModel();
        BillItem biBodyItems[] = new BillItem[names.length];
        for(int i = 0; i < names.length; i++)
        {
            biBodyItems[i] = new BillItem();
            biBodyItems[i].setName(names[i].toString());
            biBodyItems[i].setWidth(150);
            biBodyItems[i].setEdit(true);
            biBodyItems[i].setShow(true);
        }

        biBodyItems[0].setShow(false);
        biBodyItems[1].setShow(false);
        biBodyItems[0].setKey("pk_clerkconfig");
        biBodyItems[1].setKey("pk_glorgbook");
        biBodyItems[2].setKey("glorgbookcode");
        biBodyItems[3].setKey("glorgbookname");
        biBodyItems[4].setKey("clerkcode");
        biBodyItems[5].setKey("bankbm");
        biBodyItems[6].setKey("bankmc");
        biBodyItems[2].setEdit(false);
        biBodyItems[3].setEdit(false);
        for(int i = 0; i < biBodyItems.length; i++)
        {
            biBodyItems[i].setDataType(0);
            biBodyItems[i].setPos(1);
        }

        biBodyItems[1].setIDColName("pk_glorgbook");
        biBodyItems[2].setLoadFormula(new String[] {
            "glorgbookcode->getColValue(bd_glorgbook,glorgbookcode,pk_glorgbook,pk_glorgbook)"
        });
        biBodyItems[3].setLoadFormula(new String[] {
            "glorgbookname->getColValue(bd_glorgbook,glorgbookname,pk_glorgbook,pk_glorgbook)"
        });
        for(int i = 0; i < biBodyItems.length; i++)
            biBodyItems[i].setLength(20);

        oModel.setBodyItems(biBodyItems);
        getTablePanel().setTableModel(oModel);
        getTablePanel().setRowNOShow(true);
        getTablePanel().getTable().setSelectionMode(0);
        for(int i = 0; i < names.length; i++)
            biBodyItems[i].setEdit(false);
    }

    public static void main(String args[])
    {
        try
        {
            JFrame frame = new JFrame();
            NCBankContrastUI aClerkConfigUI = new NCBankContrastUI();
            frame.setContentPane(aClerkConfigUI);
            frame.setSize(aClerkConfigUI.getSize());
            frame.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }

            });
            frame.show();
            Insets insets = frame.getInsets();
            frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
            frame.setVisible(true);
        }
        catch(Throwable exception)
        {
            System.err.println("nc.ui.pub.ToftPanel 的 main() 中发生异常");
            exception.printStackTrace(System.out);
        }
    }

    public void onButtonClicked(ButtonObject bo)
    {
        if(bo == m_boSave)
            onSave();
        if(bo == m_boCancel)
            onCancel();
        if(bo == m_boEdit)
            onEdit();
        if(bo == m_boRefresh)
            onRefresh();
        if(bo == m_boPrint)
            onPrint();
    }

    public void onCancel()
    {
        if(getTablePanel().getTable().isEditing())
            getTablePanel().getTable().editingStopped(null);
        getTablePanel().getTableModel().resumeValue();
        getTablePanel().getTable().updateUI();
        getTablePanel().getTableModel().execLoadFormula();
        m_iState = 0;
        setButtonState();
        setCardEdit(false);
    }

    public boolean onClosing()
    {
        return true;
    }

    public void onEdit()
    {
        getTablePanel().getTableModel().setRowEditState(false);
        setCardEdit(true);
        m_iState = 1;
        setButtonState();
    }

    public void onPrint()
    {
        String m_sColumnName[] = {
            "人员成本类别编码", "人员成本类别名称", "费用承担范围", "部门承担比例(%)"
        };
        String colname[][] = new String[1][m_sColumnName.length];
        for(int i = 0; i < m_sColumnName.length; i++)
            colname[0][i] = m_sColumnName[i];

        Vector data = getTablePanel().getTableModel().getDataVector();
        Object printdata[][] = new Object[data.size()][4];
        int count = data.size();
        for(int i = 0; i < count; i++)
        {
            printdata[i][0] = ((Vector)(Vector)data.elementAt(i)).elementAt(1);
            printdata[i][1] = ((Vector)(Vector)data.elementAt(i)).elementAt(2);
            printdata[i][2] = ((Vector)(Vector)data.elementAt(i)).elementAt(3);
            printdata[i][3] = ((Vector)(Vector)data.elementAt(i)).elementAt(4);
        }

        int colwidth[] = {
            180, 180, 180, 180, 180
        };
        int alignflag[] = new int[5];
        int rowheight[] = new int[data.size() + 1];
        for(int i = 0; i < rowheight.length; i++)
            rowheight[i] = getTablePanel().getTable().getRowHeight();

        String title = "人员成本类别";
        Font font = new Font("dialog", 1, 20);
        Font font1 = new Font("dialog", 0, 12);
        UFDate date = new UFDate();
        String topstr = (new StringBuilder("制表时间： ")).append(getClientEnvironment().getBusinessDate().toString()).toString();
        String botstr = (new StringBuilder("制表人： ")).append(getClientEnvironment().getUser().getUserName()).toString();
        PrintDirectEntry print = new PrintDirectEntry();
        print.setRowHeight(rowheight);
        print.setTitle(title);
        print.setTitleFont(font);
        print.setContentFont(font1);
        print.setColNames(colname);
        print.setData(printdata);
        print.setColWidth(colwidth);
        print.setAlignFlag(alignflag);
        print.preview();
    }

    public void onRefresh()
    {
/*        if(getCmbSystem().getSelectedItem() == null)
        {
            showWarningMessage("请选择系统!");
            return;
        }*/
        IBankContrast bo = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
        try
        {
            m_vos = bo.queryBankContrastVOs("0");
        }
        catch(BusinessException e1)
        {
            e1.printStackTrace();
        }
        if(m_vos == null || m_vos.length == 0)
            m_vos = getInitClerkCollate(m_vos);
        //String pk_system = (String)hashsystem.get(getCmbSystem().getSelectedItem().toString().trim());
        BankContrastVO[] vos = null;
        Vector v = new Vector();
        for(int i = 0; i < m_vos.length; i++)
            //if(pk_system.trim().equals(m_vos[i].getPk_system().trim()))
                v.addElement(m_vos[i]);

        if(v.size() > 0)
        {
            vos = new BankContrastVO[v.size()];
            v.copyInto(vos);
        }
        BankContrastVO[] vas =null;
        try
        {
            IGLOrgBook igb = (IGLOrgBook)NCLocator.getInstance().lookup(nc.itf.dap.priv.IGLOrgBook.class.getName());
            GlorgVO vo1[] = igb.queryGlorgs(null);
            String[] pk_glorgs = null;
            if(vo1 != null)
            {
                pk_glorgs = new String[vo1.length];
                for(int i = 0; i < vo1.length; i++)
                    pk_glorgs[i] = vo1[i].getPrimaryKey();

            }
            IGLOrgBookAcc igf = (IGLOrgBookAcc)NCLocator.getInstance().lookup(nc.itf.uap.bd.multibook.IGLOrgBookAcc.class.getName());
            GlorgbookVO glvos[] = igf.getGLOrgBookVOsByPk_GlOrgs(pk_glorgs, null);
            HashMap hmcc = new HashMap();
            if(vos != null)
            {
                for(int i = 0; i < vos.length; i++)
                    hmcc.put(vos[i].getPk_glorgbook(), vos[i]);

            }
            if(glvos != null)
            {
                vas = new BankContrastVO[glvos.length];
                for(int i = 0; i < glvos.length; i++)
                    if(hmcc.containsKey(glvos[i].getPrimaryKey()))
                    {
                        vas[i] = (BankContrastVO)hmcc.get(glvos[i].getPrimaryKey());
                    } else
                    {
                        BankContrastVO svo = new BankContrastVO();
                        svo.setPk_glorgbook(glvos[i].getPrimaryKey());
                        svo.setClerkcode("**");
                        svo.setPwd("**");
                        svo.setBankbm("##");
                        svo.setBankmc("##");
                        svo.setTerminalno("##");
                        vas[i] = svo;
                    }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        getTablePanel().getTableModel().setBodyDataVO(vas);
        getTablePanel().getTableModel().updateValue();
        getTablePanel().getTableModel().execLoadFormula();
        setButtonState();
    }

    public void onSave()
    {
        BankContrastVO allVO[];
        IBankContrast bo;
        boolean flag;
        try
        {
            if(getTablePanel().getTable().isEditing())
                getTablePanel().getTable().editingStopped(new ChangeEvent(this));
            allVO = (BankContrastVO[])getTablePanel().getTableModel().getBodyValueVOs("nc.vo.bankpub.bankcontrast.BankContrastVO");
            bo = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
            if(!checkVO(allVO))
                return;
            flag = false;
	        if(m_vos != null && m_vos.length != 0)
	            if(allVO.length == m_vos.length)
	                flag = true;
	            else
	                bo.delete();
	        for(int i = 0; i < allVO.length; i++)
	        {
	            if(flag)
	                allVO[i].setPk_bankcontrast(m_vos[i].getPk_bankcontrast());
	            //allVO[i].setPk_system((String)hashsystem.get(getCmbSystem().getSelectedItem().toString().trim()));
	            allVO[i].setOrient("0");
	        }
	
	        bo.maintainBankContrast(allVO);
	        getTablePanel().getTableModel().setBodyDataVO(allVO);
	        getTablePanel().getTableModel().updateValue();
	        getTablePanel().getTableModel().execLoadFormula();
	        m_iState = 0;
	        setButtonState();
	        setCardEdit(false);
	        onRefresh();
	        return;
        }
        catch(BusinessException e)
        {
            MessageDialog.showErrorDlg(this, "保存出错", e.getMessage());
            return;
        }
        catch(Exception e)
        {
            MessageDialog.showErrorDlg(this, "保存出错", e.getMessage());
            return;
        }
    }

    public void setButtonState()
    {
        if(m_iState == 2)
        {
            m_boEdit.setEnabled(false);
            m_boSave.setEnabled(true);
            m_boCancel.setEnabled(true);
        } else
        if(m_iState == 1)
        {
            m_boEdit.setEnabled(false);
            m_boSave.setEnabled(true);
            m_boCancel.setEnabled(true);
        } else
        if(m_iState == 0)
        {
            m_boEdit.setEnabled(true);
            m_boSave.setEnabled(false);
            m_boCancel.setEnabled(false);
            if(getTablePanel().getTable().getRowCount() > 0)
                m_boEdit.setEnabled(true);
            else
                m_boEdit.setEnabled(false);
        }
        updateButtons();
    }

    public void setCardEdit(boolean b)
    {
        BillItem bi[] = getTablePanel().getTableModel().getBodyItems();
        if(b)
        {
            for(int i = 0; i < bi.length; i++)
                if(bi[i].getKey().equals("corpcode") || bi[i].getKey().equals("corpname"))
                    bi[i].setEdit(false);
                else
                    bi[i].setEdit(true);

        } else
        {
            for(int i = 0; i < bi.length; i++)
                bi[i].setEdit(false);

        }
    }

    public void valueChanged(ValueChangedEvent e)
    {
        Object a = e.getNewValue();
        System.out.println(a);
    }

    public UIPanel getPane()
    {
        if(ivjUIPanel == null)
        {
            ivjUIPanel = new UIPanel();
            ivjUIPanel.setLayout(new BorderLayout());
            ivjUIPanel.add(getNothPanel(), "North");
            ivjUIPanel.add(getTablePanel(), "Center");
        }
        return ivjUIPanel;
    }

    private UILabel getUILabel()
    {
        if(UILabel == null)
        {
            UILabel = new UILabel();
            UILabel.setText("系统");
            UILabel.setBounds(0, 0, 0, 0);
        }
        return UILabel;
    }

    private UIComboBox getCmbSystem()
    {
        if(cmbSystem == null)
        {
            cmbSystem = new UIComboBox();
            cmbSystem.setBounds(0,0,0,0);
        }
        return cmbSystem;
    }

    private UIPanel getNothPanel()
    {
        if(nothPanel == null)
        {
            nothPanel = new UIPanel();
            nothPanel.setLayout(null);
            nothPanel.setPreferredSize(new Dimension(0, 0));
            nothPanel.add(getUILabel());
            nothPanel.add(getCmbSystem());
        }
        return nothPanel;
    }

    private static final long serialVersionUID = 0x6475e274195faba8L;
    private UIRefPane ivjm_refDwbm;
    private UILabel ivjUILabel1;
    private UIPanel ivjUIPanel1;
    private UIPanel ivjUIPanel;
    private ButtonObject m_boPrint;
    private ButtonObject m_boEdit;
    private ButtonObject m_boSave;
    private ButtonObject m_boCancel;
    private ButtonObject m_boRefresh;
    private ButtonObject m_aryButtons[];
    private int m_iState;
    private BillScrollPane ivjTable1;
    private UILabel UILabel;
    private UIPanel nothPanel;
    private UIComboBox cmbSystem;
    private BankContrastVO m_vos[];
    private Hashtable hashsystem;
        
}