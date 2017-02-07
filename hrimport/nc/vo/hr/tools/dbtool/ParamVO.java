package nc.vo.hr.tools.dbtool;

/**
 * 从界面上传递的参数的数据结构。
 * 创建日期：(2003-11-25 20:25:04)
 * @author：Administrator
 */
public class ParamVO extends nc.vo.pub.ValueObject {
	public String datasource = null; //数据源
	public String filepath = null; //文件路径
	public int selMode = -1; //导入模式
	public String pk_corp = null; //公司主键
	
	private int sheetIndex = 0;	// excel sheet index.
	
/**
 * ParamVO 构造子注解。
 */
public ParamVO() {
	super();
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getDatasource() {
	return datasource;
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
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getFilepath() {
	return filepath;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getPk_corp() {
	return pk_corp;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @return int
 */
public int getSelMode() {
	return selMode;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @param newDatasource java.lang.String
 */
public void setDatasource(java.lang.String newDatasource) {
	datasource = newDatasource;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @param newFilepath java.lang.String
 */
public void setFilepath(java.lang.String newFilepath) {
	filepath = newFilepath;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @param newPk_corp java.lang.String
 */
public void setPk_corp(java.lang.String newPk_corp) {
	pk_corp = newPk_corp;
}
/**
 * 此处插入方法描述。
 * 创建日期：(2003-11-25 20:28:39)
 * @param newSelMode int
 */
public void setSelMode(int newSelMode) {
	selMode = newSelMode;
}
/**
 * 验证对象各属性之间的数据逻辑正确性。
 * 
 * 创建日期：(2001-2-15 11:47:35)
 * @exception nc.vo.pub.ValidationException 如果验证失败，抛出
 *     ValidationException，对错误进行解释。
 */
public void validate() throws nc.vo.pub.ValidationException {}
	/**
	 * @return 返回 sheetIndex。
	 */
	public int getSheetIndex() {
		return sheetIndex;
	}
	/**
	 * @param sheetIndex 要设置的 sheetIndex。
	 */
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
}
