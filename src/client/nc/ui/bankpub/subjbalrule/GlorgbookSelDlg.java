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
	//列表框左右标题
//	private final static String TITLE_LEFT = "待选帐簿";
//	private final static String TITLE_RIGHT = "已选帐簿";
//	//公司变化监听器
//	private ICollectCorpQuanListener m_listener = null;
//	private final static String DLG_TITLE = "选择帐簿";
	
private UILabel getLblMsg() {
	if (lblMsg == null) {
		lblMsg = new UILabel("lblMsg");
		lblMsg.setText("请选择对账规则导入的来源核心传NC科目对照表所在的主体账簿：");
		lblMsg.setToolTipText(lblMsg.getText());
	}
	return lblMsg;
}

private UIRefPane getRefGlorgbook() {
	if (refGlorgbook == null) {
		refGlorgbook = new UIRefPane();
		refGlorgbook.setRefNodeName("主体账簿");
	}
	return refGlorgbook;
}
/**
 * CorpChooseDlg 构造子注解。
 */
public GlorgbookSelDlg() {
	super();
	initialize();
}
/**
 * CorpChooseDlg 构造子注解。
 * @param parent java.awt.Container
 */
public GlorgbookSelDlg(java.awt.Container parent) {
	super(parent);
	initialize();//add by zcm
}
/**
 * CorpChooseDlg 构造子注解。
 * @param parent java.awt.Container
 * @param title java.lang.String
 */
public GlorgbookSelDlg(java.awt.Container parent, String title) {
	super(parent, title);
}
/**
 * CorpChooseDlg 构造子注解。
 * @param owner java.awt.Frame
 */
public GlorgbookSelDlg(java.awt.Frame owner) {
	super(owner);
}
/**
 * CorpChooseDlg 构造子注解。
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
			MessageDialog.showErrorDlg(this, "错误", "请选择主体账簿");
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
 * 返回 UIBtnCancel 特性值。
 * @return nc.ui.pub.beans.UIButton
 */
/* 警告：此方法将重新生成。 */
private nc.ui.pub.beans.UIButton getUIBtnCancel() {
	if (ivjUIBtnCancel == null) {
		try {
			ivjUIBtnCancel = new nc.ui.pub.beans.UIButton();
			ivjUIBtnCancel.setName("UIBtnCancel");
			ivjUIBtnCancel.setText("取消");
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
 * 返回 UIBtnOK 特性值。
 * @return nc.ui.pub.beans.UIButton
 */
/* 警告：此方法将重新生成。 */
private nc.ui.pub.beans.UIButton getUIBtnOK() {
	if (ivjUIBtnOK == null) {
		try {
			ivjUIBtnOK = new nc.ui.pub.beans.UIButton();
			ivjUIBtnOK.setName("UIBtnOK");
			ivjUIBtnOK.setText("确定");
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
 * 返回 UIDialogContentPane 特性值。
 * @return javax.swing.JPanel
 */
/* 警告：此方法将重新生成。 */
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
 * 返回 UIPanelBtn 特性值。
 * @return nc.ui.pub.beans.UIPanel
 */
/* 警告：此方法将重新生成。 */
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
 * 每当部件抛出异常时被调用
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* 除去下列各行的注释，以将未捕捉到的异常打印至 stdout。 */
	System.out.println("--------- 未捕捉到的异常 ---------");
	exception.printStackTrace(System.out);
}
/**
* 
* 函数功能:初始化
* 
* 参数:
* 
* 返回值:
* 
* 异常:
* 
* 创建日期：(2003-9-1 12:56:05)
* 
* 备注:
* 		修改:
* 		作用:
* 
*/
private void init() {
	//设置窗口标题
	setTitle("主体账簿选择");



	//初始化监听
	initListener();

}
/**
 * 初始化类。
 */
/* 警告：此方法将重新生成。 */
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
* 函数功能:初始化监听
* 
* 参数:
* 
* 返回值:
* 
* 异常:
* 
* 创建日期：(2003-9-1 12:56:29)
* 
* 备注:
* 		修改:
* 		作用:
* 
*/
private void initListener() {

	getUIBtnOK().addActionListener(this);
	getUIBtnCancel().addActionListener(this);
}
/**
 * 主入口点 - 当部件作为应用程序运行时，启动这个部件。
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
		System.err.println("nc.ui.pub.beans.UIDialog 的 main() 中发生异常");
		exception.printStackTrace(System.out);
	}
}
}