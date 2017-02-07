package nc.vo.bankpub.vouchertype;

import nc.vo.pub.SuperVO;

public class VouTypeVO extends SuperVO {

	
	public String pk_bankvouchertype = null;
	public String pk_vouchertype = null;
	public String istrans = null;
	
	//非数据库字段
	public String forshort = null;
	public String vouchtypename = null;
	
	@Override
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_bankvouchertype";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_vouchertype";
	}

	public String getIstrans() {
		return istrans;
	}

	public void setIstrans(String istrans) {
		this.istrans = istrans;
	}

	public String getPk_bankvouchertype() {
		return pk_bankvouchertype;
	}

	public void setPk_bankvouchertype(String pk_bankvouchertype) {
		this.pk_bankvouchertype = pk_bankvouchertype;
	}

	public String getPk_vouchertype() {
		return pk_vouchertype;
	}

	public void setPk_vouchertype(String pk_vouchertype) {
		this.pk_vouchertype = pk_vouchertype;
	}

}
