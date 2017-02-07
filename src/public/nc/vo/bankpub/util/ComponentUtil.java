package nc.vo.bankpub.util;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComboBox;
public class ComponentUtil {
    public static void setMiddleInScreen(Component comp) {    	
    	comp.setLocation(getMiddleInScreen(comp));    	
    }
    public static Point getMiddleInScreen(Component comp) {
    	Dimension compSize = comp.getSize();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int x = (int) (screenSize.getWidth() - compSize.getWidth()) / 2;
    	int y = (int) (screenSize.getHeight() - compSize.getHeight()) / 2;
//    	comp.setLocation(x, y);
    	return new Point(x, y);
    }

	public static void initCombo(JComboBox combo, Object[] objs) {
		if (objs == null || objs.length == 0) {
			combo.removeAllItems();
			return;
		}
		for (int i = 0; i < objs.length; i++) {
			combo.addItem(objs[i]);
		}
	}

	// public static

	public static Component[] getComponents(Container c,
			boolean bFilterInvisible) {
		Component[] coms = c.getComponents();
		if (!bFilterInvisible || coms == null || coms.length == 0)
			return coms;
		ArrayList list = new ArrayList();
		for (int i = 0; i < coms.length; i++) {
			if (coms[i].isVisible())
            list.add(coms[i]);
		}
		return (Component[]) list.toArray(new Component[0]);
	}
	
	public static void layout(Container com, int colCount) {
		com.setLayout(null);
		Dimension di = com.getPreferredSize();

		// 6 ÁÐÉèÖÃÎ»ÖÃ

		Component ca[] = getComponents(com, true);
		int N_ONE_LINE_COMPONET = colCount;
		int xUint = di.width / N_ONE_LINE_COMPONET;
		if (xUint < 100) {
			xUint = 100;
		}
		int Y_UNIT = 30;
		int X_SPACE = 5;
		int Y_SPANCE = 5;
		for (int i = 0; i < ca.length; i++) {
			int xLoc = i % N_ONE_LINE_COMPONET;
			int yLoc = i / N_ONE_LINE_COMPONET;
			int x = xLoc * xUint;
			int y = Y_UNIT * yLoc;
			ca[i].setBounds(x + X_SPACE, y + Y_SPANCE, xUint - X_SPACE,
					Y_UNIT - Y_SPANCE);
		}
	}
}
