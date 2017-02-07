package nc.vo.bankpub.ncsubject.ass;

import nc.vo.bankpub.util.StringUtils;
import nc.vo.pub.SuperVO;

public class SimplSubjAssConstrastVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pk_subjassconstrast;

	/** 会计科目 */
	private String pk_accsubj;

	/** 核心账户 */
	private String bankzh;

	/** 核心帐户名称 */
	private String bankzhname;

	private String pk_asstype;

	private String pk_assvalue;

	private String m_asstypecode;

	private String m_asstypename;

	private String m_assvaluecode;

	private String m_assvaluename;
	
	private String pk_subjassconstrast_rowid;


    public String getPk_subjassconstrast_rowid() {
		return pk_subjassconstrast_rowid;
	}

	public void setPk_subjassconstrast_rowid(String pk_subjassconstrast_rowid) {
		this.pk_subjassconstrast_rowid = pk_subjassconstrast_rowid;
	}

	public void setPrimaryKey(String key) {
    	pk_subjassconstrast = key; 
    }
    
    public String getPrimaryKey() {
    	return pk_subjassconstrast;
    }
	public String getPk_asstype() {
		return pk_asstype;
	}

	public void setPk_asstype(String pk_asstype) {
		this.pk_asstype = pk_asstype;
	}

	public String getPk_assvalue() {
		return pk_assvalue;
	}

	public void setPk_assvalue(String pk_assvalue) {
		this.pk_assvalue = pk_assvalue;
	}

	public String getBankzh() {
		return bankzh;
	}

	public void setBankzh(String bank_zh) {
		this.bankzh = bank_zh;
	}

	public String getBankzhname() {
		return bankzhname;
	}

	public void setBankzhname(String bank_zhname) {
		this.bankzhname = bank_zhname;
	}

	public String getPk_accsubj() {
		return pk_accsubj;
	}

	public void setPk_accsubj(String pk_accsubj) {
		this.pk_accsubj = pk_accsubj;
	}

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_subjassconstrast";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "bank_subjassconstrast";
	}

	public String getAsstypecode() {
		return m_asstypecode;
	}

	public void setAsstypecode(String m_asstypecode) {
		this.m_asstypecode = m_asstypecode;
	}

	public String getAsstypename() {
		return m_asstypename;
	}

	public void setAsstypename(String m_asstypename) {
		this.m_asstypename = m_asstypename;
	}

	public String getAssvaluecode() {
		return m_assvaluecode;
	}

	public void setAssvaluecode(String m_assvaluecode) {
		this.m_assvaluecode = m_assvaluecode;
	}

	public String getAssvaluename() {
		return m_assvaluename;
	}

	public void setAssvaluename(String m_assvaluename) {
		this.m_assvaluename = m_assvaluename;
	}

	public boolean isNull() {
		return StringUtils.isNull(getPk_accsubj())
				|| StringUtils.isNull(getBankzh())
				|| StringUtils.isNull(getPk_assvalue());
	}

}
