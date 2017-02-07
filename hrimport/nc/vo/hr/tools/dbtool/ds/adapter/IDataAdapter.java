package nc.vo.hr.tools.dbtool.ds.adapter;

import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.TableSet;
import nc.vo.pub.BusinessException;


public interface IDataAdapter {
	
	// load()
	DataTable load(DataTable table, IOParam param )throws IOException, BusinessException;
	TableSet load(TableSet tableSet, IOParam param )throws IOException, BusinessException;
    
	// save()
	void save(DataTable table, IOParam param )throws IOException, BusinessException;
	void save(TableSet tableSet, IOParam param )throws IOException, BusinessException;
	
	// resolveListener.
	void addSaveListener( ISaveListener listener );
	void removeSaveListener( ISaveListener listener );
	
}
