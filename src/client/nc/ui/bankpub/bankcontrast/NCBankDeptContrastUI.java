// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 2009-12-30 22:55:48
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   NCBankDeptContrastUI.java

package nc.ui.bankpub.bankcontrast;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankContrast;
import nc.itf.bankpub.pub.IPubTools;
import nc.itf.bankpub.pub.ISystemType;
import nc.ui.bankpub.dealcode.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.bill.BillEditEvent;
import nc.vo.bankpub.bankcontrast.BankContrastVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.CorpVO;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.pub.BusinessException;

public class NCBankDeptContrastUI extends ToftPanel
    implements ValueChangedListener, ActionListener
{

    public String getTitle()
    {
        return "机构网点对照";
    }

    public NCBankDeptContrastUI()
    {
        btn = null;
        paneContent = null;
        paneTable = null;
        table = null;
        tablemodel = null;
        refGlorgbook = null;
        refDept = null;
        paneTop = null;
        labsystem = null;
        currentRow = 0;
        state = 0;
        hashcorp = null;
        hashdept = null;
        editIndexs = null;
        initBankContrastvos = null;
        tempBankContrastvos = null;
        corpmdy = false;
        deptmdy = false;
        cmbSystem = null;
        initialize();
    }

    public void onButtonClicked(ButtonObject bo)
    {
    	try
        {
	        String name;
	        name = bo.getName();
	        if(name.equals("保存"))
	        {
	            onSave();
	            return;
	        }
	        if(name.equals("增行"))
	        {
	            onAddRow();
	            return;
	        }
	        if(name.equals("插入"))
	        {
	            onInsert();
	            return;
	        }
	        if(name.equals("修改"))
	        {
	            onModify();
	            return;
	        }
	        if(name.equals("删除"))
	        {
	            onDelete();
	            return;
	        }
	        if(name.equals("刷新"))
	        {
	            onRefresh();
	            return;
	        }
	        if(name.equals("取消"))
	        {
	            onCance();
	            return;
	        }
	        if(name.equals("打印"))
            {
                onPrint();
                return;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            showErrorMessage((new StringBuilder("执行")).append(bo.getName()).append("失败：").append(ex.getMessage()).toString());
        }
        return;
    }

    private void initialize()
    {
        setSize(495, 306);
        add(getContentPane());
        initCrols();
        initTable();
        initDatas();
    }

    private void initCrols()
    {
        setButtonName(strbtn);
        onState(0);
    }

    private void initTable()
    {
        int wids[] = new int[getTable().getModel().getColumnCount()];
        for(int i = 0; i < getTable().getModel().getColumnCount(); i++)
            wids[i] = 88;

        getTable().setColumnWidth(wids);
    }

    private void initDatas()
    {
        hashcorp = new Hashtable();
        hashdept = new Hashtable();
        try
        {
            ISystemType isy = (ISystemType)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.ISystemType.class.getName());
            SystemTypeVO systemtypevos[] = (SystemTypeVO[])null;
            try
            {
                systemtypevos = isy.queryAllSystemTypeVOs();
            }
            catch(BusinessException e1)
            {
                e1.printStackTrace();
            }
            getCmbSystem().removeActionListener(this);
            getCmbSystem().removeAllItems();
            if(systemtypevos != null && systemtypevos.length != 0)
            {
                for(int i = 0; i < systemtypevos.length; i++)
                    getCmbSystem().addItem(systemtypevos[i]);

            }
            getCmbSystem().addActionListener(this);
            onRefresh();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setButtonName(String name[])
    {
        btn = new ButtonObject[name.length];
        for(int i = 0; i < name.length; i++)
            btn[i] = new ButtonObject(name[i], name[i], 2);

        setButtons(btn);
        updateButtons();
    }

    private void onState(int state)
    {
        boolean zbtnEnable[] = {
            false, true, true, true, true, true, false, true
        };
        if(state == 0)
        {
            for(int i = 0; i < btn.length; i++)
                btn[i].setEnabled(zbtnEnable[i]);

            updateButtons();
        }
        if(state == 1)
        {
            for(int i = 0; i < btn.length; i++)
                btn[i].setEnabled(!zbtnEnable[i]);

            updateButtons();
        }
        if(state == 2)
        {
            for(int i = 0; i < btn.length; i++)
                btn[i].setEnabled(!zbtnEnable[i]);

            updateButtons();
        }
        if(state == 3)
        {
            for(int i = 0; i < btn.length; i++)
                btn[i].setEnabled(!zbtnEnable[i]);

            updateButtons();
        }
    }
    
    private void onSave(){
    	
    }
    
    /*
    private void onSave()
        throws Exception
    {
    
    	String pk_system;
        //try
        //{
            getTable().getCellEditor().stopCellEditing();
        //}
        //catch(Exception exception) { }
        SystemTypeVO svo = (SystemTypeVO)getCmbSystem().getSelectedItem();
        pk_system = svo.getPk_system();
        
        //int i;
        if(state != 1 || editIndexs == null || editIndexs.length == 0)
            //break MISSING_BLOCK_LABEL_573;
        	return;
        //i = 0;
        tempBankContrastvos = new BankContrastVO[editIndexs.length];
        for(int i=0; i<editIndexs.length; i++){
        	if(getTableModel().getValueAt(editIndexs[i].intValue(), 0).equals(""))
            {
                showWarningMessage("请选择账簿!");
                return;
            }
            int row;
            //int i;
            BankContrastVO vo = null;
            int n;
            //int i;
            int j;
            try
            {
                if(getTableModel().getValueAt(editIndexs[i].intValue(), 2).equals(""))
                {
                    showWarningMessage("请填写核算机构编码!");
                    return;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
            if(getTableModel().getValueAt(editIndexs[i].intValue(), 3).equals(""))
            {
                showWarningMessage("请填写核算机构名称!");
                return;
            }
            if(getTableModel().getValueAt(editIndexs[i].intValue(), 6).equals(""))
            {
                showWarningMessage("请填写柜员号!");
                return;
            }
            //i++;
            tempBankContrastvos[i] = new BankContrastVO();
            tempBankContrastvos[i].setPk_glorgbook((String)hashcorp.get(editIndexs[i]));
            tempBankContrastvos[i].setPk_deptdoc((String)hashdept.get(editIndexs[i]));
            tempBankContrastvos[i].setBankbm(getTableModel().getValueAt(editIndexs[i].intValue(), 2).toString());
            tempBankContrastvos[i].setBankmc(getTableModel().getValueAt(editIndexs[i].intValue(), 3).toString());
            tempBankContrastvos[i].setClerkcode(getTableModel().getValueAt(editIndexs[i].intValue(), 6).toString());
            tempBankContrastvos[i].setPk_system(pk_system);
            tempBankContrastvos[i].setOrient("0");
            
//    _L1:
            //if(i < editIndexs.length){
            	
            //}
            
            
            
        }
        
        
        	//goto _L3;
        	//else goto _L2
//_L2:
        //char s[] = null;
        //tempBankContrastvos = new BankContrastVO[editIndexs.length];
        //i = 0;
          //goto _L4
//_L12:
        //tempBankContrastvos[i] = new BankContrastVO();
        //tempBankContrastvos[i].setPk_glorgbook((String)hashcorp.get(editIndexs[i]));
        //tempBankContrastvos[i].setPk_deptdoc((String)hashdept.get(editIndexs[i]));
        //tempBankContrastvos[i].setBankbm(getTableModel().getValueAt(editIndexs[i].intValue(), 2).toString());
        //tempBankContrastvos[i].setBankmc(getTableModel().getValueAt(editIndexs[i].intValue(), 3).toString());
        //tempBankContrastvos[i].setClerkcode(getTableModel().getValueAt(editIndexs[i].intValue(), 6).toString());
        //tempBankContrastvos[i].setPk_system(pk_system);
        //tempBankContrastvos[i].setOrient("0");
        if(initBankContrastvos == null || initBankContrastvos.length == 0)
            continue;  //Loop/switch isn't completed 
        n = 0;
          //goto _L5
//_L10:
        j = 0;
          //goto _L6
//_L8:
        if(initBankContrastvos[n].getPk_deptdoc().equalsIgnoreCase(tempBankContrastvos[j].getPk_deptdoc()))
        {
            getTableModel().setValueAt("", editIndexs[i].intValue(), 3);
            showWarningMessage("部门不能重复设置！");
            return;
        }
        j++;
//_L6:
        if(j < tempBankContrastvos.length) //goto _L8; else goto _L7
//_L7:
        n++;
_L5:
        if(n < initBankContrastvos.length) //goto _L10; else goto _L9
//_L9:
        i++;
//_L4:
        if(i < editIndexs.length){} //goto _L12; else goto _L11
//_L11:
        nc.itf.bankpub.pub.IBankContrast ib = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
        ib.insertBankContrastVOs(tempBankContrastvos);
        editIndexs = null;
        if(state != 2 || editIndexs == null || editIndexs.length == 0)
            break MISSING_BLOCK_LABEL_1098;
        s = 0;
          //goto _L13
//_L15:
        if(getTableModel().getValueAt(editIndexs[s].intValue(), 0).equals(""))
        {
            showWarningMessage("请选择账簿!");
            return;
        }
        if(getTableModel().getValueAt(editIndexs[s].intValue(), 2).equals(""))
        {
            showWarningMessage("请填写核算机构编码!");
            return;
        }
        if(getTableModel().getValueAt(editIndexs[s].intValue(), 3).equals(""))
        {
            showWarningMessage("请填写核算机构名称!");
            return;
        }
        if(getTableModel().getValueAt(editIndexs[s].intValue(), 6).equals(""))
        {
            showWarningMessage("请填写柜员号!");
            return;
        }
        s++;
//_L13:
        if(s < editIndexs.length) //goto _L15; else goto _L14
//_L14:
        char s[] = (char[])null;
        tempBankContrastvos = new BankContrastVO[editIndexs.length];
        ib = 0;
          //goto _L16
//_L24:
        tempBankContrastvos[ib] = new BankContrastVO();
        tempBankContrastvos[ib].setPk_glorgbook((String)hashcorp.get(editIndexs[ib]));
        tempBankContrastvos[ib].setPk_deptdoc((String)hashdept.get(editIndexs[ib]));
        tempBankContrastvos[ib].setBankbm(getTableModel().getValueAt(editIndexs[ib].intValue(), 2).toString());
        tempBankContrastvos[ib].setBankmc(getTableModel().getValueAt(editIndexs[ib].intValue(), 3).toString());
        tempBankContrastvos[ib].setClerkcode(getTableModel().getValueAt(editIndexs[ib].intValue(), 6).toString());
        tempBankContrastvos[ib].setPk_system(pk_system);
        tempBankContrastvos[ib].setOrient("0");
        n = 0;
          //goto _L17
//_L22:
        j = 0;
          //goto _L18
//_L20:
        if(initBankContrastvos[n].getPk_deptdoc().equalsIgnoreCase(tempBankContrastvos[j].getPk_deptdoc()))
        {
            getTableModel().setValueAt("", editIndexs[ib].intValue(), 3);
            showWarningMessage("部门不能重复设置！");
            return;
        }
        j++;
//_L18:
        if(j < tempBankContrastvos.length) //goto _L20; else goto _L19
//_L19:
        n++;
//_L17:
        if(n < initBankContrastvos.length) //goto _L22; else goto _L21
//_L21:
        ib++;
//_L16:
        if(ib < editIndexs.length)// goto _L24; else goto _L23
//_L23:
        IBankContrast ib = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.getName());
        ib.insertBankContrastVOs(tempBankContrastvos);
        editIndexs = null;
        if(state != 3)
            break MISSING_BLOCK_LABEL_1597;
        row = currentRow;
        if(getTableModel().getValueAt(row, 0).equals(""))
        {
            showWarningMessage("请选择账簿!");
            return;
        }
        if(getTableModel().getValueAt(row, 2).equals(""))
        {
            showWarningMessage("请填写核算机构编码!");
            return;
        }
        if(getTableModel().getValueAt(row, 3).equals(""))
        {
            showWarningMessage("请填写核算机构名称!");
            return;
        }
        if(getTableModel().getValueAt(row, 6).equals(""))
        {
            showWarningMessage("请填写柜员号!");
            return;
        }
        vo = new BankContrastVO();
        vo.setPk_bankcontrast(initBankContrastvos[row].getPk_bankcontrast());
        if(corpmdy && deptmdy)
        {
            vo.setPk_glorgbook((String)hashcorp.get(new Integer(row)));
            vo.setPk_deptdoc((String)hashdept.get(new Integer(row)));
        } else
        if(corpmdy && !deptmdy)
            getTableModel().setValueAt("", row, 3);
        else
        if(!corpmdy && !deptmdy)
        {
            vo.setPk_glorgbook(initBankContrastvos[row].getPk_glorgbook());
            vo.setPk_deptdoc(initBankContrastvos[row].getPk_deptdoc());
        } else
        {
            vo.setPk_glorgbook(initBankContrastvos[row].getPk_glorgbook());
            vo.setPk_deptdoc((String)hashdept.get(new Integer(row)));
        }
        vo.setPk_system(initBankContrastvos[row].getPk_system());
        vo.setOrient("0");
        vo.setBankbm(getTableModel().getValueAt(row, 2).toString().trim());
        vo.setBankmc(getTableModel().getValueAt(row, 3).toString().trim());
        vo.setClerkcode(getTableModel().getValueAt(row, 6).toString().trim());
        i = 0;
          //goto _L25
//_L27:
        if(row != i && initBankContrastvos[i].getPk_deptdoc().trim().equalsIgnoreCase(vo.getPk_deptdoc().trim()))
        {
            getTableModel().setValueAt("", row, 4);
            showWarningMessage("相同家机构的相同部门不能重复设置！");
            return;
        }
        i++;
//_L25:
        if(i < initBankContrastvos.length)// goto _L27; else goto _L26
//_L26:
        IBankContrast ib = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
        ib.updateBankContrastVO(vo);
        onRefresh();
        state = 0;
        onState(state);
        getTable().getSelectionModel().setSelectionInterval(-1, -1);
        corpmdy = false;
        deptmdy = false;
            	
    }
	*/
    
    private void onAddRow()
        throws Exception
    {
        try
        {
            Vector vedit = new Vector();
            if(editIndexs != null && editIndexs.length != 0)
            {
                for(int i = 0; i < editIndexs.length; i++)
                    vedit.addElement(editIndexs[i]);

            }
            vedit.addElement(new Integer(getTable().getRowCount()));
            editIndexs = new Integer[vedit.size()];
            vedit.copyInto(editIndexs);
            getTableModel().addRow(1);
            int row = getTable().getRowCount();
            getTableModel().setRowEditable(row - 1, true);
            getTableModel().setColNotEditable(notedit);
            getTableModel().setColEditable(1, false);
            getTable().setModel(getTableModel());
            state = 1;
            onState(state);
            currentRow = row - 1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private void onInsert()
        throws Exception
    {
        Vector vedit;
        try
        {
            currentRow = getTable().getSelectedRow();
            int rowCount = getTable().getRowCount();
            if(currentRow < 0 || currentRow >= rowCount)
            {
                showWarningMessage("请选择一行。");
                return;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        vedit = new Vector();
        if(editIndexs != null && editIndexs.length != 0)
        {
            for(int i = 0; i < editIndexs.length; i++)
                vedit.addElement(editIndexs[i]);

        }
        vedit.addElement(new Integer(currentRow));
        editIndexs = new Integer[vedit.size()];
        vedit.copyInto(editIndexs);
        getTable().getCellEditor(currentRow, 0).stopCellEditing();
        getTable().getCellEditor(currentRow, 1).stopCellEditing();
        getTable().getCellEditor(currentRow, 2).stopCellEditing();
        getTable().getCellEditor(currentRow, 3).stopCellEditing();
        getTable().getCellEditor(currentRow, 4).stopCellEditing();
        getTable().getCellEditor(currentRow, 5).stopCellEditing();
        getTableModel().insertRow(currentRow);
        getTableModel().setColNotEditable(notedit);
        getTableModel().setColEditable(1, false);
        getTable().setModel(getTableModel());
        state = 2;
        onState(state);
    }

    private void onModify()
        throws Exception
    {
        try
        {
            currentRow = getTable().getSelectedRow();
            int count = getTable().getRowCount();
            if(currentRow < 0 || currentRow >= count)
            {
                showWarningMessage("请选择一行。");
                return;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        getTableModel().setRowEditable(currentRow, true);
        getTableModel().setColNotEditable(notedit);
        getTableModel().setColEditable(1, false);
        getTable().setModel(getTableModel());
        state = 3;
        onState(state);
    }

    private void onDelete()
        throws Exception
    {
        int row;
        int ire;
        try
        {
            row = getTable().getSelectedRow();
            int count = getTable().getRowCount();
            if(row < 0 || row >= count)
            {
                showWarningMessage("请选择一行。");
                return;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        ire = showYesNoMessage("是否删除当前记录,请确认？");
        if(ire != 4)
            return;
        BankContrastVO vo = new BankContrastVO();
        vo.setPk_bankcontrast(initBankContrastvos[row].getPk_bankcontrast());
        try
        {
            IBankContrast ib = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
            ib.deleteBankContrastVO(vo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        onRefresh();
        state = 0;
        onState(state);
        editIndexs = null;
        getTable().getSelectionModel().setSelectionInterval(-1, -1);
    }

    private void onRefresh()
        throws Exception
    {
        try
        {
            SystemTypeVO svo = (SystemTypeVO)getCmbSystem().getSelectedItem();
            String pk_system = svo.getPk_system();
            CorpVO corpvo = ClientEnvironment.getInstance().getCorporation();
            String pk_corp = corpvo.getPk_corp();
            IPubTools tool = (IPubTools)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IPubTools.class.getName());
            String pk_glorgbook[] = tool.getPkglorgbookByPkcorp(pk_corp);
            IBankContrast ib = (IBankContrast)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IBankContrast.class.getName());
            Object data[][] = (Object[][])null;
            if(initBankContrastvos != null && initBankContrastvos.length != 0)
            {
                data = new Object[initBankContrastvos.length][7];
                for(int i = 0; i < initBankContrastvos.length; i++)
                {
                    data[i][0] = new Object();
                    data[i][0] = initBankContrastvos[i].glorgbookcode;
                    data[i][1] = new Object();
                    data[i][1] = initBankContrastvos[i].glorgbookname;
                    data[i][2] = new Object();
                    data[i][2] = initBankContrastvos[i].getBankbm();
                    data[i][3] = new Object();
                    data[i][3] = initBankContrastvos[i].getBankmc();
                    data[i][4] = new Object();
                    data[i][4] = initBankContrastvos[i].deptcode;
                    data[i][5] = new Object();
                    data[i][5] = initBankContrastvos[i].deptname;
                    data[i][6] = new Object();
                    data[i][6] = initBankContrastvos[i].getClerkcode();
                }

                getTableModel().setDataVector(data);
                getTable().setModel(getTableModel());
            } else
            {
                data = new Object[0][7];
                getTableModel().setDataVector(data);
                getTable().setModel(getTableModel());
            }
            String corp = getClientEnvironment().getCorporation().getUnitname();
            showHintMessage((new StringBuilder("登陆公司 : ")).append(corp).toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private void onCance()
        throws Exception
    {
        try
        {
            onRefresh();
            Vector vedit = new Vector();
            if(editIndexs != null && editIndexs.length != 0)
            {
                for(int i = 0; i < editIndexs.length - 1; i++)
                    vedit.addElement(editIndexs[i]);

            }
            vedit.addElement(new Integer(currentRow));
            editIndexs = new Integer[vedit.size()];
            vedit.copyInto(editIndexs);
            state = 0;
            onState(state);
            editIndexs = null;
            getTable().getSelectionModel().setSelectionInterval(-1, -1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    private void onPrint()
        throws Exception
    {
    }

    private JKTableModel getTableModel()
    {
        if(tablemodel == null)
        {
            tablemodel = new JKTableModel();
            Vector column = new Vector();
            column.addElement("机构代码");
            column.addElement("机构名称");
            column.addElement("核算机构代码");
            column.addElement("核算机构名称");
            column.addElement("NC部门代码");
            column.addElement("NC部门名称");
            column.addElement("柜员号");
            tablemodel.setColumnIdentifiers(column);
        }
        return tablemodel;
    }

    private UITable getTable()
    {
        if(table == null)
        {
            table = new UITable();
            table.setModel(getTableModel());
            table.getColumnModel().getColumn(0).setCellEditor(getUIRefCellEditor());
            table.getColumnModel().getColumn(4).setCellEditor(getUIRefCellEditorDept());
            table.setAutoResizeMode(0);
            table.setSelectionMode(0);
            table.addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent mouseevent)
                {
                }

                public void mouseEntered(MouseEvent mouseevent)
                {
                }

                public void mouseExited(MouseEvent mouseevent)
                {
                }

                public void mousePressed(MouseEvent e)
                {
                    onMousePressed(e);
                }

                public void mouseReleased(MouseEvent mouseevent)
                {
                }
                               
            });
        }
        return table;
    }

    protected void onMousePressed(MouseEvent e)
    {
        int col = getTable().getSelectedColumn();
        if(col != 4)
            return;
        if(!corpmdy)
        {
            refDept.setRefNodeName("部门档案");
            String pk_glorgbook = initBankContrastvos[currentRow].getPk_glorgbook();
            String pk_corp = null;
            IPubTools ip = (IPubTools)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IPubTools.class.getName());
            try
            {
                pk_corp = ip.getPkcorpByPkglorgbook(pk_glorgbook);
            }
            catch(BusinessException e1)
            {
                e1.printStackTrace();
            }
            refDept.getRefModel().setPk_corp(pk_corp, true);
            refDept.setWhereString((new StringBuilder("pk_corp='")).append(pk_corp).append("'").toString());
        }
    }

    private UIRefCellEditor getUIRefCellEditor()
    {
        UIRefCellEditor editor = new UIRefCellEditor(getRefGlorgbook());
        editor.addCellEditorListener(new CellEditorListener() {

            public void editingCanceled(ChangeEvent changeevent)
            {
            }

            public void editingStopped(ChangeEvent e)
            {
                afterEdit();
            }
        });
        return editor;
    }

    private UIRefCellEditor getUIRefCellEditorDept()
    {
        UIRefCellEditor editor = new UIRefCellEditor(getRefDept());
        editor.addCellEditorListener(new CellEditorListener() {

            public void editingCanceled(ChangeEvent changeevent)
            {
            }

            public void editingStopped(ChangeEvent e)
            {
                afterEditDept();
            }
        });
        return editor;
    }

    private UITablePane getTablePane()
    {
        if(paneTable == null)
        {
            paneTable = new UITablePane();
            paneTable.setTable(getTable());
        }
        return paneTable;
    }

    private UIPanel getContentPane()
    {
        if(paneContent == null)
        {
            paneContent = new UIPanel();
            paneContent.setLayout(new BorderLayout());
            paneContent.add(getPaneTop(), "North");
            paneContent.add(getTablePane(), "Center");
        }
        return paneContent;
    }

    public UIPanel getPaneTop()
    {
        if(paneTop == null)
        {
            paneTop = new UIPanel();
            paneTop.setLayout(null);
            paneTop.setPreferredSize(new Dimension(1, 35));
            labsystem = new UILabel();
            labsystem.setText("系统");
            labsystem.setBounds(5, 6, 40, 22);
            paneTop.add(labsystem);
            paneTop.add(getCmbSystem());
        }
        return paneTop;
    }

    private UIRefPane getRefGlorgbook()
    {
        if(refGlorgbook == null)
        {
            refGlorgbook = new UIRefPane();
            refGlorgbook.setRefNodeName(RefNodeNameConst.GLORGBOOK);
        }
        return refGlorgbook;
    }

    private UIRefPane getRefDept()
    {
        if(refDept == null)
        {
            refDept = new UIRefPane();
            refDept.setRefNodeName(RefNodeNameConst.DEPTDOC);
            refDept.setNotLeafSelectedEnabled(false);
        }
        return refDept;
    }

    private UIComboBox getCmbSystem()
    {
        if(cmbSystem == null)
        {
            cmbSystem = new UIComboBox();
            cmbSystem.setBounds(53, 6, 120, 20);
        }
        return cmbSystem;
    }

    public void valueChanged(ValueChangedEvent event)
    {
        corpmdy = true;
        String pk_glorgbook = refGlorgbook.getRefPK();
        if(pk_glorgbook == null || pk_glorgbook.length() == 0)
            return;
        String pk_corp = null;
        IPubTools ip = (IPubTools)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IPubTools.class.getName());
        try
        {
            pk_corp = ip.getPkcorpByPkglorgbook(pk_glorgbook);
        }
        catch(BusinessException e)
        {
            e.printStackTrace();
        }
        refGlorgbook.getUITextField().setText(refGlorgbook.getRefCode());
        refDept.setRefNodeName(RefNodeNameConst.DEPTDOC);
        refDept.getRefModel().setPk_corp(pk_corp, true);
        int row = getTable().getSelectedRow();
        hashcorp.put(new Integer(row), pk_corp);
        String glorgbookname = refGlorgbook.getRefName();
        getTableModel().setValueAt(glorgbookname, row, 2);
        getTable().updateUI();
    }

    public void afterEdit()
    {
        corpmdy = true;
        String pk_glorgbook = refGlorgbook.getRefPK();
        if(pk_glorgbook == null || pk_glorgbook.length() == 0)
            return;
        String pk_corp = null;
        IPubTools ip = (IPubTools)NCLocator.getInstance().lookup(nc.itf.bankpub.pub.IPubTools.class.getName());
        try
        {
            pk_corp = ip.getPkcorpByPkglorgbook(pk_glorgbook);
        }
        catch(BusinessException e)
        {
            e.printStackTrace();
        }
        refDept.setRefNodeName(RefNodeNameConst.DEPTDOC);
        refDept.setWhereString((new StringBuilder("pk_corp='")).append(pk_corp).append("'").toString());
        hashcorp.put(new Integer(currentRow), pk_glorgbook);
        String glorgbookname = refGlorgbook.getRefName();
        getTableModel().setValueAt(glorgbookname, currentRow, 1);
    }

    public void afterEditDept()
    {
        deptmdy = true;
        String pkdept = refDept.getRefPK();
        int row = getTable().getSelectedRow();
        if(pkdept == null || pkdept.length() == 0)
        {
            return;
        } else
        {
            hashdept.put(new Integer(row), pkdept);
            String deptname = refDept.getRefName();
            getTableModel().setValueAt(deptname, currentRow, 5);
            return;
        }
    }

    public void bodyRowChange(BillEditEvent billeditevent)
    {
    }

    private void onDeptValueChanged()
    {
        deptmdy = true;
        String pkdept = refDept.getRefPK();
        int row = getTable().getSelectedRow();
        if(pkdept == null || pkdept.length() == 0)
        {
            return;
        } else
        {
            hashdept.put(new Integer(row), pkdept);
            return;
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == getCmbSystem())
            try
            {
                onRefresh();
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
    }

    private String strbtn[] = {
        "保存", "增行", "插入", "修改", "删除", "刷新", "取消", "打印"
    };
    private ButtonObject btn[];
    private UIPanel paneContent;
    private UITablePane paneTable;
    private UITable table;
    private JKTableModel tablemodel;
    private UIRefPane refGlorgbook;
    private UIRefPane refDept;
    private UIPanel paneTop;
    private UILabel labsystem;
    private int currentRow;
    private int state;
    private Hashtable hashcorp;
    private Hashtable hashdept;
    private Integer editIndexs[];
    private int notedit[] = {
        1, 5
    };
    private BankContrastVO initBankContrastvos[];
    private BankContrastVO tempBankContrastvos[];
    private boolean corpmdy;
    private boolean deptmdy;
    private UIComboBox cmbSystem;
       
}
