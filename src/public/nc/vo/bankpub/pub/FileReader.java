package nc.vo.bankpub.pub;

/**
 * �˴���������˵����
 * �������ڣ�(2004-12-8 14:23:14)
 * @author���Ŵ���
 */
import java.io.FileInputStream;
import java.util.Vector;

public class FileReader implements IOPUBCONST
{
	private int m_iExcelPage = 0;
	private String m_sFileName = "";
	private int m_iFiletype = 0;//�ļ����
	private int m_iColCounts = 0;
	/*----------------------------------------------------
	  ��ȡ�������������3�У�����ṹΪ��
	  0�У��м�ֵ���ñ��ֶ����֣�
	  1�У���������
	  2�У�������
	  3��n�У���������
	----------------------------------------------------*/
	private String m_sResult[][] = null;//��ȡ�ļ������
	private org.apache.poi.hssf.usermodel.HSSFWorkbook wb = null;
/**
 * FileReader ������ע�⡣
 */
public FileReader() {
	super();
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-9 21:51:58)
 * @return java.lang.String[]
 * @exception java.lang.Exception �쳣˵����
 */
public String[] getAllSheets(String filename) throws java.lang.Exception 
{
	String sFile = filename;
	if (sFile == null || sFile.trim().equals(""))
	{
		throw new Exception("�ļ�������Ϊ�գ�");
		//return;
	}
	java.io.File f = new java.io.File(sFile);
	FileInputStream fis = new java.io.FileInputStream(f);

	wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fis);
	int iNum = wb.getNumberOfSheets();
	String sre[] = new String[iNum];
	for(int i = 0;i<iNum;i++)
	{
		sre[i] = wb.getSheetName(i);
	}
	fis.close();
	return sre;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-9 22:08:17)
 * @return int
 */
public int getColCounts() {
	return m_iColCounts;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-9 10:03:41)
 * @return int
 */
public int getExcelPage() {
	return m_iExcelPage;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:01:26)
 * @return java.lang.String
 */
public java.lang.String getFileName() {
	return m_sFileName;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:40:34)
 * @return int
 */
public int getFiletype() {
	return m_iFiletype;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:01:26)
 * @return java.lang.String[][]
 */
public java.lang.String[][] getResult() {
	return m_sResult;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:40:34)
 * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
 */
public org.apache.poi.hssf.usermodel.HSSFWorkbook getWb() {
	return wb;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 14:56:33)
 * @exception java.lang.Exception �쳣˵����
 */
public void readExcel() throws java.lang.Exception
{
	m_sResult = null;
	String sFile = getFileName();
	if (sFile == null || sFile.trim().equals(""))
	{
		throw new Exception("�ļ�������Ϊ�գ�");
		//return;
	}
	java.io.File f = new java.io.File(sFile);
	FileInputStream fis = new java.io.FileInputStream(f);

	wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fis);
	int iNum = wb.getNumberOfSheets();
	if (iNum <= 0)
		throw new Exception("Excel�ļ�Ϊ�գ�");
	int iColnum = m_iColCounts;
	int idxsheet = m_iExcelPage;
	Vector vRes = readFileExcel(wb, idxsheet,iColnum);
	
	if(vRes == null || vRes.size() == 0)
		return;
	m_sResult = new String [vRes.size()][iColnum];
	vRes.copyInto(m_sResult);
	fis.close();
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2003-10-14 9:38:26)
 */
private Vector readFileExcel(org.apache.poi.hssf.usermodel.HSSFWorkbook hswb,int idxsheet,int icolnum) throws Exception
{
	org.apache.poi.hssf.usermodel.HSSFSheet sheet = hswb.getSheetAt(idxsheet);
	int iRownum = sheet.getLastRowNum()+1;
	org.apache.poi.hssf.usermodel.HSSFRow row = null;
	org.apache.poi.hssf.usermodel.HSSFCell cell = null;
	Vector vRes = new Vector();
	String sRow [] = null;
	Vector vRow = null;
	for(int i = 1;i<iRownum;i++)
	{
		//System.out.println(i);
		if(i == 221)
		{
			int m = 0;
			m++;
		}
		row = sheet.getRow(i);
		if(row == null)
			break;
		sRow = new String[icolnum];
		
		for(short j = 0;j<sRow.length;j++)
		{
			cell = row.getCell(j);
			
			if(cell == null)
				continue;
			if(cell.getCellType() == cell.CELL_TYPE_STRING)
				sRow[j] = cell.getStringCellValue();
			else
			{
				//System.out.println(cell.getNumericCellValue());
				sRow[j] = Integer.toString((int)cell.getNumericCellValue());
			}
		}
		//if(sRow[0] != null && !sRow[0].equals(""))
		vRes.addElement(sRow);
	}
	
	return vRes;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-9 22:08:17)
 * @param newColCounts int
 */
public void setColCounts(int newColCounts) {
	m_iColCounts = newColCounts;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-9 10:03:41)
 * @param newExcelPage int
 */
public void setExcelPage(int newExcelPage) {
	m_iExcelPage = newExcelPage;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:01:26)
 * @param newFileName java.lang.String
 */
public void setFileName(java.lang.String newFileName) {
	m_sFileName = newFileName;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:40:34)
 * @param newFiletype int
 */
public void setFiletype(int newFiletype) {
	m_iFiletype = newFiletype;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:01:26)
 * @param newResult java.lang.String[][]
 */
public void setResult(java.lang.String[][] newResult) {
	m_sResult = newResult;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:40:34)
 * @param newWb org.apache.poi.hssf.usermodel.HSSFWorkbook
 */
public void setWb(org.apache.poi.hssf.usermodel.HSSFWorkbook newWb) {
	wb = newWb;
}
/**
 * �˴����뷽��˵����
 * �������ڣ�(2004-12-8 15:31:03)
 * @param strMsg java.lang.String
 */
public void showErrMsg(String strMsg)
{
	
}
}
