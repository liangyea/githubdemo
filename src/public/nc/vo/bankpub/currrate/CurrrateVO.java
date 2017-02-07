package nc.vo.bankpub.currrate;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class CurrrateVO extends SuperVO {

	
	public String PK_CORP = null;
	public String PK_CURRINFO = null;
	public String PK_CURRRATE = null;
	public String PK_CURRTYPE = null;
	public UFDouble RATE = null;
	public UFDate RATEDATE = null;
	
	//非数据库
	public String currtypename = null;
	
	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "PK_CURRRATE";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}

	
	public String getTableName() {
		// TODO 自动生成方法存根
		return "bd_currrate";
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


	public String getPK_CURRRATE() {
		return PK_CURRRATE;
	}


	public void setPK_CURRRATE(String pk_currrate) {
		PK_CURRRATE = pk_currrate;
	}


	public String getPK_CURRTYPE() {
		return PK_CURRTYPE;
	}


	public void setPK_CURRTYPE(String pk_currtype) {
		PK_CURRTYPE = pk_currtype;
	}


	public UFDouble getRATE() {
		return RATE;
	}


	public void setRATE(UFDouble rate) {
		RATE = rate;
	}


	public UFDate getRATEDATE() {
		return RATEDATE;
	}


	public void setRATEDATE(UFDate ratedate) {
		RATEDATE = ratedate;
	}
	public String getInsertSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("insert into bd_currrate(PK_CORP,PK_CURRINFO,PK_CURRRATE,PK_CURRTYPE,RATE,RATEDATE) values(" );
		sb.append("'"+getPK_CORP()+"',");
		sb.append("'"+(getPK_CURRINFO()==null?"s":getPK_CURRINFO())+"',");
		sb.append("'"+getPK_CURRRATE()+"',");
		sb.append("'"+getPK_CURRTYPE()+"',");
		sb.append(""+getRATE().doubleValue()+",");
		sb.append("'"+getRATEDATE()+"')");
		String sql = sb.toString();
		return sql;
	}

}
