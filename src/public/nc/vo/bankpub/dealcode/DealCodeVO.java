package nc.vo.bankpub.dealcode;

import nc.vo.pub.SuperVO;

public class DealCodeVO extends SuperVO {

	
	public String pk_dealcode = null;
	public String dealindex = null;
	public String dealcontent = null;
	public String dealcode = null;
	
	
	@Override
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_dealcode";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_dealcode";
	}

	public String getDealcode() {
		return dealcode;
	}

	public void setDealcode(String dealcode) {
		this.dealcode = dealcode;
	}

	public String getDealcontent() {
		return dealcontent;
	}

	public void setDealcontent(String dealcontent) {
		this.dealcontent = dealcontent;
	}

	public String getDealindex() {
		return dealindex;
	}

	public void setDealindex(String dealindex) {
		this.dealindex = dealindex;
	}

	public String getPk_dealcode() {
		return pk_dealcode;
	}

	public void setPk_dealcode(String pk_dealcode) {
		this.pk_dealcode = pk_dealcode;
	}

}
