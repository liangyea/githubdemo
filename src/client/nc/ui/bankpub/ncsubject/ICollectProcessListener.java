package nc.ui.bankpub.ncsubject;

/**
 * 
 * �������� : ���ܹ��̼�����
 * 
 * ���� : ���
 * 
 * �������� : (2003-9-18 20:43:23)
 * 
 * �޸ļ�¼������ : 
 * 
 * �޸��� : 
 * 
 * ��ע: 
 */
public interface ICollectProcessListener {
/**
 * 
 * ��������:��ʼִ��
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-18 20:46:47)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * @param event nc.ui.bank.bjinterface.ICollectProcessEvent
 */
void startTask(ICollectProcessEvent event);
/**
 * 
 * ��������:ִ�����
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-18 20:47:33)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * @param event nc.ui.bank.bjinterface.ICollectProcessEvent
 */
void stopTask(ICollectProcessEvent event);
}
