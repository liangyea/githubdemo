package nc.vo.bankpub.subjbalrule;

import nc.vo.pub.SuperVO;

public class SubjBalRuleVO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nc_subjcodes;	
	private String bank_subjcodes;
	private String pk_subjbalrule;
	
	public String getPrimaryKey() {

		return pk_subjbalrule;
	}

	public void setPrimaryKey(String newPk_datatype) {

		pk_subjbalrule = newPk_datatype;
	}
	
	public String getBank_subjcodes() {
		return bank_subjcodes;
	}

	public void setBank_subjcodes(String bank_subjcodes) {
		this.bank_subjcodes = bank_subjcodes;
	}

	public String getNc_subjcodes() {
		return nc_subjcodes;
	}

	public void setNc_subjcodes(String nc_subjcodes) {
		this.nc_subjcodes = nc_subjcodes;
	}

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_subjbalrule";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "bank_subjbalrule";
	}

}
