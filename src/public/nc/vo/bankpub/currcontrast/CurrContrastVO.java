package nc.vo.bankpub.currcontrast;

import nc.vo.pub.SuperVO;

public class CurrContrastVO extends SuperVO {


	public String pk_currcontrast = null;
	public String pk_currtype = null;
	public String bank_currcode = null;
	public String bank_currname = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_currcontrast";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_currcontrast";
	}


	public String getBank_currcode() {
		return bank_currcode;
	}


	public void setBank_currcode(String bank_currcode) {
		this.bank_currcode = bank_currcode;
	}


	public String getBank_currname() {
		return bank_currname;
	}


	public void setBank_currname(String bank_currname) {
		this.bank_currname = bank_currname;
	}


	public String getPk_currcontrast() {
		return pk_currcontrast;
	}


	public void setPk_currcontrast(String pk_currcontrast) {
		this.pk_currcontrast = pk_currcontrast;
	}


	public String getPk_currtype() {
		return pk_currtype;
	}


	public void setPk_currtype(String pk_currtype) {
		this.pk_currtype = pk_currtype;
	}

}
