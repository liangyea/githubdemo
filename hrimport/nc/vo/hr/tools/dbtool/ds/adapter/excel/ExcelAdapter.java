package nc.vo.hr.tools.dbtool.ds.adapter.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.TableSet;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOException;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.adapter.ISaveListener;
import nc.vo.hr.tools.dbtool.util.DSLogger;
import nc.vo.hr.tools.dbtool.util.StringHelper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Describe:HR_PUB3.5
 * 		the excel load save adapter,now only realize the load .
 * @version 1.0	2006-4-209:53:58
 * @author 闫长海
 */
public class ExcelAdapter implements IDataAdapter {
	public static final String ROWNO = "@@ROWNO";
	public static final String SHEET_NUM = "SHEET_NUM";
	public static final String EXCEL_FILE = "EXCEL_FILE";	// USE THE INPUT STREAM OR THIS FILE NAME.
	public static final String HEADER_ROW = "HEADER_ROW";
	public static final String DATA_START_ROW = "DATA_START_ROW";
	
	private boolean	isStrTrim;
	
	public DataTable load(DataTable table, String filePath ) throws IOException {
		try {
			return load( table, new IOParam(new FileInputStream(filePath)) );
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("open the excel file fail");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IOException("open the excel file fail");
		}
	}
	
	public DataTable load(DataTable table, String filePath,int sheetIndex ) throws IOException {
		try {
			IOParam param = new IOParam(new FileInputStream(filePath));
			param.setProperty( SHEET_NUM, new Integer( sheetIndex ) );
			return load( table, param );
		} catch (IOException e) {
//			e.printStackTrace();
			throw new IOException("打开Excel文件出错:"+e.getMessage() );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IOException("open the excel file fail");
		}
	}
	
	/**
	 * now only load the excel file one sheet.
	 */
	public DataTable load(DataTable table, IOParam param) throws IOException {
		if( param==null || param.getInputStream()==null ) {
			throw new IOException("the input param or the inputStream is null");
		}

		InputStream input = param.getInputStream();
		int sheetIndex = 0;
		sheetIndex = param.getPropertyInt(SHEET_NUM, sheetIndex);

		if( table==null ) {
			table = new DataTable("ExcelDataTable");
		}
		HSSFSheet sheet = null;
		try {
			POIFSFileSystem pfs = new POIFSFileSystem(input);
			sheet = (new HSSFWorkbook(pfs)).getSheetAt(sheetIndex);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			throw new IOException("打开Excel文件出错，文件可能损坏或指定打开的工作表不存在");
		}
		
		//1、从sheet中提取数据
		//1.1、获得最大行数
		int iRowNum = sheet.getLastRowNum();
		//1.2、获得表头结构
		int headRow = 0;
		int dataStartRow = 1;
		headRow = param.getPropertyInt(HEADER_ROW, headRow);
		dataStartRow = param.getPropertyInt(DATA_START_ROW, dataStartRow);
		
		String fields[] = getExcelFirstRowNames(sheet, headRow);
		
		try {
			StringHelper.validateUnique( fields );
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new IOException("Excel文件列名不唯一，请检查Excel文件! "+e1.getMessage() );
		}
					
		setTabMeta( table, fields );
		
		//1.3、获得表体数据定制一个专门的数组
		loadData(table ,sheet, dataStartRow, iRowNum);
		
		if( param.isCloseStream() ) {
			try {
				input.close();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
		
		return table;
	}
	
	private void loadData(DataTable table, HSSFSheet sheet, int startRow, int endRow ) {
		HSSFRow row = null;
		HSSFCell cell = null;

		int colcount = table.getColCount() -1;	// because add the rowno col.
		for(int i = startRow; i < endRow+1; i++){
			row = sheet.getRow(i);
			if (row == null){
		        continue;
			}
			DataRow dataRow = table.addRow();
			dataRow.setData(ROWNO, new Integer(i) );
		    for(int j = 0; j < colcount; j++){
			    cell = row.getCell((short) j);
			    dataRow.setData(j, getCellValue(cell) );			    
		    }
		}
	}

	private void setTabMeta(DataTable table, String[] fields) {
		if( table==null || fields ==null ) {
			DSLogger.debug( "input param is null ");
			return ;
		}
		
		table.getColMetaList().addCols(fields);
		table.getColMetaList().addCol( ROWNO );	// for the row no.
		
	}

	private String[] getExcelFirstRowNames(HSSFSheet sheet,int starRow) throws IOException {
		if (sheet == null)
			return null;

		HSSFRow row = null;
		HSSFCell cell = null;
		row = sheet.getRow(starRow);
		if( row==null ) {
			throw new IOException("Excel文件格式不正确，第一行必须是列名行\n");
		}
		int iColNum = row.getLastCellNum();
		String sFildNames[] = new String[iColNum];
		if (row == null)
			return null;
		//错误信息提示
		Object value = null;
		for (int i = 0; i < iColNum; i++) {
			cell = row.getCell((short) i);
			value = getCellValue(cell);
			if(value == null || value.toString().trim().length()<1){
				//throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("102112","UPT102112-000003")/*@res "第"*/+(i+1)+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("102112","UPT102112-000090")/*@res "列表头为空"*/);
				String[] arr={ ""+(i+1)+""};
				throw new IOException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("102112","UPT102112-000090",null,arr)/*@res "列表头为空"*/);
			}
			sFildNames[i] = value.toString().trim();
		}

		return sFildNames;
	}
	
	private Object getCellValue(HSSFCell cell) {
		return nc.vo.hr.tools.dbtool.util.ExcelUtil.getCellValue( cell );
	}

	public TableSet load(TableSet tableSet, IOParam param) throws IOException {
		// TODO Auto-generated method stub

		return null;
	}

	public void save(DataTable table, IOParam param) throws IOException {
		// TODO Auto-generated method stub

	}

	public void save(TableSet tableSet, IOParam param) throws IOException {
		// TODO Auto-generated method stub

	}

	public void addSaveListener(ISaveListener listener) {
		// TODO Auto-generated method stub

	}

	public void removeSaveListener(ISaveListener listener) {
		// TODO Auto-generated method stub

	}

}
