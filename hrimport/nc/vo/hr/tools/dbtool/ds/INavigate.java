package nc.vo.hr.tools.dbtool.ds;

import nc.vo.hr.tools.dbtool.ds.source.IEnumerator;

/**
 * Describe:HR_PUB3.5
 * 		navigate table
 * @version 1.0	2006-4-12 16:17:30
 * @author „∆≥§∫£
 */
public interface INavigate extends IEnumerator{
    
    int getCurrentRow();
    
    DataRow first();
    DataRow next();
    DataRow last();
    DataRow moveTo(int row);

    DataRow getCurrentData();
        
    //
    boolean isPaginate();
    void setPageSize(int size);
    void setPageCount(int count);
    void setPageIndex(int index);
    int getPageSize();
    int getPageCount();
    int getPageIndex();
    
    //event NavigeteEvent
    
}
