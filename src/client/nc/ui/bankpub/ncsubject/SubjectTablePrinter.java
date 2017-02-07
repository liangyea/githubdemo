/*
 * 创建日期 2006-8-31
 *
 * @author 孙锐
 */
package nc.ui.bankpub.ncsubject;

import java.awt.Font;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pub.beans.table.NCTableModel;
import nc.ui.pub.print.PrintDirectEntry;
import nc.ui.pub.print.datastruct.CellRange;
import nc.ui.pub.print.datastruct.Constants;

public class SubjectTablePrinter {

    private String m_sTopLeft = null;
    private String m_sTopRight = null;
    private String m_sBottomLeft = null;
    private String m_sBottomRight = null;
    private int m_iColDisplay[] = null;
    private JTable m_tabTable = null;
    private String m_sTitle = null;
    private int m_iAlignFlag[] = null;
    private int m_iColNum = 0;
    private int m_iRowNum = 0;
    private int m_iColWid[] = null;
    private int m_iRowHei = 0;
    private int m_iHeadHei = 0;
    private String m_sFullCol[] = null;
    private String m_sPureCol[] = null;
    private Object m_oData[][] = null;
    private String m_sTopStr = null;
    private String m_sBottomStr = null;
    private boolean m_bUseSettedRowHei = false;
    private boolean m_bIsTreeTable = false;

    public SubjectTablePrinter() {
        m_sTopLeft = null;
        m_sTopRight = null;
        m_sBottomLeft = null;
        m_sBottomRight = null;
        m_iColDisplay = null;
        m_tabTable = null;
        m_sTitle = "未设定标题";
        m_iAlignFlag = null;
        m_iColNum = 0;
        m_iRowNum = 0;
        m_iColWid = null;
        m_iRowHei = 30;
        m_iHeadHei = 30;
        m_sFullCol = null;
        m_sPureCol = null;
        m_oData = null;
        m_sTopStr = null;
        m_sBottomStr = null;
        m_bUseSettedRowHei = false;
        m_bIsTreeTable = false;
        init();
    }

    private int[] getAlignflag() {
        if (m_iAlignFlag != null && m_iAlignFlag.length != 0)
            return m_iAlignFlag;
        int iFlag[] = (int[])null;
        if (m_oData == null || m_oData.length == 0) {
            iFlag = new int[m_iColWid.length];
        } else {
            int iHei = m_oData.length;
            int iWid = m_oData[0].length;
            iFlag = new int[iWid];
            for (int i = 0; i < iWid; i++) {
                for (int j = 0; j < iHei; j++) {
                    if (m_oData[j][i] == null || m_oData[j][i].toString().trim().equals(""))
                        continue;
                    try {
                        if (m_oData[j][i].toString().indexOf(",") > 0)
                            iFlag[i] = 2;
                        else
                        if (m_oData[j][i].toString().equals("√")) {
                            iFlag[i] = 1;
                        } else {
                            Double.valueOf(m_oData[j][i].toString()).doubleValue();
                            iFlag[i] = 2;
                        }
                    }
                    catch (Exception e) {
                        iFlag[i] = 0;
                        for (int k = 0; k < i; k++)
                            iFlag[k] = 0;

                    }
                    break;
                }

            }

        }
        return iFlag;
    }

    private CellRange[] getCellRange(String data[][]) {
        String head[][] = data;
        Vector vData = new Vector();
        for (int i = 0; i < head.length; i++) {
            for (int j = 0; j < head[i].length; j++) {
                Vector vCenter = new Vector();
                vCenter.addElement((new StringBuffer(String.valueOf(i))).toString());
                vCenter.addElement((new StringBuffer(String.valueOf(j))).toString());
                if (head[i][j] != null && head[i][j].length() != 0) {
                    boolean bCol = false;
                    if (j + 1 == head[i].length)
                        vCenter.addElement((new StringBuffer(String.valueOf(head[i].length - 1))).toString());
                    for (int k = j + 1; k < head[i].length; k++) {
                        if (head[i][k] != null) {
                            vCenter.addElement((new StringBuffer(String.valueOf(k - 1))).toString());
                            if (k - 1 != j)
                                bCol = true;
                            break;
                        }
                        if (k == head[i].length - 1) {
                            vCenter.addElement((new StringBuffer(String.valueOf(k))).toString());
                            bCol = true;
                        }
                    }

                    boolean bRow = false;
                    if (!bCol) {
                        for (int k = i + 1; k < head.length; k++) {
                            if (head[k][j] != null) {
                                vCenter.addElement((new StringBuffer(String.valueOf(k - 1))).toString());
                                if (k - 1 != j)
                                    bRow = true;
                                break;
                            }
                            if (k == head.length - 1) {
                                vCenter.addElement((new StringBuffer(String.valueOf(k))).toString());
                                bRow = true;
                            }
                        }

                    }
                    if (bCol) {
                        int ii = Integer.valueOf(vCenter.elementAt(2).toString()).intValue();
                        for (int k = j + 1; k <= ii; k++)
                            head[i][k] = "";

                        vCenter.addElement((new StringBuffer(String.valueOf(i))).toString());
                        vData.addElement(vCenter);
                    }
                    if (bRow) {
                        int ii = Integer.valueOf(vCenter.elementAt(3).toString()).intValue();
                        for (int k = i + 1; k <= ii; k++)
                            head[k][j] = "";

                        vData.addElement(vCenter);
                    }
                }
            }

        }

        CellRange cell[] = new CellRange[vData.size()];
        for (int i = 0; i < vData.size(); i++) {
            Vector temp = (Vector)vData.elementAt(i);
            int item_1 = Integer.valueOf(temp.elementAt(0).toString()).intValue();
            int item_2 = Integer.valueOf(temp.elementAt(1).toString()).intValue();
            int item_3 = Integer.valueOf(temp.elementAt(3).toString()).intValue();
            int item_4 = Integer.valueOf(temp.elementAt(2).toString()).intValue();
            cell[i] = new CellRange(item_1, item_2, item_3, item_4);
        }

        for (int i = 0; i < head.length; i++) {
            for (int j = 0; j < head[i].length; j++)
                if (head[i][j].length() == 0)
                    head[i][j] = null;

        }

        return cell;
    }

    private void getColumnNames(JTable table) {
        Vector vInfo = new Vector();
        JViewport view = (JViewport)table.getParent();
        JScrollPane pane = (JScrollPane)view.getParent();
        GroupableTableHeader left = (GroupableTableHeader)pane.getCorner("UPPER_LEFT_CORNER");
        TableColumnModel tcm = null;
        Enumeration enum1 = null;
        TableColumn tc = null;
        if (left != null) {
            tcm = left.getColumnModel();
            Vector vCol;
            for (enum1 = left.getColumnModel().getColumns(); enum1.hasMoreElements(); vInfo.addElement(vCol)) {
                tc = (TableColumn)enum1.nextElement();
                Enumeration groups = left.getColumnGroups(tc);
                vCol = new Vector();
                vCol.addElement(tc.getHeaderValue());
                if (groups != null) {
                    ColumnGroup aGroup;
                    for (; groups.hasMoreElements(); vCol.addElement(aGroup.getHeaderValue()))
                        aGroup = (ColumnGroup)groups.nextElement();

                }
            }

        }
        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        tcm = header.getColumnModel();
        enum1 = header.getColumnModel().getColumns();
        tc = null;
        Vector vCol;
        for (; enum1.hasMoreElements(); vInfo.addElement(vCol)) {
            tc = (TableColumn)enum1.nextElement();
            Enumeration groups = header.getColumnGroups(tc);
            vCol = new Vector();
            vCol.addElement(tc.getHeaderValue());
            if (groups != null) {
                ColumnGroup aGroup;
                for (; groups.hasMoreElements(); vCol.addElement(aGroup.getHeaderValue()))
                    aGroup = (ColumnGroup)groups.nextElement();

            }
        }

        String sPure[] = (String[])null;
        String sFull[] = (String[])null;
        if (vInfo != null && vInfo.size() != 0) {
            StringBuffer sbFull = new StringBuffer();
            sPure = new String[vInfo.size()];
            sFull = new String[vInfo.size()];
            for (int i = 0; i < vInfo.size(); i++) {
                sbFull = new StringBuffer();
                Vector v0 = (Vector)vInfo.elementAt(i);
                if (v0 != null && v0.size() != 0) {
                    sPure[i] = v0.elementAt(0).toString();
                    for (int j = 1; j < v0.size(); j++)
                        sbFull.append(v0.elementAt(j).toString() + "/");

                    sbFull.append(v0.elementAt(0).toString());
                    sFull[i] = sbFull.toString();
                }
            }

        }
        m_sFullCol = sFull;
        m_sPureCol = sPure;
        if (m_bIsTreeTable) {
            m_sFullCol = new String[sPure.length - 1];
            m_sPureCol = new String[sPure.length - 1];
            for (int i = 1; i < sPure.length; i++) {
                m_sFullCol[i - 1] = sFull[i];
                m_sPureCol[i - 1] = sPure[i];
            }

        }
        for (int i = 0; i < sPure.length; i++)
            System.out.println(sPure[i]);

        for (int i = 0; i < sPure.length; i++)
            System.out.println(sFull[i]);

    }

    private int[] getColWidth() {
        if (m_iColWid == null || m_iColWid.length == 0)
            if (m_tabTable.getModel() instanceof NCTableModel) {
                m_iColWid = new int[m_iColNum];
                for (int i = 0; i < m_iColNum; i++)
                    m_iColWid[i] = m_tabTable.getColumnModel().getColumn(i + 1).getWidth();

            } else {
                int iLeftWid = 0;
                JTable tabLeft = null;
                JViewport view = (JViewport)m_tabTable.getParent();
                JScrollPane pane = (JScrollPane)view.getParent();
                pane.getCorner("UPPER_LEFT_CORNER");
                JViewport leftView = pane.getRowHeader();
                if (leftView != null) {
                    int iCount = leftView.getComponentCount();
                    for (int i = 0; i < iCount; i++) {
                        if (!(leftView.getComponent(i) instanceof JTable))
                            continue;
                        tabLeft = (JTable)leftView.getComponent(i);
                        break;
                    }

                    if (tabLeft != null)
                        iLeftWid = tabLeft.getModel().getColumnCount();
                }
                m_iColWid = new int[m_iColNum];
                for (int i = 0; i < iLeftWid; i++)
                    m_iColWid[i] = tabLeft.getColumnModel().getColumn(i).getWidth();

                for (int i = iLeftWid; i < m_iColNum; i++)
                    m_iColWid[i] = m_tabTable.getColumnModel().getColumn(i - iLeftWid).getWidth();

                if (m_iColNum < getCriticalColNums()) {
                    for (int i = 0; i < m_iColNum; i++)
                        m_iColWid[i] = getPaperSize() / m_iColNum;

                }
            }
        return m_iColWid;
    }

    private int getCriticalColNums() {
        return 4;
    }

    private void getData(JTable table) {
        JViewport view = (JViewport)table.getParent();
        JScrollPane pane = (JScrollPane)view.getParent();
        Object oLeft[][] = (Object[][])null;
        Object oRight[][] = (Object[][])null;
        pane.getCorner("UPPER_LEFT_CORNER");
        JViewport leftView = pane.getRowHeader();
        if (leftView != null) {
            int iCount = leftView.getComponentCount();
            JTable tabLeft = null;
            for (int i = 0; i < iCount; i++) {
                if (!(leftView.getComponent(i) instanceof JTable))
                    continue;
                tabLeft = (JTable)leftView.getComponent(i);
                break;
            }

            if (tabLeft != null) {
                int iWid = tabLeft.getModel().getColumnCount();
                int iHei = tabLeft.getModel().getRowCount();
                oLeft = new Object[iHei][iWid];
                for (int i = 0; i < iHei; i++) {
                    for (int j = 0; j < iWid; j++)
                        oLeft[i][j] = tabLeft.getModel().getValueAt(i, j);

                }

            }
        }
        int iWid = table.getColumnCount();
        int iHei = table.getRowCount();
        int istartcol = 0;
        if (m_bIsTreeTable)
            istartcol = 1;
        oRight = new Object[iHei][iWid - istartcol];
        for (int i = 0; i < iHei; i++) {
            for (int j = 0; j < iWid - istartcol; j++) {
                oRight[i][j] = table.getValueAt(i, j + istartcol);
                if (table.getModel().getColumnClass(j + istartcol) == java.lang.Boolean.class)
                    if (oRight[i][j] != null && ((Boolean)oRight[i][j]).booleanValue())
                        oRight[i][j] = "√";
                    else
                        oRight[i][j] = "";
            }

        }

        Object oData[][] = (Object[][])null;
        if (oLeft == null) {
            oData = oRight;
        } else {
            oData = new Object[iHei][oLeft[0].length + oRight[0].length];
            for (int i = 0; i < iHei; i++) {
                for (int j = 0; j < oLeft[0].length; j++)
                    oData[i][j] = oLeft[i][j];

                for (int j = 0; j < oRight[0].length; j++)
                    oData[i][j + oLeft[0].length] = oRight[i][j];

            }

        }
        m_oData = oData;
    }

    private int getEverageColWid() {
        return getPaperSize() / getCriticalColNums();
    }

    private String[][] getHeadNames(Vector head) {
        int i_Rows = 0;
        for (int i = 0; i < head.size(); i++) {
            String temp_name = head.elementAt(i).toString();
            int i_flag = 0;
            int i_index = 0;
            int tmp_rows = 0;
            while (i_index != -1)  {
                i_index = temp_name.indexOf("/", i_flag);
                if (i_index == -1)
                    break;
                tmp_rows++;
                i_flag = i_index + 1;
            }
            if (tmp_rows > i_Rows)
                i_Rows = tmp_rows;
        }

        int iRows = i_Rows + 1;
        String s_ColumnNames[][] = new String[iRows][head.size()];
        for (int i = 0; i < head.size(); i++) {
            String temp_name = head.elementAt(i).toString();
            int i_start = 0;
            int i_index = 0;
            for (int j = 0; j < iRows; j++) {
                i_index = temp_name.indexOf("/", i_start);
                if (i_index == -1) {
                    s_ColumnNames[j][i] = temp_name.substring(i_start);
                    for (int k = j + 1; k < s_ColumnNames.length; k++)
                        s_ColumnNames[k][i] = null;

                    break;
                }
                s_ColumnNames[j][i] = temp_name.substring(i_start, i_index);
                i_start = i_index + 1;
            }

        }

        for (int i = 0; i < iRows; i++) {
            int j = 0;
            String s_startItem = s_ColumnNames[i][0];
            for (; j < head.size(); j++) {
                String s_endItem = "";
                if (j < head.size() - 1) {
                    s_endItem = s_ColumnNames[i][j + 1];
                    if (s_startItem != null) {
                        if (s_startItem.equals(s_endItem)) {
                            if (i == 0) {
                                if (head.size() > 1) {
                                    if (s_ColumnNames[i + 1][j] != null)
                                        s_ColumnNames[i][j + 1] = null;
                                } else {
                                    s_ColumnNames[i][j + 1] = null;
                                }
                            } else
                            if (s_ColumnNames[i - 1][j + 1] == null)
                                s_ColumnNames[i][j + 1] = null;
                            else
                                s_startItem = s_endItem;
                        } else {
                            s_startItem = s_endItem;
                        }
                    } else {
                        s_startItem = s_endItem;
                    }
                }
            }

        }

        return s_ColumnNames;
    }

    private int getPaperSize() {
        return 400;
    }

    private int[] getRowHei(int headrownum) {
        int rowHei[] = (int[])null;
        rowHei = new int[headrownum + m_iRowNum];
        for (int i = 0; i < headrownum; i++)
            rowHei[i] = m_tabTable.getTableHeader().getHeight() / headrownum;

        for (int i = headrownum; i < headrownum + m_iRowNum; i++)
            if (m_bUseSettedRowHei)
                rowHei[i] = m_iRowHei;
            else
                rowHei[i] = m_tabTable.getRowHeight();

        return rowHei;
    }

    private void init() {
    }

    public static void main(String args[]) {
        new SubjectTablePrinter();
    }

    public void printMe() {
        PrintDirectEntry aPrinter = new PrintDirectEntry();
        String ColName[][] = (String[][])null;
        CellRange cellRange[] = (CellRange[])null;
        if (m_sPureCol == null || m_sPureCol.length == 0)
            return;
        if (m_sFullCol == null || m_sFullCol.length == 0) {
            ColName = (new String[][] {
                m_sPureCol
            });
        } else {
            Vector head = new Vector();
            for (int i = 0; i < m_sFullCol.length; i++)
                head.addElement(m_sFullCol[i]);

            ColName = getHeadNames(head);
        }
        cellRange = getCellRange(ColName);
        int colwidth[] = getColWidth();
        int rowheigh[] = getRowHei(ColName.length);
        aPrinter.setRowHeight(rowheigh);
        int iStrMod[] = new int[colwidth.length];
        int alignflag[] = getAlignflag();
        for (int i = 0; i < alignflag.length; i++) {
            iStrMod[i] = i;
            System.out.println("对其方式：" + alignflag[i]);
        }

        Font contentFont = new Font("dialog", 0, 12);
        if (m_sTopStr != null)
            aPrinter.setTopStr(m_sTopStr);
        if (m_sBottomStr != null)
            aPrinter.setBottomStr(m_sBottomStr);
        aPrinter.setPageNumDisp(true);
        aPrinter.setPageNumPos(0);
        aPrinter.setColWidth(colwidth);
        aPrinter.setColNames(ColName);
        aPrinter.setAlignFlag(alignflag);
        aPrinter.setData(m_oData);
        aPrinter.setTitle(m_sTitle);
        aPrinter.setColStrMode(iStrMod, Constants.IS_STRING_FOLD);
        aPrinter.setCombinCellRange(cellRange);
        aPrinter.preview();
    }

    public void setAlignFlag(int flag[]) {
        m_iAlignFlag = flag;
    }

    public void setBottomLeft(String str) {
        m_sBottomLeft = str;
    }

    public void setBottomRight(String str) {
        m_sBottomRight = str;
    }

    public void setBottomStr(String str) {
        m_sBottomStr = str;
    }

    public void setColWid(int wid[]) {
        m_iColWid = wid;
    }

    public void setHasLoclCol(boolean flag) {
    }

    public void setHeaderHei(int hei) {
        m_iHeadHei = hei;
    }

    public void setRowHeigh(int hei) {
        if (hei <= 0) {
            return;
        } else {
            m_iRowHei = hei;
            m_bUseSettedRowHei = true;
            return;
        }
    }

    public void setTable(JTable table) {
        TableModel tm = table.getModel();
        m_tabTable = table;
        if (tm instanceof NCTableModel)
            m_bIsTreeTable = true;
        else
            m_bIsTreeTable = false;
        getColumnNames(table);
        getData(table);
        m_iColNum = m_sPureCol.length;
        m_iRowNum = table.getRowCount();
        //m_oData = NumFormate.getPrecisionData(m_oData);
        int i = 0;
        i++;
    }

    public void setTable_old(UITable uitable) {
    }

    public void setTitle(String title) {
        m_sTitle = title;
    }

    public void setTopLeft(String s) {
    }

    public void setTopRight(String str) {
        m_sTopRight = str;
    }

    public void setTopStr(String str) {
        m_sTopStr = str;
    }
}