package nc.vo.hr.tools.dbtool.ds;

/**
 * 
 * Describe:LightTemplate
 *      
 * @version 1.0 2006-3-20 9:54:36
 * @author �Ƴ���
 * @see nc.ui.pub.bill.IBillItem
 */
public interface IDataType {

    //��������
	int UNKNOWN	= -1;	//����ȷ��
    int STRING = 0; //�ַ�
    int INTEGER = 1; //����
    int DECIMAL = 2; //С��
    int DATE = 3; //����
    int BOOLEAN = 4; //�߼�
    
//    int UFREF = 5; //����
//    int COMBO = 6; //����
//    int USERDEF = 7; //�Զ������
    
    int TIME = 8; //ʱ��

//    int TEXTAREA = 9; //���ı�
//    int IMAGE = 10; //ͼƬ
//    int OBJECT = 11; //����
//    int BLANK = 12; //ռλ��
    
}
