package nc.ui.bankpub.selfbuttons;

import java.awt.Event;

import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.button.IBillButtonVO;
import nc.vo.trade.button.ButtonVO;

/**
 * 此处插入类型说明。 创建日期：(2004-2-26 22:37:36)
 * 
 * @author：樊冠军1
 */
public class SynchronizeBtnVO implements IBillButtonVO
{

	public final static String btnChinaName = "从部门档案同步";

	/**
	 * BusiTypeBtnVO 构造子注解。
	 */
	public SynchronizeBtnVO()
	{
		super();
	}

	/**
	 * 返回对应ButtonVO。 创建日期：(2004-2-26 22:41:16)
	 * 
	 * @return nc.vo.trade.button.ButtonVO
	 */
	public nc.vo.trade.button.ButtonVO getButtonVO()
	{
		//1.增加按钮
		ButtonVO btnVo = new ButtonVO();
		btnVo.setBtnNo(IButtonID.SYNCRONIZE);
		btnVo.setBtnChinaName(btnChinaName);
		btnVo.setBtnName(btnChinaName);
		btnVo.setHintStr("从部门档案同步");
		btnVo.setOperateStatus(new int[] { IBillOperate.OP_NOTEDIT,
				IBillOperate.OP_INIT });
		btnVo.setBusinessStatus(null);
		btnVo.setModifiers(Event.CTRL_MASK);
		return btnVo;
	}
}
