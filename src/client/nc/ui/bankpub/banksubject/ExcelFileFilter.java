package nc.ui.bankpub.banksubject;

import javax.swing.filechooser.FileView;

/**
 * Excel文件过滤器，用于在显示文件对话框时过滤出Excel(*.xls)文件
 * 创建日期：(2005-4-25 15:49:20)
 * @author：孙锐
 */
public class ExcelFileFilter extends javax.swing.filechooser.FileFilter {
/**
 * ExcelFileFilter 构造子注解。
 */
public ExcelFileFilter() {
	super();
}
	/**
	 * Whether the given file is accepted by this filter.
	 */
public boolean accept(java.io.File f) {
	if (f != null) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = getExtension(f);
		if(extension != null && extension.equals("xls")){
			return true;
		}
	}
	return false;
}
	/**
	 * The description of this filter. For example: "JPG and GIF Images"
	 * @see FileView#getName
	 */
public String getDescription() {
	return "Excel文件(*.xls)";
}



public String getExtension(java.io.File f) {
	if (f != null) {
		String filename = f.getName();
		int i = filename.lastIndexOf('.');
		if (i > 0 && i < filename.length() - 1) {
			return filename.substring(i + 1).toLowerCase();
		};
	}
	return null;
}
}