package nc.ui.bankpub.banksubject;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.bankpub.pub.IBankSubjContrast;
import nc.itf.bankpub.pub.IFileDefine;
import nc.itf.bankpub.pub.IPubTools;
import nc.itf.bankpub.pub.ISystemType;
import nc.ui.bankpub.ncsubject.CorpChooseDlg;
import nc.ui.bankpub.system.JKTableModel;
import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.gl.datacache.AccsubjDataCache;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.beans.UITablePane;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.vo.bankpub.cache.BDAccessor;
import nc.vo.bankpub.filedefine.FileDefineVO;
import nc.vo.bankpub.pub.BankCorpVO;
import nc.vo.bankpub.subjcolshow.SubjColShowVO;
import nc.vo.bankpub.subjcontrast.BankSubjContrastVO;
import nc.vo.bankpub.systemtype.SystemTypeVO;
import nc.vo.bd.access.BddataVO;
import nc.vo.bd.access.IBDAccessor;
import nc.vo.bd.access.IBdinfoConst;
import nc.vo.bd.b02.AccsubjVO;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sm.nodepower.OrgnizeTypeVO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class BankSubjContrastUI extends ToftPanel implements ActionListener,
		ValueChangedListener {

	private String[] strbtn = { "新增", "修改", "保存", "删除", "取消", "刷新", "EXCEL导入",
			"复制", "规则" };

	private ButtonObject btn[] = null;

	private UIPanel paneContent = null;

	private UIPanel paneTop = null;

	private UITablePane tablepane = null;

	private UITable table = null;

	private JKTableModel tablemodel = null;

	private UILabel labsystem = null;

	private UILabel labglorgbook = null;

	private UIComboBox cmbsystem = null;

	private UIRefPane refglorgbook = null;

	private UIRefPane refaccsubj = null;

	private BankSubjContrastVO[] m_vos = null;

	private BankSubjContrastVO[] tempvos = null; // 暂存修改vo

	private Vector vcolumn = new Vector();

	private Hashtable hashsystem = null;

	private Hashtable hashsubj = null;

	private UIRefPane refcolumnfile = null;

	private DlgRule m_dlgCond = null;

	private FileDefineVO[] colnamevos = null;

	private SubjColShowVO[] m_columnvos = null;

	private SubjColShowVO m_columnvo = null;

	private FileDefineVO[] m_filevos = null;

	private FileDefineVO[] m_tmpfilevos = null;

	private Hashtable hashfile = new Hashtable();

	private int state = 0;

	private int currentRow = 0;

	private int initRow = 0;

	private HSSFSheet sheet = null;

	private HSSFWorkbook workbook = null;

	private InputStream excelis = null;

	private String pk_system = null;

	private String pk_glorgbook = null;

	public String getTitle() {
		// TODO 自动生成方法存根
		return null;
	}

	public BankSubjContrastUI() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}

	public void onButtonClicked(ButtonObject bo) {
		// TODO 自动生成方法存根
		try {

			String name = bo.getName();
			if (name.equals("新增")) {
				onAdd();
				return;
			}
			if (name.equals("修改")) {

				onModify();
				return;
			}
			if (name.equals("保存")) {
				onSave();
				return;
			}
			if (name.equals("删除")) {
				onDelete();
				return;
			}
			if (name.equals("取消")) {
				onCance();
				return;
			}
			if (name.equals("刷新")) {
				onRefresh();
				return;
			}
			if (name.equals("EXCEL导入")) {
				onImport();
				return;
			}
			if (name.equals("复制")) {
				onCopy();
				return;
			}
			if (name.equals("规则")) {
				onRule();
				return;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			showErrorMessage("执行" + bo.getName() + "失败：" + ex.getMessage());
		}
	}

	private void initialize() {
		this.setSize(495, 306);
		this.add(getContentpane());
		initTable();
		initCrols();
		initDatas();
		onState(-1);
	}

	private void initTable() {

	}

	private void initCrols() {
		setButtonName(strbtn);
	}

	private void initDatas() {

		hashsubj = new Hashtable();
		// 初始化系统
		ISystemType is = (ISystemType) NCLocator.getInstance().lookup(
				ISystemType.class.getName());
		SystemTypeVO[] systemtypevos = null;
		try {
			systemtypevos = is.queryAllSystemTypeVOs();
		} catch (BusinessException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		hashsystem = new Hashtable();
		getCmbSystem().removeAllItems();
		getCmbSystem().removeActionListener(this);
		if (systemtypevos != null && systemtypevos.length != 0) {
			for (int i = 0; i < systemtypevos.length; i++) {
				getCmbSystem().addItem(systemtypevos[i].getSystemname());
				hashsystem.put(systemtypevos[i].getSystemname().trim(),
						systemtypevos[i].getPk_system());
			}
		}
		getCmbSystem().addActionListener(this);
		pk_system = (String) hashsystem.get(getCmbSystem()
				.getSelectedItemName().toString().trim());

		try {
			onRefresh();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			Debug.error(e.getMessage(), e);
		}
	}

	private void onAdd() throws Exception {
		try {
			tablemodel.addRow(1);
			int row = getTable().getRowCount();
			tablemodel.setRowEditable(row - 1, true);
			getTable().setModel(tablemodel);
			state = 1;
			onState(state);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void onModify() throws Exception {
		try {
			currentRow = getTable().getSelectedRow();
			int count = getTable().getRowCount();
			if (currentRow < 0 || currentRow >= count) {
				showWarningMessage("请选择一行。");
				return;
			}
			tablemodel.setRowEditable(currentRow, true);
			getTable().setModel(tablemodel);
			state = 2;
			onState(state);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void getPk_accsubjBy(String pk_glorgbook, String subjcode,
			int rowNum) throws BusinessException {
		IBDAccessor subjAccessor = BDAccessor
				.getBDAccessor(IBdinfoConst.ACCSUBJ,
						OrgnizeTypeVO.ZHUZHANG_TYPE, pk_glorgbook);
		BddataVO subjVo = subjAccessor.getDocByCode(subjcode);
		if (subjVo == null) {
			throw new BusinessException("新会计科目" + subjcode + "不存在！");
		}
		String accsubj = subjVo.getPk();
		AccsubjDataCache accsubjDataCache = AccsubjDataCache.getInstance();
		AccsubjVO accsubjVO = accsubjDataCache.getAccsubjVOByPK(accsubj);
		boolean endFlag = accsubjVO.getEndflag().booleanValue();
		if (!endFlag) {
			throw new BusinessException("新会计科目" + subjcode + "不是末级科目！");
		}
		hashsubj.put(pk_system + pk_glorgbook
				+ String.valueOf(new Integer(rowNum)), accsubj);
	}

	private void onSave() throws Exception {
		try {
			getTable().getCellEditor().stopCellEditing();
		} catch (Exception ex) {
		}
		int colcount = getTable().getColumnCount();
		IBankSubjContrast ib = (IBankSubjContrast) NCLocator.getInstance()
				.lookup(IBankSubjContrast.class.getName());

		int row = getTable().getRowCount();
		if (state == 1 || state == 7)// 新增
		{
			tempvos = new BankSubjContrastVO[row - initRow];
			if (getRefGlorgbook().getRefPK() == null) {
				showWarningMessage("请选择账本！");
				return;
			}
			for (int i = initRow; i < row; i++) {
				tempvos[i - initRow] = new BankSubjContrastVO();

				if (checkNullValue(colcount, i))
					return;

				tempvos[i - initRow].setPk_system(pk_system);
				tempvos[i - initRow].setPk_glorgbook(getRefGlorgbook()
						.getRefPK());
				try {
					if (state == 7) {
						getPk_accsubjBy(getRefGlorgbook().getRefPK(),
								getTable().getValueAt(i, colcount - 1)
										.toString(), i);
					}
					String s = (String) hashsubj.get(pk_system + pk_glorgbook
							+ String.valueOf(new Integer(i)));
					tempvos[i - initRow].setPk_accsubj(s);
				} catch (BusinessException e) {
					e.printStackTrace();
					showWarningMessage(e.getMessage());
					return;
				}
				fillData(colcount, i, tempvos[i - initRow]);
			}
			// 检查重复项
			Hashtable hash = new Hashtable();
			checkRepeatedItem(hash, m_vos);
			checkRepeatedItem(hash, tempvos);
			try {
				ib.insertBankSubjContrastVOs(tempvos);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		if (state == 2)// 修改
		{
			if (checkNullValue(colcount, currentRow))
				return;

			BankSubjContrastVO vo = new BankSubjContrastVO();
			vo.setPk_banksubjcontrast(m_vos[currentRow]
					.getPk_banksubjcontrast());
			vo.setPk_system((String) hashsystem.get(getCmbSystem()
					.getSelectedItemName().toString().trim()));
			vo.setPk_glorgbook(getRefGlorgbook().getRefPK());
			if (!hashsubj.isEmpty()) {
				String s = (String) hashsubj.get(pk_system + pk_glorgbook
						+ String.valueOf(new Integer(currentRow)));
				vo.setPk_accsubj(s);
			}
			if (vo.getPk_accsubj() == null) {
				vo.setPk_accsubj(m_vos[currentRow].getPk_accsubj());
			}
			fillData(colcount, currentRow, vo);
			// 检查重复项
			Hashtable hash = new Hashtable();
			for (int i = 0; i < row; i++) {

				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < colcount - 1; j++) {
					sb.append(getTable().getValueAt(i, j).toString() + " ");

				}
				String str = sb.toString();
				if (!hash.containsKey(str)) {
					hash.put(str, "combination");
				} else {
					throw new Exception("外系统科目有重复");
				}

			}
			try {
				ib.updateBankSubjContrastVO(vo);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		onRefresh();
		onState(0);
		getTable().getSelectionModel().setSelectionInterval(-1, -1);
	}

	private boolean checkNullValue(int colcount, int rowNum) {
		int val = 0;
		for (int j = 0; j < colcount; j++) {
			if (getTable().getValueAt(rowNum, j) == null
					|| getTable().getValueAt(rowNum, j).toString().trim()
							.equalsIgnoreCase("")) {
				if (getTablemodel().getColumnName(j).equals("新会计科目")) {
					showWarningMessage("新会计科目不允许有空值！");
					return true;
				} else {
					val++;
				}
			}
		}
		if (val == colcount-1) {
			showWarningMessage("核心系统科目、业务代码和账户不能同时为空！");
			return true;
		}
		return false;
	}

	private void fillData(int colcount, int rowNum, BankSubjContrastVO bscv) {
		Object tempObj;
		if (colcount == 1) {
			bscv.setDef1("");
			bscv.setDef2("");
			bscv.setDef3("");
			bscv.setDef4("");
			bscv.setDef5("");
		}

		if (colcount > 1) {
			tempObj = getTable().getValueAt(rowNum, 0);
			bscv.setDef1(tempObj == null ? "" : tempObj.toString().trim());
			if (colcount > 2) {
				tempObj = getTable().getValueAt(rowNum, 1);
				bscv.setDef2(tempObj == null ? "" : tempObj.toString().trim());
				if (colcount > 3) {
					tempObj = getTable().getValueAt(rowNum, 2);
					bscv.setDef3(tempObj == null ? "" : tempObj.toString()
							.trim());
					if (colcount > 4) {
						tempObj = getTable().getValueAt(rowNum, 3);
						bscv.setDef4(tempObj == null ? "" : tempObj.toString()
								.trim());
						if (colcount > 5) {
							tempObj = getTable().getValueAt(rowNum, 4);
							bscv.setDef5(tempObj == null ? "" : tempObj
									.toString().trim());
						} else {
							bscv.setDef5("");
						}
					} else {
						bscv.setDef4("");
						bscv.setDef5("");
					}
				} else {
					bscv.setDef3("");
					bscv.setDef4("");
					bscv.setDef5("");
				}
			} else {
				bscv.setDef2("");
				bscv.setDef3("");
				bscv.setDef4("");
				bscv.setDef5("");
			}
		}
	}

	private void checkRepeatedItem(Hashtable ht, BankSubjContrastVO[] bscvo)
			throws Exception {
		// 检查重复项
		if (bscvo != null && bscvo.length != 0) {
			for (BankSubjContrastVO bsc : bscvo) {
				StringBuffer sb = new StringBuffer();
				sb.append(bsc.getDef1());
				sb.append(bsc.getDef2());
				sb.append(bsc.getDef3());
				sb.append(bsc.getDef4());
				sb.append(bsc.getDef5());
				String str = sb.toString();
				if (!ht.containsKey(str)) {
					ht.put(str, "combination");
				} else {
					throw new Exception("外系统科目有重复");
				}
			}
		}
	}

	private void onDelete() throws Exception {
		int row = getTable().getSelectedRow();
		int count = getTable().getRowCount();
		if (row < 0 || row >= count) {
			showWarningMessage("请选择一行。");
			return;
		}
		int ire = showYesNoMessage("是否删除当前记录,请确认？");
		if (ire != UIDialog.ID_YES)
			return;
		try {

			IBankSubjContrast is = (IBankSubjContrast) NCLocator.getInstance()
					.lookup(IBankSubjContrast.class.getName());
			is.deleteBankSubjContrastVO(m_vos[row]);
			onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void onCance() throws Exception {
		try {
			onRefresh();
			onState(0);
			getTable().getSelectionModel().setSelectionInterval(-1, -1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void onRefresh() throws Exception {
		pk_glorgbook = getRefGlorgbook().getRefPK();
		// pk_system = (String) hashsystem.get(getCmbSystem()
		// .getSelectedItemName().toString().trim());
		if (pk_glorgbook == null) {
			showWarningMessage("请选择账本！");
			return;
		}
		getRefSubj().setWhereString("pk_glorgbook='" + pk_glorgbook + "'");
		// 初始化列名
		IBankSubjContrast ib = (IBankSubjContrast) NCLocator.getInstance()
				.lookup(IBankSubjContrast.class.getName());
		try {
			m_columnvos = ib.queryAllSubjColShowVO();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		m_columnvo = null;
		if (m_columnvos != null && m_columnvos.length != 0) {
			for (int i = 0; i < m_columnvos.length; i++) {
				if (m_columnvos[i].getPk_system().equals(pk_system)) {
					m_columnvo = m_columnvos[i];
				}
			}
		}
		// FileDefineVO[] tempvos = new FileDefineVO[];
		// 查询文件所有列
		IFileDefine ifi = (IFileDefine) NCLocator.getInstance().lookup(
				IFileDefine.class.getName());
		try {
			m_filevos = ifi.queryAllFileDefineVOs();
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		// 筛选当前系统对应文件列
		Vector vfile = new Vector();
		hashfile = new Hashtable();
		if (m_filevos != null && m_filevos.length != 0) {
			for (int i = 0; i < m_filevos.length; i++) {
				if (m_filevos[i].getPk_system().equals(pk_system)) {
					vfile.addElement(m_filevos[i]);
				}
			}
		}
		if (vfile.size() > 0) {
			m_tmpfilevos = new FileDefineVO[vfile.size()];
			vfile.copyInto(m_tmpfilevos);
		}
		if (m_tmpfilevos != null && m_tmpfilevos.length != 0) {
			for (int i = 0; i < m_tmpfilevos.length; i++) {
				hashfile.put(m_tmpfilevos[i].getXh().toString().trim(),
						m_tmpfilevos[i]);
			}

		}
		// hashfile = new Hashtable();
		// if(m_columnvos!=null && m_columnvos.length!=0){
		// pk_system =
		// (String)hashsystem.get(getCmbSystem().getSelectedItemName().toString().trim());
		// for(int i=0;i<m_columnvos.length;i++){
		if (m_columnvo != null) {
			// FileDefineVO[] filedinevos = null;
			Vector vfilecol = new Vector();
			if (m_columnvo.getDef1() != null
					&& !m_columnvo.getDef1().trim().equals("")) {
				vfilecol.addElement(hashfile.get(m_columnvo.getDef1().trim()));
			}
			if (m_columnvo.getDef2() != null
					&& !m_columnvo.getDef2().trim().equals("")) {
				vfilecol.addElement(hashfile.get(m_columnvo.getDef2().trim()));
			}
			if (m_columnvo.getDef3() != null
					&& !m_columnvo.getDef3().trim().equals("")) {

				vfilecol.addElement(hashfile.get(m_columnvo.getDef3().trim()));
			}
			if (m_columnvo.getDef4() != null
					&& !m_columnvo.getDef4().trim().equals("")) {
				vfilecol.addElement(hashfile.get(m_columnvo.getDef4().trim()));
			}
			if (m_columnvo.getDef5() != null
					&& !m_columnvo.getDef5().trim().equals("")) {

				vfilecol.addElement(hashfile.get(m_columnvo.getDef5().trim()));
			}
			if (vfilecol.size() > 0) {
				colnamevos = new FileDefineVO[vfilecol.size()];
				vfilecol.copyInto(colnamevos);
			}
			// break;
			// }else{
			// colnamevos = null;
			// }
		}
		// }
		if (m_columnvo == null) {
			showWarningMessage("请选择规则!");
			tablemodel.setDataVector(new Object[0][0]);
			return;
		}
		Vector v = new Vector();
		for (int i = 0; i < colnamevos.length; i++) {
			v.addElement(colnamevos[i].getColumnname());
		}
		v.addElement("新会计科目");
		getTablemodel().setColumnIdentifiers(v);
		getTable().setModel(tablemodel);
		int colcount = getTable().getColumnCount();
		getTable().getColumnModel().getColumn(colcount - 1).setCellEditor(
				getUIRefCellEditor());

		BankSubjContrastVO[] m_allvos = null;
		try {
			// IBankSubjContrast ib =
			// (IBankSubjContrast)NCLocator.getInstance().lookup(IBankSubjContrast.class.getName());
			// m_allvos = ib.queryAllBankSubjContrastVO();
			m_vos = ib.queryBankSubjContrastVOByPkglorgbook(pk_glorgbook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		// if(m_allvos==null || m_allvos.length==0){
		// return;
		// }
		// Vector v1= new Vector();
		// for(int i=0;i<m_allvos.length;i++){
		// if(pk_glorgbook.trim().equals(m_allvos[i].getPk_glorgbook().trim())
		// && pk_system.trim().equals(m_allvos[i].getPk_system().trim())){
		// v1.addElement(m_allvos[i]);
		// }
		// }
		// m_vos = null;
		// if(v1.size()>0){
		// m_vos = new BankSubjContrastVO[v1.size()];
		// v1.copyInto(m_vos);
		// }
		// int colcount = getTable().getColumnCount();
		Object[][] datas = null;
		IPubTools ip = (IPubTools) NCLocator.getInstance().lookup(
				IPubTools.class.getName());

		if (m_vos != null && m_vos.length != 0) {
			initRow = m_vos.length;
			datas = new Object[m_vos.length][colcount];
			for (int i = 0; i < m_vos.length; i++) {
				// datas[i][0] = new Object();
				// datas[i][0] =
				// ip.getStrByPk("bd_accsubj","subjname","pk_accsubj"
				// ,m_vos[i].getPk_accsubj().trim());
				datas[i][0] = new Object();
				datas[i][0] = m_vos[i].getDef1().trim();
				if (colcount > 2) {
					datas[i][1] = new Object();
					datas[i][1] = m_vos[i].getDef2().trim();
					if (colcount > 3) {
						datas[i][2] = new Object();
						datas[i][2] = m_vos[i].getDef3().trim();
						if (colcount > 4) {
							datas[i][3] = new Object();
							datas[i][3] = m_vos[i].getDef4().trim();
							if (colcount > 5) {
								datas[i][4] = new Object();
								datas[i][4] = m_vos[i].getDef5().trim();
								datas[i][5] = new Object();
								datas[i][5] = ip.getStrByPk1("bd_accsubj",
										"subjname", "pk_accsubj", m_vos[i]
												.getPk_accsubj().trim());

							} else {
								datas[i][4] = new Object();
								datas[i][4] = ip.getStrByPk1("bd_accsubj",
										"subjname", "pk_accsubj", m_vos[i]
												.getPk_accsubj().trim());

							}
						} else {
							datas[i][3] = new Object();
							datas[i][3] = ip.getStrByPk1("bd_accsubj",
									"subjname", "pk_accsubj", m_vos[i]
											.getPk_accsubj().trim());

						}
					} else {
						datas[i][2] = new Object();
						datas[i][2] = ip.getStrByPk1("bd_accsubj", "subjname",
								"pk_accsubj", m_vos[i].getPk_accsubj().trim());

					}
				} else {
					datas[i][1] = new Object();
					datas[i][1] = ip.getStrByPk1("bd_accsubj", "subjname",
							"pk_accsubj", m_vos[i].getPk_accsubj().trim());

				}

			}
			tablemodel.setDataVector(datas);
			initRow = tablemodel.getRowCount();
		} else {
			initRow = 0;
			datas = new Object[0][colcount];
			tablemodel.setDataVector(datas);

		}
	}

	private void onImport() throws Exception {
		initRow = 0;
		Object[][] datas = getDataFromExcel();
		tablemodel.setDataVector(datas);
		// for (int i = 0; i < tablemodel.getRowCount(); i++) {
		// tablemodel.setRowEditable(i, true);
		// }
		state = 7;
		onState(state);
	}

	private Object[][] getDataFromExcel() throws Exception {

		int colcount = getTable().getColumnCount();
		if (openExcel()) {

			Vector v = new Vector();
			for (int i = 1;; i++) {

				// 判断是否到了合计行
				Object obj = getExcelAt(i, 0);
				if (obj != null) {
					String str = obj.toString();
					if (str.trim().equals("")) {
						break;
					}
				} else {
					break;
				}
				Object[] objLine = new Object[colcount];
				for (int j = 0; j < objLine.length; j++) {
					objLine[j] = getExcelAt(i, j);
				}
				v.add(objLine);
			}

			Object[][] objData = new Object[v.size()][];
			v.copyInto(objData);
			closeExcel();

			return objData;
		}
		return new Object[0][0];
	}

	private void onCopy() {
		CorpChooseDlg dlg = new CorpChooseDlg(this);
		int ire = dlg.showModal();
		if (ire == nc.ui.pub.beans.UIDialog.ID_OK
				|| ire == nc.ui.pub.beans.UIDialog.ID_YES) {

		} else {
			return;
		}
		String standard = getRefGlorgbook().getRefPK();
		if (standard == null || standard.trim().equals("")) {
			showWarningMessage("请选择复制参照账簿!");
			return;
		}
		BankCorpVO tar[] = dlg.getCorps();
		if (tar == null || tar.length == 0)
			return;
		ire = showYesNoMessage("将要把公司" + standard
				+ "所有科目对照表复制到选定公司,并且选定公司的已有科目对照将被全部删除，是否确定？");
		if (ire == nc.ui.pub.beans.UIDialog.ID_OK
				|| ire == nc.ui.pub.beans.UIDialog.ID_YES) {

		} else {
			return;
		}
		String star[] = new String[tar.length];
		for (int i = 0; i < star.length; i++)
			star[i] = tar[i].getPk_glorgbook();
		IBankSubjContrast ibank = (IBankSubjContrast) NCLocator.getInstance()
				.lookup(IBankSubjContrast.class.getName());
		try {
			ibank.copySubjcontrast(pk_system, standard, star);
		} catch (BusinessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	private void onRule() {
		String pk_system = (String) hashsystem.get(getCmbSystem()
				.getSelectedItemName().toString().trim());
		// DlgRule dlg = new DlgRule(pk_system);
		// dlg.show();
		if (m_dlgCond == null) {
			m_dlgCond = new DlgRule(pk_system);
		} else {
			m_dlgCond.setPk_system(pk_system);
			m_dlgCond.setTable();
		}
		int ire = m_dlgCond.showModal();
		if (ire != UIDialog.ID_OK)
			return;
		colnamevos = m_dlgCond.getFileDefineVOs();
	}

	private void onState(int state) {
		boolean btnEnable[] = new boolean[] { true, true, false, true, false,
				true, true, true, true };
		boolean btnEnableadd[] = new boolean[] { true, false, true, false,
				true, false, false, false, false };
		boolean btnEnableInit[] = new boolean[] { false, false, false, false, false,
				true, false, false, false };
		if (state == -1) // 初始化
		{
			for (int i = 0; i < btn.length; i++)
				btn[i].setEnabled(btnEnableInit[i]);
			updateButtons();
		}
		if (state == 0) // 浏览
		{
			for (int i = 0; i < btn.length; i++)
				btn[i].setEnabled(btnEnable[i]);
			updateButtons();
		}
		if (state == 1) // 新增
		{
			for (int i = 0; i < btn.length; i++)
				btn[i].setEnabled(btnEnableadd[i]);
			updateButtons();
		}
		if (state == 2) // 修改
		{
			for (int i = 0; i < btn.length; i++)
				btn[i].setEnabled(!btnEnable[i]);
			updateButtons();
		}
		if (state == 7) // 导入
		{
			for (int i = 0; i < btn.length; i++)
				btn[i].setEnabled(!btnEnable[i]);
			updateButtons();
		}
	}

	private void setButtonName(String name[]) {
		btn = new ButtonObject[name.length];
		for (int i = 0; i < name.length; i++) {
			btn[i] = new ButtonObject(name[i], name[i], 2);
		}
		setButtons(btn);
		updateButtons();
	}

	public boolean openExcel() throws Exception {
		UIFileChooser fc = new UIFileChooser();
		fc.setMultiSelectionEnabled(false);
		fc.setFileFilter(new ExcelFileFilter());
		if (fc.showOpenDialog(this) == UIFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			excelis = new FileInputStream(f);
			workbook = new HSSFWorkbook(excelis);
			sheet = workbook.getSheetAt(0);
			return true;
		} else {
			return false;
		}
	}

	public Object getExcelAt(int row, int col) throws Exception {
		if (sheet == null) {
			throw new Exception("尚未打开Excel文件");
		}
		System.out.print(row);
		System.out.print(col);
		HSSFRow hssfrow = sheet.getRow(row);
		if (hssfrow == null) {
			return null;
		}
		HSSFCell cell = hssfrow.getCell((short) col);
		if (cell == null) {
			return null;
		}

		// cell.setCellType(HSSFCell.CELL_TYPE_STRING);

		Object obj = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			obj = cell.getRichStringCellValue().toString().trim();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			obj = null;
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			obj = new Boolean(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			obj = new Byte(cell.getErrorCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			obj = new UFDouble(cell.getNumericCellValue(), 0);
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			obj = new UFDouble(cell.getNumericCellValue(), 0);
			break;
		}
		return obj;
	}

	public void closeExcel() throws Exception {
		excelis.close();
	}

	public UIPanel getContentpane() {
		if (paneContent == null) {
			paneContent = new UIPanel();
			paneContent.setLayout(new BorderLayout());
			paneContent.add(getPaneTop(), java.awt.BorderLayout.NORTH);
			paneContent.add(getTablepane(), java.awt.BorderLayout.CENTER);

		}
		return paneContent;
	}

	public UIPanel getPaneTop() {
		if (paneTop == null) {
			paneTop = new UIPanel();
			paneTop.setLayout(null);
			paneTop.setPreferredSize(new java.awt.Dimension(1, 35));
			labsystem = new UILabel();
			labsystem.setText("系统");
			labsystem.setBounds(5, 6, 40, 22);
			labglorgbook = new UILabel();
			labglorgbook.setText("账簿");
			labglorgbook.setBounds(190, 6, 40, 22);
			paneTop.add(labsystem);
			paneTop.add(getCmbSystem());
			paneTop.add(labglorgbook);
			paneTop.add(getRefGlorgbook());
		}
		return paneTop;
	}

	public UITablePane getTablepane() {
		if (tablepane == null) {
			tablepane = new UITablePane();
			tablepane.setName("tablepane");
			tablepane.setTable(getTable());
		}
		return tablepane;
	}

	public UITable getTable() {
		if (table == null) {
			table = new UITable();
			table.setName("table");
			table.setModel(getTablemodel());
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return table;
	}

	public JKTableModel getTablemodel() {
		if (tablemodel == null) {
			tablemodel = new JKTableModel();
		}
		return tablemodel;
	}

	public UIRefPane getRefGlorgbook() {
		if (refglorgbook == null) {
			refglorgbook = new UIRefPane();
			refglorgbook.setBounds(new Rectangle(248, 6, 118, 20));
			refglorgbook.setRefNodeName(RefNodeNameConst.GLORGBOOK);

			refglorgbook.addValueChangedListener(this);
			refglorgbook.setButtonFireEvent(true);
		}
		return refglorgbook;
	};

	public UIComboBox getCmbSystem() {
		if (cmbsystem == null) {
			cmbsystem = new UIComboBox();
			cmbsystem.setBounds(53, 6, 120, 20);
		}
		return cmbsystem;
	}

	public UIRefPane getRefSubj() {
		if (refaccsubj == null) {
			refaccsubj = new UIRefPane();
			refaccsubj.setRefNodeName(RefNodeNameConst.ACCSUBJ);
			refaccsubj.setNotLeafSelectedEnabled(false);
		}
		return refaccsubj;
	}

	private UIRefCellEditor getUIRefCellEditor() {
		UIRefCellEditor editor = new UIRefCellEditor(getRefSubj());
		editor.addCellEditorListener(new CellEditorListener() {

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

	public void afterEdit() {
		int colcount = tablemodel.getColumnCount();
		currentRow = getTable().getSelectedRow();
		String pkaccsubj = refaccsubj.getRefPK();
		if (pkaccsubj == null || pkaccsubj.length() == 0) {
			return;
		}
		hashsubj.put(pk_system + pk_glorgbook
				+ String.valueOf(new Integer(currentRow)), pkaccsubj);
		String subjname = refaccsubj.getRefName();
		getTablemodel().setValueAt(subjname, currentRow, colcount - 1);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCmbSystem()) {
			try {
				onRefresh();
			} catch (Exception e1) {

				e1.printStackTrace();
			}

		}

	}

	public void valueChanged(ValueChangedEvent event) {
		pk_glorgbook = getRefGlorgbook().getRefPK();
		try {
			onRefresh();
			onState(0);
		} catch (Exception e) {
			e.printStackTrace();
			Debug.error(e.getMessage(), e);
		}
	}
}
