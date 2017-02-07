package nc.ui.bankpub.ncsubject;

import nc.vo.bankpub.pub.BankCorpVO;

/**
 * 
 * 功能描述 : 汇总过程事件
 * 
 * 作者 : 李辉
 * 
 * 创建日期 : (2003-9-18 20:43:46)
 * 
 * 修改记录及日期 : 
 * 
 * 修改人 : 
 * 
 * 备注: 
 */
public interface ICollectProcessEvent {
/**
 * 
 * 函数功能:增加过程监听器
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-18 20:50:02)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * @param listener nc.ui.bank.bjinterface.ICollectProcessListener
 */
void addCollProcessListener(ICollectProcessListener listener);
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
BankCorpVO[] getCorps();
/**
 * 
 * 函数功能:取得任务最终消息
 * 
 * 参数:
 * 
 * 返回值:
 * 
 * 异常:
 * 
 * 创建日期：(2003-9-18 20:45:48)
 * 
 * 备注:
 * 		修改:
 * 		作用:
 * @return java.lang.String
 */
String getFinalMsg();
}
