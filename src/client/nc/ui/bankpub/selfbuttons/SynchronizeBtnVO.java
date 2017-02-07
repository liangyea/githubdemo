package nc.ui.bankpub.selfbuttons;

import java.awt.Event;

import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.button.IBillButtonVO;
import nc.vo.trade.button.ButtonVO;

/**
 * �˴���������˵���� �������ڣ�(2004-2-26 22:37:36)
 * 
 * @author�����ھ�1
 */
public class SynchronizeBtnVO implements IBillButtonVO
{

	public final static String btnChinaName = "�Ӳ��ŵ���ͬ��";

	/**
	 * BusiTypeBtnVO ������ע�⡣
	 */
	public SynchronizeBtnVO()
	{
		super();
	}

	/**
	 * ���ض�ӦButtonVO�� �������ڣ�(2004-2-26 22:41:16)
	 * 
	 * @return nc.vo.trade.button.ButtonVO
	 */
	public nc.vo.trade.button.ButtonVO getButtonVO()
	{
		//1.���Ӱ�ť
		ButtonVO btnVo = new ButtonVO();
		btnVo.setBtnNo(IButtonID.SYNCRONIZE);
		btnVo.setBtnChinaName(btnChinaName);
		btnVo.setBtnName(btnChinaName);
		btnVo.setHintStr("�Ӳ��ŵ���ͬ��");
		btnVo.setOperateStatus(new int[] { IBillOperate.OP_NOTEDIT,
				IBillOperate.OP_INIT });
		btnVo.setBusinessStatus(null);
		btnVo.setModifiers(Event.CTRL_MASK);
		return btnVo;
	}
}
