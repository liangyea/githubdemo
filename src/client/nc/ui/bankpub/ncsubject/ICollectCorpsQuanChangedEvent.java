package nc.ui.bankpub.ncsubject;

/**
 * 
 * �������� : ���ܹ�˾�����仯�¼�
 * 
 * ���� : ���
 * 
 * �������� : (2003-9-16 13:03:19)
 * 
 * �޸ļ�¼������ : 
 * 
 * �޸��� : 
 * 
 * ��ע: 
 */
public interface ICollectCorpsQuanChangedEvent {
/**
 * 
 * ��������:���ӹ�˾�����仯����
 * 
 * ����:
 * 
 * ����ֵ:
 * 
 * �쳣:
 * 
 * �������ڣ�(2003-9-16 13:05:55)
 * 
 * ��ע:
 * 		�޸�:
 * 		����:
 * 
 * @param listener nc.ui.bank.bjinterface.ICollectCorpQuanListener
 */
void addListener(ICollectCorpQuanListener listener);
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
nc.vo.bankpub.pub.BankCorpVO[] getCorps();
}
