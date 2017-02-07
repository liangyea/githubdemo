package nc.ui.bankpub.ncsubject.ass;

import javax.swing.table.TableColumn;

import nc.ui.bd.manage.UIRefCellEditor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITable;
import nc.vo.bankpub.ncsubject.ass.SimplSubjAssConstrastVO;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastFactory;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bankpub.util.CompareUtil;

public class SubjAssConstrastTable extends UITable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubjAssConstrastTable() {
		super();
	}

	public SubjAssConstrastTableModel getSubjAssConstrastTableModel() {
		return (SubjAssConstrastTableModel) super.getModel();
	}

	public boolean isCellEditable(int row, int col) {
		if (!super.isCellEditable(row, col))
			return false;
		String colName = getColumnName(col);
		if (CompareUtil.equals(colName, SubjAssConstrastTableModel.BANK_ZH)) {
			return true;
		}
		TableColumn column = getColumn(colName);
		UIRefCellEditor cellEditor = (UIRefCellEditor) column.getCellEditor();
		UIRefPane refPane = (UIRefPane) cellEditor.getComponent();
		SimplSubjAssConstrastVO assVo = (SimplSubjAssConstrastVO)getModel().getValueAt(
				row, col);
		if (assVo != null) {
			refPane.setPK(assVo.getPk_assvalue());
		}
		return true;

	}
	public Object getValueAt(int row, int col) {
		SubjAssConstrastTableModel model = (SubjAssConstrastTableModel) getModel();
		String colName = getColumnName(col);
		Object obj = model.getValueAt(row, col);
		if (obj == null) return null;
		if (CompareUtil.equals(colName, SubjAssConstrastTableModel.BANK_ZH)) {
			return obj;
		}
		else {
			return ((SimplSubjAssConstrastVO) obj).getAssvaluename();
		}
		
	}
	public void setValueAt(Object obj, int row, int col) {
		SubjAssConstrastTableModel model = (SubjAssConstrastTableModel) getModel();
		String colName = getColumnName(col);
		SubjAssConstrastVO subvo = (SubjAssConstrastVO) model.getVO(row);
		if (subvo == null) {
			return;
		}
		if (CompareUtil.equals(colName, SubjAssConstrastTableModel.BANK_ZH)) {
			subvo.setBank_zh((String)obj);
			return;
		}
		

		SimplSubjAssConstrastVO assVo = subvo.getAssVo(colName);
		if (assVo == null) {
			assVo = SubjAssConstrastFactory
					.createSimpleSubjAssConstrastVo(model.findColAssVo(colName));
			subvo.addAssVo(assVo);
		}
		TableColumn column = getColumn(colName);
		UIRefCellEditor cellEditor = (UIRefCellEditor) column.getCellEditor();
		UIRefPane refPane = (UIRefPane) cellEditor.getComponent();
		if (assVo == null) {
			assVo = (SimplSubjAssConstrastVO) model.findColAssVo(colName)
					.clone();
		}
		assVo.setPk_assvalue(refPane.getRefPK());
		assVo.setAssvaluecode(refPane.getRefCode());
		assVo.setAssvaluename(refPane.getRefName());
	}

}
