package nc.vo.hr.tools.dbtool.ds.adapter;

import nc.vo.hr.tools.dbtool.ds.DSException;
import nc.vo.hr.tools.dbtool.ds.DataRow;
import nc.vo.hr.tools.dbtool.util.event.BaseEvent;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-209:40:50
 * @author „∆≥§∫£
 */
public class ResolverEvent extends BaseEvent {	
	private static final long serialVersionUID = 1L;
	
	// SOURCE
//    public static int TYPE_DATA_SET = 0;
//    public static int TYPE_DATA_TABLE = 1;
//   public static int TYPE_DATA_ROW = 2;

    // OPER TYPE
	public static final int TYPE_OPER_UNKNOWN = -1;
    public static final int TYPE_OPER_LOAD = 0;
    public static final int TYPE_OPER_SAVE = 1;
    
    public static final int TYPE_OPER_INSERT = 2;
    public static final int TYPE_OPER_UPDATE = 3;
    public static final int TYPE_OPER_DELETE = 4;
    public static final int TYPE_OPER_REAL_DELETE = 5;
    
    // TIME 
    public static final int TYPE_TIME_BEFORE = 0;
    public static final int TYPE_TIME_AFTER = 1;
    public static final int TYPE_TIME_ERROR = 2;

	private DataRow oldRow;
	private ErrorResponse response;
	private DSException exception;
	private int timeType = TYPE_TIME_BEFORE;
	
	
	public ResolverEvent(Object source) {
		super(source);		
	}
    
    public ResolverEvent(Object source,int operType) {
        super(source,operType);      
    }
    
    public ResolverEvent(Object source,int operType, ErrorResponse response) {
        super(source,operType);      
        setResponse(response);
    }
    
    public ResolverEvent(Object source,int operType,int timeType ) {
        super(source,operType);      
        setTimeType( timeType );
    }

	public DSException getException() {
		return exception;
	}

	public void setException(DSException exception) {
		this.exception = exception;
	}

	public DataRow getOldRow() {
		return oldRow;
	}

	public void setOldRow(DataRow oldRow) {
		this.oldRow = oldRow;
	}

	public ErrorResponse getResponse() {
		return response;
	}

	public void setResponse(ErrorResponse response) {
		this.response = response;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

}
