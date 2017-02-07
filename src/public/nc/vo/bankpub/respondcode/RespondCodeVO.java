package nc.vo.bankpub.respondcode;

import nc.vo.pub.SuperVO;

public class RespondCodeVO extends SuperVO {

	
	
	public String pk_respondcode = null;
	public String respondindex = null;
	public String respondcode = null;
	public String hintinfo = null;
	public String memo = null;
	
	
	
	
	@Override
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_respondcode";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_respondcode";
	}

	public String getHintinfo() {
		return hintinfo;
	}

	public void setHintinfo(String hintinfo) {
		this.hintinfo = hintinfo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPk_respondcode() {
		return pk_respondcode;
	}

	public void setPk_respondcode(String pk_respondcode) {
		this.pk_respondcode = pk_respondcode;
	}

	public String getRespondcode() {
		return respondcode;
	}

	public void setRespondcode(String respondcode) {
		this.respondcode = respondcode;
	}

	public String getRespondindex() {
		return respondindex;
	}

	public void setRespondindex(String respondindex) {
		this.respondindex = respondindex;
	}

}
