package nc.vo.hr.tools.dbtool.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-2011:12:43
 * @author 闫长海
 */
public class ExcelUtil {
	
	public static Object getCellValue(HSSFCell cell) {
		if (cell == null)
			return null;
		
		Object obj = null;
		String strval = null;
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC :
					if( HSSFDateUtil.isCellDateFormatted(cell) ) {
						obj = new UFDate( cell.getDateCellValue() );
					}else {
						double value = cell.getNumericCellValue();
						if(((long)value)==value){//integer
			                return obj = new Long((long)value);
						}
						obj = new UFDouble(value);
					}
					break;
			case HSSFCell.CELL_TYPE_STRING :
				//处理空格的情况
	 			strval = cell.getStringCellValue();
	 			strval = (strval==null)||(strval.trim().length()<1)?null:strval.trim();
				obj = strval;
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN :
				obj = new Boolean( cell.getBooleanCellValue() );
				break;
			case HSSFCell.CELL_TYPE_FORMULA :
//				String formula = cell.getCellFormula();	//?
				obj = cell.getDateCellValue();	// date
				break;
			case HSSFCell.CELL_TYPE_BLANK :
				break;
			default :
				DSLogger.info("unsuported sell type");
				break;
		}
		
		return obj;
	}
	
	
	public static String[] getSheetName(String filePath ) {

		try {
			POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(
					filePath));
			HSSFWorkbook workBook = new HSSFWorkbook(pfs);
			int count = workBook.getNumberOfSheets();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				list.add(workBook.getSheetName(i));
			}

			return list.toArray(new String[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			DSLogger.info("打开Excel文件出错!");
		}
		return new String[0];
	}
}
