package nc.vo.bankpub.especialsubj;

import nc.vo.pub.SuperVO;

public class EspecialSubjVO extends SuperVO {

	public String pk_especialsubj = null;
	public String accsubjcode = null;
	public String accsubjname = null;
	public String dcflag = null; //1 ��  2��
	public String subjcode = null;
	
	//�����ݿ��ֶ�

	public String subjname = null;
	
	@Override
	public String getPKFieldName() {
		// TODO �Զ����ɷ������
		return "pk_especialsubj";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public String getTableName() {
		// TODO �Զ����ɷ������
		return "bank_especialsubj";
	}

	public String getDcflag() {
		return dcflag;
	}

	public void setDcflag(String dcflag) {
		this.dcflag = dcflag;
	}

	public String getPk_especialsubj() {
		return pk_especialsubj;
	}

	public void setPk_especialsubj(String pk_especialsubj) {
		this.pk_especialsubj = pk_especialsubj;
	}

	public String getSubjcode() {
		return subjcode;
	}

	public void setSubjcode(String subjcode) {
		this.subjcode = subjcode;
	}

	public String getAccsubjcode() {
		return accsubjcode;
	}

	public void setAccsubjcode(String accsubjcode) {
		this.accsubjcode = accsubjcode;
	}

	public String getAccsubjname() {
		return accsubjname;
	}

	public void setAccsubjname(String accsubjname) {
		this.accsubjname = accsubjname;
	}

}
