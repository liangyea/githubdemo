// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 11:13:12
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   CorpChooseDlg.java

package nc.ui.bankpub.ncsubject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nc.bs.framework.common.NCLocator;
import nc.itf.dap.priv.IGLOrgBook;
import nc.itf.uap.bd.multibook.IGLOrgBookAcc;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIListToList;
import nc.ui.pub.beans.UIPanel;
import nc.ui.sm.user.UserBO_Client;
import nc.vo.bankpub.pub.BankCorpVO;
import nc.vo.bd.b54.GlorgVO;
import nc.vo.bd.b54.GlorgbookVO;

// Referenced classes of package nc.ui.bankpub.ncsubject:
//            ICollectCorpsQuanChangedEvent, ICollectCorpQuanListener

public class CorpChooseDlg extends UIDialog
    implements ActionListener, ICollectCorpsQuanChangedEvent
{

    public CorpChooseDlg()
    {
        ivjUIBtnCancel = null;
        ivjUIBtnOK = null;
        ivjUIDialogContentPane = null;
        ivjUIList2ListCorp = null;
        ivjUIPanelBtn = null;
        m_listener = null;
        initialize();
    }

    public CorpChooseDlg(Container parent)
    {
        super(parent);
        ivjUIBtnCancel = null;
        ivjUIBtnOK = null;
        ivjUIDialogContentPane = null;
        ivjUIList2ListCorp = null;
        ivjUIPanelBtn = null;
        m_listener = null;
        initialize();
    }

    public CorpChooseDlg(Container parent, String title)
    {
        super(parent, title);
        ivjUIBtnCancel = null;
        ivjUIBtnOK = null;
        ivjUIDialogContentPane = null;
        ivjUIList2ListCorp = null;
        ivjUIPanelBtn = null;
        m_listener = null;
    }

    public CorpChooseDlg(Frame owner)
    {
        super(owner);
        ivjUIBtnCancel = null;
        ivjUIBtnOK = null;
        ivjUIDialogContentPane = null;
        ivjUIList2ListCorp = null;
        ivjUIPanelBtn = null;
        m_listener = null;
    }

    public CorpChooseDlg(Frame owner, String title)
    {
        super(owner, title);
        ivjUIBtnCancel = null;
        ivjUIBtnOK = null;
        ivjUIDialogContentPane = null;
        ivjUIList2ListCorp = null;
        ivjUIPanelBtn = null;
        m_listener = null;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == getUIBtnOK())
            onOK();
        else
        if(e.getSource() == getUIBtnCancel())
            onAbort();
    }

    public void addListener(ICollectCorpQuanListener listener)
    {
        m_listener = listener;
    }

    private BankCorpVO[] getAllCorps()
    {
        BankCorpVO result[] = new BankCorpVO[0];
        try
        {
            String pk_user = ClientEnvironment.getInstance().getUser().getPrimaryKey();
            nc.vo.bd.CorpVO corps[] = UserBO_Client.queryAllCorpsByUserPK(pk_user);
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
            GlorgbookVO vo[] = igf.getGLOrgBookVOsByPk_GlOrgs(pk_glorgs, null);
            if(vo == null || vo.length == 0)
            {
                result = (BankCorpVO[])null;
            } else
            {
                result = new BankCorpVO[vo.length];
                for(int i = 0; i < vo.length; i++)
                {
                    result[i] = new BankCorpVO();
                    result[i].setPk_glorgbook(vo[i].getPrimaryKey());
                    result[i].setUnitcode(vo[i].getGlorgbookcode());
                    result[i].setUnitname(vo[i].getGlorgbookname());
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public BankCorpVO[] getCorps()
    {
        BankCorpVO result[] = (BankCorpVO[])null;
        Object objects[] = getUIList2ListCorp().getRightData() != null ? getUIList2ListCorp().getRightData() : new Object[0];
        result = new BankCorpVO[objects.length];
        int i = 0;
        for(int count = objects.length; i < count; i++)
            result[i] = (BankCorpVO)objects[i];

        return result;
    }

    private UIButton getUIBtnCancel()
    {
        if(ivjUIBtnCancel == null)
            try
            {
                ivjUIBtnCancel = new UIButton();
                ivjUIBtnCancel.setName("UIBtnCancel");
                ivjUIBtnCancel.setText("取消");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIBtnCancel;
    }

    private UIButton getUIBtnOK()
    {
        if(ivjUIBtnOK == null)
            try
            {
                ivjUIBtnOK = new UIButton();
                ivjUIBtnOK.setName("UIBtnOK");
                ivjUIBtnOK.setText("确定");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIBtnOK;
    }

    private JPanel getUIDialogContentPane()
    {
        if(ivjUIDialogContentPane == null)
            try
            {
                ivjUIDialogContentPane = new JPanel();
                ivjUIDialogContentPane.setName("UIDialogContentPane");
                ivjUIDialogContentPane.setLayout(new BorderLayout());
                getUIDialogContentPane().add(getUIList2ListCorp(), "Center");
                getUIDialogContentPane().add(getUIPanelBtn(), "South");
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIDialogContentPane;
    }

    private UIListToList getUIList2ListCorp()
    {
        if(ivjUIList2ListCorp == null)
            try
            {
                ivjUIList2ListCorp = new UIListToList();
                ivjUIList2ListCorp.setName("UIList2ListCorp");
                ivjUIList2ListCorp.setDisplayTitle(true);
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIList2ListCorp;
    }

    private UIPanel getUIPanelBtn()
    {
        if(ivjUIPanelBtn == null)
            try
            {
                ivjUIPanelBtn = new UIPanel();
                ivjUIPanelBtn.setName("UIPanelBtn");
                ivjUIPanelBtn.setPreferredSize(new Dimension(0, 35));
                ivjUIPanelBtn.setLayout(new BoxLayout(getUIPanelBtn(), 0));
                getUIPanelBtn().add(Box.createHorizontalGlue());
                getUIPanelBtn().add(getUIBtnOK(), getUIBtnOK().getName());
                getUIPanelBtn().add(Box.createHorizontalStrut(10));
                getUIPanelBtn().add(getUIBtnCancel(), getUIBtnCancel().getName());
                getUIPanelBtn().add(Box.createHorizontalGlue());
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUIPanelBtn;
    }

    private void handleException(Throwable exception)
    {
        System.out.println("--------- 未捕捉到的异常 ---------");
        exception.printStackTrace(System.out);
    }

    private void init()
    {
        setTitle("选择帐簿");
        getUIList2ListCorp().setLeftText("待选帐簿");
        getUIList2ListCorp().setRightText("已选帐簿");
        getUIList2ListCorp().setLeftData(getAllCorps());
        initListener();
    }

    private void initialize()
    {
        try
        {
            setName("CorpChooseDlg");
            setDefaultCloseOperation(2);
            setSize(544, 352);
            setResizable(false);
            setContentPane(getUIDialogContentPane());
        }
        catch(Throwable ivjExc)
        {
            handleException(ivjExc);
        }
        init();
    }

    private void initListener()
    {
        getUIBtnOK().addActionListener(this);
        getUIBtnCancel().addActionListener(this);
    }

    public static void main(String args[])
    {
        try
        {
            CorpChooseDlg aCorpChooseDlg = new CorpChooseDlg();
            aCorpChooseDlg.setModal(true);
            aCorpChooseDlg.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }

            });
            aCorpChooseDlg.show();
            Insets insets = aCorpChooseDlg.getInsets();
            aCorpChooseDlg.setSize(aCorpChooseDlg.getWidth() + insets.left + insets.right, aCorpChooseDlg.getHeight() + insets.top + insets.bottom);
            aCorpChooseDlg.setVisible(true);
        }
        catch(Throwable exception)
        {
            System.err.println("nc.ui.pub.beans.UIDialog 的 main() 中发生异常");
            exception.printStackTrace(System.out);
        }
    }

    private void onAbort()
    {
        closeCancel();
    }

    private void onOK()
    {
        if(m_listener != null)
            m_listener.changeCorps(this);
        closeOK();
    }

    private UIButton ivjUIBtnCancel;
    private UIButton ivjUIBtnOK;
    private JPanel ivjUIDialogContentPane;
    private UIListToList ivjUIList2ListCorp;
    private UIPanel ivjUIPanelBtn;
    private static final String TITLE_LEFT = "待选帐簿";
    private static final String TITLE_RIGHT = "已选帐簿";
    private ICollectCorpQuanListener m_listener;
    private static final String DLG_TITLE = "选择帐簿";
}