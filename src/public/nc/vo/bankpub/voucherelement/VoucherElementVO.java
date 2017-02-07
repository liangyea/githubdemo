package nc.vo.bankpub.voucherelement;

import nc.vo.pub.SuperVO;

public class VoucherElementVO extends SuperVO {

	public String pk_voucherelement = null;
	public String pk_system = null;
	public String pk_corpxh = null;
	public String pk_currtypexh = null;
	public String money = null;
	public String dcorient = null; //1 借 2 贷
	public String subjcode = null;
	public String datecol = null; 
	public String deptass = null; 
	public String inoutflag = null;
	
	// 非数据库
	public String colcorp = null;
	public String colcurrtype = null;
	public String colmoney = null;
	public String coldcorient = null;
	public String colsubjcode = null;
	public String coldatecol = null;
	public String coldeptass = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_voucherelement";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_voucherelement";
	}


	public String getDcorient() {
		return dcorient;
	}


	public void setDcorient(String dcorient) {
		this.dcorient = dcorient;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}

	public String getPk_system() {
		return pk_system;
	}


	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}


	public String getPk_voucherelement() {
		return pk_voucherelement;
	}


	public void setPk_voucherelement(String pk_voucherelement) {
		this.pk_voucherelement = pk_voucherelement;
	}


	public String getSubjcode() {
		return subjcode;
	}


	public void setSubjcode(String subjcode) {
		this.subjcode = subjcode;
	}

	public String getDatecol() {
		return datecol;
	}


	public void setDatecol(String datecol) {
		this.datecol = datecol;
	}


	public String getPk_corpxh() {
		return pk_corpxh;
	}


	public void setPk_corpxh(String pk_corpxh) {
		this.pk_corpxh = pk_corpxh;
	}


	public String getPk_currtypexh() {
		return pk_currtypexh;
	}


	public void setPk_currtypexh(String pk_currtypexh) {
		this.pk_currtypexh = pk_currtypexh;
	}


	public String getDeptass() {
		return deptass;
	}


	public void setDeptass(String deptass) {
		this.deptass = deptass;
	}


	public String getInoutflag() {
		return inoutflag;
	}


	public void setInoutflag(String inoutflag) {
		this.inoutflag = inoutflag;
	}



}
