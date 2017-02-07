package nc.vo.bankpub.systemtype;

import nc.vo.pub.SuperVO;

public class SystemTypeVO extends SuperVO {

	public String pk_system = null;
	public String systemcode = null;
	public String systemname = null;
	public String dateformat = null;
	public String vouchertype = null;
	public String transmether = null;
	public String IPaddress = null;
	public String port = null;
	public String username = null;
	public String password = null;
	public String ftppath = null;
	public String localpath = null;
	public String isfixlen = null;
	public String separator = null;
	public String datafilename = null;
	public String ratefilename = null;
	public String accountfilename = null;
	public String suffixname = null;
	public String isprealert = null;
	
	//非数据库字段
	public String vouchertypename = null;

	public String getPKFieldName() {
		// TODO 自动生成方法存根
		return "pk_system";
	}


	public String getParentPKFieldName() {
		// TODO 自动生成方法存根
		return null;
	}


	public String getTableName() {
		// TODO 自动生成方法存根
		return "bank_system";
	}

	public String getIPaddress() {
		return IPaddress;
	}


	public void setIPaddress(String paddress) {
		IPaddress = paddress;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getPk_system() {
		return pk_system;
	}


	public void setPk_system(String pk_system) {
		this.pk_system = pk_system;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getSystemcode() {
		return systemcode;
	}


	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}


	public String getSystemname() {
		return systemname;
	}


	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}


	public String getTransmether() {
		return transmether;
	}


	public void setTransmether(String transmether) {
		this.transmether = transmether;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getVouchertype() {
		return vouchertype;
	}


	public void setVouchertype(String vouchertype) {
		this.vouchertype = vouchertype;
	}

	public String toString()
	{
		return systemname;
	}


	public String getFtppath() {
		return ftppath;
	}


	public void setFtppath(String ftppath) {
		this.ftppath = ftppath;
	}


	public String getIsfixlen() {
		return isfixlen;
	}


	public void setIsfixlen(String isfixlen) {
		this.isfixlen = isfixlen;
	}


	public String getLocalpath() {
		return localpath;
	}


	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}


	public String getSeparator() {
		return separator;
	}


	public void setSeparator(String separator) {
		this.separator = separator;
	}


	public String getDatafilename() {
		return datafilename;
	}


	public void setDatafilename(String datafilename) {
		this.datafilename = datafilename;
	}


	public String getRatefilename() {
		return ratefilename;
	}


	public void setRatefilename(String ratefilename) {
		this.ratefilename = ratefilename;
	}


	public String getSuffixname() {
		return suffixname;
	}


	public void setSuffixname(String suffixname) {
		this.suffixname = suffixname;
	}


	public String getDateformat() {
		return dateformat;
	}


	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}


	public String getIsprealert() {
		return isprealert;
	}


	public void setIsprealert(String isprealert) {
		this.isprealert = isprealert;
	}


	public String getAccountfilename() {
		return accountfilename;
	}


	public void setAccountfilename(String accountfilename) {
		this.accountfilename = accountfilename;
	}
}
