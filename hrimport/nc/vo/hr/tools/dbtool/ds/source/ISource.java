package nc.vo.hr.tools.dbtool.ds.source;
/**
 * Describe:HR_PUB3.5
 * 		data source interface for data binding.
 * @version 1.0	2006-4-1813:09:24
 * @author „∆≥§∫£
 */
public interface ISource<E> {
	int size();
	E getData(String key);
	void setData(String key, E val);
}
