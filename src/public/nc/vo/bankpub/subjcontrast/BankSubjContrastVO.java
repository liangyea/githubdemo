package nc.vo.bankpub.subjcontrast;

import nc.vo.pub.SuperVO;

public class BankSubjContrastVO extends SuperVO {


	public String pk_banksubjcontrast = null;
	public String pk_system = null;
	public String pk_glorgbook = null;
	public String pk_accsubj = null;
	public String def1 = null;
	public String def2 = null;
	public String def3 = null;
	public String def4 = null;
	public String def5 = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_banksubjcontrast";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_banksubjcontrast";
	}


	public String getDef1() {
		return def1;
	}


	public void setDef1(String def1) {
		this.def1 = def1;
	}


	public String getDef2() {
		return def2;
	}


	public void setDef2(String def2) {
		this.def2 = def2;
	}


	public String getDef3() {
		return def3;
	}


	public void setDef3(String def3) {
		this.def3 = def3;
	}


	public String getDef4() {
		return def4;
	}


	public void setDef4(String def4) {
		this.def4 = def4;
	}


	public String getDef5() {
		return def5;
	}


	public void setDef5(String def5) {
		this.def5 = def5;
	}


	public String getPk_accsubj() {
		return pk_accsubj;
	}


	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}


	public String getPk_banksubjcontrast() {
		return pk_banksubjcontrast;
	}


	public void setPk_banksubjcontrast(String pk_banksubjcontrast) {
		this.pk_banksubjcontrast = pk_banksubjcontrast;
	}


	public String getPk_glorgbook() {
		return pk_glorgbook;
	}


	public void setPk_glorgbook(String pk_glorgbook) {
		this.pk_glorgbook = pk_glorgbook;
	}


	public String getPk_system() {
		return pk_system;
	}


	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}

}
