package test;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(new UFDate());
		//UFDateTime date=ClientEnvironment.getInstance().getServerTime();
	/*	FtpClient ftp = new FtpClient();
		UFDouble  d=new UFDouble(0);
		System.out.println(d);
		System.out.println(UFDouble.ONE_DBL);
		System.out.println(UFDouble.ZERO_DBL);*/
		
		
/*		String str[][] =  new String[2][];
		str[0] = new String[]{"abc","de","abce"};
		str[1] = new String[]{"def0","def1","def2"};
		int lent = str.length;
		for(int i=0;i<lent;i++){
			String[] tem = str[i];
			//System.out.println(str[0][i]);
			systemItem(tem);
		}*/
		System.out.println("hello");
	}

	private static void systemItem(String[] str) {
		int lent = str.length;
		
		for(int i=0;i<lent;i++){
			System.out.println(str[i]);
		}
	}
	
	private static void systemItem1(String[][] str) {
		int lentDetail = str[0].length;
		
		for(int i=0;i<lentDetail;i++){
			System.out.println(str[0][i]);
		}
	}

}
