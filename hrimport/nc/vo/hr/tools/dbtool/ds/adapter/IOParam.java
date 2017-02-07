package nc.vo.hr.tools.dbtool.ds.adapter;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Describe:HR_PUB3.5
 * 		
 * @version 1.0	2006-4-208:58:03
 * @author 闫长海
 */
public class IOParam extends nc.vo.hr.tools.dbtool.util.Properties{
	private static final long serialVersionUID = 6122028387802814956L;
	
	   private OutputStream outputStream;
	   private InputStream inputStream;
	    	   
	   private boolean isCloseStream = true;
	   
	   private ISaveListener saveListener;
	   
	    public boolean isCloseStream() {
		return isCloseStream;
	}

	public void setCloseStream(boolean isCloseStream) {
		this.isCloseStream = isCloseStream;
	}

		/**
	     * 
	     */
	    public IOParam() {
	        super();
	    }
	    
	    public IOParam(OutputStream outputstream) {
	        super();
	        setOutputStream(outputstream);
	    }
	    
	    public IOParam(InputStream inputstream) {
	        super();
	        setInputStream(inputstream);
	    }
	    
	    /* (non-Javadoc)
	     * @see nc.ui.print.util.io.IOParam#getOutputStream()
	     */
	    public OutputStream getOutputStream() {
	        return outputStream;
	    }

	    /* (non-Javadoc)
	     * @see nc.ui.print.util.io.IOParam#setOutputStream(java.io.OutputStream)
	     */
	    public void setOutputStream(OutputStream outputstream) {
	        this.outputStream = outputstream;
	    }

	    /* (non-Javadoc)
	     * @see nc.ui.print.util.io.IOParam#getInputStream()
	     */
	    public InputStream getInputStream() {
	        return inputStream;
	    }

	    /* (non-Javadoc)
	     * @see nc.ui.print.util.io.IOParam#setInputStream(java.io.InputStream)
	     */
	    public void setInputStream(InputStream inputstream) {
	        this.inputStream = inputstream;
	    }
	    
	    /**
	     * will overwrite to show dlg or set other param.
	     */
	    public void setIOParam() {
	    	
	    }
	    
	/**
	 * @return 返回 saveListener。
	 */
	public ISaveListener getSaveListener() {
		return saveListener;
	}
	/**
	 * @param saveListener 要设置的 saveListener。
	 */
	public void setSaveListener(ISaveListener saveListener) {
		this.saveListener = saveListener;
	}
}
