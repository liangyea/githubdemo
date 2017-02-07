package nc.vo.hr.tools.dbtool.ds;

/**
 * 
 * Describe:LightTemplate
 *      
 * @version 1.0 2006-3-20 9:54:36
 * @author 闫长海
 * @see nc.ui.pub.bill.IBillItem
 */
public interface IDataType {

    //数据类型
	int UNKNOWN	= -1;	//　不确定
    int STRING = 0; //字符
    int INTEGER = 1; //整数
    int DECIMAL = 2; //小数
    int DATE = 3; //日期
    int BOOLEAN = 4; //逻辑
    
//    int UFREF = 5; //参照
//    int COMBO = 6; //下拉
//    int USERDEF = 7; //自定义项档案
    
    int TIME = 8; //时间

//    int TEXTAREA = 9; //大文本
//    int IMAGE = 10; //图片
//    int OBJECT = 11; //对象
//    int BLANK = 12; //占位块
    
}
