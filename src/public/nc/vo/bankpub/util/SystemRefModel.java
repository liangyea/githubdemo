package nc.vo.bankpub.util;

import nc.ui.bd.ref.AbstractRefModel;

public class SystemRefModel extends AbstractRefModel {
	public SystemRefModel() {
		super();
	}

	@Override
	public int getDefaultFieldCount() {
		return 2;
	}

	@Override
	public String[] getFieldCode() {
		return new String[] { "systemcode", "systemname" };
	}

	@Override
	public String[] getFieldName() {
		return new String[] { "编码", "名称" };
	}

	@Override
	public String[] getHiddenFieldCode() {
		return new String[] { "pk_system" };
	}

	@Override
	public String getPkFieldCode() {

		return "pk_system";

	}

	@Override
	public String getRefCodeField() {

		return "systemcode";

	}

	@Override
	public String getRefNameField() {

		return "systemname";

	}

	@Override
	public String getRefTitle() {

		return "系统设置参照";

	}

	@Override
	public String getTableName() {

		return "bank_system";

	}

}
