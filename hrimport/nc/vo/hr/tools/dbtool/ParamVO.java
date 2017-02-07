package nc.vo.hr.tools.dbtool;

/**
 * �ӽ����ϴ��ݵĲ��������ݽṹ��
 * �������ڣ�(2003-11-25 20:25:04)
 * @author��Administrator
 */
public class ParamVO extends nc.vo.pub.ValueObject {
	public String datasource = null; //����Դ
	public String filepath = null; //�ļ�·��
	public int selMode = -1; //����ģʽ
	public String pk_corp = null; //��˾����
	
	private int sheetIndex = 0;	// excel sheet index.
	
/**
 * ParamVO ������ע�⡣
 */
public ParamVO() {
	super();
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getDatasource() {
	return datasource;
}
/**
 * ������ֵ�������ʾ���ơ�
 * 
 * �������ڣ�(2001-2-15 14:18:08)
 * @return java.lang.String ������ֵ�������ʾ���ơ�
 */
public String getEntityName() {
	return null;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getFilepath() {
	return filepath;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @return java.lang.String
 */
public java.lang.String getPk_corp() {
	return pk_corp;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @return int
 */
public int getSelMode() {
	return selMode;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @param newDatasource java.lang.String
 */
public void setDatasource(java.lang.String newDatasource) {
	datasource = newDatasource;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @param newFilepath java.lang.String
 */
public void setFilepath(java.lang.String newFilepath) {
	filepath = newFilepath;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @param newPk_corp java.lang.String
 */
public void setPk_corp(java.lang.String newPk_corp) {
	pk_corp = newPk_corp;
}
/**
 * �˴����뷽��������
 * �������ڣ�(2003-11-25 20:28:39)
 * @param newSelMode int
 */
public void setSelMode(int newSelMode) {
	selMode = newSelMode;
}
/**
 * ��֤���������֮��������߼���ȷ�ԡ�
 * 
 * �������ڣ�(2001-2-15 11:47:35)
 * @exception nc.vo.pub.ValidationException �����֤ʧ�ܣ��׳�
 *     ValidationException���Դ�����н��͡�
 */
public void validate() throws nc.vo.pub.ValidationException {}
	/**
	 * @return ���� sheetIndex��
	 */
	public int getSheetIndex() {
		return sheetIndex;
	}
	/**
	 * @param sheetIndex Ҫ���õ� sheetIndex��
	 */
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
}
