package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.vo.hr.tools.dbtool.util.ListMap;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-12 16:51:22
 * @author „∆≥§∫£
 */
public class ColMetaList implements Serializable {    
    private static final long serialVersionUID = 743032010132291250L;

    transient private TableMeta tableMeta;    
    private ListMap<ColumnMeta> list;
    
    // add event
    /**
     * 
     */
    public ColMetaList(TableMeta tableMeta) {
        super();        
        setTableMeta(tableMeta);
    }
    
    /**
     * 
     * @param colNames
     */
    public ColMetaList(String[] colNames) {
        super();
        addCols(colNames);
    }

    public ListMap<ColumnMeta> getList() {
		if( list==null ) {
			list = new ListMap<ColumnMeta>("colName");
		}
		return list;
	}
	
    public int getColCount() {
        return getList().size();
    }

    // col oper.
    public ColumnMeta[] getColMetas(int col) {
    	return getList().toArray(new ColumnMeta[getList().size()] );
    }
    
    public String[] getColMetaNames() {
    	List<String> list = new ArrayList<String>();
    	
    	for (int i = 0; i < getColCount(); i++) {
			list.add( getColMeta(i).getColName() );
		}
    	
    	return list.toArray(new String[list.size()]);
    }
    
    public ColumnMeta getColMeta(int col) {
        return getList().get(col);
    }
    
    public ColumnMeta getColMeta(String colName) {
        return getList().getData(colName);
    }
    
    public void setColMeta(int col, ColumnMeta colMeta) {        
    	getList().set(col, colMeta);
    }
    
    public void addCol(ColumnMeta col) { 
    	if( col==null ) {
    		return;
    	}
    	// if exist ,no t add.
    	if( getColMeta(col.getColName() )!=null ) {
    		nc.vo.hr.tools.dbtool.util.DSLogger.debug(col.getColName()+"is exist,add fail. ");
    		return;
    	}
    	
    	getList().add(col);
    }
    
    public void addCols(ColumnMeta[] cols) {        
//    	getList().addAll(Arrays.asList(cols));
    	if( cols==null ) {
    		return;
    	}
    	for (int i = 0; i < cols.length; i++) {
			addCol( cols[i] );
		}
    }
    
    public void addCols(String[] colNames) {
        if( colNames==null ) {
        	return ;
        }
        for (int i = 0; i < colNames.length; i++) {
			addCol(colNames[i]);
		}
    }

	public void addCol(String colName ) {
		ColumnMeta meta = creDefaultColMeta(colName);
		addCol(meta);
	}
    
    protected ColumnMeta creDefaultColMeta(String colName) {    	
		return new ColumnMeta(colName, getTableMeta() );
	}

    public ColumnMeta addCol() {
		ColumnMeta meta = creDefaultColMeta(null);
        getList().add( meta );
        return meta;
    }
    public ColumnMeta removeCol(int index) {
    	return getList().remove(index);
    }
    
    public void removeAll() {
        getList().clear();
    }
    
    // move col

    public TableMeta getTableMeta() {
		return tableMeta;
	}

    public void setTableMeta(TableMeta tableMeta) {
		this.tableMeta = tableMeta;
	}
    
    public int getColIndex(String colName ) {
    	return getList().getIndex(colName);
    }

	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String toString() {
		return getList().toString();
	}    
    
    // col oper event.
    
}
