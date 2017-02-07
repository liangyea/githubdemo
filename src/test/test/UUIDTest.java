package test;

import java.util.Random;


public class UUIDTest {
	
	public static void main(String[] args) {
		/*for(int i=0;i<10;i++) {
			UUID uuid = UUID.randomUUID();
			String s = uuid.toString();
			s = s.replaceAll("-", "");
			System.out.println(s);
			System.out.println(s.length());
		}*/
		final char[] codePool = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random ra =new Random();
		for (int i=0;i<4;i++) {
			System.out.println(codePool[ra.nextInt(codePool.length)]);
		}
	}

}
