package nc.vo.bankpub.subjcolshow;

import nc.vo.pub.SuperVO;

public class SubjColShowVO extends SuperVO {

	public String pk_subjcolshow = null;
	public String pk_system = null;
	public String def1 = null;
	public String def2 = null;
	public String def3 = null;
	public String def4 = null;
	public String def5 = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_subjcolshow";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return "";
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_subjcolshow";
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


	public String getPk_subjcolshow() {
		return pk_subjcolshow;
	}


	public void setPk_subjcolshow(String pk_subjcolshow) {
		this.pk_subjcolshow = pk_subjcolshow;
	}


	public String getPk_system() {
		return pk_system;
	}


	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}

}
