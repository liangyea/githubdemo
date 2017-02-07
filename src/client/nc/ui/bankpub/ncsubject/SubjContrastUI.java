// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 9:48:17
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   SubjContrastUI.java

package nc.ui.bankpub.ncsubject;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.bankpub.pub.ISubjContrast;
import nc.itf.doc.api.Accsubjdoc;
import nc.ui.bankpub.ncsubject.ass.SubjAssConstrastDataModel;
import nc.ui.bankpub.ncsubject.ass.SubjAssConstrastEditDlg;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.bd.printadapter.PrintManager;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.print.PrintDirectEntry;
import nc.ui.tools.actualize.ExcelFileFilter;
import nc.vo.bankpub.cache.BDAccessor;
import nc.vo.bankpub.gl.AssVOs;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastHelper;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastMap;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO_OneSubj;
import nc.vo.bankpub.pub.BankCorpVO;
import nc.vo.bankpub.pub.FileReader;
import nc.vo.bankpub.subjcontrast.NCSubjContrastVO;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;

// Referenced classes of package nc.ui.bankpub.ncsubject:
//            ColDisTabModel, CorpChooseDlg

public class SubjContrastUI extends ToftPanel
    implements ListSelectionListener
{

    private SubjAssConstrastDataModel getDataModel(String pk_accsubj)
        throws BusinessException
    {
        SubjAssConstrastDataModel dataModel = (SubjAssConstrastDataModel)dataModelMap.get(pk_accsubj);
        if(dataModel == null)
        {
            dataModel = new SubjAssConstrastDataModel();
            dataModel.setPk_accsubj(pk_accsubj);
            SubjAssConstrastVO_OneSubj subjAssVos = assMap.get(pk_accsubj);
            dataModel.addAssConstrast(subjAssVos.toArray());
            nc.vo.glcom.ass.AssVO assVos[] = SubjAssConstrastHelper.getAssVosBy(pk_accsubj);
            AssVOs assVos1 = new AssVOs();
            assVos1.add(assVos);
            dataModel.setAssVos(assVos1);
            dataModelMap.put(pk_accsubj, dataModel);
        }
        return dataModel;
    }

    private SubjAssConstrastEditDlg getAssConstrastDlg()
    {
        assConstrastDlg = new SubjAssConstrastEditDlg(this, "辅助项对照");
        return assConstrastDlg;
    }

    public SubjContrastUI()
    {
        ivjUITablePane1 = null;
        m_bnCopy = new ButtonObject("复制", "复制", 2);
        m_bnUpdate = new ButtonObject("修改", "修改", 2);
        m_bnAssConstrast = new ButtonObject("辅助项对照", "辅助项对照", 2);
        m_bnSave = new ButtonObject("保存", "保存", 2);
        m_bnCancel = new ButtonObject("取消", "取消", 2);
        m_bnAdd = new ButtonObject("增加", "增加", 2);
        m_bnDel = new ButtonObject("删除", "删除", 2);
        m_bnRefresh = new ButtonObject("刷新", "刷新", 2);
        m_bnImpdata = new ButtonObject("导入数据", "导入数据", 2);
        m_bnPrint = new ButtonObject("打印", "打印", 2);
        m_bnsAll = (new ButtonObject[] {
            m_bnUpdate, m_bnAssConstrast, m_bnSave, m_bnCancel, m_bnRefresh, m_bnImpdata, m_bnCopy, m_bnPrint
        });
        m_bnNormal = (new ButtonObject[] {
            m_bnUpdate, m_bnSave, m_bnCancel, m_bnRefresh, m_bnCopy
        });
        m_tm = new ColDisTabModel();
        m_voResult = null;
        m_voAccsubj = null;
        m_htAccda = null;
        gllable = null;
        CorpRef = null;
        cmbIsAcount = null;
        assMap = new SubjAssConstrastMap();
        dataModelMap = new HashMap();
        initialize();
    }

    public String checkValid()
    {
        return null;
    }

    public String getTitle()
    {
        return "联机科目对照表";
    }

    public UITablePane getUITablePane1()
    {
        if(ivjUITablePane1 == null)
            try
            {
                ivjUITablePane1 = new UITablePane();
                ivjUITablePane1.setName("UITablePane1");
                ivjUITablePane1.setBounds(10, 44, 752, 364);
                ivjUITablePane1.getTable().getSelectionModel().addListSelectionListener(this);
            }
            catch(Throwable ivjExc)
            {
                handleException(ivjExc);
            }
        return ivjUITablePane1;
    }

    public void handleException(Throwable exception)
    {
        System.out.println("--------- 未捕捉到的异常 ---------");
        exception.printStackTrace(System.out);
        showWarningMessage(exception.getMessage());
    }

    public void initialize()
    {
        try
        {
            gllable = new UILabel();
            gllable.setBounds(new Rectangle(10, 8, 89, 21));
            gllable.setText("会计主体帐簿：");
            setName("SubjContrastUI");
            setLayout(null);
            setSize(774, 419);
            add(getUITablePane1(), getUITablePane1().getName());
            add(gllable, null);
            add(getGlorgbookRef(), null);
        }
        catch(Throwable ivjExc)
        {
            handleException(ivjExc);
        }
        m_bnRefresh.setEnabled(true);
        m_bnUpdate.setEnabled(false);
        m_bnAssConstrast.setEnabled(false);
        m_bnSave.setEnabled(false);
        m_bnCancel.setEnabled(false);
        setButtons(m_bnsAll);
        updateButtons();
        getUITablePane1().getTable().setModel(m_tm);
        getUITablePane1().getTable().setAutoResizeMode(0);
    }

    public void initializeData()
        throws Exception
    {
        m_tm.setColIdentifier(m_sTmheader);
        getUITablePane1().getTable().getColumnModel().getColumn(5).setCellEditor(new UIRefCellEditor(getCmbIsAcount()));
        String pk_glorgbook = getGlorgbookRef().getRefPK();
        if(pk_glorgbook == null || pk_glorgbook.trim().equals(""))
        {
            showWarningMessage("账簿不能为空!");
            return;
        } else
        {
            ISubjContrast it = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
            m_voResult = it.initSubjContrast(pk_glorgbook);
            resetTable();
            return;
        }
    }

    public static void main(String args[])
    {
        try
        {
            JFrame frame = new JFrame();
            SubjContrastUI aSubjContrastUI = new SubjContrastUI();
            frame.setContentPane(aSubjContrastUI);
            frame.setSize(aSubjContrastUI.getSize());
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

    public void onBnAdd()
        throws Exception
    {
        int count = m_tm.getRowCount();
        Object aobj[] = new Object[7];
        aobj[0] = new Integer(count + 1);
        m_tm.addRow(aobj);
        m_tm.fireTableDataChanged();
    }

    public void onBnCancel()
        throws Exception
    {
        onBnRefresh();
        m_bnRefresh.setEnabled(true);
        m_bnUpdate.setEnabled(true);
        m_bnSave.setEnabled(false);
        m_bnCancel.setEnabled(false);
        updateButtons();
        m_tm.setEditable(false);
    }

    private NCSubjContrastVO getSelectResult()
    {
        int i = getUITablePane1().getTable().getSelectedRow();
        if(i > -1)
            return m_voResult[i];
        else
            return null;
    }

    private String getSelectPkaccsubj()
    {
        int i = getUITablePane1().getTable().getSelectedRow();
        if(i > -1)
            return m_voResult[i].getPk_accsubj();
        else
            return null;
    }

    private boolean hasAss()
    {
        NCSubjContrastVO vo = getSelectResult();
        if(vo == null)
            return false;
        nc.vo.glcom.ass.AssVO assVos[] = SubjAssConstrastHelper.getAssVosBy(vo.getPk_accsubj());
        return assVos != null && assVos.length > 0;
    }

    public void onBnCopy()
    throws Exception
    {
	    CorpChooseDlg dlg = new CorpChooseDlg(this);
	    int ire = dlg.showModal();
	    if(ire != 1 && ire != 4)
	        return;
	    String standard = getGlorgbookRef().getRefPK();
	    if(standard == null || standard.trim().equals(""))
	        standard = "0001";
	    BankCorpVO tar[] = dlg.getCorps();
	    if(tar == null || tar.length == 0)
	        return;
	    ire = showYesNoMessage((new StringBuilder("将要把公司")).append(standard).append("所有科目对照表复制到选定公司，是否确定？").toString());
	    if(ire != 1 && ire != 4)
	        return;
	    String star[] = new String[tar.length];
	    for(int i = 0; i < star.length; i++)
	        star[i] = tar[i].getPk_glorgbook();
	    
	    ISubjContrast it = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
	    it.copySubjcontrast(standard, star);
    }


    public void onBnDel()
        throws Exception
    {
    }

    private String getPk_accsubjBy(String pk_glorgbook, String subjcode)
        throws BusinessException
    {
        String pk_accsubj = "";
        IBDAccessor subjAccessor = BDAccessor.getBDAccessor("00010000000000000034", "2", pk_glorgbook);
        BddataVO subjVo = subjAccessor.getDocByCode(subjcode);
        if(subjVo != null)
            pk_accsubj = subjVo.getPk();
        else
            throw new BusinessException((new StringBuilder("科目编码 [")).append(subjcode).append("] 在系统中没有找到对应记录！请追查待到如数据文件，修改后再进行导入！").toString());
        return pk_accsubj;
    }

    private String openExcel()
    {
        UIFileChooser fc = new UIFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setFileFilter(new ExcelFileFilter());
        if(fc.showOpenDialog(this) == 0)
        {
            File f = fc.getSelectedFile();
            return f.getAbsolutePath();
        } else
        {
            return null;
        }
    }

    public void onBnImp()
        throws Exception
    {
        String corp = getGlorgbookRef().getRefPK();
        if(corp == null || corp.trim().equals(""))
        {
            showWarningMessage("请选择会计主题账簿!");
            return;
        }
        String filename = openExcel();
        if(filename == null)
            return;
        FileReader reader = new FileReader();
        reader.setFileName(filename);
        reader.setColCounts(5);
        reader.readExcel();
        String sre[][] = reader.getResult();
        if(sre == null || sre.length == 0)
            throw new Exception("Excel文件没有数据！");
        for(int i = 0; i < sre.length; i++)
            if(sre[i][0] == null || sre[i][0].trim().equals(""))
                throw new Exception((new StringBuilder("Excel文件的第一列不能为空!\\n文件的第")).append(i).append("为空").toString());

        HashMap hm = getExcelData(sre, corp);
        ISubjContrast it = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
        NCSubjContrastVO ncscvo[] = it.querySubjContrast(corp);
        for(int j = 0; j < ncscvo.length; j++)
        {
            String pk_accsubj = ncscvo[j].getPk_accsubj();
            String row[] = (String[])hm.get(pk_accsubj);
            if(row != null)
            {
                if(row[1] != null && row[1].trim().length() > 0)
                    ncscvo[j].setIstrans(row[1]);
                if(row[2] != null && row[2].trim().length() > 0)
                    ncscvo[j].setBank_kmbm(row[2]);
                if(row[3] != null && row[3].trim().length() > 0)
                    ncscvo[j].setIsAccount(row[3]);
                if(row[4] != null && row[4].trim().length() > 0)
                    ncscvo[j].setBalbank_kmbm(row[4]);
            }
        }

        it.updateSubjContrast(ncscvo);
        onBnRefresh();
    }

    private HashMap getExcelData(String sre[][], String pk_glorgbook)
    {
        HashMap hm = new HashMap();
        try
        {
            IBDAccessor subjAccessor = BDAccessor.getBDAccessor("00010000000000000034", "2", pk_glorgbook);
            int lent = sre != null ? sre.length : 0;
            for(int i = 0; i < lent; i++)
            {
                String pk_accsubj = "";
                String subjcode = sre[i][0];
                BddataVO subjVo = subjAccessor.getDocByCode(subjcode.trim());
                if(subjVo == null)
                	continue;
                    //throw new BusinessException((new StringBuilder("科目编码 [")).append(subjcode).append("] 在系统中没有找到对应记录！请追查待到如数据文件，修改后再进行导入！").toString());
                String row[] = sre[i];
                pk_accsubj = subjVo.getPk();
                hm.put(pk_accsubj, row);
            }

        }
        catch(BusinessException e)
        {
            e.printStackTrace();
            Logger.error(e.getMessage(), e);
        }
        return hm;
    }

    public void onBnRefresh()
        throws Exception
    {
        m_tm.setData(null);
        String glorgbook = getGlorgbookRef().getRefPK();
        if(glorgbook == null || glorgbook.trim().equals(""))
        {
            showWarningMessage("请选择账簿!");
            return;
        }
        ISubjContrast it = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
        m_voResult = it.querySubjContrast(glorgbook);
        Accsubjdoc itf = (Accsubjdoc)NCLocator.getInstance().lookup(nc.itf.doc.api.Accsubjdoc.class.getName());
        m_voAccsubj = itf.queryAccsubjVOs(glorgbook, null, Boolean.valueOf(false));
        m_htAccda = new Hashtable();
        if(m_voAccsubj != null && m_voAccsubj.length != 0)
        {
            for(int i = 0; i < m_voAccsubj.length; i++)
                m_htAccda.put(m_voAccsubj[i].getPk_accsubj(), m_voAccsubj[i]);

            if(m_voResult == null || m_voResult.length == 0)
                initializeData();
            else
                resetTable();
        } else
        {
            throw new Exception("查询科目档案为空！");
        }
        m_bnUpdate.setEnabled(true);
        updateButtons();
    }

    public void onBnSave()
        throws Exception
    {
        try
        {
            getUITablePane1().getTable().getCellEditor().stopCellEditing();
        }
        catch(Exception exception) { }
        String strmsg = checkValid();
        if(strmsg != null)
            throw new Exception(strmsg);
        int irow = getUITablePane1().getTable().getRowCount();
        for(int i = 0; i < irow; i++)
        {
            m_voResult[i].setIstrans(m_tm.getValueAt(i, 3).equals(new Boolean(true)) ? "Y" : "N");
            m_voResult[i].setBank_kmbm(m_tm.getValueAt(i, 4).toString());
            m_voResult[i].setIsAccount(m_tm.getValueAt(i, 5).equals("是") ? "Y" : "N");
            m_voResult[i].setBalbank_kmbm(m_tm.getValueAt(i, 6).equals("是") ? "Y" : "N");
        }

        ISubjContrast it = (ISubjContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISubjContrast.class.getName());
        it.updateSubjContrast(m_voResult);
        //NcSubjContrastCache. getInstance().initCache();
        m_bnRefresh.setEnabled(true);
        m_bnUpdate.setEnabled(true);
        m_bnSave.setEnabled(false);
        m_bnCancel.setEnabled(false);
        updateButtons();
        m_tm.setEditable(false);
    }

    public void onBnUpdate()
        throws Exception
    {
        m_tm.setEditable(true);
        m_tm.setColEditable(new boolean[] {
            false, false, false, true, true, true, true
        });
        m_bnRefresh.setEnabled(false);
        m_bnUpdate.setEnabled(false);
        m_bnSave.setEnabled(true);
        m_bnCancel.setEnabled(true);
        updateButtons();
    }

    public void onButtonClicked(ButtonObject bo)
    {
    	try
        {
	        if(bo == m_bnAdd)
	        {
	            onBnAdd();
	            return;
	        }
	        if(bo == m_bnDel)
	        {
	            onBnDel();
	            return;
	        }
	        if(bo == m_bnRefresh)
	        {
	            onBnRefresh();
	            return;
	        }
	        if(bo == m_bnSave)
	        {
	            onBnSave();
	            return;
	        }
	        if(bo == m_bnCancel)
	        {
	            onBnCancel();
	            return;
	        }
	        if(bo == m_bnUpdate)
	        {
	            onBnUpdate();
	            return;
	        }
	        if(bo == m_bnImpdata)
	        {
	            onBnImp();
	            return;
	        }
	        if(bo == m_bnCopy)
	        {
	            onBnCopy();
	            return;
	        }
	        if(bo == m_bnPrint)
	        {
	            onBnPrint();
	            return;
	        }
	        
            if(bo == m_bnAssConstrast)
            {
                onAssConstrast();
                return;
            }
        }
        catch(Exception e)
        {
            handleException(e);
        }
        return;
    }

    public void onBnPrint()
        throws Exception
    {
        PrintDirectEntry print = PrintManager.getDirectPrinter(ivjUITablePane1.getTable());
        print.setTitle(getTitle());
        print.preview();
    }

    public void resetTable()
        throws Exception
    {
        m_tm.setColIdentifier(m_sTmheader);
        getUITablePane1().getTable().getColumnModel().getColumn(5).setCellEditor(new UIRefCellEditor(getCmbIsAcount()));
        getUITablePane1().getTable().getColumnModel().getColumn(6).setCellEditor(new UIRefCellEditor(getCmbIsAcount()));
        if(m_voResult == null || m_voResult.length == 0)
        {
            m_tm.setData(null);
            return;
        }
        int icols = 7;
        Object data[][] = new Object[m_voResult.length][icols - 1];
        String ncpk = null;
        AccsubjVO vo = null;
        for(int i = 0; i < m_voResult.length; i++)
        {
            ncpk = m_voResult[i].getPk_accsubj();
            vo = (AccsubjVO)m_htAccda.get(ncpk);
            data[i][0] = m_voResult[i].nckmbm;
            data[i][1] = m_voResult[i].nckmmc;
            data[i][2] = m_voResult[i].getIstrans().equals("Y") ? ((Object) (new Boolean(true))) : ((Object) (new Boolean(false)));
            data[i][3] = m_voResult[i].getBank_kmbm();
            data[i][4] = m_voResult[i].getIsAccount().equals("Y") ? "是" : "否";
            data[i][5] = m_voResult[i].getBalbank_kmbm().equals("Y") ? "是" : "否";
        }

        Object data2[][] = new Object[data.length][icols];
        for(int i = 0; i < data.length; i++)
        {
            data2[i][0] = Integer.toString(i + 1);
            for(int j = 0; j < data[i].length; j++)
                data2[i][j + 1] = data[i][j];

        }

        m_tm.setData(data2);
        m_tm.setEditable(false);
    }

    public void setBounds(int x, int y, int w, int h)
    {
        super.setBounds(x, y, w, h);
        getUITablePane1().setBounds(10, 44, w - 10 - 5, h - 44 - 5);
    }

    public UIRefPane getGlorgbookRef()
    {
        if(CorpRef == null)
        {
            CorpRef = new UIRefPane();
            CorpRef.setBounds(new Rectangle(100, 8, 147, 20));
            CorpRef.setRefNodeName(RefNodeNameConst.GLORGBOOK);
        }
        return CorpRef;
    }

    private UIComboBox getCmbIsAcount()
    {
        if(cmbIsAcount == null)
        {
            cmbIsAcount = new UIComboBox();
            cmbIsAcount.addItem("是");
            cmbIsAcount.addItem("否");
        }
        return cmbIsAcount;
    }

    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == getUITablePane1().getTable().getSelectionModel())
        {//tiancy1
            m_bnAssConstrast.setEnabled(hasAss() && m_bnUpdate.isEnabled());
            updateButton(m_bnAssConstrast);
        }
    }

    public void onAssConstrast()
    {
        String pk_accsubj = getSelectPkaccsubj();
        if(pk_accsubj == null)
            return;
        nc.vo.glcom.ass.AssVO assVos[] = SubjAssConstrastHelper.getAssVosBy(pk_accsubj);
        ///tiancy1
        
        if(assVos == null || assVos.length == 0)
            return;
        try
        {
            SubjAssConstrastEditDlg dlg = getAssConstrastDlg();
            dlg.setDataModel(getDataModel(pk_accsubj));
            dlg.showModal();
        }
        catch(Exception e)
        {
            showErrorMessage(e.getMessage());
            Debug.error(e.getMessage(), e);
            Logger.error(e.getMessage(), e);
        }
    }

    private static final long serialVersionUID = 0xf0a8bdce7553aabeL;
    public UITablePane ivjUITablePane1;
    public ButtonObject m_bnCopy;
    public ButtonObject m_bnUpdate;
    private ButtonObject m_bnAssConstrast;
    public ButtonObject m_bnSave;
    public ButtonObject m_bnCancel;
    public ButtonObject m_bnAdd;
    public ButtonObject m_bnDel;
    public ButtonObject m_bnRefresh;
    public ButtonObject m_bnImpdata;
    public ButtonObject m_bnPrint;
    public ButtonObject m_bnsAll[];
    public ButtonObject m_bnNormal[];
    public ColDisTabModel m_tm;
    public String m_sTmheader[] = {
        "序号", "NC科目编码", "NC科目名称", "是否上传核心系统", "账户序号", "是否对账", "是否双向账号"
    };
    public String m_sTmheaderfull[] = {
        "序号", "NC系统/科目编码", "NC系统/科目名称", "大机系统/科目编码", "大机系统/账户序号", "是否对账"
    };
    public NCSubjContrastVO m_voResult[];
    public AccsubjVO m_voAccsubj[];
    public Hashtable m_htAccda;
    private UILabel gllable;
    private UIRefPane CorpRef;
    private UIComboBox cmbIsAcount;
    private SubjAssConstrastMap assMap;
    private Map dataModelMap;
    private SubjAssConstrastEditDlg assConstrastDlg;
}