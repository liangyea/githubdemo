package nc.vo.bankpub.subjcontrast;

import nc.vo.pub.SuperVO;

public class NCSubjContrastVO extends SuperVO {

	public String pk_ncsubjcontrast = null;
	public String pk_glorgbook = null;
	public String pk_accsubj = null;
	public String pk_dept = null;
	public String bank_kmbm = null;
	public String istrans = null;
	public String isAccount = null;
	public String balbank_kmbm = null;
	
	// 非数据库
	public String nckmbm = null;
	public String nckmmc = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_ncsubjcontrast";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_ncsubjcontrast";
	}


	public String getBank_kmbm() {
		return bank_kmbm;
	}


	public void setBank_kmbm(String bank_kmbm) {
		this.bank_kmbm = bank_kmbm;
	}


	public String getIstrans() {
		return istrans;
	}


	public void setIstrans(String istrans) {
		this.istrans = istrans;
	}


	/*public String getPk_accsubj() {
		return pk_accsubj;
	}


	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}*/


	public String getPk_glorgbook() {
		return pk_glorgbook;
	}


	public void setPk_glorgbook(String pk_glorgbook) {
		this.pk_glorgbook = pk_glorgbook;
	}


	public String getPk_ncsubjcontrast() {
		return pk_ncsubjcontrast;
	}


	public void setPk_ncsubjcontrast(String pk_ncsubjcontrast) {
		this.pk_ncsubjcontrast = pk_ncsubjcontrast;
	}
	public String getInsertSql()
	{
		StringBuffer sb = new StringBuffer();
        sb.append("insert into bank_ncsubjcontrast(pk_ncsubjcontrast,pk_accsubj,bank_kmbm,pk_glorgbook,istrans,isAccount,balbank_kmbm) values(");
        sb.append((new StringBuilder("'")).append(getPk_ncsubjcontrast()).append("',").toString());
        sb.append((new StringBuilder("'")).append(getPk_accsubj()).append("',").toString());
        sb.append((new StringBuilder("'")).append(getBank_kmbm() != null ? getBank_kmbm() : "#").append("',").toString());
        sb.append((new StringBuilder("'")).append(getPk_glorgbook()).append("',").toString());
        sb.append((new StringBuilder("'")).append(getIstrans()).append("',").toString());
        sb.append((new StringBuilder("'")).append(getIsAccount()).append("',").toString());
        sb.append((new StringBuilder("'")).append(getBalbank_kmbm()).append("')").toString());
        return sb.toString();

	}
	
	public String getUpdateSql()
	{
		StringBuffer sb = new StringBuffer();
        sb.append((new StringBuilder("update bank_ncsubjcontrast set pk_accsubj='")).append(getPk_accsubj()).append("',").toString());
        sb.append((new StringBuilder(" bank_kmbm='")).append(getBank_kmbm() != null ? getBank_kmbm() : "#").append("',").toString());
        sb.append((new StringBuilder(" pk_glorgbook='")).append(getPk_glorgbook()).append("', ").toString());
        sb.append((new StringBuilder(" istrans='")).append(getIstrans()).append("', ").toString());
        sb.append((new StringBuilder(" isAccount='")).append(getIsAccount()).append("', ").toString());
        sb.append((new StringBuilder(" balbank_kmbm='")).append(getBalbank_kmbm()).append("'").toString());
        sb.append((new StringBuilder(" where pk_ncsubjcontrast='")).append(getPk_ncsubjcontrast()).append("' ").toString());
        return sb.toString();
	}


	public String getIsAccount() {
		return isAccount;
	}


	public void setIsAccount(String isAccount) {
		this.isAccount = isAccount;
	}


	public String getBalbank_kmbm() {
		return balbank_kmbm;
	}


	public void setBalbank_kmbm(String balbank_kmbm) {
		this.balbank_kmbm = balbank_kmbm;
	}


	public String getPk_accsubj() {
		return pk_accsubj;
	}


	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}

	public String getPk_dept() {
		return pk_dept;
	}
	
	
	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}
	
}
