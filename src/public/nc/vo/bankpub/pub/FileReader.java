package nc.vo.bankpub.pub;

/**
 * 此处插入类型说明。
 * 创建日期：(2004-12-8 14:23:14)
 * @author：张春明
 */
import java.io.FileInputStream;
import java.util.Vector;

public class FileReader implements IOPUBCONST
{
	private int m_iExcelPage = 0;
	private String m_sFileName = "";
	private int m_iFiletype = 0;//文件类别
	private int m_iColCounts = 0;
	/*----------------------------------------------------
	  读取结果。数组至少3行，具体结构为：
	  0行－列键值（用表字段名字）
	  1行－数据类型
	  2行－列名称
	  3～n行－具体数据
	----------------------------------------------------*/
	private String m_sResult[][] = null;//读取文件结果：
	private org.apache.poi.hssf.usermodel.HSSFWorkbook wb = null;
/**
 * FileReader 构造子注解。
 */
public FileReader() {
	super();
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-9 21:51:58)
 * @return java.lang.String[]
 * @exception java.lang.Exception 异常说明。
 */
public String[] getAllSheets(String filename) throws java.lang.Exception 
{
	String sFile = filename;
	if (sFile == null || sFile.trim().equals(""))
	{
		throw new Exception("文件名不能为空！");
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
 * 此处插入方法说明。
 * 创建日期：(2004-12-9 22:08:17)
 * @return int
 */
public int getColCounts() {
	return m_iColCounts;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-9 10:03:41)
 * @return int
 */
public int getExcelPage() {
	return m_iExcelPage;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:01:26)
 * @return java.lang.String
 */
public java.lang.String getFileName() {
	return m_sFileName;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:40:34)
 * @return int
 */
public int getFiletype() {
	return m_iFiletype;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:01:26)
 * @return java.lang.String[][]
 */
public java.lang.String[][] getResult() {
	return m_sResult;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:40:34)
 * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
 */
public org.apache.poi.hssf.usermodel.HSSFWorkbook getWb() {
	return wb;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 14:56:33)
 * @exception java.lang.Exception 异常说明。
 */
public void readExcel() throws java.lang.Exception
{
	m_sResult = null;
	String sFile = getFileName();
	if (sFile == null || sFile.trim().equals(""))
	{
		throw new Exception("文件名不能为空！");
		//return;
	}
	java.io.File f = new java.io.File(sFile);
	FileInputStream fis = new java.io.FileInputStream(f);

	wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fis);
	int iNum = wb.getNumberOfSheets();
	if (iNum <= 0)
		throw new Exception("Excel文件为空！");
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
 * 此处插入方法说明。
 * 创建日期：(2003-10-14 9:38:26)
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
 * 此处插入方法说明。
 * 创建日期：(2004-12-9 22:08:17)
 * @param newColCounts int
 */
public void setColCounts(int newColCounts) {
	m_iColCounts = newColCounts;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-9 10:03:41)
 * @param newExcelPage int
 */
public void setExcelPage(int newExcelPage) {
	m_iExcelPage = newExcelPage;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:01:26)
 * @param newFileName java.lang.String
 */
public void setFileName(java.lang.String newFileName) {
	m_sFileName = newFileName;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:40:34)
 * @param newFiletype int
 */
public void setFiletype(int newFiletype) {
	m_iFiletype = newFiletype;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:01:26)
 * @param newResult java.lang.String[][]
 */
public void setResult(java.lang.String[][] newResult) {
	m_sResult = newResult;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:40:34)
 * @param newWb org.apache.poi.hssf.usermodel.HSSFWorkbook
 */
public void setWb(org.apache.poi.hssf.usermodel.HSSFWorkbook newWb) {
	wb = newWb;
}
/**
 * 此处插入方法说明。
 * 创建日期：(2004-12-8 15:31:03)
 * @param strMsg java.lang.String
 */
public void showErrMsg(String strMsg)
{
	
}
}
