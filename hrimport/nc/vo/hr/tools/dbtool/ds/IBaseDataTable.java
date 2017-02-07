package nc.vo.hr.tools.dbtool.ds;

import java.io.Serializable;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-12 14:57:22
 * @author „∆≥§∫£
 */
public interface IBaseDataTable extends Serializable,Cloneable{
    
    boolean isEmpty();
    
    // count.
    int getRowCount();
    int getColCount();
    
    // data access.
//    DataRow getRow(int row );
    
    Object getData(int row , int col );
    // by the col name
    Object getData(int row , String colName );
       
    // table meta.
    TableMeta getTableMeta();
    void setTableMeta(TableMeta tableMeta);
}
