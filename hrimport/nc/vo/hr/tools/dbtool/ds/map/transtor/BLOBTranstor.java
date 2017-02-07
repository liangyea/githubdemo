/*
 * �������� 2006-5-18
 */
package nc.vo.hr.tools.dbtool.ds.map.transtor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.ds.adapter.ErrorResponse;
import nc.vo.hr.tools.dbtool.ds.adapter.IDataAdapter;
import nc.vo.hr.tools.dbtool.ds.adapter.IOParam;
import nc.vo.hr.tools.dbtool.ds.map.ColumnMap;
import nc.vo.hr.tools.dbtool.ds.map.ITranstor;
import nc.vo.pub.BusinessException;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-5-18 9:01:49
 * @author ych
 */
public class BLOBTranstor implements ITranstor {

	/* ���� Javadoc��
	 * @see nc.vo.dbtool.ds.map.ITranstor#transtor(nc.vo.dbtool.ds.adapter.IDataAdapter, nc.vo.dbtool.ds.DataRow, nc.vo.dbtool.ds.map.ColumnMap, nc.vo.dbtool.ds.adapter.IOParam, nc.vo.dbtool.ds.adapter.ErrorResponse)
	 */
	public Object transtor(IDataAdapter dataAdapter, DataRow row,
			ColumnMap colMap, IOParam param, ErrorResponse response)
			throws BusinessException {

		String filePath = row.getString( colMap.getColumnName() );
		
		// read the file to byte.
		File file = new File( filePath );
        long length = file.length();
        byte[] imgData = null;
		try {
			FileInputStream fis = new FileInputStream( file );
			imgData = new byte[(int) length];
			fis.read(imgData);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("�ļ�������!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("�ļ���ȡ����!");
		}
		
		return imgData;
	}

}
