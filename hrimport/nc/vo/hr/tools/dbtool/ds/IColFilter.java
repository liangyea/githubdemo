/*
 * 创建日期 2006-6-5
 */
package nc.vo.hr.tools.dbtool.ds;

/**
 * Describe:HR_PUB3.5
 * 		mainly for the db persist col filter.
 * @version 1.0	2006-6-5 16:01:31
 * @author ych
 */
public interface IColFilter {
    boolean isColVisible( String colName );
}
