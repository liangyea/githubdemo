package nc.vo.bankpub.pub;

/**
 * 
 * 功能描述 : 公司VO
 * 
 * 作者 : 李辉
 * 
 * 创建日期 : (2003-9-4 11:40:02)
 * 
 * 修改记录及日期 : 
 * 
 * 修改人 : 
 * 
 * 备注: 
 */
public final class BankCorpVO extends nc.vo.pub.ValueObject {
	public String pk_glorgbook;
	public String unitcode;
	public String unitname;
	private final String DISP_LEFT = " [ "; //显示字符-左
	private final String DISP_RIGHT = " ] "; //显示字符-右
/**
 * BankCorpVO 构造子注解。
 */
public BankCorpVO() {
	super();
}
/**
 * 返回数值对象的显示名称。
 * 
 * 创建日期：(2001-2-15 14:18:08)
 * @return java.lang.String 返回数值对象的显示名称。
 */
public String getEntityName() {
	return null;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @return java.lang.String
 */
public java.lang.String getPk_glorgbook() {
	return pk_glorgbook;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @return java.lang.String
 */
public java.lang.String getUnitcode() {
	return unitcode;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @return java.lang.String
 */
public java.lang.String getUnitname() {
	return unitname;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @param newPk_corp java.lang.String
 */
public void setPk_glorgbook(java.lang.String newPk_corp) {
	pk_glorgbook = newPk_corp;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @param newUnitcode java.lang.String
 */
public void setUnitcode(java.lang.String newUnitcode) {
	unitcode = newUnitcode;
}
/**
 * 
 * 函数功能:
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-4 11:40:29)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @param newUnitname java.lang.String
 */
public void setUnitname(java.lang.String newUnitname) {
	unitname = newUnitname;
}
/**
 * 返回表示此对象的值的字符串。
 * @return 接收器的字符串表示法
 */
public String toString() {
	return DISP_LEFT + getUnitcode() + DISP_RIGHT + getUnitname();
}
/**
 * 验证对象各属性之间的数据逻辑正确性。
 * 
 * 创建日期：(2001-2-15 11:47:35)
 * @exception nc.vo.pub.ValidationException 如果验证失败，抛出
 *     ValidationException，对错误进行解释。
 */
public void validate() throws nc.vo.pub.ValidationException {}
}
