package nc.vo.bankpub.currrate;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class CurrAdjustRateVO extends SuperVO {
	
	public UFDouble ADJUSTRATE = null;
	public UFDouble AVERAGERATE = null;
	public UFDouble ENDRATE= null;
	public String  PK_ACCPERIOD  = null;
	public String  PK_ACCPERIODMONTH  = null;
	public String  PK_ACCPERIODSCHEME = null;
	public String  PK_ADJUSTRATE   = null;
	public String  PK_CORP      = null;
	public String  PK_CURRINFO  = null;
	public String  PK_CURRTYPE  = null;
	public String  RATEMONTH   = null;
	public String  RATEYEAR    = null;

	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "PK_ADJUSTRATE";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}
	public String getTableName() {
		// TODO 自动生成方法存根
		return "BD_ADJUSTRATE";
	}


	public UFDouble getADJUSTRATE() {
		return ADJUSTRATE;
	}


	public void setADJUSTRATE(UFDouble adjustrate) {
		ADJUSTRATE = adjustrate;
	}


	public UFDouble getAVERAGERATE() {
		return AVERAGERATE;
	}


	public void setAVERAGERATE(UFDouble averagerate) {
		AVERAGERATE = averagerate;
	}


	public UFDouble getENDRATE() {
		return ENDRATE;
	}


	public void setENDRATE(UFDouble endrate) {
		ENDRATE = endrate;
	}


	public String getPK_ACCPERIOD() {
		return PK_ACCPERIOD;
	}


	public void setPK_ACCPERIOD(String pk_accperiod) {
		PK_ACCPERIOD = pk_accperiod;
	}


	public String getPK_ACCPERIODMONTH() {
		return PK_ACCPERIODMONTH;
	}


	public void setPK_ACCPERIODMONTH(String pk_accperiodmonth) {
		PK_ACCPERIODMONTH = pk_accperiodmonth;
	}


	public String getPK_ACCPERIODSCHEME() {
		return PK_ACCPERIODSCHEME;
	}


	public void setPK_ACCPERIODSCHEME(String pk_accperiodscheme) {
		PK_ACCPERIODSCHEME = pk_accperiodscheme;
	}


	public String getPK_ADJUSTRATE() {
		return PK_ADJUSTRATE;
	}


	public void setPK_ADJUSTRATE(String pk_adjustrate) {
		PK_ADJUSTRATE = pk_adjustrate;
	}


	public String getPK_CORP() {
		return PK_CORP;
	}


	public void setPK_CORP(String pk_corp) {
		PK_CORP = pk_corp;
	}


	public String getPK_CURRINFO() {
		return PK_CURRINFO;
	}


	public void setPK_CURRINFO(String pk_currinfo) {
		PK_CURRINFO = pk_currinfo;
	}


	public String getPK_CURRTYPE() {
		return PK_CURRTYPE;
	}


	public void setPK_CURRTYPE(String pk_currtype) {
		PK_CURRTYPE = pk_currtype;
	}


	public String getRATEMONTH() {
		return RATEMONTH;
	}


	public void setRATEMONTH(String ratemonth) {
		RATEMONTH = ratemonth;
	}


	public String getRATEYEAR() {
		return RATEYEAR;
	}


	public void setRATEYEAR(String rateyear) {
		RATEYEAR = rateyear;
	}
	public String getInsertSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into bd_adjustrate(ADJUSTRATE,PK_ACCPERIOD,PK_ACCPERIODMONTH,PK_ACCPERIODSCHEME,");
		sb.append("PK_ADJUSTRATE,PK_CORP,PK_CURRINFO,PK_CURRTYPE,RATEMONTH,RATEYEAR) values(" );
		sb.append(""+getADJUSTRATE()+",");
		sb.append("'"+getPK_ACCPERIOD()+"',");
		sb.append("'"+getPK_ACCPERIODMONTH()+"',");
		sb.append("'"+getPK_ACCPERIODSCHEME()+"',");
		sb.append("'"+getPK_ADJUSTRATE()+"',");
		sb.append("'"+getPK_CORP()+"',");
		sb.append("'"+getPK_CURRINFO()+"',");
		sb.append("'"+getPK_CURRTYPE()+"',");
		sb.append("'"+getRATEMONTH()+"',");
		sb.append("'"+getRATEYEAR()+"')");
		String sql = sb.toString();
		return sql;
	}
}
