package nc.ui.bankpub.ncsubject;

import nc.vo.bankpub.pub.BankCorpVO;

/**
 * 
 * �������� : ���ܹ����¼�
 * 
 * ���� : ���
 * 
 * �������� : (2003-9-18 20:43:46)
 * 
 * �޸ļ�¼������ : 
 * 
 * �޸��� : 
 * 
 * ��ע: 
 */
public interface ICollectProcessEvent {
/**
 * 
 * ��������:���ӹ��̼�����
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-18 20:50:02)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * @param listener nc.ui.bank.bjinterface.ICollectProcessListener
 */
void addCollProcessListener(ICollectProcessListener listener);
	/**
 * 
 * ��������:ȡ�ù�˾VO
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-16 13:07:27)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @return nc.vo.bank.bjinterface.BankCorpVO[]
 */
BankCorpVO[] getCorps();
/**
 * 
 * ��������:ȡ������������Ϣ
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-18 20:45:48)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * @return java.lang.String
 */
String getFinalMsg();
}
