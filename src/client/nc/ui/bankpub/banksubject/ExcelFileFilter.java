package nc.ui.bankpub.banksubject;

import javax.swing.filechooser.FileView;

/**
 * Excel�ļ�����������������ʾ�ļ��Ի���ʱ���˳�Excel(*.xls)�ļ�
 * �������ڣ�(2005-4-25 15:49:20)
 * @author������
 */
public class ExcelFileFilter extends javax.swing.filechooser.FileFilter {
/**
 * ExcelFileFilter ������ע�⡣
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
	return "Excel�ļ�(*.xls)";
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