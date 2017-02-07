package nc.vo.hr.tools.dbtool.util.event;

import java.util.EventObject;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-20 16:46:11
 * @author „∆≥§∫£
 */
public class BaseEvent extends EventObject {
    private static final long serialVersionUID = -5633702099477011524L;

    public BaseEvent(Object source) {
        super(source);        
    }

    public BaseEvent(Object source,int type) {
        super(source); 
        setType(type);
    }
    private int type = -1;

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }    
}
