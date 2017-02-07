package nc.ui.bankpub.subjbalrule;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;

import nc.ui.bankpub.ncsubject.CorpChooseDlg;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIRefPane;

public class GlorgbookSelDlg extends UIDialog implements ActionListener {
	private nc.ui.pub.beans.UIButton ivjUIBtnCancel = null;
	private nc.ui.pub.beans.UIButton ivjUIBtnOK = null;
	private javax.swing.JPanel ivjUIDialogContentPane = null;
	
	private UILabel lblMsg;
	
	private UIRefPane refGlorgbook;
	
//	private nc.ui.pub.beans.UIListToList ivjUIList2ListCorp = null;
	private nc.ui.pub.beans.UIPanel ivjUIPanelBtn = null;
	//�б�����ұ���
//	private final static String TITLE_LEFT = "��ѡ�ʲ�";
//	private final static String TITLE_RIGHT = "��ѡ�ʲ�";
//	//��˾�仯������
//	private ICollectCorpQuanListener m_listener = null;
//	private final static String DLG_TITLE = "ѡ���ʲ�";
	
private UILabel getLblMsg() {
	if (lblMsg == null) {
		lblMsg = new UILabel("lblMsg");
		lblMsg.setText("��ѡ����˹��������Դ���Ĵ�NC��Ŀ���ձ����ڵ������˲���");
		lblMsg.setToolTipText(lblMsg.getText());
	}
	return lblMsg;
}

private UIRefPane getRefGlorgbook() {
	if (refGlorgbook == null) {
		refGlorgbook = new UIRefPane();
		refGlorgbook.setRefNodeName("�����˲�");
	}
	return refGlorgbook;
}
/**
 * CorpChooseDlg ������ע�⡣
 */
public GlorgbookSelDlg() {
	super();
	initialize();
}
/**
 * CorpChooseDlg ������ע�⡣
 * @param parent java.awt.Container
 */
public GlorgbookSelDlg(java.awt.Container parent) {
	super(parent);
	initialize();//add by zcm
}
/**
 * CorpChooseDlg ������ע�⡣
 * @param parent java.awt.Container
 * @param title java.lang.String
 */
public GlorgbookSelDlg(java.awt.Container parent, String title) {
	super(parent, title);
}
/**
 * CorpChooseDlg ������ע�⡣
 * @param owner java.awt.Frame
 */
public GlorgbookSelDlg(java.awt.Frame owner) {
	super(owner);
}
/**
 * CorpChooseDlg ������ע�⡣
 * @param owner java.awt.Frame
 * @param title java.lang.String
 */
public GlorgbookSelDlg(java.awt.Frame owner, String title) {
	super(owner, title);
}
/**
 * Invoked when an action occurs.
 */
public void actionPerformed(java.awt.event.ActionEvent e) {
	if (e.getSource() == getUIBtnOK()) {
		if(getRefGlorgbook().getRefPK() == null || getRefGlorgbook().getRefPK().trim().length() == 0) {
			MessageDialog.showErrorDlg(this, "����", "��ѡ�������˲�");
			return;
		}
		closeOK();
	}
	else if (e.getSource() == getUIBtnCancel()) {
		closeCancel();
	}
}

public String getPk_glorgbook() {
	return getRefGlorgbook().getRefPK();

}
/**
 * ���� UIBtnCancel ����ֵ��
 * @return nc.ui.pub.beans.UIButton
 */
/* ���棺�˷������������ɡ� */
private nc.ui.pub.beans.UIButton getUIBtnCancel() {
	if (ivjUIBtnCancel == null) {
		try {
			ivjUIBtnCancel = new nc.ui.pub.beans.UIButton();
			ivjUIBtnCancel.setName("UIBtnCancel");
			ivjUIBtnCancel.setText("ȡ��");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjUIBtnCancel;
}
/**
 * ���� UIBtnOK ����ֵ��
 * @return nc.ui.pub.beans.UIButton
 */
/* ���棺�˷������������ɡ� */
private nc.ui.pub.beans.UIButton getUIBtnOK() {
	if (ivjUIBtnOK == null) {
		try {
			ivjUIBtnOK = new nc.ui.pub.beans.UIButton();
			ivjUIBtnOK.setName("UIBtnOK");
			ivjUIBtnOK.setText("ȷ��");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjUIBtnOK;
}
/**
 * ���� UIDialogContentPane ����ֵ��
 * @return javax.swing.JPanel
 */
/* ���棺�˷������������ɡ� */
private javax.swing.JPanel getUIDialogContentPane() {
	if (ivjUIDialogContentPane == null) {
		try {
			ivjUIDialogContentPane = new javax.swing.JPanel();
			ivjUIDialogContentPane.setName("UIDialogContentPane");
			ivjUIDialogContentPane.setLayout(new java.awt.BorderLayout());
			getUIDialogContentPane().add(getLblMsg(), BorderLayout.NORTH);
			getUIDialogContentPane().add(getRefGlorgbook(), "Center");
			getUIDialogContentPane().add(getUIPanelBtn(), "South");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjUIDialogContentPane;
}

/**
 * ���� UIPanelBtn ����ֵ��
 * @return nc.ui.pub.beans.UIPanel
 */
/* ���棺�˷������������ɡ� */
private nc.ui.pub.beans.UIPanel getUIPanelBtn() {
	if (ivjUIPanelBtn == null) {
		try {
			ivjUIPanelBtn = new nc.ui.pub.beans.UIPanel();
			ivjUIPanelBtn.setName("UIPanelBtn");
			ivjUIPanelBtn.setPreferredSize(new java.awt.Dimension(0, 35));
			ivjUIPanelBtn.setLayout(
				new javax.swing.BoxLayout(getUIPanelBtn(), javax.swing.BoxLayout.X_AXIS));

			// user code begin {1}
			getUIPanelBtn().add(Box.createHorizontalGlue());
			getUIPanelBtn().add(getUIBtnOK(), getUIBtnOK().getName());
			getUIPanelBtn().add(Box.createHorizontalStrut(10));
			getUIPanelBtn().add(getUIBtnCancel(), getUIBtnCancel().getName());
			getUIPanelBtn().add(Box.createHorizontalGlue());
			// user code end
		}
		catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjUIPanelBtn;
}
/**
 * ÿ�������׳��쳣ʱ������
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* ��ȥ���и��е�ע�ͣ��Խ�δ��׽�����쳣��ӡ�� stdout�� */
	System.out.println("--------- δ��׽�����쳣 ---------");
	exception.printStackTrace(System.out);
}
/**
* 
* ��������:��ʼ��
* 
* ����:
* 
* ����ֵ:
* 
* �쳣:
* 
* �������ڣ�(2003-9-1 12:56:05)
* 
* ��ע:
* 		�޸�:
* 		����:
* 
*/
private void init() {
	//���ô��ڱ���
	setTitle("�����˲�ѡ��");



	//��ʼ������
	initListener();

}
/**
 * ��ʼ���ࡣ
 */
/* ���棺�˷������������ɡ� */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("CorpChooseDlg");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(544, 352);
		setResizable(false);
		setContentPane(getUIDialogContentPane());
	}
	catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	init();
	// user code end
}
/**
* 
* ��������:��ʼ������
* 
* ����:
* 
* ����ֵ:
* 
* �쳣:
* 
* �������ڣ�(2003-9-1 12:56:29)
* 
* ��ע:
* 		�޸�:
* 		����:
* 
*/
private void initListener() {

	getUIBtnOK().addActionListener(this);
	getUIBtnCancel().addActionListener(this);
}
/**
 * ����ڵ� - ��������ΪӦ�ó�������ʱ���������������
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		CorpChooseDlg aCorpChooseDlg;
		aCorpChooseDlg = new CorpChooseDlg();
		aCorpChooseDlg.setModal(true);
		aCorpChooseDlg.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		aCorpChooseDlg.show();
		java.awt.Insets insets = aCorpChooseDlg.getInsets();
		aCorpChooseDlg.setSize(aCorpChooseDlg.getWidth() + insets.left + insets.right, aCorpChooseDlg.getHeight() + insets.top + insets.bottom);
		aCorpChooseDlg.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("nc.ui.pub.beans.UIDialog �� main() �з����쳣");
		exception.printStackTrace(System.out);
	}
}
}