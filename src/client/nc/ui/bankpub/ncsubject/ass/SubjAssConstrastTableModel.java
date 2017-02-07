package nc.ui.bankpub.ncsubject.ass;

import nc.ui.pub.beans.table.VOTableModel;
import nc.vo.bankpub.ncsubject.ass.SubjAssConstrastVO;
import nc.vo.bankpub.util.CompareUtil;
import nc.vo.glcom.ass.AssVO;
import nc.vo.pub.ValueObject;

public class SubjAssConstrastTableModel extends VOTableModel {

	private SubjAssConstrastDataModel dataModel;

	public static final String BANK_ZH = "核心账户";

	private boolean isEditable = false;

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public SubjAssConstrastTableModel(Class c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	public SubjAssConstrastTableModel(SubjAssConstrastDataModel dataModel) {
		super(SubjAssConstrastVO.class);
		this.dataModel = dataModel;
//		super.addVO();
	}

	public SubjAssConstrastDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(SubjAssConstrastDataModel dataModel) {
		this.dataModel = dataModel;
		clearTable();
		super.addVO(dataModel.toArray());
	}

	public void addVO(ValueObject[] vos) {
		if (vos == null || vos.length == 0) return; 
		super.addVO(vos);
		if (dataModel != null)
		dataModel.addAssConstrast((SubjAssConstrastVO[]) vos);
	}
	
	public void addVO_super(ValueObject[] vos) {
		if (vos == null || vos.length == 0) return; 
		super.addVO(vos);
		
	}

	public void addVO(ValueObject vo) {
		if (vo == null) return;
		super.addVO(vo);
		if (dataModel != null)
		dataModel.addAssConstrast((SubjAssConstrastVO) vo);
	}

	public void insertDefaultAssConstrast(int index) {
		dataModel.insertDefaultAssConstrast(index);
		super.insertVO(dataModel.getAssConstrast(index), index);

	}

	public void addDefaultAssConstrast() {
		dataModel.addDefaultAssConstrast();
		super
				.addVO(dataModel
						.getAssConstrast(dataModel.sizeAssConstrast() - 1));
	}

	public void insertVO(ValueObject vo, int row) {
		
		super.insertVO(vo, row);
		dataModel.insertAssConstrast((SubjAssConstrastVO) vo, row);
	}

	public void removeVO(ValueObject vo) {
		super.removeVO(vo);
		dataModel.remove((SubjAssConstrastVO) vo);
	}

	public void removeVO(int nRow) {
		super.removeVO(nRow);
		dataModel.removeAssConstrast(nRow);
	}

	public Class getColumnClass(int col) {
		return String.class;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		// Object obj = columns.get(col);
		if (col >= dataModel.getAssVos().size()) {
			return BANK_ZH;
		}

		AssVO assVo = dataModel.getAssVos().get(col);
		return assVo.getChecktypename();

	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		// 辅助核算加最后一列核心账户
		return dataModel.getAssVos().size() + 1;
	}

	public Object getValueAt(int row, int col) {

		SubjAssConstrastVO subvo = (SubjAssConstrastVO) getVO(row);
		if (subvo == null)
			return null;
		String colName = getColumnName(col);
		if (CompareUtil.equals(colName, BANK_ZH)) {
			return subvo.getBank_zh();
		}

		return subvo.getAssVo(colName);
	}

	public boolean isCellEditable(int row, int col) {
		return isEditable;
	}

	public AssVO findColAssVo(String asstypename) {
		return dataModel.getAssVos().findBy(asstypename);
	}

	public void backupSubjAssConstrasts() {
		dataModel.backupSubjAssConstrasts();
	}

	public void restoreSubjAssConstrasts() {
		dataModel.restoreSubjAssConstrasts();
		clearTable();
		super.addVO(dataModel.toArray());
	}
}
