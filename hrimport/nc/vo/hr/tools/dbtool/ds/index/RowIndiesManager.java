package nc.vo.hr.tools.dbtool.ds.index;

import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataTable;
import nc.vo.hr.tools.dbtool.ds.DefaultRowIndex;
import nc.vo.hr.tools.dbtool.ds.IRowIndex;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-1913:56:11
 * @author „∆≥§∫£
 */
public class RowIndiesManager extends RowIndies{
	
	private DataTable table;
	
	private IRowIndex defaultIndex;
	private IRowIndex filterIndex;	// command excute the chld or state.
	private IRowIndex pageIndex;
	private IRowIndex sortIndex;
		
	public RowIndiesManager(DataTable table) throws DSException {
		super(null);	
		this.table = table;
		init();
	}
	
	private void init() {
		try {
			setInnerIndex( getSortIndex() );
		} catch (DSException e) {
			e.printStackTrace();
			nc.vo.hr.tools.dbtool.util.DSLogger.error("the index is null");
		}
	}

	public RowIndiesManager(IRowIndex index) throws DSException {
		super(index);		
	}

	public void index() {
		super.index();
	}

	protected IRowIndex getDefaultIndex() {
		if( defaultIndex==null ) {
			defaultIndex =  new DefaultRowIndex(table);
		}
		return defaultIndex;
	}
	
	protected IRowIndex getPageIndex() {
		if( pageIndex==null ) {
			try {
				pageIndex =  new PageIndex( getFilterIndex() );
			} catch (DSException e) {
				e.printStackTrace();				
			}
		}
		return pageIndex;
	}

	protected IRowIndex getSortIndex() {
		if( sortIndex==null ) {
			try {
				sortIndex =  new SortIndex( getPageIndex() );
			} catch (DSException e) {
				e.printStackTrace();				
			}
		}
		return sortIndex;
	}
	
	protected IRowIndex getFilterIndex() {
		if( filterIndex==null ) {
			try {
				filterIndex =  new FilterIndex( getDefaultIndex() );
			} catch (DSException e) {
				e.printStackTrace();				
			}
		}
		return filterIndex;
	}

}
