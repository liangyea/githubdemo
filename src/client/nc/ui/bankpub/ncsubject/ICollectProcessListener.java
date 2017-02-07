package nc.ui.bankpub.ncsubject;

/**
 * 
 * 功能描述 : 汇总过程监听器
 * 
 * 作者 : 李辉
 * 
 * 创建日期 : (2003-9-18 20:43:23)
 * 
 * 修改记录及日期 : 
 * 
 * 修改人 : 
 * 
 * 备注: 
 */
public interface ICollectProcessListener {
/**
 * 
 * 函数功能:开始执行
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-18 20:46:47)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * @param event nc.ui.bank.bjinterface.ICollectProcessEvent
 */
void startTask(ICollectProcessEvent event);
/**
 * 
 * 函数功能:执行完毕
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-18 20:47:33)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * @param event nc.ui.bank.bjinterface.ICollectProcessEvent
 */
void stopTask(ICollectProcessEvent event);
}
