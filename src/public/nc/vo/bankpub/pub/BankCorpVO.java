package nc.vo.bankpub.pub;

/**
 * 
 * �������� : ��˾VO
 * 
 * ���� : ���
 * 
 * �������� : (2003-9-4 11:40:02)
 * 
 * �޸ļ�¼������ : 
 * 
 * �޸��� : 
 * 
 * ��ע: 
 */
public final class BankCorpVO extends nc.vo.pub.ValueObject {
	public String pk_glorgbook;
	public String unitcode;
	public String unitname;
	private final String DISP_LEFT = " [ "; //��ʾ�ַ�-��
	private final String DISP_RIGHT = " ] "; //��ʾ�ַ�-��
/**
 * BankCorpVO ������ע�⡣
 */
public BankCorpVO() {
	super();
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
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @return java.lang.String
 */
public java.lang.String getPk_glorgbook() {
	return pk_glorgbook;
}
/**
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @return java.lang.String
 */
public java.lang.String getUnitcode() {
	return unitcode;
}
/**
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @return java.lang.String
 */
public java.lang.String getUnitname() {
	return unitname;
}
/**
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @param newPk_corp java.lang.String
 */
public void setPk_glorgbook(java.lang.String newPk_corp) {
	pk_glorgbook = newPk_corp;
}
/**
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @param newUnitcode java.lang.String
 */
public void setUnitcode(java.lang.String newUnitcode) {
	unitcode = newUnitcode;
}
/**
 * 
 * ��������:
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-4 11:40:29)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @param newUnitname java.lang.String
 */
public void setUnitname(java.lang.String newUnitname) {
	unitname = newUnitname;
}
/**
 * ���ر�ʾ�˶����ֵ���ַ�����
 * @return ���������ַ�����ʾ��
 */
public String toString() {
	return DISP_LEFT + getUnitcode() + DISP_RIGHT + getUnitname();
}
/**
 * ��֤���������֮��������߼���ȷ�ԡ�
 * 
 * �������ڣ�(2001-2-15 11:47:35)
 * @exception nc.vo.pub.ValidationException �����֤ʧ�ܣ��׳�
 *     ValidationException���Դ�����н��͡�
 */
public void validate() throws nc.vo.pub.ValidationException {}
}
