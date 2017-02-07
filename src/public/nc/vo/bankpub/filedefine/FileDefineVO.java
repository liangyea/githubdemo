package nc.vo.bankpub.filedefine;

import nc.vo.pub.SuperVO;

public class FileDefineVO extends SuperVO {

	public String pk_filedefine = null;
	public Integer xh = null;
	public String columnname = null;
	public String datatype = null;  //0 String 1 NUMBER
	public Integer length = null;
	public String pk_system = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_filedefine";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_filedefine";
	}
	public String toString()
	{
		return xh.toString()+"."+columnname ;
	}

	public String getColumnname() {
		return columnname;
	}


	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}


	public String getDatatype() {
		return datatype;
	}


	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public String getPk_filedefine() {
		return pk_filedefine;
	}


	public void setPk_filedefine(String pk_filedefine) {
		this.pk_filedefine = pk_filedefine;
	}

	public Integer getXh() {
		return xh;
	}


	public void setXh(Integer xh) {
		this.xh = xh;
	}


	public String getPk_system() {
		return pk_system;
	}


	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}

}
