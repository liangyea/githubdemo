package nc.ui.bankpub.ncsubject;

/**
 * 
 * 功能描述 : 汇总公司数量变化事件
 * 
 * 作者 : 李辉
 * 
 * 创建日期 : (2003-9-16 13:03:19)
 * 
 * 修改记录及日期 : 
 * 
 * 修改人 : 
 * 
 * 备注: 
 */
public interface ICollectCorpsQuanChangedEvent {
/**
 * 
 * 函数功能:增加公司数量变化监听
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-16 13:05:55)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @param listener nc.ui.bank.bjinterface.ICollectCorpQuanListener
 */
void addListener(ICollectCorpQuanListener listener);
/**
 * 
 * 函数功能:取得公司VO
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-16 13:07:27)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * 
 * @return nc.vo.bank.bjinterface.BankCorpVO[]
 */
nc.vo.bankpub.pub.BankCorpVO[] getCorps();
}
